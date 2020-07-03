package com.nopalyer.navigationdrawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegInstruction extends AppCompatActivity {

    Button Next;
    private CheckBox tick;
    String programme = "";
    TextView name,roll,prog,dep,year;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference ref;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_instruction);

        Next = findViewById(R.id.press);
        name = findViewById(R.id.naam);
        roll = findViewById(R.id.kramank);
        prog = findViewById(R.id.karyakrm);
        dep = findViewById(R.id.vibhag);
        year = findViewById(R.id.umr);
        pd = new ProgressDialog(this);

        tick = (CheckBox) findViewById(R.id.tiktok);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        programme = getIntent().getStringExtra("prog");

        pd.setMessage("Wait ...");
        pd.setCancelable(false);
        pd.show();
        ref = firebaseDatabase.getReference(firebaseAuth.getUid()).child("Profile");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               name.setText(dataSnapshot.child("Name").getValue().toString());
               roll.setText(dataSnapshot.child("Roll No").getValue().toString());
               prog.setText(dataSnapshot.child("Programme").getValue().toString());
               dep.setText(dataSnapshot.child("Department").getValue().toString());
               year.setText(dataSnapshot.child("Year").getValue().toString());
               pd.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(RegInstruction.this,databaseError.getCode(),Toast.LENGTH_SHORT).show();
            }
        });

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tick.isChecked()){
                    if (programme.equals("Ug")){
                        startActivity(new Intent(RegInstruction.this,Btech_registration.class));
                    }else if (programme.equals("Pg")){
                        startActivity(new Intent(RegInstruction.this,PgRegis.class));
                    }else if (programme.equals("Phd")){
                        startActivity(new Intent(RegInstruction.this,PhdRegis.class));
                    }else if (programme.equals("late")){
                        startActivity(new Intent(RegInstruction.this,LateRegistration.class));
                    }
                }
            }
        });

    }
}