package ru.job4j.exam;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import ru.job4j.exam.store.ExamBaseHelper;
import ru.job4j.exam.store.ExamDbSchema;

/**
 * Класс ExamUpdateActivity - activity для создания заявки
 * @author Ilya Osipov (mailto:il.osipov.ya@yandex.ru)
 * @since 13.05.2019
 * @version $Id$
 */

public class ExamUpdateActivity extends AppCompatActivity {
    private SQLiteDatabase store;

    @Override
    protected void onCreate(@Nullable Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.exam_update);
        this.store = new ExamBaseHelper(this.getApplicationContext()).getWritableDatabase();
        final EditText edit = findViewById(R.id.title);
        Button save = findViewById(R.id.save);
        save.setOnClickListener(
                (v) -> {
                    ContentValues value = new ContentValues();
                    value.put(ExamDbSchema.ExamTable.Cols.TITLE, edit.getText().toString());
                    store.insert(ExamDbSchema.ExamTable.NAME, null, value);
                    startActivity(new Intent(ExamUpdateActivity.this,
                            ExamsActivity.class));
                }
        );
    }
}
