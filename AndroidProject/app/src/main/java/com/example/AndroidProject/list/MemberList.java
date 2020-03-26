package com.example.AndroidProject.list;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.AndroidProject.R;
import com.example.AndroidProject.ayncTask.HttpAsyncTask;

public class MemberList extends AppCompatActivity {
    EditText webpage_src;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_list);

        Button memberListBtn = findViewById(R.id.listBtn);
        memberListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webpage_src = findViewById(R.id.webpage_area);

                HttpAsyncTask asyncTask = new HttpAsyncTask(MemberList.this, webpage_src);
                asyncTask.execute();

            }
        });
    }//end of onCreate
}//end of Class
