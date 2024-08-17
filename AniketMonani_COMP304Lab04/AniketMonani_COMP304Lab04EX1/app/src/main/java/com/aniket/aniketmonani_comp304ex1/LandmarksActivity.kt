package com.aniket.aniketmonani_comp304ex1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aniket.aniketmonani_comp304ex1.viewmodels.LandmarkViewModel

class LandmarksActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: LandmarkViewModel
    private lateinit var landmarkAdapter: LandmarkAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landmarks)

        viewModel = ViewModelProvider(this)[LandmarkViewModel::class.java]
        recyclerView = findViewById(R.id.recyclerViewLandmarks)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize the adapter
        landmarkAdapter = LandmarkAdapter(emptyList()) { selectedLandmark ->
            Log.d("LandmarksActivity", "Landmark selected: ${selectedLandmark.name}")
            val intent = Intent(this, DetailLandmarkActivity::class.java).apply {
                putExtra("EXTRA_LANDMARK", selectedLandmark)
            }
            startActivity(intent)
        }

        recyclerView.adapter = landmarkAdapter

        // Retrieve the landmark type from the Intent extras and observe the LiveData from ViewModel
        intent.getStringExtra(EXTRA_LANDMARK_TYPE)?.let { typeName ->
            viewModel.getLandmarksByType(typeName).observe(this, { landmarks ->
                landmarkAdapter.updateData(landmarks)
            })
        }
    }

    companion object {
        const val EXTRA_LANDMARK_TYPE = "EXTRA_LANDMARK_TYPE"
    }
}