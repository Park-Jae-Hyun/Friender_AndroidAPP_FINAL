package com.example.jteam.friender.myinfo;//////////////////////////////////////////

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jteam.friender.bulletinview.BulletinItemView;
import com.example.jteam.friender.bulletinview.Bulletin;
import com.example.jteam.friender.R;
import com.example.jteam.friender.database.DB_Bulletin;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by flag on 2016-08-09.
 */
public class MyJoinList extends AppCompatActivity {

    BoardAdapter Adapter;
    ArrayList<Bulletin> post_bulletin = new ArrayList<Bulletin>();
    private int USER_UNIQUE_ID = 0;
    private String destination = null, sub_route1 = null, sub_route2 = null, text = null;
    private int date = 0, finding_friends = 0, joined_friends = 0, character1, character2, character3;
    private int id = 0;
    private int num_bulletin = 0;
    private String city = null;
    private String name = null;
    private String mobile_number = null;
    private String writer = null;
    ArrayList<Bulletin> bulletin = new ArrayList<Bulletin>();
    ListView list;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_board);

        Intent intent = getIntent();
        USER_UNIQUE_ID = intent.getIntExtra("USER_UNIQUE_ID",0);
        MyBulletinJoin my_b_show = new MyBulletinJoin();
        my_b_show.execute(""+USER_UNIQUE_ID);

        Adapter = new BoardAdapter();
        list = (ListView) findViewById(R.id.listView2);
        list.setAdapter(Adapter);

        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        list.setDivider(new ColorDrawable(Color.BLACK));
        list.setDividerHeight(10);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Selected : " + position, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),DB_Bulletin.class);

                //인텐트에 bulletin정보를 담아 전달
                intent.putExtra("bulletin",bulletin.get(position));
                startActivity(intent);
            }

        });

        android.support.v7.app.ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("FIND");
        actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF000000")));
        actionbar.setDisplayHomeAsUpEnabled(true);
    }

    class BoardAdapter extends BaseAdapter {

        public BoardAdapter()
        {
        }

        @Override
        public int getCount() {
            return post_bulletin.size();
        }

        @Override
        public Object getItem(int position) {
            return 0;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        //각 게시판리스트에 보여질 뷰 세팅
        public View getView(int position, View convertView, ViewGroup parent) {

            BulletinItemView view = new BulletinItemView(getApplicationContext());

            view.setBulletin(post_bulletin.get(position));

            return view;
        }
    }

    class MyBulletinJoin extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String user_u_id = params[0];

            String data = "";
            int tmp;

            try {
                URL url = new URL("http://52.68.212.232/db_travel_my_join.php");
                String urlParams = "user_u_id=" + user_u_id;

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
            String u_mobile = null;
            String u_name = null;

            try {
                JSONObject json = new JSONObject(s);
                data = json.getString("post_data");
                JSONArray results = json.getJSONArray("post_data");

                for( int i = 0; i < results.length(); ++i) {

                    Bulletin temp = new Bulletin();
                    JSONObject dataJObject = results.getJSONObject(i);

                    id = dataJObject.getInt("id");
                    num_bulletin = dataJObject.getInt("num_bulletin");
                    destination = dataJObject.getString("destination");
                    writer = dataJObject.getString("writer");
                    sub_route1 = dataJObject.getString("sub_route1");
                    sub_route2 = dataJObject.getString("sub_route2");
                    date = dataJObject.getInt("date");
                    finding_friends = dataJObject.getInt("finding_friends");
                    joined_friends = dataJObject.getInt("joined_friends");
                    character1 = dataJObject.getInt("character1");
                    character2 = dataJObject.getInt("character2");
                    character3 = dataJObject.getInt("character3");
                    text = dataJObject.getString("text");
                    u_name = dataJObject.getString("name");
                    u_mobile = dataJObject.getString("mobile_number");

                    temp.setAllcomponents(num_bulletin,destination, writer, sub_route1, sub_route2, date,
                            finding_friends, joined_friends, character1, character2, character3,text);

                    post_bulletin.add(temp);

                }
                Adapter = new BoardAdapter();
                list = (ListView) findViewById(R.id.listView2);
                list.setAdapter(Adapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
