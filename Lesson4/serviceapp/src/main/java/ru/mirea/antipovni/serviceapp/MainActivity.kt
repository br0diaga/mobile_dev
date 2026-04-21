package ru.mirea.antipovni.serviceapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import ru.mirea.antipovni.serviceapp.databinding.ActivityMainBinding

/**
 * MainActivity для управления музыкальным сервисом
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val PERMISSION_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Проверяем и запрашиваем разрешения
        checkPermissions()

        // Настраиваем обработчики кнопок
        setupButtons()
    }

    /**
     * Проверяет наличие разрешений и запрашивает их при необходимости
     * Для Android 13+ требуется POST_NOTIFICATIONS
     */
    private fun checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // Android 13+ (API 33+)
            if (ContextCompat.checkSelfPermission(
                    this, Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // Запрашиваем разрешения
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        Manifest.permission.POST_NOTIFICATIONS,
                        Manifest.permission.FOREGROUND_SERVICE
                    ),
                    PERMISSION_CODE
                )
                Log.d("MainActivity", "Запрошены разрешения")
            } else {
                Log.d("MainActivity", "Разрешения уже получены")
            }
        }
    }

    /**
     * Настраивает обработчики нажатий на кнопки
     */
    private fun setupButtons() {
        // Кнопка "Воспроизвести"
        binding.buttonPlay.setOnClickListener {
            val intent = Intent(this, PlayerService::class.java)

            // Для Android 8.0+ нужно использовать startForegroundService
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                ContextCompat.startForegroundService(this, intent)
            } else {
                startService(intent)
            }

            Log.d("MainActivity", "Сервис запущен")
        }

        // Кнопка "Остановить"
        binding.buttonStop.setOnClickListener {
            stopService(Intent(this, PlayerService::class.java))
            Log.d("MainActivity", "Сервис остановлен")
        }
    }

    /**
     * Обрабатывает результат запроса разрешений
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                Log.d("MainActivity", "Все разрешения получены")
            } else {
                Log.d("MainActivity", "Разрешения не получены")
            }
        }
    }
}