package com.nopalyer.navigationdrawer.student.Register;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nopalyer.navigationdrawer.Admin.AdminNoReg;
import com.nopalyer.navigationdrawer.Bonafide_Application;
import com.nopalyer.navigationdrawer.Btech_registration;
import com.nopalyer.navigationdrawer.Openelective;
import com.nopalyer.navigationdrawer.PgRegis;
import com.nopalyer.navigationdrawer.PhdRegis;
import com.nopalyer.navigationdrawer.R;

import java.util.ArrayList;

public class SimpleRecyclerAdapter2 extends RecyclerView.Adapter<SimpleRecyclerAdapter2.SimpleViewHolder> {

    private Context context;
    private ArrayList<reg> listreg;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference ref1,ref2,ref3,ref4;
    String semcon,sembon;

    public SimpleRecyclerAdapter2(Context context) {
        this.context = context;
        this.listreg = listreg;
    }


    public ArrayList<reg> getListreg() {
        return listreg;
    }

    public void setListreg(ArrayList<reg> listreg) {
        this.listreg = listreg;
    }

    @NonNull
    @Override
    public SimpleRecyclerAdapter2.SimpleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item2,parent, false);

        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleRecyclerAdapter2.SimpleViewHolder holder, final int position) {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        ref1 = firebaseDatabase.getReference("Admin Switch").child("Semester Switch");
        ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                semcon = dataSnapshot.child("Condition").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        ref2 = firebaseDatabase.getReference("Admin Switch").child("Semester Switch");
        ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                sembon = dataSnapshot.child("Condition").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ref3 = firebaseDatabase.getReference("Admin Switch").child("Semester Switch");
        ref3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                semcon = dataSnapshot.child("Condition").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        ref4 = firebaseDatabase.getReference("Admin Switch").child("Bonafide Switch");
        ref4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                sembon = dataSnapshot.child("Condition").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        holder.textView.setText(getListreg().get(position).getName());
        Glide.with(context).load(getListreg().get(position).getPhoto()).into(holder.imageView);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position){
                    case 0:
                        if (semcon.equals("true")){
                            Intent intent = new Intent(context, Btech_registration.class);
                            context.startActivity(intent);
                        }if (semcon.equals("false")){
                            Intent intent = new Intent(context, AdminNoReg.class);
                            context.startActivity(intent);
                        }
                        break;
                    case 1:
                        if (sembon.equals("true")){
                            Intent intent = new Intent(context, PgRegis.class);
                            context.startActivity(intent);
                        }if (sembon.equals("false")){
                            Intent intent = new Intent(context, AdminNoReg.class);
                            context.startActivity(intent);
                        }
                        break;
                    case 2:
                        if (semcon.equals("true")){
                            Intent intent = new Intent(context, PhdRegis.class);
                            context.startActivity(intent);
                        }if (semcon.equals("false")){
                        Intent intent = new Intent(context, AdminNoReg.class);
                        context.startActivity(intent);
                    }
                        break;
                    case 3:
                        if (sembon.equals("true")){
                            Intent intent = new Intent(context, Openelective.class);
                            context.startActivity(intent);
                        }if (sembon.equals("false")){
                        Intent intent = new Intent(context, AdminNoReg.class);
                        context.startActivity(intent);
                    }
                        break;
                    case 4:
                        if (sembon.equals("true")){
                            Intent intent = new Intent(context, Bonafide_Application.class);
                            context.startActivity(intent);
                        }if (sembon.equals("false")){
                        Intent intent = new Intent(context, AdminNoReg.class);
                        context.startActivity(intent);
                    }
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + position);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return listreg.size();
    }
    public static class SimpleViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;
        public ImageView imageView;
        public CardView cardView;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textViewka);
            imageView = (ImageView) itemView.findViewById(R.id.imageka);
            cardView = (CardView) itemView.findViewById(R.id.cardview);
        }
    }
}
