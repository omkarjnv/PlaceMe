package com.example.placement;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class AdminJobsFragment extends Fragment {
    EditText id,des,date1,title;
    Button post;
    private DatabaseReference mDatabase;
    final Calendar myCalendar=Calendar.getInstance();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_admin_jobs, container, false);
        id = (EditText) view.findViewById(R.id.no);
        des = (EditText) view.findViewById(R.id.usn);
        date1 = (EditText) view.findViewById(R.id.date_ret);
        title=(EditText) view.findViewById(R.id.jid);
        post=(Button) view.findViewById(R.id.add);
        mDatabase= FirebaseDatabase.getInstance().getReference();

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(),date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        return view;
    }

    DatePickerDialog.OnDateSetListener date=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR,year);
            myCalendar.set(Calendar.MONTH,month);
            myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
            UpdateLabel();
        }
    };
    private void UpdateLabel() {
        String myFormat="MM/dd/yy";
        SimpleDateFormat sdf=new SimpleDateFormat(myFormat, Locale.US);
        date1.setText(sdf.format(myCalendar.getTime()));
    }

}
