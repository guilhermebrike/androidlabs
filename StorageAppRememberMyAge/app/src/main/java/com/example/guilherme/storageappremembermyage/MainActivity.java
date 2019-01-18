package com.example.guilherme.storageappremembermyage;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mAgeTextView;
    private int age;

    //Storing information using sharing preferences.
    private SharedPreferences mPreferences;

    // with the values bellow, is possible to access the same information stored within the app, on other apps
    private final String sharedPrefFile = BuildConfig.APPLICATION_ID;
    public static final String AGE_KEY = "AGE_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAgeTextView = findViewById(R.id.age_tv);
        mPreferences = getSharedPreferences(sharedPrefFile,MODE_PRIVATE);

        //reading the data
        age = mPreferences.getInt(AGE_KEY,0);
        mAgeTextView.setText(Integer.toString(age));

    }

    // we are going to store our data in the onPause stage
    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(AGE_KEY,age);
        //syncronous commit;
        //editor.commit();
        //async commit
        editor.apply();
    }

    public void makeMeYounger(View view) {
        age = Integer.parseInt(mAgeTextView.getText().toString());
        if (age > 0){
            age--;
        }
        mAgeTextView.setText(Integer.toString(age));
    }

    public void makeMeOlder(View view) {
        age = Integer.parseInt(mAgeTextView.getText().toString());
        age++;
        mAgeTextView.setText(Integer.toString(age));
    }
}
