package com.example.profittrader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
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

public class ShareDetailsJewellery extends AppCompatActivity {


    TextView shopName,availableAmount,totalAmount,percentageAmount;
    EditText amountToBuy;
    Button buyShare;
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    String pathOfShare;
    String shareKey,shopKey;
    String idOfShop,nameOfShop,availableOfShare,totalOfShare,soldOfShare,percentageOfShare;
    int intSoldOfSum,intPercentageOfShare,setSoldSum;
    int available,buyAmountInt,num,checkBuyAmountInt;
    int buyAmountIntInAdmin;
    int buyAmountIntInUser;
    String buyAmount;
    String getSoldCount;
    String fbUpdate,fbUpdateSoldCount,fbUpdateAmount,fbUpdateNameOfUser,fbUpdateSoldSum,fbUpdateCountOfSharesBought,fbUpdateSharesBoughtSum,fbUpdateShopId,fbUpdateCustomerId;
    String fbUpdateInAdmin,fbUpdateAmountInAdmin;
    String fbUpdateInUser,fbUpdateAmountInUser;
    String idOfShare,getIdOfShop;
    String countOfSharesBought,nameOfUserBought;
    String countOfSharesBoughtInAdmin,countOfSharesBoughtInUser;

    String nameOfShopBought,fbUpdateNameOfUserInAdmin,fbUpdateNameOfShopInUser;

    String adminKey,getAdminSoldCount,adminUpdate;
    int adminNum;
    String userKey,getUserSoldCount,userUpdate;
    int userNum;

    String check_ID;



