package org.wit.booking.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import kotlinx.android.synthetic.main.activity_booking_list.*
import org.jetbrains.anko.intentFor
import org.wit.booking.R
import org.wit.booking.main.MainApp
import org.jetbrains.anko.startActivityForResult
import org.wit.booking.models.BookingModel

class BookingListActivity : AppCompatActivity(), BookingListener {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_list)
        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        //recyclerView.adapter = BookingAdapter(app.bookings)
        //recyclerView.adapter = BookingAdapter(app.bookings.findAll(),this)
        loadBookings()

        toolbarMain.title = title
        setSupportActionBar(toolbarMain)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_add -> startActivityForResult<BookingActivity>(0)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBookingClick(booking: BookingModel) {
        startActivityForResult(intentFor<BookingActivity>().putExtra("booking_edit", booking), 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loadBookings()
        //recyclerView.adapter?.notifyDataSetChanged()
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun loadBookings() {
        showBookings(app.bookings.findAll())
    }

    fun showBookings (bookings: List<BookingModel>) {
        recyclerView.adapter = BookingAdapter(bookings, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }
}

