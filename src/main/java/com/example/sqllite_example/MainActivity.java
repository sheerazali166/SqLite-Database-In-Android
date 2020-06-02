package com.example.sqllite_example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnDbOperationsListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findViewById(R.id.frameLayoutContainer) != null){
            if(savedInstanceState != null){
                return;
            }

            HomeFragment homeFragment = new HomeFragment();

            getSupportFragmentManager().beginTransaction().add(R.id.frameLayoutContainer, homeFragment, null).commit();
        }

    }


    @Override
    public void onCheckFunction(int check) {

        switch (check){
            case 0:
                AddContactFragment addContactFragment = new AddContactFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutContainer, addContactFragment, null).addToBackStack(null).commit();
                break;
            case 1:
                ReadContactFragment readContactFragment = new ReadContactFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutContainer, readContactFragment, null).addToBackStack(null).commit();
                break;
            case 2:
                UpdateContactFragment updateContactFragment = new UpdateContactFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutContainer, updateContactFragment, null).addToBackStack(null).commit();
                break;
            case 3:
                DeleteContactFragment deleteContactFragment = new DeleteContactFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutContainer, deleteContactFragment, null).addToBackStack(null).commit();
                break;
        }
    }
}