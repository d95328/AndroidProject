package com.example.AndroidProject.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.AndroidProject.MainActivity;
import com.example.AndroidProject.R;

public class JoinActivity extends AppCompatActivity {
    Button signBtn, cancelBtn, emailBtn, termsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        setTitle("JOIN");

        signBtn = findViewById(R.id.sign_btn);
        cancelBtn = findViewById(R.id.cancel_btn);
        emailBtn = findViewById(R.id.email_btn);
        termsBtn = findViewById(R.id.terms_btn);

        signBtn.setOnClickListener(new IntentListener());
        cancelBtn.setOnClickListener(new IntentListener());
        emailBtn.setOnClickListener(new IntentListener());
        termsBtn.setOnClickListener(new IntentListener());

    }
    class IntentListener implements  View.OnClickListener {
        Intent intent;

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.sign_btn) {
                intent = new Intent(JoinActivity.this, MainActivity.class);
                alert("회원가입 기능이 구현되지 않았습니다.");
            } else if (v.getId() == R.id.cancel_btn) {
                intent = new Intent(JoinActivity.this, MainActivity.class);
                alert("가입을 취소하셨습니다.");
            } else if (v.getId() == R.id.email_btn) {
                alert("중복되지 않았습니다.");
                return;
            } else if (v.getId() == R.id.terms_btn) {
                alert("약관을 추가해야 합니다.");
                return;
            }
            startActivity(intent);
        }
    }//end of Listener

    //토스트 메소드
    public void alert(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

}//end of class
