package com.nopalyer.navigationdrawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BonafideView extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView name,faname,rolln,dobn,semtr,catg,email,doc;
    private Button upload,edit;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog mProgress;
    private FirebaseDatabase database;
    private DatabaseReference ref,mref;
    String yr = "",dep = "",roll = "";
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonafide_view);

        toolbar = (Toolbar) findViewById(R.id.toolbar2);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Review Form");
        toolbar.setTitleTextColor(Color.WHITE);

        bundle = getIntent().getExtras();
        UI();

        name.setText(bundle.get("Name").toString());
        faname.setText(bundle.get("Programme").toString());
        rolln.setText(bundle.get("Roll No").toString());
        dobn.setText(bundle.get("Mobile").toString());
        semtr.setText(bundle.get("sem").toString());
        catg.setText(bundle.get("dep").toString());
        email.setText(bundle.get("Email").toString());
        doc.setText(bundle.get("type").toString());

        mProgress = new ProgressDialog(this);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgress.setMessage("Wait");
                mProgress.show();
                ref = database.getReference(firebaseAuth.getUid()).child("Profile");
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        yr = dataSnapshot.child("Year").getValue().toString();
                        dep = dataSnapshot.child("Department").getValue().toString();
                        roll = dataSnapshot.child("Roll No").getValue().toString();

                        mref = database.getReference("Bonafide Application").child(roll);
                        mref.child("Year").setValue(yr);
                        mref.child("Name").setValue(name.getText());
                        mref.child("Programme").setValue(faname.getText());
                        mref.child("RollNo").setValue(rolln.getText());
                        mref.child("Mobile").setValue(dobn.getText());
                        mref.child("Semester").setValue(semtr.getText());
                        mref.child("Department").setValue(catg.getText());
                        mref.child("Email").setValue(email.getText());
                        mref.child("Document").setValue(doc.getText());

                        mProgress.dismiss();
                        Toast.makeText(BonafideView.this,"Upload Done",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(BonafideView.this,databaseError.getCode(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BonafideView.this,Bonafide_Application.class));
            }
        });
    }

    private void  UI(){
        name = (TextView)findViewById(R.id.BontvName);
        faname = (TextView)findViewById(R.id.BontvPro);
        rolln = (TextView)findViewById(R.id.Bontvroll);
        dobn = (TextView)findViewById(R.id.BontvCon);
        semtr = (TextView)findViewById(R.id.BontvSem);
        catg = (TextView)findViewById(R.id.Bontvdep);
        email = (TextView)findViewById(R.id.Bontvemail);
        doc = (TextView)findViewById(R.id.Bontvdoc);
        upload = (Button)findViewById(R.id.Bonaddpdf);
        edit = (Button)findViewById(R.id.Bonedit);
        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
    }
}