    DatabaseReference mDbRef,mDbRef1, mDbRef2, mDbRef3, mDbRef4,mDbRef5,mDbRef6,mDbRef7;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_details_jewellery);




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
                percentageAmount.setText(percentageOfShare+" Grams");

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
                        buyAmountIntInAdmin = (Integer.parseInt(buyAmount));
                        buyAmountIntInUser = buyAmountInt;
                        getSoldCount = snapshot.child("soldCount").getValue(String.class);
                        num = Integer.parseInt(getSoldCount);
                        num += 1;
                        checkBuyAmountInt=Integer.parseInt(buyAmount);

                        countOfSharesBought = snapshot.child("soldShares").child(check_ID).child("amountSum").getValue(String.class);




                        intSoldOfSum = Integer.parseInt(soldOfShare);
                        intPercentageOfShare = Integer.parseInt(percentageOfShare);
                        setSoldSum = intSoldOfSum + buyAmountInt;
                        idOfShare=pathOfShare;


                        if(countOfSharesBought!=null){
                            buyAmountInt=buyAmountInt+ Integer.parseInt(snapshot.child("soldShares").child(check_ID).child("amountSum").getValue(String.class));
                        }
                        else {

                        }



                        DatabaseReference fb_to_read_admin = FirebaseDatabase.getInstance().getReference("admins/" + idOfShop);
                        fb_to_read_admin.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot2) {

                                List<String> list = new ArrayList<String>();
                                for (DataSnapshot dsp : snapshot2.getChildren()) {
                                    adminKey = snapshot2.getKey().toString();
                                }

                                countOfSharesBoughtInAdmin = snapshot2.child("soldShares").child(check_ID).child("amountSum").getValue(String.class);
                                nameOfShopBought = snapshot2.child("userDetails").child("name").getValue(String.class);

                                if(countOfSharesBoughtInAdmin!=null){

                                    buyAmountIntInAdmin=buyAmountIntInAdmin+ Integer.parseInt(snapshot2.child("soldShares").child(check_ID).child("amountSum").getValue(String.class));

                                }
                                else {
                                    // Toast.makeText(ShareDetails.this, "Null", Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull  DatabaseError error) {

                            }
                        });


                        DatabaseReference fb_to_read_user = FirebaseDatabase.getInstance().getReference("users/" + check_ID);
                        fb_to_read_user.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot3) {

                                List<String> list = new ArrayList<String>();
                                for (DataSnapshot dsp : snapshot3.getChildren()) {
                                    userKey = snapshot3.getKey().toString();
                                }




                                countOfSharesBoughtInUser = snapshot3.child("soldShares").child(idOfShop).child("amountSum").getValue(String.class);
                                nameOfUserBought = snapshot3.child("userDetails").child("name").getValue(String.class);

                                if(countOfSharesBoughtInUser!=null){

                                    buyAmountIntInUser=buyAmountIntInUser+ Integer.parseInt(snapshot3.child("soldShares").child(idOfShop).child("amountSum").getValue(String.class));

                                }
                                else {
                                    // Toast.makeText(ShareDetails.this, "Null", Toast.LENGTH_SHORT).show();
                                }





                            }

                            @Override
                            public void onCancelled(@NonNull  DatabaseError error) {

                            }
                        });




                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });




                if (checkBuyAmountInt <= available && buyAmountInt>0) {


                    //                if(buyAmountInt > 0){






                    fbUpdate = "shares/" + idOfShare + "/soldShares/" + check_ID;

                    fbUpdateSoldCount = "shares/" + idOfShare + "/soldCount";
                    fbUpdateAmount = "shares/" + idOfShare + "/soldShares/" + check_ID + "/amountSum";
                    fbUpdateNameOfUser = "shares/" + idOfShare + "/soldShares/" + check_ID + "/name";
                    fbUpdateSoldSum = "shares/" + idOfShare + "/soldSum";


                    fbUpdateInAdmin = "admins/" + idOfShop + "/soldShares/"+check_ID;
                    fbUpdateAmountInAdmin= "admins/" + idOfShop + "/soldShares/"+check_ID + "/amountSum";
                    fbUpdateNameOfUserInAdmin = "admins/" + idOfShop + "/soldShares/"+check_ID + "/name";

                    fbUpdateInUser= "users/" + check_ID + "/soldShares/"+idOfShop;
                    fbUpdateAmountInUser= "users/" + check_ID + "/soldShares/"+idOfShop + "/amountSum";
                    fbUpdateNameOfShopInUser = "users/" + check_ID + "/soldShares/"+idOfShop + "/nameOfShop";
                    //fbUpdateSoldSumInAdmin = "admins/" + idOfShop + "/soldSum";

                    mDbRef = mDatabase.getReference(fbUpdateSoldCount);
                    mDbRef1 = mDatabase.getReference(fbUpdateAmount);
                    mDbRef2 = mDatabase.getReference(fbUpdateSoldSum);
                    mDbRef5 = mDatabase.getReference(fbUpdateNameOfUser);

                    mDbRef3 = mDatabase.getReference(fbUpdateAmountInAdmin);
                    mDbRef6 = mDatabase.getReference(fbUpdateNameOfUserInAdmin);

                    mDbRef4 = mDatabase.getReference(fbUpdateAmountInUser);
                    mDbRef7 = mDatabase.getReference(fbUpdateNameOfShopInUser);




                    mDbRef.setValue(String.valueOf(num));
                    mDbRef1.setValue(String.valueOf(buyAmountInt+buyAmountInt/100*Integer.parseInt(percentageOfShare)));
                    mDbRef2.setValue(String.valueOf(setSoldSum));
                    mDbRef5.setValue(String.valueOf(nameOfUserBought));

                    mDbRef3.setValue(String.valueOf(buyAmountIntInAdmin+buyAmountInt/100*Integer.parseInt(percentageOfShare)));
                    mDbRef6.setValue(String.valueOf(nameOfUserBought));


                    mDbRef7.setValue(String.valueOf(nameOfShopBought));





                    Intent i=new Intent(ShareDetailsJewellery.this,BuyingShareSplashLayout.class);
                    Bundle bundle=new Bundle();
                    //bundle.putString("id_of_share",pathOfShare);
//                                bundle.putString("num",String.valueOf(num));
//                                bundle.putString("buyAmount",buyAmount);
//                                bundle.putString("setSoldSum",String.valueOf(setSoldSum));
//                                bundle.putString("idOfShop",idOfShop);

                    i.putExtras(bundle);
                    finish();
                    startActivity(i);






                    // finish();
//                           // }
//                            else
//                            {
//                                Toast.makeText(ShareDetails.this, "ENTER A VALID AMOUNT", Toast.LENGTH_SHORT).show();
//                            }








                } else {
                    Toast.makeText(ShareDetailsJewellery.this, "ENTER A VALID AMOUNT", Toast.LENGTH_SHORT).show();
                }





            }
        });








    }
}