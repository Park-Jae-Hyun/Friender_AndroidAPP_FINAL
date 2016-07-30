//리스트뷰의 각 도시를 보여줄 리니어레이아웃
package com.example.jteam.friender;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Jeong on 2016-07-27.
 */
public class CityItemView extends LinearLayout{

    TextView CitynameView;

    public CityItemView(Context context) {
        super(context);

        init(context);
    }

    public CityItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        //initializing on each list view
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.city_item,this,true);

        CitynameView = (TextView) findViewById(R.id.CitynameView);
    }

    public void setName(String name) {
        CitynameView.setText(name);
    }

    public void setImage(int imageresource)
    {
        CitynameView.setBackgroundResource(imageresource);
    }


}
