package com.nopalyer.navigationdrawer.Admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
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

import java.util.ArrayList;
import java.util.List;

public class AdminRoll extends AppCompatActivity {

    private ListView ListForm;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference,ref;
    List<String> listDataHeader;
    String dep = "",yr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_roll);

        firebaseAuth = FirebaseAuth.getInstance();
        ListForm = (ListView)findViewById(R.id.ListRoll);
        dep = getIntent().getExtras().getString("dep");
        yr = getIntent().getExtras().getString("yr");

        viewAllFiles();

        ListForm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = listDataHeader.get(position);
                Intent i = new Intent(AdminRoll.this, AdminDetails.class);
                i.putExtra("roll", item);
                i.putExtra("dep", dep);
                i.putExtra("yr", yr);
                startActivity(i);
            }
        });
    }

    private void viewAllFiles() {

        listDataHeader = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("Physical Appearance").child(yr).child(dep);
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                final String headertitile = dataSnapshot.getKey();
                listDataHeader.add(headertitile);

                final String[] uploads = new String[listDataHeader.size()];

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
