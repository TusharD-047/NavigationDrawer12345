package com.nopalyer.navigationdrawer;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.nopalyer.navigationdrawer.teacher.tpassign;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Bonafide_Application extends AppCompatActivity {

    private EditText b1, b2, b3, b4, b5,b6;
    private Button bsdg1;
    private Spinner bS1, bS2, bS3, bS4;
    private Button sub;
    //private CheckBox cb1,cb2,cb3,cb4,cb5,cb6,cb7,cb8;
    public static String prog, dep, sem, doc;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    List<String> listDataHeader, list;
    AwesomeValidation awesomeValidation;
    ArrayList<String> selectedItems = new ArrayList<>();
    CheckedTextView c;
    // private Button show;
    ArrayAdapter<String> adapter_programme, adapter_department1,adapter_department2,adapter_department3,adapter_department4, adapter_sem1,adapter_sem2,adapter_sem3,adapter_doc;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ver_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.verlogout:
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(Bonafide_Application.this, login.class));
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonafide__application);
        sub = findViewById(R.id.bonSub);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        bS1 = (Spinner) findViewById(R.id.bS1);
        bS2 = (Spinner) findViewById(R.id.bS2);
        bS3 = (Spinner) findViewById(R.id.bS3);
        bS3 = (Spinner) findViewById(R.id.bS3);
        bS4 = (Spinner) findViewById(R.id.bS4);

        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        b4 = findViewById(R.id.b4);
        b5 = findViewById(R.id.b5);
        b6 = findViewById(R.id.b6);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this, R.id.b1, RegexTemplate.NOT_EMPTY, R.string.invalid_name);
        awesomeValidation.addValidation(this, R.id.b2, RegexTemplate.NOT_EMPTY, R.string.invalid_roll);
        awesomeValidation.addValidation(this, R.id.b3, "[5-9]{1}[0-9]{9}$", R.string.invalid_mbl);
        awesomeValidation.addValidation(this, R.id.b4, Patterns.EMAIL_ADDRESS, R.string.invalid_email);


        final String[] programme = {"Choose Programme", "B.Tech", "B.Arch", "Dual Degree","M.tech","MSc","PhD"};
        final String[] department1 = {"Choose Branch","CSE DD","ECE DD"};
        final String[] department2 = {"Choose Branch", "Maths", "Physics", "Chemistry"};
        final String[] department3 = {"Choose Branch", "CSE", "ECE", "Mechanical", "Civil", "Electrical", "Architecture", "Material Science", "Chemical"};
        final String[] department4 = {"Choose Department", "D1", "D2", "D3"};
        final String[] semester1 = {"Choose Semester", "2nd sem", "3rd sem", "4th sem", "5th sem", "6th sem", "7th sem", "8th sem"};
        final String[] semester2 = {"Choose Semester", "2nd sem", "3rd sem", "4th sem", "5th sem", "6th sem", "7th sem", "8th sem", "9th sem", "10th sem"};
        final String[] semester3 = {"Choose Semester", "2nd sem", "3rd sem", "4th sem"};
        final String[] documents = {"Choose Required Document(s)", "Bonafide Certificate", "Duplicate Grade Report Card", "Attested/Verified Copy of Semester Result", "No Objection Certificate", "Character Certificate", "Migration Certificate", "Transcript", "Any Other"};

        adapter_programme = new ArrayAdapter<>(Bonafide_Application.this, R.layout.colourful_spinner_items, programme);
        adapter_programme.setDropDownViewResource(R.layout.colourful_spinner_dropdown);
        bS1.setAdapter(adapter_programme);
        bS1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    //      show.setEnabled(false);
                }
                if (position == 1 || position == 2 || position == 3) {
                    adapter_department1 = new ArrayAdapter<String>(Bonafide_Application.this, R.layout.colourful_spinner_items, department1);
                    adapter_department1.setDropDownViewResource(R.layout.colourful_spinner_dropdown);
                    bS2.setAdapter(adapter_department1);
                    prog = programme[position];
                    bS2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (position == 0) {
                                //  show.setEnabled(false);
                            }
                            if (position == 1 || position == 3 || position == 5 || position == 6 || position == 7 || position == 9 || position == 10) {
                                //   show.setEnabled(true);
                                adapter_sem1 = new ArrayAdapter<String>(Bonafide_Application.this, R.layout.colourful_spinner_items, semester1);
                                adapter_sem1.setDropDownViewResource(R.layout.colourful_spinner_dropdown);
                                bS3.setAdapter(adapter_sem1);
                                dep = department1[position];
                                bS3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        if (position == 0) {
                                            //  show.setEnabled(false);
                                        }
                                        if (position == 1 || position == 2 || position == 3 || position == 4 || position == 5 || position == 6 || position == 7) {
                                            sem = semester1[position];
                                            adapter_doc = new ArrayAdapter<String>(Bonafide_Application.this, R.layout.colourful_spinner_items, documents);
                                            adapter_doc.setDropDownViewResource(R.layout.colourful_spinner_dropdown);
                                            bS4.setAdapter(adapter_doc);
                                            bS4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                @Override
                                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                    if (position == 0) {
                                                        //  show.setEnabled(false);
                                                    }
                                                    if (position == 1 || position == 2 || position == 3 || position == 4 || position == 5 || position == 6 || position == 7 || position == 8) {
                                                        doc = documents[position];
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
                            if (position == 2 || position == 4 || position == 8) {
                                //   show.setEnabled(true);
                                adapter_sem2 = new ArrayAdapter<String>(Bonafide_Application.this, R.layout.colourful_spinner_items, semester2);
                                adapter_sem2.setDropDownViewResource(R.layout.colourful_spinner_dropdown);
                                bS3.setAdapter(adapter_sem2);
                                dep = department1[position];
                                bS3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        if (position == 0) {
                                            //  show.setEnabled(false);
                                        }
                                        if (position == 1 || position == 2 || position == 3 || position == 4 || position == 5 || position == 6 || position == 7 || position == 8 || position == 9) {
                                            sem = semester2[position];
                                            adapter_doc = new ArrayAdapter<String>(Bonafide_Application.this, R.layout.colourful_spinner_items, documents);
                                            adapter_doc.setDropDownViewResource(R.layout.colourful_spinner_dropdown);
                                            bS4.setAdapter(adapter_doc);
                                            bS4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                @Override
                                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                    if (position == 0) {
                                                        //  show.setEnabled(false);
                                                    }
                                                    if (position == 1 || position == 2 || position == 3 || position == 4 || position == 5 || position == 6 || position == 7 || position == 8) {
                                                        doc = documents[position];
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
                }
                if (position == 4) {
                    adapter_department3 = new ArrayAdapter<String>(Bonafide_Application.this, R.layout.colourful_spinner_items, department3);
                    adapter_department3.setDropDownViewResource(R.layout.colourful_spinner_dropdown);
                    bS2.setAdapter(adapter_department3);
                    prog = programme[position];
                    bS2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (position == 0) {
                                //  show.setEnabled(false);
                            }
                            if (position == 1 || position == 2 || position == 3 || position == 4 || position == 5 || position == 6 || position == 7 || position == 8) {
                                adapter_sem3 = new ArrayAdapter<String>(Bonafide_Application.this, R.layout.colourful_spinner_items, semester3);
                                adapter_sem3.setDropDownViewResource(R.layout.colourful_spinner_dropdown);
                                bS3.setAdapter(adapter_sem1);
                                dep = department3[position];
                                bS3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        if (position == 0) {
                                            //  show.setEnabled(false);
                                        }
                                        if (position == 1 || position == 2 || position == 3) {
                                            sem = semester3[position];
                                            adapter_doc = new ArrayAdapter<String>(Bonafide_Application.this, R.layout.colourful_spinner_items, documents);
                                            adapter_doc.setDropDownViewResource(R.layout.colourful_spinner_dropdown);
                                            bS4.setAdapter(adapter_doc);
                                            bS4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                @Override
                                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                    if (position == 0) {
                                                        //  show.setEnabled(false);
                                                    }
                                                    if (position == 1 || position == 2 || position == 3 || position == 4 || position == 5 || position == 6 || position == 7 || position == 8) {
                                                        doc = documents[position];
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
                }

                if (position == 5) {
                    adapter_department2 = new ArrayAdapter<String>(Bonafide_Application.this, R.layout.colourful_spinner_items, department2);
                    adapter_department2.setDropDownViewResource(R.layout.colourful_spinner_dropdown);
                    bS2.setAdapter(adapter_department2);
                    prog = programme[position];
                    bS2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (position == 0) {
                                //  show.setEnabled(false);
                            }
                            if (position == 1 || position == 2 || position == 3) {
                                adapter_sem3 = new ArrayAdapter<String>(Bonafide_Application.this, R.layout.colourful_spinner_items, semester3);
                                adapter_sem3.setDropDownViewResource(R.layout.colourful_spinner_dropdown);
                                bS3.setAdapter(adapter_sem1);
                                dep = department2[position];
                                bS3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        if (position == 0) {
                                            //  show.setEnabled(false);
                                        }
                                        if (position == 1 || position == 2 || position == 3) {
                                            sem = semester3[position];
                                            adapter_doc = new ArrayAdapter<String>(Bonafide_Application.this, R.layout.colourful_spinner_items, documents);
                                            adapter_doc.setDropDownViewResource(R.layout.colourful_spinner_dropdown);
                                            bS4.setAdapter(adapter_doc);
                                            bS4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                @Override
                                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                    if (position == 0) {
                                                        //  show.setEnabled(false);
                                                    }
                                                    if (position == 1 || position == 2 || position == 3 || position == 4 || position == 5 || position == 6 || position == 7 || position == 8) {
                                                        doc = documents[position];
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
                }
                if (position == 6) {
                    adapter_department4 = new ArrayAdapter<String>(Bonafide_Application.this, R.layout.colourful_spinner_items, department4);
                    adapter_department4.setDropDownViewResource(R.layout.colourful_spinner_dropdown);
                    bS2.setAdapter(adapter_department4);
                    prog = programme[position];
                    bS2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (position == 0) {
                                //  show.setEnabled(false);
                            }
                            if (position == 1 || position == 2 || position == 3) {
                                adapter_sem3 = new ArrayAdapter<String>(Bonafide_Application.this, R.layout.colourful_spinner_items, semester3);
                                adapter_sem3.setDropDownViewResource(R.layout.colourful_spinner_dropdown);
                                bS3.setAdapter(adapter_sem1);
                                dep = department4[position];
                                bS3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        if (position == 0) {
                                            //  show.setEnabled(false);
                                        }
                                        if (position == 1 || position == 2 || position == 3) {
                                            sem = semester3[position];
                                            adapter_doc = new ArrayAdapter<String>(Bonafide_Application.this, R.layout.colourful_spinner_items, documents);
                                            adapter_doc.setDropDownViewResource(R.layout.colourful_spinner_dropdown);
                                            bS4.setAdapter(adapter_doc);
                                            bS4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                @Override
                                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                    if (position == 0) {
                                                        //  show.setEnabled(false);
                                                    }
                                                    if (position == 1 || position == 2 || position == 3 || position == 4 || position == 5 || position == 6 || position == 7 || position == 8) {
                                                        doc = documents[position];
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
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // SPINNER ENDS==================================================================================================================================


        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (awesomeValidation.validate() && prog != null && dep != null && sem != null && doc!=null) {
                    Bundle bd = new Bundle();
                    bd.putString("Name", b1.getText().toString());
                    bd.putString("Roll No", b2.getText().toString());
                    bd.putString("Mobile", b3.getText().toString());
                    bd.putString("Email", b4.getText().toString());
                    bd.putString("Programme", prog);
                    bd.putString("dep", dep);
                    bd.putString("sem", sem);
                    bd.putString("type", doc);

                    Intent it = new Intent(Bonafide_Application.this, BonafideView.class);
                    it.putExtras(bd);
                    startActivity(it);
                } else {
                    Toast.makeText(getApplicationContext(), "Fill all the required fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}

