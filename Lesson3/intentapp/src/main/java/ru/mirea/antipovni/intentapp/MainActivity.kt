package ru.mirea.antipovni.intentapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var textViewTime: TextView
    private lateinit var buttonSendTime: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewTime = findViewById(R.id.textViewTime)
        buttonSendTime = findViewById(R.id.buttonSendTime)

        // Получаем текущее системное время
        val dateInMillis = System.currentTimeMillis()
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val dateString = sdf.format(Date(dateInMillis))

        // Отображаем время в TextView
        textViewTime.text = "Текущее время: $dateString"

        // Обработка нажатия кнопки
        buttonSendTime.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("current_time", dateString)
            intent.putExtra("student_number", 5) // Ваш номер в группе
            startActivity(intent)
        }
    }
}