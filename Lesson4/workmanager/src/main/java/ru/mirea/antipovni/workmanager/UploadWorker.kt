package ru.mirea.antipovni.lesson4

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker.Result
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay

/**
 * Фоновая задача, выполняемая через WorkManager
 * Наследуется от CoroutineWorker для удобной работы с корутинами
 */
class UploadWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        Log.d("UploadWorker", "Задача запущена")

        return try {
            // Имитация длительной операции (3 секунды)
            // Используем delay() вместо Thread.sleep(), так как мы в корутине
            delay(3000)

            Log.d("UploadWorker", "Задача успешно завершена")
            // Явно указываем полный путь, чтобы избежать конфликта с kotlin.Result
            Result.success()
        } catch (e: Exception) {
            Log.e("UploadWorker", "Ошибка выполнения: ${e.message}")
            Result.failure()
        }
    }
}