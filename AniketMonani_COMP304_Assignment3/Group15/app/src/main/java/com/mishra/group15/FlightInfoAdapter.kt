package com.mishra.group15

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Adapter class for the flight information
class FlightInfoAdapter(private val flightList: List<AirLinesDB>) :
    RecyclerView.Adapter<FlightInfoAdapter.FlightInfoViewHolder>() {

    // Interface for click listener
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    private var listener: OnItemClickListener? = null

    // Setter method for the click listener
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    // Creates and returns a ViewHolder object, inflating the view layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightInfoViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.air_flight_schedule_view, parent, false)
        return FlightInfoViewHolder(itemView)
    }

    // Binds data to the ViewHolder at the specified position
    override fun onBindViewHolder(holder: FlightInfoViewHolder, position: Int) {
        val currentItem = flightList[position]

        // Set the text for each TextView in the ViewHolder
        holder.airlineNameTextView.text = currentItem.airlineName
        holder.arrivalTimeTextView.text = currentItem.arrivalTime
        holder.terminalNumberTextView.text = currentItem.terminalNumber

        // Set the click listener for the item view
        holder.itemView.setOnClickListener {
            listener?.onItemClick(position)
        }
    }

    // Returns the total number of items in the data set
    override fun getItemCount(): Int = flightList.size

    // ViewHolder class to hold references to each item view's components
    class FlightInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val airlineNameTextView: TextView = itemView.findViewById(R.id.airlineName)
        val arrivalTimeTextView: TextView = itemView.findViewById(R.id.arrivalTime)
        val terminalNumberTextView: TextView = itemView.findViewById(R.id.terminalNumber)
    }
}
