package com.nopalyer.navigationdrawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CourseView extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference mref;
    PDFView pdf;
    String course,branch;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_view);
        pdf = findViewById(R.id.pdfv);
        course = getIntent().getStringExtra("Course");
        branch = getIntent().getStringExtra("branch");
        firebaseDatabase = FirebaseDatabase.getInstance();
        pd = new ProgressDialog(this);
        pd.setMessage("Wait..");
        pd.setCancelable(false);
        pd.show();
        mref = firebaseDatabase.getReference("CourseCurriculum").child(course);
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String url = dataSnapshot.child(branch).getValue().toString();
                new RetrievePdfStream().execute(url);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
        class RetrievePdfStream extends AsyncTask<String,Void, InputStream>{

            @Override
            protected InputStream doInBackground(String... strings) {
                InputStream inputStream=null;
                try {
                    URL url = new URL(strings[0]);
                    HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                    if (urlConnection.getResponseCode()==200){
                        inputStream = new BufferedInputStream(urlConnection.getInputStream());
                    }
                }catch (IOException e){
                    return null;
                }
                return inputStream;
            }

            @Override
            protected void onPostExecute(InputStream inputStream) {
                pdf.fromStream(inputStream).load();
                pd.dismiss();
            }
        }
}