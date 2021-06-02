package com.example.profittrader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ParticularSharesBought extends AppCompatActivity {

    String pathOfShare;
    String getShareName,getShareAmount,getShareUserId,getPathOfShare,getCurrentNum;
    int i=0;

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<ModelClassOfParticularSharesBought> userListOfParticularSharesBought;
    AdapterOfParticularSharesBought adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_particular_shares_bought);

        Bundle login = getIntent().getExtras();
        if (login != null) {
            pathOfShare = login.getString("path");
            //  Toast.makeText(MainActivity.this,user, Toast.LENGTH_SHORT).show();
        }


        Toast.makeText(this, pathOfShare, Toast.LENGTH_SHORT).show();


        initData("1,","2","2,3","4,","5","67");



    }






    private void initRecyclerView() {

        recyclerView=findViewById(R.id.recyclerViewOfParticularSharesBought);
        layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new AdapterOfParticularSharesBought(userListOfParticularSharesBought);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private void initData(String name,String amount, String date,String id,String shareId, String number) {


        userListOfParticularSharesBought = new ArrayList<>();


        DatabaseReference fb_to_read = FirebaseDatabase.getInstance().getReference("shares/"+pathOfShare+"/soldShares/");

        fb_to_read.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                List<String> list=new ArrayList<String>();
                for (DataSnapshot dsp : snapshot.getChildren()){
                    list.add(String.valueOf(dsp.getKey()));
                }

                for(final String data:list){

                    getShareAmount=snapshot.child(data).child("amountSum").getValue(String.class);
                    getShareUserId="ID: "+ data;
                    getShareName=snapshot.child(data).child("name").getValue(String.class);
                    //String getShareId=snapshot.child(data).child("adminId").getValue(String.class);
                    //String getShareDate=snapshot.child(data).child("date").getValue(String.class);
                    //Toast.makeText(context, "hy", Toast.LENGTH_SHORT).show();

                        i=i+1;

//                       // Toast.makeText(context, "hy", Toast.LENGTH_SHORT).show();

                        userListOfParticularSharesBought.add(new ModelClassOfParticularSharesBought(R.drawable.dollar5, getShareName, getShareUserId, getShareAmount, "3", data.toString(), String.valueOf(i)));



                    initRecyclerView();

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }




}