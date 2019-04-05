package ru.job4j.exam;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Класс SecondFragment - второй фрагмент
 * @author Ilya Osipov (mailto:il.osipov.ya@yandex.ru)
 * @since 01.04.2019
 * @version $Id$
 */

public class SecondFragment extends Fragment {
    private Button backFragment;
    private OnBackButtonClickListener callback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savesInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        //return view;

        backFragment = view.findViewById(R.id.back_fragment);
        backFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onBackButtonClicked("Back button clicked");
            }
        });
        return view;
    }

    public interface OnBackButtonClickListener {
        void onBackButtonClicked(String message);
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
}
