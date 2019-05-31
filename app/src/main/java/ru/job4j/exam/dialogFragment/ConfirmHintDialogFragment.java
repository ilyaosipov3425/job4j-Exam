package ru.job4j.exam.dialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import ru.job4j.exam.R;

/**
 * Класс ConfirmHintDialogFragment - диалоговое окно "подсказки"
 * @author Ilya Osipov (mailto:il.osipov.ya@yandex.ru)
 * @since 16.04.2019
 * @version $Id$
 */

public class ConfirmHintDialogFragment extends DialogFragment {

    private ConfirmHintDialogListener callback;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new AlertDialog.Builder(getActivity())
                .setMessage(R.string.show_hint)
                .setPositiveButton(android.R.string.ok,
                        (dialogInterface, i) -> callback.onPositiveDialogClick(ConfirmHintDialogFragment.this))
                .setNegativeButton(android.R.string.cancel,
                        (dialogInterface, i) -> callback.onNegativeDialogClick(ConfirmHintDialogFragment.this))
                .create();
        return dialog;
    }

    public interface ConfirmHintDialogListener {
        void onPositiveDialogClick(DialogFragment dialog);
        void onNegativeDialogClick(DialogFragment dialog);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (ConfirmHintDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(String.format("%s must implement ConfirmHintDialogListener", context.toString()));
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }
}
