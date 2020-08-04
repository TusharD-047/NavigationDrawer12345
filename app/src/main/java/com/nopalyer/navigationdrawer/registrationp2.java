package com.nopalyer.navigationdrawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import com.nopalyer.navigationdrawer.Admin.AdminNoReg;

import java.util.Objects;

public class registrationp2 extends AppCompatActivity {
    CardView c1,c2,c3,c4,c5,c6;
    ProgressDialog pd;
    Toolbar toolbar;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference ref1,ref2,ref3,ref4,ref5,ref6,reef;
    String semcon1,semcon2,semcon3,semcon4,semcon5,semcon6;
    String course,year;
    Dialog msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrationp2);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Registration");
        toolbar.setTitleTextColor(Color.WHITE);

        msg = new Dialog(this);
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
        pd.setCancelable(false);
        pd.show();
        ref1 = firebaseDatabase.getReference("Admin Switch").child("UG Switch");
        ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                semcon1 = dataSnapshot.child("Condition").getValue().toString();
                ref2 = firebaseDatabase.getReference("Admin Switch").child("PG Switch");
                ref2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        semcon2 = dataSnapshot.child("Condition").getValue().toString();
                        ref3 = firebaseDatabase.getReference("Admin Switch").child("PhD Switch");
                        ref3.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                semcon3 = dataSnapshot.child("Condition").getValue().toString();
                                ref4 = firebaseDatabase.getReference("Admin Switch").child("OpenElective Switch");
                                ref4.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        semcon4 = dataSnapshot.child("Condition").getValue().toString();
                                        ref5 = firebaseDatabase.getReference("Admin Switch").child("Document Switch");
                                        ref5.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                semcon5 = dataSnapshot.child("Condition").getValue().toString();
                                                ref6 = firebaseDatabase.getReference("Admin Switch").child("LateRegistration Switch");
                                                ref6.addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                        semcon6 = dataSnapshot.child("Condition").getValue().toString();
                                                        reef = firebaseDatabase.getReference(firebaseAuth.getUid()).child("Profile");
                                                        reef.addValueEventListener(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                course = dataSnapshot.child("Programme").getValue().toString();
                                                                year = dataSnapshot.child("Year").getValue().toString();

                                                                if(course.equals("B.Tech") || course.equals("B.Arch") || course.equals("Dual Degree")){
                                                                    if (year.equals("3rd year")){
                                                                        c2.setVisibility(View.GONE);
                                                                        c3.setVisibility(View.GONE);
                                                                    }else {
                                                                        c2.setVisibility(View.GONE);
                                                                        c3.setVisibility(View.GONE);
                                                                        c4.setVisibility(View.GONE);
                                                                    }
                                                                }
                                                                else if(course.equals("M.tech") || course.equals("M.Arch") || course.equals("MBA") || course.equals("MSc")){
                                                                    c1.setVisibility(View.GONE);
                                                                    c3.setVisibility(View.GONE);
                                                                    c4.setVisibility(View.GONE);
                                                                }
                                                                else if(course.equals("PhD")){
                                                                    c2.setVisibility(View.GONE);
                                                                    c1.setVisibility(View.GONE);
                                                                    c4.setVisibility(View.GONE);
                                                                }
                                                                pd.dismiss();
                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                                            }
                                                        });
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                                    }
                                                });
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (semcon1.equals("true")) {
                    Intent intent = new Intent(registrationp2.this, RegInstruction.class);
                    intent.putExtra("prog","Ug");
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
                    Intent intent = new Intent(registrationp2.this, RegInstruction.class);
                    intent.putExtra("prog","Pg");
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
                    Intent intent = new Intent(registrationp2.this, RegInstruction.class);
                    intent.putExtra("prog","Phd");
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
                    Intent intent = new Intent(registrationp2.this, RegInstruction.class);
                    intent.putExtra("prog","late");
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

    public void showPopup(View v){
        TextView close ;
        Button cntinue;
        msg.setContentView(R.layout.popup);
        close = (TextView) msg.findViewById(R.id.close);
        cntinue = (Button) msg.findViewById(R.id.cntinue);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msg.dismiss();
            }
        });
        cntinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msg.dismiss();
            }
        });
        msg.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        msg.show();
    }

}