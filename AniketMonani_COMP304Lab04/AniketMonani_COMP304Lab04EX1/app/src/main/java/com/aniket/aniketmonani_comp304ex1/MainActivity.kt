package com.aniket.aniketmonani_comp304ex1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aniket.aniketmonani_comp304ex1.database.LandmarkType

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewLandmarkTypes)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val landmarkTypes = LandmarkType.values()
        val adapter = LandmarkTypeAdapter(landmarkTypes) { landmarkType ->
            val intent = Intent(this, LandmarksActivity::class.java).apply {
                putExtra(LandmarksActivity.EXTRA_LANDMARK_TYPE, landmarkType.name)
            }
            startActivity(intent)
        }

        recyclerView.adapter = adapter
    }
}