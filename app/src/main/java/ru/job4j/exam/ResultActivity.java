package ru.job4j.exam;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Класс ResultActivity - результаты прохождения теста
 * @author Ilya Osipov (mailto:il.osipov.ya@yandex.ru)
 * @since 29.03.2019
 * @version $Id$
 */

public class ResultActivity extends AppCompatActivity {

    public ResultActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.result_activity);
    }
}
