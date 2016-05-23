package com.android11.android.moneyflow.activities;

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

import com.android11.android.moneyflow.R;
import com.android11.android.moneyflow.dialogs.AddNewExpenseDialog;
import com.android11.android.moneyflow.util.Prefs;

public class DashboardActivity extends AppCompatActivity {

    private Button btnDashboardShowExpenses;


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

        btnDashboardShowExpenses = (Button) findViewById(R.id.btnDashboardShowExpenses);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.item_expenses:

                AddNewExpenseDialog dialog = new AddNewExpenseDialog();

                dialog.show(getSupportFragmentManager(), "addExpenses");

//                Toast.makeText(this,"Click on expency",Toast.LENGTH_LONG).show();
                break;
        }
        return true;

    }


    public void onClick(View view) {
        Cursor c = getContentResolver().query(Prefs.URI_EXPENSE, null, null, null, null);

        switch (view.getId()) {
            case R.id.btnDashboardShowExpenses:
                if (c != null) {
                    if (c.moveToFirst()) {

                        do {
                            Log.d(Prefs.LOG_TAG, c.getString(c.getColumnIndex(Prefs.FIELD_ID)) + ", " +
                                    c.getColumnIndex(Prefs.EXPENSE_FIELD_ID_PASSIVE) + ", " +
                                    c.getColumnIndex(Prefs.EXPENSE_FIELD_DATE) + "," +
                                    c.getColumnIndex(Prefs.EXPENSE_FIELD_VOLUME));
                        } while (c.moveToNext());

                    } else
                        Log.d(Prefs.LOG_TAG, "Table:" + Prefs.TABLE_NAME_EXPENSES + "it contains o rows");
                    c.close();
                }
                break;
        }
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((resultCode == Activity.RESULT_OK && requestCode == 1)) {
            Cursor c = getContentResolver().query(data.getData(), new String[]{ContactsContract.Contacts.DISPLAY_NAME}, null, null, null);

            if (c.moveToFirst()) {
                int columnIndex = c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                String[] columnName = c.getColumnNames();
                for (int i = 0; i < columnName.length; i++) {
                    i = c.getColumnIndex(columnName[i]);
                    String s = c.getString(i);
                }
            }
        }
    }*/


}
