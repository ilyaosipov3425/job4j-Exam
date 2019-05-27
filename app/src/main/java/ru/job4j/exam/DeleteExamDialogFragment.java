package ru.job4j.exam;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

/**
 * Класс DeleteExamDialogFragment - диалоговое окно удаление одного экзамена
 * @author Ilya Osipov (mailto:il.osipov.ya@yandex.ru)
 * @since 26.05.2019
 * @version $Id$
 */

public class DeleteExamDialogFragment extends DialogFragment {
    private DeleteExamDialogListener callback;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = new AlertDialog.Builder(getActivity())
                .setMessage(R.string.delete_exam)
                .setPositiveButton(android.R.string.ok,
                        (dialogInterface, i) -> callback
                                .onPositiveDeleteExamClick(this))
                .setNegativeButton(android.R.string.cancel,
                        (dialogInterface, i) -> callback
                                .onNegativeDeleteExamClick(this))
                .create();
        return dialog;
    }

    public interface DeleteExamDialogListener {
        void onPositiveDeleteExamClick(DialogFragment dialog);
        void onNegativeDeleteExamClick(DialogFragment dialog);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (DeleteExamDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(
                    String.format("%s must implement DeleteExamDialogListener", context.toString()));
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }
}
