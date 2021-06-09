package com.example.profittrader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
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

public class RedeemPoints extends AppCompatActivity {

    TextView availableAmountTextView,redeemedAmountTextView;
    EditText amountEditText;
    Button redeemButton;
    String pathOfShare;
    String check_ID,userKey;
    String fbUpdateAmountSum,fbUpdateRedeemedSum;

    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference mDbRef1, mDbRef2;


    int amountSum,redeemedSum=0,toRedeem;
    String amountSumString,redeemedSumString,toRedeemString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redeem_points);


        availableAmountTextView=(TextView)findViewById(R.id.availableAmountTextView);
        redeemedAmountTextView=(TextView)findViewById(R.id.redeemedAmountTextView);
        amountEditText=(EditText) findViewById(R.id.amountEditText);
        redeemButton=(Button) findViewById(R.id.redeemButton);



        Bundle login = getIntent().getExtras();
        if (login != null) {
            pathOfShare = login.getString("path");
            //  Toast.makeText(MainActivity.this,user, Toast.LENGTH_SHORT).show();
        }


        SharedPreferences loginDetails =  getSharedPreferences("loginDetails", MODE_PRIVATE);
        check_ID = loginDetails.getString("id","0");

        DatabaseReference fb_to_read = FirebaseDatabase.getInstance().getReference("admins/"+pathOfShare+"/soldShares/"+check_ID);

        fb_to_read.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                List<String> list=new ArrayList<String>();
                for (DataSnapshot dsp : snapshot.getChildren()){
                    userKey=snapshot.getKey().toString();
                }
                amountSumString=snapshot.child("amountSum").getValue(String.class);
                redeemedSumString=snapshot.child("redeemedSum").getValue(String.class);


                availableAmountTextView.setText(amountSumString);

                if(redeemedSumString!=null){

                    redeemedAmountTextView.setText(redeemedSumString);
                   // buyAmountIntInAdmin=buyAmountIntInAdmin+ Integer.parseInt(snapshot2.child("soldShares").child(check_ID).child("amountSum").getValue(String.class));

                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        redeemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                toRedeem=Integer.parseInt(amountEditText.getText().toString());

                DatabaseReference fb_to_read = FirebaseDatabase.getInstance().getReference("admins/"+pathOfShare+"/soldShares/"+check_ID);

                fb_to_read.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        List<String> list=new ArrayList<String>();
                        for (DataSnapshot dsp : snapshot.getChildren()){
                            userKey=snapshot.getKey().toString();
                        }
                        amountSumString=snapshot.child("amountSum").getValue(String.class);
                        redeemedSumString=snapshot.child("redeemedSum").getValue(String.class);

                        amountSum=Integer.parseInt(amountSumString);


                        availableAmountTextView.setText(amountSumString);

                        if(redeemedSumString!=null){
                            redeemedSum=Integer.parseInt(redeemedSumString);
                            toRedeem=Integer.parseInt(amountEditText.getText().toString());
                            redeemedSum =redeemedSum+ toRedeem;
                        }
                        else{
                            toRedeem=Integer.parseInt(amountEditText.getText().toString());
                            redeemedSum=toRedeem;
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });




                if(toRedeem>0 && toRedeem<amountSum){

                    toRedeem=amountSum-toRedeem;

                    fbUpdateAmountSum="admins/" + pathOfShare + "/soldShares/"+check_ID + "/amountSum";
                    fbUpdateRedeemedSum="admins/" + pathOfShare + "/soldShares/"+check_ID + "/redeemedSum";

                    mDbRef1 = mDatabase.getReference(fbUpdateAmountSum);
                    mDbRef2 = mDatabase.getReference(fbUpdateRedeemedSum);

                    mDbRef1.setValue(String.valueOf(toRedeem));
                    mDbRef2.setValue(String.valueOf(redeemedSum));


                    Intent iu=new Intent(RedeemPoints.this,RedeemPoints.class);
                    iu.putExtra("path",pathOfShare);
                    finish();
                    startActivity(iu);

                }

            }
        });

        Toast.makeText(this, "path: "+ pathOfShare, Toast.LENGTH_SHORT).show();





    }


    private Boolean exit = false;
    @Override
    public void onBackPressed() {
        if (exit) {
            finish();
            System.exit(0);// finish activity
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }

    }
}