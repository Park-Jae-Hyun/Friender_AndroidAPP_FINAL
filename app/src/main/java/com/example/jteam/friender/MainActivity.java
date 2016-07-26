package com.example.jteam.friender;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.widget.Toast;


// AppCompatActivity
public class MainActivity extends Activity {

    private EditText editTextName;
    private EditText editTextAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_db);
        editTextName = (EditText) findViewById(R.id.name);
        editTextAdd = (EditText) findViewById(R.id.address);

    }

    public void insert(View view){


        String name = editTextName.getText().toString();
        String address = editTextAdd.getText().toString();

        insertToDatabase(name, address);


    }

    private void insertToDatabase(String name, String address){

        class InsertData extends AsyncTask<String, Void, String> {
            ProgressDialog loading;



            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {

                try{
                    String name = (String)params[0];
                    String address = (String)params[1];

                    String link="http://52.68.212.232/insert.php";
                    String data  = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8");
                    data += "&" + URLEncoder.encode("address", "UTF-8") + "=" + URLEncoder.encode(address, "UTF-8");

                    URL url = new URL(link);
                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                    wr.write( data );
                    wr.flush();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    // Read Server Response
                    while((line = reader.readLine()) != null)
                    {
                        sb.append(line);
                        break;
                    }
                    return sb.toString();
                }
                catch(Exception e){
                    return new String("Exception: " + e.getMessage());
                }

            }
        }

        InsertData task = new InsertData();
        task.execute(name,address);
    }
}



/*
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Complete
//        ArrayList<String> main_city_list = new ArrayList<String>();
//        CityList CList= new CityList();
//
//        // get city names (city list)
//        main_city_list=CList.getCity_list();
//
//        // preparation adapter
//        ArrayAdapter<String> Adapter;
//        Adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, main_city_list);
//
//        // connection adapter
//        ListView list = (ListView) findViewById(R.id.listView);
//        list.setAdapter(Adapter);
//
//        // listview options
//        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
//        list.setDivider(new ColorDrawable(Color.WHITE));
//        list.setDividerHeight(2);
    }
*/




    // ActionBar Option
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu){
//        getMenuInflater().inflate(R.menu.actionbar_menu,menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        if ( id == R.id.newID ) {
//            Toast.makeText(MainActivity.this, "새 글 등록 버튼 클릭", Toast.LENGTH_SHORT).show();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//}
