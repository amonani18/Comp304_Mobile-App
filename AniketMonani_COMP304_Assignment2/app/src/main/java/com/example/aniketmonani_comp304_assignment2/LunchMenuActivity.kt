package com.example.aniketmonani_comp304_assignment2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LunchMenuActivity : AppCompatActivity() {

    private lateinit var wrapCheckBox: CheckBox
    private lateinit var burritoCheckBox: CheckBox
    private lateinit var addToCartButton: Button
    private lateinit var checkoutButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_lunch_type)

        // Initialize views
        wrapCheckBox = findViewById(R.id.wrap_checkbox)
        burritoCheckBox = findViewById(R.id.burrito_checkbox)
        addToCartButton = findViewById(R.id.add_to_cart_button)
        checkoutButton = findViewById(R.id.checkout_button)

        // Set click listeners for checkboxes
        wrapCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                showToast("wrap selected")
            } else {
                showToast("wrap deselected")
            }
        }

        burritoCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                showToast("burrito selected")
            } else {
                showToast("burrito deselected")
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

        if (wrapCheckBox.isChecked) selectedItems.add("wrap")
        if (burritoCheckBox.isChecked) selectedItems.add("burrito")

        editor.putStringSet("SelectedItems", selectedItems)
        editor.apply()
        showToast("Items added to cart")
    }
}
