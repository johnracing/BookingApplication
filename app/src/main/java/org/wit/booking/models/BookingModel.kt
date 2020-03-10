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