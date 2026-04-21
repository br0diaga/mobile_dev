package ru.mirea.antipovni.cryptoloader

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import ru.mirea.antipovni.cryptoloader.databinding.ActivityMainBinding

/**
 * MainActivity реализует интерфейс LoaderManager.LoaderCallbacks<String>
 * для обработки результатов работы Loader
 */
class MainActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<String> {

    private lateinit var binding: ActivityMainBinding
    private val LOADER_ID = 100  // Уникальный идентификатор загрузчика

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonMirea.setOnClickListener {
            val text = binding.editTextMirea.text.toString()

            if (text.isNotEmpty()) {
                // 1. Генерируем ключ шифрования
                val key = CryptoUtils.generateKey()

                // 2. Шифруем введенный текст
                val encrypted = CryptoUtils.encryptMsg(text, key)

                // 3. Подготавливаем Bundle с данными для Loader
                val bundle = Bundle().apply {
                    putByteArray(MyLoader.ARG_CIPHER, encrypted)
                    putByteArray(MyLoader.ARG_KEY, key.encoded)
                }

                // 4. Инициализируем Loader
                // Если Loader с таким ID уже существует, он будет переиспользован
                LoaderManager.getInstance(this@MainActivity)
                    .initLoader(LOADER_ID, bundle, this@MainActivity)
            } else {
                Toast.makeText(this, "Введите текст!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * Вызывается LoaderManager для создания нового Loader
     * @param id идентификатор Loader
     * @param args аргументы, переданные при инициализации
     * @return новый экземпляр Loader
     */
    override fun onCreateLoader(id: Int, args: Bundle?): Loader<String> {
        Toast.makeText(this, "onCreateLoader: $id", Toast.LENGTH_SHORT).show()
        return MyLoader(this, args)
    }

    /**
     * Вызывается автоматически, когда Loader завершает загрузку
     * @param loader завершившийся Loader
     * @param data результат работы (расшифрованная строка)
     */
    override fun onLoadFinished(loader: Loader<String>, data: String) {
        Toast.makeText(this, "Расшифровано: $data", Toast.LENGTH_LONG).show()
    }

    /**
     * Вызывается, когда Loader сбрасывается и его данные будут удалены
     * Нужно очистить ссылки на данные
     */
    override fun onLoaderReset(loader: Loader<String>) {
        // Очистка ресурсов при необходимости
    }
}