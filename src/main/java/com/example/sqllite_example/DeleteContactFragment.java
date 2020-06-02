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

public class DeleteContactFragment extends Fragment {

    EditText editTextDeleteContactId;
    Button buttonDeleteContactDeleteRecord;

    public DeleteContactFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delete_contact, container, false);
        editTextDeleteContactId = view.findViewById(R.id.editTextDeleteContactId);
        buttonDeleteContactDeleteRecord = view.findViewById(R.id.buttonDeleteContactDeleteRecord);
        buttonDeleteContactDeleteRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteContactFromTheDatabase();
            }
        });

        return view;
    }

    private void deleteContactFromTheDatabase() {

        int id = Integer.parseInt(editTextDeleteContactId.getText().toString());
        ContactDbHelper contactDbHelper = new ContactDbHelper(getActivity());
        SQLiteDatabase sqLiteDatabase = contactDbHelper.getWritableDatabase();
        contactDbHelper.deleteContact(id, sqLiteDatabase);
        contactDbHelper.close();

        editTextDeleteContactId.setText("");

        Toast.makeText(getActivity(), "Record successfully deleted.", Toast.LENGTH_SHORT).show();
    }
}