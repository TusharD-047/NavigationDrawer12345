package com.nopalyer.navigationdrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Btech_registration extends AppCompatActivity {

    private EditText academicyr;
    private EditText dob;
    private EditText name;
    private EditText fname;
    private EditText roll;
    private EditText room;
    private EditText emailid;
    private EditText addcoress;
    private EditText peradd;
    private EditText pin1;
    private EditText pin2;
    private EditText mob1;
    private EditText mob2;
    private EditText code1;
    private EditText code2;
    private EditText code3;
    private EditText code4;
    private EditText code5;
    private EditText code6;
    private EditText code7;
    private EditText code8;
    private EditText code9;
    private EditText code10;
    private EditText course1;
    private EditText course2;
    private EditText course3;
    private EditText course4;
    private EditText course5;
    private EditText course6;
    private EditText course7;
    private EditText course8;
    private EditText course9;
    private EditText course10;
    private EditText lab1;
    private EditText lab2;
    private EditText lab3;
    private EditText lab4;
    private EditText lab5;
    private EditText lab6;
    private EditText lab7;
    private EditText lab8;
    private EditText lab9;
    private EditText lab10;
    private EditText credit1;
    private EditText credit2;
    private EditText credit3;
    private EditText credit4;
    private EditText credit5;
    private EditText credit6;
    private EditText credit7;
    private EditText credit8;
    private EditText credit9;
    private EditText credit10;
    private EditText labsum;
    private EditText creditsum;
    private EditText sg1,sg2,sg3,sg4,sg5,sg6,sg7,sg8,sg9,sg10,cg1,cg2,cg3,cg4,cg5,cg6,cg7,cg8,cg9,cg10,rep1,rep2,rep3,rep4,rep5,rep6,rep7,rep8,rep9,rep10;
    final Calendar myCalendar = Calendar.getInstance();
    private Spinner prog,dep,hostt,sem;
    ArrayAdapter<String> adapter_programme,adapter_depp1,adapter_depp2,adapter_depp3,adapter_hostel,adapter_semester,adapter_semester1,adapter_host;
    String progm = "",depp = "",semm = "",host = "";
    int year2;
    private String myname,emailadd,permaadd,coress,rollno,roomno,birth,lab,code,course,credit,father,pincode1,pincode2,phone1,phone2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btech_registration);

        Button submit = (Button) findViewById(R.id.next);
        prog = (Spinner) findViewById(R.id.regsem);
        dep = (Spinner) findViewById(R.id.regdep);
        hostt = (Spinner) findViewById(R.id.reghostel);
        sem = (Spinner) findViewById(R.id.prog);
        academicyr = findViewById(R.id.session);
        name = (EditText) findViewById(R.id.editname);
        fname = (EditText) findViewById(R.id.editfname);
        roll = (EditText) findViewById(R.id.editroll);
        dob = (EditText) findViewById(R.id.editdob);
        dob.setKeyListener(null);
        room = (EditText) findViewById(R.id.editroom);
        emailid = (EditText) findViewById(R.id.emailid);
        addcoress = (EditText) findViewById(R.id.addcorres);
        peradd = (EditText) findViewById(R.id.peradd);
        pin1 = (EditText) findViewById(R.id.pin1);
        pin2 = (EditText) findViewById(R.id.pin2);
        mob1 = (EditText) findViewById(R.id.mob1);
        mob2 = (EditText) findViewById(R.id.mob2);
        code1 = (EditText) findViewById(R.id.code1);
        code2 = (EditText) findViewById(R.id.code2);
        code3 = (EditText) findViewById(R.id.code3);
        code4 = (EditText) findViewById(R.id.code4);
        code5 = (EditText) findViewById(R.id.code5);
        code6 = (EditText) findViewById(R.id.code6);
        code7 = (EditText) findViewById(R.id.code7);
        code8 = (EditText) findViewById(R.id.code8);
        code9 = (EditText) findViewById(R.id.code9);
        code10 = (EditText) findViewById(R.id.code10);
        course1 = (EditText) findViewById(R.id.course1);
        course2 = (EditText) findViewById(R.id.course2);
        course3 = (EditText) findViewById(R.id.course3);
        course4 = (EditText) findViewById(R.id.course4);
        course5 = (EditText) findViewById(R.id.course5);
        course6 = (EditText) findViewById(R.id.course6);
        course7 = (EditText) findViewById(R.id.course7);
        course8 = (EditText) findViewById(R.id.course8);
        course9 = (EditText) findViewById(R.id.course9);
        course10 = (EditText) findViewById(R.id.course10);
        lab1 = (EditText) findViewById(R.id.lab1);
        lab2 = (EditText) findViewById(R.id.lab2);
        lab3 = (EditText) findViewById(R.id.lab3);
        lab4 = (EditText) findViewById(R.id.lab4);
        lab5 = (EditText) findViewById(R.id.lab5);
        lab6 = (EditText) findViewById(R.id.lab6);
        lab7 = (EditText) findViewById(R.id.lab7);
        lab8 = (EditText) findViewById(R.id.lab8);
        lab9 = (EditText) findViewById(R.id.lab9);
        lab10 = (EditText) findViewById(R.id.lab10);
        labsum = findViewById(R.id.labsum);
        credit1 = (EditText) findViewById(R.id.credit1);
        credit2 = (EditText) findViewById(R.id.credit2);
        credit3 = (EditText) findViewById(R.id.credit3);
        credit4 = (EditText) findViewById(R.id.credit4);
        credit5 = (EditText) findViewById(R.id.credit5);
        credit6 = (EditText) findViewById(R.id.credit6);
        credit7 = (EditText) findViewById(R.id.credit7);
        credit8 = (EditText) findViewById(R.id.credit8);
        credit9 = (EditText) findViewById(R.id.credit9);
        credit10 = (EditText) findViewById(R.id.credit10);
        creditsum = (EditText) findViewById(R.id.creditsum);
        sg1 = (EditText) findViewById(R.id.sg1);
        sg2 = (EditText) findViewById(R.id.sg2);
        sg3 = (EditText) findViewById(R.id.sg3);
        sg4 = (EditText) findViewById(R.id.sg4);
        sg5 = (EditText) findViewById(R.id.sg5);
        sg6 = (EditText) findViewById(R.id.sg6);
        sg7 = (EditText) findViewById(R.id.sg7);
        sg8 = (EditText) findViewById(R.id.sg8);
        sg9 = (EditText) findViewById(R.id.sg9);
        cg1 = (EditText) findViewById(R.id.cg1);
        cg2 = (EditText) findViewById(R.id.cg2);
        cg3 = (EditText) findViewById(R.id.cg3);
        cg4 = (EditText) findViewById(R.id.cg4);
        cg5 = (EditText) findViewById(R.id.cg5);
        cg6 = (EditText) findViewById(R.id.cg6);
        cg7 = (EditText) findViewById(R.id.cg7);
        cg8 = (EditText) findViewById(R.id.cg8);
        cg9 = (EditText) findViewById(R.id.cg9);
        rep1 = (EditText) findViewById(R.id.rep1);
        rep2 = (EditText) findViewById(R.id.rep2);
        rep3 = (EditText) findViewById(R.id.rep3);
        rep4 = (EditText) findViewById(R.id.rep4);
        rep5 = (EditText) findViewById(R.id.rep5);
        rep6 = (EditText) findViewById(R.id.rep6);
        rep7 = (EditText) findViewById(R.id.rep7);
        rep8 = (EditText) findViewById(R.id.rep8);
        rep9 = (EditText) findViewById(R.id.rep9);




        final String[] programme = {"Choose Programme", "B.Tech", "B.Arch", "Dual Degree"};
        final String[] department1 = {"Choose Department", "CSE", "ECE", "Mechanical", "Civil", "Electrical", "Material Science", "Chemical"};
        final String[] department2 = {"Choose Department", "Architecture"};
        final String[] department3 = {"Choose Department", "CSE DD", "ECE DD"};
        final String[] semester1 = {"Choose Semester", "2nd sem", "3rd sem", "4th sem", "5th sem", "6th sem", "7th sem", "8th sem"};
        final String[] semester2 = {"Choose Semester", "2nd sem", "3rd sem", "4th sem", "5th sem", "6th sem", "7th sem", "8th sem", "9th sem", "10th sem"};
        final String[] hostel = {"Choose Hostel", "Ambika Girls Hostel", "Kailash Boys Hostel", "Himadri Boys Hostel","Udaygiri Boys Hostel","Neelkanth Boys Hostel","Dhauladhar Boys Hostel","Vindhyachal Boys Hostel","Shivalik Boys Hostel","Parvati Girls Hostel","Mani-Mahesh Girls Hostel","Aravali Girls Hostel"};

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                year2 = year;
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        adapter_programme = new ArrayAdapter<>(Btech_registration.this, R.layout.colourful_spinner_items2, programme);
        adapter_programme.setDropDownViewResource(R.layout.colourful_spinner_dropdown2);
        prog.setAdapter(adapter_programme);
        prog.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    //      show.setEnabled(false);
                }
                if (position == 1){
                    progm = programme[position];
                    adapter_depp1 = new ArrayAdapter<>(Btech_registration.this, R.layout.colourful_spinner_items2, department1);
                    adapter_depp1.setDropDownViewResource(R.layout.colourful_spinner_dropdown2);
                    dep.setAdapter(adapter_depp1);
                    dep.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (position == 0) {
                                //      show.setEnabled(false);
                            }
                            if (position == 1 || position == 2 || position == 3 || position == 4 || position == 5 || position == 6 || position == 7) {
                                //   show.setEnabled(true);
                                adapter_semester = new ArrayAdapter<String>(Btech_registration.this, R.layout.colourful_spinner_items2, semester1);
                                adapter_semester.setDropDownViewResource(R.layout.colourful_spinner_dropdown2);
                                sem.setAdapter(adapter_semester);
                                depp = department1[position];
                                sem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        if (position == 0) {
                                            //  show.setEnabled(false);
                                        }
                                        if (position == 1 || position == 2 || position == 3 || position == 4 || position == 5 || position == 6 || position == 7) {
                                            semm = semester1[position];
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
                    adapter_depp2 = new ArrayAdapter<String>(Btech_registration.this, R.layout.colourful_spinner_items2, department2);
                    adapter_depp2.setDropDownViewResource(R.layout.colourful_spinner_dropdown2);
                    dep.setAdapter(adapter_depp2);
                    progm= programme[position];
                    dep.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (position == 0) {
                                //  show.setEnabled(false);
                            }
                            if (position == 1 ) {
                                adapter_semester1 = new ArrayAdapter<String>(Btech_registration.this, R.layout.colourful_spinner_items2, semester2);
                                adapter_semester1.setDropDownViewResource(R.layout.colourful_spinner_dropdown2);
                                sem.setAdapter(adapter_semester1);
                                depp= department2[position];
                                sem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        if (position == 0) {
                                            //  show.setEnabled(false);
                                        }
                                        if (position == 1 || position == 2 || position == 3 || position == 4 || position == 5 || position == 6 || position == 7|| position == 8 || position == 9) {
                                            semm = semester2[position];

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
                    adapter_depp3 = new ArrayAdapter<String>(Btech_registration.this, R.layout.colourful_spinner_items2, department3);
                    adapter_depp3.setDropDownViewResource(R.layout.colourful_spinner_dropdown2);
                    dep.setAdapter(adapter_depp3);
                    progm = programme[position];
                    dep.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (position == 0) {
                                //  show.setEnabled(false);
                            }
                            if (position == 1 || position == 2 ) {
                                adapter_semester1 = new ArrayAdapter<String>(Btech_registration.this, R.layout.colourful_spinner_items2, semester2);
                                adapter_semester1.setDropDownViewResource(R.layout.colourful_spinner_dropdown2);
                                sem.setAdapter(adapter_semester1);
                                depp = department2[position];
                                sem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        if (position == 0) {
                                            //  show.setEnabled(false);
                                        }
                                        if (position == 1 || position == 2 || position == 3 || position == 4 || position == 5 || position == 6 || position == 7|| position == 8 || position == 9) {
                                            semm = semester2[position];

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
        adapter_host = new ArrayAdapter<String>(Btech_registration.this, R.layout.colourful_spinner_items2, hostel);
        adapter_host.setDropDownViewResource(R.layout.colourful_spinner_dropdown2);
        hostt.setAdapter(adapter_host);
        hostt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    //  show.setEnabled(false);
                }
                if (position == 1 || position == 2 || position == 3 || position == 4 || position == 5 || position == 6 || position == 7|| position == 8 || position == 9 || position == 10||position==11) {
                    host = hostel[position];

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//Spinner ends .................... :> .... :)

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Btech_registration.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initialize();
                Calendar calendar = Calendar.getInstance();
                int yr = calendar.get(Calendar.YEAR);
                int a = yr-year2;
                if (validate()){
                    if (a< 15){
                        dob.setError("Age Too Short");
                    }else if (host.isEmpty() || semm.isEmpty() || depp.isEmpty() || progm.isEmpty()){
                        Toast.makeText(Btech_registration.this,"Fill all details in dropdown",Toast.LENGTH_SHORT).show();
                    } else{
                        Bundle bd = new Bundle();
                        bd.putString("Name", name.getText().toString());
                        bd.putString("FatherName", fname.getText().toString());
                        bd.putString("RollNo", roll.getText().toString());
                        bd.putString("Date Of Birth", dob.getText().toString());
                        bd.putString("Email Address", emailid.getText().toString());
                        bd.putString("Academic Year", academicyr.getText().toString());
                        bd.putString("Room", room.getText().toString());
                        bd.putString("Prog", progm);
                        bd.putString("Dep", depp);
                        bd.putString("Coress", coress);
                        bd.putString("Mobile No. 1",phone1);
                        bd.putString("pin1", pincode1);
                        bd.putString("perma", permaadd);
                        bd.putString("Mobile No. 2",phone2);
                        bd.putString("pin2", pincode2);
                        bd.putString("sem", semm);
                        bd.putString("hostel", host);
                        bd.putString("code1", code1.getText().toString());
                        bd.putString("course1", course1.getText().toString());
                        bd.putString("lab1", lab1.getText().toString());
                        bd.putString("credit1", credit1.getText().toString());
                        bd.putString("code2", code2.getText().toString());
                        bd.putString("course2", course2.getText().toString());
                        bd.putString("lab2", lab2.getText().toString());
                        bd.putString("credit2", credit2.getText().toString());
                        bd.putString("code3", code3.getText().toString());
                        bd.putString("course3", course3.getText().toString());
                        bd.putString("lab3", lab3.getText().toString());
                        bd.putString("credit3", credit3.getText().toString());
                        bd.putString("code4", code4.getText().toString());
                        bd.putString("course4", course4.getText().toString());
                        bd.putString("lab4", lab4.getText().toString());
                        bd.putString("credit4", credit4.getText().toString());
                        bd.putString("code5", code5.getText().toString());
                        bd.putString("course5", course5.getText().toString());
                        bd.putString("lab5", lab5.getText().toString());
                        bd.putString("credit5", credit5.getText().toString());
                        bd.putString("code6", code6.getText().toString());
                        bd.putString("course6", course6.getText().toString());
                        bd.putString("lab6", lab6.getText().toString());
                        bd.putString("credit6", credit6.getText().toString());
                        bd.putString("code7", code7.getText().toString());
                        bd.putString("course7", course7.getText().toString());
                        bd.putString("lab7", lab7.getText().toString());
                        bd.putString("credit7", credit7.getText().toString());
                        bd.putString("code8", code8.getText().toString());
                        bd.putString("course8", course8.getText().toString());
                        bd.putString("lab8", lab8.getText().toString());
                        bd.putString("credit8", credit8.getText().toString());
                        bd.putString("code9", code9.getText().toString());
                        bd.putString("course9", course9.getText().toString());
                        bd.putString("lab9", lab9.getText().toString());
                        bd.putString("credit9", credit9.getText().toString());
                        bd.putString("code10", code10.getText().toString());
                        bd.putString("course10", course10.getText().toString());
                        bd.putString("lab10", lab10.getText().toString());
                        bd.putString("credit10", credit10.getText().toString());
                        bd.putString("labsum", labsum.getText().toString());
                        bd.putString("creditsum", creditsum.getText().toString());
                        bd.putString("cg1", cg1.getText().toString());
                        bd.putString("sg1", sg1.getText().toString());
                        bd.putString("rep1", rep1.getText().toString());
                        bd.putString("cg2", cg2.getText().toString());
                        bd.putString("sg2", sg2.getText().toString());
                        bd.putString("rep2", rep2.getText().toString());
                        bd.putString("cg3", cg3.getText().toString());
                        bd.putString("sg3", sg3.getText().toString());
                        bd.putString("rep3", rep3.getText().toString());
                        bd.putString("cg4", cg4.getText().toString());
                        bd.putString("sg4", sg4.getText().toString());
                        bd.putString("rep4", rep4.getText().toString());
                        bd.putString("cg5", cg5.getText().toString());
                        bd.putString("sg5", sg5.getText().toString());
                        bd.putString("rep5", rep5.getText().toString());
                        bd.putString("cg6", cg6.getText().toString());
                        bd.putString("sg6", sg6.getText().toString());
                        bd.putString("rep6", rep6.getText().toString());
                        bd.putString("cg7", cg7.getText().toString());
                        bd.putString("sg7", sg7.getText().toString());
                        bd.putString("rep7", rep7.getText().toString());
                        bd.putString("cg8", cg8.getText().toString());
                        bd.putString("sg8", sg8.getText().toString());
                        bd.putString("rep8", rep8.getText().toString());
                        bd.putString("cg9", cg9.getText().toString());
                        bd.putString("sg9", sg9.getText().toString());
                        bd.putString("rep9", rep9.getText().toString());
                        bd.putString("type","UG");

                        Intent it = new Intent(Btech_registration.this,ViewActivity.class);
                        it.putExtras(bd);
                        startActivity(it);
                    }
                }

            }
        });
    }
     /* private void register(){
        initialize ();
        if(!validate()){
            Toast.makeText(this,"register failed",Toast.LENGTH_SHORT).show();
        }
        else {
            onRegisterSuccess();
        }
      }
public void onRegisterSuccess(){
        //todo
}*/
    public boolean validate (){
        boolean valid = true;
        if(myname.isEmpty()||name.length()>32){
            name.setError("Please enter valid name");
            valid=false;
        }
        if(emailadd.isEmpty()){
            emailid.setError("please enter email address");
            valid=false;
        }
        if(birth.isEmpty()){
            dob.setError("please enter DOB");
            valid=false;
        }
        if(rollno.isEmpty()){
            roll.setError("please enter roll no.");
            valid=false;
        }
        if(roomno.isEmpty()){
            room.setError("please enter room no.");
            valid=false;
        }
        if(father.isEmpty()){
            fname.setError("please enter name");
            valid=false;
        }
        if(phone1.isEmpty()){
            mob1.setError("enter mobile no.");
            valid=false;
        }if(phone2.isEmpty()){
            mob2.setError("enter mobile no.");
            valid=false;
        }if(pincode1.isEmpty()){
            pin1.setError("enter pincode");
            valid=false;
        }if(pincode2.isEmpty()){
            pin2.setError("enter pincode");
            valid=false;
        }if(permaadd.isEmpty()){
            peradd.setError("enter Address");
            valid=false;
        }if(coress.isEmpty()){
            addcoress.setError("enter Address");
            valid=false;
        }if(academicyr.getText().toString().isEmpty()){
            academicyr.setError("enter Academic year");
            valid=false;
        }if(cg1.getText().toString().isEmpty()){
            cg1.setError("enter Cgpa");
            valid=false;
        }if(sg1.getText().toString().isEmpty()){
            sg1.setError("enter Sgpa");
            valid=false;
        }
        return valid;
    }
    private void initialize() {
        myname = name.getText().toString().trim();
        emailadd = emailid.getText().toString().trim();
        coress = addcoress.getText().toString().trim();
        permaadd = peradd.getText().toString().trim();
        birth = dob.getText().toString().trim();
        rollno = roll.getText().toString().trim();
        roomno = room.getText().toString().trim();
        father = fname.getText().toString().trim();
        pincode1 = pin1.getText().toString().trim();
        pincode2 = pin2.getText().toString().trim();
        phone1 = mob1.getText().toString().trim();
        phone2 = mob2.getText().toString().trim();
        code = code1.getText().toString().trim();
        course = course1.getText().toString().trim();

    }


    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dob.setText(sdf.format(myCalendar.getTime()));
    }
}
