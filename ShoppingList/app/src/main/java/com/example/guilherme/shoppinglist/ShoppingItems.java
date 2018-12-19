package com.example.guilherme.shoppinglist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ShoppingItems extends AppCompatActivity {

    public static final String EXTRA_REPLY = "EXTRA_REPLY";

    private ImageView mFirstImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_items);

        mFirstImage = findViewById(R.id.image1);
    }

    public void replyItemClicked(View view) {


        // creating an intent for response, as we should not use the Intent sent to go to the second activity
        Intent replyIntent = new Intent();

        // put my new Extra bundle in the reply intent which is the TAG of the Image
        replyIntent.putExtra(EXTRA_REPLY, view.getTag().toString());

        // setting the results
        setResult(RESULT_OK,replyIntent);

        // finish to close the activity
        finish();
    }
}
