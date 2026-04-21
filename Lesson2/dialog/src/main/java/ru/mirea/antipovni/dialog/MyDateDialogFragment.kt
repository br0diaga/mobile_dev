package ru.mirea.antipovni.dialog

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import java.util.Calendar

class MyDateDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(
            requireActivity(),
            { _, selectedYear, selectedMonth, selectedDay ->
                Toast.makeText(
                    requireContext(),
                    "Выбрана дата: $selectedDay.${selectedMonth + 1}.$selectedYear",
                    Toast.LENGTH_LONG
                ).show()
            },
            year,
            month,
            day
        )
    }
}