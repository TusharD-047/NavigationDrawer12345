package com.nopalyer.navigationdrawer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;

import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import static android.os.Environment.*;


public class ViewActivity extends AppCompatActivity {

    private static final String TAG = "AdminActivity";
    private TextView name,faname,rolln,dobn,semtr,catg,acyr,room,deppp,prog;
    Bundle bundle;
    private Button upload,edit,next;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog mProgress;
    private FirebaseDatabase database;
    private DatabaseReference ref,mref;
    private Toolbar toolbar;
    String yr = "",dep = "",roll = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Review Form");
        toolbar.setTitleTextColor(Color.WHITE);

        bundle = getIntent().getExtras();
        UI();
        ActivityCompat.requestPermissions(ViewActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        mProgress = new ProgressDialog(ViewActivity.this);
        name.setText(bundle.get("Name").toString());
        faname.setText(bundle.get("FatherName").toString());
        rolln.setText(bundle.get("RollNo").toString());
        dobn.setText(bundle.get("Date Of Birth").toString());
        semtr.setText(bundle.get("Email Address").toString());
        catg.setText(bundle.get("Mobile No. 1").toString());
        acyr.setText(bundle.get("Academic Year").toString());
        room.setText(bundle.get("Room").toString());
        deppp.setText(bundle.get("Dep").toString());
        prog.setText(bundle.get("Prog").toString());

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgress.setMessage("Wait");
                mProgress.show();
                ref = database.getReference(firebaseAuth.getUid()).child("Profile");
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        yr = dataSnapshot.child("Year").getValue().toString();
                        dep = dataSnapshot.child("Department").getValue().toString();
                        roll = dataSnapshot.child("Roll No").getValue().toString();

                        mref = database.getReference("Application Form").child(yr).child(dep).child(roll);
                        mref.child("Name").setValue(name.getText());
                        mref.child("FatherName").setValue(faname.getText());
                        mref.child("RollNo").setValue(rolln.getText());
                        mref.child("DOB").setValue(dobn.getText());
                        mref.child("Email").setValue(semtr.getText());
                        mref.child("Mobile").setValue(catg.getText());
                        mref.child("Academic Year").setValue(acyr.getText());
                        mref.child("Room").setValue(room.getText());
                        mref.child("Programme").setValue(prog.getText());
                        mref.child("Department").setValue(deppp.getText());

                        mProgress.dismiss();
                        Toast.makeText(ViewActivity.this,"Upload Done",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(ViewActivity.this,databaseError.getCode(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewActivity.this,Btech_registration.class));
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewActivity.this,UpDocument.class));
            }
        });
    }

    private void  UI(){
        name = (TextView)findViewById(R.id.regtvName);
        faname = (TextView)findViewById(R.id.regtvFather);
        rolln = (TextView)findViewById(R.id.regtvroll);
        dobn = (TextView)findViewById(R.id.regtvDob);
        semtr = (TextView)findViewById(R.id.regtvSem);
        catg = (TextView)findViewById(R.id.regtvCategory);
        room = (TextView)findViewById(R.id.regtvroom);
        deppp = (TextView)findViewById(R.id.regtvdep);
        prog = (TextView)findViewById(R.id.regtvprog);
        acyr = findViewById(R.id.regtvAcyr);
        upload = (Button)findViewById(R.id.regaddpdf);
        edit = (Button)findViewById(R.id.regedit);
        next = (Button)findViewById(R.id.regupload2);
        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
    }


}
