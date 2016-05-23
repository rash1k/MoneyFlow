package com.android11.android.moneyflow.services;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;

import com.android11.android.moneyflow.util.Prefs;

import java.util.Calendar;


public class MyIntentService extends IntentService {


    private static final String ACTION_INSERT_EXPENSE = "com.android11.android.moneyflow.services.action.INSERT_EXPENSE";

    private static final String EXTRA_INSERT_EXPENSE_NAME = "com.android11.android.moneyflow.services.extra.INSERT_EXPENSE_NAME";

    private static final String EXTRA_INSERT_EXPENSE_VOLUME = "com.android11.android.moneyflow.services.extra.INSERT_EXPENSE_VOLUME";


    public MyIntentService() {
        super("MyIntentService");
    }

    public static void startActionInsertExpense(Context context, String name, double volume) {

        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_INSERT_EXPENSE);
        intent.putExtra(EXTRA_INSERT_EXPENSE_NAME, name);
        intent.putExtra(EXTRA_INSERT_EXPENSE_VOLUME, volume);
        context.startService(intent);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();

            switch (action) {
                case ACTION_INSERT_EXPENSE:
                    final String name = intent.getStringExtra(EXTRA_INSERT_EXPENSE_NAME);
                    final double volume = intent.getDoubleExtra(EXTRA_INSERT_EXPENSE_VOLUME, 0);
                    handleActionInsertExpense(name, volume);
                    break;
            }

        }
    }

    private void handleActionInsertExpense(String name, double volume) {
        ContentValues cv = new ContentValues();
//        cv.put(Prefs.EXPENSE_NAMES_FIELD_NAME, name);

        String date = String.valueOf(Calendar.getInstance().getTimeInMillis());

        cv.put(Prefs.EXPENSE_FIELD_ID_PASSIVE, name);
        cv.put(Prefs.EXPENSE_FIELD_DATE, date);
        cv.put(Prefs.EXPENSE_FIELD_VOLUME, volume);

        getContentResolver().insert(Prefs.URI_EXPENSE, cv);


    }


}
