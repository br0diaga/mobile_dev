package ru.mirea.antipovni.intentfilter

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickOpenBrowser(view: android.view.View) {
        // Неявный вызов для открытия браузера
        val url = "https://www.mirea.ru"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(this, "Нет приложения для открытия ссылки", Toast.LENGTH_SHORT).show()
        }
    }

    fun onClickShare(view: android.view.View) {
        // Неявный вызов для отправки текста
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "МИРЭА")
            putExtra(Intent.EXTRA_TEXT, "Антипов Н.И. - студент РТУ МИРЭА")
        }

        startActivity(Intent.createChooser(shareIntent, "Поделиться ФИО"))
    }
}