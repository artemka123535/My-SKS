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

public class PeredachaActivity extends AppCompatActivity {
    private DatabaseReference mdt;
    private FirebaseAuth mauth;
    private String id1;
    private EditText pokaz;
    private EditText pokaz1;
    private SharedPreferences sbpokaz;
    private int GVS;
    private float cash;
    private float cash1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peredacha);
        getSupportActionBar().setTitle("Передача показаний");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mauth = FirebaseAuth.getInstance();
        mdt = FirebaseDatabase.getInstance().getReference();
        String email = mauth.getCurrentUser().getEmail();
        pokaz = findViewById(R.id.pokaz);
        pokaz1 = findViewById(R.id.pokaz1);
        Button savepokaz = findViewById(R.id.savepokaz);
        sbpokaz = getSharedPreferences("AppSettings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sbpokaz.edit();
        mdt.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    User user1 = ds.getValue(User.class);
                    assert user1 != null;
                    String email1 = user1.email;
                    if (email1.equals(email)) {
                        id1 = ds.getKey();
                        GVS = user1.GVS;
                        cash = user1.cash;
                        cash1 = user1.cash1;;
                        if(GVS!=0){
                            pokaz1.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        savepokaz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sbpokaz.getString("AddressStatus","").equals("1")) {
                    final DatabaseReference dbr = mdt.child(id1);
                    Map<String, Object> updates = new HashMap<>();
                    if(GVS!=0){
                        updates.put("cash", (cash+ Float.parseFloat(pokaz.getText().toString())*47.26 + (Float.parseFloat(pokaz.getText().toString())+Float.parseFloat(pokaz1.getText().toString())) * 44.47));
                        dbr.updateChildren(updates);
                    }else {
                        updates.put("cash", (cash+ Float.parseFloat(pokaz.getText().toString()) * 47.26 + Float.parseFloat(pokaz.getText().toString()) * 44.47));
                        dbr.updateChildren(updates);
                    }
                    editor.putString("PokazStatus", "1");
                    editor.apply();
                    Toast.makeText(PeredachaActivity.this, "Показания счётчика добавлен", Toast.LENGTH_SHORT).show();
                    Intent i;
                    i = new Intent(PeredachaActivity.this, MenuActivity.class);
                    startActivity(i);
                }else{
                    final DatabaseReference dbr = mdt.child(id1);
                    Map<String, Object> updates = new HashMap<>();
                    if(GVS!=0){
                        updates.put("cash1", (cash1+Float.parseFloat(pokaz.getText().toString())*47.26 + (Float.parseFloat(pokaz.getText().toString())+Float.parseFloat(pokaz1.getText().toString())) * 44.47));
                        dbr.updateChildren(updates);
                    }else {
                        updates.put("cash1", (cash1+Float.parseFloat(pokaz.getText().toString()) * 47.26 + Float.parseFloat(pokaz.getText().toString()) * 44.47));
                        dbr.updateChildren(updates);
                    }
                    editor.putString("PokazStatus1", "1");
                    editor.apply();
                    Toast.makeText(PeredachaActivity.this, "Показания счётчика добавлен", Toast.LENGTH_SHORT).show();
                    Intent i;
                    i = new Intent(PeredachaActivity.this, MenuActivity.class);
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