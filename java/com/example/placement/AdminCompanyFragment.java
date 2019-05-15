package com.example.placement;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AdminCompanyFragment extends Fragment {

    private DatabaseReference mDatabase;
    EditText name, id;
    Button add;

    public AdminCompanyFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_admin_company, container, false);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        name = (EditText) view.findViewById(R.id.name);
        id = (EditText) view.findViewById(R.id.compid);
        add=(Button) view.findViewById(R.id.btnadd);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String coname=name.getText().toString();
                String coid=id.getText().toString();
                mDatabase.child("company").child(coid).setValue(coname);
            }
        });

        return view;
    }


}
