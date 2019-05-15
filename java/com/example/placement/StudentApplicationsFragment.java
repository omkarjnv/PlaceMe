package com.example.placement;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class StudentApplicationsFragment extends Fragment {

    DatabaseReference mDatabase,pDatabase,uDatabase;
    FirebaseUser user;
    String usn="1BM16CS002",email;
    DatabaseReference ref;
    ArrayList<jobs> list;
    RecyclerView recyclerView;
    public static ArrayList<String> keys=new ArrayList<>();


    public StudentApplicationsFragment() {
        // Required empty public constructor
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_student_applications, container, false);

        recyclerView=(RecyclerView)view.findViewById(R.id.apps_recycler);
        uDatabase= FirebaseDatabase.getInstance().getReference().child("students");
        user= FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null)
        {
            email=user.getEmail().replace(".",",");
        }
        uDatabase.child(email).child("usn").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                usn=dataSnapshot.getValue(String.class);
                System.out.println(usn);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ref= FirebaseDatabase.getInstance().getReference().child("stud-apps").child(usn);
        if (ref!=null)
        {

            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    list=new ArrayList<>();
                    for(DataSnapshot ds: dataSnapshot.getChildren())
                    {

                        list.add(ds.getValue(jobs.class));
                        keys.add(ds.getKey());
                    }
                    stud_adapter adapterClass=new stud_adapter(list,keys);
                    LinearLayoutManager manager=new LinearLayoutManager(getActivity());
                    manager.setOrientation(LinearLayoutManager.VERTICAL);
                    adapterClass.notifyDataSetChanged();
                    recyclerView.setLayoutManager(manager);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setAdapter(adapterClass);
                    System.out.println(list.size());
                    System.out.println(keys);
                }


                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getActivity(),"Database Error",Toast.LENGTH_SHORT).show();
                }
            });
        }

        return view;
    }

    @Override
    public void onStart()
    {
        super.onStart();



    }



}
