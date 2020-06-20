package com.nopalyer.navigationdrawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nopalyer.navigationdrawer.Admin.AdminNoReg;

public class registrationp2 extends AppCompatActivity {
    CardView c1,c2,c3,c4,c5,c6;
    ProgressDialog pd;
    Toolbar toolbar;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference ref1,ref2,ref3,ref4,ref5,ref6;
    String semcon1,semcon2,semcon3,semcon4,semcon5,semcon6;
    String course = "UG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrationp2);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Registration");
        toolbar.setTitleTextColor(Color.WHITE);

        c1 = findViewById(R.id.cardview);
        c2 = findViewById(R.id.cardview1);
        c3 = findViewById(R.id.cardview2);
        c4 = findViewById(R.id.cardview3);
        c5 = findViewById(R.id.cardview4);
        c6 = findViewById(R.id.cardview5);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        pd = new ProgressDialog(this);



        pd.setMessage("Wait");
        pd.show();
        ref1 = firebaseDatabase.getReference("Admin Switch").child("UG Switch");
        ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                semcon1 = dataSnapshot.child("Condition").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        ref2 = firebaseDatabase.getReference("Admin Switch").child("PG Switch");
        ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                semcon2 = dataSnapshot.child("Condition").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ref3 = firebaseDatabase.getReference("Admin Switch").child("PhD Switch");
        ref3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                semcon3 = dataSnapshot.child("Condition").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        ref4 = firebaseDatabase.getReference("Admin Switch").child("OpenElective Switch");
        ref4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                semcon4 = dataSnapshot.child("Condition").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ref5 = firebaseDatabase.getReference("Admin Switch").child("Document Switch");
        ref5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                semcon5 = dataSnapshot.child("Condition").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        ref6 = firebaseDatabase.getReference("Admin Switch").child("LateRegistration Switch");
        ref6.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                semcon6 = dataSnapshot.child("Condition").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        pd.dismiss();

        if(course == "UG"){
            c2.setVisibility(View.GONE);
            c3.setVisibility(View.GONE);
            c4.setVisibility(View.GONE);
            c5.setVisibility(View.GONE);
            c6.setVisibility(View.GONE);

        }
        if(course == "PG"){
            c1.setVisibility(View.GONE);
            c3.setVisibility(View.GONE);
            c4.setVisibility(View.GONE);
            c5.setVisibility(View.GONE);
            c6.setVisibility(View.GONE);

        }
        if(course == "Phd"){
            c2.setVisibility(View.GONE);
            c1.setVisibility(View.GONE);
            c4.setVisibility(View.GONE);
            c5.setVisibility(View.GONE);
            c6.setVisibility(View.GONE);

        }
        if(course == "Open"){
            c2.setVisibility(View.GONE);
            c3.setVisibility(View.GONE);
            c1.setVisibility(View.GONE);
            c5.setVisibility(View.GONE);
            c6.setVisibility(View.GONE);

        }
        if(course == "Late"){
            c2.setVisibility(View.GONE);
            c3.setVisibility(View.GONE);
            c4.setVisibility(View.GONE);
            c1.setVisibility(View.GONE);
            c6.setVisibility(View.GONE);

        }
        if(course == "Bona"){
            c2.setVisibility(View.GONE);
            c3.setVisibility(View.GONE);
            c4.setVisibility(View.GONE);
            c5.setVisibility(View.GONE);
            c1.setVisibility(View.GONE);

        }

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (semcon1.equals("true")) {
                    Intent intent = new Intent(registrationp2.this, Btech_registration.class);
                    startActivity(intent);
                }
                if (semcon1.equals("false")) {
                    Intent intent = new Intent(registrationp2.this, AdminNoReg.class);
                    startActivity(intent);
                }
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (semcon2.equals("true")) {
                    Intent intent = new Intent(registrationp2.this, PgRegis.class);
                    startActivity(intent);
                }
                if (semcon2.equals("false")) {
                    Intent intent = new Intent(registrationp2.this, AdminNoReg.class);
                    startActivity(intent);
                }
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (semcon3.equals("true")) {
                    Intent intent = new Intent(registrationp2.this, PhdRegis.class);
                    startActivity(intent);
                }
                if (semcon3.equals("false")) {
                    Intent intent = new Intent(registrationp2.this, AdminNoReg.class);
                    startActivity(intent);
                }
            }
        });
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (semcon4.equals("true")) {
                    Intent intent = new Intent(registrationp2.this, Openelective.class);
                    startActivity(intent);
                }
                if (semcon4.equals("false")) {
                    Intent intent = new Intent(registrationp2.this, AdminNoReg.class);
                    startActivity(intent);
                }
            }
        });
        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (semcon5.equals("true")) {
                    Intent intent = new Intent(registrationp2.this, LateRegistration.class);
                    startActivity(intent);
                }
                if (semcon5.equals("false")) {
                    Intent intent = new Intent(registrationp2.this, AdminNoReg.class);
                    startActivity(intent);
                }
            }
        });
        c6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (semcon6.equals("true")) {
                    Intent intent = new Intent(registrationp2.this, Bonafide_Application.class);
                    startActivity(intent);
                }
                if (semcon6.equals("false")) {
                    Intent intent = new Intent(registrationp2.this, AdminNoReg.class);
                    startActivity(intent);
                }
            }
        });



    }
}