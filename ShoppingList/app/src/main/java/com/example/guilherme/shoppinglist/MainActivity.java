package com.example.guilherme.shoppinglist;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> listOfTextViews = new ArrayList<>();

    private static final String TAG = "MyActivity";
    private static final String PRESERVE_TEXT_FIELD = "TEXT_FIELD";

    public static final int ADD_ITEM_REQUEST = 1;

    private LinearLayout mlinear_layout_list;

    private EditText mTextSearchStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mlinear_layout_list = findViewById(R.id.linear_layout_list);
        mTextSearchStore = findViewById(R.id.text_search_store);

        if (savedInstanceState != null) {
            listOfTextViews = savedInstanceState.getStringArrayList(PRESERVE_TEXT_FIELD);
            for (String s: listOfTextViews) {
                TextView newTextView = new TextView(this);
                newTextView.setText(s.toString());
                mlinear_layout_list.addView(newTextView);
            }
        }
    }



    public void addItem(View view) {

        // initiate the Intent
        Intent intent = new Intent(this, ShoppingItems.class);


        // start activity for request
        startActivityForResult(intent,ADD_ITEM_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_ITEM_REQUEST) {
            if (resultCode == RESULT_OK) {
                TextView newTextView = new TextView(this);
                String reply = data.getStringExtra(ShoppingItems.EXTRA_REPLY);
                newTextView.setText(reply);
                listOfTextViews.add(newTextView.getText().toString());
                mlinear_layout_list.addView(newTextView);

            }
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList(PRESERVE_TEXT_FIELD,listOfTextViews);
    }


    public void search_store(View view) {

        String loc = mTextSearchStore.getText().toString();

        Uri addressUri = Uri.parse("geo:0,0?q=" + loc);

        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }

    }
}
