package com.example.bmta_palka_kodytek

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myButton: Button = findViewById(R.id.btn)
        val text: TextView = findViewById(R.id.textField)
        myButton.setOnClickListener {
            // Your code to handle button click goes here
            // For example, you can show a toast message
            text.text = "Ahoj"
        }
    }
}