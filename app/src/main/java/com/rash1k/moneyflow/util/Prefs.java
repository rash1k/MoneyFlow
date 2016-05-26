package com.rash1k.moneyflow.util;

import android.net.Uri;
import android.provider.BaseColumns;

public class Prefs {

//    The Log constance
    public static final String LOG_TAG = "MoneyFlow";
    public static final String LOG_WARN_SQL = "SQLite";

    public static final String RAW_QUERY_ALL_EXPENSES = "SELECT expense_names.name, expenses.volume, expense_names.critical, expenses.date FROM expenses INNER JOIN expense_names ON expense_names._id = expenses.id_passive;";
    public static final String RAW_QUERY_ALL_EXPENSES_2 = Prefs.TABLE_NAME_EXPENSES + " INNER JOIN " + Prefs.TABLE_NAME_EXPENSE_NAMES + " ON (" +
            Prefs.TABLE_NAME_EXPENSE_NAMES + "." + Prefs.FIELD_ID + " = "
            + Prefs.TABLE_NAME_EXPENSES + "." + Prefs.EXPENSE_FIELD_ID_PASSIVE + ")";

    //The password constants:
    public static final String FN_FIELD_NAME = "first_name";
    public static final String LN_FIELD_NAME = "last_name";
    public static final String BIRTHDAY_FIELD_NAME = "birthday";
    public static final String EMAIL_FIELD_NAME = "email";
    public static final String API_SERVER = "http://cityfinder.esy.es";



    //The Database constants:
    public static final String DB_NAME = "Money_Flow_DB";
    public static final int DB_CURRENT_VERSION = 2;
    public static final String FIELD_ID = BaseColumns._ID;

    //The Table Expenses constants:

//    The Table Expenses
    public static final String TABLE_NAME_EXPENSES = "expenses";
    public static final String EXPENSE_FIELD_ID_PASSIVE = "id_passive";
    public static final String EXPENSE_FIELD_VOLUME = "volume";
    public static final String EXPENSE_FIELD_DATE = "date";

    //The Table Expense Names
    public static final String TABLE_NAME_EXPENSE_NAMES = "expense_names";
    public static final String EXPENSE_NAMES_FIELD_NAME = "name";
    public static final String EXPENSE_NAMES_FIELD_CRITICAL = "critical";

    //The provider constants:
    public static final String URI_EXPENSES_AUTHORITIES = "com.rash1k.moneyflow.provider";
    public static final String URI_EXPENSES_PATH = "expenses";
    public static final String URI_SCHEMA_PROVIDER = "content://";
    public static final Uri URI_EXPENSES = Uri.parse(URI_SCHEMA_PROVIDER + URI_EXPENSES_AUTHORITIES + "/" + URI_EXPENSES_PATH);

    public static final String URI_EXPENSES_NAMES_AUTHORITIES = "com.rash1k.moneyflow.provider";
    public static final String URI_EXPENSES_NAMES_PATH = "expenses_names";
    public static final Uri URI_EXPENSES_NAMES = Uri.parse(URI_SCHEMA_PROVIDER + URI_EXPENSES_NAMES_AUTHORITIES + "/" + URI_EXPENSES_NAMES_PATH);


    public static final String URI_ALL_EXPENSES_PATH = "all_expenses";
    public static final Uri URI_ALL_EXPENSES = Uri.parse("content://" + URI_EXPENSES_NAMES_AUTHORITIES + "/" + URI_ALL_EXPENSES_PATH);



}
