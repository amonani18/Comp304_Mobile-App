package com.mishra.group15


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonChangeActivity: Button = findViewById(R.id.start)
        buttonChangeActivity.setOnClickListener {
            val intent = Intent(this, AirSchedule::class.java)
            startActivity(intent)
        }
    }
}