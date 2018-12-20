package com.derrick.park.assignment3_contacts.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.derrick.park.assignment3_contacts.R;

public class AddContact extends AppCompatActivity {

    private EditText mEtAddName;
    private EditText mEtAddPhone;
    private Button mBtnAddContact;

    boolean change = false;

    boolean nameOk = false;
    boolean phoneOK = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        mEtAddName = findViewById(R.id.et_add_name);

        mEtAddPhone = findViewById(R.id.et_add_phone);

        mBtnAddContact = findViewById(R.id.btn_add_contact);


        mEtAddName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            // ask how to use some regex to check the name
            @Override
            public void afterTextChanged(Editable s) {
                int indexOfSpace = s.toString().indexOf(" ");
                if (indexOfSpace != -1 && indexOfSpace != s.toString().length() -1){
                    if (Character.isUpperCase(s.toString().charAt(0)) && Character.isUpperCase(s.toString().charAt(indexOfSpace+1))){
                            nameOk=true;
                    }
                }

                if (nameOk && phoneOK){
                    mBtnAddContact.setEnabled(true);
                }
            }
        });


        mEtAddPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int size = s.toString().length();
                if (size >= 10) {
                    phoneOK = true;
                }

                if (nameOk && phoneOK){
                    mBtnAddContact.setEnabled(true);
                }
            }
        });



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