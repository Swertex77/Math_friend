package com.example.math_friend.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.math_friend.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val numbersInput = findViewById<EditText>(R.id.numbersInput)
        val resultView = findViewById<TextView>(R.id.resultView)
        val addButton = findViewById<Button>(R.id.addButton)
        val subtractButton = findViewById<Button>(R.id.subtractButton)
        val multiplyButton = findViewById<Button>(R.id.multiplyButton)
        val divideButton = findViewById<Button>(R.id.divideButton)

        addButton.setOnClickListener {
            performOperation(numbersInput.text.toString(), resultView, "add")
        }
        subtractButton.setOnClickListener {
            performOperation(numbersInput.text.toString(), resultView, "subtract")
        }
        multiplyButton.setOnClickListener {
            performOperation(numbersInput.text.toString(), resultView, "multiply")
        }
        divideButton.setOnClickListener {
            performOperation(numbersInput.text.toString(), resultView, "divide")
        }
    }

    private fun performOperation(numbers: String, resultView: TextView, operation: String) {
        val numberList = numbers.split(",", " ").mapNotNull { it.toDoubleOrNull() }
        val result = when (operation) {
            "add" -> numberList.sum()
            "subtract" -> numberList.fold(0.0) { acc, d -> if (acc == 0.0) d else acc - d }
            "multiply" -> numberList.fold(1.0) { acc, d -> acc * d }
            "divide" -> numberList.fold(0.0) { acc, d -> if (acc == 0.0) d else if (d == 0.0) Double.MAX_VALUE else acc / d }
            else -> 0.0
        }
        resultView.text = "Результат: $result"
    }
}