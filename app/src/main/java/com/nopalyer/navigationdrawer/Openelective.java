package com.nopalyer.navigationdrawer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class Openelective extends Activity {

    Spinner spin1,spin2,spin3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_openelective);
        spin1 = findViewById(R.id.spin_1);
        spin2 = findViewById(R.id.spin_2);
        spin3 = findViewById(R.id.spin_3);

        final String divisions[] =  {
                "--1st Preference--",
                "CSE","ECE","Mechanical","Electrical","Chemical","Civil"

        };
        final String Electrical[] ={
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


        final ArrayAdapter<String> adapter;
        adapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,divisions);

        spin1.setAdapter(adapter);

        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedDivision = divisions[i];
                if(i==0){
                    final ArrayAdapter<String> adapter4;
                    adapter4= new ArrayAdapter<String>(Openelective.this,android.R.layout.simple_spinner_dropdown_item,select);
                    spin2.setAdapter(adapter4);
                }
                if(i==1){
                    final ArrayAdapter<String> adapter4;
                    adapter4= new ArrayAdapter<String>(Openelective.this,android.R.layout.simple_spinner_dropdown_item,CSE);
                    spin2.setAdapter(adapter4);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            String upozalla = CSE[i];
                            if(i==0){
                                ArrayAdapter<String> adapter2;
                                adapter2 = new ArrayAdapter<String>(Openelective.this,android.R.layout.simple_spinner_dropdown_item,select1);
                                spin3.setAdapter(adapter2);
                            }
                            if(i==1){
//
                                ArrayAdapter<String> adapter2;
                                adapter2 = new ArrayAdapter<String>(Openelective.this,android.R.layout.simple_spinner_dropdown_item,select1);
                                spin3.setAdapter(adapter2);
                                spin3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        String service = CSE[i];
                                        Toast.makeText(Openelective.this, ""+service, Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {

                                    }
                                });

                            }

                            Toast.makeText(Openelective.this, ""+upozalla, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
                if(i==2){
                    final ArrayAdapter<String> adapter5;
                    adapter5= new ArrayAdapter<String>(Openelective.this,android.R.layout.simple_spinner_dropdown_item,ECE);
                    spin2.setAdapter(adapter5);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            String upozalla = ECE[i];
                            if(i==0){
                                ArrayAdapter<String> adapter2;
                                adapter2 = new ArrayAdapter<String>(Openelective.this,android.R.layout.simple_spinner_dropdown_item,select3);
                                spin3.setAdapter(adapter2);
                            }                     }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
                if(i==3){
                    final ArrayAdapter<String> adapter5;
                    adapter5= new ArrayAdapter<String>(Openelective.this,android.R.layout.simple_spinner_dropdown_item,Mechanical);
                    spin2.setAdapter(adapter5);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            String upozalla = Mechanical[i];
                            if(i==0){
                                ArrayAdapter<String> adapter2;
                                adapter2 = new ArrayAdapter<String>(Openelective.this,android.R.layout.simple_spinner_dropdown_item,select4);
                                spin3.setAdapter(adapter2);
                            }                      }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }if(i==4){
                    final ArrayAdapter<String> adapter5;
                    adapter5= new ArrayAdapter<String>(Openelective.this,android.R.layout.simple_spinner_dropdown_item,Electrical);
                    spin2.setAdapter(adapter5);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            String upozalla = Electrical[i];
                            if(i==0){
                                ArrayAdapter<String> adapter2;
                                adapter2 = new ArrayAdapter<String>(Openelective.this,android.R.layout.simple_spinner_dropdown_item,select5);
                                spin3.setAdapter(adapter2);
                            }                   }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }if(i==5){
                    final ArrayAdapter<String> adapter5;
                    adapter5= new ArrayAdapter<String>(Openelective.this,android.R.layout.simple_spinner_dropdown_item,Chemical);
                    spin2.setAdapter(adapter5);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            String upozalla = Chemical[i];
                            if(i==0){
                                ArrayAdapter<String> adapter2;
                                adapter2 = new ArrayAdapter<String>(Openelective.this,android.R.layout.simple_spinner_dropdown_item,select6);
                                spin3.setAdapter(adapter2);
                            }                  }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }

                if(i==6){
                    final ArrayAdapter<String> adapter5;
                    adapter5= new ArrayAdapter<String>(Openelective.this,android.R.layout.simple_spinner_dropdown_item,Civil);
                    spin2.setAdapter(adapter5);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            String upozalla = Civil[i];
                            if(i==0){
                                ArrayAdapter<String> adapter2;
                                adapter2 = new ArrayAdapter<String>(Openelective.this,android.R.layout.simple_spinner_dropdown_item,select7);
                                spin3.setAdapter(adapter2);
                            }                     }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }

                Toast.makeText(Openelective.this, ""+selectedDivision, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
//

    }
}
