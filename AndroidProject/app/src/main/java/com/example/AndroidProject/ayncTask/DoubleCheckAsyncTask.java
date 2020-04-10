package com.example.AndroidProject.ayncTask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.AndroidProject.dto.MemberDTO;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLConnection;

public class DoubleCheckAsyncTask extends AsyncTask<String, Void, String> {
    Activity activity;
    boolean isConnection = false;
    MemberDTO memberDTO;
    ProgressDialog progressDialog;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedEditor;

    //default 생성자
    public  DoubleCheckAsyncTask() {

    }

    public DoubleCheckAsyncTask(Activity activity, MemberDTO memberDTO) {
        this.activity = activity;
        this.memberDTO = memberDTO;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(activity, "Loading", "Data Loading...");
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        InputStream inputStream;
        StringBuffer sb = new StringBuffer();
        String jsonString = "";
        URLConnection url = null;

        try {
            Log.i("DoubleCheck doInBack", "------DoubleCheckStart-------");
            if(memberDTO.getM_userid() != null) {
                //아이디중복확인요청
                url = HttpURLConnction.getConnection(Common.IDCHECK_URL);

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("m_userid", memberDTO.getM_userid());

                StreamController.OutputController(url, jsonObject);
            } else if (memberDTO.getM_email() != null) {
                //이메일중복확인요청
                url = HttpURLConnction.getConnection(Common.EMAILCHECK_URL);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("m_email", memberDTO.getM_email());

                StreamController.OutputController(url, jsonObject);
            } else if (memberDTO.getM_nickname() != null) {
                url = HttpURLConnction.getConnection(Common.NICKNAME_URL);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("m_nickname", memberDTO.getM_nickname());

                StreamController.OutputController(url, jsonObject);
            }

            jsonString = StreamController.InputController(url);

            if(memberDTO.getM_userid() != null) {
                JSONObject result = new JSONObject(jsonString);
                jsonString = result.getString("result");
            } else if(memberDTO.getM_email() != null) {
                JSONObject result = new JSONObject(jsonString);
                jsonString = result.getString("result");
            } else if(memberDTO.getM_nickname() != null) {
                JSONObject result = new JSONObject(jsonString);
                jsonString = result.getString("result");
            }

            Log.i("jsonResult", jsonString);
            isConnection = true;
        } catch (Exception e) {
            e.printStackTrace();
            isConnection = false;
        }
        return jsonString;
    }//end of doInBackground

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        progressDialog.dismiss();
        try {
            if(memberDTO.getM_userid() != null) {
                if (result.equals("success")) {
                } else {
                }
            } else if(memberDTO.getM_email() != null) {
                if (result.equals("success")) {
                } else {
                }
            } else if(memberDTO.getM_nickname() != null) {
                if(result.equals("success")) {
//                    alert("사용가능한 닉네임입니다.");
                } else {
//                    alert("이미 사용중인 닉네임입니다.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("exception", e+"중복체크onPost에러");
        }
    }//end of onPost

    //토스트 메소드
    public void alert(String msg) {
        Toast.makeText(activity, msg, Toast.LENGTH_LONG).show();
    }

}//end of class
