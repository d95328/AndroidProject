package com.example.AndroidProject.ayncTask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.AndroidProject.MainActivity;
import com.example.AndroidProject.dto.MemberDTO;
import com.example.AndroidProject.userActivities.AccountActivity;
import com.example.AndroidProject.userActivities.JoinActivity;
import com.example.AndroidProject.userActivities.LoginActivity;
import com.example.AndroidProject.userActivities.ProfileActivity;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URLConnection;

public class RequestAsyncTask extends AsyncTask<String, Void, String> {

    Activity activity;
    ProgressDialog progressDialog;
    boolean isConnection = false;
    MemberDTO memberDTO;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedEditor;

    public RequestAsyncTask() {
    }

    public RequestAsyncTask(Activity activity, MemberDTO memberDTO) {
        this.activity = activity;
        this.memberDTO = memberDTO;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(activity, "Loading", "Data Loading...");
    }

    @Override
    protected String doInBackground(String... params) {
        InputStream inputStream;
        StringBuffer sb = new StringBuffer();
        String jsonString = "";
        URLConnection url = null;
//        String LoginUrl = Common.LOGIN_URL + "?m_userid=" + loginDTO.getM_userid() + "&m_userpw=" + loginDTO.getM_userpw();

        try {
            Log.i("Login doInBackground", "------LoginDoInStart-------");

            if(activity instanceof LoginActivity) {
                //getConnection 메소드를 이용해 HttpURLConnection 인스턴스를 취득 및 서블릿으로 향하는 URL 파라미터 입력
                url = HttpURLConnction.getConnection(Common.LOGIN_URL);
                //입력된 ID,PW를 담아줄 JSONObject 객체 생성
                JSONObject jsonObject = new JSONObject();
                //화면에서 입력된 사용자의 id,pw를 jsonObject형식으로 담아줌
                jsonObject.put("m_userid", memberDTO.getM_userid());
                jsonObject.put("m_userpw", memberDTO.getM_userpw());

                //서블릿으로 보내기위해 jsonObject를 OutputStream에 넣어서 보내주는 처리
                StreamController.OutputController(url, jsonObject);
            } else if(activity instanceof JoinActivity) {
                url = HttpURLConnction.getConnection(Common.JOIN_URL);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("m_userid", memberDTO.getM_userid());
                jsonObject.put("m_userpw", memberDTO.getM_userpw());
                jsonObject.put("m_nickname", memberDTO.getM_nickname());
                jsonObject.put("m_email", memberDTO.getM_nickname());
                jsonObject.put("m_birth", memberDTO.getM_birth());
                jsonObject.put("m_gender", memberDTO.getM_gender());
                jsonObject.put("m_interest", memberDTO.getM_interest());

                StreamController.OutputController(url, jsonObject);
            } else if(activity instanceof ProfileActivity) {
                url = HttpURLConnction.getConnection(Common.UPDATE_URL);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("m_nickname", memberDTO.getM_nickname());
                jsonObject.put("m_email", memberDTO.getM_email());
                jsonObject.put("m_interest", memberDTO.getM_interest());
                jsonObject.put("m_userid", memberDTO.getM_userid());

                //정보수정하는 값으로 sharedPreferences를 수정!!
                sharedPreferences = activity.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                sharedEditor = sharedPreferences.edit();
                sharedEditor.putString("m_nickname", memberDTO.getM_nickname());
                sharedEditor.putString("m_interest", memberDTO.getM_interest());
                sharedEditor.putString("m_email", memberDTO.getM_email());

                StreamController.OutputController(url, jsonObject);
            }

            //서블릿에서 보낸 response를 inputStream에 받는 처리
            jsonString = StreamController.InputController(url);

            if(activity instanceof LoginActivity) {
                //sharedPreferences 사용
                //sharedPreferences, sharedEditor 생성
                sharedPreferences = activity.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                sharedEditor = sharedPreferences.edit();
                JSONObject result = new JSONObject(jsonString);

                sharedEditor.putString("m_userid", result.getString("m_userid"));
                sharedEditor.putString("m_nickname", result.getString("m_nickname"));
                sharedEditor.putString("m_interest", result.getString("m_interest"));
                sharedEditor.putString("m_gender", result.getString("m_gender"));
                sharedEditor.putString("m_email", result.getString("m_email"));
                sharedEditor.putString("m_birth", result.getString("m_birth"));
                sharedEditor.putString("m_joindate", result.getString("m_joindate"));

                jsonString = result.getString("result");
            } else if(activity instanceof JoinActivity) {
                JSONObject result = new JSONObject(jsonString);
                jsonString = result.getString("result");
            } else if(activity instanceof ProfileActivity) {
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
    }

    @Override
    protected void onPostExecute(String result) {
        Intent intent;
        super.onPostExecute(result);
        progressDialog.dismiss();
        try {
            if(activity instanceof LoginActivity) {
                if (result.equals("success")) {
                    //서버로부터 result 키를 가진 값이 success일 시
                    //SharedPreferences editor commit 해주기(doIn에서 commit을 해줄경우 로그인이 실패해도 받아온 JSONObject 값이 editor에 저장됨)
                    sharedEditor.commit();
                    //Toast 띄워주고 메인클래스로 이동
                    alert(sharedPreferences.getString("m_nickname", "") + "님 환영합니다!");
                    intent =  new Intent(activity, MainActivity.class);
                    activity.startActivity(intent);
                    activity.finish();
                } else {
                    alert("로그인 정보가 일치하지 않습니다.");
                }

            } else if (activity instanceof JoinActivity) {
                if (result.equals("success")) {
                    //서버로부터 result 키를 가진 값이 success일 시 토스트창과 함께 메인클래스로 이동
                    alert("회원가입을 축하합니다!");
                    intent = new Intent(activity, MainActivity.class);
                    activity.startActivity(intent);
                    activity.finish();
                } else {
                    alert("회원가입처리가 되지 않았습니다.");
                }
            } else if (activity instanceof ProfileActivity) {
                if (result.equals("success")) {
                    //수정해준 sharedEditor를 커밋해준다.
                    sharedEditor.commit();
                    //서버로부터 result 키를 가진 값이 success일 시 토스트창과 함께 AccountActivity로 이동
                    alert("회원정보수정이 완료되었습니다.");
                    intent = new Intent(activity, AccountActivity.class);
                    activity.startActivity(intent);
                    activity.finish();
                } else {
                    alert("정보수정에 실패했습니다.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //토스트 메소드
    public void alert(String msg) {
        Toast.makeText(activity, msg, Toast.LENGTH_LONG).show();
    }
}
