package com.nopalyer.navigationdrawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

public class PhysicalVerification extends AppCompatActivity {

    Toolbar toolbar;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference ref,mref,ref2,ref3,ref4,ref5,ref6,ref7;
    String dep = "",yr = "",roll = "";
    private TextView libver,hosver;
    private Button done,form,reciept;
    ProgressDialog pd;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ver_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id =item.getItemId();
        switch(id)
        {
            case R.id.verlogout:
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(PhysicalVerification.this, login.class));
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physical_verification);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Verification");
        toolbar.setTitleTextColor(Color.WHITE);

        libver = findViewById(R.id.libtextver);
        hosver = findViewById(R.id.hostextver);
        done = findViewById(R.id.verdone);
        form = findViewById(R.id.showForm);
        reciept = findViewById(R.id.showReciept);
        pd = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        dep = getIntent().getExtras().getString("dep");
        yr = getIntent().getExtras().getString("year");
        roll = getIntent().getExtras().getString("roll");

        pd.setMessage("Wait!");
        pd.show();
        ref = firebaseDatabase.getReference();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("Other Verification")){
                    ref2 = firebaseDatabase.getReference("Other Verification");
                    ref2.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.hasChild(yr)){
                                ref3 = firebaseDatabase.getReference("Other Verification").child(yr);
                                ref3.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.hasChild(dep)){
                                            ref4 = firebaseDatabase.getReference("Other Verification").child(yr).child(dep);
                                            ref4.addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    if (dataSnapshot.hasChild(roll)){
                                                        ref5 = firebaseDatabase.getReference("Other Verification").child(yr).child(dep).child(roll);
                                                        ref5.addListenerForSingleValueEvent(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                if (dataSnapshot.hasChild("LibVerify")){
                                                                    String lib = dataSnapshot.child("LibVerify").getValue().toString();
                                                                    libver.setText(lib);
                                                                    ref6 = firebaseDatabase.getReference("Other Verification").child(yr).child(dep).child(roll);
                                                                    ref6.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                        @Override
                                                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                            if (dataSnapshot.hasChild("HosVerify")){
                                                                                String hostel = dataSnapshot.child("HosVerify").getValue().toString();
                                                                                hosver.setText(hostel);
                                                                                pd.dismiss();
                                                                            }else {
                                                                                hosver.setText("No");
                                                                                pd.dismiss();
                                                                            }
                                                                        }

                                                                        @Override
                                                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                                                        }
                                                                    });
                                                                }else {
                                                                    libver.setText("No");
                                                                    pd.dismiss();
                                                                }
                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                                            }
                                                        });
                                                    }else {
                                                        libver.setText("No");
                                                        hosver.setText("No");
                                                        pd.dismiss();
                                                    }
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                        }else {
                                            libver.setText("No");
                                            hosver.setText("No");
                                            pd.dismiss();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }else {
                                libver.setText("No");
                                hosver.setText("No");
                                pd.dismiss();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }else {
                    libver.setText("No");
                    hosver.setText("No");
                    pd.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mref = firebaseDatabase.getReference("Final Verification").child(yr).child(dep).child(roll);
                mref.child("verify").setValue("yes");
                Toast.makeText(PhysicalVerification.this,"Done",Toast.LENGTH_SHORT).show();
            }
        });

        form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PhysicalVerification.this, RegistrationformVer.class);
                i.putExtra("roll", roll);
                i.putExtra("year", yr);
                i.putExtra("dep", dep);

                startActivity(i);
            }
        });

    }
}