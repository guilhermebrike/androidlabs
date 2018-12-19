package com.example.guilherme.receiver1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private TextView mMainText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainText = findViewById(R.id.main_text);

        Intent intent = getIntent();
        Uri uri = intent.getData();

        if (uri != null){
            String uri_str = uri.toString();
            mMainText.setText(uri_str);
        }
    }
}
