package com.example.guilherme.recyclerviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private String[] mNames = {"John","Nick"};
    private String[] mNumbers = {"1234567890","0987654321"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.contacts_rv);

        // creating the adapter
        ContactsAdapter adapter = new ContactsAdapter(this,mNames,mNumbers);
        // setting my adapter to my recyclerview
        mRecyclerView.setAdapter(adapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

}
