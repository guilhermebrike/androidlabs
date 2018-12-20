package com.derrick.park.assignment3_contacts.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.derrick.park.assignment3_contacts.R;
import com.derrick.park.assignment3_contacts.models.Contact;
import com.derrick.park.assignment3_contacts.models.ContactList;
import com.derrick.park.assignment3_contacts.network.ContactClient;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Contact> mContactList;


    public static final String TAG = MainActivity.class.getSimpleName();

    // My ReclycerView
    private RecyclerView mRecyclerView;

    // My Adapter
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Call<ContactList> call = ContactClient.getContacts(100);

        call.enqueue(new Callback<ContactList>() {
            @Override
            public void onResponse(Call<ContactList> call, Response<ContactList> response) {
                if (response.isSuccessful()) {

                    // getting Contacts from the API
                    mContactList = response.body().getContactList();

                    // I have to sort my names inside my list

                    Collections.sort(mContactList);

                    // Get a handle to the RecyclerView.
                    mRecyclerView = findViewById(R.id.recycler_view);

                    // Create an adapter and supply the data to be displayed.
                    mAdapter = new MyAdapter(mContactList);

                    // Connect the adapter with the RecyclerView.
                    mRecyclerView.setAdapter(mAdapter);

                    // Give the RecyclerView a default layout manager.
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                }
            }

            @Override
            public void onFailure(Call<ContactList> call, Throwable t) {
                // Error Handling
            }
        });




    }

    // Toast for testing some functionalities
    private void toast(String message){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_contact:
                toast("Add Contact");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }




}
