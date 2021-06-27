package com.example.profittrader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EditProfile extends AppCompatActivity {

    String check_ID,check_Mode,path;
    EditText nameEditText,emailEditText,passwordEditText,phoneEditText,confirmEditText;
    Button saveButton;
    String idString,nameString,emailString,phoneString,passwordString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);


        SharedPreferences loginDetails =  getSharedPreferences("loginDetails", MODE_PRIVATE);
        check_ID = loginDetails.getString("id","0");
        check_Mode = loginDetails.getString("mode","0");

        nameEditText=(EditText)findViewById(R.id.nameEditText);
        emailEditText=(EditText)findViewById(R.id.emailEditText);
        passwordEditText=(EditText)findViewById(R.id.passwordEditText);
        phoneEditText=(EditText)findViewById(R.id.phoneEditText);
        confirmEditText=(EditText)findViewById(R.id.confirmEditText);
        saveButton=(Button)findViewById(R.id.saveButton);

        if(check_Mode.equals("admin")){
            path="admins/"+check_ID+"/userDetails";
        }
        else if(check_Mode.equals("user")){
            path="users/"+check_ID+"/userDetails";
        }

        DatabaseReference fb_to_read = FirebaseDatabase.getInstance().getReference(path);

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
                passwordString=snapshot.child("password1").getValue(String.class);

                nameEditText.setText(nameString);
                emailEditText.setText(emailString);
                phoneEditText.setText(phoneString);
                passwordEditText.setText("******");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String checkPassword=confirmEditText.getText().toString();

                if(passwordString.equals(checkPassword)){
                    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();

                    String namePath = path+ "/name";
                    String emailPath = path + "/emailId";
                    String mobilePath = path + "/mobile";
                    String passwordPath = path + "/password1";

                    DatabaseReference mName = mDatabase.getReference(namePath);
                    DatabaseReference mEmail = mDatabase.getReference(emailPath);
                    DatabaseReference mMobile = mDatabase.getReference(mobilePath);
                    DatabaseReference mPassword = mDatabase.getReference(passwordPath);

                    String getName=nameEditText.getText().toString();
                    String getEmail=emailEditText.getText().toString();
                    String getMobile=phoneEditText.getText().toString();
                    String getPassword=passwordEditText.getText().toString();

                    //DatabaseReference chittyCount = mDatabase.getReference( user_id + "/" + username + "/chittycount");

                    //DatabaseReference passw2 = mDatabase.getReference(password2);

                    mName.setValue(getName);
                    mEmail.setValue(getEmail);
                    mMobile.setValue(getMobile);
                    if(getPassword!="******") {
                        mPassword.setValue(getPassword);
                    }
                    SharedPreferences loginDetails = getSharedPreferences("loginDetails", MODE_PRIVATE);
                    SharedPreferences.Editor editor = loginDetails.edit();
                    editor.putString("id","0" );
                    editor.putString("password","0" );
                    editor.putString("mode","0" );
                    editor.commit();

                    Intent i=new Intent(EditProfile.this,Login.class);
                    finish();
                    startActivity(i);

                }



            }
        });



    }
}