package com.jasjotsingh.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    static final int REQUEST_SELECT_CONTACT = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.buttonContact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callUs();
            }
        });
        findViewById(R.id.buttonPickContact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectContact();
            }
        });
    }
    public void callUs(){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        String phoneNumber = "+919463809630";
        String formattedNumber = String.format("tel: %s", phoneNumber);
        intent.setData(Uri.parse(formattedNumber));

        if (intent.resolveActivity(getPackageManager())!=null)
            startActivity(intent);
    }
    public void selectContact() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_SELECT_CONTACT);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SELECT_CONTACT && resultCode == RESULT_OK) {
            Uri contactUri = data.getData();
            // Do something with the selected contact at contactUri
            Toast.makeText(this, contactUri.toString(), Toast.LENGTH_SHORT).show();
            Log.d(TAG, "onActivityResult: "+contactUri);

        }
    }
}