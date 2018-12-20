package com.example.guilherme.twoactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private static final String LOG_TAG = SecondActivity.class.getSimpleName();

    public static final String EXTRA_REPLY = "EXTRA_REPLY";

    private EditText mReply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // when created the activity get the intent that launched the activity
        Intent intent = getIntent();

        // getting the message sent to the Second Activity using the static EXTRA_MESSAGE as the key
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // first get the id of the text field by using the findViewById
        TextView textView = findViewById(R.id.text_message);

        // set the message in the text field
        textView.setText(message);


        // getting the ID of the text field to reply
        mReply = findViewById(R.id.editText_second);

        // ALL OF THIS ON THE CREATION OF THE ACTIVITY

        Log.d(LOG_TAG, "-------");
        Log.d(LOG_TAG, "onCreate");

    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }

    public void returnReply(View view) {
        // this is the message that will be replied to the Main Activity
        String reply = mReply.getText().toString();

        // creating an intent for response, as we should not use the Intent sent to go to the second activity
        Intent replyIntent = new Intent();

        // put my new Extra bundle in the reply intent
        replyIntent.putExtra(EXTRA_REPLY, reply);

        // setting the results
        setResult(RESULT_OK,replyIntent);

        Log.d(LOG_TAG, "End SecondActivity");

        // finish to close the activity
        finish();
    }
}
