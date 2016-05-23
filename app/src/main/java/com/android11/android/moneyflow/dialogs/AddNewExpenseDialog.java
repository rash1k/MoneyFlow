package com.android11.android.moneyflow.dialogs;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.android11.android.moneyflow.R;
import com.android11.android.moneyflow.services.MyIntentService;

public class AddNewExpenseDialog extends DialogFragment {

    private AutoCompleteTextView etName;
    private EditText etVolume;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_add_expancy, null, false);

        etName = (AutoCompleteTextView) view.findViewById(R.id.acNameOfExpense);
        etVolume = (EditText) view.findViewById(R.id.etVolumeOfExpency);


//        TODO set Adapter for AutocompliteTextView
        builder.setView(view)
                .setMessage(R.string.message_add_new_expency_dialog)
                .setTitle(R.string.title_add_new_expancy_dialog)
                .setPositiveButton(R.string.positive_button_add_new_expency_dialog, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        addNewExpense();
                    }
                }).setNegativeButton(R.string.negative_button_add_new_expency_dialog, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });
        return builder.create();
    }

    private void addNewExpense() {

        String name = etName.getText().toString();
        double volume = Double.parseDouble(etVolume.getText().toString());

        Toast.makeText(getActivity(), "Add from dialog", Toast.LENGTH_LONG).show();

        MyIntentService.startActionInsertExpense(getActivity(), name, volume);
    }
}
