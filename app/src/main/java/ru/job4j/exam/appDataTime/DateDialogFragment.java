package ru.job4j.exam.appDataTime;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Класс DateDialogFragment - фрагмент контейнер для DatePickerDialog
 * @author Ilya Osipov (mailto:il.osipov.ya@yandex.ru)
 * @since 16.04.2019
 * @version $Id$
 */

public class DateDialogFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
    }
}
