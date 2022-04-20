package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class AddSchetActivity extends AppCompatActivity {
    private DatabaseReference mdt;
    private FirebaseAuth mauth;
    private String id1;
    private String schet1;
    private EditText schet;
    private SharedPreferences sbschet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schet);
        getSupportActionBar().setTitle("Добавление лицевого счёта");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mauth = FirebaseAuth.getInstance();
        mdt = FirebaseDatabase.getInstance().getReference();
        String email = mauth.getCurrentUser().getEmail();
        schet = findViewById(R.id.schet);
        Button saveschet = findViewById(R.id.saveschet);
        sbschet = getSharedPreferences("AppSettings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sbschet.edit();
        mdt.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    User user1 = ds.getValue(User.class);
                    assert user1 != null;
                    String email1 = user1.email;
                    if (email1.equals(email)) {
                        id1 = ds.getKey();
                        schet1 = user1.schet;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        saveschet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(schet1.isEmpty()) {
                    final DatabaseReference dbr = mdt.child(id1);
                    Map<String, Object> updates = new HashMap<>();
                    updates.put("schet", schet.getText().toString());
                    dbr.updateChildren(updates);
                    editor.putString("SchetStatus", "1");
                    editor.apply();
                    Toast.makeText(AddSchetActivity.this, "Лицевой счёт добавлен", Toast.LENGTH_SHORT).show();
                    Intent i;
                    i = new Intent(AddSchetActivity.this, MenuActivity.class);
                    startActivity(i);
                }else{
                    final DatabaseReference dbr = mdt.child(id1);
                    Map<String, Object> updates = new HashMap<>();
                    updates.put("schet1", schet.getText().toString());
                    dbr.updateChildren(updates);
                    editor.putString("SchetStatus", "1");
                    editor.apply();
                    Toast.makeText(AddSchetActivity.this, "Лицевой счёт добавлен", Toast.LENGTH_SHORT).show();
                    Intent i;
                    i = new Intent(AddSchetActivity.this, MenuActivity.class);
                    startActivity(i);
                }
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