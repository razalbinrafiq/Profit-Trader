package com.example.profittrader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity {


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

    public TextView sentText(Context context, String text){
        final ViewGroup.LayoutParams lparams= new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final TextView textView=new TextView(context);
        textView.setLayoutParams(lparams);
        textView.setTextSize(10);
        textView.setTextColor(Color.rgb(0,0,0));
        textView.setText(""+ text + "");
        textView.setTextSize(22);
        textView.setMaxEms(8);
        textView.setVisibility(View.INVISIBLE);
       // numOfChitty= textView.getText().toString();
        return textView;
    }



    public Button activeSharesButton(final Context context) {
        final ViewGroup.LayoutParams lparams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final Button button = new Button(context);
        button.setLayoutParams(lparams);
        button.setTextSize(22);
        button.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        button.setTextColor(Color.rgb(255, 255, 255));
        button.setText(getShareName);
        button.setTag(getCurrentNum);
        button.setBackgroundResource(R.drawable.blackbutton);
        button.setGravity(Gravity.CENTER_VERTICAL);
        lparams.width=lparams.MATCH_PARENT;
        lparams.height=261;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                    String value="Hello world";
//                    Intent i = new Intent(view.getContext(),Test.class);
//                   // i.putExtra("key",hellooi);
//                    context.startActivity(i);
                //       Intent i=new Intent(ctx.getApplicationContext(),Test.class);
//                   .startActivity(i);
                String sendingID=button.getText().toString();
                String sendingShareID="shares/"+button.getTag().toString();
                Toast.makeText(context,button.getTag().toString(), Toast.LENGTH_SHORT).show();
                Intent i=new Intent(AdminActivity.this,AmountDetails.class);
                Bundle bundle=new Bundle();

                bundle.putString("key",sendingShareID);
                bundle.putString("user",user);
                bundle.putString("share",sendingID);
                i.putExtras(bundle);
                //  finish();
                startActivity(i);
            }
        });
        return button;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);


        Bundle login = getIntent().getExtras();
        if (login != null) {
            user = login.getString("user_id");
            //  Toast.makeText(MainActivity.this,user, Toast.LENGTH_SHORT).show();
        }

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
        mLayout=(GridLayout) findViewById(R.id.mylayout);

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
                    getCurrentNum=data;
                    String getShareId=snapshot.child(data).child("adminId").getValue(String.class);
                   //Toast.makeText(context, "hy", Toast.LENGTH_SHORT).show();
                    if(getShareId.equals(user)){
                        mLayout.addView(sentText(getApplicationContext(),data),i);
                        mLayout.addView(activeSharesButton(getApplicationContext()),i+1);
                        i=i+2;

//                       // Toast.makeText(context, "hy", Toast.LENGTH_SHORT).show();
                    }

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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

                DatabaseReference mDbRef1 = mDatabase.getReference(fbChittyname);
                DatabaseReference mDbRef2 = mDatabase.getReference(fbdate);
                DatabaseReference mDbRef3= mDatabase.getReference(fbpaymentdate);
                DatabaseReference mDbRef4= mDatabase.getReference("shareCount");
                DatabaseReference mDbRef5= mDatabase.getReference(fbStatus);
                DatabaseReference mDbRef6 = mDatabase.getReference(shareProfitPath);
                DatabaseReference mDbRef7 = mDatabase.getReference(fbShopName);
                DatabaseReference mDbRef8 = mDatabase.getReference(soldShareCount);
                DatabaseReference mDbRef9 = mDatabase.getReference(soldShareSum);

                mDbRef1.setValue(user);
                mDbRef2.setValue(chittyDate);
                mDbRef3.setValue(chittyPaymentDate);
                mDbRef4.setValue(num);
                mDbRef5.setValue("active");
                mDbRef6.setValue(shareProfitPercentage);
                mDbRef7.setValue(nameOfShop);
                mDbRef8.setValue("0");
                mDbRef9.setValue("0");

                // Toast.makeText(getApplicationContext(), "Yes button Clicked", Toast.LENGTH_LONG).show();
                // Log.i("Code2care ", "Yes button Clicked!");
                dialog.dismiss();
//               finish();
//               onStart();

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


    public void profiles()
    {
        String sendingprofile="admins/"+user+"/adminDetails";

        Intent profile=new Intent(AdminActivity.this,AdminProfileDetails.class);
        profile.putExtra("key",sendingprofile);
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