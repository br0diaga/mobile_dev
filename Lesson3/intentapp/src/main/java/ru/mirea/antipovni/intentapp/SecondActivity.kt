package ru.mirea.antipovni.intentapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    private lateinit var textViewResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        textViewResult = findViewById(R.id.textViewResult)

        // Получаем данные из Intent
        val currentTime = intent.getStringExtra("current_time") ?: "время не получено"
        val studentNumber = intent.getIntExtra("student_number", 0)

        // Вычисляем квадрат номера (5² = 25)
        val square = studentNumber * studentNumber

        // Формируем итоговую строку
        val resultText = "КВАДРАТ ЗНАЧЕНИЯ МОЕГО НОМЕРА ПО СПИСКУ В ГРУППЕ СОСТАВЛЯЕТ $square, а текущее время $currentTime"

        textViewResult.text = resultText
    }
}