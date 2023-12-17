package com.example.bmta_palka_kodytek

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.bmta_palka_kodytek.recycler_view.CarListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var buttonCalculate: Button
    private lateinit var buttonCars: Button
    private lateinit var buttonExit: Button
    private lateinit var carListFragment: CarListFragment
    private var btnBackToMain: Button? = null

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

        btnBackToMain = carListFragment.view?.findViewById(R.id.btnBackToMain)
        btnBackToMain?.setOnClickListener {
            supportFragmentManager.popBackStack()
            onOptionsItemSelected(findViewById(android.R.id.home))
        }

        buttonCalculate.setOnClickListener {
        }

        buttonCars.setOnClickListener {
            showButtons(false)

            supportFragmentManager.beginTransaction()
                .replace(R.id.container, carListFragment)
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
                    showButtons(false)

                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, carListFragment)
                        .addToBackStack(null)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                } else {
                    showButtons(true)
                    supportFragmentManager.popBackStack()
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun showButtons(switch: Boolean) {
        if (switch) {
            buttonCalculate.visibility = Button.VISIBLE
            buttonCars.visibility = Button.VISIBLE
            buttonExit.visibility = Button.VISIBLE
        } else {
            buttonCalculate.visibility = Button.GONE
            buttonCars.visibility = Button.GONE
            buttonExit.visibility = Button.GONE
        }

    }

}