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
 * Класс SecondFragment - второй фрагмент
 * @author Ilya Osipov (mailto:il.osipov.ya@yandex.ru)
 * @since 01.04.2019
 * @version $Id$
 */

public class SecondFragment extends Fragment {

    private Button backFragment;
    private OnBackButtonClickListener callback;
    private TextView text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savesInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        text = view.findViewById(R.id.message_second);
        backFragment = view.findViewById(R.id.back_fragment);
        backFragment.setOnClickListener(this::onClick);

        return view;
    }

    public interface OnBackButtonClickListener {
        void onBackButtonClicked(String message);
    }

    public void onClick(View view) {
        callback.onBackButtonClicked("Back button licked");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callback = (OnBackButtonClickListener) context;
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
