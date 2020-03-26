package com.example.AndroidProject.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.AndroidProject.R;

public class AccountActivity extends AppCompatActivity {
    RelativeLayout relLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        setTitle("ACCOUNT");

        /*ViewGroup layout = (ViewGroup) findViewById(R.id.main_layout);
        layout.setOnClickListener(new OnClickListener() {*/
        relLayout = findViewById(R.id.profileChange);
        relLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }//end of onCreate
}//end of class
