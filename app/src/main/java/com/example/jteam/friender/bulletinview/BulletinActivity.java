//게시판 액티비티
package com.example.jteam.friender.bulletinview;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jteam.friender.R;
import com.example.jteam.friender.cityview.CityList;
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
import java.util.Calendar;
import java.util.Collections;

public class BulletinActivity extends AppCompatActivity {
    CityList CList = new CityList();
    TextView textview;
    BoardAdapter Adapter;
    Button startdateButton;
    Button lastdateButton;
    ArrayList<Bulletin> bulletin = new ArrayList<Bulletin>();
    ListView list;

    private int USER_UNIQUE_ID = 0;
    private String city = null;
    private String name = null;
    private String writer = null;
    private String destination = null, sub_route1 = null, sub_route2 = null, text = null, mobile_number = null;
    private int num_bulletin =0, date = 0, finding_friends = 0, joined_friends = 0, character1, character2, character3;
    // 필터시 사용할 날짜
    private int startdate =0, lastdate= 99999999;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_board);

        Intent intent = getIntent();


        if(intent.getIntExtra("USER_UNIQUE_ID",0)!=0) {
            USER_UNIQUE_ID = intent.getIntExtra("USER_UNIQUE_ID",0);
            name = intent.getStringExtra("NAME");
            mobile_number = intent.getStringExtra("mobile_number");
            city = intent.getStringExtra("city");
        }


        //액션바 타이틀 변경
        android.support.v7.app.ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle(city);
        //actionbar.setTitle((String)CList.getCity_list().get(intent.getFlags()));

       // View itemview = getLayoutInflater().inflate(R.layout.city_item,null);
        //actionbar.setCustomView(itemview);
        actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF000000")));
        actionbar.setDisplayHomeAsUpEnabled(true);

        //Get travel_info from database
        if(city == null)
            city = (String)CList.getCity_list().get(intent.getFlags());

        BulletinShow B_Show = new BulletinShow();
        B_Show.execute(city);

        Adapter = new BoardAdapter(bulletin);
        ListView list = (ListView) findViewById(R.id.listView2);
        list.setAdapter(Adapter);

        // listview options
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
                intent.putExtra("USER_UNIQUE_ID",USER_UNIQUE_ID);/////////////////////////////////////////////////
                intent.putExtra("user_name",name);
                intent.putExtra("mobile_number",mobile_number);
                startActivity(intent);
                finish();
            }

        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if(id == android.R.id.home)
        {
            finish();
            return true;
        }

        if(id == R.id.Find)
        {
            findcustom(null);
        }
        if(id == R.id.Write)
        {
            Intent intent2 = new Intent(BulletinActivity.this,BulletinPost.class);
            if(USER_UNIQUE_ID!=0) {
                intent2.putExtra("USER_UNIQUE_ID",USER_UNIQUE_ID);
                intent2.putExtra("city",city);
                intent2.putExtra("writer",name);
                intent2.putExtra("mobile_number",mobile_number);
            }

            startActivity(intent2);
        }
        return super.onOptionsItemSelected(item);
    }

    private void findcustom(final CalendarContract.Reminders reminder)
    {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.find_custom);
        startdate =0;
        lastdate= 99999999;

        final Button findButton = (Button) dialog.findViewById(R.id.custom_button_find);
        Button cancelButton = (Button) dialog.findViewById(R.id.custom_button_cancel);
        startdateButton = (Button) dialog.findViewById(R.id.custom_button_startdate);
        lastdateButton = (Button) dialog.findViewById(R.id.custom_button_lastdate);

        //날짜 선택 초기상태가 현재 날짜이도록 초기화
        final Calendar c = Calendar.getInstance();
        final int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DATE);



        //날짜선택버튼 클릭리스너 설정(데이트피커다이얼로그 뜨게)
        startdateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog
                        (BulletinActivity.this, startdatelistener, year, month, day);
                dialog.show();
            }
        });

        lastdateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog
                        (BulletinActivity.this, lastdatelistener, year, month, day);
                dialog.show();
            }
        });


        findButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                ArrayList<Bulletin> filtedbulletin = new ArrayList<Bulletin>();
                EditText destination = (EditText) dialog.findViewById(R.id.find_word);
                String des = destination.getText().toString();

                //글자검색 안할때
                if(des.equals(""))
                {
                    for(int i = 0; i < bulletin.size();i++)
                        if(startdate<=bulletin.get(i).getintDate()&&lastdate>=bulletin.get(i).getintDate())
                            filtedbulletin.add(bulletin.get(i));

                }
                //글자검색할때
                else
                    for(int i = 0; i < bulletin.size();i++)
                    {
                      if(bulletin.get(i).getDestination().equals(des)
                              ||bulletin.get(i).getRoute1().equals(des)
                              ||bulletin.get(i).getRoute2().equals(des))
                          if(startdate<=bulletin.get(i).getintDate()&&lastdate>=bulletin.get(i).getintDate())
                              filtedbulletin.add(bulletin.get(i));
                    }

                Adapter = new BoardAdapter(filtedbulletin);
                ListView list = (ListView) findViewById(R.id.listView2);
                list.setAdapter(Adapter);
                dialog.dismiss();
            }

        });
        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                dialog.dismiss();
            }

        });
        dialog.show();
    }

    private DatePickerDialog.OnDateSetListener startdatelistener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            monthOfYear++;
            Toast.makeText(getApplicationContext(), year + "/" +
                    monthOfYear + "/" + dayOfMonth, Toast.LENGTH_SHORT).show();
            //시간이 선택되면 버튼에 그 시간을 표시하게 변경
            startdateButton.setText(year + "/" + monthOfYear + "/" + dayOfMonth);
            startdate = year*10000+(monthOfYear)*100+dayOfMonth;
        }
    };

    private DatePickerDialog.OnDateSetListener lastdatelistener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            monthOfYear++;
            Toast.makeText(getApplicationContext(), year + "/" +
                    monthOfYear + "/" + dayOfMonth, Toast.LENGTH_SHORT).show();
            //시간이 선택되면 버튼에 그 시간을 표시하게 변경
            lastdateButton.setText(year + "/" + monthOfYear + "/" + dayOfMonth);
            lastdate = year*10000+(monthOfYear)*100+dayOfMonth;
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_menu, menu);
        return true;
    }


    class BoardAdapter extends BaseAdapter {
        ArrayList<Bulletin> bulletin = new ArrayList<Bulletin>();

        public BoardAdapter(ArrayList<Bulletin> newbulletin)
        {
            bulletin = newbulletin;
        }

        @Override
        public int getCount() {
            return bulletin.size();
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

            view.setBulletin(bulletin.get(position));

            return view;
        }
    }

    class BulletinShow extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String city = params[0];

            String data = "";
            int tmp;

            try {
                URL url = new URL("http://52.68.212.232/db_travel_bulletin.php");
                String urlParams = "city=" + city;

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

            final Calendar c = Calendar.getInstance();
            final int year = c.get(Calendar.YEAR);
            final int month = c.get(Calendar.MONTH);
            final int day = c.get(Calendar.DATE);

            try {
                JSONObject json = new JSONObject(s);
                data = json.getString("travel_data");
                JSONArray results = json.getJSONArray("travel_data");

                for( int i = 0; i < results.length(); ++i) {
                    Bulletin temp = new Bulletin();
                    JSONObject dataJObject = results.getJSONObject(i);
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

                    temp.setAllcomponents(num_bulletin, destination, writer, sub_route1, sub_route2, date,
                            finding_friends, joined_friends, character1, character2, character3,text);

                    //지금보다 이전날짜글은 안띄움
                    if(date>=year*10000+(month+1)*100+day)
                         bulletin.add(temp);
                }

                Collections.sort(bulletin);
                Adapter = new BoardAdapter(bulletin);
                list = (ListView) findViewById(R.id.listView2);
                list.setAdapter(Adapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
