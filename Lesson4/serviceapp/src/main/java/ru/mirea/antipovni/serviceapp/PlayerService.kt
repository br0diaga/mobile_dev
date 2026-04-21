package ru.mirea.antipovni.serviceapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

/**
 * Сервис для воспроизведения музыки в фоновом режиме
 * Наследуется от Service
 */
class PlayerService : Service() {

    private var mediaPlayer: MediaPlayer? = null

    // ID канала уведомлений (требуется для Android 8.0+)
    private val CHANNEL_ID = "MusicPlayerChannel"

    // ID уведомления
    private val NOTIFICATION_ID = 1

    /**
     * Вызывается при создании сервиса
     * Здесь создаем канал уведомлений и инициализируем MediaPlayer
     */
    override fun onCreate() {
        super.onCreate()

        // Создаем канал уведомлений для Android 8.0+
        createNotificationChannel()

        // Инициализируем MediaPlayer с аудиофайлом из res/raw/music.mp3
        mediaPlayer = MediaPlayer.create(this, R.raw.music).apply {
            isLooping = false  // Не повторяем композицию
        }
    }

    /**
     * Вызывается при запуске сервиса через startForegroundService()
     * @param intent Intent, переданный при запуске
     * @param flags флаги
     * @param startId уникальный идентификатор запуска
     * @return START_NOT_STICKY - не перезапускать сервис при завершении
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Начинаем воспроизведение
        mediaPlayer?.start()

        // Устанавливаем слушатель окончания воспроизведения
        mediaPlayer?.setOnCompletionListener {
            // Останавливаем foreground сервис и удаляем уведомление
            stopForeground(STOP_FOREGROUND_REMOVE)
            // Останавливаем сервис
            stopSelf()
        }

        // Запускаем сервис как Foreground Service с уведомлением
        val notification = buildNotification()
        startForeground(NOTIFICATION_ID, notification)

        // Возвращаем START_NOT_STICKY:
        // - если система убьет сервис, она НЕ будет его перезапускать автоматически
        // - подходит для задач, которые выполняются один раз
        return START_NOT_STICKY
    }

    /**
     * Вызывается при остановке сервиса
     * Освобождаем ресурсы
     */
    override fun onDestroy() {
        super.onDestroy()

        // Останавливаем воспроизведение
        mediaPlayer?.stop()
        // Освобождаем ресурсы MediaPlayer
        mediaPlayer?.release()
        mediaPlayer = null

        // Удаляем уведомление
        stopForeground(STOP_FOREGROUND_REMOVE)
    }

    /**
     * Возвращает null, т.к. это не привязанный сервис
     * (не используется bindService)
     */
    override fun onBind(intent: Intent?): IBinder? = null

    /**
     * Создает канал уведомлений (требуется для Android 8.0+)
     */
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Music Player Channel"
            val descriptionText = "Channel for music player notifications"
            val importance = NotificationManager.IMPORTANCE_LOW
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }

            // Регистрируем канал в системе
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    /**
     * Строит уведомление для Foreground Service
     */
    private fun buildNotification(): android.app.Notification {
        // Создаем PendingIntent для открытия MainActivity при клике на уведомление
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        }
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Строим уведомление
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("🎵 Музыкальный плеер")
            .setContentText("Воспроизведение: Трек студента Антипова")  // ВАШЕ название!
            .setSmallIcon(android.R.drawable.ic_media_play)
            .setContentIntent(pendingIntent)
            .setOngoing(true)  // Уведомление нельзя смахнуть
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()
    }
}