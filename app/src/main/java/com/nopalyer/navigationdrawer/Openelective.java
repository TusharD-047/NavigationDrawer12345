package com.nopalyer.navigationdrawer;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.protobuf.StringValue;
import com.nopalyer.navigationdrawer.student.StudentsPage;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Openelective extends AppCompatActivity {

    Spinner spin1,spin2,spin3,spin4,spin5,spin6,spin7,spin8,spin9,spin10,spin11,spin12,spin13,spin14,spin15,spin16,spin17,spin18,spin19,spin20,spin21,spin22,spin23;
    ImageView im16,im17,im18,im19,im20,im21,im22,im23;
    Button nxt;
    LinearLayout l15,l16,l17,l18,l19,l20,l21,l22,l23,l14,l13;
    String first,sec,third,forth,five,six,seven,eight,nine,ten,eleven,twelve,thirteen,fourteen,fifteen,sixteen,seventeen,eighteen,nineteen,twenty,twentyone,twentytwo,twentythree;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser firebaseUser;
    DatabaseReference ref,ref2,ref3,ref4,ref5,ref6,ref7,ref8,ref9,ref10,ref11,ref12,ref13,databaseReference,databaseReference2;
    ProgressDialog pd,pd2;
    Toolbar toolbar;
    String dep = "";
    List<String> listDataHeader,listDataHeader1;
    int sublength,deplength;
    int abc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_openelective);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AlertDialog.Builder builder = new AlertDialog.Builder(Openelective.this);
        builder.setCancelable(true);
        builder.setTitle("MESSAGE");
        builder.setMessage("Please click on the i icon for more info about Open Elective Registration and do check the subjects code once before filling your choice.");
        builder.setPositiveButton("CONTINUE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();

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
        spin11 = findViewById(R.id.spin_11);
        spin12 = findViewById(R.id.spin_12);
        spin13 = findViewById(R.id.spin_13);
        spin14 = findViewById(R.id.spin_14);
        spin15 = findViewById(R.id.spin_15);
        spin16 = findViewById(R.id.spin_16);
        spin17 = findViewById(R.id.spin_17);
        spin18 = findViewById(R.id.spin_18);
        spin19 = findViewById(R.id.spin_19);
        spin20 = findViewById(R.id.spin_20);
        spin21 = findViewById(R.id.spin_21);
        spin22 = findViewById(R.id.spin_22);
        spin23 = findViewById(R.id.spin_23);

        im16 = findViewById(R.id.image_16);
        im17 = findViewById(R.id.image_17);
        im18 = findViewById(R.id.image_18);
        im19 = findViewById(R.id.image_19);
        im20 = findViewById(R.id.image_20);
        im21 = findViewById(R.id.image_21);
        im22 = findViewById(R.id.image_22);
        im23 = findViewById(R.id.image_23);

        l13 = findViewById(R.id.l13);
        l14 = findViewById(R.id.l14);
        l15 = findViewById(R.id.l15);
        l16 = findViewById(R.id.l16);
        l17 = findViewById(R.id.l17);
        l18 = findViewById(R.id.l18);
        l19 = findViewById(R.id.l19);
        l20 = findViewById(R.id.l20);
        l21 = findViewById(R.id.l21);
        l22 = findViewById(R.id.l22);
        l23 = findViewById(R.id.l23);

        nxt = findViewById(R.id.next3);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        pd = new ProgressDialog(this);

        listDataHeader1 = new ArrayList<>();
        listDataHeader = new ArrayList<>();

        DatabaseReference databaseReference1 = firebaseDatabase.getReference(firebaseAuth.getUid()).child("Profile");
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               dep = dataSnapshot.child("Department").getValue().toString();
                databaseReference = FirebaseDatabase.getInstance().getReference("Subjects");
                databaseReference.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        final String headertitile = dataSnapshot.getKey();
                        listDataHeader.add(headertitile);

//                        final String[] divisions = new String[listDataHeader.size()];
//
//                        //divisions[0] = "--1st Preference--";
//                        for(int i=0; i<divisions.length;i++){
//                            divisions[i] = listDataHeader.get(i);
//                        }

//                        sublength = divisions.length;
                        databaseReference2 = FirebaseDatabase.getInstance().getReference("Department Wise").child(dep);
                        databaseReference2.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                final String depsub = dataSnapshot.getKey();
                                listDataHeader1.add(depsub);
//                                final String[] divsub = new String[listDataHeader1.size()];
//                                for(int i=0;i<divsub.length;i++){
//                                    divsub[i] = listDataHeader1.get(i);
//                                }
//                                deplength = divsub.length;
//                                final ArrayList<String> divisions1 = new ArrayList<>();
//                                for(int i=0;i<divisions.length;i++){
//                                    for(int j=0;j<divsub.length;j++) {
//
//                                        if (!Arrays.asList(divisions[i]).contains(divsub[j])) {
//                                            divisions1.add(divisions[i]);
//                                        }
//                                    }
//                                }
                                final ArrayList<String> divisions1 = new ArrayList<>();
                                divisions1.addAll(listDataHeader);
                                divisions1.removeAll(listDataHeader1);
                                abc = divisions1.size();
                                Toast.makeText(Openelective.this, String.valueOf(abc),Toast.LENGTH_SHORT).show();
                                if(abc==13){
                                    l13.setVisibility(View.VISIBLE);
                                    l14.setVisibility(View.GONE);
                                    l15.setVisibility(View.GONE);
                                    l16.setVisibility(View.GONE);
                                    l17.setVisibility(View.GONE);
                                    l18.setVisibility(View.GONE);
                                    l19.setVisibility(View.GONE);
                                    l20.setVisibility(View.GONE);
                                    l21.setVisibility(View.GONE);
                                    l22.setVisibility(View.GONE);
                                    l23.setVisibility(View.GONE);

                                }
                                if(abc==14){
                                    l14.setVisibility(View.VISIBLE);
                                    l15.setVisibility(View.GONE);
                                    l16.setVisibility(View.GONE);
                                    l17.setVisibility(View.GONE);
                                    l18.setVisibility(View.GONE);
                                    l19.setVisibility(View.GONE);
                                    l20.setVisibility(View.GONE);
                                    l21.setVisibility(View.GONE);
                                    l22.setVisibility(View.GONE);
                                    l23.setVisibility(View.GONE);

                                }
                                if(abc==15){
                                    l15.setVisibility(View.VISIBLE);
                                    l16.setVisibility(View.GONE);
                                    l17.setVisibility(View.GONE);
                                    l18.setVisibility(View.GONE);
                                    l19.setVisibility(View.GONE);
                                    l20.setVisibility(View.GONE);
                                    l21.setVisibility(View.GONE);
                                    l22.setVisibility(View.GONE);
                                    l23.setVisibility(View.GONE);

                                }
                                if(abc==16){
                                    l16.setVisibility(View.VISIBLE);
                                    l17.setVisibility(View.GONE);
                                    l18.setVisibility(View.GONE);
                                    l19.setVisibility(View.GONE);
                                    l20.setVisibility(View.GONE);
                                    l21.setVisibility(View.GONE);
                                    l22.setVisibility(View.GONE);
                                    l23.setVisibility(View.GONE);
                                }
                                if(abc==17){
                                    l17.setVisibility(View.VISIBLE);
                                    l18.setVisibility(View.GONE);
                                    l19.setVisibility(View.GONE);
                                    l20.setVisibility(View.GONE);
                                    l21.setVisibility(View.GONE);
                                    l22.setVisibility(View.GONE);
                                    l23.setVisibility(View.GONE);
                                }
                                if(abc==18){
                                    l18.setVisibility(View.VISIBLE);
                                    l19.setVisibility(View.GONE);
                                    l20.setVisibility(View.GONE);
                                    l21.setVisibility(View.GONE);
                                    l22.setVisibility(View.GONE);
                                    l23.setVisibility(View.GONE);
                                }
                                if(abc==19){
                                    l19.setVisibility(View.VISIBLE);
                                    l20.setVisibility(View.GONE);
                                    l21.setVisibility(View.GONE);
                                    l22.setVisibility(View.GONE);
                                    l23.setVisibility(View.GONE);
                                }
                                if(abc==20){
                                    l20.setVisibility(View.VISIBLE);
                                    l21.setVisibility(View.GONE);
                                    l22.setVisibility(View.GONE);
                                    l23.setVisibility(View.GONE);
                                }
                                if(abc==21){
                                    l21.setVisibility(View.VISIBLE);
                                    l22.setVisibility(View.GONE);
                                    l23.setVisibility(View.GONE);
                                }
                                if(abc==22){
                                    l22.setVisibility(View.VISIBLE);
                                    l23.setVisibility(View.GONE);
                                }
                                final String[] divisions2 = new String[divisions1.size()+1];
                                divisions2[0]= "--1st Preference--";
                                for (int k =0;k<divisions1.size();k++){
                                    divisions2[k+1] = divisions1.get(k);
                                }
                                final ArrayAdapter<String> adapter;
                                adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,divisions2);
                                spin1.setAdapter(adapter);

                                spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        if (i == 0) {
//                                            Toast.makeText(Openelective.this, String.valueOf(listDataHeader.size()),Toast.LENGTH_SHORT).show();
                                            spin2.setEnabled(false);
                                            spin3.setEnabled(false);
                                            spin4.setEnabled(false);
                                            spin5.setEnabled(false);
                                            spin6.setEnabled(false);
                                            spin7.setEnabled(false);
                                            spin8.setEnabled(false);
                                            spin9.setEnabled(false);
                                            spin10.setEnabled(false);
                                            spin11.setEnabled(false);
                                            spin12.setEnabled(false);
                                            spin13.setEnabled(false);
                                            spin14.setEnabled(false);
                                            spin15.setEnabled(false);
                                            spin16.setEnabled(false);
                                            spin17.setEnabled(false);
                                            spin18.setEnabled(false);
                                            spin19.setEnabled(false);
                                            spin20.setEnabled(false);
                                            spin21.setEnabled(false);
                                            spin22.setEnabled(false);
                                            spin23.setEnabled(false);
                                            first = "";

                                            nxt.setEnabled(true);
                                        }if (i == 1 || i == 2 || i == 3 || i == 4 || i == 5 || i == 6 || i == 7 || i == 8 || i == 9 || i == 10 || i == 11 || i == 12 || i==13 || i == 14 || i == 15 || i == 16 || i==17 || i == 18 || i == 19 || i == 20 || i==21|| i == 22 || i==23) {
                                            first = divisions2[i];
                                            spin2.setEnabled(true);

                                            final ArrayList<String> sndpref = new ArrayList<>();
                                            sndpref.add("2nd Preference");
                                            for (int j = 0; j < divisions2.length; j++) {
                                                if (!Arrays.asList(divisions2[j]).contains(first)) {
                                                    sndpref.add(divisions2[j]);
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
                                                        spin11.setEnabled(false);
                                                        spin12.setEnabled(false);
                                                        spin13.setEnabled(false);
                                                        spin14.setEnabled(false);
                                                        spin15.setEnabled(false);
                                                        spin16.setEnabled(false);
                                                        spin17.setEnabled(false);
                                                        spin18.setEnabled(false);
                                                        spin19.setEnabled(false);
                                                        spin20.setEnabled(false);
                                                        spin21.setEnabled(false);
                                                        spin22.setEnabled(false);
                                                        spin23.setEnabled(false);

                                                        sec = "";
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
                                                                    spin11.setEnabled(false);
                                                                    spin12.setEnabled(false);
                                                                    spin13.setEnabled(false);
                                                                    spin14.setEnabled(false);
                                                                    spin15.setEnabled(false);
                                                                    spin16.setEnabled(false);
                                                                    spin17.setEnabled(false);
                                                                    spin18.setEnabled(false);
                                                                    spin19.setEnabled(false);
                                                                    spin20.setEnabled(false);
                                                                    spin21.setEnabled(false);
                                                                    spin22.setEnabled(false);
                                                                    spin23.setEnabled(false);
                                                                    third = "";
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
                                                                                spin11.setEnabled(false);
                                                                                spin12.setEnabled(false);
                                                                                spin13.setEnabled(false);
                                                                                spin14.setEnabled(false);
                                                                                spin15.setEnabled(false);
                                                                                spin16.setEnabled(false);
                                                                                spin17.setEnabled(false);
                                                                                spin18.setEnabled(false);
                                                                                spin19.setEnabled(false);
                                                                                spin20.setEnabled(false);
                                                                                spin21.setEnabled(false);
                                                                                spin22.setEnabled(false);
                                                                                spin23.setEnabled(false);
                                                                                forth = "";
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
                                                                                            spin11.setEnabled(false);
                                                                                            spin12.setEnabled(false);
                                                                                            spin13.setEnabled(false);
                                                                                            spin14.setEnabled(false);
                                                                                            spin15.setEnabled(false);
                                                                                            spin16.setEnabled(false);
                                                                                            spin17.setEnabled(false);
                                                                                            spin18.setEnabled(false);
                                                                                            spin19.setEnabled(false);
                                                                                            spin20.setEnabled(false);
                                                                                            spin21.setEnabled(false);
                                                                                            spin22.setEnabled(false);
                                                                                            spin23.setEnabled(false);
                                                                                            five = "";
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
                                                                                                        spin11.setEnabled(false);
                                                                                                        spin12.setEnabled(false);
                                                                                                        spin13.setEnabled(false);
                                                                                                        spin14.setEnabled(false);
                                                                                                        spin15.setEnabled(false);
                                                                                                        spin16.setEnabled(false);
                                                                                                        spin17.setEnabled(false);
                                                                                                        spin18.setEnabled(false);
                                                                                                        spin19.setEnabled(false);
                                                                                                        spin20.setEnabled(false);
                                                                                                        spin21.setEnabled(false);
                                                                                                        spin22.setEnabled(false);
                                                                                                        spin23.setEnabled(false);
                                                                                                        six = "";
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
                                                                                                                    spin11.setEnabled(false);
                                                                                                                    spin12.setEnabled(false);
                                                                                                                    spin13.setEnabled(false);
                                                                                                                    spin14.setEnabled(false);
                                                                                                                    spin15.setEnabled(false);
                                                                                                                    spin16.setEnabled(false);
                                                                                                                    spin17.setEnabled(false);
                                                                                                                    spin18.setEnabled(false);
                                                                                                                    spin19.setEnabled(false);
                                                                                                                    spin20.setEnabled(false);
                                                                                                                    spin21.setEnabled(false);
                                                                                                                    spin22.setEnabled(false);
                                                                                                                    spin23.setEnabled(false);
                                                                                                                    seven ="";
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
                                                                                                                                spin11.setEnabled(false);
                                                                                                                                spin12.setEnabled(false);
                                                                                                                                spin13.setEnabled(false);
                                                                                                                                spin14.setEnabled(false);
                                                                                                                                spin15.setEnabled(false);
                                                                                                                                spin16.setEnabled(false);
                                                                                                                                spin17.setEnabled(false);
                                                                                                                                spin18.setEnabled(false);
                                                                                                                                spin19.setEnabled(false);
                                                                                                                                spin20.setEnabled(false);
                                                                                                                                spin21.setEnabled(false);
                                                                                                                                spin22.setEnabled(false);
                                                                                                                                spin23.setEnabled(false);
                                                                                                                                eight = "";
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
                                                                                                                                            spin11.setEnabled(false);
                                                                                                                                            spin12.setEnabled(false);
                                                                                                                                            spin13.setEnabled(false);
                                                                                                                                            spin14.setEnabled(false);
                                                                                                                                            spin15.setEnabled(false);
                                                                                                                                            spin16.setEnabled(false);
                                                                                                                                            spin17.setEnabled(false);
                                                                                                                                            spin18.setEnabled(false);
                                                                                                                                            spin19.setEnabled(false);
                                                                                                                                            spin20.setEnabled(false);
                                                                                                                                            spin21.setEnabled(false);
                                                                                                                                            spin22.setEnabled(false);
                                                                                                                                            spin23.setEnabled(false);
                                                                                                                                            nine ="";
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
                                                                                                                                                        spin11.setEnabled(false);
                                                                                                                                                        spin12.setEnabled(false);
                                                                                                                                                        spin13.setEnabled(false);
                                                                                                                                                        spin14.setEnabled(false);
                                                                                                                                                        spin15.setEnabled(false);
                                                                                                                                                        spin16.setEnabled(false);
                                                                                                                                                        spin17.setEnabled(false);
                                                                                                                                                        spin18.setEnabled(false);
                                                                                                                                                        spin19.setEnabled(false);
                                                                                                                                                        spin20.setEnabled(false);
                                                                                                                                                        spin21.setEnabled(false);
                                                                                                                                                        spin22.setEnabled(false);
                                                                                                                                                        spin23.setEnabled(false);
                                                                                                                                                        ten = "";
                                                                                                                                                        nxt.setEnabled(true);
                                                                                                                                                    } if (i == 1 || i == 2 || i == 3 || i == 4|| i == 5 ||i == 6|| i == 7|| i == 8|| i == 9|| i == 10 || i == 11 || i == 12 || i==13|| i == 14){
                                                                                                                                                        ten = thdpref10.get(i);
                                                                                                                                                        spin11.setEnabled(true);
                                                                                                                                                        final ArrayList<String> thdpref11 = new ArrayList<>();
                                                                                                                                                        thdpref11.add("11th Preference");
                                                                                                                                                        for (int j = 0; j < thdpref10.size(); j++) {
                                                                                                                                                            if (!Arrays.asList(thdpref10.get(j)).contains(ten)) {
                                                                                                                                                                thdpref11.add(thdpref10.get(j));
                                                                                                                                                            }
                                                                                                                                                        }

                                                                                                                                                        thdpref11.remove("10th Preference");
                                                                                                                                                        ArrayAdapter<String> adapter13;
                                                                                                                                                        adapter13 = new ArrayAdapter<String>(Openelective.this, android.R.layout.simple_spinner_dropdown_item, thdpref11);
                                                                                                                                                        spin11.setAdapter(adapter13);
                                                                                                                                                        spin11.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                                                                                                            @Override
                                                                                                                                                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                                                                                                                                if (i == 0) {
                                                                                                                                                                    spin12.setEnabled(false);
                                                                                                                                                                    spin13.setEnabled(false);
                                                                                                                                                                    spin14.setEnabled(false);
                                                                                                                                                                    spin15.setEnabled(false);
                                                                                                                                                                    spin16.setEnabled(false);
                                                                                                                                                                    spin17.setEnabled(false);
                                                                                                                                                                    spin18.setEnabled(false);
                                                                                                                                                                    spin19.setEnabled(false);
                                                                                                                                                                    spin20.setEnabled(false);
                                                                                                                                                                    spin21.setEnabled(false);
                                                                                                                                                                    spin22.setEnabled(false);
                                                                                                                                                                    spin23.setEnabled(false);
                                                                                                                                                                    eleven ="";
                                                                                                                                                                    nxt.setEnabled(true);
                                                                                                                                                                } if (i == 1 || i == 2 || i == 3 || i == 4|| i == 5 ||i == 6|| i == 7|| i == 8|| i == 9|| i == 10 || i == 11 || i == 12 || i==13){
                                                                                                                                                                    eleven = thdpref11.get(i);
                                                                                                                                                                    spin12.setEnabled(true);
                                                                                                                                                                    final ArrayList<String> thdpref12 = new ArrayList<>();
                                                                                                                                                                    thdpref12.add("12th Preference");
                                                                                                                                                                    for (int j = 0; j < thdpref11.size(); j++) {
                                                                                                                                                                        if (!Arrays.asList(thdpref11.get(j)).contains(eleven)) {
                                                                                                                                                                            thdpref12.add(thdpref11.get(j));
                                                                                                                                                                        }
                                                                                                                                                                    }
                                                                                                                                                                    for(int j=0;j<thdpref11.size();j++){
                                                                                                                                                                        Toast.makeText(Openelective.this,thdpref11.get(j),Toast.LENGTH_SHORT).show();
                                                                                                                                                                    }

                                                                                                                                                                    thdpref12.remove("11th Preference");
                                                                                                                                                                    ArrayAdapter<String> adapter14;
                                                                                                                                                                    adapter14 = new ArrayAdapter<String>(Openelective.this, android.R.layout.simple_spinner_dropdown_item, thdpref12);
                                                                                                                                                                    spin12.setAdapter(adapter14);
                                                                                                                                                                    spin12.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                                                                                                                        @Override
                                                                                                                                                                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                                                                                                                                            if (i == 0) {
                                                                                                                                                                                spin13.setEnabled(false);
                                                                                                                                                                                spin14.setEnabled(false);
                                                                                                                                                                                spin15.setEnabled(false);
                                                                                                                                                                                spin16.setEnabled(false);
                                                                                                                                                                                spin17.setEnabled(false);
                                                                                                                                                                                spin18.setEnabled(false);
                                                                                                                                                                                spin19.setEnabled(false);
                                                                                                                                                                                spin20.setEnabled(false);
                                                                                                                                                                                spin21.setEnabled(false);
                                                                                                                                                                                spin22.setEnabled(false);
                                                                                                                                                                                spin23.setEnabled(false);
                                                                                                                                                                                twelve = "";
                                                                                                                                                                                nxt.setEnabled(true);
                                                                                                                                                                            } if (i == 1 || i == 2 || i == 3 || i == 4|| i == 5 ||i == 6|| i == 7|| i == 8|| i == 9|| i == 10 || i == 11 || i == 12){
                                                                                                                                                                                twelve = thdpref12.get(i);
                                                                                                                                                                                spin13.setEnabled(true);
                                                                                                                                                                                final ArrayList<String> thdpref13 = new ArrayList<>();
                                                                                                                                                                                thdpref13.add("13th Preference");
                                                                                                                                                                                for (int j = 0; j < thdpref12.size(); j++) {
                                                                                                                                                                                    if (!Arrays.asList(thdpref12.get(j)).contains(twelve)) {
                                                                                                                                                                                        thdpref13.add(thdpref12.get(j));
                                                                                                                                                                                    }
                                                                                                                                                                                }

                                                                                                                                                                                thdpref13.remove("12th Preference");

                                                                                                                                                                                ArrayAdapter<String> adapter15;
                                                                                                                                                                                adapter15 = new ArrayAdapter<String>(Openelective.this, android.R.layout.simple_spinner_dropdown_item, thdpref13);
                                                                                                                                                                                spin13.setAdapter(adapter15);
                                                                                                                                                                                spin13.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                                                                                                                                    @Override
                                                                                                                                                                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                                                                                                                                                        if (i == 0) {
                                                                                                                                                                                            spin14.setEnabled(false);
                                                                                                                                                                                            spin15.setEnabled(false);
                                                                                                                                                                                            spin16.setEnabled(false);
                                                                                                                                                                                            spin17.setEnabled(false);
                                                                                                                                                                                            spin18.setEnabled(false);
                                                                                                                                                                                            spin19.setEnabled(false);
                                                                                                                                                                                            spin20.setEnabled(false);
                                                                                                                                                                                            spin21.setEnabled(false);
                                                                                                                                                                                            spin22.setEnabled(false);
                                                                                                                                                                                            spin23.setEnabled(false);
                                                                                                                                                                                            thirteen = "";
                                                                                                                                                                                            nxt.setEnabled(false);
                                                                                                                                                                                        } if (i == 1 || i == 2 || i == 3 || i == 4|| i == 5 ||i == 6|| i == 7|| i == 8|| i == 9|| i == 10 || i == 11){
                                                                                                                                                                                            thirteen = thdpref13.get(i);
                                                                                                                                                                                            spin14.setEnabled(true);
                                                                                                                                                                                            nxt.setEnabled(true);
                                                                                                                                                                                            final ArrayList<String> thdpref14 = new ArrayList<>();
                                                                                                                                                                                            thdpref14.add("14th Preference");
                                                                                                                                                                                            for (int j = 0; j < thdpref13.size(); j++) {
                                                                                                                                                                                                if (!Arrays.asList(thdpref13.get(j)).contains(thirteen)) {
                                                                                                                                                                                                    thdpref14.add(thdpref13.get(j));
                                                                                                                                                                                                }
                                                                                                                                                                                            }

                                                                                                                                                                                            thdpref14.remove("13th Preference");
                                                                                                                                                                                            ArrayAdapter<String> adapter16;
                                                                                                                                                                                            adapter16 = new ArrayAdapter<String>(Openelective.this, android.R.layout.simple_spinner_dropdown_item, thdpref14);
                                                                                                                                                                                            spin14.setAdapter(adapter16);
                                                                                                                                                                                            spin14.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                                                                                                                                                @Override
                                                                                                                                                                                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                                                                                                                                                                    if (i == 0) {
                                                                                                                                                                                                        spin15.setEnabled(false);
                                                                                                                                                                                                        spin16.setEnabled(false);
                                                                                                                                                                                                        spin17.setEnabled(false);
                                                                                                                                                                                                        spin18.setEnabled(false);
                                                                                                                                                                                                        spin19.setEnabled(false);
                                                                                                                                                                                                        spin20.setEnabled(false);
                                                                                                                                                                                                        spin21.setEnabled(false);
                                                                                                                                                                                                        spin22.setEnabled(false);
                                                                                                                                                                                                        spin23.setEnabled(false);
                                                                                                                                                                                                        fourteen = "";
                                                                                                                                                                                                        nxt.setEnabled(true);
                                                                                                                                                                                                    } if (i == 1 || i == 2 || i == 3 || i == 4|| i == 5 ||i == 6|| i == 7|| i == 8|| i == 9|| i == 10 || i == 11 || i == 12 || i==13|| i == 14 || i == 15){
                                                                                                                                                                                                        fourteen = thdpref14.get(i);
                                                                                                                                                                                                        spin15.setEnabled(true);
                                                                                                                                                                                                        nxt.setEnabled(true);
                                                                                                                                                                                                        final ArrayList<String> thdpref15 = new ArrayList<>();
                                                                                                                                                                                                        thdpref15.add("15th Preference");
                                                                                                                                                                                                        for (int j = 0; j < thdpref14.size(); j++) {
                                                                                                                                                                                                            if (!Arrays.asList(thdpref14.get(j)).contains(fourteen)) {
                                                                                                                                                                                                                thdpref15.add(thdpref14.get(j));
                                                                                                                                                                                                            }
                                                                                                                                                                                                        }

                                                                                                                                                                                                        thdpref15.remove("14th Preference");
                                                                                                                                                                                                        ArrayAdapter<String> adapter17;
                                                                                                                                                                                                        adapter17 = new ArrayAdapter<String>(Openelective.this, android.R.layout.simple_spinner_dropdown_item, thdpref15);
                                                                                                                                                                                                        spin15.setAdapter(adapter17);
                                                                                                                                                                                                        spin15.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                                                                                                                                                            @Override
                                                                                                                                                                                                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                                                                                                                                                                                if (i == 0) {
                                                                                                                                                                                                                    spin16.setEnabled(false);
                                                                                                                                                                                                                    spin17.setEnabled(false);
                                                                                                                                                                                                                    spin18.setEnabled(false);
                                                                                                                                                                                                                    spin19.setEnabled(false);
                                                                                                                                                                                                                    spin20.setEnabled(false);
                                                                                                                                                                                                                    spin21.setEnabled(false);
                                                                                                                                                                                                                    spin22.setEnabled(false);
                                                                                                                                                                                                                    spin23.setEnabled(false);
                                                                                                                                                                                                                    fifteen = "";
                                                                                                                                                                                                                    nxt.setEnabled(true);
                                                                                                                                                                                                                } if (i == 1 || i == 2 || i == 3 || i == 4|| i == 5 ||i == 6|| i == 7|| i == 8|| i == 9|| i == 10 || i == 11 || i == 12 || i==13|| i == 14 || i == 15){
                                                                                                                                                                                                                    fifteen = thdpref15.get(i);
                                                                                                                                                                                                                    spin16.setEnabled(true);
                                                                                                                                                                                                                    final ArrayList<String> thdpref16 = new ArrayList<>();
                                                                                                                                                                                                                    thdpref16.add("16th Preference");
                                                                                                                                                                                                                    for (int j = 0; j < thdpref15.size(); j++) {
                                                                                                                                                                                                                        if (!Arrays.asList(thdpref15.get(j)).contains(fifteen)) {
                                                                                                                                                                                                                            thdpref16.add(thdpref15.get(j));
                                                                                                                                                                                                                        }
                                                                                                                                                                                                                    }

                                                                                                                                                                                                                    thdpref16.remove("15th Preference");
                                                                                                                                                                                                                    ArrayAdapter<String> adapter18;
                                                                                                                                                                                                                    adapter18 = new ArrayAdapter<String>(Openelective.this, android.R.layout.simple_spinner_dropdown_item, thdpref16);
                                                                                                                                                                                                                    spin16.setAdapter(adapter18);
                                                                                                                                                                                                                    spin16.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                                                                                                                                                                        @Override
                                                                                                                                                                                                                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                                                                                                                                                                                            if (i == 0) {
                                                                                                                                                                                                                                spin17.setEnabled(false);
                                                                                                                                                                                                                                spin18.setEnabled(false);
                                                                                                                                                                                                                                spin19.setEnabled(false);
                                                                                                                                                                                                                                spin20.setEnabled(false);
                                                                                                                                                                                                                                spin21.setEnabled(false);
                                                                                                                                                                                                                                spin22.setEnabled(false);
                                                                                                                                                                                                                                spin23.setEnabled(false);
                                                                                                                                                                                                                                sixteen = "";
                                                                                                                                                                                                                                nxt.setEnabled(true);
                                                                                                                                                                                                                            } if (i == 1 || i == 2 || i == 3 || i == 4|| i == 5 ||i == 6|| i == 7|| i == 8|| i == 9|| i == 10 || i == 11 || i == 12 || i==13|| i == 14 || i == 15){
                                                                                                                                                                                                                                sixteen = thdpref16.get(i);
                                                                                                                                                                                                                                nxt.setEnabled(true);
                                                                                                                                                                                                                                spin17.setEnabled(true);
                                                                                                                                                                                                                                final ArrayList<String> thdpref17 = new ArrayList<>();
                                                                                                                                                                                                                                thdpref17.add("17th Preference");
                                                                                                                                                                                                                                for (int j = 0; j < thdpref16.size(); j++) {
                                                                                                                                                                                                                                    if (!Arrays.asList(thdpref16.get(j)).contains(sixteen)) {
                                                                                                                                                                                                                                        thdpref17.add(thdpref16.get(j));
                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                }

                                                                                                                                                                                                                                thdpref17.remove("16th Preference");
                                                                                                                                                                                                                                ArrayAdapter<String> adapter19;
                                                                                                                                                                                                                                adapter19 = new ArrayAdapter<String>(Openelective.this, android.R.layout.simple_spinner_dropdown_item, thdpref17);
                                                                                                                                                                                                                                spin17.setAdapter(adapter19);
                                                                                                                                                                                                                                spin17.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                                                                                                                                                                                    @Override
                                                                                                                                                                                                                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                                                                                                                                                                                                        if (i == 0) {
                                                                                                                                                                                                                                            spin18.setEnabled(false);
                                                                                                                                                                                                                                            spin19.setEnabled(false);
                                                                                                                                                                                                                                            spin20.setEnabled(false);
                                                                                                                                                                                                                                            spin21.setEnabled(false);
                                                                                                                                                                                                                                            spin22.setEnabled(false);
                                                                                                                                                                                                                                            spin23.setEnabled(false);
                                                                                                                                                                                                                                            seventeen = "";
                                                                                                                                                                                                                                            nxt.setEnabled(true);
                                                                                                                                                                                                                                        } if (i == 1 || i == 2 || i == 3 || i == 4|| i == 5 ||i == 6|| i == 7|| i == 8|| i == 9|| i == 10 || i == 11 || i == 12 || i==13|| i == 14 || i == 15){
                                                                                                                                                                                                                                            seventeen = thdpref17.get(i);
                                                                                                                                                                                                                                            nxt.setEnabled(true);
                                                                                                                                                                                                                                            spin18.setEnabled(true);
                                                                                                                                                                                                                                            final ArrayList<String> thdpref18 = new ArrayList<>();
                                                                                                                                                                                                                                            thdpref18.add("18th Preference");
                                                                                                                                                                                                                                            for (int j = 0; j < thdpref17.size(); j++) {
                                                                                                                                                                                                                                                if (!Arrays.asList(thdpref17.get(j)).contains(seventeen)) {
                                                                                                                                                                                                                                                    thdpref18.add(thdpref17.get(j));
                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                            }

                                                                                                                                                                                                                                            thdpref18.remove("17th Preference");
                                                                                                                                                                                                                                            ArrayAdapter<String> adapter20;
                                                                                                                                                                                                                                            adapter20 = new ArrayAdapter<String>(Openelective.this, android.R.layout.simple_spinner_dropdown_item, thdpref18);
                                                                                                                                                                                                                                            spin18.setAdapter(adapter20);
                                                                                                                                                                                                                                            spin18.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                                                                                                                                                                                                @Override
                                                                                                                                                                                                                                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                                                                                                                                                                                                                    if (i == 0) {
                                                                                                                                                                                                                                                        spin19.setEnabled(false);
                                                                                                                                                                                                                                                        spin20.setEnabled(false);
                                                                                                                                                                                                                                                        spin21.setEnabled(false);
                                                                                                                                                                                                                                                        spin22.setEnabled(false);
                                                                                                                                                                                                                                                        spin23.setEnabled(false);
                                                                                                                                                                                                                                                        nxt.setEnabled(true);
                                                                                                                                                                                                                                                    } if (i == 1 || i == 2 || i == 3 || i == 4|| i == 5 ||i == 6|| i == 7|| i == 8|| i == 9|| i == 10 || i == 11 || i == 12 || i==13|| i == 14 || i == 15){
                                                                                                                                                                                                                                                        eighteen = thdpref18.get(i);
                                                                                                                                                                                                                                                        eighteen = "";
                                                                                                                                                                                                                                                        spin19.setEnabled(true);
                                                                                                                                                                                                                                                        nxt.setEnabled(true);
                                                                                                                                                                                                                                                        final ArrayList<String> thdpref19 = new ArrayList<>();
                                                                                                                                                                                                                                                        thdpref19.add("19th Preference");
                                                                                                                                                                                                                                                        for (int j = 0; j < thdpref18.size(); j++) {
                                                                                                                                                                                                                                                            if (!Arrays.asList(thdpref18.get(j)).contains(eighteen)) {
                                                                                                                                                                                                                                                                thdpref19.add(thdpref18.get(j));
                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                        }

                                                                                                                                                                                                                                                        thdpref19.remove("18th Preference");
                                                                                                                                                                                                                                                        ArrayAdapter<String> adapter21;
                                                                                                                                                                                                                                                        adapter21 = new ArrayAdapter<String>(Openelective.this, android.R.layout.simple_spinner_dropdown_item, thdpref19);
                                                                                                                                                                                                                                                        spin19.setAdapter(adapter21);
                                                                                                                                                                                                                                                        spin19.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                                                                                                                                                                                                            @Override
                                                                                                                                                                                                                                                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                                                                                                                                                                                                                                if (i == 0) {
                                                                                                                                                                                                                                                                    spin20.setEnabled(false);
                                                                                                                                                                                                                                                                    spin21.setEnabled(false);
                                                                                                                                                                                                                                                                    spin22.setEnabled(false);
                                                                                                                                                                                                                                                                    spin23.setEnabled(false);
                                                                                                                                                                                                                                                                    nxt.setEnabled(true);
                                                                                                                                                                                                                                                                    nineteen = "";
                                                                                                                                                                                                                                                                } if (i == 1 || i == 2 || i == 3 || i == 4|| i == 5 ||i == 6|| i == 7|| i == 8|| i == 9|| i == 10 || i == 11 || i == 12 || i==13|| i == 14 || i == 15){
                                                                                                                                                                                                                                                                    nineteen = thdpref19.get(i);
                                                                                                                                                                                                                                                                    spin20.setEnabled(true);
                                                                                                                                                                                                                                                                    nxt.setEnabled(true);
                                                                                                                                                                                                                                                                    final ArrayList<String> thdpref20 = new ArrayList<>();
                                                                                                                                                                                                                                                                    thdpref20.add("20th Preference");
                                                                                                                                                                                                                                                                    for (int j = 0; j < thdpref19.size(); j++) {
                                                                                                                                                                                                                                                                        if (!Arrays.asList(thdpref19.get(j)).contains(nineteen)) {
                                                                                                                                                                                                                                                                            thdpref20.add(thdpref19.get(j));
                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                    }

                                                                                                                                                                                                                                                                    thdpref20.remove("19th Preference");
                                                                                                                                                                                                                                                                    ArrayAdapter<String> adapter22;
                                                                                                                                                                                                                                                                    adapter22 = new ArrayAdapter<String>(Openelective.this, android.R.layout.simple_spinner_dropdown_item, thdpref20);
                                                                                                                                                                                                                                                                    spin20.setAdapter(adapter22);
                                                                                                                                                                                                                                                                    spin20.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                                                                                                                                                                                                                        @Override
                                                                                                                                                                                                                                                                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                                                                                                                                                                                                                                            if (i == 0) {
                                                                                                                                                                                                                                                                                spin21.setEnabled(false);
                                                                                                                                                                                                                                                                                spin22.setEnabled(false);
                                                                                                                                                                                                                                                                                spin23.setEnabled(false);
                                                                                                                                                                                                                                                                                twenty = "";
                                                                                                                                                                                                                                                                                nxt.setEnabled(true);
                                                                                                                                                                                                                                                                            } if (i == 1 || i == 2 || i == 3 || i == 4|| i == 5 ||i == 6|| i == 7|| i == 8|| i == 9|| i == 10 || i == 11 || i == 12 || i==13|| i == 14 || i == 15){
                                                                                                                                                                                                                                                                                twenty = thdpref20.get(i);
                                                                                                                                                                                                                                                                                spin21.setEnabled(true);
                                                                                                                                                                                                                                                                                nxt.setEnabled(true);
                                                                                                                                                                                                                                                                                final ArrayList<String> thdpref21 = new ArrayList<>();
                                                                                                                                                                                                                                                                                thdpref21.add("21th Preference");
                                                                                                                                                                                                                                                                                for (int j = 0; j < thdpref20.size(); j++) {
                                                                                                                                                                                                                                                                                    if (!Arrays.asList(thdpref20.get(j)).contains(twenty)) {
                                                                                                                                                                                                                                                                                        thdpref21.add(thdpref20.get(j));
                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                }

                                                                                                                                                                                                                                                                                thdpref21.remove("20th Preference");
                                                                                                                                                                                                                                                                                ArrayAdapter<String> adapter23;
                                                                                                                                                                                                                                                                                adapter23 = new ArrayAdapter<String>(Openelective.this, android.R.layout.simple_spinner_dropdown_item, thdpref21);
                                                                                                                                                                                                                                                                                spin21.setAdapter(adapter23);
                                                                                                                                                                                                                                                                                spin21.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                                                                                                                                                                                                                                    @Override
                                                                                                                                                                                                                                                                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                                                                                                                                                                                                                                                        if (i == 0) {
                                                                                                                                                                                                                                                                                            spin22.setEnabled(false);
                                                                                                                                                                                                                                                                                            spin23.setEnabled(false);
                                                                                                                                                                                                                                                                                            twentyone = "";
                                                                                                                                                                                                                                                                                            nxt.setEnabled(true);
                                                                                                                                                                                                                                                                                        } if (i == 1 || i == 2 || i == 3 || i == 4|| i == 5 ||i == 6|| i == 7|| i == 8|| i == 9|| i == 10 || i == 11 || i == 12 || i==13|| i == 14 || i == 15){
                                                                                                                                                                                                                                                                                            twentyone = thdpref21.get(i);
                                                                                                                                                                                                                                                                                            spin22.setEnabled(true);
                                                                                                                                                                                                                                                                                            nxt.setEnabled(true);
                                                                                                                                                                                                                                                                                            final ArrayList<String> thdpref22 = new ArrayList<>();
                                                                                                                                                                                                                                                                                            thdpref22.add("22th Preference");
                                                                                                                                                                                                                                                                                            for (int j = 0; j < thdpref21.size(); j++) {
                                                                                                                                                                                                                                                                                                if (!Arrays.asList(thdpref21.get(j)).contains(twentyone)) {
                                                                                                                                                                                                                                                                                                    thdpref22.add(thdpref21.get(j));
                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                            }

                                                                                                                                                                                                                                                                                            thdpref22.remove("21th Preference");
                                                                                                                                                                                                                                                                                            ArrayAdapter<String> adapter24;
                                                                                                                                                                                                                                                                                            adapter24 = new ArrayAdapter<String>(Openelective.this, android.R.layout.simple_spinner_dropdown_item, thdpref22);
                                                                                                                                                                                                                                                                                            spin22.setAdapter(adapter24);
                                                                                                                                                                                                                                                                                            spin22.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                                                                                                                                                                                                                                                @Override
                                                                                                                                                                                                                                                                                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                                                                                                                                                                                                                                                                    if (i == 0) {
                                                                                                                                                                                                                                                                                                        twentytwo = "";
                                                                                                                                                                                                                                                                                                        spin23.setEnabled(false);
                                                                                                                                                                                                                                                                                                        nxt.setEnabled(false);
                                                                                                                                                                                                                                                                                                    } if (i == 1 || i == 2 || i == 3 || i == 4|| i == 5 ||i == 6|| i == 7|| i == 8|| i == 9|| i == 10 || i == 11 || i == 12 || i==13|| i == 14 || i == 15){
                                                                                                                                                                                                                                                                                                        twentytwo = thdpref22.get(i);
                                                                                                                                                                                                                                                                                                        spin23.setEnabled(true);
                                                                                                                                                                                                                                                                                                        nxt.setEnabled(true);
                                                                                                                                                                                                                                                                                                        final ArrayList<String> thdpref23 = new ArrayList<>();
                                                                                                                                                                                                                                                                                                        thdpref23.add("23th Preference");
                                                                                                                                                                                                                                                                                                        for (int j = 0; j < thdpref22.size(); j++) {
                                                                                                                                                                                                                                                                                                            if (!Arrays.asList(thdpref22.get(j)).contains(twentytwo)) {
                                                                                                                                                                                                                                                                                                                thdpref23.add(thdpref22.get(j));
                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                        }

                                                                                                                                                                                                                                                                                                        thdpref23.remove("22th Preference");
                                                                                                                                                                                                                                                                                                        ArrayAdapter<String> adapter25;
                                                                                                                                                                                                                                                                                                        adapter25 = new ArrayAdapter<String>(Openelective.this, android.R.layout.simple_spinner_dropdown_item, thdpref23);
                                                                                                                                                                                                                                                                                                        spin23.setAdapter(adapter25);
                                                                                                                                                                                                                                                                                                        spin23.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                                                                                                                                                                                                                                                            @Override
                                                                                                                                                                                                                                                                                                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                                                                                                                                                                                                                                                                                if (i == 0) {
                                                                                                                                                                                                                                                                                                                    twentythree = "";
                                                                                                                                                                                                                                                                                                                    nxt.setEnabled(true);
                                                                                                                                                                                                                                                                                                                } if (i == 1 || i == 2 || i == 3 || i == 4|| i == 5 ||i == 6|| i == 7|| i == 8|| i == 9|| i == 10 || i == 11 || i == 12 || i==13|| i == 14 || i == 15){
                                                                                                                                                                                                                                                                                                                    twentythree = thdpref23.get(i);
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

                                                }

                                            });
                                        }
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {

                                    }
                                });
                            }

                            @Override
                            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                            }

                            @Override
                            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                            }

                            @Override
                            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

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


        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(abc==13){
                    if (first.isEmpty() || sec.isEmpty() || third.isEmpty() || forth.isEmpty() || five.isEmpty() || six.isEmpty() || seven.isEmpty() || eight.isEmpty() || nine.isEmpty() || ten.isEmpty()|| eleven.isEmpty() || twelve.isEmpty() || thirteen.isEmpty()){
//                    Toast.makeText(Openelective.this,"Fill atleast 1st preference",Toast.LENGTH_SHORT).show();
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
                                        ref3 = firebaseDatabase.getReference("OpenElective").child(rank);
                                        ref3.child("1").setValue(first);
                                        ref3.child("2").setValue(sec);
                                        ref3.child("3").setValue(third);
                                        ref3.child("4").setValue(forth);
                                        ref3.child("5").setValue(five);
                                        ref3.child("6").setValue(six);
                                        ref3.child("7").setValue(seven);
                                        ref3.child("8").setValue(eight);
                                        ref3.child("9").setValue(nine);
                                        ref3.child("10").setValue(ten);
                                        ref3.child("11").setValue(eleven);
                                        ref3.child("12").setValue(twelve);
                                        ref3.child("13").setValue(thirteen);

                                        ref4 = firebaseDatabase.getReference("Info").child(rank);
                                        ref4.child("Name").setValue(name);
                                        ref4.child("Rollno").setValue(roll);
                                        ref4.child("Email").setValue(email);
                                        ref4.child("Cgpa").setValue(cg);
                                        ref4.child("Phone").setValue(phone);
                                        ref4.child("Branch").setValue(dep);

                                        pd.dismiss();
//                                    Toast.makeText(Openelective.this,"Done Registration",Toast.LENGTH_SHORT).show();
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
                if(abc==14){
                    if (first.isEmpty() || sec.isEmpty() || third.isEmpty() || forth.isEmpty() || five.isEmpty() || six.isEmpty() || seven.isEmpty() || eight.isEmpty() || nine.isEmpty() || ten.isEmpty()|| eleven.isEmpty() || twelve.isEmpty() || thirteen.isEmpty()||fourteen.isEmpty()){
//                    Toast.makeText(Openelective.this,"Fill atleast 1st preference",Toast.LENGTH_SHORT).show();
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
                                        ref3 = firebaseDatabase.getReference("OpenElective").child(rank);
                                        ref3.child("1").setValue(first);
                                        ref3.child("2").setValue(sec);
                                        ref3.child("3").setValue(third);
                                        ref3.child("4").setValue(forth);
                                        ref3.child("5").setValue(five);
                                        ref3.child("6").setValue(six);
                                        ref3.child("7").setValue(seven);
                                        ref3.child("8").setValue(eight);
                                        ref3.child("9").setValue(nine);
                                        ref3.child("10").setValue(ten);
                                        ref3.child("11").setValue(eleven);
                                        ref3.child("12").setValue(twelve);
                                        ref3.child("13").setValue(thirteen);
                                        ref3.child("14").setValue(fourteen);

                                        ref4 = firebaseDatabase.getReference("Info").child(rank);
                                        ref4.child("Name").setValue(name);
                                        ref4.child("Rollno").setValue(roll);
                                        ref4.child("Email").setValue(email);
                                        ref4.child("Cgpa").setValue(cg);
                                        ref4.child("Phone").setValue(phone);
                                        ref4.child("Branch").setValue(dep);

                                        pd.dismiss();
//                                    Toast.makeText(Openelective.this,"Done Registration",Toast.LENGTH_SHORT).show();
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
                if(abc==15){
                    if (first.isEmpty() || sec.isEmpty() || third.isEmpty() || forth.isEmpty() || five.isEmpty() || six.isEmpty() || seven.isEmpty() || eight.isEmpty() || nine.isEmpty() || ten.isEmpty()|| eleven.isEmpty() || twelve.isEmpty() || thirteen.isEmpty()||fourteen.isEmpty()||fifteen.isEmpty()){
//                    Toast.makeText(Openelective.this,"Fill atleast 1st preference",Toast.LENGTH_SHORT).show();
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
                                        ref3 = firebaseDatabase.getReference("OpenElective").child(rank);
                                        ref3.child("1").setValue(first);
                                        ref3.child("2").setValue(sec);
                                        ref3.child("3").setValue(third);
                                        ref3.child("4").setValue(forth);
                                        ref3.child("5").setValue(five);
                                        ref3.child("6").setValue(six);
                                        ref3.child("7").setValue(seven);
                                        ref3.child("8").setValue(eight);
                                        ref3.child("9").setValue(nine);
                                        ref3.child("10").setValue(ten);
                                        ref3.child("11").setValue(eleven);
                                        ref3.child("12").setValue(twelve);
                                        ref3.child("13").setValue(thirteen);
                                        ref3.child("14").setValue(fourteen);
                                        ref3.child("15").setValue(fifteen);

                                        ref4 = firebaseDatabase.getReference("Info").child(rank);
                                        ref4.child("Name").setValue(name);
                                        ref4.child("Rollno").setValue(roll);
                                        ref4.child("Email").setValue(email);
                                        ref4.child("Cgpa").setValue(cg);
                                        ref4.child("Phone").setValue(phone);
                                        ref4.child("Branch").setValue(dep);

                                        pd.dismiss();
//                                    Toast.makeText(Openelective.this,"Done Registration",Toast.LENGTH_SHORT).show();
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
            }
        });
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
                startActivity(new Intent(Openelective.this,OpenElectiveInfo.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
