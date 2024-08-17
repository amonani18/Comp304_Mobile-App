package com.example.aniketmonani_comp304_assignment2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.CompoundButtonCompat
import android.content.res.ColorStateList
import android.graphics.Color

class CheckoutActivity : AppCompatActivity() {

    private lateinit var itemContainer: LinearLayout
    private lateinit var proceedToPaymentButton: Button
    private lateinit var totalPriceTextView: TextView

    // Prices of the items
    private val itemPrices = mapOf(
        "Bagel" to 2.00,
        "Muffin" to 1.00,
        "wrap" to 6.00,
        "burrito" to 5.00,
        "burgers" to 4.00,
        "bean" to 6.00,
        "drinks" to 2.00,
        "milkshake" to 5.00,
        "icecream" to 2.50,
        "pastry" to 3.50
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        itemContainer = findViewById(R.id.item_container)
        proceedToPaymentButton = findViewById(R.id.proceed_to_payment)
        totalPriceTextView = findViewById(R.id.total_price)

        loadSelectedItems()

        proceedToPaymentButton.setOnClickListener {
            // Navigate to the payment page
            val intent = Intent(this, PaymentActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loadSelectedItems() {
        val sharedPreferences = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)
        val selectedItems = sharedPreferences.getStringSet("SelectedItems", setOf())
        var totalPrice = 0.0

        // Define the color you want for the CheckBox tint and text
        val checkboxColor = resources.getColor(R.color.black, theme)
        val textColor = Color.BLACK

        selectedItems?.forEach { item ->
            val checkBox = CheckBox(this)
            checkBox.text = "$item - $${itemPrices[item]}"

            // Set the button tint color
            CompoundButtonCompat.setButtonTintList(checkBox, ColorStateList.valueOf(checkboxColor))

            // Set the text color
            checkBox.setTextColor(textColor)

            itemContainer.addView(checkBox)
            totalPrice += itemPrices[item] ?: 0.0
        }

        totalPriceTextView.text = "Total Price: $${String.format("%.2f", totalPrice)}"
    }
}
