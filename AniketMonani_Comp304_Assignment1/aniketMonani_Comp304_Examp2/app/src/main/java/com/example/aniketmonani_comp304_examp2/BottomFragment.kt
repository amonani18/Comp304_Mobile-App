package com.example.aniketmonani_comp304_examp2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class BottomFragment : Fragment() {

    private lateinit var lifecycleTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bottom, container, false)
        lifecycleTextView = view.findViewById(R.id.lifecycleTextView)
        lifecycleTextView.text = "onCreate\n"

        return view
    }

    override fun onStart() {
        super.onStart()
        lifecycleTextView.append("onStart\n")
    }

    override fun onResume() {
        super.onResume()
        lifecycleTextView.append("onResume\n")
    }

    override fun onPause() {
        super.onPause()
        lifecycleTextView.append("onPause\n")
    }

    override fun onStop() {
        super.onStop()
        lifecycleTextView.append("onStop\n")
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycleTextView.append("onDestroy\n")
    }
}
