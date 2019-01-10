package com.example.guilherme.asynctasksimpleexample;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void, String, String> {

    private WeakReference<TextView> mTextView;
    private ProgressDialog progress;

    public SimpleAsyncTask(MainActivity activity,TextView tv) {
        mTextView = new WeakReference<>(tv);
        progress = new ProgressDialog(activity);
    }

    @Override
    protected void onPreExecute() {
        progress.setMessage("Doing something, please wait.");
        progress.show();
    }

    @Override
    protected String doInBackground(Void... voids) {
        Random r = new Random();
        int n = r.nextInt(20);
        // n will make i wait a max of 20 seconds
        try {
            for(int i = 1; i <= n; i++) {
                Thread.sleep(1000);
                publishProgress("Remaining... " + (n - i)); // Calls onProgressUpdate()
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Awake at last after sleeping for " + n + " Seconds!";
    }

    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
        if (progress.isShowing()) {
            progress.dismiss();
        }
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        progress.setMessage(values[0]);
    }


}