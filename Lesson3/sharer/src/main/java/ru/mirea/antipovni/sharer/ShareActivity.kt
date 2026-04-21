package ru.mirea.antipovni.sharer

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ShareActivity : AppCompatActivity() {

    private lateinit var textViewReceivedData: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share)

        textViewReceivedData = findViewById(R.id.textViewReceivedData)

        // Получение данных из Intent, который запустил эту Activity
        val receivedText = intent?.getStringExtra(Intent.EXTRA_TEXT)

        if (receivedText != null) {
            textViewReceivedData.text = "Полученный текст: $receivedText"
        } else {
            textViewReceivedData.text = "Данные не получены"
        }
    }
}