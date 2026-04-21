package ru.mirea.antipovni.multiactivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
        const val EXTRA_MESSAGE = "ru.mirea.antipovni.multiactivity.MESSAGE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(TAG, "MainActivity onCreate")
    }

    fun onClickNewActivity(view: android.view.View) {
        Log.i(TAG, "Запуск SecondActivity через явный Intent")
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }

    fun onClickSendData(view: android.view.View) {
        val editText = findViewById<EditText>(R.id.editTextInput)
        val message = editText.text.toString()

        if (message.isNotEmpty()) {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra(EXTRA_MESSAGE, message)
            startActivity(intent)
            Log.i(TAG, "Отправлено сообщение: $message")
        } else {
            Toast.makeText(this, "Введите текст", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "MainActivity onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "MainActivity onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "MainActivity onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "MainActivity onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "MainActivity onDestroy")
    }
}