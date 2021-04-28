package com.example.profittrader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {



    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<ModelClass> userList;
    Adapter adapter;


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

//
//
//    public TextView sentText(Context context, String text){
//        final ViewGroup.LayoutParams lparams= new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        final TextView textView=new TextView(context);
//        textView.setLayoutParams(lparams);
//        textView.setTextSize(10);
//        textView.setTextColor(Color.rgb(0,0,0));
//        textView.setText(""+ text + "");
//        textView.setTextSize(22);
//        textView.setMaxEms(8);
//        textView.setVisibility(View.INVISIBLE);
//        numOfChitty= textView.getText().toString();
//        return textView;
//    }
//
//
//
//    public Button chittymemberButton(final Context context) {
//        final ViewGroup.LayoutParams lparams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        final Button button = new Button(context);
//        button.setLayoutParams(lparams);
//        button.setTextSize(22);
//        button.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
//        button.setTextColor(Color.rgb(255, 255, 255));
//        button.setText(getChittyName);
//        button.setTag(numOfChitty);
//        button.setBackgroundResource(R.drawable.blackbutton);
//        button.setGravity(Gravity.CENTER_VERTICAL);
//        lparams.width=lparams.MATCH_PARENT;
//        lparams.height=261;
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                    String value="Hello world";
////                    Intent i = new Intent(view.getContext(),Test.class);
////                   // i.putExtra("key",hellooi);
////                    context.startActivity(i);
//                //       Intent i=new Intent(ctx.getApplicationContext(),Test.class);
////                   .startActivity(i);
//                String sendingID=button.getText().toString();
//                String sendingChittyID="users/"+user+"/Chitties/"+ button.getTag().toString();
//                Toast.makeText(context,button.getTag().toString(), Toast.LENGTH_SHORT).show();
//                Intent i=new Intent(UserActivity.this,ShareDetails.class);
//                Bundle bundle=new Bundle();
//
//                bundle.putString("key",sendingChittyID);
//                bundle.putString("user",user);
//                bundle.putString("chitty",sendingID);
//                i.putExtras(bundle);
//                //  finish();
//                startActivity(i);
//            }
//        });
//        return button;
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Bundle login = getIntent().getExtras();
        if (login != null) {
            user = login.getString("user_id");
            //  Toast.makeText(MainActivity.this,user, Toast.LENGTH_SHORT).show();
        }

        getRef1 = FirebaseDatabase.getInstance().getReference("shares");
        //addChittyButton=(Button)findViewById(R.id.addChittyButton);
        mLayout=(GridLayout) findViewById(R.id.mylayout);


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

                    //  dmv=new DynamicViews(context);

                    // String status=snapshot.child(data).child("status").getValue(String.class);


                    // mLayout.addView(sentText(getApplicationContext(),data),i);
                    getShareName=snapshot.child(data).child("shopName").getValue(String.class).toUpperCase();
                    getShareAmount=snapshot.child(data).child("shareAmount").getValue(String.class);
                    getShareDate="Valid till "+ snapshot.child(data).child("date").getValue(String.class);
                    getShareId=snapshot.child(data).child("adminId").getValue(String.class);
                    i++;
                    userList.add(new ModelClass(R.drawable.logo,getShareName,getShareAmount, getShareDate,getShareId,data.toString(),String.valueOf(i)));


//                    userList = new ArrayList<>();
//                    userList.add(new ModelClass(R.drawable.logo,getChittyName,"Am", "Da"));

                 //   Toast.makeText(UserActivity.this,getShareAmount, Toast.LENGTH_SHORT).show();

//                    userList = new ArrayList<>();
//                    userList.add(new ModelClass(R.drawable.logo,getShareName,getShareAmount, getShareDate));
//
//                    recyclerView=findViewById(R.id.recyclerView);
//                    layoutManager=new LinearLayoutManager(UserActivity.this);
//                    layoutManager.setOrientation(RecyclerView.VERTICAL);
//                    recyclerView.setLayoutManager(layoutManager);
//                    adapter=new Adapter(userList);
//                    recyclerView.setAdapter(adapter);
//                    adapter.notifyDataSetChanged();
//


                    //  mLayout.addView(chittymemberButton(getApplicationContext()),i+1);
                    // i=i+2;

                }

                initRecyclerView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



//for(int i=1;i<=3;i++){
//    userList.add(new ModelClass(R.drawable.logo,"name","amount", "date"));
//}


       //userList.add(new ModelClass(R.drawable.logo,getShareName.toString(),"amount1", "date1"));

    }


    public void logout(){

        SharedPreferences loginDetails = getSharedPreferences("logDetails", MODE_PRIVATE);
        SharedPreferences.Editor editor = loginDetails.edit();
        editor.putString("id","0" );
        editor.putString("password","0" );
        editor.commit();

        Intent login=new Intent(UserActivity.this,Login.class);
        finish();
        startActivity(login);
        System.exit(0);

    }

    public void profiles()
    {
//        String sendingprofile="users/"+user+"/userDetails";
//
//        Intent profile=new Intent(UserActivity.this,UserDetails.class);
//        profile.putExtra("key",sendingprofile);
//        startActivity(profile);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.agent2,menu);
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