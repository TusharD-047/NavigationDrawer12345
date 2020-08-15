package com.nopalyer.navigationdrawer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.itextpdf.xmp.impl.Utils;
import com.nopalyer.navigationdrawer.profile.studentp;
import com.nopalyer.navigationdrawer.student.StudentsPage;
import com.nopalyer.navigationdrawer.teacher.tpassign;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;
import java.io.File;

import static androidx.core.content.FileProvider.getUriForFile;

public class UpDocument extends AppCompatActivity {

    Button upProfile,upId,upfees,upAdd,regfinish,uphostel,uplfees;
    ImageView photopv,idpv,feepv,hospv,incomepv,lfeepv;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    FirebaseStorage firebaseStorage;
    StorageReference sref,reference,reference2,reference3,reference4,reference5,reference6;
    DatabaseReference ref,mref;
    String yr = "",dep = "",roll = "",name ="",type;
    private static int PICK_IMAGE = 123;
    private static int PICK_IMAGE2 = 124;
    private static int PICK_IMAGE3 = 125;
    private static int PICK_IMAGE4 = 126;
    private static int PICK_IMAGE5 = 127;
    private static int PICK_IMAGE6 = 128;
    private static int PICK_IMAGE7 = 129;
    String photost ="",idst ="",feesst ="",hosst ="",incomest ="",lfeesst="";
    Uri photo,id,fees,hos,income,lfees;
    long size,size2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_document);

        type = getIntent().getExtras().getString("type");
        upProfile = findViewById(R.id.upPhoto);
        uphostel = findViewById(R.id.upHostelFees);
        upId = findViewById(R.id.upId);
        upfees = findViewById(R.id.upFees);
        upAdd = findViewById(R.id.upAdd);
        uplfees = findViewById(R.id.upLFees);
        regfinish =findViewById(R.id.regfinish);
        photopv = findViewById(R.id.PhotoPreview);
        idpv = findViewById(R.id.Idpreview);
        feepv = findViewById(R.id.FeesPreview);
        hospv = findViewById(R.id.Hostelpreview);
        incomepv = findViewById(R.id.AddDocPreview);
        lfeepv = findViewById(R.id.LFeesPreview);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        sref =firebaseStorage.getReference(type);
        mref = firebaseDatabase.getReference(type);

        ref = firebaseDatabase.getReference(firebaseAuth.getUid()).child("Profile");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                yr = dataSnapshot.child("Year").getValue().toString();
                dep = dataSnapshot.child("Department").getValue().toString();
                roll = dataSnapshot.child("Roll No").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(UpDocument.this,databaseError.getCode(),Toast.LENGTH_SHORT).show();
            }
        });

        upProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Image"),PICK_IMAGE);
            }
        });

        upId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Image"),PICK_IMAGE2);
            }
        });

        upfees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Image"),PICK_IMAGE3);
            }
        });

        upAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Image"),PICK_IMAGE4);
            }
        });
        uphostel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Image"),PICK_IMAGE5);
            }
        });
        uplfees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Image"),PICK_IMAGE6);
            }
        });
        regfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hosst.isEmpty() || idst.isEmpty() || feesst.isEmpty() || photost.isEmpty()){
                    Toast.makeText(UpDocument.this,"All Document Are necessary except Income Certificate",Toast.LENGTH_SHORT).show();
                }else if (incomest.isEmpty() && lfeesst.isEmpty()){
                    reference2 = sref.child(yr).child(dep).child(roll).child(photost);
                    reference2.putFile(photo).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                            while(!uri.isComplete());
                            Uri url1 = uri.getResult();
                            //uploadPDF uploadPDF = new uploadPDF(name,url1.toString());
                            mref.child(yr).child(dep).child(roll).child(photost).setValue(url1.toString());
                        }
                    });
                    reference = sref.child(yr).child(dep).child(roll).child(idst);
                    reference.putFile(id).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                            while(!uri.isComplete());
                            Uri url1 = uri.getResult();
                            //uploadPDF uploadPDF = new uploadPDF(name,url1.toString());
                            mref.child(yr).child(dep).child(roll).child(idst).setValue(url1.toString());
                        }
                    });
                    reference3 = sref.child(yr).child(dep).child(roll).child(feesst);
                    reference3.putFile(fees).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                            while(!uri.isComplete());
                            Uri url1 = uri.getResult();
                            //uploadPDF uploadPDF = new uploadPDF(name,url1.toString());
                            mref.child(yr).child(dep).child(roll).child(feesst).setValue(url1.toString());
                        }
                    });
                    reference5 = sref.child(yr).child(dep).child(roll).child(hosst);
                    reference5.putFile(hos).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                            while(!uri.isComplete());
                            Uri url1 = uri.getResult();
                            //uploadPDF uploadPDF = new uploadPDF(name,url1.toString());
                            mref.child(yr).child(dep).child(roll).child(hosst).setValue(url1.toString());
                        }
                    });
                    Toast.makeText(UpDocument.this,"Doneee",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(UpDocument.this,RegistrationFinished.class));
                }   else if(incomest.isEmpty()){
                    reference2 = sref.child(yr).child(dep).child(roll).child(photost);
                    reference2.putFile(photo).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                            while(!uri.isComplete());
                            Uri url1 = uri.getResult();
                            //uploadPDF uploadPDF = new uploadPDF(name,url1.toString());
                            mref.child(yr).child(dep).child(roll).child(photost).setValue(url1.toString());
                        }
                    });
                    reference = sref.child(yr).child(dep).child(roll).child(idst);
                    reference.putFile(id).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                            while(!uri.isComplete());
                            Uri url1 = uri.getResult();
                            //uploadPDF uploadPDF = new uploadPDF(name,url1.toString());
                            mref.child(yr).child(dep).child(roll).child(idst).setValue(url1.toString());
                        }
                    });
                    reference3 = sref.child(yr).child(dep).child(roll).child(feesst);
                    reference3.putFile(fees).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                            while(!uri.isComplete());
                            Uri url1 = uri.getResult();
                            //uploadPDF uploadPDF = new uploadPDF(name,url1.toString());
                            mref.child(yr).child(dep).child(roll).child(feesst).setValue(url1.toString());
                        }
                    });
                    reference5 = sref.child(yr).child(dep).child(roll).child(hosst);
                    reference5.putFile(hos).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                            while(!uri.isComplete());
                            Uri url1 = uri.getResult();
                            //uploadPDF uploadPDF = new uploadPDF(name,url1.toString());
                            mref.child(yr).child(dep).child(roll).child(hosst).setValue(url1.toString());
                        }
                    });
                    reference6 = sref.child(yr).child(dep).child(roll).child(lfeesst);
                    reference6.putFile(lfees).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                            while(!uri.isComplete());
                            Uri url1 = uri.getResult();
                            //uploadPDF uploadPDF = new uploadPDF(name,url1.toString());
                            mref.child(yr).child(dep).child(roll).child(lfeesst).setValue(url1.toString());
                        }
                    });
                    Toast.makeText(UpDocument.this,"Doneee",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(UpDocument.this,RegistrationFinished.class));
                }
                else if(lfeesst.isEmpty()){
                    reference2 = sref.child(yr).child(dep).child(roll).child(photost);
                    reference2.putFile(photo).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                            while(!uri.isComplete());
                            Uri url1 = uri.getResult();
                            //uploadPDF uploadPDF = new uploadPDF(name,url1.toString());
                            mref.child(yr).child(dep).child(roll).child(photost).setValue(url1.toString());
                        }
                    });
                    reference = sref.child(yr).child(dep).child(roll).child(idst);
                    reference.putFile(id).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                            while(!uri.isComplete());
                            Uri url1 = uri.getResult();
                            //uploadPDF uploadPDF = new uploadPDF(name,url1.toString());
                            mref.child(yr).child(dep).child(roll).child(idst).setValue(url1.toString());
                        }
                    });
                    reference3 = sref.child(yr).child(dep).child(roll).child(feesst);
                    reference3.putFile(fees).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                            while(!uri.isComplete());
                            Uri url1 = uri.getResult();
                            //uploadPDF uploadPDF = new uploadPDF(name,url1.toString());
                            mref.child(yr).child(dep).child(roll).child(feesst).setValue(url1.toString());
                        }
                    });
                    reference4 = sref.child(yr).child(dep).child(roll).child(incomest);
                    reference4.putFile(income).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                            while(!uri.isComplete());
                            Uri url1 = uri.getResult();
                            //uploadPDF uploadPDF = new uploadPDF(name,url1.toString());
                            mref.child(yr).child(dep).child(roll).child(incomest).setValue(url1.toString());
                        }
                    });
                    reference5 = sref.child(yr).child(dep).child(roll).child(hosst);
                    reference5.putFile(hos).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                            while(!uri.isComplete());
                            Uri url1 = uri.getResult();
                            //uploadPDF uploadPDF = new uploadPDF(name,url1.toString());
                            mref.child(yr).child(dep).child(roll).child(hosst).setValue(url1.toString());
                        }
                    });
                    Toast.makeText(UpDocument.this,"Doneee",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(UpDocument.this,RegistrationFinished.class));
                }
                else {
                    reference2 = sref.child(yr).child(dep).child(roll).child(photost);
                    reference2.putFile(photo).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                            while(!uri.isComplete());
                            Uri url1 = uri.getResult();
                            //uploadPDF uploadPDF = new uploadPDF(name,url1.toString());
                            mref.child(yr).child(dep).child(roll).child(photost).setValue(url1.toString());
                        }
                    });
                    reference = sref.child(yr).child(dep).child(roll).child(idst);
                    reference.putFile(id).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                            while(!uri.isComplete());
                            Uri url1 = uri.getResult();
                            //uploadPDF uploadPDF = new uploadPDF(name,url1.toString());
                            mref.child(yr).child(dep).child(roll).child(idst).setValue(url1.toString());
                        }
                    });
                    reference3 = sref.child(yr).child(dep).child(roll).child(feesst);
                    reference3.putFile(fees).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                            while(!uri.isComplete());
                            Uri url1 = uri.getResult();
                            //uploadPDF uploadPDF = new uploadPDF(name,url1.toString());
                            mref.child(yr).child(dep).child(roll).child(feesst).setValue(url1.toString());
                        }
                    });
                    reference4 = sref.child(yr).child(dep).child(roll).child(incomest);
                    reference4.putFile(income).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                            while(!uri.isComplete());
                            Uri url1 = uri.getResult();
                            //uploadPDF uploadPDF = new uploadPDF(name,url1.toString());
                            mref.child(yr).child(dep).child(roll).child(incomest).setValue(url1.toString());
                        }
                    });
                    reference5 = sref.child(yr).child(dep).child(roll).child(hosst);
                    reference5.putFile(hos).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                            while(!uri.isComplete());
                            Uri url1 = uri.getResult();
                            //uploadPDF uploadPDF = new uploadPDF(name,url1.toString());
                            mref.child(yr).child(dep).child(roll).child(hosst).setValue(url1.toString());
                        }
                    });
                    reference6 = sref.child(yr).child(dep).child(roll).child(lfeesst);
                    reference6.putFile(lfees).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                            while(!uri.isComplete());
                            Uri url1 = uri.getResult();
                            //uploadPDF uploadPDF = new uploadPDF(name,url1.toString());
                            mref.child(yr).child(dep).child(roll).child(lfeesst).setValue(url1.toString());
                        }
                    });
                    Toast.makeText(UpDocument.this,"Doneee",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(UpDocument.this,RegistrationFinished.class));
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK && data.getData() != null){
            Uri imagePath = data.getData();

            name = "Urlphoto";
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
                if (size/1024 < 100){
                    if (name.equals("Urlphoto")){
                        photost = name;
                        photo = resultUri;
                        photopv.setImageURI(resultUri);
                    }else if (name.equals("Urlidcard")){
                        idst = name;
                        id = resultUri;
                        idpv.setImageURI(resultUri);
                    }else if (name.equals("Fees")){
                        feesst = name;
                        fees = resultUri;
                        feepv.setImageURI(resultUri);
                    }else if (name.equals("Additional")){
                        incomest = name;
                        income = resultUri;
                        incomepv.setImageURI(resultUri);
                    }else if (name.equals("Urlhostel")){
                        hosst = name;
                        hos = resultUri;
                        hospv.setImageURI(resultUri);
                    }
                    else if (name.equals("UrlLfees")){
                        lfeesst = name;
                        lfees = resultUri;
                        lfeepv.setImageURI(resultUri);
                    }
                }else {
                    Toast.makeText(UpDocument.this,"Upload with smaller size",Toast.LENGTH_SHORT).show();
                }

            }

        }

        if(requestCode == PICK_IMAGE2 && resultCode == RESULT_OK && data.getData() != null){
            Uri imagePath = data.getData();

            name = "Urlidcard";
            CropImage.activity(imagePath)
                    .start(this);
           /* try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imagePath);
                profile.setImageBitmap(bitmap);
            } catch (IOException e){
                e.printStackTrace();
            }*/
        }
        if(requestCode == PICK_IMAGE3 && resultCode == RESULT_OK && data.getData() != null){
            Uri imagePath = data.getData();
            name = "Fees";
            CropImage.activity(imagePath)
                    .start(this);
           /* try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imagePath);
                profile.setImageBitmap(bitmap);
            } catch (IOException e){
                e.printStackTrace();
            }*/
        }
        if(requestCode == PICK_IMAGE4 && resultCode == RESULT_OK && data.getData() != null){
            Uri imagePath = data.getData();
            name = "Additional";
            CropImage.activity(imagePath)
                    .start(this);
           /* try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imagePath);
                profile.setImageBitmap(bitmap);
            } catch (IOException e){
                e.printStackTrace();
            }*/
        }
        if(requestCode == PICK_IMAGE5 && resultCode == RESULT_OK && data.getData() != null){
            Uri imagePath = data.getData();

            name = "Urlhostel";
            CropImage.activity(imagePath)
                    .start(this);

           /* try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imagePath);
                profile.setImageBitmap(bitmap);
            } catch (IOException e){
                e.printStackTrace();
            }*/
        }
        if(requestCode == PICK_IMAGE6 && resultCode == RESULT_OK && data.getData() != null){
            Uri imagePath = data.getData();

            name = "UrlLfees";
            CropImage.activity(imagePath)
                    .start(this);

           /* try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imagePath);
                profile.setImageBitmap(bitmap);
            } catch (IOException e){
                e.printStackTrace();
            }*/
        }
    }
    @Override
    public void onBackPressed() {

    }

}
