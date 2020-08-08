package com.nopalyer.navigationdrawer.profile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Picture;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.nopalyer.navigationdrawer.R;
import com.nopalyer.navigationdrawer.student.Spschedule;
import com.nopalyer.navigationdrawer.student.StudentsPage;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class studentp extends AppCompatActivity {
    private ImageView profile;
    private TextView name,roll,department,contact,email,name1,prog;
    ProgressDialog pd,pd1,pd2;
    Button updtyr;
    private static int PICK_IMAGE = 123;
    StorageReference storageReference;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,ref,ref2;
    FirebaseStorage firebaseStorage;
    //Spinner yearspinner;
    SharedPreferences sharedprefs,sharedPreferences2;
    SharedPreferences.Editor editor,editor2;
    String save = "";
    Long size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentp);

        updtyr = findViewById(R.id.updateyear);
        //yearspinner=(Spinner)findViewById(R.id.profileSpinner);
        profile = (ImageView)findViewById(R.id.profilep);
        name = (TextView)findViewById(R.id.name123);
        roll = (TextView)findViewById(R.id.roll123);
        name1 = (TextView)findViewById(R.id.name1);
        prog = (TextView)findViewById(R.id.program123);
        department = (TextView)findViewById(R.id.department123);
        contact = (TextView)findViewById(R.id.contact123);
        email = (TextView)findViewById(R.id.email123);
        pd =new ProgressDialog(this);
        pd1 =new ProgressDialog(this);
        pd2 =new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        pd2.setMessage("Wait");
        pd2.setCancelable(false);
        pd2.show();
        ref = firebaseDatabase.getReference();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(firebaseAuth.getUid())){
                    pd2.dismiss();
                    pd.setMessage("Retrieving Info ! Please Smile");
                    pd.setCancelable(false);
                    pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    pd.show();
                    databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid()).child("Profile");
                    storageReference = firebaseStorage.getReference();
                    storageReference.child(firebaseAuth.getUid()).child("image").child("Profile").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Picasso.get().load(uri).fit().centerCrop().into(profile);
                        }
                    });

                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            name.setText(dataSnapshot.child("Name").getValue().toString().trim());
                            name1.setText(dataSnapshot.child("Name").getValue().toString().trim());
                            roll.setText(dataSnapshot.child("Roll No").getValue().toString().trim());
                            department.setText(dataSnapshot.child("Department").getValue().toString().trim());
                            prog.setText(dataSnapshot.child("Programme").getValue().toString().trim());
                            email.setText(firebaseUser.getEmail());
                            contact.setText(dataSnapshot.child("Contact").getValue().toString().trim());
                            pd.dismiss();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Toast.makeText(studentp.this, databaseError.getCode(),Toast.LENGTH_SHORT).show();
                            pd.dismiss();
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

                    //spinner starts=============================================================================================================================================
                    /*final  String[] year1 = {"Choose year","1st year","2nd year","3rd year","4th year"};
                    sharedprefs = getSharedPreferences("yash",MODE_PRIVATE);
                    editor=sharedprefs.edit();

                    final int lastposition_yr = sharedprefs.getInt("lastselected_yr",0); // Load data

                    ArrayAdapter<String> adapter_year= new ArrayAdapter<String>(studentp.this,R.layout.colourful_spinner_items,year1);
                    adapter_year.setDropDownViewResource(R.layout.colourful_spinner_dropdown);
                    yearspinner.setAdapter(adapter_year);
                    yearspinner.setSelection(lastposition_yr);    // Update views
                    yearspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            editor.putInt("lastselected_yr",position).apply();  // save data
                            save = year1[position];

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
*/

                    //spinner ends==============================================================================================================================================
                }else {
                    pd2.dismiss();
                    startActivity(new Intent(studentp.this,com.nopalyer.navigationdrawer.editProfile.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        updtyr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref2 = firebaseDatabase.getReference(firebaseAuth.getUid()).child("Profile");
                ref2.child("Year").setValue(save);
                startActivity(new Intent(studentp.this,StudentsPage.class));
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
                File f = new File(resultUri.getPath());
                size = f.length();
                if (size/1024 < 200 ){
                    pd1.setMessage("Uploading Image ! Please Smile");
                    pd1.setCancelable(false);
                    pd1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    pd1.show();
                    StorageReference imageReference = storageReference.child(firebaseAuth.getUid()).child("image").child("Profile");
                    imageReference.putFile(resultUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                            if(task.isSuccessful()){
                                Toast.makeText(studentp.this, "Upload Image Successfully",Toast.LENGTH_SHORT).show();
                                storageReference = firebaseStorage.getReference();
                                storageReference.child(firebaseAuth.getUid()).child("image").child("Profile").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        Picasso.get().load(uri).fit().centerCrop().into(profile);
                                    }
                                });
                                pd1.dismiss();
                            }else {
                                Toast.makeText(studentp.this, "Image not Uploaded",Toast.LENGTH_SHORT).show();
                                pd1.dismiss();
                            }
                        }
                    });
                }else {
                    Toast.makeText(studentp.this, "Upload with Smaller Size",Toast.LENGTH_SHORT).show();
                }

            }

        }
    }
}
