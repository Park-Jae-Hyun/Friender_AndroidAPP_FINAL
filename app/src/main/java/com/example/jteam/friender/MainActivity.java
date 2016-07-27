package com.example.jteam.friender;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;


public class MainActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Complete
        ArrayList<String> main_city_list = new ArrayList<String>();
        CityList CList= new CityList();

        // get city names (city list)
        main_city_list=CList.getCity_list();

        // preparation adapter
        ArrayAdapter<String> Adapter;
        Adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, main_city_list);

        // connection adapter
        ListView list = (ListView) findViewById(R.id.listView);
        list.setAdapter(Adapter);

        // listview options
        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        list.setDivider(new ColorDrawable(Color.WHITE));
        list.setDividerHeight(2);
    }


}







    // ActionBar Option // 액션연습한건데 잘안됨
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
