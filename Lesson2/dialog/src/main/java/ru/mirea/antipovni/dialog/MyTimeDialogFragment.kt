package ru.mirea.antipovni.dialog

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import java.util.Calendar

class MyTimeDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        return TimePickerDialog(
            requireActivity(),
            { _, selectedHour, selectedMinute ->
                Toast.makeText(
                    requireContext(),
                    "Выбрано время: $selectedHour:$selectedMinute",
                    Toast.LENGTH_LONG
                ).show()
            },
            hour,
            minute,
            true
        )
    }
}