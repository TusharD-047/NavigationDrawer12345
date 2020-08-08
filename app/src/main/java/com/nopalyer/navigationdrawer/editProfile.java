package com.nopalyer.navigationdrawer;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.nopalyer.navigationdrawer.Login.verification;
import com.nopalyer.navigationdrawer.profile.studentp;
import com.nopalyer.navigationdrawer.student.StudentsPage;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

public class editProfile extends AppCompatActivity {
    private ImageView profile;
    private TextView department,email,name1,samuh;
    private EditText roll,name,contact;
    private CheckBox note;
    ProgressDialog pd,pd1;
    private static int PICK_IMAGE = 123;
    StorageReference storageReference;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,ref;
    FirebaseStorage firebaseStorage;
    Spinner ES1,ES2,ES0,ES3;
    SharedPreferences sharedprefs,sharedPreferences2;
    SharedPreferences.Editor editor,editor2;
    private Button save;
    String progm,depp,year,grp;
    ArrayAdapter<String> adapter_programme, adapter_department1,adapter_department2,adapter_department3,adapter_department4,adapter_department5,adapter_department6, adapter_sem1,adapter_sem2,adapter_sem3,adapter_grp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        ES0=(Spinner)findViewById(R.id.ES0);
        ES1=(Spinner)findViewById(R.id.ES1);
        ES2=(Spinner)findViewById(R.id.ES2);
        ES3=(Spinner)findViewById(R.id.ES3);
        name = (EditText)findViewById(R.id.nameedit);
        contact = (EditText)findViewById(R.id.editCon);
        profile = (ImageView)findViewById(R.id.profileedit);
        roll = (EditText) findViewById(R.id.rolledit);
        save = findViewById(R.id.Ebutton);
        note =(CheckBox) findViewById(R.id.EC);
        email = findViewById(R.id.editemail);
        samuh = findViewById(R.id.samuh);

        pd = new ProgressDialog(this);
        pd1 = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        storageReference = firebaseStorage.getReference();

        final String[] programme = {"Choose Programme", "B.Tech", "B.Arch", "Dual Degree", "M.tech", "M.Arch", "MBA", "MSc", "PhD"};
        final String[] department1 = {"Choose Department", "CSE", "ECE", "Mechanical", "Civil", "Electrical", "Material Science", "Chemical"};
        final String[] department2 = {"Choose Department", "Mathematics and Computing", "Physics", "Chemistry"};
        final String[] department3 = {"Choose Department", "D1", "D2", "D3"};
        final String[] department4 = {"Choose Department", "Architecture"};
        final String[] department5 = {"Choose Department", "CSE-DD", "ECE-DD"};
        final String[] department6 = {"Choose Department", "Management Studies"};
        final String[] year1 = {"Choose Year","1st year","2nd year","3rd year","4th year"};
        final String[] year3 = {"Choose Year","1st year","2nd year"};
        final String[] year2 = {"Choose Year","1st year","2nd year","3rd year","4th year","5th year"};
        final String[] group = {"Choose Group","A","B","C","D","E","F","G","H","I","J"};

        email.setText(user.getEmail());

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
                                        if (position == 1) {
                                            year = year1[position];
                                            samuh.setVisibility(View.VISIBLE);
                                            ES3.setVisibility(View.VISIBLE);
                                            adapter_grp = new ArrayAdapter<String>(editProfile.this, R.layout.colourful_spinner_items, group);
                                            adapter_grp.setDropDownViewResource(R.layout.colourful_spinner_dropdown);
                                            ES3.setAdapter(adapter_grp);
                                            ES3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                @Override
                                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                    grp = group[position];
                                                }

