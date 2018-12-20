package com.derrick.park.assignment3_contacts.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.derrick.park.assignment3_contacts.R;

public class AddContact extends AppCompatActivity {

    private EditText mEtAddName;
    private EditText mEtAddPhone;
    private Button mBtnAddContact;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        mEtAddName = findViewById(R.id.et_add_name);

        mEtAddPhone = findViewById(R.id.et_add_phone);

        mBtnAddContact = findViewById(R.id.btn_add_contact);
    }


    public void returnNewContact(View view) {
        String mName = mEtAddName.getText().toString();
        String mPhone = mEtAddPhone.getText().toString();

        Intent reply_intent = new Intent(this, MainActivity.class);

        Bundle extras = new Bundle();

        extras.putString("EXTRA_CONTACT_NAME",mName);
        extras.putString("EXTRA_CONTACT_PHONE",mPhone);

        reply_intent.putExtras(extras);

        setResult(RESULT_OK,reply_intent);

        finish();
    }
}