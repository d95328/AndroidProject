package com.example.AndroidProject.loginAsync;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.AndroidProject.ayncTask.Common;
import com.example.AndroidProject.dto.MemberDTO;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginRequestAsync extends AsyncTask<String, Void, String> {

    Activity activity;
    ProgressDialog progressDialog;
    boolean isConnection = false;
    MemberDTO loginDTO;

    public LoginRequestAsync() {
    }

    public LoginRequestAsync(Activity activity, MemberDTO loginDTO) {
        this.activity = activity;
        this.loginDTO = loginDTO;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(activity, "Loading", "Data Loading...");
    }

    @Override
    protected String doInBackground(String... params) {
        InputStream inputStream;
        String jsonString = "";
        String newUrl = Common.LOGIN_URL + "?m_userid=" + loginDTO.getM_userid() + "&m_userpw=" + loginDTO.getM_userpw();

        try {
            Log.i("Login doInBackground", "------LoginDoInStart-------" + newUrl);
            URL url = new URL(newUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setUseCaches(false);
            urlConnection.setDefaultUseCaches(false);

            inputStream = new BufferedInputStream(urlConnection.getInputStream());

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            StringBuilder sb = new StringBuilder();

            String line = null;
            while ( (line = reader.readLine()) != null ) {
                sb.append(line + "\n");
            }
            Log.i("sb",sb.toString());
            jsonString = sb.toString();
            inputStream.close();

            JSONObject result = new JSONObject(jsonString);
            jsonString = result.getString("result");
            Log.i("json", jsonString);
            isConnection = true;
        } catch (Exception e) {
            e.printStackTrace();
            isConnection = false;
        }
        return jsonString;
    }

    @Override
    protected void onPostExecute(String info) {
        super.onPostExecute(info);
        progressDialog.dismiss();
        try {
            LoginAlert(info);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void LoginAlert(String info) throws JSONException {
        try {
            if (info.equals("success")) {
                Toast.makeText(activity, "로그인 성공", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(activity, "로그인 실패", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
