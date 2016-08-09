package com.example.jteam.friender;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
 * Created by Sun on 2016-08-08.
 */
public class MyPost extends AppCompatActivity {

    BoardAdapter Adapter;
    ArrayList<Bulletin> post_bulletin = new ArrayList<Bulletin>();
    private int USER_UNIQUE_ID = 0;
    private String destination = null, sub_route1 = null, sub_route2 = null, text = null;
    private int date = 0, total_friends = 0, joined_friends = 0, character1, character2, character3;
    private int id = 0;
    private int num_bulletin = 0;
    private String city = null;
    private String name = null;
    private String writer = null;
    ListView list;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_board);

        Intent intent = getIntent();
        USER_UNIQUE_ID = intent.getIntExtra("USER_UNIQUE_ID",0);
        MyBulletinPost my_b_show = new MyBulletinPost();
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

                Intent intent = new Intent(getApplicationContext(),MyPost_info.class);

                //인텐트에 bulletin정보를 담아 전달
                intent.putExtra("bulletin",post_bulletin.get(position));
                startActivity(intent);
            }

        });

        android.support.v7.app.ActionBar actionbar = getSupportActionBar();
       // actionbar.setTitle("FIND");
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

            BoardItemView view = new BoardItemView(getApplicationContext());

            view.setBulletin(post_bulletin.get(position));

            return view;
        }
    }

    class MyBulletinPost extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String user_u_id = params[0];

            String data = "";
            int tmp;

            try {
                URL url = new URL("http://52.68.212.232/db_travel_my_post.php");
                String urlParams = "user_u_id=" + user_u_id;

                Log.i("user_u_id2222",""+user_u_id);
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
                    total_friends = dataJObject.getInt("total_friends");
                    joined_friends = dataJObject.getInt("joined_friends");
                    character1 = dataJObject.getInt("character1");
                    character2 = dataJObject.getInt("character2");
                    character3 = dataJObject.getInt("character3");
                    text = dataJObject.getString("text");

                    temp.setAllcomponents(num_bulletin,destination, writer, sub_route1, sub_route2, date,
                            total_friends, joined_friends, character1, character2, character3,text);

                    post_bulletin.add(temp);

                    Log.i("id", "" + id);
                    Log.i("num_bulletin", "" + num_bulletin);
                    Log.i("writer", "" + writer);
                    Log.i("destination", "" + destination);
                    Log.i("sub_route1", "" + sub_route1);
                    Log.i("sub_route2", "" + sub_route2);
                    Log.i("date", "" + date);
                    Log.i("total_friends", "" + total_friends);
                    Log.i("joined_friends", "" + joined_friends);
                    Log.i("character1", "" + character1);
                    Log.i("character2", "" + character2);
                    Log.i("character3", "" + character3);
                    Log.i("text",""+text);
                    Log.i("-----------", "----------\n");
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
