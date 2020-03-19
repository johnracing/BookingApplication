package org.wit.booking.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.sql.Time
import java.util.*

@Parcelize

data class BookingModel(var id: Long = 0,
                        var title: String = "",
                        var company: String = "",
                        var contact: String = "",
                        var mentor: String = "",
                        var notes: String = "",
                        var date: String = "",
                        var time: String = "",
                        var image: String = "",
                        var lat: Double = 0.0,
                        var lng: Double = 0.0,
                        var zoom: Float = 0f
): Parcelable

@Parcelize
data class Location(var lat: Double = 0.0,
                    var lng: Double = 0.0,
                    var zoom: Float = 0f
) : Parcelable