package com.example.AndroidProject.ayncTask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpAsyncTask extends AsyncTask<String, Void, String> {
    ProgressDialog asyncDialog;
    EditText webpage_src;

    public HttpAsyncTask (Context context, EditText editText) {
        asyncDialog = new ProgressDialog(context);
        webpage_src = editText;
    }

    @Override
    protected void onPreExecute() {
        asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        asyncDialog.setMessage("데이터 수신중....");
        asyncDialog.show();

        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        return getJsonText();

    }

    @Override
    protected void onPostExecute(String result) {
        asyncDialog.dismiss();
        webpage_src.setText(result);
    }

    public String getJsonText(){
        StringBuffer sb = new StringBuffer();

        try {
            String jsonPage = getStringFromUrl("http://192.168.0.104:81/LoveBand/AndroidLoveBandJsonAction.love");
            JSONObject json = new JSONObject(jsonPage);
            JSONArray jArr = json.getJSONArray("members");

            for(int i =0; i< jArr.length();i++){
                json = jArr.getJSONObject(i);
                String m_userid = json.getString("m_userid");
                String m_nickname = json.getString("m_nickname");
                String m_email = json.getString("m_email");

                Log.i("products 정보","p_price2"+m_userid+","+"p_img"+","+"p_name"+m_email);

                sb.append(m_userid + "\n");
                sb.append(m_nickname + "\n");
                sb.append(m_email + "\n");
                sb.append("\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return sb.toString();
    }

    public String getStringFromUrl(String pUrl) {
        BufferedReader bufferedReader = null;
        HttpURLConnection urlConnection = null;

        StringBuffer page = new StringBuffer();

        try {
            URL url = new URL(pUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setUseCaches(false);
            urlConnection.setDefaultUseCaches(false);

            InputStream contentStream = urlConnection.getInputStream();

            bufferedReader = new BufferedReader(new InputStreamReader(contentStream, "UTF-8"));

            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                Log.d("line :", line);
                page.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
                urlConnection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return page.toString();
    }
}
