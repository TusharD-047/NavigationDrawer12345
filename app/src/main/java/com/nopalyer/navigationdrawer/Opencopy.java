package com.nopalyer.navigationdrawer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Opencopy extends AppCompatActivity {

    Spinner spin1,spin2,spin3,spin4,spin5,spin6,spin7,spin8,spin9,spin10,spin11,spin12,spin13,spin14,spin15,spin16,spin17,spin18,spin19,spin20,spin21,spin22,spin23;
    ImageView im16,im17,im18,im19,im20,im21,im22,im23;
    Button nxt;
    LinearLayout l15,l16,l17,l18,l19,l20,l21,l22,l23,l14,l13;
    String first,sec,third,forth,five,six,seven,eight,nine,ten,eleven,twelve,thirteen,fourteen,fifteen,sixteen,seventeen,eighteen,nineteen,twenty,twentyone,twentytwo,twentythree;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser firebaseUser;
    DatabaseReference ref,ref2,ref3,ref4,ref5,ref6,ref7,ref8,ref9,ref10,ref11,ref12,ref13,databaseReference,databaseReference1,databaseReference2;
    ProgressDialog pd,pd2;
    Toolbar toolbar;
    String dep = "";
    List<String> listDataHeader,listDataHeader1;
    int sublength,deplength;
    int abc;
    String[] divisions;
    ArrayList<String> selection = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_openelective);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AlertDialog.Builder builder = new AlertDialog.Builder(Opencopy.this);
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
        listDataHeader = new ArrayList<>();
        listDataHeader1 = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("Subjects");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                final String headertitile = dataSnapshot.getKey();
                listDataHeader.add(headertitile);

                divisions = new String[listDataHeader.size()];

                //divisions[0] = "--1st Preference--";
                for(int i=0; i<divisions.length;i++){
                    divisions[i] = listDataHeader.get(i);
                }
                sublength = divisions.length;
                final ArrayList<String> divisions1 = new ArrayList<>();
                Toast.makeText(Opencopy.this, String.valueOf(sublength), Toast.LENGTH_SHORT).show();


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
        databaseReference1 = firebaseDatabase.getReference(firebaseAuth.getUid()).child("Profile");
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dep = dataSnapshot.child("Department").getValue().toString();
                databaseReference2 = firebaseDatabase.getReference("Department Wise").child(dep);
                databaseReference2.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        final String depsub = dataSnapshot.getKey();

                        listDataHeader1.add(depsub);
                        final String[] divsub = new String[listDataHeader1.size()];
                        for(int i=0;i<divsub.length;i++){
                            divsub[i] = listDataHeader1.get(i);
                        }
                        deplength = divsub.length;
                        final ArrayList<String> divisions1 = new ArrayList<>();
                        for(int i=0;i<divisions.length;i++){
                            for(int j=0;j<divsub.length;j++) {

                                if (!Arrays.asList(divisions[i]).contains(divsub[j])) {
                                    divisions1.add(divisions[i]);
                                }
                            }
                        }
                        Toast.makeText(Opencopy.this,String.valueOf(divisions1),Toast.LENGTH_SHORT).show();
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
    }
}