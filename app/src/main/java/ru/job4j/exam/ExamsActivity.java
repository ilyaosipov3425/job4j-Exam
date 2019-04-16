package ru.job4j.exam;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Класс ExamsActivity
 * @author Ilya Osipov (mailto:il.osipov.ya@yandex.ru)
 * @since 15.04.2019
 * @version $Id$
 */

public class ExamsActivity extends AppCompatActivity {
    private RecyclerView recycler;

    @Override
    protected void onCreate(@Nullable Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.exams);
        this.recycler = findViewById(R.id.exams);
        this.recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
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
                                Toast.LENGTH_SHORT
                        ).show();
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

    /**
     * Метод генерирует список экзаменов и загружает их в адаптер
     */
    private void updateUI() {
        List<Exam> exams = new ArrayList<Exam>();
        for (int index = 0; index != 100; index++) {
            exams.add(new Exam(index, String.format("Exam %s", index), System.currentTimeMillis(), index));
        }
        this.recycler.setAdapter(new ExamAdapter(exams));
    }
}
