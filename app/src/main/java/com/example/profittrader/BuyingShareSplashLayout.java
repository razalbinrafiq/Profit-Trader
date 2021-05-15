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
        //Intent intent=getIntent();
        //String idOfShop=intent.getString("id_of_shop");
        idOfShare=intent.getString("id_of_share");
        buyAmount=intent.getString("buyAmount");
        num=Integer.parseInt(intent.getString("num"));
        setSoldSum=Integer.parseInt(intent.getString("setSoldSum"));
        idOfShop=intent.getString("idOfShop");

//                bundle.putString("id_of_share",pathOfShare);
//                bundle.putString("num",String.valueOf(num));
//                bundle.putString("buyAmount",buyAmount);
//                bundle.putString("setSoldSum",String.valueOf(setSoldSum));
//                bundle.putString("idOfShop",idOfShop);



//
//        Toast.makeText(BuyingShareSplashLayout.this, "idOfShare "+idOfShare, Toast.LENGTH_SHORT).show();
//        Toast.makeText(BuyingShareSplashLayout.this, "buyamount "+buyAmount, Toast.LENGTH_SHORT).show();
//        Toast.makeText(BuyingShareSplashLayout.this, "num "+num, Toast.LENGTH_SHORT).show();
//        Toast.makeText(BuyingShareSplashLayout.this, "setsoldsum "+setSoldSum, Toast.LENGTH_SHORT).show();
//        Toast.makeText(BuyingShareSplashLayout.this, "idofshop "+idOfShop, Toast.LENGTH_SHORT).show();


//                            fbUpdate = "shares/" + idOfShare + "/soldShares/" + num;
//
//                            fbUpdateSoldCount = "shares/" + idOfShare + "/soldCount";
//                            fbUpdateAmount = "shares/" + idOfShare + "/soldShares/" + num + "/amount";
//                            fbUpdateSoldSum = "shares/" + idOfShare + "/soldSum";
//                            fbUpdateShopId = "shares/" + idOfShare + "/soldShares/" + num + "/shopId";
//                            fbUpdateCustomerId = "shares/" + idOfShare + "/soldShares/" + num + "/customerId";
//
//
//
//                            mDbRef = mDatabase.getReference(fbUpdateSoldCount);
//                            mDbRef1 = mDatabase.getReference(fbUpdateAmount);
//                            mDbRef2 = mDatabase.getReference(fbUpdateSoldSum);
//                            mDbRef3 = mDatabase.getReference(fbUpdateShopId);
//                            mDbRef4 = mDatabase.getReference(fbUpdateCustomerId);
//
//
//                            mDbRef.setValue(String.valueOf(num));
//                            mDbRef1.setValue(buyAmount);
//                            mDbRef2.setValue(String.valueOf(setSoldSum));
//                            mDbRef3.setValue(idOfShop);
//                            mDbRef4.setValue(check_ID);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {



//                DatabaseReference fb_to_read_share = FirebaseDatabase.getInstance().getReference("shares/" + idOfShare);
//
//                fb_to_read_share.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                        List<String> list = new ArrayList<String>();
//                        for (DataSnapshot dsp : snapshot.getChildren()) {
//                            shareKey = snapshot.getKey().toString();
//                        }
//
//                        idOfShop = snapshot.child("adminId").getValue(String.class);
//                        totalOfShare = snapshot.child("shareAmount").getValue(String.class);
//                        soldOfShare = snapshot.child("soldSum").getValue(String.class);
//                        percentageOfShare = snapshot.child("profitPercentage").getValue(String.class);
//
//                        Toast.makeText(getApplicationContext(),"="+ soldOfShare, Toast.LENGTH_SHORT).show();
//                        Toast.makeText(getApplicationContext(), "="+percentageOfShare, Toast.LENGTH_SHORT).show();
//
//                        available = ((Integer.parseInt(totalOfShare) - Integer.parseInt(soldOfShare)));
//                       // buyAmount = amountToBuy.getText().toString();
//                        buyAmountInt = (Integer.parseInt(buyAmount));
//                       getSoldCount = snapshot.child("soldCount").getValue(String.class);
//                        num = Integer.parseInt(getSoldCount);
//                        num += 1;
//
//                        intSoldOfSum = Integer.parseInt(soldOfShare);
//                        intPercentageOfShare = Integer.parseInt(percentageOfShare);
//                        setSoldSum = intSoldOfSum + buyAmountInt;
//
//
//
//
//                        if (buyAmountInt <= available && buyAmountInt>0 ) {
//
//
//
//                        //    loopExiter=77;
//
//                            fbUpdate = "shares/" + idOfShare + "/soldShares/" + num;
//
//                            fbUpdateSoldCount = "shares/" + idOfShare + "/soldCount";
//                            fbUpdateAmount = "shares/" + idOfShare + "/soldShares/" + num + "/amount";
//                            fbUpdateSoldSum = "shares/" + idOfShare + "/soldSum";
//                            fbUpdateShopId = "shares/" + idOfShare + "/soldShares/" + num + "/shopId";
//                            fbUpdateCustomerId = "shares/" + idOfShare + "/soldShares/" + num + "/customerId";
//
//
//
//                            mDbRef = mDatabase.getReference(fbUpdateSoldCount);
//                            mDbRef1 = mDatabase.getReference(fbUpdateAmount);
//                            mDbRef2 = mDatabase.getReference(fbUpdateSoldSum);
//                            mDbRef3 = mDatabase.getReference(fbUpdateShopId);
//                            mDbRef4 = mDatabase.getReference(fbUpdateCustomerId);
//
//
//                            mDbRef.setValue(String.valueOf(num));
//                            mDbRef1.setValue(buyAmount);
//                            mDbRef2.setValue(String.valueOf(setSoldSum));
//                            mDbRef3.setValue(idOfShop);
//                            mDbRef4.setValue(check_ID);
//
//                       //     Toast.makeText(BuyingShareSplashLayout.this, "Okay", Toast.LENGTH_SHORT).show();
//
//
//
//
//                              buyAmount="-1";
////
                            Intent i=new Intent(BuyingShareSplashLayout.this,UserActivity.class);
                            i.putExtra("user_id",check_ID);
                            startActivity(i);
                            Toast.makeText(BuyingShareSplashLayout.this, "S U C C E S S", Toast.LENGTH_SHORT).show();
                            finish();

//
//
//                        }
//
//
//
//
//
//
//                    }
//
//
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//
//                });


            }


        },DELAY);




    }
}