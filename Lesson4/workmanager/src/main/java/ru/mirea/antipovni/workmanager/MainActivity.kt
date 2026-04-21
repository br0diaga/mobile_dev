package ru.mirea.antipovni.lesson4

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import ru.mirea.antipovni.workmanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Обработчик нажатия на кнопку
        binding.buttonMirea.setOnClickListener {
            runBackgroundTask()
        }
    }

    private fun runBackgroundTask() {
        // 1. Настраиваем условия запуска задачи
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED) // Требуется интернет
            .setRequiresBatteryNotLow(true)                // Не запускать при низком заряде
            .build()

        // 2. Создаём запрос на выполнение работы
        val workRequest = OneTimeWorkRequestBuilder<UploadWorker>()
            .setConstraints(constraints)
            .build()

        // 3. Отправляем задачу в WorkManager
        WorkManager.getInstance(this).enqueue(workRequest)

        Toast.makeText(this, "Фоновая задача поставлена в очередь", Toast.LENGTH_SHORT).show()
    }
}