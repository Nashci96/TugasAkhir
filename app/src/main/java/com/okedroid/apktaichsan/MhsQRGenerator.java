package com.okedroid.apktaichsan;

import android.graphics.Bitmap;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MhsQRGenerator extends AppCompatActivity {

    private ImageView iv_qr_g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mhs_qrgenerator);

        iv_qr_g = findViewById(R.id.iv_qr_g);
        Bitmap bitmap = getIntent().getParcelableExtra("pic");
        iv_qr_g.setImageBitmap(bitmap);
    }
}
