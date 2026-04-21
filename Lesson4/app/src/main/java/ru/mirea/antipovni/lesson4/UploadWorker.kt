package ru.mirea.antipovni.lesson4

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import java.util.concurrent.TimeUnit

class UploadWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        Log.d("UploadWorker", "Задача выполняется")
        try {
            TimeUnit.SECONDS.sleep(5)
            Log.d("UploadWorker", "Задача завершена")
            return Result.success()
        } catch (e: Exception) {
            return Result.retry()
        }
    }
}