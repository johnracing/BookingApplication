package org.wit.booking.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.wit.booking.helpers.*
import java.util.*

val JSON_FILE = "bookings.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<BookingModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class BookingJSONStore : BookingStore, AnkoLogger {

    val context: Context
    var bookings = mutableListOf<BookingModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<BookingModel> {
        return bookings
    }

    override fun create(booking: BookingModel) {
        booking.id = generateRandomId()
        bookings.add(booking)
        serialize()
    }


    override fun update(booking: BookingModel) {
        val bookingsList = findAll() as ArrayList<BookingModel>
        var foundBooking: BookingModel? = bookingsList.find { p -> p.id == booking.id }
        if (foundBooking != null) {
            foundBooking.title = booking.title
            foundBooking.company = booking.company
            foundBooking.contact = booking.contact
            foundBooking.image = booking.image
            foundBooking.lat = booking.lat
            foundBooking.lng = booking.lng
            foundBooking.zoom = booking.zoom
        }
        serialize()
    }

    override fun delete(booking: BookingModel) {
        bookings.remove(booking)
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(bookings, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        bookings = Gson().fromJson(jsonString, listType)
    }
}
