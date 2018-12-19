package com.example.guilherme.activitiesandintents;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mFirstEditText;

    private static final int TEXT_REQUEST_CODE = 1;

    public static final String EXTRA_MESSAGE="EXTRA_MESSAGE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirstEditText = findViewById(R.id.first_et);
    }

    public void launchSecondActivity(View view) {

        Intent intent = new Intent(this,SecondActivity.class);
        String message = mFirstEditText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);

        // start the activity without a result
        //startActivity(intent);


        // start the activity for a result
        startActivityForResult(intent,TEXT_REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == TEXT_REQUEST_CODE){
            if (resultCode == RESULT_OK){
                String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);
                mFirstEditText.setText(reply);
                Toast.makeText(this,reply,Toast.LENGTH_LONG).show();

            }
        }

    }
}
