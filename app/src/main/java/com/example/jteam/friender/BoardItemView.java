//리스트뷰의 각 도시를 보여줄 리니어레이아웃
package com.example.jteam.friender;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

/**
 * Created by Jeong on 2016-07-27.
 */
public class BoardItemView extends LinearLayout{

    public BoardItemView(Context context) {
        super(context);

        init(context);
    }

    public BoardItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        //initializing on each list view
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.board_item,this,true);

    }


}
