package com.nopalyer.navigationdrawer.Admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nopalyer.navigationdrawer.R;

public class AdminTry extends AppCompatActivity {

    FirebaseAuth Auth;
    FirebaseDatabase Database;
    DatabaseReference databaseReference,ref,ref3;
    EditText dd;
    TextView t;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_try);

        Auth = FirebaseAuth.getInstance();
        Database = FirebaseDatabase.getInstance();
        dd = findViewById(R.id.editText);
        b = findViewById(R.id.button);
        t = findViewById(R.id.textView17);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //databaseReference = Database.getReference().child(Auth.getUid()).child("Profile");
                //databaseReference.child("value").setValue(dd.getText().toString());

                /*ref = Database.getReference().child(Auth.getUid()).child("Profile");
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String ve = dataSnapshot.child("role").getValue().toString();
                        t.setText(ve);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });*/

                ref3 = Database.getReference().child("Application Form").child("1st year").child("CSE");
                ref3.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        String d = dataSnapshot.getKey();
                        Toast.makeText(AdminTry.this,d,Toast.LENGTH_SHORT).show();
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
        });
    }
}
