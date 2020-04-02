package com.example.AndroidProject;

import android.content.Intent;
import android.os.Bundle;

import com.example.AndroidProject.userActivities.AccountActivity;
import com.example.AndroidProject.userActivities.JoinActivity;
import com.example.AndroidProject.userActivities.LoginActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    EditText webPage_src;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //툴바(액션바) 객체 생성
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*RecyclerView recyclerView = findViewById(R.id.rvMain);*/


    }//end of onCreatie()

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
        }
        return super.onOptionsItemSelected(item);
    }//end of onOptionItemSelected()

}//end of class
