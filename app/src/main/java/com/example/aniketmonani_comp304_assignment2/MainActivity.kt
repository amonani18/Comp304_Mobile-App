package com.example.aniketmonani_comp304_assignment2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val enterButton: Button = findViewById(R.id.enterButton)
        enterButton.setOnClickListener {
            val intent = Intent(this, MenuTypeActivity::class.java)
            startActivity(intent)
        }
    }

}
