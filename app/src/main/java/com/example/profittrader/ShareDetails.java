package com.example.profittrader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShareDetails extends AppCompatActivity {

    TextView shopName,availableAmount,totalAmount,percentageAmount;
    String pathOfShare;
    String key,user;
    String nameOfShop,idOfShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_details);

      //  t1=findViewById(R.id.textView);
        shopName=findViewById(R.id.shopNameTextView);
        availableAmount=findViewById(R.id.availableAmountTextView);
        totalAmount=findViewById(R.id.totalAmountTextView);
        percentageAmount=findViewById(R.id.percentageAmountTextView);

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
                    key =snapshot.getKey().toString();
                }

               idOfShop =snapshot.child("adminId").getValue(String.class).toUpperCase();



//                DatabaseReference fb_to_read_shop = FirebaseDatabase.getInstance().getReference("admins/"+idOfShop+"/userDetails");
//
//                fb_to_read_shop.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                        List<String> list=new ArrayList<String>();
//                        for (DataSnapshot dsp : snapshot.getChildren()){
//                            user =snapshot.getKey().toString();
//                        }
//
//                        nameOfShop =snapshot.child("name").getValue(String.class).toUpperCase();
//                        shopName.setText(nameOfShop);
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });


                shopName.setText(idOfShop);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        //t1.setText(pathOfShare);




    }
}