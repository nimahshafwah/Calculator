package com.example.calculator

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etNumber1: EditText = findViewById(R.id.etNumber1)
        val etNumber2: EditText = findViewById(R.id.etNumber2)
        val radioGroupOperations: RadioGroup = findViewById(R.id.radioGroupOperations)
        val btnHitung: Button = findViewById(R.id.btnHitung)

        btnHitung.setOnClickListener {
            val num1 = etNumber1.text.toString().toDoubleOrNull()
            val num2 = etNumber2.text.toString().toDoubleOrNull()

            if (num1 == null || num2 == null) {
                Toast.makeText(this, "Masukkan angka yang valid", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selectedOperation = radioGroupOperations.checkedRadioButtonId
            val result = when (selectedOperation) {
                R.id.radioAdd -> num1 + num2
                R.id.radioSubtract -> num1 - num2
                R.id.radioMultiply -> num1 * num2
                R.id.radioDivide -> {
                    if (num2 == 0.0) {
                        Toast.makeText(this, "Tidak bisa membagi dengan nol", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    } else {
                        num1 / num2
                    }
                }
                else -> null
            }

            result?.let {
                // Pindah ke halaman kedua
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("result", it)
                startActivity(intent)
            }
        }
    }
}
