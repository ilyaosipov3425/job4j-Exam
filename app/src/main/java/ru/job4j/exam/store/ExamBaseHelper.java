package ru.job4j.exam.store;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Класс ExamBaseHelper - помощник SQLite
 * @author Ilya Osipov (mailto:il.osipov.ya@yandex.ru)
 * @since 13.05.2019
 * @version $Id$
 */

public class ExamBaseHelper extends SQLiteOpenHelper {
    public static final String DB = "exams.db";
    public static final int VERSION = 1;

    public ExamBaseHelper(Context context) {
        super(context, DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + ExamDbSchema.ExamTable.NAME + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ExamDbSchema.ExamTable.Cols.TITLE + " " + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
