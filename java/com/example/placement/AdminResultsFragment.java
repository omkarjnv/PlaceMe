package com.example.placement;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class AdminResultsFragment extends Fragment {
    LinearLayout l;
    EditText n,jobid;
    Button add,remove,update;
    Integer no;
    ArrayList<EditText> et=new ArrayList<>();
    DatabaseReference mDatabase,jDatabase;
    private  int text1=10;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_admin_results, container, false);
        n=(EditText)view.findViewById(R.id.no);
        jobid=(EditText)view.findViewById(R.id.jid);
        add=(Button)view.findViewById(R.id.add);
        l=(LinearLayout) view.findViewById(R.id.linear);
        remove=(Button)view.findViewById(R.id.remove);
        update=(Button)view.findViewById(R.id.upload);
        mDatabase= FirebaseDatabase.getInstance().getReference().child("results");
        jDatabase=FirebaseDatabase.getInstance().getReference().child("jobs");
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String no1=n.getText().toString();

                no= Integer.parseInt(no1);
                int top=0,left=50;
                for (int i=0;i<no;i++){
                    EditText e = new EditText(getContext());
                    et.add(e);
                    e.setId(text1);
                    e.setText("");
                    e.setEms(11);
                    e.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) e.getLayoutParams();
                    params.setMargins(left, top, 0, 0);
                    params.weight=1;
                    e.setLayoutParams(params);
                    l.addView(e);
                    System.out.println(e.getId());

                    text1++;
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String [] strings=new String[et.size()];
                String id=jobid.getText().toString();
                final String jobt=jobid.getText().toString();
                for (int i=0;i<et.size();i++)
                {
                    strings[i]=et.get(i).getText().toString();

                }
                jDatabase.child(id).child("title").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String  jtitle=dataSnapshot.getValue(String.class);
                        for (int k=0;k<strings.length;k++)
                        {
                            System.out.println(jobt + jtitle);
                            mDatabase.child(strings[k]).child(jobt).child("title").setValue(jtitle);
                            System.out.println("im here");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=jobid.getText().toString();
                jDatabase.child(id).removeValue();
            }
        });

        return view;
    }


}
