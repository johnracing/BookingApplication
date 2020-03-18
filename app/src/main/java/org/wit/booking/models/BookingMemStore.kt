package org.wit.booking.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class BookingMemStore : BookingStore, AnkoLogger {

    val bookings = ArrayList<BookingModel>()

    override fun findAll(): List<BookingModel> {
        return bookings
    }


    override fun create(booking: BookingModel) {
        booking.id=getId()
        bookings.add(booking)
        logAll()
    }

    override fun update(booking: BookingModel) {
        var foundBooking: BookingModel? = bookings.find { p -> p.id == booking.id }
        if (foundBooking != null) {
            foundBooking.title = booking.title
            foundBooking.company = booking.company
            foundBooking.contact = booking.contact
            foundBooking.image = booking.image
            logAll()
        }
    }

    fun logAll() {
        bookings.forEach{ info("${it}") }
    }
}