package ru.job4j.exam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ExamActivity extends AppCompatActivity {
    private static final String TAG = "ExamActivity";
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
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
        Log.d(TAG, "onRestoreInstanceState");
        Log.d(TAG, "Count = " + ++count);
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
}
