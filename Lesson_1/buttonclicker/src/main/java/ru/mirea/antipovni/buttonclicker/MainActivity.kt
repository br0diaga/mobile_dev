package ru.mirea.antipovni.buttonclicker


import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvOut: TextView
    private lateinit var btnWhoAmI: Button
    private lateinit var checkBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Инициализация
        tvOut = findViewById(R.id.tvOut)
        btnWhoAmI = findViewById(R.id.btnWhoAmI)
        checkBox = findViewById(R.id.checkBox)

        // Способ 1: Лямбда для WhoAmI
        btnWhoAmI.setOnClickListener {
            tvOut.text = "Мой номер по списку № 5"
            checkBox.isChecked = true
        }
    }

    // Способ 2: Метод для XML onClick (ItIsNotMe)
    fun onItIsNotMeClick(view: android.view.View) {
        tvOut.text = "Это не я сделал!"
        checkBox.isChecked = false
        Toast.makeText(this, "Ещё один способ!", Toast.LENGTH_SHORT).show()
    }
}
