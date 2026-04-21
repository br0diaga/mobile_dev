package ru.mirea.antipovni.systemintentsapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var btnCall: Button
    private lateinit var btnBrowser: Button
    private lateinit var btnMaps: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCall = findViewById(R.id.btnCall)
        btnBrowser = findViewById(R.id.btnBrowser)
        btnMaps = findViewById(R.id.btnMaps)

        btnCall.setOnClickListener {
            onClickCall()
        }

        btnBrowser.setOnClickListener {
            onClickOpenBrowser()
        }

        btnMaps.setOnClickListener {
            onClickMaps()
        }
    }

    private fun onClickCall() {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:+78005553535")
        startActivity(intent)
    }

    private fun onClickOpenBrowser() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("http://developer.android.com")
        startActivity(intent)
    }

    private fun onClickMaps() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("geo:55.749479,37.613944")
        startActivity(intent)
    }
}