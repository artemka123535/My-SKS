package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class VerificationActivity extends AppCompatActivity {
    private FirebaseAuth mauth;
    private DatabaseReference mdt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        getSupportActionBar().setTitle("Верификация");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mauth = FirebaseAuth.getInstance();
        Button emailbut = findViewById(R.id.emailbut);
        Button verify = findViewById(R.id.verify);
        ImageView yes = findViewById(R.id.Yes);
        ImageView no = findViewById(R.id.No);
        TextView isVerify = findViewById(R.id.isVerify);
        emailbut.setText("   "+mauth.getCurrentUser().getEmail());
        FirebaseUser user = mauth.getCurrentUser();
        if (user.isEmailVerified()){
            yes.setVisibility(View.VISIBLE);
            isVerify.setText("               Email потверждён");
        }else {
            no.setVisibility(View.VISIBLE);
            isVerify.setText("               Email  не потверждён");
            verify.setVisibility(View.VISIBLE);
        }
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.sendEmailVerification();
                Toast.makeText(VerificationActivity.this, "Письмо отправлено на ваш email", Toast.LENGTH_SHORT).show();
            }
        });
        emailbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i;
                i = new Intent(VerificationActivity.this,ChangeEmailActivity.class);
                startActivity(i);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(android.R.id.home==item.getItemId()){
            finish();
        }
        return true;
    }
}