package ru.job4j.exam;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ru.job4j.exam.store.ExamBaseHelper;
import ru.job4j.exam.store.ExamDbSchema;

/**
 * Класс ExamEditActivity - activity для редактирования заявки
 * @author Ilya Osipov (mailto:il.osipov.ya@yandex.ru)
 * @since 27.05.2019
 * @since $Id$
 */

public class ExamEditActivity extends AppCompatActivity {

    private SQLiteDatabase store;

    @Override
    protected void onCreate(@Nullable Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.exam_update);

        this.store = new ExamBaseHelper(this.getApplicationContext()).getWritableDatabase();
        int position = getIntent().getIntExtra("position", 0);
        EditText edit = findViewById(R.id.title);
        Button save = findViewById(R.id.save);
        save.setOnClickListener(
                (v) -> {
                    ContentValues values = new ContentValues();
                    values.put(ExamDbSchema.ExamTable.Cols.TITLE, edit.getText().toString());
                    Cursor cursor = this.store.query(
                            ExamDbSchema.ExamTable.NAME,
                            null, null, null,
                            null, null, null
                    );
                    cursor.moveToFirst();
                    cursor.moveToPosition(position);
                    String title = cursor.getString(cursor.getColumnIndex("title"));
                    store.update(ExamDbSchema.ExamTable.NAME, values, "TITLE = ?",
                            new String[] {title});
                    cursor.close();
                    startActivity(new Intent(ExamEditActivity.this, ExamsActivity.class));
                    Toast.makeText(this, R.string.positive_edit_click,
                            Toast.LENGTH_SHORT).show();
                });
    }
}
