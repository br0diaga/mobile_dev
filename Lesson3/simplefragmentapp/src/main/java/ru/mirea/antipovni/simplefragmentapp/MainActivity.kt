package ru.mirea.antipovni.simplefragmentapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {

    private lateinit var btnFirstFragment: Button
    private lateinit var btnSecondFragment: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnFirstFragment = findViewById(R.id.btnFirstFragment)
        btnSecondFragment = findViewById(R.id.btnSecondFragment)

        // Загружаем первый фрагмент по умолчанию
        if (savedInstanceState == null) {
            loadFragment(FirstFragment())
        }

        btnFirstFragment.setOnClickListener {
            loadFragment(FirstFragment())
        }

        btnSecondFragment.setOnClickListener {
            loadFragment(SecondFragment())
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView, fragment)
        transaction.commit()
    }
}