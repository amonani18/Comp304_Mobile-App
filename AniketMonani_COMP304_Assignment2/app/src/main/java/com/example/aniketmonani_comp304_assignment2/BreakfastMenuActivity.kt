package com.example.aniketmonani_comp304_assignment2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class BreakfastMenuActivity : AppCompatActivity() {

    private lateinit var bagelCheckBox: CheckBox
    private lateinit var muffinCheckBox: CheckBox
    private lateinit var addToCartButton: Button
    private lateinit var checkoutButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_breakfast_type)

        // Initialize views
        bagelCheckBox = findViewById(R.id.bagel_checkbox)
        muffinCheckBox = findViewById(R.id.muffin_checkbox)
        addToCartButton = findViewById(R.id.add_to_cart_button)
        checkoutButton = findViewById(R.id.checkout_button)

        // Set click listeners for checkboxes
        bagelCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                showToast("Bagel selected")
            } else {
                showToast("Bagel deselected")
            }
        }

        muffinCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                showToast("Muffin selected")
            } else {
                showToast("Muffin deselected")
            }
        }

        addToCartButton.setOnClickListener {
            saveSelectedItems()
        }

        checkoutButton.setOnClickListener {
            startActivity(Intent(this, CheckoutActivity::class.java))
        }
    }

    // Function to show a toast message
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun saveSelectedItems() {
        val sharedPreferences = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val selectedItems = sharedPreferences.getStringSet("SelectedItems", mutableSetOf())?.toMutableSet() ?: mutableSetOf()

        if (bagelCheckBox.isChecked) selectedItems.add("Bagel")
        if (muffinCheckBox.isChecked) selectedItems.add("Muffin")

        editor.putStringSet("SelectedItems", selectedItems)
        editor.apply()
        showToast("Items added to cart")
    }
}
