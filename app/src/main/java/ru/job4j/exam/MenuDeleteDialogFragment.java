package ru.job4j.exam;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * Класс MenuDeleteDialogFragment - диалоговое окно меню delete
 * @author Ilya Osipov (mailto:il.osipov.ya@yandex.ru)
 * @since 29.04.2019
 * @version $Id$
 */

public class MenuDeleteDialogFragment extends DialogFragment {
    private MenuDeleteDialogListener callback;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new AlertDialog.Builder(getActivity())
                .setMessage(R.string.menu_delete)
                .setPositiveButton(android.R.string.ok,
                        (dialogInterface, i) -> callback
                                .onPositiveDeleteClick(MenuDeleteDialogFragment.this))
                .setNegativeButton(android.R.string.cancel,
                        (dialogInterface, i) -> callback
                                .onNegativeDeleteClick(MenuDeleteDialogFragment.this))
                .create();
        return dialog;
    }

    public interface  MenuDeleteDialogListener {
        void onPositiveDeleteClick(DialogFragment dialog);
        void onNegativeDeleteClick(DialogFragment dialog);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (MenuDeleteDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(
                    String.format("%s must implement MenuDeleteDialogListener", context.toString()));
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }
}
