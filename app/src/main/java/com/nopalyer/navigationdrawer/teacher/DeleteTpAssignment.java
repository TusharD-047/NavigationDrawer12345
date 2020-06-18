package com.nopalyer.navigationdrawer.teacher;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.storage.StorageManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.nopalyer.navigationdrawer.DeleteAssignment;
import com.nopalyer.navigationdrawer.R;
import com.nopalyer.navigationdrawer.UploadedAssignment;

import java.util.ArrayList;
import java.util.List;

public class DeleteTpAssignment extends AppCompatActivity {

    Toolbar toolbar;
    Spinner sp1,sp2;
    Button show,delete;
    private ListView ListForm;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference,reeef,ref;
    private StorageReference sref;
    List<String> listDataHeader;
    ArrayAdapter<String> adapter_year,adapter_group,adapter_department;
    public static String dep,year2;
    String name;
    ArrayList<String> selectedItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_tp_assignment);

        firebaseAuth = FirebaseAuth.getInstance();
        ListForm = (ListView)findViewById(R.id.ListDeleteAssign);
        ListForm.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        reeef = FirebaseDatabase.getInstance().getReference(firebaseAuth.getUid()).child("Profile");

        toolbar = (Toolbar) findViewById(R.id.toolbar123456);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Delete Assignment");
        toolbar.setTitleTextColor(Color.WHITE);

        sp1 = (Spinner) findViewById(R.id.uploadassigndeletesp3);
        sp2 = (Spinner) findViewById(R.id.uploadassigndeletesp4);
        show = (Button) findViewById(R.id.tpassigndeleteshow);
        delete = (Button) findViewById(R.id.tpassigndelete__);

        final String[] year = {"Choose year", "1st year", "2nd year", "3rd year", "4th year"};
        final String[] group = {"Choose group", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        final String[] department = {"Choose branch", "CSE", "CSE DD", "ECE", "ECE DD", "Mechanical", "Civil", "Electrical", "Architecture", "Material Science", "Chemical"};

        adapter_year = new ArrayAdapter<>(DeleteTpAssignment.this, R.layout.colourful_spinner_items, year);
        adapter_year.setDropDownViewResource(R.layout.colourful_spinner_dropdown);
        sp1.setAdapter(adapter_year);

        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    show.setEnabled(false);
                }
                if (position == 1) {
                    adapter_group = new ArrayAdapter<String>(DeleteTpAssignment.this, R.layout.colourful_spinner_items, group);
                    adapter_group.setDropDownViewResource(R.layout.colourful_spinner_dropdown);
                    sp2.setAdapter(adapter_group);
                    year2 = year[position];
                    sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (position == 0) {
                                show.setEnabled(false);
                            }
                            if (position == 1) {
                                show.setEnabled(true);
                                dep = group[position];
                            }
                            if (position == 2) {
                                show.setEnabled(true);
                                dep = group[position];
                            }
                            if (position == 3) {
                                show.setEnabled(true);
                                dep = group[position];
                            }
                            if (position == 4) {
                                show.setEnabled(true);
                                dep = group[position];
                            }
                            if (position == 5) {
                                show.setEnabled(true);
                                dep = group[position];
                            }
                            if (position == 6) {
                                show.setEnabled(true);
                                dep = group[position];
                            }
                            if (position == 7) {
                                show.setEnabled(true);
                                dep = group[position];
                            }
                            if (position == 8) {
                                show.setEnabled(true);
                                dep = group[position];
                            }
                            if (position == 9) {
                                show.setEnabled(true);
                                dep = group[position];
                            }
                            if (position == 10) {
                                show.setEnabled(true);
                                dep = group[position];
                            }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }

                if (position == 2) {
                    adapter_department = new ArrayAdapter<String>(DeleteTpAssignment.this, R.layout.colourful_spinner_items, department);
                    adapter_department.setDropDownViewResource(R.layout.colourful_spinner_dropdown);
                    sp2.setAdapter(adapter_department);
                    year2 = year[position];
                    sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            if (position == 0) {
                                show.setEnabled(false);
                            }
                            if (position == 1) {
                                show.setEnabled(true);
                                dep = department[position];
                            }
                            if (position == 2) {
                                show.setEnabled(true);
                                dep = department[position];
                            }
                            if (position == 3) {
                                show.setEnabled(true);
                                dep = department[position];
                            }
                            if (position == 4) {
                                show.setEnabled(true);
                                dep = department[position];
                            }
                            if (position == 5) {
                                show.setEnabled(true);
                                dep = department[position];
                            }
                            if (position == 6) {
                                show.setEnabled(true);
                                dep = department[position];
                            }
                            if (position == 7) {
                                show.setEnabled(true);
                                dep = department[position];
                            }
                            if (position == 8) {
                                show.setEnabled(true);
                                dep = department[position];
                            }
                            if (position == 9) {
                                show.setEnabled(true);
                                dep = department[position];
                            }
                            if (position == 10) {
                                show.setEnabled(true);
                                dep = department[position];
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }

                if (position == 3) {
                    adapter_department = new ArrayAdapter<String>(DeleteTpAssignment.this, R.layout.colourful_spinner_items, department);
                    adapter_department.setDropDownViewResource(R.layout.colourful_spinner_dropdown);
                    sp2.setAdapter(adapter_department);
                    year2 = year[position];
                    sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            if (position == 0) {
                                show.setEnabled(false);
                            }
                            if (position == 1) {
                                show.setEnabled(true);
                                dep = department[position];
                            }
                            if (position == 2) {
                                show.setEnabled(true);
                                dep = department[position];
                            }
                            if (position == 3) {
                                show.setEnabled(true);
                                dep = department[position];
                            }
                            if (position == 4) {
                                show.setEnabled(true);
                                dep = department[position];
                            }
                            if (position == 5) {
                                show.setEnabled(true);
                                dep = department[position];
                            }
                            if (position == 6) {
                                show.setEnabled(true);
                                dep = department[position];
                            }
                            if (position == 7) {
                                show.setEnabled(true);
                                dep = department[position];
                            }
                            if (position == 8) {
                                show.setEnabled(true);
                                dep = department[position];
                            }
                            if (position == 9) {
                                show.setEnabled(true);
                                dep = department[position];
                            }
                            if (position == 10) {
                                show.setEnabled(true);
                                dep = department[position];
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }

                if (position == 4) {
                    adapter_department = new ArrayAdapter<String>(DeleteTpAssignment.this, R.layout.colourful_spinner_items, department);
                    adapter_department.setDropDownViewResource(R.layout.colourful_spinner_dropdown);
                    sp2.setAdapter(adapter_department);
                    year2 = year[position];
                    sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            if (position == 0) {
                                show.setEnabled(false);
                            }
                            if (position == 1) {
                                show.setEnabled(true);
                                dep = department[position];
                            }
                            if (position == 2) {
                                show.setEnabled(true);
                                dep = department[position];
                            }
                            if (position == 3) {
                                show.setEnabled(true);
                                dep = department[position];
                            }

                            if (position == 4) {
                                show.setEnabled(true);
                                dep = department[position];
                            }
                            if (position == 5) {
                                show.setEnabled(true);
                                dep = department[position];
                            }
                            if (position == 6) {
                                show.setEnabled(true);
                                dep = department[position];
                            }
                            if (position == 7) {
                                show.setEnabled(true);
                                dep = department[position];

                            }
                            if (position == 8) {
                                show.setEnabled(true);
                                dep = department[position];
                            }
                            if (position == 9) {
                                show.setEnabled(true);
                                dep = department[position];
                            }
                            if (position == 10) {
                                show.setEnabled(true);
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

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewAllFiles();
            }
        });

        ListForm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i< selectedItems.size();i++){
                    ref = FirebaseDatabase.getInstance().getReference("Assignment").child(year2).child(dep).child(name);
                    ref.child(selectedItems.get(i)).removeValue();
                    sref = FirebaseStorage.getInstance().getReference("Assignment").child(year2).child(dep).child(name);
                    sref.child(selectedItems.get(i)).delete();
                }
                Toast.makeText(DeleteTpAssignment.this,"Successfully Deleted",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void viewAllFiles() {

        listDataHeader = new ArrayList<>();
        reeef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                name = dataSnapshot.child("Name").getValue().toString();
                databaseReference = FirebaseDatabase.getInstance().getReference("Assignment").child(year2).child(dep).child(name);
                databaseReference.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        final String headertitile = dataSnapshot.getKey();
                        listDataHeader.add(headertitile);

                        String[] uploads = new String[listDataHeader.size()];
                        for(int i=0; i<uploads.length;i++){
                            uploads[i] = listDataHeader.get(i);
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.rowlayout,R.id.txt_lan,uploads);
                        /*{

                            @Override
                            public View getView(int position, View convertView, ViewGroup parent) {

                                View view = super.getView(position, convertView, parent);

                                TextView myText = (TextView)view.findViewById(android.R.id.text1);
                                myText.setTextColor(Color.WHITE);
                                myText.setTextSize(20);

                                return view;
                            }
                        };*/
                        ListForm.setAdapter(adapter);
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