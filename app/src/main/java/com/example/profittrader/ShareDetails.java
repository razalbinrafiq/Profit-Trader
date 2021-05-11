package com.example.profittrader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
                Toast.makeText(ShareDetails.this, soldOfShare, Toast.LENGTH_SHORT).show();
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
                        available=((Integer.parseInt(totalOfShare)-Integer.parseInt(soldOfShare)));
                        buyAmount=amountToBuy.getText().toString();
                        buyAmountInt=(Integer.parseInt(buyAmount));
                        String getSoldCount=snapshot.child("soldCount").getValue(String.class);
                        num=Integer.parseInt(getSoldCount);
                        num+=1;

                        intSoldOfSum=Integer.parseInt(soldOfShare);
                        intPercentageOfShare=Integer.parseInt(percentageOfShare);
                        setSoldSum=intSoldOfSum+buyAmountInt;


                        if(buyAmountInt<=available){

                            String fbUpdate="shares/"+pathOfShare+"/soldShares/"+num;

                            String fbUpdateSoldCount="shares/"+pathOfShare+"/soldCount";
                            String fbUpdateAmount=fbUpdate+"/amount";
                            String fbUpdateSoldSum="shares/"+pathOfShare+"/soldSum";


                            DatabaseReference mDbRef = mDatabase.getReference(fbUpdateSoldCount);
                            DatabaseReference mDbRef1 = mDatabase.getReference(fbUpdateAmount);
                            DatabaseReference mDbRef2 = mDatabase.getReference(fbUpdateSoldSum);


                            mDbRef.setValue(String.valueOf(num));
                            mDbRef1.setValue(buyAmount);
                            mDbRef2.setValue(String.valueOf(setSoldSum));


                            Toast.makeText(ShareDetails.this, "Okay", Toast.LENGTH_SHORT).show();
                            amountToBuy.setText("");
                        }
                        else {
                            Toast.makeText(ShareDetails.this, "Entered Large Amount", Toast.LENGTH_SHORT).show();
                        }




                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });





            }
        });




        //t1.setText(pathOfShare);




    }
}