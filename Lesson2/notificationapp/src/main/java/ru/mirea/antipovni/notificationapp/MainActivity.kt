package ru.mirea.antipovni.notificationapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "NotificationApp"
        private const val CHANNEL_ID = "mirea_notification_channel"
        private const val CHANNEL_NAME = "МИРЭА Уведомления"
        private const val CHANNEL_DESCRIPTION = "Канал для уведомлений приложения МИРЭА"
        private const val NOTIFICATION_ID = 1001
        private const val NOTIFICATION_ID_CHANNEL = 1002
        private const val PERMISSION_CODE = 200
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Проверка и запрос разрешения на уведомления
        checkAndRequestNotificationPermission()

        // Создание канала уведомлений для Android 8+
        createNotificationChannel()
    }

    private fun checkAndRequestNotificationPermission() {
        // Для Android 13+ (API 33+) нужно запрашивать разрешение
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                Log.d(TAG, "Разрешения получены")
            } else {
                Log.d(TAG, "Нет разрешений! Запрашиваем...")
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                    PERMISSION_CODE
                )
            }
        }
    }

    private fun createNotificationChannel() {
        // Создаём канал только для Android 8+ (API 26+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = CHANNEL_DESCRIPTION
                enableLights(true)
                enableVibration(true)
            }

            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
            Log.d(TAG, "Канал уведомлений создан: $CHANNEL_ID")
        }
    }

    fun onClickSendNotification(view: android.view.View?) {
        // Проверяем разрешение перед отправкой
        if (!checkNotificationPermission()) {
            Toast.makeText(this, "Нет разрешения на отправку уведомлений", Toast.LENGTH_SHORT).show()
            checkAndRequestNotificationPermission()
            return
        }

        sendSimpleNotification()
    }

    fun onClickSendWithChannel(view: android.view.View?) {
        if (!checkNotificationPermission()) {
            Toast.makeText(this, "Нет разрешения на отправку уведомлений", Toast.LENGTH_SHORT).show()
            return
        }

        sendChannelNotification()
    }

    private fun checkNotificationPermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            // Для версий ниже Android 13 разрешение не требуется
            true
        }
    }

    private fun sendSimpleNotification() {
        try {
            val builder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("РТУ МИРЭА")
                .setContentText("Привет, студент! Это простое уведомление.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)

            with(NotificationManagerCompat.from(this)) {
                notify(NOTIFICATION_ID, builder.build())
                Log.d(TAG, "Уведомление отправлено с ID: $NOTIFICATION_ID")
                Toast.makeText(this@MainActivity, "Уведомление отправлено!", Toast.LENGTH_SHORT).show()
            }
        } catch (e: SecurityException) {
            Log.e(TAG, "Ошибка отправки уведомления: ${e.message}")
            Toast.makeText(this, "Ошибка: нет разрешения", Toast.LENGTH_SHORT).show()
        }
    }

    private fun sendChannelNotification() {
        try {
            val builder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("РТУ МИРЭА")
                .setContentText("Уведомление с каналом! Студент: Антипов Н.И., Группа: ИКБО-01-24")
                .setStyle(NotificationCompat.BigTextStyle()
                    .bigText("Подробная информация: Это уведомление использует канал, созданный специально для приложения МИРЭА. Студент выполнил практическую работу по уведомлениям."))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)

            with(NotificationManagerCompat.from(this)) {
                notify(NOTIFICATION_ID_CHANNEL, builder.build())
                Log.d(TAG, "Уведомление с каналом отправлено с ID: $NOTIFICATION_ID_CHANNEL")
                Toast.makeText(this@MainActivity, "Уведомление с каналом отправлено!", Toast.LENGTH_SHORT).show()
            }
        } catch (e: SecurityException) {
            Log.e(TAG, "Ошибка отправки уведомления: ${e.message}")
            Toast.makeText(this, "Ошибка: нет разрешения", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "Разрешение получено")
                Toast.makeText(this, "Разрешение получено, можно отправлять уведомления", Toast.LENGTH_SHORT).show()
            } else {
                Log.d(TAG, "Разрешение отклонено")
                Toast.makeText(this, "Разрешение не получено. Уведомления не будут работать", Toast.LENGTH_LONG).show()
            }
        }
    }
}