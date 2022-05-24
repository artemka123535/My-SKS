package com.example.loginapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.billingclient.api.BillingClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Calendar;
import java.util.Map;


public class fragment_shet extends Fragment {
    private HorizontalScrollView adresa;
    private DatabaseReference mdt;
    private FirebaseAuth mauth;
    private String address;
    private float cash;
    private String address1;
    private String schet1;
    private float cash1;
    private TextView money;
    private TextView money1;
    private BillingClient billingClient;
    private ImageButton oplata;
    private ImageButton peredacha;
    private SharedPreferences sbpokaz;
    private String id1;
    private int GVS;
    private int people;
    private int times = 0;

    public fragment_shet() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_shet, container, false);
        adresa = v.findViewById(R.id.adresa);
        Button adres1 = v.findViewById(R.id.adres1);
        Button adres2 = v.findViewById(R.id.adres2);
        money = v.findViewById(R.id.cash);
        money1 = v.findViewById(R.id.cash1);
        ImageButton addAdres = v.findViewById(R.id.addAdres);
        oplata = v.findViewById(R.id.oplata);
        peredacha = v.findViewById(R.id.watermeter);
        mauth = FirebaseAuth.getInstance();
        mdt = FirebaseDatabase.getInstance().getReference();
        sbpokaz = getActivity().getSharedPreferences("AppSettings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sbpokaz.edit();
        editor.putString("AddressStatus", "1");
        editor.apply();
        String email = mauth.getCurrentUser().getEmail();
        int date1 = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        mdt.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    User user1 = ds.getValue(User.class);
                    assert user1 != null;
                    String email1 = user1.email;
                    if (email1.equals(email)) {
                        id1 = ds.getKey();
                        cash = user1.cash;
                        cash1 = user1.cash1;
                        people = user1.people;
                        if (date1 == 1 && times == 0) {
                            if (!sbpokaz.contains("PokazStatus")) {
                                final DatabaseReference dbr = mdt.child(id1);
                                Map<String, Object> updates = new HashMap<>();
                                if (GVS != 0) {
                                    updates.put("cash", (cash + 5.7 * 47.26 * people + 5.7 * 44.47 * people));
                                    dbr.updateChildren(updates);
                                    times = 1;

                                } else {
                                    updates.put("cash", (cash + 5.7 * 47.26 * people + 5.7 * 44.47 * people));
                                    dbr.updateChildren(updates);
                                    times = 1;
                                }
                            } else if (sbpokaz.getString("PokazStatus", "").equals("0")) {
                                final DatabaseReference dbr = mdt.child(id1);
                                Map<String, Object> updates = new HashMap<>();
                                if (GVS != 0) {
                                    updates.put("cash", (cash + 5.7 * 47.26 * people + 5.7 * 44.47 * people));
                                    dbr.updateChildren(updates);
                                    times = 1;

                                } else {
                                    updates.put("cash", (cash + 5.7 * 47.26 * people + 5.7 * 44.47 * people));
                                    dbr.updateChildren(updates);
                                    times = 1;
                                }
                            } else if (!sbpokaz.contains("PokazStatus1")) {
                                final DatabaseReference dbr = mdt.child(id1);
                                Map<String, Object> updates = new HashMap<>();
                                if (GVS != 0) {
                                    updates.put("cash1", (cash1 + 5.7 * 47.26 * people +5.7 * 44.47 * people));
                                    dbr.updateChildren(updates);
                                    times = 1;

                                } else {
                                    updates.put("cash1", (cash1 + 5.7 * 47.26 * people + 5.7 * 44.47 * people));
                                    dbr.updateChildren(updates);
                                    times = 1;
                                }
                            } else if (sbpokaz.getString("PokazStatus1", "").equals("0")) {
                                final DatabaseReference dbr = mdt.child(id1);
                                Map<String, Object> updates = new HashMap<>();
                                if (GVS != 0) {
                                    updates.put("cash1", (cash1 + 5.7 * 47.26 * people  +5.7 * 44.47 * people));
                                    dbr.updateChildren(updates);
                                    times = 1;

                                } else {
                                    updates.put("cash1", (cash1 + 5.7 * 47.26 * people + 5.7 * 44.47 * people));
                                    dbr.updateChildren(updates);
                                    times = 1;
                                }
                            }
                        }
                        address = user1.address;
                        address1 = user1.address1;
                        schet1 = user1.schet1;
                        GVS = user1.GVS;
                        cash = user1.cash;
                        cash1 = user1.cash1;
                        adres1.setText(address);
                        money.setText(String.valueOf(cash) + " руб.");
                        money1.setText(String.valueOf(cash1) + " руб.");
                        if (sbpokaz.contains("SchetStatus1")) {
                            adres2.setVisibility(View.VISIBLE);
                            if (adres2.getVisibility() == View.VISIBLE) {
                                adres2.setText(address1);
                            }
                        }

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        if (date1 == 20) {
            editor.putString("PokazStatus", "0");
            editor.apply();
            editor.putString("PokazStatus1", "0");
            editor.apply();
        }
        addAdres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!sbpokaz.contains("SchetStatus1")) {
                    Intent i;
                    i = new Intent(getActivity(), AddSchetActivity.class);
                    startActivity(i);
                }
            }
        });
        adres2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("AddressStatus", "2");
                editor.apply();
                adres1.setBackgroundColor(getResources().getColor(R.color.grey));
                adres2.setBackgroundColor(getResources().getColor(R.color.white));
                money1.setVisibility(View.VISIBLE);
                money.setVisibility(View.INVISIBLE);
            }
        });
        adres1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("AddressStatus", "1");
                editor.apply();
                adres2.setBackgroundColor(getResources().getColor(R.color.grey));
                adres1.setBackgroundColor(getResources().getColor(R.color.white));
                money.setVisibility(View.VISIBLE);
                money1.setVisibility(View.INVISIBLE);
            }
        });
        if (25 >= date1 && date1 >= 20) {
            peredacha.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!sbpokaz.contains("PokazStatus") && money.getVisibility()==View.VISIBLE) {
                        Intent i;
                        i = new Intent(getActivity(), PeredachaActivity.class);
                        startActivity(i);
                    } else if (sbpokaz.contains("PokazStatus") && money.getVisibility()==View.VISIBLE) {
                        if (sbpokaz.getString("PokazStatus", "").equals("0")) {
                            Intent i;
                            i = new Intent(getActivity(), PeredachaActivity.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(getActivity(), "Показания счётчика уже добавлены", Toast.LENGTH_SHORT).show();
                        }
                    } else if (!sbpokaz.contains("PokazStatus1") && money1.getVisibility()==View.VISIBLE) {
                        Intent i;
                        i = new Intent(getActivity(), PeredachaActivity.class);
                        startActivity(i);
                    } else if (sbpokaz.contains("PokazStatus1") && money1.getVisibility()==View.VISIBLE) {
                        if (sbpokaz.getString("PokazStatus1", "").equals("0")) {
                            Intent i;
                            i = new Intent(getActivity(), PeredachaActivity.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(getActivity(), "Показания счётчика уже добавлены", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getActivity(), "Показания счётчика уже добавлены", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            peredacha.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "Снимать показания можно с 20 по 25", Toast.LENGTH_SHORT).show();
                }
            });
        }
        oplata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!money.getText().toString().equals("0.0 руб.") && money.getVisibility() == View.VISIBLE){
                    Intent i;
                    i = new Intent(getActivity(), Uspeh.class);
                    startActivity(i);
                }else if (!money1.getText().toString().equals("0.0 руб.") && money1.getVisibility() == View.VISIBLE){
                    Intent i;
                    i = new Intent(getActivity(), Uspeh.class);
                    startActivity(i);
                }else{
                    Toast.makeText(getActivity(), "Вы всё оплатили", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return v;
    }
}