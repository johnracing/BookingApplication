package org.wit.booking.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize

data class BookingModel(var id: Long = 0,
                        var title: String = "",
                        var company: String = "",
                        var contact: String = "",
                        var image: String = ""
): Parcelable

@Parcelize
data class Location(var lat: Double = 0.0,
                    var lng: Double = 0.0,
                    var zoom: Float = 0f
) : Parcelable