package ru.mirea.antipovni.toastapp

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object {
        private const val STUDENT_NAME = "Антипов Н.И."
        private const val GROUP = "БСБО-50-24"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickCountCharacters(view: View) {
        val editText = findViewById<EditText>(R.id.editTextInput)
        val text = editText.text.toString()
        val charCount = text.length

        val message = "$STUDENT_NAME ГРУППА $GROUP Количество символов - $charCount"


        Toast.makeText(
            applicationContext,
            message,
            Toast.LENGTH_LONG
        ).show()
    }

    fun onClickCustomToast(view: View) {
        val editText = findViewById<EditText>(R.id.editTextInput)
        val text = editText.text.toString()
        val charCount = text.length

        val message = "Символов: $charCount"

        val toast = Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }
}