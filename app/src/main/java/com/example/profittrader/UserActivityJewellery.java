package com.example.profittrader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserActivityJewellery extends AppCompatActivity {



    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<ModelClass> userList;
    Adapter adapter;


//    MeowBottomNavigation bottomNavigation;


    private GridLayout mLayout;
    Button addChittyButton,test;
    DynamicViews dmv;
    int i=1;
    int num=1;
    int count;
    Context context;
    Button button;
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference getRef1;
    String chittynameEditText,chittydateEditText,chittyamountEditText;
    String numOfChitty;
    EditText chittyname,chittydate,chittyamount;
    String getShareName, getShareAmount, getShareDate,getShareId;
    String user = null;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_jewellery);

        Bundle login = getIntent().getExtras();
        if (login != null) {
            user = login.getString("user_id");
            //  Toast.makeText(MainActivity.this,user, Toast.LENGTH_SHORT).show();
        }

//
//        bottomNavigation =findViewById(R.id.bottom_navigation);
//        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_profile));
//        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_home));
//        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_wallet));


        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation_jewellery);
        bottomNavigationView.setSelectedItemId(R.id.home);

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




        getRef1 = FirebaseDatabase.getInstance().getReference("shares");
        //addChittyButton=(Button)findViewById(R.id.addChittyButton);
        // mLayout=(GridLayout) findViewById(R.id.mylayout);


        initData("1,","2","2,3","4,","5","67");
        //  initRecyclerView();






    }

    private void initRecyclerView() {
        recyclerView=findViewById(R.id.recyclerView);
        layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new Adapter(userList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private void initData(String name,String amount, String date,String id,String shareId, String number) {


        userList = new ArrayList<>();



        DatabaseReference fb_to_read = FirebaseDatabase.getInstance().getReference("shares");

        fb_to_read.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                List<String> list=new ArrayList<String>();
                for (DataSnapshot dsp : snapshot.getChildren()){
                    list.add(String.valueOf(dsp.getKey()));
                }

                for(final String data:list){

                    getShareName=snapshot.child(data).child("shopName").getValue(String.class).toUpperCase();
                    getShareAmount=snapshot.child(data).child("shareAmount").getValue(String.class);
                    getShareDate="Valid till "+ snapshot.child(data).child("date").getValue(String.class);
                    getShareId=snapshot.child(data).child("adminId").getValue(String.class);
                    String getShareType=snapshot.child(data).child("typeOfShop").getValue(String.class);

                    if(getShareType.equals("Jewellery")) {
                        i++;
                        userList.add(new ModelClass(R.drawable.gold1, getShareName, getShareAmount, getShareDate, getShareId, data.toString(), String.valueOf(i)));
                    }



                }

                initRecyclerView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


    public void logout(){

        SharedPreferences loginDetails = getSharedPreferences("loginDetails", MODE_PRIVATE);
        SharedPreferences.Editor editor = loginDetails.edit();
        editor.putString("id","0" );
        editor.putString("password","0" );
        editor.putString("mode","0" );
        editor.commit();

        Intent login=new Intent(UserActivityJewellery.this,Login.class);
        finish();
        startActivity(login);
        System.exit(0);

    }

    public void profiles()
    {

        Intent login=new Intent(UserActivityJewellery.this,UserActivity.class);
        login.putExtra("user_id",user);
        startActivity(login);
        finish();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.jewellery_logout,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:logout();
                return  true;
            case R.id.profile:profiles();
                return  true;
            default: return super.onOptionsItemSelected(item);
        }

    }

 /*   @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }*/


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