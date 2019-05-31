package ru.job4j.exam.appDataTime;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import ru.job4j.exam.R;

/**
 * Класс DateTimeActivity - вывод выбраной даты и время
 * @author Ilya Osipov (mailto:il.osipov.ya@yandex.ru)
 * @since 16.04.2019
 * @version $Id$
 */

public class DateTimeActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener {

    private Button button;
    private TextView currentDateTime;
    private Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time);

        currentDateTime = findViewById(R.id.text_data_time);
        button = findViewById(R.id.button_data_time);
        button.setOnClickListener(this::onClick);
        setInitDateTime();
    }

    public void onClick(View view) {
        DialogFragment datePicker = new DateDialogFragment();
        datePicker.show(getSupportFragmentManager(), "date picker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        setInitDateTime();
        DialogFragment timePicker = new TimeDialogFragment();
        timePicker.show(getSupportFragmentManager(), "time picker");
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        setInitDateTime();
    }

    public void setInitDateTime() {
        currentDateTime.setText(DateUtils.formatDateTime(this,
                calendar.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE |
                        DateUtils.FORMAT_SHOW_YEAR |
                        DateUtils.FORMAT_SHOW_TIME));
    }
}
