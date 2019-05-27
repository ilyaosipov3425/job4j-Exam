package ru.job4j.exam;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

/**
 * Класс EditExamDialogFragment - диалоговое окно редактирования одного экзамена
 * @author Ilya Osipov (mailto:il.osipov.ya@yandex.ru)
 * @since 26.05.2019
 * @version $Id$
 */

public class EditExamDialogFragment extends DialogFragment {
    private EditExamDialogListener callback;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = new AlertDialog.Builder(getActivity())
                .setMessage(R.string.edit_exam)
                .setPositiveButton(android.R.string.ok,
                        (dialogInterface, i) -> callback
                                .onPositiveEditClick(EditExamDialogFragment.this))
                .setNegativeButton(android.R.string.cancel,
                        (dialogInterface, i) -> callback
                                .onNegativeEditClick(EditExamDialogFragment.this))
                .create();
        return dialog;
    }

    public interface EditExamDialogListener {
        void onPositiveEditClick(DialogFragment dialog);
        void onNegativeEditClick(DialogFragment dialog);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (EditExamDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(
                    String.format("%s must implement EditExamDialogListener", context.toString()));
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }
}
