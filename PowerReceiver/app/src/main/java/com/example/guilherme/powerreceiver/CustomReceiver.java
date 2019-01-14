package com.example.guilherme.powerreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {

    private static final String ACTION_CUSTOM_BROADCAST =
            BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";

    @Override
    public void onReceive(Context context, Intent intent) {
            String intentAction = intent.getAction();
            if (intentAction != null) {
              String message = "Unknown intent action";
                switch (intentAction) {
                    case Intent.ACTION_POWER_CONNECTED:
                        message ="Power connected!";
                        break;
                    case Intent.ACTION_POWER_DISCONNECTED:
                        message = "Power disconnected!";
                        break;
                    case ACTION_CUSTOM_BROADCAST:
                        message = "Custom Broadcast received!";
                        break;
                    case Intent.ACTION_HEADSET_PLUG:
                        message = "A Headset Has been plugged";
                        break;
                }
                Toast.makeText(context,message,Toast.LENGTH_LONG).show();
            }
    }
}
