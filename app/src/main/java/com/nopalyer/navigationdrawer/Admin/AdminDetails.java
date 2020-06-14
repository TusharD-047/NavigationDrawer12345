package com.nopalyer.navigationdrawer.Admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nopalyer.navigationdrawer.R;
import com.nopalyer.navigationdrawer.uploadPDF;

import java.util.ArrayList;
import java.util.List;

public class AdminDetails extends AppCompatActivity {

    private ListView ListForm;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference,fref;
    //List<uploadPDF> uploadPDFS;
    List<String> listDataHeader;
    String yr,dep,roll;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_details);

        yr = getIntent().getExtras().getString("yr");
        dep = getIntent().getExtras().getString("dep");
        roll = getIntent().getExtras().getString("roll");
        pd =new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();
        ListForm = (ListView)findViewById(R.id.ListDetails);
        //uploadPDFS = new ArrayList<>();

        viewAllFiles();

        ListForm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pd.setMessage("Uploading Image ! Please Smile");
                pd.setCancelable(false);
                pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                pd.show();
                //uploadPDF uploadPDF = uploadPDFS.get(position);
                String item = listDataHeader.get(position);
                fref = FirebaseDatabase.getInstance().getReference("Application Form").child(yr).child(dep).child(roll).child(item);
                fref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String url = dataSnapshot.child("url").getValue().toString();

                        Intent intent = new Intent();
                        intent.setType(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        Intent newIntent = Intent.createChooser(intent,"Open File");
                        startActivity(newIntent);
                        pd.dismiss();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    private void viewAllFiles() {
        listDataHeader = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("Application Form").child(yr).child(dep).child(roll);
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                final String headertitile = dataSnapshot.getKey();
                listDataHeader.add(headertitile);

                String[] uploads = new String[listDataHeader.size()];
                for(int i=0; i<uploads.length;i++){
                    uploads[i] = listDataHeader.get(i);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,uploads){

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {

                        View view = super.getView(position, convertView, parent);

                        TextView myText = (TextView)view.findViewById(android.R.id.text1);
                        myText.setTextColor(Color.BLACK);
                        myText.setTextSize(20);

                        return view;
                    }
                };
                ListForm.setAdapter(adapter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
