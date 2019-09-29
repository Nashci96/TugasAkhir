package com.okedroid.apktaichsan;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Button bLoginMhs;
    Button bLoginDsn;
    Button bLoginKpd;
    Button bLoginAdm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bLoginMhs = findViewById(R.id.buttonMhs);
        bLoginDsn = findViewById(R.id.buttonDsn);
        bLoginKpd = findViewById(R.id.buttonKpd);
        bLoginAdm = findViewById(R.id.buttonAdm);

        bLoginMhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LoginActivityMhs.class);
                startActivity(intent);
                finish();
            }
        });

        bLoginDsn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this,LoginActivityDsn.class);
                startActivity(intent);
                finish();
            }
        });

        bLoginKpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LoginActivityKpd.class);
                startActivity(intent);
                finish();
            }
        });

        bLoginAdm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this,LoginActivityAdm.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
