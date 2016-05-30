package com.example.pavel.moneyflow.dialogs;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.example.pavel.moneyflow.R;
import com.example.pavel.moneyflow.service.MyIntentService;

public class AddNewExpencyDialog extends DialogFragment {

    EditText etVolumeOfExpenses;
    AutoCompleteTextView acNameOfExpenses;

    Activity activity;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        activity = getActivity();

        View view = LayoutInflater.from(activity).inflate(R.layout.dialog_add_expency, null, false);
        //TODO set adapter for AutoCompleateTextView

        etVolumeOfExpenses = (EditText) view.findViewById(R.id.etVolumeOfExpenses);
        acNameOfExpenses = (AutoCompleteTextView) view.findViewById(R.id.acNameOfExpenses);

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(view)
            .setMessage(R.string.message_add_new_expenses)
            .setTitle(R.string.title_add_new_expency_dialog)
            .setPositiveButton(R.string.positive_button_add_new_expency_dialog, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    addNewExpense();
                }
            })
            .setNegativeButton(R.string.negative_button_add_new_expency_dialog, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dismiss();
                }
            });

        return builder.create();
    }

    private void addNewExpense() {
        String name = acNameOfExpenses.getText().toString();
        int volume = Integer.parseInt(etVolumeOfExpenses.getText().toString());

        MyIntentService.startActionInsertExpency(activity, name, volume);
    }
}
