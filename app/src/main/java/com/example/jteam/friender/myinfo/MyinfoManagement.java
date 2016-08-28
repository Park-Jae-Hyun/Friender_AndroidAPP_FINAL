package com.example.jteam.friender.myinfo;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jteam.friender.R;

import org.w3c.dom.Text;

/**
 * Created by Sun on 2016-08-09.
 */
public class MyinfoManagement extends Activity{

    ImageView choice1;
    ImageView choice2;
    ImageView choice3;

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_information);
        Intent intent = getIntent();
        String ID = intent.getExtras().getString("EMAIL");
        String SEX = intent.getExtras().getString("SEX");
        String FNAME = intent.getExtras().getString("FNAME");
        String LNAME = intent.getExtras().getString("LNAME");
        String BIRTH = intent.getExtras().getString("BIRTH");
        String MOBILE = intent.getExtras().getString("MOBILE");

        TextView text_id = (TextView)findViewById(R.id.inform_id);
        TextView text_birth = (TextView)findViewById(R.id.inform_birth);
        TextView text_sex = (TextView)findViewById(R.id.inform_gender);
        TextView text_fname = (TextView)findViewById(R.id.inform_first);
        TextView text_lname = (TextView)findViewById(R.id.inform_last);
        TextView text_mobile = (TextView)findViewById(R.id.inform_mobile);

        text_id.setText(ID);
        text_sex.setText(SEX);
        text_fname.setText(FNAME);
        text_lname.setText(LNAME);
        text_birth.setText(BIRTH);
        text_mobile.setText(MOBILE);

        choice1 = (ImageView) findViewById(R.id.choice1);
        choice2 = (ImageView) findViewById(R.id.choice2);
        choice3 = (ImageView) findViewById(R.id.choice3);
    }

    public void onButtonClick(View view)
    {
        switch (view.getId())
        {
            case R.id.modify:
                choice1.setImageResource(android.R.color.transparent);
                choice2.setImageResource(android.R.color.transparent);
                choice3.setImageResource(android.R.color.transparent);
                count = 0;
                break;

            case R.id.cancel:
                finish();
                break;

            case R.id.ib1:
                if(count == 0) {
                    choice1.setImageResource(R.mipmap.p01people);
                }

                else if(count == 1){
                    choice2.setImageResource(R.mipmap.p01people);
                }
                else if(count ==2){
                    choice3.setImageResource(R.mipmap.p01people);
                }
                count++;
                break;

            case R.id.ib2:
                if(count == 0) {
                    choice1.setImageResource(R.mipmap.p02food);
                }

                else if(count == 1){
                    choice2.setImageResource(R.mipmap.p02food);
                }
                else if(count ==2){
                    choice3.setImageResource(R.mipmap.p02food);
                }
                count++;
                break;
            case R.id.ib3:
                if(count == 0) {
                    choice1.setImageResource(R.mipmap.p03beer);
                }

                else if(count == 1){
                    choice2.setImageResource(R.mipmap.p03beer);
                }
                else if(count ==2){
                    choice3.setImageResource(R.mipmap.p03beer);
                }
                count++;
                break;
            case R.id.ib4:
                if(count == 0) {
                    choice1.setImageResource(R.mipmap.p04coffee);
                }

                else if(count == 1){
                    choice2.setImageResource(R.mipmap.p04coffee);
                }
                else if(count ==2){
                    choice3.setImageResource(R.mipmap.p04coffee);
                }
                count++;
                break;
            case R.id.ib5:
                if(count == 0) {
                    choice1.setImageResource(R.mipmap.p05sports);
                }

                else if(count == 1){
                    choice2.setImageResource(R.mipmap.p05sports);
                }
                else if(count ==2){
                    choice3.setImageResource(R.mipmap.p05sports);
                }
                count++;
                break;
            case R.id.ib6:
                if(count == 0) {
                    choice1.setImageResource(R.mipmap.p06music);
                }

                else if(count == 1){
                    choice2.setImageResource(R.mipmap.p06music);
                }
                else if(count ==2){
                    choice3.setImageResource(R.mipmap.p06music);
                }
                count++;
                break;
            case R.id.ib7:
                if(count == 0) {
                    choice1.setImageResource(R.mipmap.p07movie);
                }

                else if(count == 1){
                    choice2.setImageResource(R.mipmap.p07movie);
                }
                else if(count ==2){
                    choice3.setImageResource(R.mipmap.p07movie);
                }
                count++;
                break;
            case R.id.ib8:
                if(count == 0) {
                    choice1.setImageResource(R.mipmap.p08photo);
                }

                else if(count == 1){
                    choice2.setImageResource(R.mipmap.p08photo);
                }
                else if(count ==2){
                    choice3.setImageResource(R.mipmap.p08photo);
                }
                count++;
                break;
            case R.id.ib9:
                if(count == 0) {
                    choice1.setImageResource(R.mipmap.p09reading);
                }

                else if(count == 1){
                    choice2.setImageResource(R.mipmap.p09reading);
                }
                else if(count ==2){
                    choice3.setImageResource(R.mipmap.p09reading);
                }
                count++;
                break;
            case R.id.ib10:
                if(count == 0) {
                    choice1.setImageResource(R.mipmap.p10concert);
                }

                else if(count == 1){
                    choice2.setImageResource(R.mipmap.p10concert);
                }
                else if(count ==2){
                    choice3.setImageResource(R.mipmap.p10concert);
                }
                count++;
                break;
            case R.id.ib11:
                if(count == 0) {
                    choice1.setImageResource(R.mipmap.p11festival);
                }

                else if(count == 1){
                    choice2.setImageResource(R.mipmap.p11festival);
                }
                else if(count ==2){
                    choice3.setImageResource(R.mipmap.p11festival);
                }
                count++;
                break;
            case R.id.ib12:
                if(count == 0) {
                    choice1.setImageResource(R.mipmap.p12travel);
                }

                else if(count == 1){
                    choice2.setImageResource(R.mipmap.p12travel);
                }
                else if(count ==2){
                    choice3.setImageResource(R.mipmap.p12travel);
                }
                count++;
                break;
            case R.id.ib13:
                if(count == 0) {
                    choice1.setImageResource(R.mipmap.p13rest);
                }

                else if(count == 1){
                    choice2.setImageResource(R.mipmap.p13rest);
                }
                else if(count ==2){
                    choice3.setImageResource(R.mipmap.p13rest);
                }
                count++;
                break;
            case R.id.ib14:
                if(count == 0) {
                    choice1.setImageResource(R.mipmap.p14tour);
                }

                else if(count == 1){
                    choice2.setImageResource(R.mipmap.p14tour);
                }
                else if(count ==2){
                    choice3.setImageResource(R.mipmap.p14tour);
                }
                count++;
                break;
            case R.id.ib15:
                if(count == 0) {
                    choice1.setImageResource(R.mipmap.p15beach);
                }

                else if(count == 1){
                    choice2.setImageResource(R.mipmap.p15beach);
                }
                else if(count ==2){
                    choice3.setImageResource(R.mipmap.p15beach);
                }
                count++;
                break;
            case R.id.ib16:
                if(count == 0) {
                    choice1.setImageResource(R.mipmap.p16mountain);
                }

                else if(count == 1){
                    choice2.setImageResource(R.mipmap.p16mountain);
                }
                else if(count ==2){
                    choice3.setImageResource(R.mipmap.p16mountain);
                }
                count++;
                break;

        }

    }



}