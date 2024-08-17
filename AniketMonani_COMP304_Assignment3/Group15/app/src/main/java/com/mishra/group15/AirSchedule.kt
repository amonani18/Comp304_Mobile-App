package com.mishra.group15

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room

class AirSchedule : AppCompatActivity() {

    // Declare the database and adapter as late-initialized variables
    lateinit var database: AirLinesDatabase
    lateinit var adapter: FlightInfoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_air_schedule) // Set the content view to the XML layout file

        // Initialize the Room database
        database = Room.databaseBuilder(
            applicationContext,
            AirLinesDatabase::class.java, "database-name"
        ).build()

        // Clear the database in a background thread
        Thread {
            database.AirLinesDAO().apply {
                deleteAll() // Clear all existing entries in the database
            }
        }.start()

        // Insert sample data into the database
        insertSampleData()

        // Set up the RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this) // Set the layout manager

        // Observe the data from the database and update the RecyclerView adapter
        database.AirLinesDAO().getAirLines().observe(this, Observer { flightList ->
            // Check if the flightList is not null
            flightList?.let {
                // Initialize the adapter with the flight list
                adapter = FlightInfoAdapter(it)
                recyclerView.adapter = adapter // Set the adapter to the RecyclerView

                // Set item click listener
                adapter.setOnItemClickListener(object : FlightInfoAdapter.OnItemClickListener {
                    override fun onItemClick(position: Int) {
                        // Create an intent to start the DetailedSchedule activity
                        val intent = Intent(this@AirSchedule, DetailedSchedule::class.java)
                        intent.putExtra("airlineName", it[position].airlineName) // Pass the airline name to the new activity
                        startActivity(intent) // Start the new activity
                    }
                })
            }
        })
    }

    // Insert sample data into the database
    private fun insertSampleData() {
        Thread {
            database.AirLinesDAO().apply {
                insertAirLine("Qatar Airways", "5:15 PM", "T 3", false)
                insertAirLine("Singapore Airlines", "8:45 AM", "T 1", true)
                insertAirLine("British Airways", "4:45 PM", "T 2", false)
                insertAirLine("Cathay Pacific", "1:00 PM", "T 3", true)
                insertAirLine("British Airways", "2:30 PM", "T 2", false)
                insertAirLine("Cathay Pacific", "12:45 PM", "T 3", true)
                insertAirLine("ANA (All Nippon Airways)", "8:15 AM", "T 1", false)
                insertAirLine("KLM Royal Dutch Airlines", "10:00 AM", "T 2", true)
                insertAirLine("Turkish Airlines", "4:45 PM", "T 3", false)
                insertAirLine("Delta Air Lines", "11:30 AM", "T 1", true)
                insertAirLine("American Airlines", "2:00 PM", "T 2", false)
                insertAirLine("United Airlines", "6:30 AM", "T 1", true)
                insertAirLine("Southwest Airlines", "3:15 PM", "T 2", false)
                insertAirLine("JetBlue Airways", "6:45 PM", "T 1", true)
                insertAirLine("ANA (All Nippon Airways)", "7:30 AM", "T 1", true)
                insertAirLine("KLM Royal Dutch Airlines", "9:30 AM", "T 2", false)
                insertAirLine("Delta Air Lines", "9:00 AM", "T 1", true)
                insertAirLine("American Airlines", "12:00 PM", "T 2", false)
                insertAirLine("United Airlines", "6:00 AM", "T 1", true)
                insertAirLine("Southwest Airlines", "3:00 PM", "T 2", false)
                insertAirLine("JetBlue Airways", "6:00 PM", "T 1", true)
                insertAirLine("Air Canada", "10:30 AM", "T 1", true)
                insertAirLine("Emirates", "2:30 PM", "T 3", false)
                insertAirLine("Delta Air Lines", "10:45 AM", "T 1", false)
                insertAirLine("American Airlines", "3:45 PM", "T 2", true)
                insertAirLine("United Airlines", "7:00 AM", "T 1", false)
                insertAirLine("Southwest Airlines", "4:30 PM", "T 2", true)
                insertAirLine("JetBlue Airways", "7:45 PM", "T 1", false)
                insertAirLine("Air Canada", "11:15 AM", "T 1", false)
                insertAirLine("Emirates", "1:45 PM", "T 3", true)
                insertAirLine("Lufthansa", "10:30 AM", "T 2", false)
                insertAirLine("Qatar Airways", "6:30 PM", "T 3", true)
                insertAirLine("Singapore Airlines", "9:15 AM", "T 1", false)
                insertAirLine("Air France", "4:00 PM", "T 2", false)
                insertAirLine("Qantas Airways", "12:30 PM", "T 1", true)
                insertAirLine("Etihad Airways", "11:00 AM", "T 3", false)
                insertAirLine("Lufthansa", "11:45 AM", "T 2", true)
                insertAirLine("Turkish Airlines", "3:45 PM", "T 3", true)
            }
        }.start()
    }
}
