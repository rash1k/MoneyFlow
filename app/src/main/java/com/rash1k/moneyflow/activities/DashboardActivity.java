package com.rash1k.moneyflow.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.rash1k.moneyflow.R;
import com.rash1k.moneyflow.dialogs.AddNewExpenseDialog;
import com.rash1k.moneyflow.util.Prefs;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button buttonShowExpenses = (Button) findViewById(R.id.btnDashBoardShowExpenses);
        buttonShowExpenses.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_expencies:
                AddNewExpenseDialog addNewExpenseDialog = new AddNewExpenseDialog();
                addNewExpenseDialog.show(getSupportFragmentManager(), "addExpency");
                //Toast.makeText(this, "Click on expency", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    // вывод в лог данных из курсора
    void logCursor(Cursor c) {
        if (c != null) {
            if (c.moveToFirst()) {
                String str;
                do {
                    str = "";
                    for (String cn : c.getColumnNames()) {
                        str = str.concat(cn + " = " + c.getString(c.getColumnIndex(cn)) + "; ");
                    }
                    Log.d(Prefs.LOG_TAG, str);
                } while (c.moveToNext());
            }
        } else
            Log.d(Prefs.LOG_TAG, "Cursor is null");
    }

    @Override
    public void onClick(View v) {

        Cursor c;

        switch (v.getId()) {
            case R.id.btnDashBoardShowExpenses:

                Log.d(Prefs.LOG_TAG, "--- EXPENSES_NAMES Table ---");
                c = getContentResolver().query(Prefs.URI_EXPENSES_NAMES, null, null, null, null);
                logCursor(c);
                c.close();
               /* Log.d(Prefs.LOG_TAG, "---- ----");

                Log.d(Prefs.LOG_TAG, "--- EXPENSES Table ---");
                c = getContentResolver().query(Prefs.URI_EXPENSES, null, null, null, null);
                logCursor(c);
                c.close();
                Log.d(Prefs.LOG_TAG, "---- ----");*/

                Intent intent = new Intent(this, ExpensesActivity.class);
                startActivity(intent);
                break;
        }
    }
}
