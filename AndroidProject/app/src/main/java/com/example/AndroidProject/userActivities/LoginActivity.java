package com.example.AndroidProject.userActivities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.AndroidProject.MainActivity;
import com.example.AndroidProject.R;
import com.example.AndroidProject.dto.MemberDTO;
import com.example.AndroidProject.ayncTask.RequestAsyncTask;

public class LoginActivity extends AppCompatActivity {
    Button regBtn;
    Button loginBtn;
    CheckBox autoLogin;
    SharedPreferences sharedPreferences;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("LOGIN");

        //로그인, 회원가입 버튼
        regBtn = findViewById(R.id.register_btn);
        loginBtn = findViewById(R.id.login_btn);

        //ID, PW EditText
        final EditText loginUserid = findViewById(R.id.login_userid);
        final EditText loginUserPw = findViewById(R.id.login_userpw);

        //자동로그인 구현
//        autoLogin = findViewById(R.id.autoLogin); //체크박스 체크여부

        //sharedPreferences 가 존재하면! 메인액티비티로, 없다면 로그인페이지 그대로 띄우기
//        if(getSharedPreferences("autoLogin", MODE_PRIVATE).getString("autoLoginTrue","").equals("true")) {
            //sharedPreferences에 아이디 정보가 있을 시에
//            sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
//            alert(sharedPreferences.getString("m_nickname", "") + "님 환영합니다!");
//            intent = new Intent(this, MainActivity.class);
//            startActivity(intent);
//            finish();
//        } else if(getSharedPreferences("userInfo", MODE_PRIVATE) == null) {
            //sharedPreferences에 정보 없을 시 LoginActivity 실행되고 처리할 내용 이곳에
            //회원가입버튼클릭이벤트
            regBtn.setOnClickListener(new IntentListener());
            //로그인버튼클릭이벤트
            loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MemberDTO loginDTO = new MemberDTO();
//                    if(autoLogin.isChecked()) {
                        loginDTO.setM_userid(loginUserid.getText().toString());
                        loginDTO.setM_userpw(loginUserPw.getText().toString());
//                        sharedPreferences = getSharedPreferences("autoLogin", MODE_PRIVATE);
//                        SharedPreferences.Editor sharedEditor =  sharedPreferences.edit();
//                        sharedEditor.putBoolean("autoLoginTrue", true);
//                    } else {
//                        loginDTO.setM_userid(loginUserid.getText().toString());
//                        loginDTO.setM_userpw(loginUserPw.getText().toString());
//                    }
                    RequestAsyncTask loginRequestAsync = new RequestAsyncTask(LoginActivity.this, loginDTO);
                    loginRequestAsync.execute();
                }
            });
//        }
        ImageButton imgBtn = findViewById(R.id.logoImg);
        imgBtn.setOnClickListener(new IntentListener());
    }

    //Intent View Change Method Class
    class IntentListener implements View.OnClickListener {
        Intent intent;
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.register_btn) {
                intent = new Intent(LoginActivity.this, JoinActivity.class);
            } else if(v.getId() == R.id.logoImg) {
                intent = new Intent(LoginActivity.this, MainActivity.class);
            }
            startActivity(intent);
            finish();
        }
    }

    //토스트 메소드
    public void alert(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

}//end of class
