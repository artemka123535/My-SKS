package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

public class PinLockActivity extends AppCompatActivity {
    private DatabaseReference mdt;
    private FirebaseAuth mauth;
    private String id1;
    private String codeexist1;
    private String code1;
    private TextView codetext;
    private String newcode= "";
    private int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_lock);
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
        Button vyhod = findViewById(R.id.vyhod);
        ImageButton back = findViewById(R.id.back);
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
                if (codeexist1.equals("1")){
                    codetext.setText("Введите код-пароль");
                    vyhod.setVisibility(View.VISIBLE);
                    button1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(newcode.length()==4) {
                                if(code1.equals(newcode)){
                                    Toast.makeText(PinLockActivity.this, "Добро пожаловать!!!", Toast.LENGTH_SHORT).show();
                                    Intent i;
                                    i = new Intent(PinLockActivity.this,MenuActivity.class);
                                    startActivity(i);
                                }else{
                                    parol1.setBackgroundResource(R.drawable.freepin);
                                    parol2.setBackgroundResource(R.drawable.freepin);
                                    parol3.setBackgroundResource(R.drawable.freepin);
                                    parol4.setBackgroundResource(R.drawable.freepin);
                                    Toast.makeText(PinLockActivity.this, "Код пароль не правильный", Toast.LENGTH_SHORT).show();
                                    newcode = "";
                                    count = 0;
                                }
                            }
                            else if (newcode.length()<=4) {
                                count += 1;
                                newcode += "1";
                                if (count == 1) {
                                    parol1.setBackgroundResource(R.drawable.pin);
                                } else if (count == 2) {
                                    parol2.setBackgroundResource(R.drawable.pin);
                                } else if (count == 3) {
                                    parol3.setBackgroundResource(R.drawable.pin);
                                } else if (count == 4) {
                                    parol4.setBackgroundResource(R.drawable.pin);
                                }
                            }
                            if(newcode.length()==4) {
                                if(code1.equals(newcode)){
                                    Toast.makeText(PinLockActivity.this, "Добро пожаловать!!!", Toast.LENGTH_SHORT).show();
                                    Intent i;
                                    i = new Intent(PinLockActivity.this,MenuActivity.class);
                                    startActivity(i);
                                }else{
                                    parol1.setBackgroundResource(R.drawable.freepin);
                                    parol2.setBackgroundResource(R.drawable.freepin);
                                    parol3.setBackgroundResource(R.drawable.freepin);
                                    parol4.setBackgroundResource(R.drawable.freepin);
                                    Toast.makeText(PinLockActivity.this, "Код пароль не правильный", Toast.LENGTH_SHORT).show();
                                    newcode = "";
                                    count = 0;
                                }
                            }
                        }
                    });
                    button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(newcode.length()==4) {
                                if(code1.equals(newcode)){
                                    Toast.makeText(PinLockActivity.this, "Добро пожаловать!!!", Toast.LENGTH_SHORT).show();
                                    Intent i;
                                    i = new Intent(PinLockActivity.this,MenuActivity.class);
                                    startActivity(i);
                                }else{
                                    parol1.setBackgroundResource(R.drawable.freepin);
                                    parol2.setBackgroundResource(R.drawable.freepin);
                                    parol3.setBackgroundResource(R.drawable.freepin);
                                    parol4.setBackgroundResource(R.drawable.freepin);
                                    Toast.makeText(PinLockActivity.this, "Код пароль не правильный", Toast.LENGTH_SHORT).show();
                                    newcode = "";
                                    count = 0;


                                }
                            }
                            else if (newcode.length()<=4) {
                                count += 1;
                                newcode += "2";
                                if (count == 1) {
                                    parol1.setBackgroundResource(R.drawable.pin);
                                } else if (count == 2) {
                                    parol2.setBackgroundResource(R.drawable.pin);
                                } else if (count == 3) {
                                    parol3.setBackgroundResource(R.drawable.pin);
                                } else if (count == 4) {
                                    parol4.setBackgroundResource(R.drawable.pin);
                                }
                            }
                            if(newcode.length()==4) {
                                if(code1.equals(newcode)){
                                    Toast.makeText(PinLockActivity.this, "Добро пожаловать!!!", Toast.LENGTH_SHORT).show();
                                    Intent i;
                                    i = new Intent(PinLockActivity.this,MenuActivity.class);
                                    startActivity(i);
                                }else{
                                    parol1.setBackgroundResource(R.drawable.freepin);
                                    parol2.setBackgroundResource(R.drawable.freepin);
                                    parol3.setBackgroundResource(R.drawable.freepin);
                                    parol4.setBackgroundResource(R.drawable.freepin);
                                    Toast.makeText(PinLockActivity.this, "Код пароль не правильный", Toast.LENGTH_SHORT).show();
                                    newcode = "";
                                    count = 0;

                                }
                            }
                        }
                    });
                    button3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(newcode.length()==4) {
                                if(code1.equals(newcode)){
                                    Toast.makeText(PinLockActivity.this, "Добро пожаловать!!!", Toast.LENGTH_SHORT).show();
                                    Intent i;
                                    i = new Intent(PinLockActivity.this,MenuActivity.class);
                                    startActivity(i);
                                }else{
                                    parol1.setBackgroundResource(R.drawable.freepin);
                                    parol2.setBackgroundResource(R.drawable.freepin);
                                    parol3.setBackgroundResource(R.drawable.freepin);
                                    parol4.setBackgroundResource(R.drawable.freepin);
                                    Toast.makeText(PinLockActivity.this, "Код пароль не правильный", Toast.LENGTH_SHORT).show();
                                    newcode = "";
                                    count = 0;
                                }
                            }
                            else if (newcode.length()<=4) {
                                count += 1;
                                newcode += "3";
                                if (count == 1) {
                                    parol1.setBackgroundResource(R.drawable.pin);
                                } else if (count == 2) {
                                    parol2.setBackgroundResource(R.drawable.pin);
                                } else if (count == 3) {
                                    parol3.setBackgroundResource(R.drawable.pin);
                                } else if (count == 4) {
                                    parol4.setBackgroundResource(R.drawable.pin);
                                }
                            }
                            if(newcode.length()==4) {
                                if(code1.equals(newcode)){
                                    Toast.makeText(PinLockActivity.this, "Добро пожаловать!!!", Toast.LENGTH_SHORT).show();
                                    Intent i;
                                    i = new Intent(PinLockActivity.this,MenuActivity.class);
                                    startActivity(i);
                                }else{
                                    parol1.setBackgroundResource(R.drawable.freepin);
                                    parol2.setBackgroundResource(R.drawable.freepin);
                                    parol3.setBackgroundResource(R.drawable.freepin);
                                    parol4.setBackgroundResource(R.drawable.freepin);
                                    Toast.makeText(PinLockActivity.this, "Код пароль не правильный", Toast.LENGTH_SHORT).show();
                                    newcode = "";
                                    count = 0;
                                }
                            }
                        }

                    });
                    button4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(newcode.length()==4) {
                                if(code1.equals(newcode)){
                                    Toast.makeText(PinLockActivity.this, "Добро пожаловать!!!", Toast.LENGTH_SHORT).show();
                                    Intent i;
                                    i = new Intent(PinLockActivity.this,MenuActivity.class);
                                    startActivity(i);
                                }else{
                                    parol1.setBackgroundResource(R.drawable.freepin);
                                    parol2.setBackgroundResource(R.drawable.freepin);
                                    parol3.setBackgroundResource(R.drawable.freepin);
                                    parol4.setBackgroundResource(R.drawable.freepin);
                                    Toast.makeText(PinLockActivity.this, "Код пароль не правильный", Toast.LENGTH_SHORT).show();
                                    newcode = "";
                                    count = 0;
                                }
                            }
                            else if (newcode.length()<=4) {
                                count += 1;
                                newcode += "4";
                                if (count == 1) {
                                    parol1.setBackgroundResource(R.drawable.pin);
                                } else if (count == 2) {
                                    parol2.setBackgroundResource(R.drawable.pin);
                                } else if (count == 3) {
                                    parol3.setBackgroundResource(R.drawable.pin);
                                } else if (count == 4) {
                                    parol4.setBackgroundResource(R.drawable.pin);
                                }
                            }
                            if(newcode.length()==4) {
                                if(code1.equals(newcode)){
                                    Toast.makeText(PinLockActivity.this, "Добро пожаловать!!!", Toast.LENGTH_SHORT).show();
                                    Intent i;
                                    i = new Intent(PinLockActivity.this,MenuActivity.class);
                                    startActivity(i);
                                }else{
                                    parol1.setBackgroundResource(R.drawable.freepin);
                                    parol2.setBackgroundResource(R.drawable.freepin);
                                    parol3.setBackgroundResource(R.drawable.freepin);
                                    parol4.setBackgroundResource(R.drawable.freepin);
                                    Toast.makeText(PinLockActivity.this, "Код пароль не правильный", Toast.LENGTH_SHORT).show();
                                    newcode = "";
                                    count = 0;
                                }
                            }
                        }

                    });
                    button5.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(newcode.length()==4) {
                                if(code1.equals(newcode)){
                                    Toast.makeText(PinLockActivity.this, "Добро пожаловать!!!", Toast.LENGTH_SHORT).show();
                                    Intent i;
                                    i = new Intent(PinLockActivity.this,MenuActivity.class);
                                    startActivity(i);
                                }else{
                                    parol1.setBackgroundResource(R.drawable.freepin);
                                    parol2.setBackgroundResource(R.drawable.freepin);
                                    parol3.setBackgroundResource(R.drawable.freepin);
                                    parol4.setBackgroundResource(R.drawable.freepin);
                                    Toast.makeText(PinLockActivity.this, "Код пароль не правильный", Toast.LENGTH_SHORT).show();
                                    newcode = "";
                                    count = 0;
                                }
                            }
                            else if (newcode.length()<=4) {
                                count += 1;
                                newcode += "5";
                                if (count == 1) {
                                    parol1.setBackgroundResource(R.drawable.pin);
                                } else if (count == 2) {
                                    parol2.setBackgroundResource(R.drawable.pin);
                                } else if (count == 3) {
                                    parol3.setBackgroundResource(R.drawable.pin);
                                } else if (count == 4) {
                                    parol4.setBackgroundResource(R.drawable.pin);
                                }
                            }
                            if(newcode.length()==4) {
                                if(code1.equals(newcode)){
                                    Toast.makeText(PinLockActivity.this, "Добро пожаловать!!!", Toast.LENGTH_SHORT).show();
                                    Intent i;
                                    i = new Intent(PinLockActivity.this,MenuActivity.class);
                                    startActivity(i);
                                }else{
                                    parol1.setBackgroundResource(R.drawable.freepin);
                                    parol2.setBackgroundResource(R.drawable.freepin);
                                    parol3.setBackgroundResource(R.drawable.freepin);
                                    parol4.setBackgroundResource(R.drawable.freepin);
                                    Toast.makeText(PinLockActivity.this, "Код пароль не правильный", Toast.LENGTH_SHORT).show();
                                    newcode = "";
                                    count = 0;
                                }
                            }
                        }

                    });
                    button6.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(newcode.length()==4) {
                                if(code1.equals(newcode)){
                                    Toast.makeText(PinLockActivity.this, "Добро пожаловать!!!", Toast.LENGTH_SHORT).show();
                                    Intent i;
                                    i = new Intent(PinLockActivity.this,MenuActivity.class);
                                    startActivity(i);
                                }else{
                                    parol1.setBackgroundResource(R.drawable.freepin);
                                    parol2.setBackgroundResource(R.drawable.freepin);
                                    parol3.setBackgroundResource(R.drawable.freepin);
                                    parol4.setBackgroundResource(R.drawable.freepin);
                                    Toast.makeText(PinLockActivity.this, "Код пароль не правильный", Toast.LENGTH_SHORT).show();
                                    newcode = "";
                                    count = 0;

                                }
                            }
                            else if (newcode.length()<=4) {
                                count += 1;
                                newcode += "6";
                                if (count == 1) {
                                    parol1.setBackgroundResource(R.drawable.pin);
                                } else if (count == 2) {
                                    parol2.setBackgroundResource(R.drawable.pin);
                                } else if (count == 3) {
                                    parol3.setBackgroundResource(R.drawable.pin);
                                } else if (count == 4) {
                                    parol4.setBackgroundResource(R.drawable.pin);
                                }
                            }
                            if(newcode.length()==4) {
                                if(code1.equals(newcode)){
                                    Toast.makeText(PinLockActivity.this, "Добро пожаловать!!!", Toast.LENGTH_SHORT).show();
                                    Intent i;
                                    i = new Intent(PinLockActivity.this,MenuActivity.class);
                                    startActivity(i);
                                }else{
                                    parol1.setBackgroundResource(R.drawable.freepin);
                                    parol2.setBackgroundResource(R.drawable.freepin);
                                    parol3.setBackgroundResource(R.drawable.freepin);
                                    parol4.setBackgroundResource(R.drawable.freepin);
                                    Toast.makeText(PinLockActivity.this, "Код пароль не правильный", Toast.LENGTH_SHORT).show();
                                    newcode = "";
                                    count = 0;
                                }
                            }
                        }

                    });
                    button7.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(newcode.length()==4) {
                                if(code1.equals(newcode)){
                                    Toast.makeText(PinLockActivity.this, "Добро пожаловать!!!", Toast.LENGTH_SHORT).show();
                                    Intent i;
                                    i = new Intent(PinLockActivity.this,MenuActivity.class);
                                    startActivity(i);
                                }else{
                                    parol1.setBackgroundResource(R.drawable.freepin);
                                    parol2.setBackgroundResource(R.drawable.freepin);
                                    parol3.setBackgroundResource(R.drawable.freepin);
                                    parol4.setBackgroundResource(R.drawable.freepin);
                                    Toast.makeText(PinLockActivity.this, "Код пароль не правильный", Toast.LENGTH_SHORT).show();
                                    newcode = "";
                                    count = 0;
                                }
                            }
                            else if (newcode.length()<=4) {
                                count += 1;
                                newcode += "7";
                                if (count == 1) {
                                    parol1.setBackgroundResource(R.drawable.pin);
                                } else if (count == 2) {
                                    parol2.setBackgroundResource(R.drawable.pin);
                                } else if (count == 3) {
                                    parol3.setBackgroundResource(R.drawable.pin);
                                } else if (count == 4) {
                                    parol4.setBackgroundResource(R.drawable.pin);
                                }
                            }
                            if(newcode.length()==4) {
                                if(code1.equals(newcode)){
                                    Toast.makeText(PinLockActivity.this, "Добро пожаловать!!!", Toast.LENGTH_SHORT).show();
                                    Intent i;
                                    i = new Intent(PinLockActivity.this,MenuActivity.class);
                                    startActivity(i);
                                }else{
                                    parol1.setBackgroundResource(R.drawable.freepin);
                                    parol2.setBackgroundResource(R.drawable.freepin);
                                    parol3.setBackgroundResource(R.drawable.freepin);
                                    parol4.setBackgroundResource(R.drawable.freepin);
                                    Toast.makeText(PinLockActivity.this, "Код пароль не правильный", Toast.LENGTH_SHORT).show();
                                    newcode = "";
                                    count = 0;
                                }
                            }
                        }

                    });
                    button8.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(newcode.length()==4) {
                                if(code1.equals(newcode)){
                                    Toast.makeText(PinLockActivity.this, "Добро пожаловать!!!", Toast.LENGTH_SHORT).show();
                                    Intent i;
                                    i = new Intent(PinLockActivity.this,MenuActivity.class);
                                    startActivity(i);
                                }else{
                                    parol1.setBackgroundResource(R.drawable.freepin);
                                    parol2.setBackgroundResource(R.drawable.freepin);
                                    parol3.setBackgroundResource(R.drawable.freepin);
                                    parol4.setBackgroundResource(R.drawable.freepin);
                                    Toast.makeText(PinLockActivity.this, "Код пароль не правильный", Toast.LENGTH_SHORT).show();
                                    newcode = "";
                                    count = 0;
                                }
                            }
                            else if (newcode.length()<=4) {
                                count += 1;
                                newcode += "8";
                                if (count == 1) {
                                    parol1.setBackgroundResource(R.drawable.pin);
                                } else if (count == 2) {
                                    parol2.setBackgroundResource(R.drawable.pin);
                                } else if (count == 3) {
                                    parol3.setBackgroundResource(R.drawable.pin);
                                } else if (count == 4) {
                                    parol4.setBackgroundResource(R.drawable.pin);
                                }
                            }
                            if(newcode.length()==4) {
                                if(code1.equals(newcode)){
                                    Toast.makeText(PinLockActivity.this, "Добро пожаловать!!!", Toast.LENGTH_SHORT).show();
                                    Intent i;
                                    i = new Intent(PinLockActivity.this,MenuActivity.class);
                                    startActivity(i);
                                }else{
                                    parol1.setBackgroundResource(R.drawable.freepin);
                                    parol2.setBackgroundResource(R.drawable.freepin);
                                    parol3.setBackgroundResource(R.drawable.freepin);
                                    parol4.setBackgroundResource(R.drawable.freepin);
                                    Toast.makeText(PinLockActivity.this, "Код пароль не правильный", Toast.LENGTH_SHORT).show();
                                    newcode = "";
                                    count = 0;
                                }
                            }
                        }

                    });
                    button9.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(newcode.length()==4) {
                                if(code1.equals(newcode)){
                                    Toast.makeText(PinLockActivity.this, "Добро пожаловать!!!", Toast.LENGTH_SHORT).show();
                                    Intent i;
                                    i = new Intent(PinLockActivity.this,MenuActivity.class);
                                    startActivity(i);
                                }else{
                                    parol1.setBackgroundResource(R.drawable.freepin);
                                    parol2.setBackgroundResource(R.drawable.freepin);
                                    parol3.setBackgroundResource(R.drawable.freepin);
                                    parol4.setBackgroundResource(R.drawable.freepin);
                                    Toast.makeText(PinLockActivity.this, "Код пароль не правильный", Toast.LENGTH_SHORT).show();
                                    newcode = "";
                                    count = 0;
                                }
                            }
                            else if (newcode.length()<=4) {
                                count += 1;
                                newcode += "9";
                                if (count == 1) {
                                    parol1.setBackgroundResource(R.drawable.pin);
                                } else if (count == 2) {
                                    parol2.setBackgroundResource(R.drawable.pin);
                                } else if (count == 3) {
                                    parol3.setBackgroundResource(R.drawable.pin);
                                } else if (count == 4) {
                                    parol4.setBackgroundResource(R.drawable.pin);
                                }
                            }
                            if(newcode.length()==4) {
                                if(code1.equals(newcode)){
                                    Toast.makeText(PinLockActivity.this, "Добро пожаловать!!!", Toast.LENGTH_SHORT).show();
                                    Intent i;
                                    i = new Intent(PinLockActivity.this,MenuActivity.class);
                                    startActivity(i);
                                }else{
                                    parol1.setBackgroundResource(R.drawable.freepin);
                                    parol2.setBackgroundResource(R.drawable.freepin);
                                    parol3.setBackgroundResource(R.drawable.freepin);
                                    parol4.setBackgroundResource(R.drawable.freepin);
                                    Toast.makeText(PinLockActivity.this, "Код пароль не правильный", Toast.LENGTH_SHORT).show();
                                    newcode = "";
                                    count = 0;
                                }
                            }
                        }

                    });
                    button0.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(newcode.length()==4) {
                                if(code1.equals(newcode)){
                                    Toast.makeText(PinLockActivity.this, "Добро пожаловать!!!", Toast.LENGTH_SHORT).show();

                                    Intent i;
                                    i = new Intent(PinLockActivity.this,MenuActivity.class);
                                    startActivity(i);
                                }else{
                                    parol1.setBackgroundResource(R.drawable.freepin);
                                    parol2.setBackgroundResource(R.drawable.freepin);
                                    parol3.setBackgroundResource(R.drawable.freepin);
                                    parol4.setBackgroundResource(R.drawable.freepin);
                                    Toast.makeText(PinLockActivity.this, "Код пароль не правильный", Toast.LENGTH_SHORT).show();
                                    newcode = "";
                                    count = 0;
                                }
                            }
                            else if (newcode.length()<=4) {
                                count += 1;
                                newcode += "0";
                                if (count == 1) {
                                    parol1.setBackgroundResource(R.drawable.pin);
                                } else if (count == 2) {
                                    parol2.setBackgroundResource(R.drawable.pin);
                                } else if (count == 3) {
                                    parol3.setBackgroundResource(R.drawable.pin);
                                } else if (count == 4) {
                                    parol4.setBackgroundResource(R.drawable.pin);
                                }
                            }
                            if(newcode.length()==4) {
                                if(code1.equals(newcode)){
                                    Toast.makeText(PinLockActivity.this, "Добро пожаловать!!!", Toast.LENGTH_SHORT).show();
                                    Intent i;
                                    i = new Intent(PinLockActivity.this,MenuActivity.class);
                                    startActivity(i);
                                }else{
                                    parol1.setBackgroundResource(R.drawable.freepin);
                                    parol2.setBackgroundResource(R.drawable.freepin);
                                    parol3.setBackgroundResource(R.drawable.freepin);
                                    parol4.setBackgroundResource(R.drawable.freepin);
                                    Toast.makeText(PinLockActivity.this, "Код пароль не правильный", Toast.LENGTH_SHORT).show();
                                    newcode = "";
                                    count = 0;
                                }
                            }
                        }

                    });
                    back.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (newcode.length() > 0 && count >=1) {
                                newcode = newcode.substring(0, newcode.length()-1);
                                if (count == 1) {
                                    parol1.setBackgroundResource(R.drawable.freepin);
                                    count-=1;
                                } else if (count == 2) {
                                    parol2.setBackgroundResource(R.drawable.freepin);
                                    count-=1;
                                } else if (count == 3) {
                                    parol3.setBackgroundResource(R.drawable.freepin);
                                    count-=1;
                                } else if (count == 4) {
                                    parol4.setBackgroundResource(R.drawable.freepin);
                                    count-=1;
                                }
                            }
                        }
                    });
                    vyhod.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mauth.signOut();
                            Intent i;
                            i = new Intent(PinLockActivity.this,MainActivity.class);
                            startActivity(i);
                        }
                    });

                } else{
                    codetext.setText("Создайте код-пароль");
                    button1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(newcode.length()==4) {
                                final DatabaseReference dbr = mdt.child(id1);
                                    Map<String, Object> updates = new HashMap<>();
                                    updates.put("code", newcode);
                                    updates.put("codeexist", "1");
                                updates.put("pin", 1);
                                    dbr.updateChildren(updates);
                                    Toast.makeText(PinLockActivity.this, "Код пароль создан", Toast.LENGTH_SHORT).show();
                                Intent i;
                                i = new Intent(PinLockActivity.this,MainActivity.class);
                                startActivity(i);
                                }
                                else if (newcode.length()<=4) {
                                    count += 1;
                                    newcode += "1";
                                    if (count == 1) {
                                        parol1.setBackgroundResource(R.drawable.pin);
                                    } else if (count == 2) {
                                        parol2.setBackgroundResource(R.drawable.pin);
                                    } else if (count == 3) {
                                        parol3.setBackgroundResource(R.drawable.pin);
                                    } else if (count == 4) {
                                        parol4.setBackgroundResource(R.drawable.pin);
                                    }
                                }
                            if(newcode.length()==4) {
                                final DatabaseReference dbr = mdt.child(id1);
                                Map<String, Object> updates = new HashMap<>();
                                updates.put("code", newcode);
                                updates.put("codeexist", "1");
                                updates.put("pin", "1");
                                dbr.updateChildren(updates);
                                Toast.makeText(PinLockActivity.this, "Код пароль создан", Toast.LENGTH_SHORT).show();
                                Intent i;
                                i = new Intent(PinLockActivity.this,MainActivity.class);
                                startActivity(i);
                            }
                            }
                        });
                        button2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(newcode.length()==4) {
                                    final DatabaseReference dbr = mdt.child(id1);
                                    Map<String, Object> updates = new HashMap<>();
                                    updates.put("code", newcode);
                                    updates.put("codeexist", "1");
                                    dbr.updateChildren(updates);
                                    Toast.makeText(PinLockActivity.this, "Код пароль создан", Toast.LENGTH_SHORT).show();
                                    Intent i;
                                    i = new Intent(PinLockActivity.this,MainActivity.class);
                                    startActivity(i);
                                }
                                else if (newcode.length()<=4) {
                                    count += 1;
                                    newcode += "2";
                                    if (count == 1) {
                                        parol1.setBackgroundResource(R.drawable.pin);
                                    } else if (count == 2) {
                                        parol2.setBackgroundResource(R.drawable.pin);
                                    } else if (count == 3) {
                                        parol3.setBackgroundResource(R.drawable.pin);
                                    } else if (count == 4) {
                                        parol4.setBackgroundResource(R.drawable.pin);
                                    }
                                }
                                if(newcode.length()==4) {
                                    final DatabaseReference dbr = mdt.child(id1);
                                    Map<String, Object> updates = new HashMap<>();
                                    updates.put("code", newcode);
                                    updates.put("codeexist", "1");
                                    dbr.updateChildren(updates);
                                    Toast.makeText(PinLockActivity.this, "Код пароль создан", Toast.LENGTH_SHORT).show();
                                    Intent i;
                                    i = new Intent(PinLockActivity.this,MainActivity.class);
                                    startActivity(i);
                                }
                            }
                        });
                        button3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(newcode.length()==4) {
                                    final DatabaseReference dbr = mdt.child(id1);
                                    Map<String, Object> updates = new HashMap<>();
                                    updates.put("code", newcode);
                                    updates.put("codeexist", "1");
                                    dbr.updateChildren(updates);
                                    Toast.makeText(PinLockActivity.this, "Код пароль создан", Toast.LENGTH_SHORT).show();
                                    Intent i;
                                    i = new Intent(PinLockActivity.this,MainActivity.class);
                                    startActivity(i);
                                }
                                else if (newcode.length()<=4) {
                                    count += 1;
                                    newcode += "3";
                                    if (count == 1) {
                                        parol1.setBackgroundResource(R.drawable.pin);
                                    } else if (count == 2) {
                                        parol2.setBackgroundResource(R.drawable.pin);
                                    } else if (count == 3) {
                                        parol3.setBackgroundResource(R.drawable.pin);
                                    } else if (count == 4) {
                                        parol4.setBackgroundResource(R.drawable.pin);
                                    }
                                }
                                if(newcode.length()==4) {
                                    final DatabaseReference dbr = mdt.child(id1);
                                    Map<String, Object> updates = new HashMap<>();
                                    updates.put("code", newcode);
                                    updates.put("codeexist", "1");
                                    dbr.updateChildren(updates);
                                    Toast.makeText(PinLockActivity.this, "Код пароль создан", Toast.LENGTH_SHORT).show();
                                    Intent i;
                                    i = new Intent(PinLockActivity.this,MainActivity.class);
                                    startActivity(i);
                                }
                            }

                        });
                        button4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(newcode.length()==4) {
                                    final DatabaseReference dbr = mdt.child(id1);
                                    Map<String, Object> updates = new HashMap<>();
                                    updates.put("code", newcode);
                                    updates.put("codeexist", "1");
                                    dbr.updateChildren(updates);
                                    Toast.makeText(PinLockActivity.this, "Код пароль создан", Toast.LENGTH_SHORT).show();
                                    Intent i;
                                    i = new Intent(PinLockActivity.this,MainActivity.class);
                                    startActivity(i);
                                }
                                else if (newcode.length()<=4) {
                                    count += 1;
                                    newcode += "4";
                                    if (count == 1) {
                                        parol1.setBackgroundResource(R.drawable.pin);
                                    } else if (count == 2) {
                                        parol2.setBackgroundResource(R.drawable.pin);
                                    } else if (count == 3) {
                                        parol3.setBackgroundResource(R.drawable.pin);
                                    } else if (count == 4) {
                                        parol4.setBackgroundResource(R.drawable.pin);
                                    }
                                }
                                if(newcode.length()==4) {
                                    final DatabaseReference dbr = mdt.child(id1);
                                    Map<String, Object> updates = new HashMap<>();
                                    updates.put("code", newcode);
                                    updates.put("codeexist", "1");
                                    dbr.updateChildren(updates);
                                    Toast.makeText(PinLockActivity.this, "Код пароль создан", Toast.LENGTH_SHORT).show();
                                    Intent i;
                                    i = new Intent(PinLockActivity.this,MainActivity.class);
                                    startActivity(i);
                                }
                            }

                        });
                        button5.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(newcode.length()==4) {
                                    final DatabaseReference dbr = mdt.child(id1);
                                    Map<String, Object> updates = new HashMap<>();
                                    updates.put("code", newcode);
                                    updates.put("codeexist", "1");
                                    dbr.updateChildren(updates);
                                    Toast.makeText(PinLockActivity.this, "Код пароль создан", Toast.LENGTH_SHORT).show();
                                    Intent i;
                                    i = new Intent(PinLockActivity.this,MainActivity.class);
                                    startActivity(i);
                                }
                                else if (newcode.length()<=4) {
                                    count += 1;
                                    newcode += "5";
                                    if (count == 1) {
                                        parol1.setBackgroundResource(R.drawable.pin);
                                    } else if (count == 2) {
                                        parol2.setBackgroundResource(R.drawable.pin);
                                    } else if (count == 3) {
                                        parol3.setBackgroundResource(R.drawable.pin);
                                    } else if (count == 4) {
                                        parol4.setBackgroundResource(R.drawable.pin);
                                    }
                                }
                                if(newcode.length()==4) {
                                    final DatabaseReference dbr = mdt.child(id1);
                                    Map<String, Object> updates = new HashMap<>();
                                    updates.put("code", newcode);
                                    updates.put("codeexist", "1");
                                    dbr.updateChildren(updates);
                                    Toast.makeText(PinLockActivity.this, "Код пароль создан", Toast.LENGTH_SHORT).show();
                                    Intent i;
                                    i = new Intent(PinLockActivity.this,MainActivity.class);
                                    startActivity(i);
                                }
                            }

                        });
                        button6.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(newcode.length()==4) {
                                    final DatabaseReference dbr = mdt.child(id1);
                                    Map<String, Object> updates = new HashMap<>();
                                    updates.put("code", newcode);
                                    updates.put("codeexist", "1");
                                    dbr.updateChildren(updates);
                                    Toast.makeText(PinLockActivity.this, "Код пароль создан", Toast.LENGTH_SHORT).show();
                                    Intent i;
                                    i = new Intent(PinLockActivity.this,MainActivity.class);
                                    startActivity(i);
                                }
                                else if (newcode.length()<=4) {
                                    count += 1;
                                    newcode += "6";
                                    if (count == 1) {
                                        parol1.setBackgroundResource(R.drawable.pin);
                                    } else if (count == 2) {
                                        parol2.setBackgroundResource(R.drawable.pin);
                                    } else if (count == 3) {
                                        parol3.setBackgroundResource(R.drawable.pin);
                                    } else if (count == 4) {
                                        parol4.setBackgroundResource(R.drawable.pin);
                                    }
                                }
                                if(newcode.length()==4) {
                                    final DatabaseReference dbr = mdt.child(id1);
                                    Map<String, Object> updates = new HashMap<>();
                                    updates.put("code", newcode);
                                    updates.put("codeexist", "1");
                                    dbr.updateChildren(updates);
                                    Toast.makeText(PinLockActivity.this, "Код пароль создан", Toast.LENGTH_SHORT).show();
                                    Intent i;
                                    i = new Intent(PinLockActivity.this,MainActivity.class);
                                    startActivity(i);
                                }
                            }

                        });
                        button7.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(newcode.length()==4) {
                                    final DatabaseReference dbr = mdt.child(id1);
                                    Map<String, Object> updates = new HashMap<>();
                                    updates.put("code", newcode);
                                    updates.put("codeexist", "1");
                                    dbr.updateChildren(updates);
                                    Toast.makeText(PinLockActivity.this, "Код пароль создан", Toast.LENGTH_SHORT).show();
                                    Intent i;
                                    i = new Intent(PinLockActivity.this,MainActivity.class);
                                    startActivity(i);
                                }
                                else if (newcode.length()<=4) {
                                    count += 1;
                                    newcode += "7";
                                    if (count == 1) {
                                        parol1.setBackgroundResource(R.drawable.pin);
                                    } else if (count == 2) {
                                        parol2.setBackgroundResource(R.drawable.pin);
                                    } else if (count == 3) {
                                        parol3.setBackgroundResource(R.drawable.pin);
                                    } else if (count == 4) {
                                        parol4.setBackgroundResource(R.drawable.pin);
                                    }
                                }
                                if(newcode.length()==4) {
                                    final DatabaseReference dbr = mdt.child(id1);
                                    Map<String, Object> updates = new HashMap<>();
                                    updates.put("code", newcode);
                                    updates.put("codeexist", "1");
                                    dbr.updateChildren(updates);
                                    Toast.makeText(PinLockActivity.this, "Код пароль создан", Toast.LENGTH_SHORT).show();
                                    Intent i;
                                    i = new Intent(PinLockActivity.this,MainActivity.class);
                                    startActivity(i);
                                }
                            }

                        });
                        button8.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(newcode.length()==4) {
                                    final DatabaseReference dbr = mdt.child(id1);
                                    Map<String, Object> updates = new HashMap<>();
                                    updates.put("code", newcode);
                                    updates.put("codeexist", "1");
                                    dbr.updateChildren(updates);
                                    Toast.makeText(PinLockActivity.this, "Код пароль создан", Toast.LENGTH_SHORT).show();
                                    Intent i;
                                    i = new Intent(PinLockActivity.this,MainActivity.class);
                                    startActivity(i);
                                }
                                else if (newcode.length()<=4) {
                                    count += 1;
                                    newcode += "8";
                                    if (count == 1) {
                                        parol1.setBackgroundResource(R.drawable.pin);
                                    } else if (count == 2) {
                                        parol2.setBackgroundResource(R.drawable.pin);
                                    } else if (count == 3) {
                                        parol3.setBackgroundResource(R.drawable.pin);
                                    } else if (count == 4) {
                                        parol4.setBackgroundResource(R.drawable.pin);
                                    }
                                }
                                if(newcode.length()==4) {
                                    final DatabaseReference dbr = mdt.child(id1);
                                    Map<String, Object> updates = new HashMap<>();
                                    updates.put("code", newcode);
                                    updates.put("codeexist", "1");
                                    dbr.updateChildren(updates);
                                    Toast.makeText(PinLockActivity.this, "Код пароль создан", Toast.LENGTH_SHORT).show();
                                    Intent i;
                                    i = new Intent(PinLockActivity.this,MainActivity.class);
                                    startActivity(i);
                                }
                            }

                        });
                        button9.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(newcode.length()==4) {
                                    final DatabaseReference dbr = mdt.child(id1);
                                    Map<String, Object> updates = new HashMap<>();
                                    updates.put("code", newcode);
                                    updates.put("codeexist", "1");
                                    dbr.updateChildren(updates);
                                    Toast.makeText(PinLockActivity.this, "Код пароль создан", Toast.LENGTH_SHORT).show();
                                    Intent i;
                                    i = new Intent(PinLockActivity.this,MainActivity.class);
                                    startActivity(i);
                                }
                                else if (newcode.length()<=4) {
                                    count += 1;
                                    newcode += "9";
                                    if (count == 1) {
                                        parol1.setBackgroundResource(R.drawable.pin);
                                    } else if (count == 2) {
                                        parol2.setBackgroundResource(R.drawable.pin);
                                    } else if (count == 3) {
                                        parol3.setBackgroundResource(R.drawable.pin);
                                    } else if (count == 4) {
                                        parol4.setBackgroundResource(R.drawable.pin);
                                    }
                                }
                                if(newcode.length()==4) {
                                    final DatabaseReference dbr = mdt.child(id1);
                                    Map<String, Object> updates = new HashMap<>();
                                    updates.put("code", newcode);
                                    updates.put("codeexist", "1");
                                    dbr.updateChildren(updates);
                                    Toast.makeText(PinLockActivity.this, "Код пароль создан", Toast.LENGTH_SHORT).show();
                                    Intent i;
                                    i = new Intent(PinLockActivity.this,MainActivity.class);
                                    startActivity(i);
                                }
                            }

                        });
                        button0.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(newcode.length()==4) {
                                    final DatabaseReference dbr = mdt.child(id1);
                                    Map<String, Object> updates = new HashMap<>();
                                    updates.put("code", newcode);
                                    updates.put("codeexist", "1");
                                    dbr.updateChildren(updates);
                                    Toast.makeText(PinLockActivity.this, "Код пароль создан", Toast.LENGTH_SHORT).show();
                                    Intent i;
                                    i = new Intent(PinLockActivity.this,MainActivity.class);
                                    startActivity(i);
                                }
                                else if (newcode.length()<=4) {
                                    count += 1;
                                    newcode += "0";
                                    if (count == 1) {
                                        parol1.setBackgroundResource(R.drawable.pin);
                                    } else if (count == 2) {
                                        parol2.setBackgroundResource(R.drawable.pin);
                                    } else if (count == 3) {
                                        parol3.setBackgroundResource(R.drawable.pin);
                                    } else if (count == 4) {
                                        parol4.setBackgroundResource(R.drawable.pin);
                                    }
                                }
                                if(newcode.length()==4) {
                                    final DatabaseReference dbr = mdt.child(id1);
                                    Map<String, Object> updates = new HashMap<>();
                                    updates.put("code", newcode);
                                    updates.put("codeexist", "1");
                                    dbr.updateChildren(updates);
                                    Toast.makeText(PinLockActivity.this, "Код пароль создан", Toast.LENGTH_SHORT).show();
                                    Intent i;
                                    i = new Intent(PinLockActivity.this,MainActivity.class);
                                    startActivity(i);
                                }
                            }

                        });
                        back.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (newcode.length() > 0 && count >=1) {
                                    newcode = newcode.substring(0, newcode.length()-1);
                                    if (count == 1) {
                                        parol1.setBackgroundResource(R.drawable.freepin);
                                        count-=1;
                                    } else if (count == 2) {
                                        parol2.setBackgroundResource(R.drawable.freepin);
                                        count-=1;
                                    } else if (count == 3) {
                                        parol3.setBackgroundResource(R.drawable.freepin);
                                        count-=1;
                                    } else if (count == 4) {
                                        parol4.setBackgroundResource(R.drawable.freepin);
                                        count-=1;
                                    }
                                }
                            }
                        });
                    }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}