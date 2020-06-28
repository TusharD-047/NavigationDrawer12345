package com.nopalyer.navigationdrawer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nopalyer.navigationdrawer.teacher.tpassign;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LibVer extends AppCompatActivity {

    private Button nxt,done;
    private Spinner lib1,lib2;
    private ListView rollList;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference,mref,reference,ref2,ref3,ref4,mref2;
    List<String> listDataHeader,list;
    ProgressDialog pd;
    public static String dep,year2;
    ArrayList<String> selectedItems = new ArrayList<>();
    CheckedTextView c;
    // private Button show;
    ArrayAdapter<String> adapter_year,adapter_department;
    //SharedPreferences sharedprefs;
    //SharedPreferences.Editor editor;
    Toolbar toolbar;

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
                startActivity(new Intent(LibVer.this, login.class));
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lib_ver);
        nxt = findViewById(R.id.lshow);
        done = findViewById(R.id.ldone);
        c = findViewById(R.id.txt_lan);
        rollList = findViewById(R.id.libList);
        rollList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        toolbar = (Toolbar)findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Verification");
        toolbar.setTitleTextColor(Color.WHITE);

        lib1 = (Spinner) findViewById(R.id.lib1);
        lib2 = (Spinner) findViewById(R.id.lib2);

        final String[] year = {"Choose year","1st year","2nd year","3rd year","4th year"};
        final String[] department = {"Choose branch","CSE","CSE-DD","ECE","ECE-DD","Mechanical","Civil","Electrical","Architecture","Material Science","Chemical"};

        adapter_year = new ArrayAdapter<>(LibVer.this, R.layout.colourful_spinner_items, year);
        adapter_year.setDropDownViewResource(R.layout.colourful_spinner_dropdown);
        lib1.setAdapter(adapter_year);


        adapter_year = new ArrayAdapter<>(LibVer.this, R.layout.colourful_spinner_items, year);
        adapter_year.setDropDownViewResource(R.layout.colourful_spinner_dropdown);
        lib1.setAdapter(adapter_year);
        lib1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    //      show.setEnabled(false);
                }
                if (position == 1 || position == 2 || position == 3 || position == 4) {
                    adapter_department = new ArrayAdapter<String>(LibVer.this, R.layout.colourful_spinner_items, department);
                    adapter_department.setDropDownViewResource(R.layout.colourful_spinner_dropdown);
                    lib2.setAdapter(adapter_department);
                    year2 = year[position];
                    lib2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            if (position == 0) {
                                //  show.setEnabled(false);
                            }
                            if (position == 1) {
                                //   show.setEnabled(true);
                                dep = department[position];
                            }
                            if (position == 2) {
                                //  show.setEnabled(true);
                                dep = department[position];
                            }
                            if (position == 3) {
                                // show.setEnabled(true);
                                dep = department[position];
                            }
                            if (position == 4) {
                                //  show.setEnabled(true);
                                dep = department[position];
                            }
                            if (position == 5) {
                                //  show.setEnabled(true);
                                dep = department[position];
                            }
                            if (position == 6) {
                                // show.setEnabled(true);
                                dep = department[position];
                            }
                            if (position == 7) {
                                //  show.setEnabled(true);
                                dep = department[position];
                            }
                            if (position == 8) {
                                // show.setEnabled(true);
                                dep = department[position];
                            }
                            if (position == 9) {
                                // show.setEnabled(true);
                                dep = department[position];
                            }
                            if (position == 10) {
                                // show.setEnabled(true);
                                dep = department[position];
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // SPINNER ENDS==================================================================================================================================

        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list = new ArrayList<>();
                ref2 = firebaseDatabase.getReference();
                ref2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("Lib Verification")){
                            ref3 = firebaseDatabase.getReference().child("Lib Verification");
                            ref3.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.hasChild(year2)){
                                        ref4 = firebaseDatabase.getReference().child("Lib Verification").child(year2);
                                        ref4.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.hasChild(dep)){
                                                    reference = firebaseDatabase.getReference("Lib Verification").child(year2).child(dep);
                                                    reference.addChildEventListener(new ChildEventListener() {
                                                        @Override
                                                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                                            final String headertitile = dataSnapshot.getKey();
                                                            list.add(headertitile);

                                                            String[] verroll = new String[list.size()];

                                                            for(int i=0; i<verroll.length;i++){
                                                                verroll[i] = list.get(i);
                                                            }

                                                            viewAllFiles(verroll);

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
                                                }else {
                                                    String[] vvv = new String[list.size()];
                                                    viewAllFiles(vvv);
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });
                                    }else {
                                        String[] vvv = new String[list.size()];
                                        viewAllFiles(vvv);
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }else {
                            String[] vvv = new String[list.size()];
                            viewAllFiles(vvv);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        rollList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectItem = ((TextView)view).getText().toString();
                if (selectedItems.contains(selectItem)){
                    selectedItems.remove(selectItem);
                }else {
                    selectedItems.add(selectItem);
                }
            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i< selectedItems.size();i++){
                    mref = firebaseDatabase.getReference("Other Verification").child(year2).child(dep).child(selectedItems.get(i));
                    mref.child("LibVerify").setValue("Yes");

                    mref2 = firebaseDatabase.getReference("Lib Verification").child(year2).child(dep).child(selectedItems.get(i));
                    mref2.child("LibVerify").setValue("Yes");

                }
            }
        });
    }

        private void viewAllFiles(final String[] a) {

            listDataHeader = new ArrayList<>();
            databaseReference = firebaseDatabase.getReference("Application Form").child(year2).child(dep);
            databaseReference.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    final String headertitile = dataSnapshot.getKey();
                    listDataHeader.add(headertitile);
                    String[] uploads = new String[listDataHeader.size()];

                    for(int i=0; i<uploads.length;i++){
                        uploads[i] = listDataHeader.get(i);
                    }

                    ArrayList<String> ls = new ArrayList<>();
                    for (int j = 0; j < uploads.length; j++){
                        if (!Arrays.asList(a).contains(uploads[j])){
                            ls.add(uploads[j]);
                        }
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.rowlayout,R.id.txt_lan,ls);
                /*{
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {

                        View view = super.getView(position, convertView, parent);

                        TextView myText = (TextView)view.findViewById(android.R.id.text1);
                        myText.setTextColor(Color.BLACK);
                        myText.setTextSize(20);

                        return view;
                    }
                };*/
                    rollList.setAdapter(adapter);
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
    }



