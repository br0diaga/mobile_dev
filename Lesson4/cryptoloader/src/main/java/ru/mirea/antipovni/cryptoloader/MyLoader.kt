package ru.mirea.antipovni.cryptoloader

import android.content.Context
import android.os.Bundle
import androidx.loader.content.AsyncTaskLoader
import javax.crypto.spec.SecretKeySpec

/**
 * Загрузчик для расшифровки данных в фоновом потоке
 * Наследуется от AsyncTaskLoader, который автоматически создает поток
 */
class MyLoader(context: Context, args: Bundle?) : AsyncTaskLoader<String>(context) {

    companion object {
        const val ARG_CIPHER = "cipher"  // Ключ для зашифрованных данных
        const val ARG_KEY = "key"        // Ключ для ключа шифрования
    }

    private var cipherData: ByteArray? = null
    private var keyData: ByteArray? = null

    init {
        // Извлекаем данные из Bundle
        cipherData = args?.getByteArray(ARG_CIPHER)
        keyData = args?.getByteArray(ARG_KEY)
    }

    /**
     * Вызывается при старте загрузки
     * forceLoad() принудительно запускает loadInBackground()
     */
    override fun onStartLoading() {
        super.onStartLoading()
        forceLoad()
    }

    /**
     * Выполняется в фоновом потоке
     * Здесь происходит расшифровка данных
     */
    override fun loadInBackground(): String {
        // Восстанавливаем ключ из байтового массива
        val originalKey = keyData?.let {
            SecretKeySpec(it, 0, it.size, "AES")
        } ?: throw IllegalArgumentException("Key is null")

        // Расшифровываем данные
        return cipherData?.let {
            CryptoUtils.decryptMsg(it, originalKey)
        } ?: "Error: No data"
    }
}