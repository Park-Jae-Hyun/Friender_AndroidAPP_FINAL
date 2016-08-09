package com.example.jteam.friender;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

//Seon Test
public class MainActivity extends AppCompatActivity {
    CityAdapter Adapter;
    Intent intent;

    boolean loginset = false; // whether login was complete or not
    private static final int RESULT = 1000;
    private String user_id=null;
    private String F_NAME = null, L_NAME = null, SEX = null, EMAIL = null, BIRTH = null, MOBILE_NUMBER = null;
    private int USER_UNIQUE_ID = 0;
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            intent = getIntent();
        // Complete
        ArrayList<String> main_city_list = new ArrayList<String>();
        CityList CList= new CityList();
        //사진 리스트를 담기위한 어래이리스트
        ArrayList<Integer> plist = new ArrayList<Integer>();

        // get city names (city list)
        main_city_list=CList.getCity_list();
        //get city pictures
        plist = CList.getCity_plist();

        // preparation adapter
        Adapter = new CityAdapter(main_city_list, plist);//시티어탭터로 리스트뷰에 연결

        // connection adapter
        ListView list = (ListView) findViewById(R.id.listView);
        list.setAdapter(Adapter);

        // listview options
        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        list.setDivider(new ColorDrawable(Color.BLACK));
        list.setDividerHeight(2);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Selected : " + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),BoardActivity.class);
                //인텐트에 position정보를 담아 전달

                if(USER_UNIQUE_ID!=0) {
                    intent.putExtra("USER_UNIQUE_ID",USER_UNIQUE_ID);
                    intent.putExtra("NAME",""+F_NAME+" "+L_NAME);
                    intent.putExtra("mobile_number",MOBILE_NUMBER);
                }
                intent.setFlags(position);
                startActivity(intent);
            }

        });

        android.support.v7.app.ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("Friender");

        actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF000000")));
        actionbar.setDisplayHomeAsUpEnabled(true);


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, requestCode, data);
        switch (requestCode) {
            case RESULT:
                if(resultCode==RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    USER_UNIQUE_ID  = bundle.getInt("USER_UNIQUE_ID");
                    F_NAME = bundle.getString("F_NAME");
                    L_NAME = bundle.getString("L_NAME");
                    EMAIL = bundle.getString("EMAIL");
                    SEX = bundle.getString("SEX");
                    BIRTH = bundle.getString("BIRTH");
                    MOBILE_NUMBER = bundle.getString("MOBILE_NUMBER");
                    loginset = true;

//                    Log.i("F_NAME",""+F_NAME);
//                    Log.i("L_NAME",""+L_NAME);
//                    Log.i("EMAIL",""+EMAIL);
//                    Log.i("BIRTH",""+BIRTH);
//                    Log.i("MOBILE_NUMBER",""+MOBILE_NUMBER);

                    Log.i("USER_UNIQUE_ID",""+USER_UNIQUE_ID);
                    Log.i("F_NAME",""+F_NAME);
                    Log.i("L_NAME",""+L_NAME);
                    Log.i("EMAIL",""+EMAIL);
                    Log.i("SEX",""+SEX);
                    Log.i("BIRTH",""+BIRTH);
                    Log.i("MOBILE_NUMBER",""+MOBILE_NUMBER);
                    //loginset = true;

                    //TextView textview = (TextView)findViewById(R.id.inform_id);
                    //textview.setText("GI");
                    //textview.append("HELLO");
                   // textview.setBackgroundColor(Color.BLACK);
                }
                break;
        }
    }

    private void fireCustomDialog(final CalendarContract.Reminders reminder)
    {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_custom);


        TextView titleView = (TextView) dialog.findViewById(R.id.custom_title);
        Button commitButton = (Button) dialog.findViewById(R.id.custom_button_login);
        LinearLayout rootLayout = (LinearLayout) dialog.findViewById(R.id.custom_root_layout);
        Button signupButton = (Button) dialog.findViewById(R.id.custom_button_signup);

        final boolean isEditOperation = (reminder != null);


        commitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText main_id = (EditText) dialog.findViewById(R.id.main_id);
                EditText main_password = (EditText) dialog.findViewById(R.id.main_password);

                String id = main_id.getText().toString();
                String password = main_password.getText().toString();

                Intent intent = new Intent(MainActivity.this, DB_Login.class);
                intent.putExtra("main_id", id);
                intent.putExtra("main_password", password);
                startActivityForResult(intent, RESULT);
                dialog.dismiss();
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                dialog.dismiss();
                startActivity(new Intent(MainActivity.this, DB_Resister.class));
            }
        });

        Button buttonCancle = (Button)dialog.findViewById(R.id.custom_button_cancel);

        buttonCancle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                dialog.dismiss();
            }

        });
        dialog.show();


    }


    private void logincustom(final CalendarContract.Reminders reminder)
    {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.login_custom);

        Button joinedButton = (Button) dialog.findViewById(R.id.joined);
        Button logoutButton = (Button) dialog.findViewById(R.id.logout);

        logoutButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
            }

        });


        joinedButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {

            }

        });

        Button magementButton = (Button) dialog.findViewById(R.id.magement);
        magementButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                dialog.dismiss();
               // setContentView(R.layout.member_information);///////////////////////
                Intent intent = new Intent(MainActivity.this,Magement.class);
                startActivity(intent);
               // setContentView(R.layout.member_information);
            }

        });
        dialog.show();

        Button mypostButton = (Button) dialog.findViewById(R.id.mypost);
        mypostButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                dialog.dismiss();
                Intent intent = new Intent(MainActivity.this,MyPost.class);
                intent.putExtra("USER_UNIQUE_ID",USER_UNIQUE_ID);
                startActivity(intent);
            }

        });
        dialog.show();

    }


    @Override
   public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.actionbar_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.main_login )
        {
            if(loginset == false) {
                fireCustomDialog(null);
            }
            else
            {
                logincustom(null);
            }
            return true;

        }
        return super.onOptionsItemSelected(item);
            
    }

    class CityAdapter extends BaseAdapter {
        ArrayList<String> cities;
        ArrayList<Integer> pcities;

        //시티리스트(이름, 사진)를 받아와 초기화
        public CityAdapter(ArrayList<String> list, ArrayList plist)
        {
            cities = list;
            pcities = plist;
        }

        @Override
        public int getCount() {
            return cities.size();
        }

        @Override
        public Object getItem(int position) {
            return cities.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        //각 리스트에 보여질 뷰 세팅
        public View getView(int position, View convertView, ViewGroup parent) {

            CityItemView view = new CityItemView(getApplicationContext());

            view.setImage(pcities.get(position));
            view.setName(cities.get(position));

            return view;
        }
    }

}