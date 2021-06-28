package com.example.profittrader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RegisterAdmin extends AppCompatActivity {

    TextView Name,Password1,Password2,email_id,number,User;
    String name,password1,password2,mail,mnumber,username;
    List<String> list=new ArrayList<String>();
    int f=0;

    RadioGroup radioGroup;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_admin);


        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);




        Button data;
        data=(Button)findViewById(R.id.detials);
        DatabaseReference fb_to_read = FirebaseDatabase.getInstance().getReference("admins");
        fb_to_read.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dsp : snapshot.getChildren()){
                    list.add(String.valueOf(dsp.getKey()));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f=0;
                Name = (TextView) findViewById(R.id.name);
                Password1 = (TextView) findViewById(R.id.password1);
                Password2 = (TextView) findViewById(R.id.password2);
                email_id = (TextView) findViewById(R.id.email);
                User = (TextView) findViewById(R.id.userid);
                number = (TextView) findViewById(R.id.mobile_no);

                name = Name.getText().toString();
                username = User.getText().toString();
                password1 = Password1.getText().toString();
                password2 = Password2.getText().toString();
                mail = email_id.getText().toString();
                mnumber = number.getText().toString();
                FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
                String user_id = "admins";
                // DatabaseReference uid = mDatabase.getReference(user_id);
                // uid.setValue(mail);
                for(final String data:list){
                    //   Toast.makeText(register.this, data, Toast.LENGTH_SHORT).show();
                    if(data.equals(username.toString()))
                    {
                        f=1;
                    }}
                if(f==0)
                {
                    if (mnumber.length() == 10)
                    {
                        if(password1.length()>=5) {
                            if (password1.equals(password2)) {
                                String details = user_id + "/" + username + "/" + "userDetails";
                                String agentname = details + "/" + "name";
                                String emailid = details + "/" + "emailId";
                                String num = details + "/" + "mobile";
                                String type = details + "/" + "type";
                                String passwordpath = details + "/" + "password1";
                                String shareCount = user_id+"/"+username + "/" + "shareCount";
                                //String password2=details+"/"+"password2";

                                DatabaseReference Agentname = mDatabase.getReference(agentname);
                                DatabaseReference email = mDatabase.getReference(emailid);
                                DatabaseReference mno = mDatabase.getReference(num);
                                DatabaseReference sType = mDatabase.getReference(type);
                                DatabaseReference Passwordpath = mDatabase.getReference(passwordpath);
                                DatabaseReference shareCountSetter = mDatabase.getReference(shareCount);

                                //DatabaseReference chittyCount = mDatabase.getReference( user_id + "/" + username + "/chittycount");

                                //DatabaseReference passw2 = mDatabase.getReference(password2);

                                int radioId=radioGroup.getCheckedRadioButtonId();
                                radioButton=findViewById(radioId);


                                Agentname.setValue(name);
                                email.setValue(mail);
                                mno.setValue(mnumber);
                                sType.setValue(radioButton.getText().toString());
                                Passwordpath.setValue(password1);
                                shareCountSetter.setValue("0");
                                //chittyCount.setValue(0);
                                // passw2.setValue(pass2);
                                Intent d = new Intent(RegisterAdmin.this, AdminLogin.class);
                                startActivity(d);
                                finish();
                            } else {
                                Toast.makeText(RegisterAdmin.this, "PASSWORDS DOESN'T MATCH", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(RegisterAdmin.this, "Not Strong 5-10 digit", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(RegisterAdmin.this, "NOT A VALID NUMBER", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(RegisterAdmin.this, "USERNAME ALREADY EXISTS", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }



    public  void checkButton(View v){
        int radioId=radioGroup.getCheckedRadioButtonId();
        radioButton=findViewById(radioId);
        Toast.makeText(this, "LL:"+radioButton.getText().toString(), Toast.LENGTH_SHORT).show();
    }
}