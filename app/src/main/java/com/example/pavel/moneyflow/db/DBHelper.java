package com.example.pavel.moneyflow.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.pavel.moneyflow.util.Prefs;

public class DBHelper extends SQLiteOpenHelper {

    /*
    Table expenses:
    - _id
    - id_passive (id from table passive)
    - volume (volume of money)
    - date (date when expenses made)
     */
    private static final String CREATE_TABLE_EXPENSES = String.format(
            "create table %s ( %s integer primary key autoincrement, %s integer, %s integer, %s text);",
            Prefs.TABLE_EXPENSES, Prefs.FIELD_ID, Prefs.EXPENSE_FIELD_ID_PASSIVE, Prefs.EXPENSE_FIELD_VOLUME,
            Prefs.EXPENSE_FIELD_DATE);

    /*
    Table expense_name
    - _id
    - name (name of expense)
     */
    private static final String CREATE_TABLE_EXPENSE_NAME = String.format(
            "create table %s ( %s integer primary key autoincrement, %s text);",
            Prefs.TABLE_EXPENSES_NAMES, Prefs.FIELD_ID, Prefs.EXPENCE_NAMES_FIELDS_NAME);


    public DBHelper(Context context, int version) {
        super(context, Prefs.DB_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_EXPENSES);
        db.execSQL(CREATE_TABLE_EXPENSE_NAME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        
    }
}
