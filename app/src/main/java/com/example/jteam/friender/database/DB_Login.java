package com.example.jteam.friender.database;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.jteam.friender.cityview.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DB_Login extends Activity{

    private String id = null, password = null;
    private static String F_NAME = null, L_NAME = null, EMAIL = null, SEX = null, BIRTH = null, MOBILE_NUMBER = null;
    private static int USER_UNIQUE_ID=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        id = intent.getStringExtra("main_id");
        password =intent.getStringExtra("main_password");

        LoginCheck login_check = new LoginCheck();
        login_check.execute(id, password);

    }

    class LoginCheck extends AsyncTask<String, String, String> {

        ProgressDialog loading;


        @Override
        protected String doInBackground(String... params) {
            String user_id = params[0];
            String user_password = params[1];
            String data = "";
            int tmp;

            try {
                URL url = new URL("http://52.68.212.232/db_login.php");
                String urlParams = "id=" + user_id + "&password=" + user_password;

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();
                InputStream is = httpURLConnection.getInputStream();
                while ((tmp = is.read()) != -1) {
                    data += (char) tmp;
                }

                is.close();
                httpURLConnection.disconnect();

                return data;

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {

            String data = null;

            try {
                JSONObject json = new JSONObject(s);
                data = json.getString("user_data");
                JSONObject dataJObject = json.getJSONObject("user_data");
                USER_UNIQUE_ID = dataJObject.getInt("user_unique_id");
                F_NAME = dataJObject.getString("f_name");
                L_NAME = dataJObject.getString("l_name");
                EMAIL = dataJObject.getString("email");
                SEX = dataJObject.getString("sex");
                BIRTH = dataJObject.getString("birth");
                MOBILE_NUMBER = dataJObject.getString("mobile_number");


                Intent intent = new Intent(DB_Login.this, MainActivity.class);
                intent.putExtra("USER_UNIQUE_ID", USER_UNIQUE_ID);
                intent.putExtra("F_NAME", F_NAME);
                intent.putExtra("L_NAME", L_NAME);
                intent.putExtra("EMAIL", EMAIL);
                intent.putExtra("SEX", SEX);
                intent.putExtra("BIRTH", BIRTH);
                intent.putExtra("MOBILE_NUMBER", MOBILE_NUMBER);

                setResult(RESULT_OK,intent);
                finish();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}