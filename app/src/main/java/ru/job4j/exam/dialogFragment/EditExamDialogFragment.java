package ru.job4j.exam.dialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import ru.job4j.exam.R;

/**
 * Класс EditExamDialogFragment - диалоговое окно редактирования одного экзамена
 * @author Ilya Osipov (mailto:il.osipov.ya@yandex.ru)
 * @since 26.05.2019
 * @version $Id$
 */

public class EditExamDialogFragment extends DialogFragment {

    private EditExamDialogListener callback;
    private int position = 0;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        if (bundle != null) {
            position = bundle.getInt("position");
        }

        Dialog dialog = new AlertDialog.Builder(getActivity())
                .setMessage(R.string.edit_exam)
                .setPositiveButton(android.R.string.ok,
                        (dialogInterface, i) -> callback
                                .onPositiveEditClick(EditExamDialogFragment.this, position))
                .setNegativeButton(android.R.string.cancel,
                        (dialogInterface, i) -> callback
                                .onNegativeEditClick(EditExamDialogFragment.this))
                .create();
        return dialog;
    }

    public interface EditExamDialogListener {
        void onPositiveEditClick(DialogFragment dialog, int position);
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
