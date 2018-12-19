package com.example.guilherme.buttonsscrollactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class passagesActivity extends AppCompatActivity {

    private TextView mTextView1;
    private TextView mTextView2;
    private TextView mTextView3;

    private TextView mAlbumTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passages);

        mTextView1 = findViewById(R.id.passages1);
        mTextView2 = findViewById(R.id.passages2);
        mTextView3 = findViewById(R.id.passages3);

        mAlbumTitle = findViewById(R.id.article_heading);

        // when created the activity get the intent that launched the activity
        Intent intent = getIntent();

        // getting the message sent to the Second Activity using the static EXTRA_MESSAGE as the key
        String buttonClicked = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        if (buttonClicked.equals("Abbey Road")){
            mAlbumTitle.setText("Abbey Road");
            mTextView1.setVisibility(View.VISIBLE);
        }else if (buttonClicked.equals("White Album")){
            mAlbumTitle.setText("White Album");
            mTextView2.setVisibility(View.VISIBLE);
        }else{
            mAlbumTitle.setText("Sgt. Peppers");
            mTextView3.setVisibility(View.VISIBLE);
        }



        /*
        if (buttonClicked.equals("1")){
            mTextView.setText("aaa777777");
        }else if ((buttonClicked.equals("2"))){
            mTextView.setText("AABBCC");
        }else{
            mTextView.setText("AABBCC123131231");
        }

        */

    }
}
