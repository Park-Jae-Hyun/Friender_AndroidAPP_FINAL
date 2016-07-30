package com.example.jteam.friender;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Timer m_timer = new Timer();

        TimerTask  m_task = new TimerTask()
        {
            @Override
            public void run()
            {
                Intent mainIntent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(mainIntent);
                finish();
                //        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                //        startActivity(intent);
                //        finish();
            }
        };

        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        m_timer.schedule(m_task,3000);

    }

}
