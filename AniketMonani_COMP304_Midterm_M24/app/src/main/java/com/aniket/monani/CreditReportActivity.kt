package com.aniket.monani
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class CreditReportActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credit_report)

        val name = intent.getStringExtra("name")
        val address = intent.getStringExtra("address")
        val sin = intent.getStringExtra("sin")
        val income = intent.getStringExtra("income")
        val dob = intent.getStringExtra("dob")
        val creditScore = intent.getStringExtra("creditScore")
        val inquiryType = intent.getStringExtra("inquiryType")

        val greetingTextView: TextView = findViewById(R.id.tv_greeting)
        val addressTextView: TextView = findViewById(R.id.tv_address)
        val sinTextView: TextView = findViewById(R.id.tv_sin)
        val incomeTextView: TextView = findViewById(R.id.tv_income)
        val ageTextView: TextView = findViewById(R.id.tv_age)
        val creditScoreStatusTextView: TextView = findViewById(R.id.tv_credit_score_status)
        val inquiryTypeTextView: TextView = findViewById(R.id.tv_inquiry_type)
        val eligibilityTextView: TextView = findViewById(R.id.tv_eligibility)
        val creditScoreChartImageView: ImageView = findViewById(R.id.iv_credit_score_chart)

        greetingTextView.text = "Hello $name!"
        addressTextView.text = "Address: $address"
        sinTextView.text = "SIN Number: $sin"
        incomeTextView.text = "Annual Income: $$income"
        ageTextView.text = "Age: ${calculateAge(dob)}"

        val creditScoreStatus = when (creditScore) {
            "800 to 850" -> "Excellent Credit Score"
            "740 to 799" -> "Very Good Credit Score"
            "670 to 739" -> "Good Credit Score"
            "580 to 669" -> "Fair Credit Score"
            else -> "Poor Credit Score"
        }
        creditScoreStatusTextView.text = "Credit Score Status: $creditScore $creditScoreStatus"
        inquiryTypeTextView.text = "Type of Inquiry: $inquiryType"

        val eligibilityMessage = if (creditScore in listOf("800 to 850", "740 to 799", "670 to 739")) {
            "You are eligible to apply for a loan!"
        } else {
            "You need to build a good credit score for a loan."
        }
        eligibilityTextView.text = eligibilityMessage

        // Load the image from resources
        creditScoreChartImageView.setImageResource(R.drawable.creditscore)
    }

    private fun calculateAge(dob: String?): Int {
        val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.US)
        val birthDate = dateFormat.parse(dob)
        val today = Calendar.getInstance()
        val birthDay = Calendar.getInstance()
        if (birthDate != null) {
            birthDay.time = birthDate
        }

        var age = today.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR)
        if (today.get(Calendar.DAY_OF_YEAR) < birthDay.get(Calendar.DAY_OF_YEAR)) {
            age--
        }
        return age
    }
}
