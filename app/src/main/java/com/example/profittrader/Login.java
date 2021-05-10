package com.example.profittrader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {

    Button adminLoginButton,userLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        adminLoginButton=(Button)findViewById(R.id.adminLoginButton);
        userLoginButton=(Button)findViewById(R.id.userLoginButton);


        userLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent userlog=new Intent(Login.this,MainActivity.class);
                startActivity(userlog);
                finish();
            }
        });
        adminLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent userlog=new Intent(Login.this,AdminLogin.class);
                startActivity(userlog);
                finish();
            }
        });

    }
}