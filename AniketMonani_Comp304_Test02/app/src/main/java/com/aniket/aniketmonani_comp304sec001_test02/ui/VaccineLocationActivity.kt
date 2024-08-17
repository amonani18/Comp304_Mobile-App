package com.aniket.aniketmonani_comp304sec001_test02.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aniket.aniketmonani_comp304sec001_test02.adapter.PharmacyAdapter
import com.aniket.aniketmonani_comp304sec001_test02.databinding.ActivityVaccineLocationBinding
import com.aniket.aniketmonani_comp304sec001_test02.viewmodel.VaccineViewModel

class VaccineLocationActivity : AppCompatActivity() {

    private lateinit var viewModel: VaccineViewModel
    private lateinit var binding: ActivityVaccineLocationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            binding = ActivityVaccineLocationBinding.inflate(layoutInflater)
            setContentView(binding.root)
            viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))
                .get(VaccineViewModel::class.java)

            setupRecyclerView()
        } catch (e: Exception) {
            Log.e("VaccineLocationActivity", "Failed to initialize ViewModel", e)
            Toast.makeText(this, "Failed to initialize ViewModel: ${e.message}", Toast.LENGTH_LONG).show()
            finish()  // Close activity if it cannot function without ViewModel
        }
    }

    private fun setupRecyclerView() {
        val adapter = PharmacyAdapter(emptyList()) { pharmacy ->
            val intent = Intent(this, VaccineDetailsActivity::class.java)
            intent.putExtra("PHARMACY_NAME", pharmacy.name)
            startActivity(intent)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        viewModel.allPharmacies.observe(this, { pharmacies ->
            adapter.updateList(pharmacies)
        })
    }
}

