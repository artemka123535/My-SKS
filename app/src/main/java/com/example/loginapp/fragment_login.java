package com.example.loginapp;


import android.content.Intent;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 *
 */
public class fragment_login extends Fragment {
    private EditText email;
    private EditText password;
    private FirebaseAuth mauth;


    public fragment_login() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        email = v.findViewById(R.id.et_email);
        password = v.findViewById(R.id.et_password);
        mauth = FirebaseAuth.getInstance();
        Button button = v.findViewById(R.id.btn_login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mdtemail = email.getText().toString();
                String mdtpassword = password.getText().toString();
                if (!TextUtils.isEmpty(mdtemail) && !TextUtils.isEmpty(mdtpassword)) {
                    mauth.signInWithEmailAndPassword(mdtemail,mdtpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent i;
                                i = new Intent(getActivity(),PinLockActivity.class);
                                startActivity(i);
                            }else {
                                Toast.makeText(getActivity().getApplicationContext(), "Вы неправильно ввели данные", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }

        });
        // Inflate the layout for this fragment
        return v;
    }
}