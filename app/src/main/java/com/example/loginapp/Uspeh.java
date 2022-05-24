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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;


public class Uspeh extends AppCompatActivity {
    private EditText cardNumber;
    private EditText cardDate;
    private EditText cardCVV;
    private int times = 1;
    private DatabaseReference mdt;
    private FirebaseAuth mauth;
    private String id1;
    private TextView itogo;
    private TextView itog;
    private SharedPreferences sbpokaz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uspeh);
        getSupportActionBar().setTitle("Оплата");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mauth = FirebaseAuth.getInstance();
        mdt = FirebaseDatabase.getInstance().getReference();
        String email = mauth.getCurrentUser().getEmail();
        itogo = findViewById(R.id.itogo);
        sbpokaz = getSharedPreferences("AppSettings", Context.MODE_PRIVATE);
        mdt.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    User user1 = ds.getValue(User.class);
                    assert user1 != null;
                    String email1 = user1.email;
                    if (email1.equals(email)) {
                        id1 = ds.getKey();
                        if(sbpokaz.getString("AddressStatus","").equals("1")){
                            itogo.setText(String.valueOf(user1.cash)+ " руб.");
                        }else{
                            itogo.setText(String.valueOf(user1.cash1)+ " руб.");
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        cardNumber = findViewById(R.id.cardNumber);
        cardDate = findViewById(R.id.cardDate);
        cardCVV = findViewById(R.id.cardCVV);
        itog = findViewById(R.id.itog);
        Button oplata = findViewById(R.id.button10);
        ImageView ok = findViewById(R.id.ok);
        TextView uspeh = findViewById(R.id.uspeh);
        oplata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!cardCVV.getText().toString().isEmpty() && !cardDate.getText().toString().isEmpty() && !cardNumber.getText().toString().isEmpty() && times == 1 && cardDate.getText().toString().length() == 5 && cardNumber.getText().toString().length() == 19 && cardCVV.getText().toString().length() == 3){
                    oplata.setText("Продолжить");
                    cardNumber.setVisibility(View.INVISIBLE);
                    cardDate.setVisibility(View.INVISIBLE);
                    cardCVV.setVisibility(View.INVISIBLE);
                    itogo.setVisibility(View.INVISIBLE);
                    itog.setVisibility(View.INVISIBLE);
                    ok.setVisibility(View.VISIBLE);
                    uspeh.setVisibility(View.VISIBLE);
                    times = 2;
                    final DatabaseReference dbr = mdt.child(id1);
                    Map<String, Object> updates = new HashMap<>();
                    if(sbpokaz.getString("AddressStatus","").equals("1")){
                        updates.put("cash", 0);
                        dbr.updateChildren(updates);
                    }else{
                        updates.put("cash1", 0);
                        dbr.updateChildren(updates);
                    }
                }else if(times==2) {
                    Intent i;
                    i = new Intent(Uspeh.this, MenuActivity.class);
                    startActivity(i);
                }else if(cardDate.getText().toString().length() != 5 || cardNumber.getText().toString().length() != 19 || cardCVV.getText().toString().length() != 3){
                    Toast.makeText(Uspeh.this, "Данные введены неправльно", Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(Uspeh.this, "Не все поля заполнены", Toast.LENGTH_SHORT).show();
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