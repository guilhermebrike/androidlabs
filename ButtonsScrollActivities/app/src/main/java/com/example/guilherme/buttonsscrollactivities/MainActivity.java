package com.example.guilherme.buttonsscrollactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";

    private Button mButton1;
    private Button mButton2;
    private Button mButton3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton1 = findViewById(R.id.button1);
        mButton2 = findViewById(R.id.button2);
        mButton3 = findViewById(R.id.button3);


    }

    public void openAlbumText(View view) {

        Intent intent = new Intent(this, passagesActivity.class);

        // WE CAN DO THE FOLLOWING BOTH WAYS
        /*
        if (view.getId() == R.id.button1){
            intent.putExtra(EXTRA_MESSAGE,mButton1.getText().toString());
        }else if (view.getId() == R.id.button2){
            intent.putExtra(EXTRA_MESSAGE,mButton2.getText().toString());
        }else{
            intent.putExtra(EXTRA_MESSAGE,mButton3.getText().toString());
        }

        */

        if (view.getId() == mButton1.getId()){
            intent.putExtra(EXTRA_MESSAGE,mButton1.getText().toString());
        }else if (view.getId() == mButton2.getId()){
            intent.putExtra(EXTRA_MESSAGE,mButton2.getText().toString());
        }else{
            intent.putExtra(EXTRA_MESSAGE,mButton3.getText().toString());
        }

        startActivity(intent);

    }
}
