package org.wit.booking.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.booking.models.BookingMemStore
import org.wit.booking.models.BookingModel

class MainApp : Application(), AnkoLogger {

    val bookings = BookingMemStore()

    override fun onCreate() {
        super.onCreate()
        info("Booking started")

        //bookings.add(BookingModel("One", "About one..."))
        //bookings.add(BookingModel("Two", "About two..."))
        //bookings.add(BookingModel("Three", "About three..."))

    }
}
