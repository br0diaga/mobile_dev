package ru.mirea.antipovni.favoritebook

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ShareActivity : AppCompatActivity() {

    private lateinit var textViewDeveloperBook: TextView
    private lateinit var textViewDeveloperQuote: TextView
    private lateinit var editTextBookName: EditText
    private lateinit var editTextQuote: EditText
    private lateinit var buttonSendData: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share)

        textViewDeveloperBook = findViewById(R.id.textViewDeveloperBook)
        textViewDeveloperQuote = findViewById(R.id.textViewDeveloperQuote)
        editTextBookName = findViewById(R.id.editTextBookName)
        editTextQuote = findViewById(R.id.editTextQuote)
        buttonSendData = findViewById(R.id.buttonSendData)

        // Отображаем данные разработчика
        val developerBook = intent.getStringExtra("developer_book") ?: ""
        val developerQuote = intent.getStringExtra("developer_quote") ?: ""

        textViewDeveloperBook.text = "Любимая книга разработчика: $developerBook"
        textViewDeveloperQuote.text = "Цитата: $developerQuote"

        buttonSendData.setOnClickListener {
            val userBook = editTextBookName.text.toString()
            val userQuote = editTextQuote.text.toString()

            val resultIntent = Intent()
            resultIntent.putExtra(MainActivity.USER_BOOK_KEY, userBook)
            resultIntent.putExtra(MainActivity.USER_QUOTE_KEY, userQuote)

            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}