package com.nopalyer.navigationdrawer;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nopalyer.navigationdrawer.student.StudentsPage;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Openelective extends AppCompatActivity {

    Spinner spin1,spin2,spin3,spin4,spin5,spin6,spin7,spin8,spin9,spin10;
    Button nxt;
    String first,sec,third,forth,five,six,seven,eight,nine,ten;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser firebaseUser;
    DatabaseReference ref,ref2,ref3,ref4,ref5,ref6,ref7,ref8,ref9,ref10,ref11,ref12,ref13;
    ProgressDialog pd;
    Toolbar toolbar;
    Dialog msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_openelective);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        first="";sec="";third="";forth="";five="";six="";seven="";eight="";nine="";ten="";;
        spin1 = findViewById(R.id.spin_1);
        spin2 = findViewById(R.id.spin_2);
        spin3 = findViewById(R.id.spin_3);
        spin4 = findViewById(R.id.spin_4);
        spin5 = findViewById(R.id.spin_5);
        spin6 = findViewById(R.id.spin_6);
        spin7 = findViewById(R.id.spin_7);
        spin8 = findViewById(R.id.spin_8);
        spin9 = findViewById(R.id.spin_9);
        spin10 = findViewById(R.id.spin_10);
        msg = new Dialog(this);

        nxt = findViewById(R.id.next3);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        pd = new ProgressDialog(this);

        final String divisions[] =  {
                "--1st Preference--",
                "CH-306","CH-370","CY-306","CE-306","CE-307","CE-308","CS-306","CS-370","EC-370", "EE-370" ,"EE-371 ","HS-306","HS-370","MB-306","MB-380","MS-370","MS-371","MA-370","MA-371","ME-370","ME-371","PH-370","PH-371"

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
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, divisions);

        spin1.setAdapter(adapter);

        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {

                    spin2.setEnabled(false);
                    spin3.setEnabled(false);
                    spin4.setEnabled(false);
                    spin5.setEnabled(false);
                    spin6.setEnabled(false);
                    spin7.setEnabled(false);
                    spin8.setEnabled(false);
                    spin9.setEnabled(false);
                    spin10.setEnabled(false);

                    nxt.setEnabled(true);
                }
                if (i == 1 || i == 2 || i == 3 || i == 4 || i == 5 || i == 6 || i == 7 || i == 8 || i == 9 || i == 10 || i == 11 || i == 12 || i==13 || i == 14 || i == 15 || i == 16 || i==17 || i == 18 || i == 19 || i == 20 || i==21|| i == 22 || i==23) {
                    first = divisions[i];
                    spin2.setEnabled(true);
                    final ArrayList<String> sndpref = new ArrayList<>();
                    sndpref.add("2nd Preference");
                    for (int j = 0; j < divisions.length; j++) {
                        if (!Arrays.asList(divisions[j]).contains(first)) {
                            sndpref.add(divisions[j]);
                        }
                    }
                    sndpref.remove("--1st Preference--");
                    final ArrayAdapter<String> adapter4;
                    adapter4 = new ArrayAdapter<String>(Openelective.this, android.R.layout.simple_spinner_dropdown_item, sndpref);
                    spin2.setAdapter(adapter4);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if (i == 0) {
                                spin3.setEnabled(false);
                                spin4.setEnabled(false);
                                spin5.setEnabled(false);
                                spin6.setEnabled(false);
                                spin7.setEnabled(false);
                                spin8.setEnabled(false);
                                spin9.setEnabled(false);
                                spin10.setEnabled(false);

                                nxt.setEnabled(true);
                            }
                            if (i == 1 || i == 2 || i == 3 || i == 4 || i == 5 || i == 6 || i == 7 || i == 8 || i == 9 || i == 10 || i == 11 || i == 12 || i==13 || i == 14 || i == 15 || i == 16 || i==17 || i == 18 || i == 19 || i == 20|| i==21|| i == 22 ) {
                                sec = sndpref.get(i);
                                spin3.setEnabled(true);
                                final ArrayList<String> thdpref = new ArrayList<>();
                                thdpref.add("3rd Preference");
                                for (int j = 0; j < sndpref.size(); j++) {
                                    if (!Arrays.asList(sndpref.get(j)).contains(sec)) {
                                        thdpref.add(sndpref.get(j));
                                    }
                                }
                                thdpref.remove("2nd Preference");
                                ArrayAdapter<String> adapter5;
                                adapter5 = new ArrayAdapter<String>(Openelective.this, android.R.layout.simple_spinner_dropdown_item, thdpref);
                                spin3.setAdapter(adapter5);
                                spin3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        if (i == 0) {
                                            spin4.setEnabled(false);
                                            spin5.setEnabled(false);
                                            spin6.setEnabled(false);
                                            spin7.setEnabled(false);
                                            spin8.setEnabled(false);
                                            spin9.setEnabled(false);
                                            spin10.setEnabled(false);

                                            nxt.setEnabled(true);
                                        }

                                        if (i == 1 || i == 2 || i == 3 || i == 4 || i == 5 || i == 6 || i == 7 || i == 8 || i == 9 || i == 10 || i == 11 || i == 12 || i==13 || i == 14 || i == 15 || i == 16 || i==17 || i == 18 || i == 19|| i == 20|| i==21) {
                                            third = thdpref.get(i);
                                            spin4.setEnabled(true);
                                            final ArrayList<String> thdpref4 = new ArrayList<>();
                                            thdpref4.add("4th Preference");
                                            for (int j = 0; j < thdpref.size(); j++) {
                                                if (!Arrays.asList(thdpref.get(j)).contains(third)) {
                                                    thdpref4.add(thdpref.get(j));
                                                }
                                            }
                                            thdpref4.remove("3rd Preference");
                                            ArrayAdapter<String> adapter6;
                                            adapter6 = new ArrayAdapter<String>(Openelective.this, android.R.layout.simple_spinner_dropdown_item, thdpref4);
                                            spin4.setAdapter(adapter6);
                                            spin4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                @Override
                                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                    if (i == 0) {

                                                        spin5.setEnabled(false);
                                                        spin6.setEnabled(false);
                                                        spin7.setEnabled(false);
                                                        spin8.setEnabled(false);
                                                        spin9.setEnabled(false);
                                                        spin10.setEnabled(false);

                                                        nxt.setEnabled(true);
                                                    }

                                                    if (i == 1 || i == 2 || i == 3 || i == 4 || i == 5 || i == 6 || i == 7 || i == 8 || i == 9|| i == 10 || i == 11 || i == 12 || i==13 || i == 14 || i == 15 || i == 16 || i==17 || i == 18 || i == 19|| i == 20) {
                                                        forth = thdpref4.get(i);
                                                        spin5.setEnabled(true);
                                                        final ArrayList<String> thdpref5 = new ArrayList<>();
                                                        thdpref5.add("5th Preference");
                                                        for (int j = 0; j < thdpref4.size(); j++) {
                                                            if (!Arrays.asList(thdpref4.get(j)).contains(forth)) {
                                                                thdpref5.add(thdpref4.get(j));
                                                            }
                                                        }
                                                        thdpref5.remove("4th Preference");
                                                        ArrayAdapter<String> adapter7;
                                                        adapter7 = new ArrayAdapter<String>(Openelective.this, android.R.layout.simple_spinner_dropdown_item, thdpref5);
                                                        spin5.setAdapter(adapter7);
                                                        spin5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                            @Override
                                                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                                if (i == 0) {


                                                                    spin6.setEnabled(false);
                                                                    spin7.setEnabled(false);
                                                                    spin8.setEnabled(false);
                                                                    spin9.setEnabled(false);
                                                                    spin10.setEnabled(false);

                                                                    nxt.setEnabled(true);
                                                                }
                                                                if (i == 1 || i == 2 || i == 3 || i == 4 || i == 5 || i == 6 || i == 7 || i == 8|| i == 9|| i == 10 || i == 11 || i == 12 || i==13 || i == 14 || i == 15 || i == 16 || i==17|| i == 18 || i == 19) {
                                                                    five = thdpref5.get(i);
                                                                    spin6.setEnabled(true);
                                                                    final ArrayList<String> thdpref6 = new ArrayList<>();
                                                                    thdpref6.add("6th Preference");
                                                                    for (int j = 0; j < thdpref5.size(); j++) {
                                                                        if (!Arrays.asList(thdpref5.get(j)).contains(five)) {
                                                                            thdpref6.add(thdpref5.get(j));
                                                                        }
                                                                    }
                                                                    thdpref6.remove("5th Preference");
                                                                    ArrayAdapter<String> adapter8;
                                                                    adapter8 = new ArrayAdapter<String>(Openelective.this, android.R.layout.simple_spinner_dropdown_item, thdpref6);
                                                                    spin6.setAdapter(adapter8);
                                                                    spin6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                        @Override
                                                                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                                            if (i == 0) {

                                                                                spin7.setEnabled(false);
                                                                                spin8.setEnabled(false);
                                                                                spin9.setEnabled(false);
                                                                                spin10.setEnabled(false);

                                                                                nxt.setEnabled(true);
                                                                            }
                                                                            if (i == 1 || i == 2 || i == 3 || i == 4 || i == 5 || i == 6 || i == 7|| i == 8|| i == 9|| i == 10 || i == 11 || i == 12 || i==13 || i == 14 || i == 15 || i == 16|| i==17|| i == 18 ) {
                                                                                six = thdpref6.get(i);
                                                                                spin7.setEnabled(true);
                                                                                final ArrayList<String> thdpref7 = new ArrayList<>();
                                                                                thdpref7.add("7th Preference");
                                                                                for (int j = 0; j < thdpref6.size(); j++) {
                                                                                    if (!Arrays.asList(thdpref6.get(j)).contains(six)) {
                                                                                        thdpref7.add(thdpref6.get(j));
                                                                                    }
                                                                                }
                                                                                thdpref7.remove("6th Preference");
                                                                                ArrayAdapter<String> adapter9;
                                                                                adapter9 = new ArrayAdapter<String>(Openelective.this, android.R.layout.simple_spinner_dropdown_item, thdpref7);
                                                                                spin7.setAdapter(adapter9);
                                                                                spin7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                                    @Override
                                                                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                                                        if (i == 0) {


                                                                                            spin8.setEnabled(false);
                                                                                            spin9.setEnabled(false);
                                                                                            spin10.setEnabled(false);

                                                                                            nxt.setEnabled(true);
                                                                                        }
                                                                                        if (i == 1 || i == 2 || i == 3 || i == 4 || i == 5 || i == 6|| i == 7|| i == 8|| i == 9|| i == 10 || i == 11 || i == 12 || i==13 || i == 14 || i == 15|| i == 16|| i==17) {
                                                                                            seven = thdpref7.get(i);
                                                                                            spin8.setEnabled(true);
                                                                                            final ArrayList<String> thdpref8 = new ArrayList<>();
                                                                                            thdpref8.add("8th Preference");
                                                                                            for (int j = 0; j < thdpref7.size(); j++) {
                                                                                                if (!Arrays.asList(thdpref7.get(j)).contains(seven)) {
                                                                                                    thdpref8.add(thdpref7.get(j));
                                                                                                }
                                                                                            }
                                                                                            thdpref8.remove("7th Preference");
                                                                                            ArrayAdapter<String> adapter10;
                                                                                            adapter10 = new ArrayAdapter<String>(Openelective.this, android.R.layout.simple_spinner_dropdown_item, thdpref8);
                                                                                            spin8.setAdapter(adapter10);
                                                                                            spin8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                                                @Override
                                                                                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                                                                    if (i == 0) {


                                                                                                        spin9.setEnabled(false);
                                                                                                        spin10.setEnabled(false);

                                                                                                        nxt.setEnabled(true);
                                                                                                    }
                                                                                                    if (i == 1 || i == 2 || i == 3 || i == 4 || i == 5 ||i == 6|| i == 7|| i == 8|| i == 9|| i == 10 || i == 11 || i == 12 || i==13 || i == 14 || i == 15|| i == 16) {
                                                                                                        eight = thdpref8.get(i);
                                                                                                        spin9.setEnabled(true);
                                                                                                        final ArrayList<String> thdpref9 = new ArrayList<>();
                                                                                                        thdpref9.add("9th Preference");
                                                                                                        for (int j = 0; j < thdpref8.size(); j++) {
                                                                                                            if (!Arrays.asList(thdpref8.get(j)).contains(eight)) {
                                                                                                                thdpref9.add(thdpref8.get(j));
                                                                                                            }
                                                                                                        }
                                                                                                        thdpref9.remove("8th Preference");
                                                                                                        ArrayAdapter<String> adapter11;
                                                                                                        adapter11 = new ArrayAdapter<String>(Openelective.this, android.R.layout.simple_spinner_dropdown_item, thdpref9);
                                                                                                        spin9.setAdapter(adapter11);
                                                                                                        spin9.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                                                            @Override
                                                                                                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                                                                                if (i == 0) {
                                                                                                                    spin10.setEnabled(false);

                                                                                                                    nxt.setEnabled(true);
                                                                                                                }
                                                                                                                if (i == 1 || i == 2 || i == 3 || i == 4|| i == 5 ||i == 6|| i == 7|| i == 8|| i == 9|| i == 10 || i == 11 || i == 12 || i==13|| i == 14 || i == 15) {
                                                                                                                    nine = thdpref9.get(i);
                                                                                                                    spin10.setEnabled(true);
                                                                                                                    final ArrayList<String> thdpref10 = new ArrayList<>();
                                                                                                                    thdpref10.add("10th Preference");
                                                                                                                    for (int j = 0; j < thdpref9.size(); j++) {
                                                                                                                        if (!Arrays.asList(thdpref9.get(j)).contains(nine)) {
                                                                                                                            thdpref10.add(thdpref9.get(j));
                                                                                                                        }
                                                                                                                    }
                                                                                                                    thdpref10.remove("9th Preference");
                                                                                                                    ArrayAdapter<String> adapter12;
                                                                                                                    adapter12 = new ArrayAdapter<String>(Openelective.this, android.R.layout.simple_spinner_dropdown_item, thdpref10);
                                                                                                                    spin10.setAdapter(adapter12);
                                                                                                                    spin10.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                                                                        @Override
                                                                                                                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                                                                                            if (i == 0) {

                                                                                                                                nxt.setEnabled(true);
                                                                                                                            } else {
                                                                                                                                ten = thdpref10.get(i);
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
                                                                                                                            }}

                                                                                                                        @Override
                                                                                                                        public void onNothingSelected(AdapterView<?> adapterView) {

                                                                                                                        }
                                                                                                                    });
                                                                                                                }}


                                                                                                            @Override
                                                                                                            public void onNothingSelected(AdapterView<?> adapterView) {

                                                                                                            }
                                                                                                        });
                                                                                                    }}


                                                                                                @Override
                                                                                                public void onNothingSelected(AdapterView<?> adapterView) {

                                                                                                } });
                                                                                        }}


                                                                                    @Override
                                                                                    public void onNothingSelected(AdapterView<?> adapterView) {

                                                                                    } });
                                                                            }}

                                                                        @Override
                                                                        public void onNothingSelected(AdapterView<?> adapterView) {

                                                                        } });
                                                                }}

                                                            @Override
                                                            public void onNothingSelected(AdapterView<?> adapterView) {

                                                            } });
                                                    }}


                                                @Override
                                                public void onNothingSelected(AdapterView<?> adapterView) {

                                                } });
                                        }}




            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            } });


        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (first.isEmpty()){
                    Toast.makeText(Openelective.this,"Fill atleast 1st preference",Toast.LENGTH_SHORT).show();
                }else {
                    pd.setMessage("Registring....");
                    pd.setCancelable(false);
                    pd.show();
                    ref = firebaseDatabase.getReference(firebaseAuth.getUid()).child("Profile");
                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final String name = dataSnapshot.child("Name").getValue().toString();
                            final String roll = dataSnapshot.child("Roll No").getValue().toString();
                            final String email = firebaseUser.getEmail();
                            final String phone = dataSnapshot.child("Contact").getValue().toString();
                            ref2 = firebaseDatabase.getReference("Result").child(roll);
                            ref2.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    String rank = dataSnapshot.child("Rank").getValue().toString();
                                    String cg = dataSnapshot.child("Cgpa").getValue().toString();
                                    ref3 = firebaseDatabase.getReference("OpenElective");
                                    if (sec.isEmpty()){
                                        ref4 = ref3.child(first).child("1st Preference").child(rank);
                                        ref4.child("Name").setValue(name);
                                        ref4.child("Roll").setValue(roll);
                                        ref4.child("Cgpa").setValue(cg);
                                        ref4.child("Email").setValue(email);
                                        ref4.child("Phone").setValue(phone);
                                    }else if (third.isEmpty()){
                                        ref4 = ref3.child(first).child("1st Preference").child(rank);
                                        ref4.child("Name").setValue(name);
                                        ref4.child("Roll").setValue(roll);
                                        ref4.child("Cgpa").setValue(cg);
                                        ref4.child("Email").setValue(email);
                                        ref4.child("Phone").setValue(phone);
                                        ref5 = ref3.child(sec).child("2nd Preference").child(rank);
                                        ref5.child("Name").setValue(name);
                                        ref5.child("Roll").setValue(roll);
                                        ref5.child("Cgpa").setValue(cg);
                                        ref5.child("Email").setValue(email);
                                        ref5.child("Phone").setValue(phone);
                                    }else if (forth.isEmpty()){
                                        ref4 = ref3.child(first).child("1st Preference").child(rank);
                                        ref4.child("Name").setValue(name);
                                        ref4.child("Roll").setValue(roll);
                                        ref4.child("Cgpa").setValue(cg);
                                        ref4.child("Email").setValue(email);
                                        ref4.child("Phone").setValue(phone);
                                        ref5 = ref3.child(sec).child("2nd Preference").child(rank);
                                        ref5.child("Name").setValue(name);
                                        ref5.child("Roll").setValue(roll);
                                        ref5.child("Cgpa").setValue(cg);
                                        ref5.child("Email").setValue(email);
                                        ref5.child("Phone").setValue(phone);
                                        ref6 = ref3.child(third).child("3rd Preference").child(rank);
                                        ref6.child("Name").setValue(name);
                                        ref6.child("Roll").setValue(roll);
                                        ref6.child("Cgpa").setValue(cg);
                                        ref6.child("Email").setValue(email);
                                        ref6.child("Phone").setValue(phone);
                                    }else if (five.isEmpty()){
                                        ref4 = ref3.child(first).child("1st Preference").child(rank);
                                        ref4.child("Name").setValue(name);
                                        ref4.child("Roll").setValue(roll);
                                        ref4.child("Cgpa").setValue(cg);
                                        ref4.child("Email").setValue(email);
                                        ref4.child("Phone").setValue(phone);
                                        ref5 = ref3.child(sec).child("2nd Preference").child(rank);
                                        ref5.child("Name").setValue(name);
                                        ref5.child("Roll").setValue(roll);
                                        ref5.child("Cgpa").setValue(cg);
                                        ref5.child("Email").setValue(email);
                                        ref5.child("Phone").setValue(phone);
                                        ref6 = ref3.child(third).child("3rd Preference").child(rank);
                                        ref6.child("Name").setValue(name);
                                        ref6.child("Roll").setValue(roll);
                                        ref6.child("Cgpa").setValue(cg);
                                        ref6.child("Email").setValue(email);
                                        ref6.child("Phone").setValue(phone);
                                        ref7 = ref3.child(forth).child("4th Preference").child(rank);
                                        ref7.child("Name").setValue(name);
                                        ref7.child("Roll").setValue(roll);
                                        ref7.child("Cgpa").setValue(cg);
                                        ref7.child("Email").setValue(email);
                                        ref7.child("Phone").setValue(phone);
                                    }else if (six.isEmpty()){
                                        ref4 = ref3.child(first).child("1st Preference").child(rank);
                                        ref4.child("Name").setValue(name);
                                        ref4.child("Roll").setValue(roll);
                                        ref4.child("Cgpa").setValue(cg);
                                        ref4.child("Email").setValue(email);
                                        ref4.child("Phone").setValue(phone);
                                        ref5 = ref3.child(sec).child("2nd Preference").child(rank);
                                        ref5.child("Name").setValue(name);
                                        ref5.child("Roll").setValue(roll);
                                        ref5.child("Cgpa").setValue(cg);
                                        ref5.child("Email").setValue(email);
                                        ref5.child("Phone").setValue(phone);
                                        ref6 = ref3.child(third).child("3rd Preference").child(rank);
                                        ref6.child("Name").setValue(name);
                                        ref6.child("Roll").setValue(roll);
                                        ref6.child("Cgpa").setValue(cg);
                                        ref6.child("Email").setValue(email);
                                        ref6.child("Phone").setValue(phone);
                                        ref7 = ref3.child(forth).child("4th Preference").child(rank);
                                        ref7.child("Name").setValue(name);
                                        ref7.child("Roll").setValue(roll);
                                        ref7.child("Cgpa").setValue(cg);
                                        ref7.child("Email").setValue(email);
                                        ref7.child("Phone").setValue(phone);
                                        ref8 = ref3.child(five).child("5th Preference").child(rank);
                                        ref8.child("Name").setValue(name);
                                        ref8.child("Roll").setValue(roll);
                                        ref8.child("Cgpa").setValue(cg);
                                        ref8.child("Email").setValue(email);
                                        ref8.child("Phone").setValue(phone);
                                    }else if (seven.isEmpty()){
                                        ref4 = ref3.child(first).child("1st Preference").child(rank);
                                        ref4.child("Name").setValue(name);
                                        ref4.child("Roll").setValue(roll);
                                        ref4.child("Cgpa").setValue(cg);
                                        ref4.child("Email").setValue(email);
                                        ref4.child("Phone").setValue(phone);
                                        ref5 = ref3.child(sec).child("2nd Preference").child(rank);
                                        ref5.child("Name").setValue(name);
                                        ref5.child("Roll").setValue(roll);
                                        ref5.child("Cgpa").setValue(cg);
                                        ref5.child("Email").setValue(email);
                                        ref5.child("Phone").setValue(phone);
                                        ref6 = ref3.child(third).child("3rd Preference").child(rank);
                                        ref6.child("Name").setValue(name);
                                        ref6.child("Roll").setValue(roll);
                                        ref6.child("Cgpa").setValue(cg);
                                        ref6.child("Email").setValue(email);
                                        ref6.child("Phone").setValue(phone);
                                        ref7 = ref3.child(forth).child("4th Preference").child(rank);
                                        ref7.child("Name").setValue(name);
                                        ref7.child("Roll").setValue(roll);
                                        ref7.child("Cgpa").setValue(cg);
                                        ref7.child("Email").setValue(email);
                                        ref7.child("Phone").setValue(phone);
                                        ref8 = ref3.child(five).child("5th Preference").child(rank);
                                        ref8.child("Name").setValue(name);
                                        ref8.child("Roll").setValue(roll);
                                        ref8.child("Cgpa").setValue(cg);
                                        ref8.child("Email").setValue(email);
                                        ref8.child("Phone").setValue(phone);
                                        ref9 = ref3.child(six).child("6th Preference").child(rank);
                                        ref9.child("Name").setValue(name);
                                        ref9.child("Roll").setValue(roll);
                                        ref9.child("Cgpa").setValue(cg);
                                        ref9.child("Email").setValue(email);
                                        ref9.child("Phone").setValue(phone);
                                    }else if (eight.isEmpty()){
                                        ref4 = ref3.child(first).child("1st Preference").child(rank);
                                        ref4.child("Name").setValue(name);
                                        ref4.child("Roll").setValue(roll);
                                        ref4.child("Cgpa").setValue(cg);
                                        ref4.child("Email").setValue(email);
                                        ref4.child("Phone").setValue(phone);
                                        ref5 = ref3.child(sec).child("2nd Preference").child(rank);
                                        ref5.child("Name").setValue(name);
                                        ref5.child("Roll").setValue(roll);
                                        ref5.child("Cgpa").setValue(cg);
                                        ref5.child("Email").setValue(email);
                                        ref5.child("Phone").setValue(phone);
                                        ref6 = ref3.child(third).child("3rd Preference").child(rank);
                                        ref6.child("Name").setValue(name);
                                        ref6.child("Roll").setValue(roll);
                                        ref6.child("Cgpa").setValue(cg);
                                        ref6.child("Email").setValue(email);
                                        ref6.child("Phone").setValue(phone);
                                        ref7 = ref3.child(forth).child("4th Preference").child(rank);
                                        ref7.child("Name").setValue(name);
                                        ref7.child("Roll").setValue(roll);
                                        ref7.child("Cgpa").setValue(cg);
                                        ref7.child("Email").setValue(email);
                                        ref7.child("Phone").setValue(phone);
                                        ref8 = ref3.child(five).child("5th Preference").child(rank);
                                        ref8.child("Name").setValue(name);
                                        ref8.child("Roll").setValue(roll);
                                        ref8.child("Cgpa").setValue(cg);
                                        ref8.child("Email").setValue(email);
                                        ref8.child("Phone").setValue(phone);
                                        ref9 = ref3.child(six).child("6th Preference").child(rank);
                                        ref9.child("Name").setValue(name);
                                        ref9.child("Roll").setValue(roll);
                                        ref9.child("Cgpa").setValue(cg);
                                        ref9.child("Email").setValue(email);
                                        ref9.child("Phone").setValue(phone);
                                        ref10 = ref3.child(seven).child("7th Preference").child(rank);
                                        ref10.child("Name").setValue(name);
                                        ref10.child("Roll").setValue(roll);
                                        ref10.child("Cgpa").setValue(cg);
                                        ref10.child("Email").setValue(email);
                                        ref10.child("Phone").setValue(phone);
                                    }else if (nine.isEmpty()){
                                        ref4 = ref3.child(first).child("1st Preference").child(rank);
                                        ref4.child("Name").setValue(name);
                                        ref4.child("Roll").setValue(roll);
                                        ref4.child("Cgpa").setValue(cg);
                                        ref4.child("Email").setValue(email);
                                        ref4.child("Phone").setValue(phone);
                                        ref5 = ref3.child(sec).child("2nd Preference").child(rank);
                                        ref5.child("Name").setValue(name);
                                        ref5.child("Roll").setValue(roll);
                                        ref5.child("Cgpa").setValue(cg);
                                        ref5.child("Email").setValue(email);
                                        ref5.child("Phone").setValue(phone);
                                        ref6 = ref3.child(third).child("3rd Preference").child(rank);
                                        ref6.child("Name").setValue(name);
                                        ref6.child("Roll").setValue(roll);
                                        ref6.child("Cgpa").setValue(cg);
                                        ref6.child("Email").setValue(email);
                                        ref6.child("Phone").setValue(phone);
                                        ref7 = ref3.child(forth).child("4th Preference").child(rank);
                                        ref7.child("Name").setValue(name);
                                        ref7.child("Roll").setValue(roll);
                                        ref7.child("Cgpa").setValue(cg);
                                        ref7.child("Email").setValue(email);
                                        ref7.child("Phone").setValue(phone);
                                        ref8 = ref3.child(five).child("5th Preference").child(rank);
                                        ref8.child("Name").setValue(name);
                                        ref8.child("Roll").setValue(roll);
                                        ref8.child("Cgpa").setValue(cg);
                                        ref8.child("Email").setValue(email);
                                        ref8.child("Phone").setValue(phone);
                                        ref9 = ref3.child(six).child("6th Preference").child(rank);
                                        ref9.child("Name").setValue(name);
                                        ref9.child("Roll").setValue(roll);
                                        ref9.child("Cgpa").setValue(cg);
                                        ref9.child("Email").setValue(email);
                                        ref9.child("Phone").setValue(phone);
                                        ref10 = ref3.child(seven).child("7th Preference").child(rank);
                                        ref10.child("Name").setValue(name);
                                        ref10.child("Roll").setValue(roll);
                                        ref10.child("Cgpa").setValue(cg);
                                        ref10.child("Email").setValue(email);
                                        ref10.child("Phone").setValue(phone);
                                        ref11 = ref3.child(eight).child("8th Preference").child(rank);
                                        ref11.child("Name").setValue(name);
                                        ref11.child("Roll").setValue(roll);
                                        ref11.child("Cgpa").setValue(cg);
                                        ref11.child("Email").setValue(email);
                                        ref11.child("Phone").setValue(phone);
                                    }else if (ten.isEmpty()){
                                        ref4 = ref3.child(first).child("1st Preference").child(rank);
                                        ref4.child("Name").setValue(name);
                                        ref4.child("Roll").setValue(roll);
                                        ref4.child("Cgpa").setValue(cg);
                                        ref4.child("Email").setValue(email);
                                        ref4.child("Phone").setValue(phone);
                                        ref5 = ref3.child(sec).child("2nd Preference").child(rank);
                                        ref5.child("Name").setValue(name);
                                        ref5.child("Roll").setValue(roll);
                                        ref5.child("Cgpa").setValue(cg);
                                        ref5.child("Email").setValue(email);
                                        ref5.child("Phone").setValue(phone);
                                        ref6 = ref3.child(third).child("3rd Preference").child(rank);
                                        ref6.child("Name").setValue(name);
                                        ref6.child("Roll").setValue(roll);
                                        ref6.child("Cgpa").setValue(cg);
                                        ref6.child("Email").setValue(email);
                                        ref6.child("Phone").setValue(phone);
                                        ref7 = ref3.child(forth).child("4th Preference").child(rank);
                                        ref7.child("Name").setValue(name);
                                        ref7.child("Roll").setValue(roll);
                                        ref7.child("Cgpa").setValue(cg);
                                        ref7.child("Email").setValue(email);
                                        ref7.child("Phone").setValue(phone);
                                        ref8 = ref3.child(five).child("5th Preference").child(rank);
                                        ref8.child("Name").setValue(name);
                                        ref8.child("Roll").setValue(roll);
                                        ref8.child("Cgpa").setValue(cg);
                                        ref8.child("Email").setValue(email);
                                        ref8.child("Phone").setValue(phone);
                                        ref9 = ref3.child(six).child("6th Preference").child(rank);
                                        ref9.child("Name").setValue(name);
                                        ref9.child("Roll").setValue(roll);
                                        ref9.child("Cgpa").setValue(cg);
                                        ref9.child("Email").setValue(email);
                                        ref9.child("Phone").setValue(phone);
                                        ref10 = ref3.child(seven).child("7th Preference").child(rank);
                                        ref10.child("Name").setValue(name);
                                        ref10.child("Roll").setValue(roll);
                                        ref10.child("Cgpa").setValue(cg);
                                        ref10.child("Email").setValue(email);
                                        ref10.child("Phone").setValue(phone);
                                        ref11 = ref3.child(eight).child("8th Preference").child(rank);
                                        ref11.child("Name").setValue(name);
                                        ref11.child("Roll").setValue(roll);
                                        ref11.child("Cgpa").setValue(cg);
                                        ref11.child("Email").setValue(email);
                                        ref11.child("Phone").setValue(phone);
                                        ref12 = ref3.child(nine).child("9th Preference").child(rank);
                                        ref12.child("Name").setValue(name);
                                        ref12.child("Roll").setValue(roll);
                                        ref12.child("Cgpa").setValue(cg);
                                        ref12.child("Email").setValue(email);
                                        ref12.child("Phone").setValue(phone);
                                    }else {
                                        ref4 = ref3.child(first).child("1st Preference").child(rank);
                                        ref4.child("Name").setValue(name);
                                        ref4.child("Roll").setValue(roll);
                                        ref4.child("Cgpa").setValue(cg);
                                        ref4.child("Email").setValue(email);
                                        ref4.child("Phone").setValue(phone);
                                        ref5 = ref3.child(sec).child("2nd Preference").child(rank);
                                        ref5.child("Name").setValue(name);
                                        ref5.child("Roll").setValue(roll);
                                        ref5.child("Cgpa").setValue(cg);
                                        ref5.child("Email").setValue(email);
                                        ref5.child("Phone").setValue(phone);
                                        ref6 = ref3.child(third).child("3rd Preference").child(rank);
                                        ref6.child("Name").setValue(name);
                                        ref6.child("Roll").setValue(roll);
                                        ref6.child("Cgpa").setValue(cg);
                                        ref6.child("Email").setValue(email);
                                        ref6.child("Phone").setValue(phone);
                                        ref7 = ref3.child(forth).child("4th Preference").child(rank);
                                        ref7.child("Name").setValue(name);
                                        ref7.child("Roll").setValue(roll);
                                        ref7.child("Cgpa").setValue(cg);
                                        ref7.child("Email").setValue(email);
                                        ref7.child("Phone").setValue(phone);
                                        ref8 = ref3.child(five).child("5th Preference").child(rank);
                                        ref8.child("Name").setValue(name);
                                        ref8.child("Roll").setValue(roll);
                                        ref8.child("Cgpa").setValue(cg);
                                        ref8.child("Email").setValue(email);
                                        ref8.child("Phone").setValue(phone);
                                        ref9 = ref3.child(six).child("6th Preference").child(rank);
                                        ref9.child("Name").setValue(name);
                                        ref9.child("Roll").setValue(roll);
                                        ref9.child("Cgpa").setValue(cg);
                                        ref9.child("Email").setValue(email);
                                        ref9.child("Phone").setValue(phone);
                                        ref10 = ref3.child(seven).child("7th Preference").child(rank);
                                        ref10.child("Name").setValue(name);
                                        ref10.child("Roll").setValue(roll);
                                        ref10.child("Cgpa").setValue(cg);
                                        ref10.child("Email").setValue(email);
                                        ref10.child("Phone").setValue(phone);
                                        ref11 = ref3.child(eight).child("8th Preference").child(rank);
                                        ref11.child("Name").setValue(name);
                                        ref11.child("Roll").setValue(roll);
                                        ref11.child("Cgpa").setValue(cg);
                                        ref11.child("Email").setValue(email);
                                        ref11.child("Phone").setValue(phone);
                                        ref12 = ref3.child(nine).child("9th Preference").child(rank);
                                        ref12.child("Name").setValue(name);
                                        ref12.child("Roll").setValue(roll);
                                        ref12.child("Cgpa").setValue(cg);
                                        ref12.child("Email").setValue(email);
                                        ref12.child("Phone").setValue(phone);
                                        ref13 = ref3.child(ten).child("10th Preference").child(rank);
                                        ref13.child("Name").setValue(name);
                                        ref13.child("Roll").setValue(roll);
                                        ref13.child("Cgpa").setValue(cg);
                                        ref13.child("Email").setValue(email);
                                        ref13.child("Phone").setValue(phone);
                                    }
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String mera2 = " ";
        switch (item.getItemId()) {
            case (R.id.adhik):
                //.....
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
