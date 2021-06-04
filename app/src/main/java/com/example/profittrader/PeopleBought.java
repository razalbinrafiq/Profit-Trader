package com.example.profittrader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PeopleBought extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_bought);




        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation_admin_activity);
        bottomNavigationView.setSelectedItemId(R.id.people2);

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
                        startActivity(new Intent(getApplicationContext(),AdminActivity.class));
                        overridePendingTransition(0,0);
                        finish();

                        return true;
                    case R.id.people2:

                        return true;
                }
                return false;
            }
        });






    }
}