                                                @Override
                                                public void onNothingSelected(AdapterView<?> parent) {

                                                }
                                            });
                                        }
                                        if (position == 2 || position == 3 || position == 4) {
                                            year = year1[position];
                                            ES3.setVisibility(View.GONE);
                                            samuh.setVisibility(View.GONE);
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
                                        if (position == 1) {
                                            samuh.setVisibility(View.VISIBLE);
                                            ES3.setVisibility(View.VISIBLE);
                                            adapter_grp = new ArrayAdapter<String>(editProfile.this, R.layout.colourful_spinner_items, group);
                                            adapter_grp.setDropDownViewResource(R.layout.colourful_spinner_dropdown);
                                            ES3.setAdapter(adapter_grp);
                                            ES3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                @Override
                                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                    grp = group[position];
                                                }

                                                @Override
                                                public void onNothingSelected(AdapterView<?> parent) {

                                                }
                                            });
                                        }
                                        if (position == 2 || position == 3 || position == 4 || position == 5 ) {
                                            year = year2[position];
                                            samuh.setVisibility(View.GONE);
                                            ES3.setVisibility(View.GONE);
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
                                        if (position == 1) {
                                            adapter_grp = new ArrayAdapter<String>(editProfile.this, R.layout.colourful_spinner_items, group);
                                            adapter_grp.setDropDownViewResource(R.layout.colourful_spinner_dropdown);
                                            ES3.setVisibility(View.VISIBLE);
                                            samuh.setVisibility(View.VISIBLE);
                                            ES3.setAdapter(adapter_grp);
                                            ES3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                @Override
                                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                    grp = group[position];
                                                }

                                                @Override
                                                public void onNothingSelected(AdapterView<?> parent) {

                                                }
                                            });
                                        }
                                        if (position == 2 || position == 3 || position == 4 || position == 5) {
                                            year = year2[position];
                                            samuh.setVisibility(View.GONE);
                                            ES3.setVisibility(View.GONE);
                                            ES3.setEnabled(false);
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
                    samuh.setVisibility(View.GONE);
                    ES3.setVisibility(View.GONE);
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
                    ES3.setVisibility(View.GONE);
                    samuh.setVisibility(View.GONE);
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
                    ES3.setVisibility(View.GONE);
                    samuh.setVisibility(View.GONE);
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
                    ES3.setVisibility(View.GONE);
                    samuh.setVisibility(View.GONE);
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
                    samuh.setVisibility(View.GONE);
                    ES3.setVisibility(View.GONE);
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
                if(progm != null && depp != null && year != null ){
                   if (note.isChecked()){
                       databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid()).child("Profile");
                       databaseReference.child("Name").setValue(name.getText().toString());
                       databaseReference.child("Roll No").setValue(roll.getText().toString());
                       databaseReference.child("Programme").setValue(progm);
                       databaseReference.child("Department").setValue(depp);
                       databaseReference.child("Year").setValue(year);
                       databaseReference.child("Contact").setValue(contact.getText().toString());
                       if (year.equals("1st year")){
                           ref = firebaseDatabase.getReference("Group").child(firebaseAuth.getUid());
                           ref.child("group").setValue(grp);
                           Toast.makeText(getApplicationContext(), "Details Submitted", Toast.LENGTH_SHORT).show();
                           startActivity(new Intent(editProfile.this,StudentsPage.class));
                       }else {
                           Toast.makeText(getApplicationContext(), "Details Submitted", Toast.LENGTH_SHORT).show();
                       }
                   }else {
                       note.setError("Please Read and tick");
                   }
                }
                else{
                    Toast.makeText(getApplicationContext(), " Please fill all the required fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Image"),PICK_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK && data.getData() != null){
            Uri imagePath = data.getData();
            CropImage.activity(imagePath)
                    .start(this);
           /* try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imagePath);
                profile.setImageBitmap(bitmap);
            } catch (IOException e){
                e.printStackTrace();
            }*/
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if(resultCode == RESULT_OK){
                Uri resultUri = result.getUri();
                pd1.setMessage("Uploading Image ! Please Smile");
                pd1.setCancelable(false);
                pd1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                pd1.show();
                StorageReference imageReference = storageReference.child(firebaseAuth.getUid()).child("image").child("Profile");
                imageReference.putFile(resultUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(editProfile.this, "Upload Image Successfully",Toast.LENGTH_SHORT).show();
                            storageReference = firebaseStorage.getReference();
                            storageReference.child(firebaseAuth.getUid()).child("image").child("Profile").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Picasso.get().load(uri).fit().centerCrop().into(profile);
                                }
                            });
                            pd1.dismiss();
                        }else {
                            Toast.makeText(editProfile.this, "Image not Uploaded",Toast.LENGTH_SHORT).show();
                            pd1.dismiss();
                        }
                    }
                });

            }

        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        //firebaseAuth.signOut();
        //startActivity(new Intent(editProfile.this, StudentsPage.class));
        Toast.makeText(editProfile.this, "Complete Your profile First",Toast.LENGTH_SHORT).show();
    }
}