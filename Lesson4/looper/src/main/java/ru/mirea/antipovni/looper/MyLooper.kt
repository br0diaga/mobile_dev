package ru.mirea.antipovni.looper

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log

class MyLooper(private val mainHandler: Handler) : Thread() {

    // Делаем свойство nullable вместо lateinit
    var workerHandler: Handler? = null

    override fun run() {
        Log.d("MyLooper", "run")

        // 1. Подготавливаем Looper для этого потока
        Looper.prepare()

        // 2. Создаем Handler
        workerHandler = Handler(Looper.myLooper()!!) { msg ->
            val data = msg.data.getString("KEY") ?: ""
            Log.d("MyLooper get message:", data)

            // Считаем количество букв
            val count = data.length

            // Создаем сообщение для отправки обратно в главный поток
            val resultMsg = Message.obtain()
            val bundle = Bundle()
            bundle.putString("result", "Количество букв в слове '$data' равно $count")
            resultMsg.data = bundle

            // Отправляем сообщение в главный поток
            mainHandler.sendMessage(resultMsg)

            true
        }

        // 3. Запускаем цикл обработки сообщений
        Looper.loop()
    }
}