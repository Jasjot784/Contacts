package com.jasjotsingh.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

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
    }
    public void callUs(){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        String phoneNumber = "+919463809630";
        String formattedNumber = String.format("tel: %s", phoneNumber);
        intent.setData(Uri.parse(formattedNumber));

        if (intent.resolveActivity(getPackageManager())!=null)
            startActivity(intent);
    }
}