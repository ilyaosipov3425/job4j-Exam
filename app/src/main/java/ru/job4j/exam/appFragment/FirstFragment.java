package ru.job4j.exam.appFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import ru.job4j.exam.R;

/**
 * Класс FirstFragment - первый фрагмент
 * @author Ilya Osipov (mailto:il.osipov.ya@yandex.ru)
 * @since 01.04.2019
 * @version $Id$
 */

public class FirstFragment extends Fragment {

    private Button nextFragment;
    private OnNextButtonClickListener callback;
    private TextView text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        text = view.findViewById(R.id.message_first);
        nextFragment = view.findViewById(R.id.next_fragment);
        nextFragment.setOnClickListener(this::onClick);

        return view;
    }

    public interface OnNextButtonClickListener {
        void onNextButtonClicked(String message);
    }

    public void onClick(View view) {
        callback.onNextButtonClicked("Next button clicked");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callback = (OnNextButtonClickListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle bundle = getArguments();
        if (bundle != null) {
            text.setText(bundle.getString("message"));
        }
    }
}
