package com.example.AndroidProject.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.AndroidProject.R;

import static android.graphics.Color.*;

public class AccountActivity extends AppCompatActivity {
    RelativeLayout relProfile, relContents;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        setTitle("ACCOUNT");

        /*ViewGroup layout = (ViewGroup) findViewById(R.id.main_layout);
        layout.setOnClickListener(new OnClickListener() {*/
        relProfile = findViewById(R.id.profileChange);
        relProfile.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    relProfile.setBackgroundColor(Color.parseColor("#ff9494"));
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    relProfile.setBackgroundColor(Color.parseColor("#eb6868"));
                    Intent intent = new Intent(AccountActivity.this, ProfileActivity.class);
                    startActivity(intent);
                }
                return true;
            }
        });
        /*relProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });*/

        relContents = findViewById(R.id.likeContents);
        relContents.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    relContents.setBackgroundColor(Color.parseColor("#ff9494"));
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    relContents.setBackgroundColor(Color.parseColor("#eb6868"));
                }
                return true;
            }
        });


    }//end of onCreate
}//end of class
