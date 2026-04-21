package ru.mirea.antipovni.cryptoloader

import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

object CryptoUtils {

    /**
     * Генерирует ключ шифрования AES
     * Использует SHA1PRNG для генерации случайного seed
     */
    fun generateKey(): SecretKey {
        try {
            // Создаем генератор случайных чисел с фиксированным seed для воспроизводимости
            val sr = SecureRandom.getInstance("SHA1PRNG")
            sr.setSeed("MireaSeed2025".toByteArray())

            // Создаем генератор ключей AES
            val kg = KeyGenerator.getInstance("AES")
            kg.init(256, sr) // 256-битный ключ

            // Возвращаем сгенерированный ключ
            return SecretKeySpec(kg.generateKey().encoded, "AES")
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    /**
     * Шифрует сообщение с помощью AES
     */
    fun encryptMsg(message: String, secret: SecretKey): ByteArray {
        try {
            val cipher = Cipher.getInstance("AES")
            cipher.init(Cipher.ENCRYPT_MODE, secret)
            return cipher.doFinal(message.toByteArray())
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    /**
     * Расшифровывает сообщение с помощью AES
     */
    fun decryptMsg(cipherText: ByteArray, secret: SecretKey): String {
        try {
            val cipher = Cipher.getInstance("AES")
            cipher.init(Cipher.DECRYPT_MODE, secret)
            return String(cipher.doFinal(cipherText))
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}