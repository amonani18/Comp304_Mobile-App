package com.example.aniketmonani_comp304_examp2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_top, TopFragment())
                .replace(R.id.fragment_container_bottom, BottomFragment())
                .commit()
        }
    }
}
