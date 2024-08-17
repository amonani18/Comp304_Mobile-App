package com.example.aniketmonani_comp304_examp1
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DisplayMessageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_message)

        // Get the message from the intent
        val message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE)

        // Capture the layout's TextView and set the string as its text
        val textViewMessage = findViewById<TextView>(R.id.textViewMessage).apply {
            text = message
        }
    }
}
