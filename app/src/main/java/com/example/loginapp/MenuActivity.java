package com.example.loginapp;

import android.content.ClipData;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setTitle("Главная");
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fl, new fragment_main());
        ft.commit();


        binding.bnv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.main){
                    getSupportActionBar().setTitle("Главная");
                    fragment_main fragmentMain = new fragment_main();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.fl, fragmentMain);
                    ft.commit();
                }
                else if(id == R.id.account){
                    getSupportActionBar().setTitle("Профиль");
                    fragment_account fragmentAccount = new fragment_account();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.fl, fragmentAccount);
                    ft.commit();

                }
                else if(id == R.id.history){
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
