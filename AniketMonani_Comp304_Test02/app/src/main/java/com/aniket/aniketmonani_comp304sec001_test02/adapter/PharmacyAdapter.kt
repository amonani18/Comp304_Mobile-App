package com.aniket.aniketmonani_comp304sec001_test02.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aniket.aniketmonani_comp304sec001_test02.data.entities.Pharmacy
import com.aniket.aniketmonani_comp304sec001_test02.databinding.PharmacyItemBinding

class PharmacyAdapter(
    private var pharmacyList: List<Pharmacy>,
    private val onItemClick: (Pharmacy) -> Unit
) : RecyclerView.Adapter<PharmacyAdapter.PharmacyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PharmacyViewHolder {
        val binding = PharmacyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PharmacyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PharmacyViewHolder, position: Int) {
        val pharmacy = pharmacyList[position]
        holder.bind(pharmacy)
        holder.itemView.setOnClickListener { onItemClick(pharmacy) }
    }

    override fun getItemCount(): Int = pharmacyList.size
    fun updateList(newPharmacyList: List<Pharmacy>) {
        pharmacyList = newPharmacyList
        notifyDataSetChanged()
    }
    class PharmacyViewHolder(private val binding: PharmacyItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pharmacy: Pharmacy) {
            binding.pharmacyNameTextView.text = pharmacy.name
            binding.locationTextView.text = pharmacy.location
        }
    }
}
