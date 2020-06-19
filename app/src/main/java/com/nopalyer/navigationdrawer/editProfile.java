package com.nopalyer.navigationdrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class editProfile extends AppCompatActivity {
    private ImageView profile;
    private TextView department,email,name1;
    private EditText roll,name,contact;
    private CheckBox note;
    ProgressDialog pd,pd1;
    private static int PICK_IMAGE = 123;
    StorageReference storageReference;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseStorage firebaseStorage;
    Spinner ES1,ES2,ES0;
    SharedPreferences sharedprefs,sharedPreferences2;
    SharedPreferences.Editor editor,editor2;
    private Button save;
    String progm,depp,year;
    ArrayAdapter<String> adapter_programme, adapter_department1,adapter_department2,adapter_department3,adapter_department4,adapter_department5,adapter_department6, adapter_sem1,adapter_sem2,adapter_sem3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        ES0=(Spinner)findViewById(R.id.ES0);
        ES1=(Spinner)findViewById(R.id.ES1);
        ES2=(Spinner)findViewById(R.id.ES2);
        name = (EditText)findViewById(R.id.nameedit);
        contact = (EditText)findViewById(R.id.editCon);
        profile = (ImageView)findViewById(R.id.profileedit);
        roll = (EditText) findViewById(R.id.rolledit);
        save = findViewById(R.id.Ebutton);
        note =(CheckBox) findViewById(R.id.EC);

        final String[] programme = {"Choose Programme", "B.Tech", "B.Arch", "Dual Degree", "M.tech", "M.Arch", "MBA", "MSc", "PhD"};
        final String[] department1 = {"Choose Department", "CSE", "ECE", "Mechanical", "Civil", "Electrical", "Material Science", "Chemical"};
        final String[] department2 = {"Choose Department", "Mathematics and Computing", "Physics", "Chemistry"};
        final String[] department3 = {"Choose Department", "D1", "D2", "D3"};
        final String[] department4 = {"Choose Department", "Architecture"};
        final String[] department5 = {"Choose Department", "CSE DD", "ECE DD"};
        final String[] department6 = {"Choose Department", "Management Studies"};
        final String[] year1 = {"Choose Year","1st year","2nd year","3rd year","4th year"};
        final String[] year3 = {"Choose Year","1st year","2nd year"};
        final String[] year2 = {"Choose Year","1st year","2nd year","3rd year","4th year","5th year"};

        adapter_programme = new ArrayAdapter<>(editProfile.this, R.layout.colourful_spinner_items, programme);
        adapter_programme.setDropDownViewResource(R.layout.colourful_spinner_dropdown);
        ES0.setAdapter(adapter_programme);
        ES0.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    //      show.setEnabled(false);
                }
                if (position == 1){
                    progm = programme[position];
                    adapter_department1 = new ArrayAdapter<>(editProfile.this, R.layout.colourful_spinner_items, department1);
                    adapter_department1.setDropDownViewResource(R.layout.colourful_spinner_dropdown);
                    ES2.setAdapter(adapter_department1);
                    ES2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (position == 0) {
                                //      show.setEnabled(false);
                            }
                            if (position == 1 || position == 2 || position == 3 || position == 4 || position == 5 || position == 6 || position == 7) {
                                //   show.setEnabled(true);
                                adapter_sem1 = new ArrayAdapter<String>(editProfile.this, R.layout.colourful_spinner_items, year1);
                                adapter_sem1.setDropDownViewResource(R.layout.colourful_spinner_dropdown);
                                ES1.setAdapter(adapter_sem1);
                                depp = department1[position];
                                ES1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        if (position == 0) {
                                            //  show.setEnabled(false);
                                        }
                                        if (position == 1 || position == 2 || position == 3 || position == 4) {
                                            year = year1[position];
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
                }
                if (position == 2) {
                    adapter_department4 = new ArrayAdapter<String>(editProfile.this, R.layout.colourful_spinner_items, department4);
                    adapter_department4.setDropDownViewResource(R.layout.colourful_spinner_dropdown);
                    ES2.setAdapter(adapter_department4);
                    progm= programme[position];
                    ES2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (position == 0) {
                                //  show.setEnabled(false);
                            }
                            if (position == 1 ) {
                                adapter_sem2 = new ArrayAdapter<String>(editProfile.this, R.layout.colourful_spinner_items, year2);
                                adapter_sem2.setDropDownViewResource(R.layout.colourful_spinner_dropdown);
                                ES1.setAdapter(adapter_sem2);
                                depp= department4[position];
                                ES1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        if (position == 0) {
                                            //  show.setEnabled(false);
                                        }
                                        if (position == 1 || position == 2 || position == 3 || position == 4 || position == 5 ) {
                                            year = year2[position];

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
                }

                if (position == 3) {
                    adapter_department5 = new ArrayAdapter<String>(editProfile.this, R.layout.colourful_spinner_items, department5);
                    adapter_department5.setDropDownViewResource(R.layout.colourful_spinner_dropdown);
                    ES2.setAdapter(adapter_department5);
                    progm = programme[position];
                    ES2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (position == 0) {
                                //  show.setEnabled(false);
                            }
                            if (position == 1 || position == 2 ) {
                                adapter_sem2 = new ArrayAdapter<String>(editProfile.this, R.layout.colourful_spinner_items, year2);
                                adapter_sem2.setDropDownViewResource(R.layout.colourful_spinner_dropdown);
                                ES1.setAdapter(adapter_sem2);
                                depp = department5[position];
                                ES1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        if (position == 0) {
                                            //  show.setEnabled(false);
                                        }
                                        if (position == 1 || position == 2 || position == 3 || position == 4 || position == 5) {
                                            year = year2[position];

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
                }
                if (position == 4){
                    progm = programme[position];
                    adapter_department1 = new ArrayAdapter<>(editProfile.this, R.layout.colourful_spinner_items, department1);
                    adapter_department1.setDropDownViewResource(R.layout.colourful_spinner_dropdown);
                    ES2.setAdapter(adapter_department1);
                    ES2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (position == 0) {
                                //      show.setEnabled(false);
                            }
                            if (position == 1 || position == 2 || position == 3 || position == 4 || position == 5 || position == 6 || position == 7) {
                                //   show.setEnabled(true);
                                adapter_sem1 = new ArrayAdapter<String>(editProfile.this, R.layout.colourful_spinner_items, year3);
                                adapter_sem1.setDropDownViewResource(R.layout.colourful_spinner_dropdown);
                                ES1.setAdapter(adapter_sem1);
                                depp = department1[position];
                                ES1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        if (position == 0) {
                                            //  show.setEnabled(false);
                                        }
                                        if (position == 1 || position == 2) {
                                            year = year3[position];
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
                }
                if (position == 5) {
                    adapter_department4 = new ArrayAdapter<String>(editProfile.this, R.layout.colourful_spinner_items, department4);
                    adapter_department4.setDropDownViewResource(R.layout.colourful_spinner_dropdown);
                    ES2.setAdapter(adapter_department4);
                    progm= programme[position];
                    ES2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (position == 0) {
                                //  show.setEnabled(false);
                            }
                            if (position == 1 ) {
                                adapter_sem3 = new ArrayAdapter<String>(editProfile.this, R.layout.colourful_spinner_items, year3);
                                adapter_sem3.setDropDownViewResource(R.layout.colourful_spinner_dropdown);
                                ES1.setAdapter(adapter_sem3);
                                depp= department4[position];
                                ES1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        if (position == 0) {
                                            //  show.setEnabled(false);
                                        }
                                        if (position == 1 || position == 2  ) {
                                            year = year3[position];

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
                }

                if (position == 6) {
                    adapter_department6 = new ArrayAdapter<String>(editProfile.this, R.layout.colourful_spinner_items, department6);
                    adapter_department6.setDropDownViewResource(R.layout.colourful_spinner_dropdown);
                    ES2.setAdapter(adapter_department6);
                    progm = programme[position];
                    ES2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (position == 0) {
                                //  show.setEnabled(false);
                            }
                            if (position == 1) {
                                adapter_sem2 = new ArrayAdapter<String>(editProfile.this, R.layout.colourful_spinner_items, year3);
                                adapter_sem2.setDropDownViewResource(R.layout.colourful_spinner_dropdown);
                                ES1.setAdapter(adapter_sem2);
                                depp = department6[position];
                                ES1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        if (position == 0) {
                                            //  show.setEnabled(false);
                                        }
                                        if (position == 1 || position == 2 ) {
                                            year = year3[position];

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
                }
                if (position == 7) {
                    adapter_department2 = new ArrayAdapter<String>(editProfile.this, R.layout.colourful_spinner_items, department2);
                    adapter_department2.setDropDownViewResource(R.layout.colourful_spinner_dropdown);
                    ES2.setAdapter(adapter_department2);
                    progm= programme[position];
                    ES2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (position == 0) {
                                //  show.setEnabled(false);
                            }
                            if (position == 1 ||position == 2 || position == 3 ) {
                                adapter_sem3 = new ArrayAdapter<String>(editProfile.this, R.layout.colourful_spinner_items, year3);
                                adapter_sem3.setDropDownViewResource(R.layout.colourful_spinner_dropdown);
                                ES1.setAdapter(adapter_sem3);
                                depp= department2[position];
                                ES1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        if (position == 0) {
                                            //  show.setEnabled(false);
                                        }
                                        if (position == 1 || position == 2  ) {
                                            year = year3[position];

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
                }

                if (position == 8) {
                    adapter_department3 = new ArrayAdapter<String>(editProfile.this, R.layout.colourful_spinner_items, department3);
                    adapter_department3.setDropDownViewResource(R.layout.colourful_spinner_dropdown);
                    ES2.setAdapter(adapter_department3);
                    progm = programme[position];
                    ES2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (position == 0) {
                                //  show.setEnabled(false);
                            }
                            if (position == 1||position == 2 || position == 3) {
                                adapter_sem2 = new ArrayAdapter<String>(editProfile.this, R.layout.colourful_spinner_items, year3);
                                adapter_sem2.setDropDownViewResource(R.layout.colourful_spinner_dropdown);
                                ES1.setAdapter(adapter_sem2);
                                depp = department3[position];
                                ES1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        if (position == 0) {
                                            //  show.setEnabled(false);
                                        }
                                        if (position == 1 || position == 2 ) {
                                            year = year3[position];

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
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



//Spinner ends .................... :> .... :)

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(note.isChecked()&&progm != null && depp != null && year != null ){
                    Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), " Please fill all the required fields", Toast.LENGTH_SHORT).show();
                }
            }
        });











































































}
}