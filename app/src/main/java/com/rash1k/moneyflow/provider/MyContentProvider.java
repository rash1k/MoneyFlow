package com.rash1k.moneyflow.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import com.rash1k.moneyflow.db.DBHelper;
import com.rash1k.moneyflow.util.Prefs;

public class MyContentProvider extends ContentProvider {

    private SQLiteDatabase database;
    private DBHelper dbHelper;

    private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    public static final int URI_EXPENSES_CODE = 1;
    public static final int URI_EXPENSE_NAME_CODE = 2;
    public static final int URI_RAW_QUERY_ALL_EXPENSES_CODE = 3;

    static {
        uriMatcher.addURI(Prefs.URI_EXPENSES_AUTHORITIES,
                Prefs.URI_EXPENSES_PATH,
                URI_EXPENSES_CODE);
        uriMatcher.addURI(Prefs.URI_EXPENSES_NAMES_AUTHORITIES,
                Prefs.URI_EXPENSES_NAMES_PATH,
                URI_EXPENSE_NAME_CODE);
        uriMatcher.addURI(Prefs.URI_EXPENSES_NAMES_AUTHORITIES,
                Prefs.URI_ALL_EXPENSES_PATH,
                URI_RAW_QUERY_ALL_EXPENSES_CODE);
    }

    public MyContentProvider() {

    }

    @Override
    public boolean onCreate() {
        dbHelper = new DBHelper(getContext(), Prefs.DB_CURRENT_VERSION);
        return true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        long id;
        Uri insertUri = null;
        database = dbHelper.getWritableDatabase();

        switch (uriMatcher.match(uri)) {
            case URI_EXPENSES_CODE:
                id = database.insert(Prefs.TABLE_NAME_EXPENSES, null, values);
                insertUri = ContentUris.withAppendedId(uri, id);
                getContext().getContentResolver().notifyChange(insertUri, null);
                break;
            case URI_EXPENSE_NAME_CODE:
                id = database.insert(Prefs.TABLE_NAME_EXPENSE_NAMES, null, values);
                insertUri = ContentUris.withAppendedId(uri, id);
                getContext().getContentResolver().notifyChange(insertUri, null);
                break;
        }
        return insertUri;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        database = dbHelper.getReadableDatabase();

        Cursor cursor = null;

        switch (uriMatcher.match(uri)) {
            case URI_EXPENSES_CODE:
                cursor = database.query(Prefs.TABLE_NAME_EXPENSES, projection,
                        selection, selectionArgs, null, null, sortOrder);
                break;
            case URI_EXPENSE_NAME_CODE:
                cursor = database.query(Prefs.TABLE_NAME_EXPENSE_NAMES, projection,
                        selection, selectionArgs, null, null, sortOrder);
                break;
            case URI_RAW_QUERY_ALL_EXPENSES_CODE:
                SQLiteQueryBuilder sqb = new SQLiteQueryBuilder();

                sqb.setTables(Prefs.RAW_QUERY_ALL_EXPENSES);
                cursor = sqb.query(database, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Input correct URI");
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        database = dbHelper.getWritableDatabase();
        int updateId;

        switch (uriMatcher.match(uri)) {
            case URI_EXPENSES_CODE:
                updateId = database.update(Prefs.TABLE_NAME_EXPENSES, values, selection, selectionArgs);
                break;
            case URI_EXPENSE_NAME_CODE:
                updateId = database.update(Prefs.TABLE_NAME_EXPENSE_NAMES, values, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Not yet implemented");
        }
        return updateId;
        // TODO: Implement this to handle requests to update one or more rows.
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        database = dbHelper.getWritableDatabase();
        int deleteId;
        switch (uriMatcher.match(uri)) {
            case URI_EXPENSES_CODE:
                deleteId = database.delete(Prefs.TABLE_NAME_EXPENSES, selection, selectionArgs);
                break;
            case URI_EXPENSE_NAME_CODE:
                deleteId = database.delete(Prefs.TABLE_NAME_EXPENSE_NAMES, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Input correct URI");
        }
        return deleteId;


    }

    @Override
    public String getType(Uri uri) {

        return uri.getAuthority();
//        throw new UnsupportedOperationException("Not yet implemented");

    }
}
