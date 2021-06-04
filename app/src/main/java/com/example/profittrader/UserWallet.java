package com.example.profittrader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
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

public class UserWallet extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<ModelClassOfUserWallet> userListOfUserWallet;
    AdapterOfUserWallet adapter;

    String shopName;

    int i=0,totalAmountInt=0;

    TextView totalTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_wallet);



        totalTextView=(TextView)findViewById(R.id.totalValueTextView);

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.wallet);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(),UserProfile.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),UserActivity.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.wallet:
                        return true;

                }
                return false;
            }
        });


        initData("1,","2","2,3","4,","5","67");



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


    private void initRecyclerView() {

        recyclerView=findViewById(R.id.recyclerViewOfUserWallet);
        layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new AdapterOfUserWallet(userListOfUserWallet);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private void initData(String name,String amount, String date,String id,String shareId, String number) {


        userListOfUserWallet = new ArrayList<>();

        SharedPreferences loginDetails =  getSharedPreferences("loginDetails", MODE_PRIVATE);
        String check_ID = loginDetails.getString("id","0");

        DatabaseReference fb_to_read = FirebaseDatabase.getInstance().getReference("users/"+check_ID+"/soldShares");

        fb_to_read.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                List<String> list=new ArrayList<String>();
                for (DataSnapshot dsp : snapshot.getChildren()){
                    list.add(String.valueOf(dsp.getKey()));
                }

                for(final String data:list){



                    DatabaseReference fb_to_read_admins = FirebaseDatabase.getInstance().getReference("admins/"+data);

                    fb_to_read_admins.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot2) {


                            List<String> list2=new ArrayList<String>();
                            for (DataSnapshot dsp : snapshot2.getChildren()){
                                list2.add(String.valueOf(dsp.getKey()));
                            }


                            i=i+1;


                            String getShareAmount=snapshot2.child("soldShares").child(check_ID).child("amountSum").getValue(String.class);
                            shopName=snapshot2.child("userDetails").child("name").getValue(String.class);
                            totalAmountInt=totalAmountInt+Integer.parseInt(getShareAmount);
                            totalTextView.setText("₹ "+String.valueOf(totalAmountInt));
                            userListOfUserWallet.add(new ModelClassOfUserWallet(R.drawable.profile1, shopName, getShareAmount, data, "3", data.toString(), String.valueOf(i)));

                            initRecyclerView();






                        }

                        @Override
                        public void onCancelled(@NonNull  DatabaseError error) {

                        }
                    });



                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });






    }

}