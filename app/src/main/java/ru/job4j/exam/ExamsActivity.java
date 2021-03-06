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

import ru.job4j.exam.dialogFragment.DeleteExamDialogFragment;
import ru.job4j.exam.dialogFragment.EditExamDialogFragment;
import ru.job4j.exam.dialogFragment.MenuDeleteDialogFragment;
import ru.job4j.exam.model.Exam;
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
    private static final String KEY_POSITION = "position";

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

        public ExamHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.info_exam, parent, false);
            return new ExamHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ExamHolder holder, int position) {
            final Exam exam = this.exams.get(position);
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
                        Toast.makeText(getApplicationContext(), "You select " + exam,
                                Toast.LENGTH_SHORT).show();
                    });

            ImageButton deleteButton = holder.view.findViewById(R.id.delete_exam);
            deleteButton.setOnClickListener(
                    (v) -> {
                        Bundle bundle = new Bundle();
                        bundle.putInt(KEY_POSITION, holder.getAdapterPosition());

                        DialogFragment dialogDelete = new DeleteExamDialogFragment();
                        dialogDelete.setArguments(bundle);
                        dialogDelete.show(getSupportFragmentManager(), "delete_exam");
                    });

            ImageButton editButton = holder.view.findViewById(R.id.edit_exam);
            editButton.setOnClickListener(
                    (v) -> {
                        Bundle bundle = new Bundle();
                        bundle.putInt(KEY_POSITION, holder.getAdapterPosition());

                        DialogFragment dialogEdit = new EditExamDialogFragment();
                        dialogEdit.setArguments(bundle);
                        dialogEdit.show(getSupportFragmentManager(), "edit_exam");
                    });
        }

        @Override
        public int getItemCount() {
            return this.exams.size();
        }
    }

    private class ExamHolder extends RecyclerView.ViewHolder {

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
        Toast.makeText(this, R.string.positive_delete_click,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNegativeDeleteClick(DialogFragment dialog) {
        Toast.makeText(this, R.string.negative_delete_click,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPositiveDeleteExamClick(DialogFragment dialog, int position) {
        Cursor cursor = this.store.query(
                ExamDbSchema.ExamTable.NAME,
                null, null, null,
                null, null, null
        );
        cursor.moveToPosition(position);
        exams.remove(position);
        this.recycler.setAdapter(new ExamAdapter(exams));
        cursor.close();
        store = new ExamBaseHelper(this.getApplicationContext()).getWritableDatabase();
        store.delete(ExamDbSchema.ExamTable.NAME, "id = " + position, null);
        //store.close();
        Toast.makeText(this, R.string.positive_exam_remove,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNegativeDeleteExamClick(DialogFragment dialog) {
        Toast.makeText(this, R.string.negative_delete_click,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPositiveEditClick(DialogFragment dialog, int position) {
        Intent intent = new Intent(this, ExamEditActivity.class);
        intent.putExtra(KEY_POSITION, position);
        startActivity(intent);
    }

    @Override
    public void onNegativeEditClick(DialogFragment dialog) {
        Toast.makeText(this, R.string.negative_exam_edit,
                Toast.LENGTH_SHORT).show();
    }
}
