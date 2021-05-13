package com.example.profittrader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BuyingShareSplashLayout extends AppCompatActivity {

    Animation topAnim,bottomAnim;
    ImageView image;
    TextView textView;

    private  static int DELAY=5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_buying_share_splash_layout);

        image=findViewById(R.id.image);
        textView=findViewById(R.id.textView);


        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        image.setAnimation(topAnim);
        textView.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {







                SharedPreferences loginDetails =  getSharedPreferences("loginDetails", MODE_PRIVATE);
                String check_ID = loginDetails.getString("id","0");
                Intent i=new Intent(BuyingShareSplashLayout.this,UserActivity.class);
                i.putExtra("user_id",check_ID);
                startActivity(i);
                Toast.makeText(BuyingShareSplashLayout.this, "S U C C E S S", Toast.LENGTH_SHORT).show();
                finish();
            }
        },DELAY);


    }
}