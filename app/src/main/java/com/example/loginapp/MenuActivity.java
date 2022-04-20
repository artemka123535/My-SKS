package com.example.loginapp;

import android.content.ClipData;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.loginapp.databinding.ActivityMenuBinding;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    ActivityMenuBinding binding;
    private SharedPreferences sbschet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sbschet = getSharedPreferences("AppSettings", Context.MODE_PRIVATE);
        if(!sbschet.contains("SchetStatus")){
                getSupportActionBar().setTitle("Главная");
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fl, new fragment_main());
                ft.commit();
        }else{
            getSupportActionBar().hide();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fl, new fragment_shet());
            ft.commit();
        }


        binding.bnv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.main){
                    if(!sbschet.contains("SchetStatus")) {
                        getSupportActionBar().show();
                        getSupportActionBar().setTitle("Главная");
                        fragment_main fragmentMain = new fragment_main();
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.fl, fragmentMain);
                        ft.commit();
                    }else{
                        getSupportActionBar().hide();
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.fl, new fragment_shet());
                        ft.commit();
                    }
                }
                else if(id == R.id.account){
                    getSupportActionBar().show();
                    getSupportActionBar().setTitle("Профиль");
                    fragment_account fragmentAccount = new fragment_account();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.fl, fragmentAccount);
                    ft.commit();

                }
                else if(id == R.id.history){
                    getSupportActionBar().show();
                    getSupportActionBar().setTitle("История");
                    fragment_history fragmentHistory = new fragment_history();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.fl, fragmentHistory);
                    ft.commit();

                }
                return true;
            }
        });
    }


}
