package com.example.sqllite_example;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeFragment extends Fragment implements View.OnClickListener {

    Button buttonAddContact, buttonDisplayContact, buttonUpdateContact, buttonDeleteContact;

    OnDbOperationsListener onDbOperationsListener;

    public interface OnDbOperationsListener{

        void onCheckFunction(int check);
    }

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        buttonAddContact = view.findViewById(R.id.buttonHomeFragmentAddContact);
        buttonAddContact.setOnClickListener(this);

        buttonDisplayContact = view.findViewById(R.id.buttonHomeFragmentDisplayContact);
        buttonDisplayContact.setOnClickListener(this);


        buttonUpdateContact = view.findViewById(R.id.buttonHomeFragmentUpdateContact);
        buttonUpdateContact.setOnClickListener(this);

        buttonDeleteContact = view.findViewById(R.id.buttonHomeFragmentDeleteContact);
        buttonDeleteContact.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.buttonHomeFragmentAddContact:
                onDbOperationsListener.onCheckFunction(0);
                break;
            case R.id.buttonHomeFragmentDisplayContact:
                onDbOperationsListener.onCheckFunction(1);
                break;
            case R.id.buttonHomeFragmentUpdateContact:
                onDbOperationsListener.onCheckFunction(2);
                break;
            case R.id.buttonHomeFragmentDeleteContact:
                onDbOperationsListener.onCheckFunction(3);
                break;
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        Activity activity = (Activity) context;

        try{
            onDbOperationsListener = (OnDbOperationsListener) activity;
        }
        catch (ClassCastException e){
            throw new ClassCastException(activity.toString() + "onCheckFunction must be implimented");
        }
    }
}