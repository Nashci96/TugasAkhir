package com.okedroid.apktaichsan;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class MhsMenuKendaliBimbingan extends AppCompatActivity {

    Button bQRGenerator,bDaftarKendaliBimbingan;
    String getId;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mhs_menu_kendali_bimbingan);

        bQRGenerator            = findViewById(R.id.bQRGenerator);
        bDaftarKendaliBimbingan = findViewById(R.id.bDftrBimbinganMhs);
        getId = getSharedPreferences("user",MODE_PRIVATE).getString("user", "empty");

        bQRGenerator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MhsMenuKendaliBimbingan.this,MhsQRGenerator.class);
//                startActivity(intent);
                QRGenetator(getId);
            }
        });

        bDaftarKendaliBimbingan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MhsMenuKendaliBimbingan.this,MhsDftrKendaliBimbingan.class);
                startActivity(intent);
            }
        });
    }

    private void QRGenetator(String getId) {
        if (!getId.isEmpty()){
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            try {
                BitMatrix bitMatrix = multiFormatWriter.encode(getId, BarcodeFormat.QR_CODE,200,200);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                Intent intent = new Intent(context, MhsQRGenerator.class);
                intent.putExtra("pic",bitmap);
                context.startActivity(intent);
            } catch (WriterException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getApplicationContext(),"Error: Text Qr Kosong",Toast.LENGTH_LONG).show();
        }

    }
}
