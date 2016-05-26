package com.rash1k.moneyflow.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;

import com.rash1k.moneyflow.R;
import com.rash1k.moneyflow.services.MyIntentService;


public class AddNewExpenseDialog extends DialogFragment {

    EditText etVolumeOfExpenses;
    AutoCompleteTextView acNameOfExpenses;
    CheckBox chbCritical;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_add_expency, null, true);
        //TODO set adapter for AutoCompleateTextView

        etVolumeOfExpenses = (EditText) view.findViewById(R.id.etVolumeOfExpency);
        acNameOfExpenses = (AutoCompleteTextView) view.findViewById(R.id.acNameOfExpense);
        chbCritical = (CheckBox) view.findViewById(R.id.chbCriticalExpense);

        builder.setView(view)
                .setMessage(R.string.message_add_new_expense_dialog)
                .setTitle(R.string.title_add_new_expense_dialog)
                .setPositiveButton(R.string.positive_button_add_new_expense_dialog, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        addNewExpense();
                    }
                })
                .setNegativeButton(R.string.negative_button_add_new_expense_dialog, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                });
        return builder.create();
    }

    private void addNewExpense() {

        String name = acNameOfExpenses.getText().toString();
        double volume = Double.parseDouble(etVolumeOfExpenses.getText().toString());
        int critical = chbCritical.isChecked() ? 1 : 0;

        if (!name.equals("")) {
            MyIntentService.startActionInsertExpense(getActivity(), name, volume, critical);
        } else dismiss();
    }
}
