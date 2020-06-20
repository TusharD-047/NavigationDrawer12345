package com.nopalyer.navigationdrawer;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nopalyer.navigationdrawer.student.StudentsPage;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Openelective extends Activity {

    Spinner spin1,spin2,spin3;
    Button nxt;
    String first,sec,third;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference ref,ref2,ref3,ref4,ref5,ref6;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_openelective);
        spin1 = findViewById(R.id.spin_1);
        spin2 = findViewById(R.id.spin_2);
        spin3 = findViewById(R.id.spin_3);
        nxt = findViewById(R.id.next3);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        pd = new ProgressDialog(this);

        final String divisions[] =  {
                "--1st Preference--",
                "CSE","ECE","Mechanical","Electrical","Chemical","Civil","Material Science"

        };
        /*final String Electrical[] ={
                "CSE","ECE","Mechanical","Chemical","Civil"

        };
        final String Mechanical[] ={
                "CSE","ECE","Electrical","Chemical","Civil"
        };
        final String CSE[] =  {
                "ECE","Mechanical","Electrical","Chemical","Civil"
        };
        final String Chemical[] =  {
                "CSE" , "ECE","Mechanical","Electrical","Civil"
        };
        final String Civil[] =  {
                "CSE", "ECE","Mechanical","Electrical","Chemical"
        };

        final String select[] = {"---2nd Preference --"};
        final String select1[] = {"---3rd Preference--","ECE","Mechanical","Electrical","Chemical","Civil"};
        final String select3[] = {"---3rd Preference--", "CSE","Mechanical","Electrical","Chemical","Civil"};
        final String select4[] = {"---3rd Preference--", "CSE","ECE","Electrical","Chemical","Civil"};
        final String select5[] = {"---3rd Preference--", "CSE","ECE","Mechanical","Chemical","Civil"};
        final String select6[] = {"---3rd Preference--", "CSE","ECE","Mechanical","Electrical","Civil"};
        final String select7[] = {"---3rd Preference--", "CSE","ECE","Mechanical","Electrical","Chemical"};


        final String ECE[] ={
                "CSE","Mechanical","Electrical","Chemical","Civil"
        };

        final String services[] ={
                "--3rd Preference--",
                "CSE",
                "ECE",
                "Mechanical",
                "Electrical",
                "Chemical",
                "Civil",

        };

*/
        final ArrayAdapter<String> adapter;
        adapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,divisions);

        spin1.setAdapter(adapter);

        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                first = divisions[i];
                if(i==0){
                    spin2.setEnabled(false);
                    spin3.setEnabled(false);
                    nxt.setEnabled(false);
                }
                if(i==1 || i ==2 || i ==3 || i ==4 || i ==5 || i ==6 || i == 7 ){
                    spin2.setEnabled(true);
                    final ArrayList<String> sndpref = new ArrayList<>();
                    sndpref.add("2nd Preference");
                    for (int j = 0 ; j < divisions.length ; j++){
                        if (!Arrays.asList(divisions[j]).contains(first)){
                            sndpref.add(divisions[j]);
                        }
                    }
                    sndpref.remove("--1st Preference--");
                    final ArrayAdapter<String> adapter4;
                    adapter4= new ArrayAdapter<String>(Openelective.this,android.R.layout.simple_spinner_dropdown_item,sndpref);
                    spin2.setAdapter(adapter4);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            sec = sndpref.get(i);
                            if(i==0){
                                spin3.setEnabled(false);
                                nxt.setEnabled(false);
                            }
                            if(i==1 || i ==2 || i ==3 || i ==4 || i ==5 || i ==6){
                                spin3.setEnabled(true);
                                final ArrayList<String> thdpref = new ArrayList<>();
                                thdpref.add("3rd Preference");
                                for (int j = 0 ; j < sndpref.size() ; j++){
                                    if (!Arrays.asList(sndpref.get(j)).contains(sec)){
                                        thdpref.add(sndpref.get(j));
                                    }
                                }
                                thdpref.remove("2nd Preference");
                                ArrayAdapter<String> adapter2;
                                adapter2 = new ArrayAdapter<String>(Openelective.this,android.R.layout.simple_spinner_dropdown_item,thdpref);
                                spin3.setAdapter(adapter2);
                                spin3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        third = thdpref.get(i);
                                        if (i == 0 ){
                                            nxt.setEnabled(false);
                                        }else {
                                            nxt.setEnabled(true);
                                        }
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {

                                    }
                                });

                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.setMessage("Registring....");
                pd.setCancelable(false);
                pd.show();
                ref = firebaseDatabase.getReference(firebaseAuth.getUid()).child("Profile");
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        final String name = dataSnapshot.child("Name").getValue().toString();
                        final String roll = dataSnapshot.child("Roll No").getValue().toString();
                        ref2 = firebaseDatabase.getReference("Result").child(roll);
                        ref2.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                String rank = dataSnapshot.child("Rank").getValue().toString();
                                String cg = dataSnapshot.child("Cgpa").getValue().toString();
                                ref3 = firebaseDatabase.getReference("OpenElective");
                                ref4 = ref3.child(first).child("1st Preference").child(rank);
                                ref4.child("Name").setValue(name);
                                ref4.child("Roll").setValue(roll);
                                ref4.child("Cgpa").setValue(cg);
                                ref5 = ref3.child(sec).child("2nd Preference").child(rank);
                                ref5.child("Name").setValue(name);
                                ref5.child("Roll").setValue(roll);
                                ref5.child("Cgpa").setValue(cg);
                                ref6 = ref3.child(third).child("3rd Preference").child(rank);
                                ref6.child("Name").setValue(name);
                                ref6.child("Roll").setValue(roll);
                                ref6.child("Cgpa").setValue(cg);
                                pd.dismiss();
                                Toast.makeText(Openelective.this,"Done Registration",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Openelective.this, StudentsPage.class));
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Toast.makeText(Openelective.this,databaseError.getCode(),Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(Openelective.this,databaseError.getCode(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
