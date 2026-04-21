package ru.mirea.antipovni.looper

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ru.mirea.antipovni.looper.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var myLooper: MyLooper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Создаем Handler для главного потока
        val mainThreadHandler = Handler(Looper.getMainLooper()) { msg ->
            Log.d("MainActivity", "Task execute. Result: ${msg.data.getString("result")}")
            binding.textViewMirea.text = msg.data.getString("result")
            true
        }

        // Создаем и запускаем наш Looper
        myLooper = MyLooper(mainThreadHandler)
        myLooper.start()

        // Обработчик кнопки
        binding.buttonMirea.setOnClickListener {
            val inputText = binding.editTextMirea.text.toString()

            if (inputText.isNotEmpty()) {
                // Проверяем, что workerHandler инициализирован (не null)
                if (myLooper.workerHandler != null) {
                    val msg = Message.obtain()
                    val bundle = Bundle()
                    bundle.putString("KEY", inputText)
                    msg.data = bundle

                    myLooper.workerHandler?.sendMessage(msg)
                } else {
                    // Handler еще не инициализирован
                    Toast.makeText(this, "Поток еще не готов. Подождите момент.", Toast.LENGTH_SHORT).show()
                    Log.w("MainActivity", "WorkerHandler еще не инициализирован")
                }
            }
        }
    }
}