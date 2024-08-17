package com.aniket.aniketmonani_comp304sec001_test02.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.aniket.aniketmonani_comp304sec001_test02.databinding.ActivityVaccineDetailsBinding
import com.aniket.aniketmonani_comp304sec001_test02.viewmodel.VaccineViewModel

class VaccineDetailsActivity : AppCompatActivity() {

    private val viewModel: VaccineViewModel by viewModels()
    private lateinit var binding: ActivityVaccineDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the binding
        binding = ActivityVaccineDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pharmacyName = intent.getStringExtra("PHARMACY_NAME")
        viewModel.getVaccineDetailsByPharmacy(pharmacyName ?: "").observe(this, { details ->
            binding.pharmacyNameTextView.text = details.pharmacyName
            binding.dateTextView.text = details.date
            binding.timeTextView.text = details.time
        })
    }
}
