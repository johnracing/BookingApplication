package org.wit.booking.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.booking.models.BookingMemStore
import org.wit.booking.models.BookingModel

class MainApp : Application(), AnkoLogger {

    //val bookings = ArrayList<BookingModel>()
    val bookings = BookingMemStore()

    override fun onCreate() {
        super.onCreate()
        info("Booking started")
    }
}
