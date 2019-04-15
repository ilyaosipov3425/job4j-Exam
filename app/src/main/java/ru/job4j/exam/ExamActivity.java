package ru.job4j.exam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Класс ExamActivity - Activity
 * @author Ilya Osipov (mailto:il.osipov.ya@yandex.ru)
 * @since 28.03.2019
 * @version $Id$
 */

public class ExamActivity extends AppCompatActivity {
    public static final String HINT_FOR = "hint for";
    public static final String ANSWER_FOR = "answer for";
    private static final String TAG = "ExamActivity";
    private List<Integer> answer = new ArrayList<>();
    private int trueCount = 0;
    private int count = 0;
    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        this.fillForm();
        final Button next = findViewById(R.id.next);
        final Button previous = findViewById(R.id.previous);
        final Button hint = findViewById(R.id.hint);
        final RadioGroup variants = findViewById(R.id.variants);

        next.setEnabled(false);
        previous.setEnabled(false);
        variants.setOnCheckedChangeListener(
                (group, checkedId) -> {
                    next.setEnabled(true);
                    previous.setEnabled(checkedId != 1 && position != 0);
                });

        next.setOnClickListener(this::nextButton);
        previous.setOnClickListener(this::previousButton);
        hint.setOnClickListener(this::hintButton);
        Log.d(TAG, "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        count = savedInstanceState.getInt("ExamActivity", count);
        position = savedInstanceState.getInt("position", position);
        fillForm();
        Log.d(TAG, "onRestoreInstanceState");
        Log.d(TAG, "Count = " + ++count);
        Log.d(TAG, "position " + position);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("ExamActivity", count);
        outState.putInt("position", position);
        Log.d(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    private final List<Question> questions = Arrays.asList(
            new Question(
                    1, "How many primitive variables does Java have?",
                    Arrays.asList(
                            new Option(1, "1.1"),
                            new Option(2, "1.2"),
                            new Option(3, "1.3"),
                            new Option(4, "1.4")
                    ), 4
            ),
            new Question(
                    2, "What is Java Virtual Machine?",
                    Arrays.asList(
                            new Option(1, "2.1"),
                            new Option(2, "2.2"),
                            new Option(3, "2.3"),
                            new Option(4, "2.4")
                    ), 4
            ),
            new Question(
                    3, "What is happen if we try unboxing null?",
                    Arrays.asList(
                            new Option(1, "3.1"),
                            new Option(2, "3.2"),
                            new Option(3, "3.3"),
                            new Option(4, "3.4")
                    ), 4
            )
    );

    public void fillForm() {
        Button previous = findViewById(R.id.previous);
        Button next = findViewById(R.id.next);
        previous.setEnabled(position != 0);
        next.setEnabled(position != questions.size() - 1);
        final TextView text = findViewById(R.id.question);
        Question question = this.questions.get(this.position);
        text.setText(question.getText());
        RadioGroup variants = findViewById(R.id.variants);
        for (int index = 0; index != variants.getChildCount(); index++) {
            RadioButton button = (RadioButton) variants.getChildAt(index);
            Option option = question.getOptions().get(index);
            button.setId(option.getId());
            button.setText(option.getText());
        }
    }

    private void showAnswer() {
        RadioGroup variants = findViewById(R.id.variants);
        int id = variants.getCheckedRadioButtonId();
        Question question = this.questions.get(this.position);
        int answer = question.getAnswer();
        Toast.makeText(
                this, "Your answer is " + id + ", correct is " + answer,
                Toast.LENGTH_SHORT
        ).show();
        if (id == answer) {
            trueCount++;
        }
    }

    private void saveAnswer() {
        RadioGroup variants = findViewById(R.id.variants);
        this.answer.add(position, variants.getCheckedRadioButtonId());
    }

    private void nextButton(View view) {
        RadioGroup variants = findViewById(R.id.variants);
        answer.add(variants.getCheckedRadioButtonId());
        showAnswer();
        saveAnswer();
        position++;
        variants.check(-1);
        if (position == questions.size()) {
            Intent intent = ResultActivity.resultIntent(ExamActivity.this, trueCount);
            startActivity(intent);
            position--;
            trueCount = 0;
        } else {
            fillForm();
        }
    }

    private void previousButton(View view) {
        position--;
        fillForm();
    }

    private void hintButton(View view) {
        Intent intent = new Intent(ExamActivity.this, HintActivity.class);
        intent.putExtra(HINT_FOR, position);
        intent.putExtra(ANSWER_FOR, position);
        startActivity(intent);
    }
}