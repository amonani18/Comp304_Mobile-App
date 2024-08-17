package com.aniket.aniketmonani_comp304ex1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aniket.aniketmonani_comp304ex1.database.LandmarkEntity

class LandmarkAdapter(
    private var landmarks: List<LandmarkEntity>,
    private val onClick: (LandmarkEntity) -> Unit
) : RecyclerView.Adapter<LandmarkAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.textViewLandmarkName)
        val addressTextView: TextView = view.findViewById(R.id.textViewLandmarkAddress)
        val typeTextView: TextView = view.findViewById(R.id.textViewLandmarkType)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.landmark_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val landmark = landmarks[position]
        with(holder) {
            nameTextView.text = landmark.name
            addressTextView.text = landmark.address
            typeTextView.text = landmark.type
            itemView.setOnClickListener { onClick(landmark) }
        }
    }

    override fun getItemCount() = landmarks.size

    fun updateData(newLandmarks: List<LandmarkEntity>) {
        landmarks = newLandmarks
        notifyDataSetChanged()
    }
}
