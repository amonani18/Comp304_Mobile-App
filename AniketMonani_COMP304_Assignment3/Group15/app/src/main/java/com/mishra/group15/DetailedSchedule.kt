package com.mishra.group15

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room

class DetailedSchedule : AppCompatActivity() {

    // Declare the database and adapter as late-initialized variables
    lateinit var database: AirLinesDatabase
    lateinit var adapter: FlightDetailedInfoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_details) // Set the content view to the XML layout file

        // Retrieve the airline name from the intent extras
        val airlineName = intent.getStringExtra("airlineName").toString()

        // Initialize the Room database
        database = Room.databaseBuilder(
            applicationContext,
            AirLinesDatabase::class.java, "database-name"
        ).build()

        // Set up the RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this) // Set the layout manager

        // Observe the data from the database and update the RecyclerView adapter
        database.AirLinesDAO().getAirLinesWithName(airlineName).observe(this, Observer { flightList ->
            // Check if the flightList is not null
            flightList?.let {
                // Initialize the adapter with the flight list
                adapter = FlightDetailedInfoAdapter(it)
                recyclerView.adapter = adapter // Set the adapter to the RecyclerView

                // Set item click listener
                adapter.setOnItemClickListener(object : FlightDetailedInfoAdapter.OnItemClickListener {
                    override fun onItemClick(position: Int) {
                        // Create an intent to start the DetailedSchedule activity
                        val intent = Intent(this@DetailedSchedule, DetailedSchedule::class.java)
                        intent.putExtra("airlineName", it[position].airlineName) // Pass the airline name to the new activity
                        startActivity(intent) // Start the new activity
                    }
                })
            }
        })
    }
}
