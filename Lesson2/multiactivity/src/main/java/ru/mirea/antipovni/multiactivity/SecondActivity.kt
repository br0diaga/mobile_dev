package ru.mirea.antipovni.multiactivity

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "SecondActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        Log.i(TAG, "SecondActivity onCreate")

        val textView = findViewById<TextView>(R.id.textViewReceived)
        val receivedMessage = intent.getStringExtra(MainActivity.EXTRA_MESSAGE)

        if (receivedMessage != null) {
            textView.text = "Получено: $receivedMessage"
            Log.i(TAG, "Получено сообщение: $receivedMessage")
        } else {
            textView.text = "Сообщение не было передано"
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "SecondActivity onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "SecondActivity onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "SecondActivity onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "SecondActivity onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "SecondActivity onDestroy")
    }
}