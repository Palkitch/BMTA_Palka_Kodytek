package com.example.bmta_palka_kodytek

import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {

    private lateinit var buttonCalculate: Button
    private lateinit var buttonCars: Button
    private lateinit var buttonExit: Button
    private lateinit var carListFragment: CarListFragment

    /*
    *   TODO:
    *       1) Přidat tlačítko zpět v sekci "Auta"
    *
    */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        carListFragment = CarListFragment()
        buttonCalculate = findViewById(R.id.buttonCalculate)
        buttonCars = findViewById(R.id.buttonCars)
        buttonExit = findViewById(R.id.buttonExit)

        buttonCalculate.setOnClickListener {

        }

        buttonCars.setOnClickListener {
            buttonCalculate.visibility = Button.GONE
            buttonCars.visibility = Button.GONE
            buttonExit.visibility = Button.GONE

            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CarListFragment())
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()

            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        buttonExit.setOnClickListener {
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                if (supportFragmentManager.findFragmentById(R.id.container) != carListFragment) {
                    // Skryj tlačítka
                    buttonCalculate.visibility = Button.GONE
                    buttonCars.visibility = Button.GONE
                    buttonExit.visibility = Button.GONE

                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, carListFragment)
                        .addToBackStack(null)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()

                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                } else {
                    // Zobraz tlačítka a skryj tlačítko zpět
                    buttonCalculate.visibility = Button.VISIBLE
                    buttonCars.visibility = Button.VISIBLE
                    buttonExit.visibility = Button.VISIBLE
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)

                    supportFragmentManager.popBackStack()
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
