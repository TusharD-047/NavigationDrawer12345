package com.nopalyer.navigationdrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class RegInstruction extends AppCompatActivity {

    Button Next;
    private CheckBox tick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_instruction);

        Next = findViewById(R.id.press);
        tick = (CheckBox) findViewById(R.id.tiktok);

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tick.isChecked()){
                    startActivity(new Intent(RegInstruction.this,Btech_registration.class));
                }
            }
        });

    }
}