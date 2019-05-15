package com.example.placement;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;


public class ProfileFragment extends Fragment {

    EditText stuname,stuno,stuss,stupu,stucg,stuusn;
    DatabaseReference mDatabase,rootRef,profRef;
    CircleImageView view1;
    FirebaseUser user;
    String st;
    FirebaseAuth firebaseAuth;
    private Uri filepath;
    private final int PICK_IMAGE_REQUEST=71;


    public ProfileFragment() {
        // Required empty public constructor
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile, container, false);
        stuname=(EditText) view.findViewById(R.id.studname);
        stuno=(EditText)view.findViewById(R.id.studno);
        stuss=(EditText)view.findViewById(R.id.sslc);
        stupu=(EditText)view.findViewById(R.id.pu);
        stucg=(EditText)view.findViewById(R.id.cgpa);
        stuusn=(EditText)view.findViewById(R.id.usn);
        view1=(CircleImageView) view.findViewById(R.id.view);
        Button edit=(Button) view.findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stname=stuname.getText().toString();
                String stusn=stuusn.getText().toString();
                String stno=stuno.getText().toString();
                String stssl=stuss.getText().toString();
                String stpu=stupu.getText().toString();
                String stcg=stucg.getText().toString();
                mDatabase.child("students").child(st).child("name").setValue(stname);
                mDatabase.child("students").child(st).child("usn").setValue(stusn);
                mDatabase.child("students").child(st).child("phnno").setValue(stno);
                mDatabase.child("students").child(st).child("sslc").setValue(stssl);
                mDatabase.child("students").child(st).child("puc").setValue(stpu);
                mDatabase.child("students").child(st).child("cgpa").setValue(stcg);

                stucg.setFocusableInTouchMode(false);
                stuusn.setFocusableInTouchMode(false);
                stuname.setFocusableInTouchMode(false);
                stuno.setFocusableInTouchMode(false);
                stuss.setFocusableInTouchMode(false);
                stupu.setFocusableInTouchMode(false);
            }
        });
        Button update=(Button)view.findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stucg.setFocusableInTouchMode(true);
                stuusn.setFocusableInTouchMode(true);
                stuname.setFocusableInTouchMode(true);
                stuno.setFocusableInTouchMode(true);
                stuss.setFocusableInTouchMode(true);
                stupu.setFocusableInTouchMode(true);
            }
        });

        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"),PICK_IMAGE_REQUEST);
            }
        });
        mDatabase= FirebaseDatabase.getInstance().getReference();
        user=FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null)
        {
            st=user.getEmail().replace(".",",");
        }
        rootRef=FirebaseDatabase.getInstance().getReference();
        profRef=rootRef.child("students").child(st);


        profRef.child("name").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value=dataSnapshot.getValue(String.class);
                stuname.setText(value);
                stuname.setBackgroundColor(android.R.color.transparent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        profRef.child("usn").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value1=dataSnapshot.getValue(String.class);
                stuusn.setText(value1);
                stuusn.setBackgroundColor(android.R.color.transparent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        profRef.child("phnno").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value2=dataSnapshot.getValue(String.class);
                stuno.setText(value2);
                stuno.setBackgroundColor(android.R.color.transparent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        profRef.child("sslc").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value3=dataSnapshot.getValue(String.class);
                stuss.setText(value3);
                stuss.setBackgroundColor(android.R.color.transparent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        profRef.child("puc").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value4=dataSnapshot.getValue(String.class);
                stupu.setText(value4);
                stupu.setBackgroundColor(android.R.color.transparent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        profRef.child("cgpa").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value5=dataSnapshot.getValue(String.class);
                stucg.setText(value5);
                stucg.setBackgroundColor(android.R.color.transparent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filepath = data.getData();
            filepath = data.getData();

            view1.setImageURI(filepath);
        }
    }



}
