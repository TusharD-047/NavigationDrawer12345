package com.nopalyer.navigationdrawer.teacher;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.nopalyer.navigationdrawer.R;
import com.nopalyer.navigationdrawer.student.Spschedule;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class sch extends AppCompatActivity {
    Toolbar toolbar;
    private PDFView pdfView;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference kref,ref,reference,ref2,ref3;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tp_sch);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        pdfView = findViewById(R.id.tpsch);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        pd =new ProgressDialog(this);

        pd.setMessage("Loading... ! Please Smile");
        pd.setCancelable(false);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.show();
        ref2 = firebaseDatabase.getReference();
        ref2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("Teacher Schedule")){
                    ref = firebaseDatabase.getReference("Teacher Schedule");
                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.hasChild(firebaseAuth.getUid())){
                                reference = ref.child(firebaseAuth.getUid());
                                reference.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        String url = dataSnapshot.child("Url").getValue().toString();
                                        new RetrievePDFStream().execute(url);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }else {
                                Toast.makeText(sch.this,"Upload Schedule as PDF",Toast.LENGTH_SHORT).show();
                                pd.dismiss();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }else {
                    Toast.makeText(sch.this,"Upload Schedule as PDF bahar",Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.image, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String mera = " ";
        switch (item.getItemId()) {
            case (R.id.chitr):
                UploadPDF();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void UploadPDF() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.parse("package:" + getPackageName()));
            startActivity(intent);
            return;
        }

        Intent intent = new Intent();
        intent.setType("application/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Doocument"), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            uploadPDFFile(data.getData());
        }
    }

    private void uploadPDFFile(final Uri data) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        StorageReference reference = FirebaseStorage.getInstance().getReference("Teacher Schedule").child(firebaseAuth.getUid()).child("Schedule");
        reference.putFile(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                while (!uri.isComplete()) ;
                final Uri url1 = uri.getResult();
                kref = firebaseDatabase.getReference("Teacher Schedule").child(firebaseAuth.getUid());
                kref.child("Url").setValue(url1.toString());
                progressDialog.dismiss();
                Toast.makeText(sch.this, "Successfully Uploaded", Toast.LENGTH_SHORT).show();

                pd.setMessage("Loading... ! Please Smile");
                pd.setCancelable(false);
                pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                pd.show();
                ref3 = firebaseDatabase.getReference("Teacher Schedule").child(firebaseAuth.getUid());
                ref3.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String url = dataSnapshot.child("Url").getValue().toString();
                        new RetrievePDFStream().execute(url);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
    }

    class RetrievePDFStream extends AsyncTask<String,Void, InputStream>
    {
        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                if(urlConnection.getResponseCode() == 200){
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }
            }catch (IOException e)
            {
                return null;

            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            pdfView.fromStream(inputStream).load();
            pd.dismiss();
        }

    }
}
