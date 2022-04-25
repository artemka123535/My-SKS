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

public class ChangeEmailActivity extends AppCompatActivity {
    private FirebaseAuth mauth;
    private DatabaseReference mdt;
    private EditText e1;
    private EditText e2;
    private String email;
    private String id1;
    private FirebaseUser currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email);
        getSupportActionBar().setTitle("Изменить эл. почту");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mauth = FirebaseAuth.getInstance();
        mdt = FirebaseDatabase.getInstance().getReference();
        e1 = findViewById(R.id.email1);
        e2 = findViewById((R.id.email2));
        currentUser = mauth.getCurrentUser();
        email = mauth.getCurrentUser().getEmail();
        Button saveemail = findViewById(R.id.saveemail);
        saveemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentUser.isEmailVerified()) {
                    mdt.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot ds : snapshot.getChildren()) {
                                User user1 = ds.getValue(User.class);
                                assert user1 != null;
                                String email1 = user1.email;
                                if (email1.equals(email)) {
                                    id1 = ds.getKey();
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    if (email.equals(e1.getText().toString())) {
                        final DatabaseReference dbr = mdt.child(id1);
                        Map<String, Object> updates = new HashMap<>();
                        updates.put("email", e2.getText().toString());
                        dbr.updateChildren(updates);
                        currentUser.updateEmail(e2.getText().toString());
                        Toast.makeText(ChangeEmailActivity.this, "Новая эл. почта установлена", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ChangeEmailActivity.this, "Текущий почта не верна", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(ChangeEmailActivity.this, "Чтобы изменить почту, нужно верефецироваться", Toast.LENGTH_SHORT).show();
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