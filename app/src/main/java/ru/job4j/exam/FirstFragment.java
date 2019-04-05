package ru.job4j.exam;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;;

/**
 * Класс FirstFragment - первый фрагмент
 * @author Ilya Osipov (mailto:il.osipov.ya@yandex.ru)
 * @since 01.04.2019
 * @version $Id$
 */

public class FirstFragment extends Fragment {
    private Button nextFragment;
    private OnNextButtonClickListener callback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_first, container, false);
        //return view;

        nextFragment = view.findViewById(R.id.next_fragment);
        nextFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onNextButtonClicked("Next button clicked");
            }
        });
        return view;
    }

    public interface OnNextButtonClickListener {
        void onNextButtonClicked(String message);
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
}
