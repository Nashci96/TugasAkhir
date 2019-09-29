package com.okedroid.apktaichsan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

public class SplashScreen extends AppCompatActivity {

    TextView tvSplash;
    SessionManager sessionManager;
    Intent mhsIntent, dsnIntent, defaultIntent , admIntent , kpdIntent;
    String user, level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screen);


        tvSplash = findViewById(R.id.tvSplash);
        sessionManager = new SessionManager(this);

        mhsIntent = new Intent(SplashScreen.this, MhsMainMenu.class);
        dsnIntent = new Intent(SplashScreen.this, DsnMainMenu.class);
        admIntent = new Intent(SplashScreen.this, AdmMainMenu.class);
        kpdIntent = new Intent(SplashScreen.this,KpdMainMenu.class);

        defaultIntent = new Intent(SplashScreen.this, MainActivity.class);

        SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        user = sharedPreferences.getString("user", "empty");
        level = sharedPreferences.getString("level", "empty");

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (!user.equals("empty")){

                    if (level.equals("1")){

                        startActivity(mhsIntent);
                        finish();

                    }else if (level.equals("2")){

                        startActivity(dsnIntent);
                        finish();

                    }else if (level.equals("3")){

                        startActivity(kpdIntent);
                        finish();
                        //user level 3

                    }else if (level.equals("4")){

                        startActivity(admIntent);
                        finish();
                        //user level 4

                    }else {
                        Toast.makeText(SplashScreen.this,"Level Undefined",Toast.LENGTH_LONG).show();
                    }
                }else {
                    startActivity(defaultIntent);
                    finish();
                }
            }
        },
                3000L); //3000 L = 3 detik
    }
}
