package com.example.phonecall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button mDialButton = (Button) findViewById(R.id.btn_dial);

        final EditText mPhoneNoEt = (EditText) findViewById(R.id.et_phone_no);

        mDialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNo = mPhoneNoEt.getText().toString();
                if(!TextUtils.isEmpty(phoneNo)) {
                    String dial = "tel:" + phoneNo;
                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
                }else {
                    Toast.makeText(MainActivity.this, "Enter a phone number", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button sendMessageBtn = (Button) findViewById(R.id.btn_send_message);
        final EditText messagetEt = (EditText) findViewById(R.id.et_message);
        sendMessageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = messagetEt.getText().toString();
                String phoneNo = mPhoneNoEt.getText().toString();
                if(!TextUtils.isEmpty(message) && !TextUtils.isEmpty(phoneNo)) {
                    Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + phoneNo));
                    smsIntent.putExtra("sms_body", message);
                    startActivity(smsIntent);
                }
            }
        });
    }
}