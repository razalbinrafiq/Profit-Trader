package com.example.profittrader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
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

public class AdminActivity extends AppCompatActivity {



    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<ModelClassOfAdminActivity>userListOfAdminActivity;
    AdapterOfAdminActivity adapter;


    private GridLayout mLayout;
    Button addShareButton,test;
    DynamicViews dmv;
    int i=2;
    int num=1;
    int count;
    Context context;
    Button button;
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference getRef1,getRef2;
    String sharenameEditText,sharedateEditText,shareamountEditText;
    String numOfShares;
    EditText shareProfit,sharedate,shareamount;
    String getShareName,getCurrentNum;
    String user = null;
    String nameOfShop = null;
    String addTo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);


        Bundle login = getIntent().getExtras();
        if (login != null) {
            user = login.getString("user_id");
            //  Toast.makeText(MainActivity.this,user, Toast.LENGTH_SHORT).show();
        }
        else{
            SharedPreferences loginDetails =  getSharedPreferences("loginDetails", MODE_PRIVATE);
            user= loginDetails.getString("id","0");

        }


        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation_admin_activity);
        bottomNavigationView.setSelectedItemId(R.id.home2);

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
                        return true;
                    case R.id.people2:
                        startActivity(new Intent(getApplicationContext(),PeopleBought.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                }
                return false;
            }
        });




        getRef2 = FirebaseDatabase.getInstance().getReference("admins/"+user+"/userDetails/name");

        getRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                nameOfShop=snapshot.getValue().toString();
                Toast.makeText(AdminActivity.this, nameOfShop, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        getRef1 = FirebaseDatabase.getInstance().getReference("shareCount");

        addShareButton=(Button)findViewById(R.id.addShareButton);








        initData("1,","2","2,3","4,","5","67");

    }




    public void addShares()
    {


        getRef1 = FirebaseDatabase.getInstance().getReference("shareCount");
        getRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                num= (int) snapshot.getValue(Integer.class);
                num=num+1;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(AdminActivity.this);
        LayoutInflater inflater = getLayoutInflater();

        SharedPreferences loginDetails =  getSharedPreferences("loginDetails", MODE_PRIVATE);
        String type= loginDetails.getString("type","0");

        if(type.equals("Jewellery")){


            final View dialoglayout= inflater.inflate(R.layout.add_gold_share,null);
            builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    shareProfit=(EditText)dialoglayout.findViewById(R.id.shareProfitEditText);
                    sharedate=(EditText)dialoglayout.findViewById(R.id.sharedateEditText);
                    shareamount=(EditText)dialoglayout.findViewById(R.id.shareamountEditText);

                    String shareProfitPercentage=shareProfit.getText().toString();
                    String chittyDate=sharedate.getText().toString();
                    String chittyPaymentDate=shareamount.getText().toString();

                    String fbChittynum="shares/"+num;
                    String fbChittyname=fbChittynum+"/adminId";
                    String fbdate=fbChittynum +"/date";
                    String fbpaymentdate=fbChittynum +"/shareAmount";
                    String fbStatus=fbChittynum+"/status";
                    String shareProfitPath=fbChittynum+"/profitPercentage";
                    String fbShopName=fbChittynum+"/shopName";
                    String soldShareCount=fbChittynum+"/soldCount";
                    String soldShareSum=fbChittynum+"/soldSum";
                    String redeemedShareSum=fbChittynum+"/redeemedSum";
                    String typeOfShop=fbChittynum+"/typeOfShop";

                    DatabaseReference mDbRef1 = mDatabase.getReference(fbChittyname);
                    DatabaseReference mDbRef2 = mDatabase.getReference(fbdate);
                    DatabaseReference mDbRef3= mDatabase.getReference(fbpaymentdate);
                    DatabaseReference mDbRef4= mDatabase.getReference("shareCount");
                    DatabaseReference mDbRef5= mDatabase.getReference(fbStatus);
                    DatabaseReference mDbRef6 = mDatabase.getReference(shareProfitPath);
                    DatabaseReference mDbRef7 = mDatabase.getReference(fbShopName);
                    DatabaseReference mDbRef8 = mDatabase.getReference(soldShareCount);
                    DatabaseReference mDbRef9 = mDatabase.getReference(soldShareSum);
                    DatabaseReference mDbRef10 = mDatabase.getReference(redeemedShareSum);
                    DatabaseReference mDbRef11 = mDatabase.getReference(typeOfShop);

                    mDbRef1.setValue(user);
                    mDbRef2.setValue(chittyDate);
                    mDbRef3.setValue(chittyPaymentDate);
                    mDbRef4.setValue(num);
                    mDbRef5.setValue("active");
                    mDbRef6.setValue(shareProfitPercentage);
                    mDbRef7.setValue(nameOfShop);
                    mDbRef8.setValue("0");
                    mDbRef9.setValue("0");
                    mDbRef10.setValue("0");
                    mDbRef11.setValue(type);


                    dialog.dismiss();


                    Intent iu=new Intent(AdminActivity.this,AdminActivity.class);
                    iu.putExtra("user_id",user);
                    finish();
                    startActivity(iu);
                    //System.exit(0);

                }

            });

            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //               Toast.makeText(getApplicationContext(), "No button Clicked", Toast.LENGTH_LONG).show();
                    Log.i("Code2care ", "No button Clicked!");
                    dialog.dismiss();

                }
            });

            builder.setView(dialoglayout);
            builder.show();


        }
        else{
            final View dialoglayout= inflater.inflate(R.layout.add_shares,null);
            builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    shareProfit=(EditText)dialoglayout.findViewById(R.id.shareProfitEditText);
                    sharedate=(EditText)dialoglayout.findViewById(R.id.sharedateEditText);
                    shareamount=(EditText)dialoglayout.findViewById(R.id.shareamountEditText);

                    String shareProfitPercentage=shareProfit.getText().toString();
                    String chittyDate=sharedate.getText().toString();
                    String chittyPaymentDate=shareamount.getText().toString();

                    String fbChittynum="shares/"+num;
                    String fbChittyname=fbChittynum+"/adminId";
                    String fbdate=fbChittynum +"/date";
                    String fbpaymentdate=fbChittynum +"/shareAmount";
                    String fbStatus=fbChittynum+"/status";
                    String shareProfitPath=fbChittynum+"/profitPercentage";
                    String fbShopName=fbChittynum+"/shopName";
                    String soldShareCount=fbChittynum+"/soldCount";
                    String soldShareSum=fbChittynum+"/soldSum";
                    String redeemedShareSum=fbChittynum+"/redeemedSum";
                    String typeOfShop=fbChittynum+"/typeOfShop";

                    DatabaseReference mDbRef1 = mDatabase.getReference(fbChittyname);
                    DatabaseReference mDbRef2 = mDatabase.getReference(fbdate);
                    DatabaseReference mDbRef3= mDatabase.getReference(fbpaymentdate);
                    DatabaseReference mDbRef4= mDatabase.getReference("shareCount");
                    DatabaseReference mDbRef5= mDatabase.getReference(fbStatus);
                    DatabaseReference mDbRef6 = mDatabase.getReference(shareProfitPath);
                    DatabaseReference mDbRef7 = mDatabase.getReference(fbShopName);
                    DatabaseReference mDbRef8 = mDatabase.getReference(soldShareCount);
                    DatabaseReference mDbRef9 = mDatabase.getReference(soldShareSum);
                    DatabaseReference mDbRef10 = mDatabase.getReference(redeemedShareSum);
                    DatabaseReference mDbRef11 = mDatabase.getReference(typeOfShop);

                    mDbRef1.setValue(user);
                    mDbRef2.setValue(chittyDate);
                    mDbRef3.setValue(chittyPaymentDate);
                    mDbRef4.setValue(num);
                    mDbRef5.setValue("active");
                    mDbRef6.setValue(shareProfitPercentage);
                    mDbRef7.setValue(nameOfShop);
                    mDbRef8.setValue("0");
                    mDbRef9.setValue("0");
                    mDbRef10.setValue("0");
                    mDbRef11.setValue(type);


                    dialog.dismiss();


                    Intent iu=new Intent(AdminActivity.this,AdminActivity.class);
                    iu.putExtra("user_id",user);
                    finish();
                    startActivity(iu);
                    //System.exit(0);

                }

            });

            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //               Toast.makeText(getApplicationContext(), "No button Clicked", Toast.LENGTH_LONG).show();
                    Log.i("Code2care ", "No button Clicked!");
                    dialog.dismiss();

                }
            });

            builder.setView(dialoglayout);
            builder.show();


        }





    }



    private void initRecyclerView() {

        recyclerView=findViewById(R.id.recyclerViewOfAdminActivity);
        layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new AdapterOfAdminActivity(userListOfAdminActivity);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private void initData(String name,String amount, String date,String id,String shareId, String number) {


        userListOfAdminActivity = new ArrayList<>();


        DatabaseReference fb_to_read = FirebaseDatabase.getInstance().getReference("shares");

        fb_to_read.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                List<String> list=new ArrayList<String>();
                for (DataSnapshot dsp : snapshot.getChildren()){
                    list.add(String.valueOf(dsp.getKey()));
                }

                for(final String data:list){
                    dmv=new DynamicViews(context);

                    String status=snapshot.child(data).child("status").getValue(String.class);

                    //                mLayout.addView(dmv.slnoTextVIew(getApplicationContext(),"data"),i);
                    //               mLayout.addView(dmv.chittalIDTextView(getApplicationContext(), "f"),i+1);
                    //                mLayout.addView(dmv.nameTextVIew(getApplicationContext(),data),i);


                    getShareName=snapshot.child(data).child("shareAmount").getValue(String.class);
                    String getSharePercentage=snapshot.child(data).child("profitPercentage").getValue(String.class);
                    getCurrentNum=data;
                    String getShareId=snapshot.child(data).child("adminId").getValue(String.class);
                    String getShareDate=snapshot.child(data).child("date").getValue(String.class);


                    String postFixTo;

                    SharedPreferences loginDetails =  getSharedPreferences("loginDetails", MODE_PRIVATE);
                    String type= loginDetails.getString("type","0");

                    if(type.equals("Jewellery")){
                        postFixTo=" grams";
                        if(getShareId.equals(user)){

                            i=i+1;

                            userListOfAdminActivity.add(new ModelClassOfAdminActivity(R.drawable.dollar5, getShareDate, getShareName+postFixTo, getSharePercentage, "3", data.toString(), String.valueOf(i)));

                        }

                    }
                    else {
                       postFixTo=" %";
                        if(getShareId.equals(user)){

                            i=i+1;

                            userListOfAdminActivity.add(new ModelClassOfAdminActivity(R.drawable.dollar5, getShareDate, getShareName, getSharePercentage+postFixTo, "3", data.toString(), String.valueOf(i)));

                        }


                    }



                    initRecyclerView();

                }


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

        Intent login=new Intent(AdminActivity.this,Login.class);
        finish();
        startActivity(login);
        System.exit(0);

    }


    public void redeemedHistory()
    {
       // String sendingprofile="admins/"+user+"/adminDetails";

        Intent profile=new Intent(AdminActivity.this,RedeemedHistory.class);
        //profile.putExtra("key",sendingprofile);
        startActivity(profile);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.agent,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.addShareButton:addShares();
                return true;
            case R.id.logout:logout();
                return  true;
            case R.id.profile:redeemedHistory();
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