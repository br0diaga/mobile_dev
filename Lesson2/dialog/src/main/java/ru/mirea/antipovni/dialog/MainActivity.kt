package ru.mirea.antipovni.dialog

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickAlertDialog(view: android.view.View) {
        val dialogFragment = MyDialogFragment()
        dialogFragment.show(supportFragmentManager, "alert_dialog")
    }

    fun onClickTimeDialog(view: android.view.View) {
        val timeDialog = MyTimeDialogFragment()
        timeDialog.show(supportFragmentManager, "time_dialog")
    }

    fun onClickDateDialog(view: android.view.View) {
        val dateDialog = MyDateDialogFragment()
        dateDialog.show(supportFragmentManager, "date_dialog")
    }

    fun onClickProgressDialog(view: android.view.View) {
        val progressDialog = MyProgressDialogFragment()
        progressDialog.show(supportFragmentManager, "progress_dialog")
    }

    fun onClickSnackbar(view: android.view.View) {
        Snackbar.make(view, "Студент Антипов Н.И. Группа ИКБО-01-24", Snackbar.LENGTH_LONG)
            .setAction("OK") {
                Toast.makeText(this, "Спасибо!", Toast.LENGTH_SHORT).show()
            }
            .setActionTextColor(ContextCompat.getColor(this, android.R.color.holo_green_dark))
            .show()
    }

    fun onOkClicked() {
        Toast.makeText(
            applicationContext,
            "Вы выбрали кнопку \"Иду дальше\"!",
            Toast.LENGTH_LONG
        ).show()
    }

    fun onCancelClicked() {
        Toast.makeText(
            applicationContext,
            "Вы выбрали кнопку \"Нет\"!",
            Toast.LENGTH_LONG
        ).show()
    }

    fun onNeutralClicked() {
        Toast.makeText(
            applicationContext,
            "Вы выбрали кнопку \"На паузе\"!",
            Toast.LENGTH_LONG
        ).show()
    }
}