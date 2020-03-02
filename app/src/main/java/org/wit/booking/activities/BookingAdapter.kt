package org.wit.booking.activities

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.card_booking.view.*
import org.wit.booking.R
import org.wit.booking.models.BookingModel
import org.wit.booking.activities.BookingAdapter.MainHolder as MainHolder


interface BookingListener {
    fun onBookingClick(booking: BookingModel)
}

class BookingAdapter constructor(private var bookings: List<BookingModel>,
                                   private val listener: BookingListener) : RecyclerView.Adapter<BookingAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(LayoutInflater.from(parent?.context).inflate(R.layout.card_booking, parent, false))
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val booking = bookings[holder.adapterPosition]
        holder.bind(booking, listener)
    }

    override fun getItemCount(): Int = bookings.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(booking: BookingModel,  listener : BookingListener) {
            itemView.bookingTitle.text = booking.title
            itemView.bookingCompany.text = booking.company
            itemView.setOnClickListener { listener.onBookingClick(booking) }
        }
    }
}