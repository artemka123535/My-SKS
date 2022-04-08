package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ChangeActivity extends AppCompatActivity {
    private FirebaseAuth mauth;
    private DatabaseReference mdt;
    private EditText p1;
    private EditText p2;
    private EditText p3;
    private String pass;
    private String id1;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        getSupportActionBar().setTitle("Изменить пароль");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mauth = FirebaseAuth.getInstance();
        mdt = FirebaseDatabase.getInstance().getReference();
        p1 = findViewById(R.id.password1);
        p2 = findViewById(R.id.password2);
        p3 = findViewById(R.id.password3);
        currentUser = mauth.getCurrentUser();
        String email = mauth.getCurrentUser().getEmail();
        Button savepass = findViewById(R.id.savepass);
        savepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mdt.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds: snapshot.getChildren()){
                            User user1 = ds.getValue(User.class);
                            assert user1 != null;
                            String email1 =user1.email;
                            if(email1.equals(email)){
                                id1 = ds.getKey();
                                pass = user1.password;
                            }
                            }
                        }
                        @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                String oldpass = p1.getText().toString();
                String newpass = p2.getText().toString();
                String secnewpass = p3.getText().toString();
                if (oldpass == pass){
                    if(newpass == secnewpass){
                        final DatabaseReference dbr = mdt.child(id1);
                        Map<String, Object> updates = new HashMap<>();
                        updates.put("password", newpass);
                        dbr.updateChildren(updates);
                        currentUser.updatePassword(newpass);
                        Toast.makeText(ChangeActivity.this, "Новый пароль установлен", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(ChangeActivity.this, "Новые пароли не сходятся", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(ChangeActivity.this, "Текущий пароль не верен", Toast.LENGTH_SHORT).show();
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