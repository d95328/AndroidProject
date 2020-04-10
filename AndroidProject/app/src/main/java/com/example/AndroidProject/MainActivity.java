package com.example.AndroidProject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.AndroidProject.dto.MemberDTO;
import com.example.AndroidProject.userActivities.AccountActivity;
import com.example.AndroidProject.userActivities.JoinActivity;
import com.example.AndroidProject.userActivities.LoginActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor sharedEditor;
    MemberDTO dto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //툴바(액션바) 객체 생성
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /*RecyclerView recyclerView = findViewById(R.id.rvMain);*/

//        sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
//        Log.i("userinfo", sharedPreferences.getString("m_userid", ""));



    }//end of onCreate()

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        if ( !sharedPreferences.getString("m_userid","").equals("")) {
            //로그인상태 시 메뉴상태
            menu.getItem(0).setVisible(false);
            menu.getItem(1).setVisible(false);
            menu.getItem(2).setVisible(true);
            menu.getItem(3).setVisible(true);
        } else if ( sharedPreferences.getString("m_userid","").equals("")){
            //비로그인상태 시 메뉴상태
            menu.getItem(0).setVisible(true);
            menu.getItem(1).setVisible(true);
            menu.getItem(2).setVisible(false);
            menu.getItem(3).setVisible(false);
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;

        if (id == R.id.loginView_btn) {
            intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        } else if (id == R.id.joinView_btn) {
            intent = new Intent(this, JoinActivity.class);
            startActivity(intent);
        }  else if (id == R.id.accountView_btn) {
            intent = new Intent(this, AccountActivity.class);
            startActivity(intent);
        } else if (id == R.id.logout_btn) {
            sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
            sharedEditor = sharedPreferences.edit();
            sharedEditor.clear();
            sharedEditor.commit();
            intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }//end of onOptionItemSelected()

    @Override
    protected void onResume() {
        super.onResume();
        Menu menu = null;
        sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        //로그인정보가 있을 때의 view처리
        if(!sharedPreferences.getString("m_userid","").equals("")) {
            setTitle(sharedPreferences.getString("m_nickname", "") + "님 환영합니다.");
        } else if(sharedPreferences.getString("m_userid","").equals("")){
            setTitle("LOVE BAND");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sharedEditor.clear();
    }

}//end of class
