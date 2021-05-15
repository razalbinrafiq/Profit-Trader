package com.example.profittrader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShareDetails extends AppCompatActivity {

    TextView shopName,availableAmount,totalAmount,percentageAmount;
    EditText amountToBuy;
    Button buyShare;
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    String pathOfShare;
    String shareKey,shopKey;
    String idOfShop,nameOfShop,availableOfShare,totalOfShare,soldOfShare,percentageOfShare;
    int intSoldOfSum,intPercentageOfShare,setSoldSum;
    int available,buyAmountInt,num;
    String buyAmount;
    String getSoldCount;
    String fbUpdate,fbUpdateSoldCount,fbUpdateAmount,fbUpdateSoldSum,fbUpdateShopId,fbUpdateCustomerId;
    String idOfShare,getIdOfShop;

    String adminKey,getAdminSoldCount,adminUpdate;
    int adminNum;
    String userKey,getUserSoldCount,userUpdate;
    int userNum;

    String check_ID;



    DatabaseReference mDbRef,mDbRef1, mDbRef2, mDbRef3, mDbRef4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_details);

      //  t1=findViewById(R.id.textView);
        shopName=findViewById(R.id.shopNameTextView);
        availableAmount=findViewById(R.id.availableAmountTextView);
        totalAmount=findViewById(R.id.totalAmountTextView);
        percentageAmount=findViewById(R.id.percentageAmountTextView);
        amountToBuy=findViewById(R.id.amountEditText);
        buyShare=findViewById(R.id.buyButton);

        Bundle login = getIntent().getExtras();
        if (login != null) {
            pathOfShare = login.getString("path");
            //  Toast.makeText(MainActivity.this,user, Toast.LENGTH_SHORT).show();
        }



        SharedPreferences loginDetails =  getSharedPreferences("loginDetails", MODE_PRIVATE);
        check_ID = loginDetails.getString("id","0");

       // Toast.makeText(ShareDetails.this, check_ID, Toast.LENGTH_SHORT).show();

        DatabaseReference fb_to_read_share = FirebaseDatabase.getInstance().getReference("shares/"+pathOfShare);

        fb_to_read_share.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                List<String> list=new ArrayList<String>();
                for (DataSnapshot dsp : snapshot.getChildren()){
                    shareKey =snapshot.getKey().toString();
                }

                idOfShop =snapshot.child("adminId").getValue(String.class);
                totalOfShare =snapshot.child("shareAmount").getValue(String.class);
                soldOfShare=snapshot.child("soldSum").getValue(String.class);
                percentageOfShare=snapshot.child("profitPercentage").getValue(String.class);
               // Toast.makeText(ShareDetails.this, soldOfShare, Toast.LENGTH_SHORT).show();
                int available=(Integer.parseInt(totalOfShare)-Integer.parseInt(soldOfShare));
                availableOfShare =String.valueOf(available);
                availableAmount.setText(availableOfShare);
                totalAmount.setText(totalOfShare);
                percentageAmount.setText(percentageOfShare+"%");

                DatabaseReference fb_to_read_shop = FirebaseDatabase.getInstance().getReference("admins/"+idOfShop+"/userDetails");

                fb_to_read_shop.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot2) {

                        List<String> list=new ArrayList<String>();
                        for (DataSnapshot dsp : snapshot2.getChildren()){
                            shopKey =snapshot2.getKey().toString();
                        }

                        nameOfShop =snapshot2.child("name").getValue(String.class).toUpperCase();


                        shopName.setText(nameOfShop);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        buyShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent login=new Intent(ShareDetails.this,BuyingShareSplashLayout.class);
//                            //login.putExtra("user_id",check_ID);
//                            startActivity(login);
//                            finish();


                DatabaseReference fb_to_read_share = FirebaseDatabase.getInstance().getReference("shares/" + pathOfShare);

                fb_to_read_share.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        List<String> list = new ArrayList<String>();
                        for (DataSnapshot dsp : snapshot.getChildren()) {
                            shareKey = snapshot.getKey().toString();
                        }

                        idOfShop = snapshot.child("adminId").getValue(String.class);
                        totalOfShare = snapshot.child("shareAmount").getValue(String.class);
                        soldOfShare = snapshot.child("soldSum").getValue(String.class);
                        percentageOfShare = snapshot.child("profitPercentage").getValue(String.class);
                        available = ((Integer.parseInt(totalOfShare) - Integer.parseInt(soldOfShare)));
                        buyAmount = amountToBuy.getText().toString();
                        buyAmountInt = (Integer.parseInt(buyAmount));
                        getSoldCount = snapshot.child("soldCount").getValue(String.class);
                        num = Integer.parseInt(getSoldCount);
                        num += 1;

                        intSoldOfSum = Integer.parseInt(soldOfShare);
                        intPercentageOfShare = Integer.parseInt(percentageOfShare);
                        setSoldSum = intSoldOfSum + buyAmountInt;
                        idOfShare=pathOfShare;



                        DatabaseReference admin_to_read_share = FirebaseDatabase.getInstance().getReference("admins/" + idOfShop);

                        admin_to_read_share.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot2) {

                                List<String> list = new ArrayList<String>();
                                for (DataSnapshot dsp : snapshot2.getChildren()) {
                                    adminKey = snapshot2.getKey().toString();
                                }
                                getAdminSoldCount = snapshot2.child("shareCount").getValue(String.class);
                                adminNum = Integer.parseInt(getAdminSoldCount);
                                adminNum += 1;

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        DatabaseReference customer_to_read_share = FirebaseDatabase.getInstance().getReference("users/" + check_ID);

                        customer_to_read_share.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot3) {

                                List<String> list = new ArrayList<String>();
                                for (DataSnapshot dsp : snapshot3.getChildren()) {
                                    userKey = snapshot3.getKey().toString();
                                }
                                getUserSoldCount = snapshot3.child("sharesBoughtCount").getValue(String.class);
                                userNum = Integer.parseInt(getUserSoldCount);
                                userNum += 1;

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });




                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


                        if (buyAmountInt <= available) {


                            if(buyAmountInt > 0){

                                fbUpdate = "shares/" + idOfShare + "/soldShares/" + num;

                                fbUpdateSoldCount = "shares/" + idOfShare + "/soldCount";
                                fbUpdateAmount = "shares/" + idOfShare + "/soldShares/" + num + "/amount";
                                fbUpdateSoldSum = "shares/" + idOfShare + "/soldSum";
                                fbUpdateShopId = "shares/" + idOfShare + "/soldShares/" + num + "/shopId";
                                fbUpdateCustomerId = "shares/" + idOfShare + "/soldShares/" + num + "/customerId";



                                mDbRef = mDatabase.getReference(fbUpdateSoldCount);
                                mDbRef1 = mDatabase.getReference(fbUpdateAmount);
                                mDbRef2 = mDatabase.getReference(fbUpdateSoldSum);
                                mDbRef3 = mDatabase.getReference(fbUpdateShopId);
                                mDbRef4 = mDatabase.getReference(fbUpdateCustomerId);


                                mDbRef.setValue(String.valueOf(num));
                                mDbRef1.setValue(buyAmount);
                                mDbRef2.setValue(String.valueOf(setSoldSum));
                                mDbRef3.setValue(idOfShop);
                                mDbRef4.setValue(check_ID);





                                Toast.makeText(ShareDetails.this, "admin num= "+String.valueOf(adminNum), Toast.LENGTH_SHORT).show();
                                Toast.makeText(ShareDetails.this, "user num= "+String.valueOf(userNum), Toast.LENGTH_SHORT).show();
                                 adminUpdate = "admins/" + idOfShop + "/soldShares";
//
//                                fbUpdateSoldCount = "shares/" + idOfShare + "/soldCount";
//                                fbUpdateAmount = "shares/" + idOfShare + "/soldShares/" + num + "/amount";
//                                fbUpdateSoldSum = "shares/" + idOfShare + "/soldSum";
//                                fbUpdateShopId = "shares/" + idOfShare + "/soldShares/" + num + "/shopId";
//                                fbUpdateCustomerId = "shares/" + idOfShare + "/soldShares/" + num + "/customerId";



                                Intent i=new Intent(ShareDetails.this,BuyingShareSplashLayout.class);
                                Bundle bundle=new Bundle();
                                bundle.putString("id_of_share",pathOfShare);
                                bundle.putString("num",String.valueOf(num));
                                bundle.putString("buyAmount",buyAmount);
                                bundle.putString("setSoldSum",String.valueOf(setSoldSum));
                                bundle.putString("idOfShop",idOfShop);

                                i.putExtras(bundle);
                                finish();
                                startActivity(i);
                               // finish();
                            }
                            else
                            {
                                Toast.makeText(ShareDetails.this, "ENTER A VALID AMOUNT", Toast.LENGTH_SHORT).show();
                            }





//                            String fbUpdate = "shares/" + pathOfShare + "/soldShares/" + num;
//
//                            String fbUpdateSoldCount = "shares/" + pathOfShare + "/soldCount";
//                            String fbUpdateAmount = "shares/" + pathOfShare + "/soldShares/" + num + "/amount";
//                            String fbUpdateSoldSum = "shares/" + pathOfShare + "/soldSum";
//                            String fbUpdateShopId = "shares/" + pathOfShare + "/soldShares/" + num + "/shopId";
//                            String fbUpdateCustomerId = "shares/" + pathOfShare + "/soldShares/" + num + "/customerId";
//
//
//                            DatabaseReference mDbRef = mDatabase.getReference(fbUpdateSoldCount);
//                            DatabaseReference mDbRef1 = mDatabase.getReference(fbUpdateAmount);
//                            DatabaseReference mDbRef2 = mDatabase.getReference(fbUpdateSoldSum);
//                            DatabaseReference mDbRef3 = mDatabase.getReference(fbUpdateShopId);
//                            DatabaseReference mDbRef4 = mDatabase.getReference(fbUpdateCustomerId);
//
//
//                            mDbRef.setValue(String.valueOf(num));
//                            mDbRef1.setValue(buyAmount);
//                            mDbRef2.setValue(String.valueOf(setSoldSum));
//                            mDbRef3.setValue(idOfShop);
//                            mDbRef4.setValue(check_ID);
//
//                            Toast.makeText(ShareDetails.this, "Okay", Toast.LENGTH_SHORT).show();
//                            amountToBuy.setText("");
//

//
//                            Intent login=new Intent(ShareDetails.this,log.class);
//                            //login.putExtra("user_id",check_ID);
//                            startActivity(login);
//                            finish();


                        } else {
                            Toast.makeText(ShareDetails.this, "ENTER A VALID AMOUNT", Toast.LENGTH_SHORT).show();
                        }






            }
        });







        //t1.setText(pathOfShare);




    }
}