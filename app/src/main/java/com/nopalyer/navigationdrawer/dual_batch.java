package com.nopalyer.navigationdrawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.nopalyer.navigationdrawer.Login.SignUp;

public class dual_batch extends AppCompatActivity {
    private EditText Email1,pass1;
    private Button go1;
    private TextView showHide1;
    private FirebaseAuth firebaseAuth1;
    private int ShowPass1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dual_batch);
        Email1 = findViewById(R.id.crroll1);
        pass1 = findViewById(R.id.crpass1);
        showHide1=(TextView)findViewById(R.id.newpass11);
        go1 = findViewById(R.id.crbuttn1);

        ShowPass1 = 1;
        showHide1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ShowPass1 ==1)
                {
                    ShowPass1 =0;
                    pass1.setTransformationMethod(null);
                    if(pass1.getText().length()>0)
                        pass1.setSelection(pass1.getText().length());
                    showHide1.setBackgroundResource(R.drawable.ic_images2);

                }
                else{
                    ShowPass1=1;
                    pass1.setTransformationMethod(new PasswordTransformationMethod());
                    if(pass1.getText().length()>0)
                        pass1.setSelection(pass1.getText().length());
                    showHide1.setBackgroundResource(R.drawable.ic_images1);
                }
            }
        });


        firebaseAuth1 = FirebaseAuth.getInstance();

        go1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Email1.getText().toString().isEmpty()){
                    Email1.setError("Required Field");
                }else if (Email1.getText().toString().trim().length() < 5){
                    Email1.setError("Enter Valid Roll No");
                }else if (pass1.getText().toString().trim().length() < 6){
                    pass1.setError("Password Length must be 6");
                }else {
                    final String mail = Email1.getText().toString().trim() + "@nith.ac.in";
                    firebaseAuth1.createUserWithEmailAndPassword(mail,pass1.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(dual_batch.this,"Succesfully Created ... Your Email is " + mail , Toast.LENGTH_LONG).show();
                                firebaseAuth1.signOut();
                                finish();
                                startActivity(new Intent(dual_batch.this,com.nopalyer.navigationdrawer.login.class));
                            }else{
                                Toast.makeText(dual_batch.this,"Try Again",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}