package com.example.profittrader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RedeemedHistory extends AppCompatActivity {


    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<ModelClassOfRedeemedHistory> userListOfRedeemedHistory;
    AdapterOfRedeemedHistory adapter;

    String check_ID;
    String getShareAmount,getShareUserId,getShareName,getShareTime,getShareDate;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redeemed_history);

        initData("1,","2","2,3","4,","5","67");

    }



    private void initRecyclerView() {

        recyclerView=findViewById(R.id.recyclerViewOfRedeemedHistory);
        layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new AdapterOfRedeemedHistory(userListOfRedeemedHistory);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private void initData(String name,String amount, String date,String id,String shareId, String number) {


        userListOfRedeemedHistory = new ArrayList<>();

        SharedPreferences loginDetails =  getSharedPreferences("loginDetails", MODE_PRIVATE);
        check_ID = loginDetails.getString("id","0");
        Toast.makeText(this, "kk:"+check_ID, Toast.LENGTH_SHORT).show();
        DatabaseReference fb_to_read = FirebaseDatabase.getInstance().getReference("admins/"+check_ID+"/redeemedMembers/");

        fb_to_read.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                List<String> list=new ArrayList<String>();
                for (DataSnapshot dsp : snapshot.getChildren()){
                    list.add(String.valueOf(dsp.getKey()));
                }

                for(final String data:list){

                    getShareAmount=snapshot.child(data).child("amount").getValue(String.class);
                    getShareUserId=snapshot.child(data).child("id").getValue(String.class);
                    getShareName=snapshot.child(data).child("name").getValue(String.class);
                    getShareTime=snapshot.child(data).child("time").getValue(String.class);
                    getShareDate=snapshot.child(data).child("date").getValue(String.class);
//                    //String getShareId=snapshot.child(data).child("adminId").getValue(String.class);
//                    //String getShareDate=snapshot.child(data).child("date").getValue(String.class);
//                    //Toast.makeText(context, "hy", Toast.LENGTH_SHORT).show();
//
                    i=i+1;
//
////                       // Toast.makeText(context, "hy", Toast.LENGTH_SHORT).show();
//
                    userListOfRedeemedHistory.add(new ModelClassOfRedeemedHistory(R.drawable.dollar5, getShareUserId, getShareAmount, getShareName, getShareTime, getShareDate, String.valueOf(i)));
//
//
//
                    initRecyclerView();

                    Toast.makeText(RedeemedHistory.this, data, Toast.LENGTH_SHORT).show();
//
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }


}