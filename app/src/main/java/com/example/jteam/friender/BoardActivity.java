//게시판 액티비티
package com.example.jteam.friender;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class BoardActivity extends AppCompatActivity {
    CityList CList = new CityList();
    TextView textview;
    Intent intent;
    BoardAdapter Adapter;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_board);

        intent = getIntent();

        //액션바 타이틀 변경
        android.support.v7.app.ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle((String)CList.getCity_list().get(intent.getFlags()));

        View itemview = getLayoutInflater().inflate(R.layout.city_item,null);

        actionbar.setCustomView(itemview);
        // actionbar.setBackgroundDrawable(Drawable.createFromPath(""));

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF000000")));
        actionbar.setDisplayHomeAsUpEnabled(true);

        Adapter = new BoardAdapter();
        ListView list = (ListView) findViewById(R.id.listView2);
        list.setAdapter(Adapter);


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

        }
        if(id == R.id.Write)
        {

        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_menu, menu);
        return true;
    }


    class BoardAdapter extends BaseAdapter {

        public BoardAdapter()
        {
        }

        @Override
        public int getCount() {
            return 1;
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

            return view;
        }
    }

}
