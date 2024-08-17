package com.aniket.monani

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class InputActivity : AppCompatActivity() {

    private var selectedCreditScore: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameEditText: EditText = findViewById(R.id.et_name)
        val addressEditText: EditText = findViewById(R.id.et_address)
        val sinEditText: EditText = findViewById(R.id.et_sin)
        val incomeEditText: EditText = findViewById(R.id.et_income)
        val dobEditText: EditText = findViewById(R.id.et_dob)
        val creditScoreListView: ListView = findViewById(R.id.lv_credit_score)
        val inquiryTypeGroup: RadioGroup = findViewById(R.id.rg_inquiry_type)
        val getCreditScoreButton: Button = findViewById(R.id.btn_get_credit_score)

        // Setup ListView
        val creditScores = arrayOf("800 to 850", "740 to 799", "670 to 739", "580 to 669", "300 to 579")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, creditScores)
        creditScoreListView.adapter = adapter
        creditScoreListView.choiceMode = ListView.CHOICE_MODE_SINGLE

        creditScoreListView.setOnItemClickListener { _, _, position, _ ->
            selectedCreditScore = creditScores[position]
        }

        getCreditScoreButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val address = addressEditText.text.toString()
            val sin = sinEditText.text.toString()
            val income = incomeEditText.text.toString()
            val dob = dobEditText.text.toString()
            val creditScore = selectedCreditScore
            val inquiryType = when (inquiryTypeGroup.checkedRadioButtonId) {
                R.id.rb_soft -> "Soft"
                else -> "Hard"
            }

            if (creditScore == null) {
                Toast.makeText(this, "Please select a credit score", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, CreditReportActivity::class.java).apply {
                putExtra("name", name)
                putExtra("address", address)
                putExtra("sin", sin)
                putExtra("income", income)
                putExtra("dob", dob)
                putExtra("creditScore", creditScore)
                putExtra("inquiryType", inquiryType)
            }
            startActivity(intent)
        }
    }
}
