package com.example.AndroidProject.ayncTask;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLConnection;

public class StreamController {
    //OutpuStream 메소드
    public static void OutputController(URLConnection url, JSONObject jsonObject) {
        try {
            //url에 쓰기작업할 정보를 outputStream으로 저장
            OutputStream outputStream = new BufferedOutputStream(url.getOutputStream());
            //jsonObject 타입으로 담긴 정보를 String으로 변환
            String updateData = jsonObject.toString();
            //String으로 변환된 변수를 byte타입으로만 전송되는 outputStream에 이용하기 위해 getBytes를 이용해서 Byte타입으로 형변환.
            outputStream.write(updateData.getBytes("UTF-8"));
            //아웃풋스트림 작성 (flush() 메소드는 출력 스트림과 버퍼된 출력 바이트를 강제로 쓰게 한다. )
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("OutputStream 에러", e.getMessage());
        }
    }

    public static String  InputController(URLConnection url) {
        InputStream inputStream;
        StringBuffer sb = new StringBuffer();
        String jsonString = "";
        try {
            //Buffered가 들어간 객체는 성능향상에 사용됨
            inputStream = new BufferedInputStream(url.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String line = null;
            while ( (line = reader.readLine()) != null ) {
                sb.append(line + "\n");
            }
            jsonString = sb.toString();
            inputStream.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
