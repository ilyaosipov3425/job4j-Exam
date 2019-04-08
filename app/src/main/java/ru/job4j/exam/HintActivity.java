package ru.job4j.exam;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс HintActivity - ответы на вопросы
 * @author Ilya Osipov (mailto:il.osipov.ya@yandex.ru)
 * @since 28.03.2019
 * @version $Id$
 */

public class HintActivity extends AppCompatActivity {
    private final Map<Integer, String> answers = new HashMap<>();
    private final Map<Integer, String> ansQuestions = new HashMap<>();

    public HintActivity() {
        this.ansQuestions.put(0, "How many primitive variables does Java have?");
        this.ansQuestions.put(1, "What is Java Virtual Machine?");
        this.ansQuestions.put(2, "What is happen if we try unboxing null?");
        this.answers.put(0, "4");
        this.answers.put(1, "4");
        this.answers.put(2, "4");
    }

    @Override
    protected void onCreate(@Nullable Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.hint_activity);
        TextView text = findViewById(R.id.hint);
        TextView ansText = findViewById(R.id.answer);
        int question = getIntent().getIntExtra(ExamActivity.HINT_FOR, 0);
        int answer = getIntent().getIntExtra(ExamActivity.ANSWER_FOR, 4);
        text.setText(this.answers.get(question));
        ansText.setText(this.ansQuestions.get(answer));

        Button back = findViewById(R.id.back);
        back.setOnClickListener(
                (v) -> onBackPressed()
        );
    }
}
