package com.derrick.park.assignment3_contacts.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
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
    public static final int TEXT_REQUEST = 1;

    // My ReclycerView
    private RecyclerView mRecyclerView;

    // My Adapter
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Call<ContactList> call = ContactClient.getContacts(10);

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

        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                String nameResult = extras.getString("EXTRA_CONTACT_NAME");
                String phoneResult = extras.getString("EXTRA_CONTACT_PHONE");
                Log.d(TAG, "onActivityResult: WORKED");
            }
        }

        Contact c1 = new Contact();
        c1.setName("Gui","W");
        c1.setCell("123131231");
        mContactList.add(c1);
        Collections.sort(mContactList);
        mRecyclerView.getAdapter().notifyItemInserted(mContactList.size());
        // Scroll to the bottom.
        mRecyclerView.smoothScrollToPosition(mContactList.size());
    }
}

