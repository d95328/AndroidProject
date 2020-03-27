package com.example.AndroidProject.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.AndroidProject.MainActivity;
import com.example.AndroidProject.R;

import static android.graphics.Color.*;

public class AccountActivity extends AppCompatActivity {
    RelativeLayout relProfile, relLike, relMy, relGrade, relSecure;
    Context context;
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

        relProfile.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    relProfile.setBackgroundColor(Color.parseColor("#ff9494"));
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    relProfile.setBackgroundColor(Color.parseColor("#eb6868"));
                    intent = new Intent(AccountActivity.this, ProfileActivity.class);
                    startActivity(intent);
                }
                return true;
            }
        });

        relLike.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    relLike.setBackgroundColor(Color.parseColor("#ff9494"));
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    relLike.setBackgroundColor(Color.parseColor("#eb6868"));
                    intent = new Intent(AccountActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                return true;
            }
        });

        relMy.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    relMy.setBackgroundColor(Color.parseColor("#ff9494"));
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    relMy.setBackgroundColor(Color.parseColor("#eb6868"));
                    intent = new Intent(AccountActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                return true;
            }
        });

        relGrade.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    relGrade.setBackgroundColor(Color.parseColor("#ff9494"));
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    relGrade.setBackgroundColor(Color.parseColor("#eb6868"));
                    intent = new Intent(AccountActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                return true;
            }
        });

        relSecure.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    relSecure.setBackgroundColor(Color.parseColor("#ff9494"));
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    relSecure.setBackgroundColor(Color.parseColor("#eb6868"));
                    intent = new Intent(AccountActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                return true;
            }
        });

        /*touchLayout(relLike);*/
    }//end of onCreate

   /* public void touchLayout(RelativeLayout relativeLayout) {
        RelativeLayout.OnTouchListener onTouchListener = new RelativeLayout.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    relativeLayout.setBackgroundColor(Color.parseColor("#ff9494"));
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    relativeLayout.setBackgroundColor(Color.parseColor("#eb6868"));
                    intent = new Intent(AccountActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                return true;
            }
        };
    }//end of method()*/

}//end of class
