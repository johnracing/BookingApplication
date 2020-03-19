package org.wit.booking.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_booking.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import org.wit.booking.models.BookingModel
import org.wit.booking.R
import org.wit.booking.helpers.readImage
import org.wit.booking.helpers.readImageFromPath
import org.wit.booking.helpers.showImagePicker
import org.wit.booking.main.MainApp
import org.wit.booking.models.Location

class BookingActivity : AppCompatActivity(), AnkoLogger {

    var edit=false;
    var booking = BookingModel()
    lateinit var app: MainApp
    val IMAGE_REQUEST = 1
    val LOCATION_REQUEST = 2
    // var location = Location(52.245696, -7.139102, 15f)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)
        app = application as MainApp


        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)

        if (intent.hasExtra("booking_edit")) {
            edit = true
            booking = intent.extras.getParcelable<BookingModel>("booking_edit")
            bookingTitle.setText(booking.title)
            bookingCompany.setText(booking.company)
            bookingContact.setText(booking.contact)
            bookingMentor.setText(booking.mentor)
            bookingNotes.setText(booking.notes)
            bookingTime.setText(booking.time)
            bookingDate.setText(booking.date)

            bookingImage.setImageBitmap(readImageFromPath(this, booking.image))

            if (booking.image != null) {
                chooseImage.setText(R.string.change_booking_image)
            }
            btnAdd.setText(R.string.save_booking)
        }

        bookingLocation.setOnClickListener {
            val location = Location(52.245696, -7.139102, 15f)
            if (booking.zoom != 0f) {
                location.lat =  booking.lat
                location.lng = booking.lng
                location.zoom = booking.zoom
            }
            startActivityForResult(intentFor<MapsActivity>().putExtra("location", location), LOCATION_REQUEST)
            info ("Set Location Pressed")
        }

        btnAdd.setOnClickListener() {
            booking.title = bookingTitle.text.toString()
            booking.company = bookingCompany.text.toString()
            booking.contact = bookingContact.text.toString()
            booking.mentor = bookingMentor.text.toString()
            booking.notes = bookingNotes.text.toString()

            booking.date = bookingDate.text.toString()
            booking.time = bookingTime.text.toString()

            if (booking.title.isEmpty()) {
                toast(R.string.enter_booking_title)
            } else {
                if (edit){
                  app.bookings.update(booking.copy())
                }
                else {
                    app.bookings.create(booking.copy())
                }
                setResult(AppCompatActivity.RESULT_OK)
                finish()
            }
        }



        chooseImage.setOnClickListener {
            showImagePicker(this, IMAGE_REQUEST)
            info("Select image")
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_booking, menu)
        if (edit && menu != null) menu.getItem(0).setVisible(true)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_delete -> {
                app.bookings.delete(booking)
                info("deleting booking")
                finish()
            }
            R.id.item_cancel -> {
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IMAGE_REQUEST -> {
                if (data != null) {
                    booking.image = data.getData().toString()
                    bookingImage.setImageBitmap(readImage(this, resultCode, data))
                    chooseImage.setText(R.string.change_booking_image)
                }
            }
            LOCATION_REQUEST -> {
                if (data != null) {
                    val location = data.extras.getParcelable<Location>("location")
                    booking.lat = location.lat
                    booking.lng = location.lng
                    booking.zoom = location.zoom
                }
            }
        }
    }


}
