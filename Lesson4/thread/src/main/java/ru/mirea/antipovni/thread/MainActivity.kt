package ru.mirea.antipovni.thread

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import ru.mirea.antipovni.thread.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonMirea.setOnClickListener {
            val threadNumber = counter++

            // Создаем и запускаем фоновый поток
            Thread {
                Log.d("ThreadProject", "Запущен поток №$threadNumber")

                // Имитация долгих вычислений (2 секунды)
                Thread.sleep(2000)

                // Вычисляем среднее количество пар
                val totalPairs = 88
                val days = 22
                val averagePairs = totalPairs.toDouble() / days

                val result = "Среднее количество пар в день: $averagePairs"

                // Обновляем UI ТОЛЬКО через runOnUiThread
                runOnUiThread {
                    binding.textViewMirea.text = result
                }

                Log.d("ThreadProject", "Поток №$threadNumber завершен")
            }.start()
        }
    }
}