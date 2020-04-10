package com.example.AndroidProject.ayncTask;

import android.util.Log;

import java.net.HttpURLConnection;
import java.net.URL;

public class HttpURLConnction {

    //HttpURLConnection 인스턴스 메소드
    public static HttpURLConnection getConnection(String actionUrl) {
        String mUrl = actionUrl;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(mUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST"); //송수신 방식선택 POST
            urlConnection.setDoOutput(true);        //안드로이드->서버 송신 허용
            urlConnection.setDoInput(true);         //서버->안드로이드 수신 허용
            urlConnection.setUseCaches(false);      //캐시사용 X
            urlConnection.setDefaultUseCaches(false);
            urlConnection.setReadTimeout(100000); //10초동안 서버반응 오지않으면 Exception 처리
            urlConnection.setConnectTimeout(100000); //접속요청시간이 10초가 넘으면 접속실패 Exception
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("HttpURLConnection 에러", e.getMessage());
        }
        return urlConnection;
    }

}
