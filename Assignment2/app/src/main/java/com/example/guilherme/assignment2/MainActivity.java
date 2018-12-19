package com.example.guilherme.assignment2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private Spinner mSpinner;

    private ImageView mMovie_poster;

    private TextView mMovie_information;

    private final String TAG ="MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSpinner = findViewById(R.id.movies_spinner);
        mMovie_information = findViewById(R.id.movie_information);
        mMovie_poster = findViewById(R.id.movie_poster);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //transform the string, that i got from the spinner.
                String resource_text = mSpinner.getSelectedItem().toString().toLowerCase().replaceAll(" ","_");

                int idPoster = getResources().getIdentifier(resource_text,"drawable",getPackageName());

                mMovie_poster.setImageResource(idPoster); // get the image id and set into an image view

                // this is how i open raw files of the  file
                int idMovieInformationText = getResources().getIdentifier(resource_text,"raw",getPackageName());

                Scanner in = new Scanner(getResources().openRawResource(idMovieInformationText));
                String result = "";
                while(in.hasNext()){
                    result += in.nextLine();
                }
                in.close();
                mMovie_information.setText(result);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
