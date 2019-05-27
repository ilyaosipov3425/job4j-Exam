package ru.job4j.exam;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.job4j.exam.store.ExamBaseHelper;
import ru.job4j.exam.store.ExamDbSchema;

/**
 * Класс ExamsActivity
 * @author Ilya Osipov (mailto:il.osipov.ya@yandex.ru)
 * @since 15.04.2019
 * @version $Id$
 */

public class ExamsActivity extends AppCompatActivity implements MenuDeleteDialogFragment.MenuDeleteDialogListener,
        DeleteExamDialogFragment.DeleteExamDialogListener, EditExamDialogFragment.EditExamDialogListener {
    private RecyclerView recycler;
    private SQLiteDatabase store;
    private List<Exam> exams = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.exams);
        this.recycler = findViewById(R.id.exams);
        this.recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        this.store = new ExamBaseHelper(this.getApplicationContext()).getWritableDatabase();
        updateUI();
    }

    public class ExamAdapter extends RecyclerView.Adapter<ExamHolder> {
        private final List<Exam> exams;

        public ExamAdapter(List<Exam> exams) {
            this.exams = exams;
        }

        /**
         * Метод загружает внутренний вид RecyclerView (info_exam.xml)
         */
        public ExamHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.info_exam, parent, false);
            return new ExamHolder(view);
        }

        /**
         * Метод загружает данные в наш вид
         * @param holder - вид
         * @param i - указатель элемента в списке
         */
        @Override
        public void onBindViewHolder(@NonNull ExamHolder holder, int i) {
            final Exam exam = this.exams.get(i);
            TextView text = holder.view.findViewById(R.id.infoExam);
            text.setText(exam.getName());

            TextView result = holder.resultExam;
            result.setText(String.valueOf(exam.getResult()));

            TextView date = holder.dateExam;
            String dateTimeString = DateFormat.getDateTimeInstance().format(new Date(exam.getTime()));
            date.setText(dateTimeString);

            text.setOnClickListener(
                    (v) -> {
                        text.getContext().startActivity(new Intent(text.getContext(), ExamActivity.class));
                        Toast.makeText(
                                getApplicationContext(), "You select " + exam,
                                Toast.LENGTH_SHORT).show();
                    });

            ImageButton deleteButton = holder.view.findViewById(R.id.delete_exam);
            deleteButton.setOnClickListener(
                    (v) -> {
                        DialogFragment dialogDelete = new DeleteExamDialogFragment();
                        dialogDelete.show(getSupportFragmentManager(), "delete_exam");
                    });

            ImageButton editButton = holder.view.findViewById(R.id.edit_exam);
            editButton.setOnClickListener(
                    (v) -> {
                        DialogFragment dialogEdit = new EditExamDialogFragment();
                        dialogEdit.show(getSupportFragmentManager(), "edit_exam");
                    });
        }

        /**
         * Метод указывает сколько всего элементов в списке
         */
        @Override
        public int getItemCount() {
            return this.exams.size();
        }
    }

    public class ExamHolder extends RecyclerView.ViewHolder {
        private View view;
        private TextView resultExam;
        private TextView dateExam;

        public ExamHolder(@Nullable View view) {
            super(view);
            this.view = itemView;
            this.resultExam = itemView.findViewById(R.id.resultExam);
            this.dateExam = itemView.findViewById(R.id.dateExam);
        }
    }

    private void updateUI() {
        //List<Exam> exams = new ArrayList<Exam>();
        Cursor cursor = this.store.query(
                ExamDbSchema.ExamTable.NAME,
                null, null, null,
                null, null, null
        );
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            exams.add(new Exam(
                    cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("title")),
                    System.currentTimeMillis(),
                    100
            ));
            cursor.moveToNext();
        }
        cursor.close();
        this.recycler.setAdapter(new ExamAdapter(exams));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.exams, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_exam:
                startActivity(new Intent(this, ExamUpdateActivity.class));
                return true;
            case R.id.delete_exams:
                DialogFragment dialog = new MenuDeleteDialogFragment();
                dialog.show(getSupportFragmentManager(), "dialog delete");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onPositiveDeleteClick(DialogFragment dialog) {
        //List<Exam> exams = new ArrayList<>();
        Cursor cursor = this.store.query(
                ExamDbSchema.ExamTable.NAME,
                null, null, null,
                null, null, null
        );
        cursor.moveToFirst();
        exams.removeAll(exams);
        cursor.close();
        this.recycler.setAdapter(new ExamAdapter(exams));
        store = new ExamBaseHelper(this.getApplicationContext()).getWritableDatabase();
        store.delete(ExamDbSchema.ExamTable.NAME, null, null);
        store.close();
        Toast.makeText(this, "All removed",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNegativeDeleteClick(DialogFragment dialog) {
        Toast.makeText(this, "Delete canceled",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPositiveDeleteExamClick(DialogFragment dialog) {
        //List<Exam> exams = new ArrayList<>();
        Cursor cursor = this.store.query(
                ExamDbSchema.ExamTable.NAME,
                null, null, null,
                null, null, null
        );
        cursor.moveToFirst();
        this.recycler.setAdapter(new ExamAdapter(exams));
        store = new ExamBaseHelper(this.getApplicationContext()).getWritableDatabase();
        store.delete(ExamDbSchema.ExamTable.NAME, "id = ?", null);
        cursor.close();
        store.close();
        Toast.makeText(this, "Exam removed",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNegativeDeleteExamClick(DialogFragment dialog) {
        Toast.makeText(this, "Delete canceled",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPositiveEditClick(DialogFragment dialog) {
        // метод редактирования одной заявки
        startActivity(new Intent(this, ExamEditActivity.class));
    }

    @Override
    public void onNegativeEditClick(DialogFragment dialog) {
        Toast.makeText(this, "Edit canceled",
                Toast.LENGTH_SHORT).show();
    }
}
