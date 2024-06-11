package com.example.aniketmonani_comp304_assignment2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class PaymentActivity : AppCompatActivity() {

    private lateinit var paymentOptionsGroup: RadioGroup
    private lateinit var cardDetailsLayout: LinearLayout
    private lateinit var cardImage: ImageView
    private lateinit var fullName: EditText
    private lateinit var address: EditText
    private lateinit var phoneNumber: EditText
    private lateinit var cardNumber: EditText
    private lateinit var expiryDate: EditText
    private lateinit var favoriteSport: EditText
    private lateinit var favoriteTeam: EditText
    private lateinit var favoriteMovie: EditText
    private lateinit var submitPaymentButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        paymentOptionsGroup = findViewById(R.id.payment_options_group)
        cardDetailsLayout = findViewById(R.id.card_details_layout)
        cardImage = findViewById(R.id.card_image)
        fullName = findViewById(R.id.full_name)
        address = findViewById(R.id.address)
        phoneNumber = findViewById(R.id.phone_number)
        cardNumber = findViewById(R.id.card_number)
        expiryDate = findViewById(R.id.expiry_date)
        favoriteSport = findViewById(R.id.favorite_sport)
        favoriteTeam = findViewById(R.id.favorite_team)
        favoriteMovie = findViewById(R.id.favorite_movie)
        submitPaymentButton = findViewById(R.id.submit_payment)

        paymentOptionsGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.payment_cash -> {
                    cardDetailsLayout.visibility = LinearLayout.GONE
                    cardImage.visibility = ImageView.GONE
                }
                R.id.payment_credit, R.id.payment_debit -> {
                    cardDetailsLayout.visibility = LinearLayout.VISIBLE
                    cardImage.visibility = ImageView.VISIBLE
                }
            }
        }

        submitPaymentButton.setOnClickListener {
            if (validateInput()) {
                saveUserData()
                Toast.makeText(this, "Payment Successful", Toast.LENGTH_SHORT).show()
                // Reset the app
                resetApp()
            }
        }
    }

    private fun resetApp() {
        // Clear any stored data
        val sharedPreferences = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()

        // Reset UI states
        fullName.text.clear()
        address.text.clear()
        phoneNumber.text.clear()
        cardNumber.text.clear()
        expiryDate.text.clear()
        favoriteSport.text.clear()
        favoriteTeam.text.clear()
        favoriteMovie.text.clear()

        // Redirect to the main activity
        startActivity(Intent(this, MainActivity::class.java))
        finishAffinity() // Finish current activity and all parent activities
    }

    private fun validateInput(): Boolean {
        if (cardDetailsLayout.visibility == LinearLayout.VISIBLE) {
            if (fullName.text.toString().isEmpty() ||
                address.text.toString().isEmpty() ||
                phoneNumber.text.toString().isEmpty() ||
                cardNumber.text.toString().isEmpty() ||
                expiryDate.text.toString().isEmpty()
            ) {
                Toast.makeText(this, "Please fill in all card details", Toast.LENGTH_SHORT).show()
                return false
            }
        }
        return true
    }

    private fun saveUserData() {
        val sharedPreferences = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString("FullName", fullName.text.toString())
        editor.putString("Address", address.text.toString())
        editor.putString("PhoneNumber", phoneNumber.text.toString())
        editor.putString("CardNumber", cardNumber.text.toString())
        editor.putString("ExpiryDate", expiryDate.text.toString())
        editor.putString("FavoriteSport", favoriteSport.text.toString())
        editor.putString("FavoriteTeam", favoriteTeam.text.toString())
        editor.putString("FavoriteMovie", favoriteMovie.text.toString())
        editor.apply()
    }
}
