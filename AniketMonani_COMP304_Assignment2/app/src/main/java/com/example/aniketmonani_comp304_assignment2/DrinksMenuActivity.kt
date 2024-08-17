package com.example.aniketmonani_comp304_assignment2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DrinksMenuActivity : AppCompatActivity() {

    private lateinit var drinksCheckBox: CheckBox
    private lateinit var milkshakeCheckBox: CheckBox
    private lateinit var addToCartButton: Button
    private lateinit var checkoutButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_drinks_type)

        // Initialize views
        drinksCheckBox = findViewById(R.id.drinks_checkbox)
        milkshakeCheckBox = findViewById(R.id.milkshake_checkbox)
        addToCartButton = findViewById(R.id.add_to_cart_button)
        checkoutButton = findViewById(R.id.checkout_button)

        // Set click listeners for checkboxes
        drinksCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                showToast("drinks selected")
            } else {
                showToast("drinks deselected")
            }
        }

        milkshakeCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                showToast("milkshake selected")
            } else {
                showToast("milkshake deselected")
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
        val selectedItems = sharedPreferences.getStringSet("SelectedItems", mutableSetOf())?.toMutableSet() ?: mutableSetOf() //change

        if (drinksCheckBox.isChecked) selectedItems.add("drinks")
        if (milkshakeCheckBox.isChecked) selectedItems.add("milkshake")

        editor.putStringSet("SelectedItems", selectedItems)
        editor.apply()
        showToast("Items added to cart")
    }
}
