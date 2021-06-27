package com.example.profittrader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserProfile extends AppCompatActivity {

    TextView nameTextView,emailTextView,phoneTextView;
    Button passwordEditButton;
    String check_ID;
    String idString,nameString,emailString,phoneString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);


        nameTextView=(TextView)findViewById(R.id.nameTextView);
        emailTextView=(TextView)findViewById(R.id.emailIDTextView) ;
        phoneTextView=(TextView)findViewById(R.id.phoneTextView) ;
        passwordEditButton=(Button)findViewById(R.id.editPasswordButton);


        SharedPreferences loginDetails =  getSharedPreferences("loginDetails", MODE_PRIVATE);
        check_ID = loginDetails.getString("id","0");

        DatabaseReference fb_to_read = FirebaseDatabase.getInstance().getReference("users/"+check_ID+"/userDetails");

        fb_to_read.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<String> list=new ArrayList<String>();
                for (DataSnapshot dsp : snapshot.getChildren()){
                   idString=snapshot.getKey().toString();
                }

                nameString=snapshot.child("name").getValue(String.class).toUpperCase();
                emailString=snapshot.child("emailId").getValue(String.class);
                phoneString=snapshot.child("mobile").getValue(String.class);

                nameTextView.setText(nameString);
                emailTextView.setText(emailString);
                phoneTextView.setText(phoneString);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        passwordEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(UserProfile.this,EditProfile.class);
                startActivity(i);

            }
        });




        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.profile);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.profile:
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),UserActivity.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.wallet:
                        startActivity(new Intent(getApplicationContext(),UserWallet.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                }
                return false;
            }
        });



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