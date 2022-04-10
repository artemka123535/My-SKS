package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class PinLockActivity extends AppCompatActivity {
    private DatabaseReference mdt;
    private FirebaseAuth mauth;
    private String id1;
    private String codeexist1;
    private String code1;
    private TextView codetext;
    private String newcode = "";
    private int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_lock);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mauth = FirebaseAuth.getInstance();
        mdt = FirebaseDatabase.getInstance().getReference();
        String email = mauth.getCurrentUser().getEmail();
        codetext = findViewById(R.id.codetext);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button button0 = findViewById(R.id.button0);
        TextView parol1 = findViewById(R.id.parol1);
        TextView parol2 = findViewById(R.id.parol2);
        TextView parol3 = findViewById(R.id.parol3);
        TextView parol4 = findViewById(R.id.parol4);

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
                        code1 = user1.code;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        if (codeexist1.equals("1")){
            codetext.setText("Введите код-пароль");

        } else{
            codetext.setText("Создайте код-пароль");
            while(newcode.length()<=4) {
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        count+=1;
                        newcode += "1";
                        if(count== 1){
                            parol1.setBackgroundResource(R.drawable.oval);
                        }
                        else if(count== 2){
                            parol2.setBackgroundResource(R.drawable.oval);
                        }
                        else if(count== 3){
                            parol3.setBackgroundResource(R.drawable.oval);
                        }
                        else if(count== 4){
                            parol4.setBackgroundResource(R.drawable.oval);
                        }
                    }
                });
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        newcode += "2";
                    }
                });
                button3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        newcode += "3";
                    }
                });
                button4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        newcode += "4";
                    }
                });
                button5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        newcode += "5";
                    }
                });
                button6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        newcode += "6";
                    }
                });
                button7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        newcode += "7";
                    }
                });
                button8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        newcode += "8";
                    }
                });
                button9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        newcode += "9";
                    }
                });
                button0.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        newcode += "0";
                    }
                });
            }
            if(newcode.length()==4) {
                final DatabaseReference dbr = mdt.child(id1);
                Map<String, Object> updates = new HashMap<>();
                updates.put("code", newcode);
                updates.put("codeexist", "1");
                dbr.updateChildren(updates);
            }
        }


    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(android.R.id.home==item.getItemId()){
            finish();
        }
        return true;
    }
}