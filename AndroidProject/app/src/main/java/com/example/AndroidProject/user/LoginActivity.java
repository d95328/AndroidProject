package com.example.AndroidProject.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.AndroidProject.MainActivity;
import com.example.AndroidProject.R;

public class LoginActivity extends AppCompatActivity {
    Button regBtn;
    Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("LOGIN");

        //회원가입버튼클릭이벤트
        regBtn = findViewById(R.id.register_btn);
        regBtn.setOnClickListener(new IntentListener());

        //로그인버튼클릭이벤트
        loginBtn = findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(new IntentListener());

    }

    //Intent View Change Method Class
    class IntentListener implements View.OnClickListener {
        Intent intent;
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.register_btn) {
                intent = new Intent(LoginActivity.this, JoinActivity.class);
            } else if (v.getId() == R.id.login_btn) {
                intent = new Intent(LoginActivity.this, MainActivity.class);
                alert("로그인기능이 구현되지 않았습니다.");
            }
            startActivity(intent);
        }
    }

    //토스트 메소드
    public void alert(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

}//end of class
