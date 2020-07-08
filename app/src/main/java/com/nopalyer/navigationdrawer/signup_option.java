package com.nopalyer.navigationdrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nopalyer.navigationdrawer.Login.SignUp;
import com.nopalyer.navigationdrawer.student.calender.calender1;
import com.nopalyer.navigationdrawer.student.calender.evencalender;
import com.nopalyer.navigationdrawer.student.calender.oddcalender;

public class signup_option extends AppCompatActivity {
    Button btn_signup1,btn_signup2,btn_signup3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_option);
        btn_signup1=(Button)  findViewById(R.id.signup1);
        btn_signup2=(Button)  findViewById(R.id.signup2);
        btn_signup3=(Button)  findViewById(R.id.signup3);

        btn_signup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(signup_option.this, SignUp.class);
                startActivity(i);
            }
        });
        btn_signup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(signup_option.this, dual_batch.class);
                i.putExtra("batch","16");
                startActivity(i);
            }
        });
        btn_signup3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(signup_option.this, dual_batch.class);
                i.putExtra("batch","17");
                startActivity(i);
            }
        });
    }
}