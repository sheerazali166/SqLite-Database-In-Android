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

public class UpdateContactFragment extends Fragment {

    EditText editTextUpdateFragmentId, editTextUpdateFragmentName, editTextUpdateFragmentEmail;
    Button buttonUpdateFragmentUpdateRecord;

    public UpdateContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update_contact, container, false);
        editTextUpdateFragmentId = view.findViewById(R.id.editTextUpdateContactId);
        editTextUpdateFragmentName = view.findViewById(R.id.editTextUpdateContactName);
        editTextUpdateFragmentEmail = view.findViewById(R.id.editTextUpdateContactEmail);
        buttonUpdateFragmentUpdateRecord = view.findViewById(R.id.buttonUpdateContactUpdateRecord);
        buttonUpdateFragmentUpdateRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateRecordIntoDatabase();
            }
        });

        return view;

    }

    private void updateRecordIntoDatabase() {
        int id = Integer.parseInt(editTextUpdateFragmentId.getText().toString());
        String name = editTextUpdateFragmentName.getText().toString();
        String email = editTextUpdateFragmentEmail.getText().toString();

        ContactDbHelper contactDbHelper = new ContactDbHelper(getActivity());
        SQLiteDatabase sqLiteDatabase = contactDbHelper.getWritableDatabase();
        contactDbHelper.updateContacts(id, name, email, sqLiteDatabase);
        contactDbHelper.close();

        editTextUpdateFragmentId.setText("");
        editTextUpdateFragmentName.setText("");
        editTextUpdateFragmentEmail.setText("");

        Toast.makeText(getActivity(), "Record successfully updated.", Toast.LENGTH_SHORT).show();
    }
}