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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonalActivity extends AppCompatActivity {
    private EditText surname;
    private EditText name;
    private  EditText patronymic;
    private EditText sex;
    private EditText birth;
    private List<String> NameList;
    private List<String> SurnameList;
    private List<String> PatronymicList;
    private List<String> SexList;
    private List<String> BirthList;
    private DatabaseReference mdt;
    private FirebaseAuth mauth;
    private String id1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        getSupportActionBar().setTitle("Личные данные");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mauth = FirebaseAuth.getInstance();
        surname = findViewById(R.id.surname);
        name = findViewById(R.id.name);
        patronymic = findViewById(R.id.patronymic);
        sex = findViewById(R.id.sex);
        birth = findViewById(R.id.birth);
        NameList = new ArrayList<>();
        SurnameList = new ArrayList<>();
        PatronymicList = new ArrayList<>();
        SexList = new ArrayList<>();
        BirthList = new ArrayList<>();
        mdt = FirebaseDatabase.getInstance().getReference();
        String email = mauth.getCurrentUser().getEmail();
        mdt.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(NameList.size()>0){
                    NameList.clear();
                }
                if(SurnameList.size()>0){
                    SurnameList.clear();
                }
                if(PatronymicList.size()>0){
                    PatronymicList.clear();
                }
                if(SexList.size()>0){
                    SexList.clear();
                }
                if(BirthList.size()>0){
                    BirthList.clear();
                }
                for (DataSnapshot ds: snapshot.getChildren()){
                    User user1 = ds.getValue(User.class);
                    assert user1 != null;
                    String email1 =user1.email;
                    if(email1.equals(email)) {
                        id1 = ds.getKey();
                        NameList.add(user1.name);
                        SurnameList.add(user1.surname);
                        PatronymicList.add(user1.patronymic);
                        SexList.add(user1.sex);
                        BirthList.add(user1.birth);
                    }
                }
                if(NameList!=null){
                   name.setText(NameList.get(0));
                }
                if(SurnameList!=null){
                   surname.setText(SurnameList.get(0));
                }
                if(PatronymicList!=null){
                   patronymic.setText(PatronymicList.get(0));
                }
                if(SexList!=null){
                  sex.setText(SexList.get(0));
                }
                if(BirthList!=null){
                birth.setText(BirthList.get(0));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        Button save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        final DatabaseReference dbr = mdt.child(id1);
                        String newsex = sex.getText().toString();
                        String newpatronymic = patronymic.getText().toString();
                        String newbirth = birth.getText().toString();
                        Map<String, Object> updates = new HashMap<>();
                        updates.put("sex", newsex);
                        updates.put("patronymic", newpatronymic);
                        updates.put("birth", newbirth);
                        dbr.updateChildren(updates);
                        Toast.makeText(PersonalActivity.this, "Данные сохранены", Toast.LENGTH_SHORT).show();
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