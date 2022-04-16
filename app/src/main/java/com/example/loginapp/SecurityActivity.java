package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SecurityActivity extends AppCompatActivity {
    private DatabaseReference mdt;
    private FirebaseAuth mauth;
    private String id1;
    private String codeexist1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);
        getSupportActionBar().setTitle("Безопасность");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mauth = FirebaseAuth.getInstance();
        mdt = FirebaseDatabase.getInstance().getReference();
        String email = mauth.getCurrentUser().getEmail();
        mdt.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds: snapshot.getChildren()) {
                    User user1 = ds.getValue(User.class);
                    assert user1 != null;
                    String email1 = user1.email;
                    if (email1.equals(email)) {
                        id1 = ds.getKey();
                        codeexist1 = user1.codeexist;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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