package ru.job4j.exam;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * Класс MenuAddDialogFragment - диалоговое окно меню add
 * @author Ilya Osipov (mailto:il.osipov.ya@yandex.ru)
 * @since 07.05.2019
 * @version $Id$
 */

public class MenuAddDialogFragment extends DialogFragment {
    private MenuAddDialogListener callback;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new AlertDialog.Builder(getActivity())
                .setMessage(R.string.menu_add)
                .setPositiveButton(android.R.string.ok,
                        (dialogInterface, i) -> callback
                                .onPositiveAddClick(MenuAddDialogFragment.this))
                .setNegativeButton(android.R.string.cancel,
                        (dialogInterface, i) -> callback
                                .onNegativeAddClick(MenuAddDialogFragment.this))
                .create();
        return dialog;
    }

    public interface MenuAddDialogListener {
        void onPositiveAddClick(DialogFragment dialog);
        void onNegativeAddClick(DialogFragment dialog);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (MenuAddDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(
                    String.format("%s must implement MenuAddDialogListener", context.toString()));
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }
}
