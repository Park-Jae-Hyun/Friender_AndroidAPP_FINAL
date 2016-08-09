package com.example.jteam.friender;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by flag on 2016-07-27.
 */
public class DB_Resister extends Activity {

    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextSex;
    private EditText editTextBirth;
    private EditText editTextMobileNumber;
    Handler mHandler;
    private String userId;
    private String userPw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_resister);
    }


    public void insert(View view){

        editTextFirstName = (EditText) findViewById(R.id.first_Name);
        editTextLastName = (EditText) findViewById(R.id.last_Name);
        editTextEmail = (EditText) findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.password);
        editTextSex = (EditText) findViewById(R.id.sex);
        editTextBirth = (EditText) findViewById(R.id.birth);
        editTextMobileNumber =(EditText) findViewById(R.id.mobile_Number);

        String f_name = editTextFirstName.getText().toString();
        String l_name = editTextLastName.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        String sex = editTextSex.getText().toString();
        String birth = editTextBirth.getText().toString();
        String mobile_number = editTextMobileNumber.getText().toString();

        insertToDatabase(f_name, l_name, email, password, sex, birth, mobile_number); // Some information of person insert into database
    }
    private void insertToDatabase(String f_name, String l_name, String email, String password, String sex, String birth, String mobile_number){

        class InsertData extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DB_Resister.this, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            protected String doInBackground(String... params) {

                try {
                    String f_name = params[0];
                    String l_name = params[1];
                    String email = params[2];
                    String password = params[3];
                    String sex = params[4];
                    String birth = params[5];
                    String mobile_number = params[6];

                    String link = "http://52.68.212.232/insert.php";
                    String data = URLEncoder.encode("f_name", "UTF-8") + "=" + URLEncoder.encode(f_name, "UTF-8");
                    data += "&" + URLEncoder.encode("l_name", "UTF-8") + "=" + URLEncoder.encode(l_name, "UTF-8");
                    data += "&" + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
                    data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                    data += "&" + URLEncoder.encode("sex","UTF-8") + "=" + URLEncoder.encode(sex, "UTF-8");
                    data += "&" + URLEncoder.encode("birth", "UTF-8") + "=" + URLEncoder.encode(birth, "UTF-8");
                    data += "&" + URLEncoder.encode("mobile_number", "UTF-8") + "=" + URLEncoder.encode(mobile_number, "UTF-8");

                    URL url = new URL(link);
                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                    wr.write(data);
                    wr.flush();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    // Read Server Response
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                        break;
                    }
                    return sb.toString();
                } catch (Exception e) {
                    return new String("Exception: " + e.getMessage());
                }
            }
        }

        InsertData task = new InsertData();
        task.execute(f_name, l_name, email, password, sex, birth, mobile_number);
    }
}
