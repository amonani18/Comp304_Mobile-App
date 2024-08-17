package com.example.aniketmonani_comp304_examp2
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment

class TopFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_top, container, false)

        val listView: ListView = view.findViewById(R.id.listView)
        val activities = arrayOf(getString(R.string.ai_activity), getString(R.string.vr_activity))
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, activities)
        listView.adapter = adapter

        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val intent = when (position) {
                0 -> Intent(requireContext(), AIActivity::class.java)
                1 -> Intent(requireContext(), VRActivity::class.java)
                else -> null
            }
            intent?.let { startActivity(it) }
        }

        Toast.makeText(requireContext(), "TopFragment onCreateView", Toast.LENGTH_SHORT).show()

        return view
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(requireContext(), "TopFragment onStart", Toast.LENGTH_SHORT).show()
    }
}
