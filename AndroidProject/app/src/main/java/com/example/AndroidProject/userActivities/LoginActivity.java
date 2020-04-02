package com.example.AndroidProject.userActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.AndroidProject.R;
import com.example.AndroidProject.dto.MemberDTO;
import com.example.AndroidProject.loginAsync.LoginRequestAsync;

public class LoginActivity extends AppCompatActivity {
    Button regBtn;
    Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("LOGIN");

        //로그인, 회원가입 버튼
        regBtn = findViewById(R.id.register_btn);
        loginBtn = findViewById(R.id.login_btn);

        //ID, PW EditText
        final EditText textUserId = findViewById(R.id.text_loginId);
        final EditText textUserPw = findViewById(R.id.text_loginPw);

        //회원가입버튼클릭이벤트
        regBtn.setOnClickListener(new IntentListener());
        //로그인버튼클릭이벤트
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MemberDTO loginDTO = new MemberDTO();
                loginDTO.setM_userid(textUserId.getText().toString());
                loginDTO.setM_userpw(textUserPw.getText().toString());

                LoginRequestAsync loginRequestAsync = new LoginRequestAsync(LoginActivity.this, loginDTO);
                loginRequestAsync.execute();
            }
        });

    }

    //Intent View Change Method Class
    class IntentListener implements View.OnClickListener {
        Intent intent;
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.register_btn) {
                intent = new Intent(LoginActivity.this, JoinActivity.class);
            }
            startActivity(intent);
        }
    }

    //토스트 메소드
    public void alert(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

}//end of class
