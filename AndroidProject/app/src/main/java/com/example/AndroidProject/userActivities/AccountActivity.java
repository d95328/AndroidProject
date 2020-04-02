package com.example.AndroidProject.userActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.AndroidProject.MainActivity;
import com.example.AndroidProject.R;

public class AccountActivity extends AppCompatActivity {
    RelativeLayout relProfile, relLike, relMy, relGrade, relSecure;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        setTitle("ACCOUNT");

        /*ViewGroup layout = (ViewGroup) findViewById(R.id.main_layout);
        layout.setOnClickListener(new OnClickListener() {*/
        relProfile = findViewById(R.id.relProfile);
        relLike = findViewById(R.id.relLike);
        relMy = findViewById(R.id.relMy);
        relGrade = findViewById(R.id.relGrage);
        relSecure = findViewById(R.id.relSecure);

        relProfile.setOnTouchListener(new IntentListener());
        relLike.setOnTouchListener(new IntentListener());
        relMy.setOnTouchListener(new IntentListener());
        relGrade.setOnTouchListener(new IntentListener());
        relSecure.setOnTouchListener(new IntentListener());

    }//end of onCreate



    //IntentListener class
    private class IntentListener implements RelativeLayout.OnTouchListener {
        Intent intent;
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (v.getId() == R.id.relProfile) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    relProfile.setBackgroundColor(Color.parseColor("#ff9494"));
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    relProfile.setBackgroundColor(Color.parseColor("#eb6868"));
                    intent = new Intent(AccountActivity.this, ProfileActivity.class);
                    startActivity(intent);
                }
            } else if (v.getId() == R.id.relLike) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    relLike.setBackgroundColor(Color.parseColor("#ff9494"));
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    relLike.setBackgroundColor(Color.parseColor("#eb6868"));
                    intent = new Intent(AccountActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            } else if (v.getId() == R.id.relMy) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    relMy.setBackgroundColor(Color.parseColor("#ff9494"));
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    relMy.setBackgroundColor(Color.parseColor("#eb6868"));
                    intent = new Intent(AccountActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            } else if (v.getId() == R.id.relGrage) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    relGrade.setBackgroundColor(Color.parseColor("#ff9494"));
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    relSecure.setBackgroundColor(Color.parseColor("#eb6868"));
                    intent = new Intent(AccountActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            } else if (v.getId() == R.id.relSecure) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    relSecure.setBackgroundColor(Color.parseColor("#ff9494"));
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    relSecure.setBackgroundColor(Color.parseColor("#eb6868"));
                    intent = new Intent(AccountActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
            return true;
        }
    }//end of IntentListener class

    //토스트 메소드
    public void alert(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

}//end of class
