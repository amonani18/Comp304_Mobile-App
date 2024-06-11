package com.example.aniketmonani_comp304_assignment2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DinnerMenuActivity : AppCompatActivity() {

    private lateinit var burgersCheckBox: CheckBox
    private lateinit var beanCheckBox: CheckBox
    private lateinit var addToCartButton: Button
    private lateinit var checkoutButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_dinner_type)

        // Initialize views
        burgersCheckBox = findViewById(R.id.burgers_checkbox)
        beanCheckBox = findViewById(R.id.bean_checkbox)
        addToCartButton = findViewById(R.id.add_to_cart_button)
        checkoutButton = findViewById(R.id.checkout_button)

        // Set click listeners for checkboxes
        burgersCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                showToast("burgers selected")
            } else {
                showToast("burgers deselected")
            }
        }

        beanCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                showToast("bean selected")
            } else {
                showToast("bean deselected")
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

        if (burgersCheckBox.isChecked) selectedItems.add("burgers")
        if (beanCheckBox.isChecked) selectedItems.add("bean")

        editor.putStringSet("SelectedItems", selectedItems)
        editor.apply()
        showToast("Items added to cart")
    }
}
