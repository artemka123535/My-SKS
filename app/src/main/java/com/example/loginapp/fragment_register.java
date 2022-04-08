package com.example.loginapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class fragment_register extends Fragment {
    private EditText name;
    private EditText surname;
    private EditText email;
    private EditText password;
    private EditText secondpassword;
    private DatabaseReference mdt;
    private FirebaseAuth mauth;
    boolean onlyLatinAlphabet = false;
    boolean onlyNumbers = false;
    public fragment_register() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_register, container, false);
        name = v.findViewById(R.id.et_name);
        surname = v.findViewById(R.id.et_surname);
        email = v.findViewById(R.id.et_email);
        password = v.findViewById(R.id.et_password);
        secondpassword = v.findViewById(R.id.et_repassword);
        mdt = FirebaseDatabase.getInstance().getReference();
        Button button = v.findViewById(R.id.btn_register);
        mauth = FirebaseAuth.getInstance();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = mdt.getKey();
                String mdtname = name.getText().toString();
                String mdtsurname = surname.getText().toString();
                String mdtemail = email.getText().toString();
                String mdtpassword = password.getText().toString();
                User newUser = new User(id, mdtname, mdtsurname, mdtemail,mdtpassword, null,null,null);
                int j = 0;
                int k = 0;
                for (int i = 0; i < mdtpassword.length(); i++){
                    if ("qwertyuiopasdfghjklzxcvbnm".contains(String.valueOf(mdtpassword.charAt(i)))){
                        j++;
                    }
                }
                if (j>0){
                    onlyLatinAlphabet = true;
                }
                for (int i = 0; i < mdtpassword.length(); i++){
                    if ("1234567890".contains(String.valueOf(mdtpassword.charAt(i)))){
                        k++;
                    }

                }
                if (k>0){
                    onlyNumbers = true;
                }

                if (!TextUtils.isEmpty(mdtname) && !TextUtils.isEmpty(mdtemail) && !TextUtils.isEmpty(mdtpassword) && !TextUtils.isEmpty(mdtname) && !TextUtils.isEmpty(mdtsurname) && mdtpassword.equals(secondpassword.getText().toString()) && mdtpassword.length()>=8 && onlyLatinAlphabet && onlyNumbers) {
                    mauth.createUserWithEmailAndPassword(mdtemail, mdtpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                mdt.push().setValue(newUser);
                                Toast.makeText(getActivity().getApplicationContext(), "Вы зарегестрированы", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(getActivity().getApplicationContext(), "У вас не получилось зарегестрироваться", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else if(mdtpassword.length()<8 || !onlyLatinAlphabet || !onlyNumbers){
                    Toast.makeText(getActivity().getApplicationContext(), "Пароль должен содержать минимум 8 символов", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getActivity().getApplicationContext(), "А также цифры и латинские буквы", Toast.LENGTH_SHORT).show();
                }
                else if(!mdtpassword.equals(secondpassword.getText().toString())){
                    Toast.makeText(getActivity().getApplicationContext(), "Пароли не совпадают", Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(getActivity().getApplicationContext(), "Пустое поле", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }


}