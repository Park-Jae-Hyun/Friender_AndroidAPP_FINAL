package com.example.jteam.friender;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends Activity {
    CityAdapter Adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        list.setDivider(new ColorDrawable(Color.WHITE));
        list.setDividerHeight(2);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Selected : " + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),BoardActivity.class);

                //인텐트에 position정보를 담아 전달
                intent.setFlags(position);
                startActivity(intent);
            }

        });

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
