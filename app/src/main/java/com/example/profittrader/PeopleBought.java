package com.example.profittrader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PeopleBought extends AppCompatActivity {


    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<ModelClassOfPeopleBought> userListOfPeopleBought;
    AdapterOfPeopleBought adapter;

    String getShareName, getCurrentNum,getShareAmount;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_bought);






        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation_admin_activity);
        bottomNavigationView.setSelectedItemId(R.id.people2);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.profile2:
                        startActivity(new Intent(getApplicationContext(),AdminProfile.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.home2:
                        startActivity(new Intent(getApplicationContext(),AdminActivity.class));
                        overridePendingTransition(0,0);
                        finish();

                        return true;
                    case R.id.people2:

                        return true;
                }
                return false;
            }
        });



        initData("1,","2","2,3","4,","5","67");


    }


    private void initRecyclerView() {

        recyclerView=findViewById(R.id.recyclerViewOfPeopleBought);
        layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new AdapterOfPeopleBought(userListOfPeopleBought);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private void initData(String name,String amount, String date,String id,String shareId, String number) {


        userListOfPeopleBought = new ArrayList<>();

        SharedPreferences loginDetails =  getSharedPreferences("loginDetails", MODE_PRIVATE);
        String check_ID = loginDetails.getString("id","0");

        DatabaseReference fb_to_read = FirebaseDatabase.getInstance().getReference("admins/"+check_ID+"/soldShares");

        fb_to_read.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                List<String> list=new ArrayList<String>();
                for (DataSnapshot dsp : snapshot.getChildren()){
                    list.add(String.valueOf(dsp.getKey()));
                }

                for(final String data:list){



                    getShareAmount=snapshot.child(data).child("amountSum").getValue(String.class);
                    getShareName=snapshot.child(data).child("name").getValue(String.class);
                    getCurrentNum=data;
                  //  String getShareId=snapshot.child(data).child("adminId").getValue(String.class);
                  //  String getShareDate=snapshot.child(data).child("date").getValue(String.class);

                        i=i+1;

//                       // Toast.makeText(context, "hy", Toast.LENGTH_SHORT).show();

                    if(Integer.parseInt(getShareAmount)!=0)
                        userListOfPeopleBought.add(new ModelClassOfPeopleBought(R.drawable.profile1, getShareName,"₹ "+ getShareAmount, getCurrentNum, "3", data.toString(), String.valueOf(i)));


                    initRecyclerView();

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }
}