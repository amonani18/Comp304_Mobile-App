package com.example.aniketmonani_comp304_examp2
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AIActivity : AppCompatActivity() {

    private lateinit var lifecycleTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle)

        lifecycleTextView = findViewById(R.id.lifecycleTextView)
        lifecycleTextView.text = "AIActivity onCreate\n"
    }

    override fun onStart() {
        super.onStart()
        lifecycleTextView.append("AIActivity onStart\n")
    }

    override fun onResume() {
        super.onResume()
        lifecycleTextView.append("AIActivity onResume\n")
    }

    override fun onPause() {
        super.onPause()
        lifecycleTextView.append("AIActivity onPause\n")
    }

    override fun onStop() {
        super.onStop()
        lifecycleTextView.append("AIActivity onStop\n")
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycleTextView.append("AIActivity onDestroy\n")
    }
}
