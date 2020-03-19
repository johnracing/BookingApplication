package org.wit.booking.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.booking.models.BookingMemStore
import org.wit.booking.models.BookingJSONStore
import org.wit.booking.models.BookingStore

class MainApp : Application(), AnkoLogger {

    //val bookings = ArrayList<BookingModel>()
    lateinit var bookings: BookingStore

    override fun onCreate() {
        super.onCreate()
        bookings = BookingJSONStore(applicationContext)
        info("Booking started")
    }
}
