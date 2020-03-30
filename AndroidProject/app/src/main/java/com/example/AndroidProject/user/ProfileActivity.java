package com.example.AndroidProject.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.AndroidProject.R;

public class ProfileActivity extends AppCompatActivity {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle("PROFILE");

        Button saveBtn = findViewById(R.id.profSave_btn);
        Button cancelBtn = findViewById(R.id.profCancel_btn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(ProfileActivity.this, AccountActivity.class);
                alert("정보수정이 완료되었습니다.");
                startActivity(intent);
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert("정보수정을 취소하셨습니다.");
                finish();
            }
        });

    }//end of onCreate

    //토스트 메소드
    public void alert(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

}//end of class
