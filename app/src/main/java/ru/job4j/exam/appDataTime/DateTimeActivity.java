package ru.job4j.exam.appDataTime;

import android.app.DatePickerDialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

import ru.job4j.exam.R;

/**
 * Класс DateTimeActivity - вывод выбраной даты и время
 * @author Ilya Osipov (mailto:il.osipov.ya@yandex.ru)
 * @since 16.04.2019
 * @version $Id$
 */

public class DateTimeActivity extends AppCompatActivity {
    private Button dataTime;
    private TextView text;
    private Calendar dateAndTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time);

        dataTime = findViewById(R.id.button_data_time);
        dataTime.setOnClickListener(this::onClickButtonDataTime);
        text = findViewById(R.id.data_time);
    }


    public void onClickButtonDataTime(View view) {
        DialogFragment dialog = new DateDialogFragment();
        dialog.show(getSupportFragmentManager(), "dialog_date_tag");
    }
}
