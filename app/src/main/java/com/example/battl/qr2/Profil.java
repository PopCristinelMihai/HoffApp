package com.example.battl.qr2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.vision.barcode.Barcode;
import com.squareup.picasso.Picasso;

public class Profil extends AppCompatActivity  {

    TextView textView4;
    ImageView imageView;
    TextView textViewCredits;

    private String credits = "50";
    String url="https://zippy.gfycat.com/LiquidBlindDorking.gif";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        textViewCredits= findViewById(R.id.textViewCredits);
        imageView= findViewById(R.id.imageView);
        textView4= findViewById(R.id.textView4);
        loadImageFromUrl(url);

        //final Barcode barcode = getIntent().getParcelableExtra("barcode");

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();


        if(extras!=null)
        {
            String getEmail=(String) extras.getString("email");
            textView4.setText(getEmail);

            String getCredits=(String) extras.getString("credits");
            textViewCredits.setText(getCredits);
        }




    }
    private void loadImageFromUrl(String url)
    {
        Picasso.get().load(url).into(imageView);

    }



}
