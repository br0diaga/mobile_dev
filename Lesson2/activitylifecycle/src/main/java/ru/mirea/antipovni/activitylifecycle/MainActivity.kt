package ru.mirea.antipovni.activitylifecycle

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "ActivityLifecycle"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(TAG, "onCreate() вызван")

        // Восстанавливаем текст, если есть сохраненное состояние
        if (savedInstanceState != null) {
            val editText = findViewById<EditText>(R.id.editTextInput)
            val savedText = savedInstanceState.getString("saved_text")
            editText.setText(savedText)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart() вызван")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume() вызван")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause() вызван")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop() вызван")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(TAG, "onRestart() вызван")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy() вызван")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i(TAG, "onSaveInstanceState() вызван")

        // Сохраняем текст из EditText
        val editText = findViewById<EditText>(R.id.editTextInput)
        val currentText = editText.text.toString()
        outState.putString("saved_text", currentText)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i(TAG, "onRestoreInstanceState() вызван")

        // Восстанавливаем текст
        val savedText = savedInstanceState.getString("saved_text")
        if (savedText != null) {
            val editText = findViewById<EditText>(R.id.editTextInput)
            editText.setText(savedText)
        }
    }
}