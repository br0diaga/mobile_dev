package ru.mirea.antipovni.favoritebook

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var textViewBookInfo: TextView
    private lateinit var buttonOpenInput: Button

    companion object {
        const val USER_BOOK_KEY = "user_book"
        const val USER_QUOTE_KEY = "user_quote"
    }

    // Регистрация лаунчера для получения результата
    private val getDataLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val bookName = data?.getStringExtra(USER_BOOK_KEY) ?: ""
            val quote = data?.getStringExtra(USER_QUOTE_KEY) ?: ""
            textViewBookInfo.text = "Название Вашей любимой книги: $bookName. Цитата: $quote"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewBookInfo = findViewById(R.id.textViewBookInfo)
        buttonOpenInput = findViewById(R.id.buttonOpenInput)

        // Пример данных разработчика
        val developerBook = "Война и мир"
        val developerQuote = "Нет величия там, где нет простоты, добра и правды."

        buttonOpenInput.setOnClickListener {
            val intent = Intent(this, ShareActivity::class.java)
            intent.putExtra("developer_book", developerBook)
            intent.putExtra("developer_quote", developerQuote)
            getDataLauncher.launch(intent)
        }
    }
}