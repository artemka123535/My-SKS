package com.example.loginapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mauth;
    private FirebaseUser currentUser;
    private DatabaseReference mdt;
    private SharedPreferences pin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mdt = FirebaseDatabase.getInstance().getReference();
        mauth = FirebaseAuth.getInstance();
        currentUser = mauth.getCurrentUser();
        pin = getSharedPreferences("AppSettings", Context.MODE_PRIVATE);
        if (currentUser == null) {
                ViewPager viewPager = findViewById(R.id.viewPager);

                AuthenticationPagerAdapter pagerAdapter = new AuthenticationPagerAdapter(getSupportFragmentManager());
                pagerAdapter.addFragmet(new fragment_login());
                pagerAdapter.addFragmet(new fragment_register());
                viewPager.setAdapter(pagerAdapter);
        } else if (currentUser != null) {
            if(pin.contains("PinStatus")) {
                if(pin.getString("PinStatus","").equals("1")) {
                    Intent i;
                    i = new Intent(MainActivity.this, PinLockActivity.class);
                    startActivity(i);
                }else{
                    ViewPager viewPager = findViewById(R.id.viewPager);

                    AuthenticationPagerAdapter pagerAdapter = new AuthenticationPagerAdapter(getSupportFragmentManager());
                    pagerAdapter.addFragmet(new fragment_login());
                    pagerAdapter.addFragmet(new fragment_register());
                    viewPager.setAdapter(pagerAdapter);
            }
            }
        }
    }




     class AuthenticationPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> fragmentList = new ArrayList<>();

        public AuthenticationPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragmentList.get(i);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        void addFragmet(Fragment fragment) {
            fragmentList.add(fragment);
        }
    }
}