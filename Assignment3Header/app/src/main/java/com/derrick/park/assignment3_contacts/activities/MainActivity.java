package com.derrick.park.assignment3_contacts.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.derrick.park.assignment3_contacts.R;
import com.derrick.park.assignment3_contacts.models.Contact;
import com.derrick.park.assignment3_contacts.models.ContactList;
import com.derrick.park.assignment3_contacts.network.ContactClient;
import com.shuhart.stickyheader.StickyHeaderItemDecorator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Contact> mContactList;

    String[] headerString = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    ArrayList<String> stringHeader = new ArrayList<String>(Arrays.asList(headerString));

    public static final String TAG = MainActivity.class.getSimpleName();
    public static final int TEXT_REQUEST = 1;

    // My ReclycerView
    private RecyclerView mRecyclerView;

    // My Adapter
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Call<ContactList> call = ContactClient.getContacts(50);

        adHeaders();

        call.enqueue(new Callback<ContactList>() {
            @Override
            public void onResponse(Call<ContactList> call, Response<ContactList> response) {

                if (response.isSuccessful()) {

                    // getting Contacts from the API
                    mContactList = response.body().getContactList();

                    // I have to sort my names inside my list

                    Collections.sort(mContactList);
                    setHeadersId();

                    Collections.sort(mContactList);

                    // set header property of mContactList
                    //setHeaders();

                    // Get a handle to the RecyclerView.
                    mRecyclerView = findViewById(R.id.recycler_view);

                    // Create an adapter and supply the data to be displayed.
                    mAdapter = new MyAdapter(mContactList);

                    // Connect the adapter with the RecyclerView.
                    mRecyclerView.setAdapter(mAdapter);

                    // Give the RecyclerView a default layout manager.
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                    StickyHeaderItemDecorator decorator = new StickyHeaderItemDecorator(mAdapter);
                    decorator.attachToRecyclerView(mRecyclerView);

                }
            }

            @Override
            public void onFailure(Call<ContactList> call, Throwable t) {
            }
        });


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
                startAddContactActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void startAddContactActivity(){
        Intent intent = new Intent(this, AddContact.class);
        startActivityForResult(intent,TEXT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String nameResult ="";
        String phoneResult = "";
        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                nameResult = extras.getString("EXTRA_CONTACT_NAME");
                phoneResult = extras.getString("EXTRA_CONTACT_PHONE");
                Log.d(TAG, "onActivityResult: WORKED");

                Contact c1 = new Contact();
                String[] arrayOfString = nameResult.split("\\s+");
                c1.setName(arrayOfString[0],arrayOfString[1]);
                c1.setCell(phoneResult);

                mContactList.add(c1);
                Collections.sort(mContactList);
                setHeaders();


                Log.d(TAG, "onActivityResult: ");
                mRecyclerView.getAdapter().notifyDataSetChanged();
                // Scroll to the bottom.
                mRecyclerView.smoothScrollToPosition(mContactList.size());
            }
        }
    }

    private void setHeaders(){
        String currentFirst_letter ="";
        String contactFirstLetter = "";
        for (Contact c: mContactList) {
            contactFirstLetter = c.getName().getFirst().substring(0,1).toUpperCase();
            if (!contactFirstLetter.equals(currentFirst_letter)) {
                currentFirst_letter = contactFirstLetter;
                c.setHeaderTrue();
            }else{
                c.setHeaderFalse();
            }
        }
    }

    private void adHeaders(){

        int count = 0;
        for (String s: headerString) {
            Contact c1 = new Contact();
            c1.setName(s,"");
            c1.setHeaderTrue();
            c1.setSectionHeaderId(count);
            count++;
        }
    }

    private void setHeadersId(){

        for (Contact c: mContactList) {
            if (c.isHeader()){
                continue;
            }
            else{
                c.setSectionItemid(stringHeader.indexOf(c.getName().getFirst().substring(0,1).toUpperCase()));
            }
        }
    }
}

