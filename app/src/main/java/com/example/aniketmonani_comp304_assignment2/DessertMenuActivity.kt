package com.example.aniketmonani_comp304_assignment2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DessertMenuActivity : AppCompatActivity() {

    private lateinit var icecreamCheckBox: CheckBox
    private lateinit var pastryCheckBox: CheckBox
    private lateinit var addToCartButton: Button
    private lateinit var checkoutButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_dessert_type)

        // Initialize views
        icecreamCheckBox = findViewById(R.id.icecream_checkbox)
        pastryCheckBox = findViewById(R.id.pastry_checkbox)
        addToCartButton = findViewById(R.id.add_to_cart_button)
        checkoutButton = findViewById(R.id.checkout_button)


        // Set click listeners for checkboxes
        icecreamCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                showToast("icecream selected")
            } else {
                showToast("icecream deselected")
            }
        }

        pastryCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                showToast("pastry selected")
            } else {
                showToast("pastry deselected")
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

        if (icecreamCheckBox.isChecked) selectedItems.add("icecream")
        if (pastryCheckBox.isChecked) selectedItems.add("pastry")

        editor.putStringSet("SelectedItems", selectedItems)
        editor.apply()
        showToast("Items added to cart")
    }
}
