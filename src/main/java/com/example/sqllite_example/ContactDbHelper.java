package com.example.sqllite_example;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ContactDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "contact_db";
    public static final int DATABASE_VERSION = 1;

    private static final String TAG = "Database Operations";

    public static final String CREATE_TABLE = "CREATE TABLE " + ContactContract.ContactEntry.TABLE_NAME +
            "(" + ContactContract.ContactEntry.CONTACT_ID + " number," + ContactContract.ContactEntry.CONTACT_NAME + " text,"
            + ContactContract.ContactEntry.CONTACT_EMAIL + " text);";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + ContactContract.ContactEntry.TABLE_NAME;

    public ContactDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        Log.i(TAG, "ContactDbHelper: Database created successfully");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE);
        Log.i(TAG, "ContactDbHelper: Table created successfully");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void addContacts(int id, String name, String email, SQLiteDatabase sqLiteDatabase){

        ContentValues contentValues = new ContentValues();
        contentValues.put(ContactContract.ContactEntry.CONTACT_ID, id);
        contentValues.put(ContactContract.ContactEntry.CONTACT_NAME, name);
        contentValues.put(ContactContract.ContactEntry.CONTACT_EMAIL, email);
        sqLiteDatabase.insert(ContactContract.ContactEntry.TABLE_NAME, null, contentValues);

        Log.i(TAG, "addContacts: One row affected successfully.");

    }

    public Cursor displayContacts(SQLiteDatabase sqLiteDatabase){

        String[] selection = {ContactContract.ContactEntry.CONTACT_ID, ContactContract.ContactEntry.CONTACT_NAME, ContactContract.ContactEntry.CONTACT_EMAIL };
        Cursor cursor = sqLiteDatabase.query(ContactContract.ContactEntry.TABLE_NAME, selection, null, null, null, null, null);
        return cursor;

    }

    public void updateContacts(int id, String name, String email, SQLiteDatabase sqLiteDatabase){

        ContentValues contentValues = new ContentValues();
        contentValues.put(ContactContract.ContactEntry.CONTACT_NAME, name);
        contentValues.put(ContactContract.ContactEntry.CONTACT_EMAIL, email);
        String selection = ContactContract.ContactEntry.CONTACT_ID + " = " + id;
        sqLiteDatabase.update(ContactContract.ContactEntry.TABLE_NAME, contentValues, selection, null);
    }

    public void deleteContact(int id, SQLiteDatabase sqLiteDatabase){

        String selection = ContactContract.ContactEntry.CONTACT_ID + " = " + id;
        sqLiteDatabase.delete(ContactContract.ContactEntry.TABLE_NAME, selection, null);
    }
}
