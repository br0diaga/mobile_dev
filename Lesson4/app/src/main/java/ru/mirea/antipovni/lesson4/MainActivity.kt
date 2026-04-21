package ru.mirea.antipovni.lesson4

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import ru.mirea.antipovni.lesson4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // переменная для ViewBinding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Инициализация binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // текст в EditText
        binding.editTextMirea.setText("Мой номер по списку № 5")

        // обработчик нажатия на кнопку
        binding.buttonMirea.setOnClickListener {
            Log.d("MainActivity", "Кнопка нажата!")
            binding.textViewMirea.text = "Кнопка была нажата!"
        }
    }
}