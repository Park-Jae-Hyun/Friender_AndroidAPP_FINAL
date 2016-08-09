package com.example.jteam.friender;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by Sun on 2016-08-09.
 */
public class Magement extends AppCompatActivity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_information);

    }

    public void onButtonClick(View view){

        ImageButton choice1 = (ImageButton) findViewById(R.id.choice1);
        ImageButton choice2 = (ImageButton) findViewById(R.id.choice2);
        ImageButton choice3 = (ImageButton) findViewById(R.id.choice3);

        switch (view.getId()){
            case R.id.ib1 :
                choice1.setImageResource(R.mipmap.p01people);

            case R.id.ib2:
        }
    }
}
