package com.example.profittrader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminLogin extends AppCompatActivity {

    Button next,register,forgotpasswordButton;
    String user_id,password;
    TextView username,password_id;
    String pass;
    String check_ID,check_Password;
    String testString1="rtrt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        next=(Button)findViewById(R.id.next);
        register=(Button)findViewById(R.id.register);
        forgotpasswordButton=(Button)findViewById(R.id.forgotpasswordButton);
        password_id=findViewById(R.id.Password);
        username=(TextView)findViewById(R.id.username);



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                user_id=username.getText().toString();
                DatabaseReference fb_to_read = FirebaseDatabase.getInstance().getReference("admins");
                fb_to_read.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        List<String> list=new ArrayList<String>();
                        for (DataSnapshot dsp : snapshot.getChildren()){
                            list.add(String.valueOf(dsp.getKey()));
                        }
                        for(final String data:list){
                            //             Toast.makeText(LoginPage.this, data, Toast.LENGTH_SHORT).show();
                            if(data.equals(user_id.toString()))
                            {
                                password=password_id.getText().toString();
                                DatabaseReference fb_read = FirebaseDatabase.getInstance().getReference("admins"+"/"+user_id+"/"+"userDetails"+"/"+"password1");
                                fb_read.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        String pass=snapshot.getValue(String.class);
                                        //                       Toast.makeText(LoginPage.this,pass, Toast.LENGTH_SHORT).show();
                                        if(pass.equals(password.toString())){
                                            //Toast.makeText(LoginPage.this,"login", Toast.LENGTH_SHORT).show();
                                            SharedPreferences loginAdminDetails = getSharedPreferences("logAdminDetails", MODE_PRIVATE);
                                            SharedPreferences.Editor editor = loginAdminDetails.edit();
                                            editor.putString("adminid",user_id );
                                            editor.putString("adminpassword",password );
                                            editor.commit();

                                            Intent login=new Intent(AdminLogin.this,AdminActivity.class);
                                            login.putExtra("user_id",user_id);
                                            startActivity(login);
                                            finish();

                                        }
                                        else
                                        {
                                            Toast.makeText(AdminLogin.this, "WRONG USERNAME OR PASSWORD", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }
                            // Toast.makeText(LoginPage.this, "WRONG USERNAME OR PASSWORD", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });




            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reg=new Intent(AdminLogin.this,RegisterAdmin.class);
                startActivity(reg);
                finish();
            }
        });


    }
}