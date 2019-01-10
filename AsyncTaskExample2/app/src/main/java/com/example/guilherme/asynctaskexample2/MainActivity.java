package com.example.guilherme.asynctaskexample2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText mTimeET;
    private TextView mResultTV;
    private Button mRunBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTimeET = findViewById(R.id.time_et);
        mResultTV = findViewById(R.id.result_tv);
        mRunBtn = findViewById(R.id.run_btn);
        mRunBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SleepAsyncTask().execute(mTimeET.getText().toString());

            }
        });

    }

    private class SleepAsyncTask extends AsyncTask<String, String, String> {

        private String resp;
        ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... params) {
            // worker thread
            try {
                int timems = Integer.parseInt(params[0]);
                for(int i = 1; i <= timems; i++) {
                    Thread.sleep(1000);
                    publishProgress("Remaining... " + (timems - i)); // Calls onProgressUpdate()
                    resp = "Slept for " + params[0] + " seconds";
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                resp = e.getMessage();
            } catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return resp;
        }


        @Override
        protected void onPostExecute(String result) {
            // Main thread
            // execution of result of Long time consuming operation
            progressDialog.dismiss();
            mResultTV.setText(result);
        }


        @Override
        protected void onPreExecute() {
            // Main thread
            progressDialog = ProgressDialog.show(MainActivity.this,
                    "ProgressDialog",
                    "Wait for "+ mTimeET.getText().toString()+ " seconds");
        }


        @Override
        protected void onProgressUpdate(String... text) {
            // Main thread
            progressDialog.setMessage(text[0]);
            mResultTV.setText(text[0]);
        }
    }


}