package com.example.guilherme.twoactivities;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private TextView mReplyHeadTextView;
    private TextView mReplyTextView;

    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";

    // TEXT_REQUEST is a KEY for a response that I am waiting, as i can have a lot of different response.
    public static final int TEXT_REQUEST = 1;

    private EditText mMessageEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mMessageEditText = findViewById(R.id.editText);
        mReplyHeadTextView = findViewById(R.id.text_header_reply);
        mReplyTextView = findViewById(R.id.text_message_reply);


        // Restore the state.
        if (savedInstanceState != null) {
            boolean isVisible = savedInstanceState.getBoolean("reply_visible");

            if (isVisible) {
                mReplyHeadTextView.setVisibility(View.VISIBLE);
                mReplyTextView.setText(savedInstanceState.getString("reply_text"));
                mReplyTextView.setVisibility(View.VISIBLE);
            }
        }


        Log.d(LOG_TAG, "-------");
        Log.d(LOG_TAG, "onCreate");

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // saving the visibility sate of the label.
        if (mReplyHeadTextView.getVisibility() == View.VISIBLE) {
            outState.putBoolean("reply_visible", true);
            outState.putString("reply_text",mReplyTextView.getText().toString());
        }
    }

    public void launchSecondActivity(View view) {
        Log.d(LOG_TAG, "Button clicked!");

        // initiate the Intent
        Intent intent = new Intent(this, SecondActivity.class);

        // get the message from the textEdit field
        String message = mMessageEditText.getText().toString();

        // create the extra bundle key with my constant EXTRA_MESSAGE, and the message that i am going to send to the next Activity
        intent.putExtra(EXTRA_MESSAGE, message);

        //commented this startActivity as this startActivity is not expecting results from second Window.

        //startActivity(intent);


        startActivityForResult(intent, TEXT_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                // get the reply from the secondActivity
                String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);

                // set the visibility of the header to VISIBLE
                mReplyHeadTextView.setVisibility(View.VISIBLE);

                // setting the text that I got from the Second Activity
                mReplyTextView.setText(reply);

                // set the visibility of the text received to VISIBLE
                mReplyTextView.setVisibility(View.VISIBLE);
            }
        }

    }
}
