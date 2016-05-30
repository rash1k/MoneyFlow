package com.example.pavel.moneyflow.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;

import com.example.pavel.moneyflow.R;

public class ConfirmDeleteDialog extends AlertDialog {

    Context context;

    public ConfirmDeleteDialog(Context context) {
        super(context);
        setCanceledOnTouchOutside(true);
        setTitle(context.getString(R.string.title_confirm_dialog));
    }
}
