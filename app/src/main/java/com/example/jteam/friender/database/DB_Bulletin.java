package com.example.jteam.friender.database;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jteam.friender.bulletinview.Bulletin;
import com.example.jteam.friender.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by flag on 2016-08-05.
 */



public class DB_Bulletin extends AppCompatActivity{
    TextView Date;
    TextView Destination;
    TextView Route1;
    TextView Route2;
    TextView ID;
    TextView Present;
    TextView Finding;
    TextView Join;
    TextView Letter;
    ImageView[] Pictogram = new ImageView[3];
    Bulletin bulletin;
    private String name = null, mobile_number = null;
    private int num_bulletin = 0;
    private int USER_UNIQUE_ID = 0;
    private int finding_friends = 0, present_friends = 0;

    int pictogramres[] = {R.color.white,R.mipmap.p01people,R.mipmap.p02food,R.mipmap.p03beer,R.mipmap.p04coffee,
            R.mipmap.p05sports,R.mipmap.p06music,R.mipmap.p07movie,R.mipmap.p08photo,R.mipmap.p09reading,
            R.mipmap.p10concert,R.mipmap.p11festival,R.mipmap.p12travel,R.mipmap.p13rest,R.mipmap.p14tour,
            R.mipmap.p15beach,R.mipmap.p16mountain,R.mipmap.p17owncar,R.mipmap.p18bycicle,
            R.mipmap.p19publictransit,R.mipmap.p20cruise};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_bulletin);

        android.support.v7.app.ActionBar actionbar = getSupportActionBar();
        actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF000000")));

        //intent로 보내온 bulletin객체 받아오기
        Intent intent = getIntent();
        bulletin = (Bulletin) intent.getSerializableExtra("bulletin");
        name = intent.getStringExtra("user_name");
        mobile_number = intent.getStringExtra("mobile_number");
        USER_UNIQUE_ID = intent.getIntExtra("USER_UNIQUE_ID",0);

        Date = (TextView) findViewById(R.id.bulletin_date);
        Destination= (TextView) findViewById(R.id.bulletin_destination);
        Route1= (TextView) findViewById(R.id.bulletin_route1);
        Route2= (TextView) findViewById(R.id.bulletin_route2);
        ID= (TextView) findViewById(R.id.bulletin_id);
        Present= (TextView) findViewById(R.id.bulletin_present);
        Finding= (TextView) findViewById(R.id.bulletin_finding);
        Join= (TextView) findViewById(R.id.bulletin_joinbutton);
        Letter= (TextView) findViewById(R.id.bulletin_letter);
        Pictogram[0] = (ImageView) findViewById(R.id.bulletin_pictogram1);
        Pictogram[1] = (ImageView) findViewById(R.id.bulletin_pictogram2);
        Pictogram[2] = (ImageView) findViewById(R.id.bulletin_pictogram3);

        Date.setText(bulletin.getDate());
        Destination.setText(bulletin.getDestination());
        Route1.setText(bulletin.getRoute1());
        Route2.setText(bulletin.getRoute2());
        ID.setText(bulletin.getUsername());
        Present.setText(""+bulletin.getJoinednum());
        present_friends = bulletin.getJoinednum();
        if(bulletin.getTotalnum()==10) {

            finding_friends = bulletin.getTotalnum();
            Finding.setText("Any");
        }
        else {
            finding_friends = bulletin.getTotalnum();
            Finding.setText(""+(bulletin.getTotalnum()));
        }
        Log.i("finding_friendshere",""+finding_friends);
        Log.i("gettotalhere",""+bulletin.getTotalnum());

        Letter.setText(bulletin.getLetter());

        Pictogram[0].setImageResource(pictogramres[bulletin.getCharacter(0)]);
        Pictogram[1].setImageResource(pictogramres[bulletin.getCharacter(1)]);
        Pictogram[2].setImageResource(pictogramres[bulletin.getCharacter(2)]);
    }



    public void join_Onclick(View view) {

        num_bulletin = bulletin.getNum_bulletin();
        Log.i("num_bulletin",""+num_bulletin);

        insertToBulletInfo(""+num_bulletin, ""+USER_UNIQUE_ID, name, mobile_number,""+present_friends,""+finding_friends);

    }

    private void insertToBulletInfo(String num_bulletin, String unique_user_id, String user_name, final String mobile_number, String p_friends, String f_friends){

        class InsertData extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DB_Bulletin.this, "Please Wait", null, true, true);
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
                    String u_num_bulletin = params[0];
                    String u_id = params[1];
                    String u_name = params[2];
                    String u_mobile = params[3];
                    String u_p_friends = params[4];
                    String u_f_friends = params[5];
                    int temp1 = 0, temp2 = 0;


                    if(!u_f_friends.equals("10")){
                        temp1 = Integer.parseInt(u_f_friends)-1;
                        u_f_friends = ""+temp1;
                    }
                    temp2 = Integer.parseInt(u_p_friends)+1;
                    u_p_friends = ""+temp2;

                    String link = "http://52.68.212.232/db_bulletin_info_insert.php";
                    String data = URLEncoder.encode("num_bulletin", "UTF-8") + "=" + URLEncoder.encode(u_num_bulletin, "UTF-8");
                    data += "&" + URLEncoder.encode("user_id", "UTF-8") + "=" + URLEncoder.encode(u_id, "UTF-8");
                    data += "&" + URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(u_name, "UTF-8");
                    data += "&" + URLEncoder.encode("user_mobile", "UTF-8") + "=" + URLEncoder.encode(u_mobile, "UTF-8");
                    data += "&" + URLEncoder.encode("user_present_friends", "UTF-8") + "=" + URLEncoder.encode(u_p_friends, "UTF-8");
                    data += "&" + URLEncoder.encode("user_finding_friends", "UTF-8") + "=" + URLEncoder.encode(u_f_friends, "UTF-8");

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
        task.execute(num_bulletin,unique_user_id, user_name, mobile_number, p_friends, f_friends);
    }

}
