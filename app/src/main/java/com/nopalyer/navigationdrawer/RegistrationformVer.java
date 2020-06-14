package com.nopalyer.navigationdrawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegistrationformVer extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference ref,mref;
    String dep = "",yr = "",roll = "";
    private TextView name,faname,rolln,dobn,semtr,catg;
    private ProgressDialog mProgress;
    Button back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrationform_ver);

        name = (TextView)findViewById(R.id.vername);
        faname = (TextView)findViewById(R.id.verfname);
        rolln = (TextView)findViewById(R.id.verroll);
        dobn = (TextView)findViewById(R.id.verdob);
        semtr = (TextView)findViewById(R.id.versem);
        catg = (TextView)findViewById(R.id.vercat);
        back = findViewById(R.id.verBack);

        mProgress = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        dep = getIntent().getExtras().getString("dep");
        yr = getIntent().getExtras().getString("year");
        roll = getIntent().getExtras().getString("roll");

        mProgress.setMessage("Wait");
        mProgress.show();
        ref = firebaseDatabase.getReference("Application Form").child(yr).child(dep).child(roll);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name1 = dataSnapshot.child("Name").getValue().toString();
                String fname1 = dataSnapshot.child("FatherName").getValue().toString();
                String roll1 = dataSnapshot.child("RollNo").getValue().toString();
                String dob1 = dataSnapshot.child("DOB").getValue().toString();
                String sem1 = dataSnapshot.child("Semester").getValue().toString();
                String cat1 = dataSnapshot.child("Category").getValue().toString();

                name.setText(name1);
                faname.setText(fname1);
                rolln.setText(roll1);
                dobn.setText(dob1);
                semtr.setText(sem1);
                catg.setText(cat1);
                mProgress.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}