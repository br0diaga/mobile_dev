package ru.mirea.antipovni.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.DialogFragment

class MyProgressDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.dialog_progress, null)

        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        val textMessage = view.findViewById<TextView>(R.id.textMessage)

        textMessage.text = "Пожалуйста, подождите..."

        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle("Загрузка")
        builder.setView(view)
        builder.setCancelable(false)

        val dialog = builder.create()

        Handler(Looper.getMainLooper()).postDelayed({
            if (isAdded) {
                dialog.dismiss()
            }
        }, 3000)

        return dialog
    }
}