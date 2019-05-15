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
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class AdminApplicationsFragment extends Fragment {
    DatabaseReference mDatabase,jDatabase,cDatabase;
    EditText jid;
    ConstraintLayout linear;
    Button getbtn,disp;
    private  int text1=10,i=0;
    TextView title1,compname;
    TableLayout tb;
    String jids,title,sjid;
    ArrayList<String>usn=new ArrayList<>();
    ArrayList<String>cgpa=new ArrayList<>();
    ArrayList<String>name=new ArrayList<>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_admin_applications, container, false);
        mDatabase= FirebaseDatabase.getInstance().getReference().child("applications");

        jDatabase=FirebaseDatabase.getInstance().getReference();
        cDatabase=FirebaseDatabase.getInstance().getReference();
        jid=(EditText) view.findViewById(R.id.jobid);
        title1=(TextView) view.findViewById(R.id.jobtitle);
        compname=(TextView) view.findViewById(R.id.compname);
        getbtn=(Button) view.findViewById(R.id.get);
        linear=(ConstraintLayout)view.findViewById(R.id.con);
        tb=(TableLayout)view.findViewById(R.id.tb);
        disp=(Button)view.findViewById(R.id.disp);


        getbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                jids = jid.getText().toString();
                sjid = jids.substring(0, 2);
                jDatabase.child("jobs").child(jids).child("title").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String tit = dataSnapshot.getValue(String.class);
                        title1.setText(tit);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                cDatabase.child("company").child(sjid).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String comp = dataSnapshot.getValue(String.class);
                        compname.setText(comp);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                mDatabase.child(jids).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot d : dataSnapshot.getChildren()) {
                            usn.add(d.getKey());

                        }
                        System.out.println(usn);
                        for (i = 0; i < usn.size(); i++) {
                            mDatabase.child(jids).child(usn.get(i)).child("name").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override

                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    name.add(dataSnapshot.getValue(String.class));

                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }

                            });
                            mDatabase.child(jids).child(usn.get(i)).child("cgpa").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    cgpa.add(dataSnapshot.getValue(String.class));
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });



                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                System.out.println(name.size());
            }
        });

        disp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < usn.size(); i++) {
                    TableRow r = new TableRow(getContext());
                    TextView p = new TextView(getContext());
                    TextView q = new TextView(getContext());
                    TextView s = new TextView(getContext());
                    q.setText(cgpa.get(i));
                    s.setText(usn.get(i));
                    s.setTextSize(25);
                    q.setTextSize(25);
                    p.setText(name.get(i));
                    p.setTextSize(25);
                    p.setTextColor(R.color.colorPrimary);
                    r.addView(v);
                    r.addView(s);
                    r.addView(q);
                    tb.addView(r);


                }
            }
        });

        return view;
    }


}
