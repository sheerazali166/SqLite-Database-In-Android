package com.example.sqllite_example;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ReadContactFragment extends Fragment {

    TextView textViewDispalyContacts;

    public ReadContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_read_contact, container, false);

        textViewDispalyContacts = view.findViewById(R.id.textViewDisplayContacts);

        displayContactsFromDatabase();


        return view;

    }

    public void displayContactsFromDatabase(){


        ContactDbHelper contactDbHelper = new ContactDbHelper(getActivity());
        SQLiteDatabase sqLiteDatabase = contactDbHelper.getReadableDatabase();
        Cursor cursor = contactDbHelper.displayContacts(sqLiteDatabase);

        String info = "";

        while (cursor.moveToNext()){

            String id = Integer.toString(cursor.getInt(cursor.getColumnIndex(ContactContract.ContactEntry.CONTACT_ID)));
            String name = cursor.getString(cursor.getColumnIndex(ContactContract.ContactEntry.CONTACT_NAME));
            String email = cursor.getString(cursor.getColumnIndex(ContactContract.ContactEntry.CONTACT_EMAIL));

            info = info + "\n\nId: " + id + "\nName: " + name + "\nEmail: " + email;

        }

        textViewDispalyContacts.setText(info);

    }
}