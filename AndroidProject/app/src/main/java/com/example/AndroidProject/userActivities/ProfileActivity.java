package com.example.AndroidProject.userActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.AndroidProject.R;
import com.example.AndroidProject.ayncTask.RequestAsyncTask;
import com.example.AndroidProject.dto.MemberDTO;

public class ProfileActivity extends AppCompatActivity {
    Intent intent;
    SharedPreferences sharedPreferences;
    Spinner updateInterest;
    String interest;
    Button saveBtn, cancelBtn;
    EditText profile_nickname, profile_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle("PROFILE");

        sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);

        saveBtn = findViewById(R.id.profSave_btn);
        cancelBtn = findViewById(R.id.profCancel_btn);
        updateInterest = findViewById(R.id.profile_inter);

        profile_nickname = findViewById(R.id.profile_nickname);
        profile_email = findViewById(R.id.profile_email);
        interest = updateInterest.getSelectedItem().toString();

        profile_nickname.setText(sharedPreferences.getString("m_nickname", ""));
        profile_email.setText(sharedPreferences.getString("m_email", ""));


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MemberDTO updateDTO = new MemberDTO();
                updateDTO.setM_nickname(profile_nickname.getText().toString());
                updateDTO.setM_email(profile_email.getText().toString());
                updateDTO.setM_interest(interest);
                updateDTO.setM_userid(sharedPreferences.getString("m_userid",""));
                RequestAsyncTask updateAsync = new RequestAsyncTask(ProfileActivity.this, updateDTO);
                updateAsync.execute();
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
