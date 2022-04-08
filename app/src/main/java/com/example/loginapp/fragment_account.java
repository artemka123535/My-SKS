package com.example.loginapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class fragment_account extends Fragment {
    private DatabaseReference mdt;
    private List<String> NameList;
    private List<String> SurnameList;
    private TextView user;
    private FirebaseAuth mauth;

    public fragment_account() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mauth = FirebaseAuth.getInstance();
        View v = inflater.inflate(R.layout.fragment_account, container, false);
        Button vyhod = v.findViewById(R.id.Vyhod);
        Button zaschita = v.findViewById(R.id.zashcita);
        Button personal = v.findViewById(R.id.personal);
        Button change = v.findViewById(R.id.change);
        Button verification = v.findViewById(R.id.proverka);
        mdt = FirebaseDatabase.getInstance().getReference();
        NameList = new ArrayList<>();
        SurnameList = new ArrayList<>();
        user = v.findViewById(R.id.user);
        String email = mauth.getCurrentUser().getEmail();
        TextView avatarka = v.findViewById(R.id.avatarka);
        mdt.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(NameList.size()>0){
                    NameList.clear();
                }
                if(SurnameList.size()>0){
                    SurnameList.clear();
                }
                for (DataSnapshot ds: snapshot.getChildren()){
                    User user1 = ds.getValue(User.class);
                    assert user1 != null;
                    String email1 =user1.email;
                    if(email1.equals(email)) {
                        NameList.add(user1.name);
                        SurnameList.add(user1.surname);
                    }
                }
                if(NameList!=null && SurnameList!=null){
                   user.setText(SurnameList.get(0)+" "+ NameList.get(0));
                    avatarka.setText(SurnameList.get(0).charAt(0) + " "+ NameList.get(0).charAt(0));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i;
                i = new Intent(getActivity(),PersonalActivity.class);
                startActivity(i);
            }
        });
        vyhod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mauth.signOut();
                Intent i;
                i = new Intent(getActivity(),MainActivity.class);
                startActivity(i);
            }
        });
        zaschita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i;
                i = new Intent(getActivity(),SecurityActivity.class);
                startActivity(i);
            }
        });
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i;
                i = new Intent(getActivity(),ChangeActivity.class);
                startActivity(i);
            }
        });
        verification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i;
                i = new Intent(getActivity(),VerificationActivity.class);
                startActivity(i);
            }
        });
        return v;
    }
}