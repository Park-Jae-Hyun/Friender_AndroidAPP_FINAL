package com.example.jteam.friender;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

public class BoardPost extends Activity {
    private Button datebutton;
    private Spinner numofmembers;
    private Spinner myfriend;

    private int USER_UNIQUE_ID = 0;
    private EditText editDestination;
    private EditText editRoute1;
    private EditText editRoute2;
    private EditText editLetter;
    private CheckBox[] checkBox = new CheckBox[20];
    private Button buttonWrite;
    private Button buttonCancel;
    private String writer = null;
/*
    private String destination = null;
    private String route1 = null;
    private String route2 = null;
    private String letter = null;
    private int totalnum = 0;/////////////
    private int joinednum = 0;//////////////
    private boolean[] checkbox = new boolean[20];
    private int[] character = new int[3];/////////////
    private String p_date = null;
    private String city = null;*/
    Bulletin bulletin = new Bulletin();


    //check box resource 배열
    int[] checkres = {R.id.posting_check1, R.id.posting_check2, R.id.posting_check3,
            R.id.posting_check4,R.id.posting_check5,R.id.posting_check6,R.id.posting_check7,
            R.id.posting_check8,R.id.posting_check9,R.id.posting_check10,R.id.posting_check11,
            R.id.posting_check12,R.id.posting_check13,R.id.posting_check14,R.id.posting_check15,
            R.id.posting_check16,R.id.posting_check17,R.id.posting_check18,R.id.posting_check19,
            R.id.posting_check20};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_posting);

        // GET ID FROM BOARDACTIVITY TO GET TRABLE_INFO FROM DATABASE, USER_UNIQUE_ID is FOREIGNER
        Intent intent = getIntent();

        if(intent.getIntExtra("USER_UNIQUE_ID",0)!=0) {
            USER_UNIQUE_ID = intent.getIntExtra("USER_UNIQUE_ID",0);
            bulletin.setCity(intent.getStringExtra("city"));
            writer = intent.getStringExtra("writer");
            Log.i("USER_UNIQUE_ID",""+USER_UNIQUE_ID);
        }
        Log.i("USER_UNIQUE_IDherere",""+USER_UNIQUE_ID);

