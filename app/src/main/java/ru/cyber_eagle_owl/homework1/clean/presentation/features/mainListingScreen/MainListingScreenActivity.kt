package ru.cyber_eagle_owl.homework1.clean.presentation.features.mainListingScreen

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main_listing_screen.*
import ru.cyber_eagle_owl.homework1.R
import ru.cyber_eagle_owl.homework1.base.BaseActivity
import ru.cyber_eagle_owl.homework1.clean.data.entities.presentation.PhotoPresentationEntity
import ru.cyber_eagle_owl.homework1.clean.presentation.features.itemDetailsScreen.PhotoDetailsActivity
import ru.cyber_eagle_owl.homework1.constants.AppConstants
import timber.log.Timber
import javax.inject.Inject

class MainListingScreenActivity : BaseActivity(), MainListingScreenMvp.View,
    MainListingScreenRecyclerViewAdapter.OnItemClickListener {

    @Inject
    lateinit var presenter: MainListingScreenMvp.Presenter

    private lateinit var photosAdapter: MainListingScreenRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_listing_screen)
        setSupportActionBar(toolbar)

        prepareRecyclerView()
        presenter.onViewCreated()
    }

    private fun prepareRecyclerView() {
        Timber.d("0=(====> prepareRecyclerView()")

        photosAdapter = MainListingScreenRecyclerViewAdapter(this)
        main_recycler_view.layoutManager = LinearLayoutManager(this)
        main_recycler_view.adapter = photosAdapter
        (main_recycler_view.adapter as MainListingScreenRecyclerViewAdapter).notifyDataSetChanged()
    }

    override fun showListOfPhotos(photos: ArrayList<PhotoPresentationEntity>) {
        Timber.d("0=(====> showListOfPhotos")

        photosAdapter.photos = photos
        photosAdapter.notifyDataSetChanged()
    }

    override fun onItemClick(photoId: Int) {
        Timber.d("onItemClick(photoId: Int) and photoId is: $photoId")
        val intent = Intent(this, PhotoDetailsActivity::class.java)
        intent.putExtra(AppConstants.PHOTO_ID_KEY, photoId)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main_listing_screen, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_exit -> {
                Timber.d("Menu item Exit was picked.")
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        presenter.onActivityDestroyed()
        super.onDestroy()
    }
}
