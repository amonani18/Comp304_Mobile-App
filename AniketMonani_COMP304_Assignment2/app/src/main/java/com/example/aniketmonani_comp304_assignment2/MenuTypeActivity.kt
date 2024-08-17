package com.example.aniketmonani_comp304_assignment2

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

class MenuTypeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_types)
    }

    // Function to show a toast message

    // Override onCreateOptionsMenu to inflate the options menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_types, menu)
        return true
    }

    // Override onOptionsItemSelected to handle menu item clicks
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.breakfast -> {
                startActivity(Intent(this, BreakfastMenuActivity::class.java))
                return true
            }
            R.id.lunch -> {
                startActivity(Intent(this, LunchMenuActivity::class.java))
                return true
            }
            R.id.dinner -> {
                startActivity(Intent(this, DinnerMenuActivity::class.java))
                return true
            }
            R.id.drinks -> {
                startActivity(Intent(this, DrinksMenuActivity::class.java))
                return true
            }
            R.id.dessert -> {
                startActivity(Intent(this, DessertMenuActivity::class.java))
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
