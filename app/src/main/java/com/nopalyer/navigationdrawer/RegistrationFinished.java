package com.nopalyer.navigationdrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.nopalyer.navigationdrawer.student.StudentsPage;

public class RegistrationFinished extends AppCompatActivity {

    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_finished);

        b1 = (Button)findViewById(R.id.finalfinish);

                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FirebaseAuth auth = FirebaseAuth.getInstance();
                        auth.signOut();
                        finish();
                        startActivity(new Intent(RegistrationFinished.this,login.class));
                    }
                });
    }

    @Override
    public void onBackPressed() {

    }
}