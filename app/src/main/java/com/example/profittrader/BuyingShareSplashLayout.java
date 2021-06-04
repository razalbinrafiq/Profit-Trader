package com.example.profittrader;

import androidx.annotation.NonNull;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BuyingShareSplashLayout extends AppCompatActivity {

    Animation topAnim,bottomAnim;
    ImageView image;
    TextView textView;
    String shareKey,getShareKey;
    String check_ID;
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    String totalOfShare,soldOfShare,percentageOfShare,idOfShop;
    int available,num,intSoldOfSum,intPercentageOfShare,buyAmountInt,getBuyAmountInt,setSoldSum;

    String fbUpdate;
    String fbUpdateSoldCount ;
    String fbUpdateAmount ;
    String fbUpdateSoldSum ;
    String fbUpdateShopId;
    String fbUpdateCustomerId ;
    String idOfShare,buyAmount;
    String getSoldCount;
    int loopExiter;


    DatabaseReference mDbRef,mDbRef1, mDbRef2, mDbRef3, mDbRef4;


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
        loopExiter=1;


        SharedPreferences loginDetails =  getSharedPreferences("loginDetails", MODE_PRIVATE);
        check_ID = loginDetails.getString("id","0");


        Bundle intent=getIntent().getExtras();
        idOfShare=intent.getString("id_of_share");
//        buyAmount=intent.getString("buyAmount");
//        num=Integer.parseInt(intent.getString("num"));
//        setSoldSum=Integer.parseInt(intent.getString("setSoldSum"));
//        idOfShop=intent.getString("idOfShop");




        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                            Intent i=new Intent(BuyingShareSplashLayout.this,UserActivity.class);
                            i.putExtra("user_id",check_ID);
                            startActivity(i);
                            Toast.makeText(BuyingShareSplashLayout.this, "S U C C E S S", Toast.LENGTH_SHORT).show();
                            finish();



            }


        },DELAY);




    }
}