//        for(int i = 0; i < 20; i++) {
//         //   checkBox[i] = (CheckBox)findViewById(checkres[i]);
//        }
        buttonWrite = (Button) findViewById(R.id.posting_write);
        buttonCancel = (Button) findViewById(R.id.posting_cancel);

        datebutton = (Button) findViewById(R.id.posting_datebutton);
        numofmembers = (Spinner) findViewById(R.id.posting_total_num);
        myfriend = (Spinner) findViewById(R.id.posting_myfriends_num);

        editDestination = (EditText) findViewById(R.id.posting_destinationedit);
        editRoute1 = (EditText)findViewById(R.id.posting_route1);
        editRoute2 = (EditText)findViewById(R.id.posting_route2);
        editLetter = (EditText)findViewById(R.id.posting_letter);

        for(int i = 0; i < 20; i++) {
            checkBox[i] = (CheckBox)findViewById(checkres[i]);
        }

        buttonWrite = (Button) findViewById(R.id.posting_write);
        buttonCancel = (Button) findViewById(R.id.posting_cancel);

        //날짜 선택 초기상태가 현재 날짜이도록 초기화
        final Calendar c = Calendar.getInstance();
        final int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DATE);

        //구하는 사람수를 선택하기 위해 string.xml에 리스트(2,3,4...미리적어둠)를 불러옴
        ArrayAdapter totalnumadapter = ArrayAdapter.createFromResource(this,
                R.array.totalnumbers, android.R.layout.simple_spinner_item);
        numofmembers.setAdapter(totalnumadapter);
        ArrayAdapter joinednumadapter = ArrayAdapter.createFromResource(this,
                R.array.joinednumbers, android.R.layout.simple_spinner_item);
        myfriend.setAdapter(joinednumadapter);

        //전체 사람수 선택 스피너 리스너
        numofmembers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Selected : " + position, Toast.LENGTH_SHORT).show();
                bulletin.setTotalnum(position+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //현재사람수 선택 스피너 리스너
        myfriend.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Selected : " + position, Toast.LENGTH_SHORT).show();
                bulletin.setJoinednum(position+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        //date버튼이 눌리면 datepickerdialog 생성
        datebutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog
                        (BoardPost.this, listener, year, month, day);
                dialog.show();
            }
        });

    }

    //datepickerdialog listener 정의
    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    monthOfYear++;
                    Toast.makeText(getApplicationContext(), year + "/" +
                            monthOfYear + "/" + dayOfMonth, Toast.LENGTH_SHORT).show();
                    Button date = (Button) findViewById(R.id.posting_datebutton);
                    date.setText(year + "/" + monthOfYear + "/" + dayOfMonth);
                   // p_year = year;
                   // p_month = monthOfYear;
                   // p_day = dayOfMonth;

                    //p_date = ""+year+""+monthOfYear+""+dayOfMonth;
                    bulletin.setDate(year*10000+monthOfYear*100+dayOfMonth);
                }
    };

    public void onClickCancel(View v) {
        finish();
    }


    public void onClickPost(View v) {
        //id, destination, route1, route2, date(p_year,p_month,p_day), character1, character2, character3, text

        bulletin.setDestination(editDestination.getText().toString());
        bulletin.setRoute1( editRoute1.getText().toString());
        bulletin.setRoute2(editRoute2.getText().toString());
        bulletin.setLetter(editLetter.getText().toString());

        // check the value of result from checkbox
        int j = 0;
        for(int i = 0; i < 20 && j<3 ; i++) {
            if(checkBox[i].isChecked())
                bulletin.setCharacter(j++,i);
        }

        PostOnBoard post_on_board = new PostOnBoard();
        //post_on_board.execute(USER_UNIQUE_ID, destination, route1, route2, p_date, ""+character[1], ""+character[2], ""+character[3]);
        post_on_board.execute(""+USER_UNIQUE_ID, bulletin.getCity(), bulletin.getDestination(), writer,
                bulletin.getRoute1(), bulletin.getRoute2(), bulletin.getDate(),""+bulletin.getTotalnum(),
                ""+bulletin.getJoinednum(), ""+bulletin.getCharacter(0), ""+bulletin.getCharacter(1), ""+bulletin.getCharacter(2), bulletin.getLetter());

    }

    class PostOnBoard extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            String user_u_id = params[0];
            String user_city = params[1];
            String user_destination = params[2];
            String user_writer = params[3];
            String user_route1 = params[4];
            String user_route2 = params[5];
            String user_date = params[6];
            String user_total_traveler = params[7];
            String user_joined_traveler = params[8];
            String user_character1 = params[9];
            String user_character2 = params[10];
            String user_character3 = params[11];
            String user_text = params[12];


            Log.i("user_u_id",""+user_u_id);
            Log.i("user_city",""+user_city);
            Log.i("user_destination",""+user_destination);
            Log.i("user_writer",""+user_writer);
            Log.i("user_route1",""+user_route1);
            Log.i("user_route2",""+user_route2);
            Log.i("user_date",""+user_date);
            Log.i("user_total_traveler",""+user_total_traveler);
            Log.i("user_joined_traveler",""+user_joined_traveler);
            Log.i("user_character1",""+user_character1);
            Log.i("user_character2",""+user_character2);
            Log.i("user_character3",""+user_character3);
            Log.i("user_text",""+user_text);




            String data = "";
            int tmp;

            try {
                URL url = new URL("http://52.68.212.232/db_travel_post.php");
//
                String urlParams =  "id="+user_u_id+
                                    "&city="+user_city+
                                    "&destination="+user_destination+
                                    "&writer="+user_writer+
                                    "&route1="+user_route1+
                                    "&route2="+user_route2+
                                    "&date="+user_date+
                                    "&total_friends="+user_total_traveler+
                                    "&joined_friends="+user_joined_traveler+
                                    "&character1="+user_character1+
                                    "&character2="+user_character2+
                                    "&character3="+user_character3+
                                    "&text="+user_text;


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

            Intent intent = getIntent();
            Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            finish();
        }
    }

}

