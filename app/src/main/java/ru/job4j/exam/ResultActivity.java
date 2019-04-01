package ru.job4j.exam;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Класс ResultActivity - результаты прохождения теста
 * @author Ilya Osipov (mailto:il.osipov.ya@yandex.ru)
 * @since 29.03.2019
 * @version $Id$
 */

public class ResultActivity extends AppCompatActivity {

    private static final String TRUE_ANSWER = "trueAnswer";
    private TextView resultAnswer;

    @Override
    protected void onCreate(@Nullable Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.result_activity);
        resultAnswer = findViewById(R.id.resultAnswer);
        getData();
    }

    private void getData() {
        Intent intent = getIntent();
        int trueAnswer = intent.getIntExtra(TRUE_ANSWER, 0);
        resultAnswer.setText(String.format(" %s correct answers out of 3 possible.", trueAnswer));
    }

    public static Intent resultIntent(Context packageContext, int trueAnswer) {
        Intent intent = new Intent(packageContext, ResultActivity.class);
        intent.putExtra(TRUE_ANSWER, trueAnswer);
        return intent;
    }
}
