package org.wit.booking.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_booking.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.booking.models.BookingModel
import org.wit.booking.R
import org.wit.booking.main.MainApp

class BookingActivity : AppCompatActivity(), AnkoLogger {

    var booking = BookingModel()
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)
        app = application as MainApp
        var edit1 = false
        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)

        if (intent.hasExtra("booking_edit")) {
            edit1 = true
            booking = intent.extras.getParcelable<BookingModel>("booking_edit")
            bookingTitle.setText(booking.title)
            bookingCompany.setText(booking.company)
            bookingContact.setText(booking.contact)
            btnAdd.setText(R.string.save_booking)
        }

        btnAdd.setOnClickListener() {
            booking.title = bookingTitle.text.toString()
            booking.company = bookingCompany.text.toString()
            booking.contact = bookingContact.text.toString()

            if (booking.title.isEmpty()) {
                toast(R.string.enter_booking_title)
            } else {
                if (edit1){

                  app.bookings.update(booking.copy())

                }
                else {

                    app.bookings.create(booking.copy())

                }


                setResult(AppCompatActivity.RESULT_OK)
                finish()
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_booking, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
