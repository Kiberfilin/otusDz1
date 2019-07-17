package ru.cyber_eagle_owl.homework1.clean.presentation.features.itemDetailsScreen

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_photo_details.*
import kotlinx.android.synthetic.main.main_recycler_view_item.view.*
import ru.cyber_eagle_owl.homework1.R
import ru.cyber_eagle_owl.homework1.base.BaseActivity
import ru.cyber_eagle_owl.homework1.clean.data.entities.presentation.PhotoDetailsPresentationEntity
import ru.cyber_eagle_owl.homework1.constants.AppConstants
import timber.log.Timber
import javax.inject.Inject

class PhotoDetailsActivity : BaseActivity(), PhotoDetailsMvp.View {

    @Inject
    lateinit var presenter: PhotoDetailsMvp.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_details)
        prepareToolbar()

        presenter.onPhotoIdReceived(intent.getIntExtra(AppConstants.PHOTO_ID_KEY, -1))
    }

    private fun prepareToolbar() {
        setSupportActionBar(photo_details_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun showPhotoInfo(photo: PhotoDetailsPresentationEntity) {

        Picasso.get().load(photo.photoUrl).into(photo_details_image_view)
        photo_id.text = photo.id.toString()
        photo_title.text = photo.photoTitle
        Timber.d("photo.albumTitle = ${photo.albumTitle}")
        photo_album.text = photo.albumTitle
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {

            android.R.id.home -> {
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
