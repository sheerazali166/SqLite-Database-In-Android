package com.example.sqllite_example;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddContactFragment extends Fragment {

    EditText editTextId, editTextName, editTextEmail;
    Button buttonSaveRecord;

    public AddContactFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_contact, container, false);

        editTextId = view.findViewById(R.id.editTextAddContactId);
        editTextName = view.findViewById(R.id.editTextAddContactName);
        editTextEmail = view.findViewById(R.id.editTextAddContactEmail);
        buttonSaveRecord = view.findViewById(R.id.buttonAddContactSaveRecord);
        buttonSaveRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addContactsIntoDatabase();
            }
        });

        return view;
    }

    public void addContactsIntoDatabase(){
        String id = editTextId.getText().toString();
        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();

        ContactDbHelper contactDbHelper = new ContactDbHelper(getActivity());
        SQLiteDatabase sqLiteDatabase = contactDbHelper.getWritableDatabase();
        contactDbHelper.addContacts(Integer.parseInt(id), name, email, sqLiteDatabase);
        contactDbHelper.close();

        editTextId.setText("");
        editTextName.setText("");
        editTextEmail.setText("");

        Toast.makeText(getActivity(), "One row affected successfully.", Toast.LENGTH_SHORT).show();


    }
}