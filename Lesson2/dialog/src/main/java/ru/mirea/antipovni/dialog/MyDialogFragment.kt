package ru.mirea.antipovni.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class MyDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireActivity())
            .setTitle("Здравствуй МИРЭА!")
            .setMessage("Успех близок?")
            .setIcon(android.R.drawable.ic_dialog_info)
            .setPositiveButton("Иду дальше") { dialog, _ ->
                (activity as? MainActivity)?.onOkClicked()
                dialog.dismiss()
            }
            .setNeutralButton("На паузе") { dialog, _ ->
                (activity as? MainActivity)?.onNeutralClicked()
                dialog.dismiss()
            }
            .setNegativeButton("Нет") { dialog, _ ->
                (activity as? MainActivity)?.onCancelClicked()
                dialog.dismiss()
            }
            .create()
    }
}