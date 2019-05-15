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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StudentHomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StudentHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentHomeFragment extends Fragment {

    DatabaseReference ref;
    ArrayList<jobs> list;
    RecyclerView recyclerView;

    public static ArrayList<String> keys=new ArrayList<>();




    public StudentHomeFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_student_home, container, false);

        recyclerView=(RecyclerView) view.findViewById(R.id.recycler);

        ref= FirebaseDatabase.getInstance().getReference().child("jobs");

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
                    AdapterClass adapterClass=new AdapterClass(list,keys);
                    LinearLayoutManager manager=new LinearLayoutManager(getActivity());
                    manager.setOrientation(LinearLayoutManager.VERTICAL);
                    adapterClass.notifyDataSetChanged();
                    recyclerView.setLayoutManager(manager);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setAdapter(adapterClass);
                    System.out.println(list.size());

                }


                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

//                    Toast.makeText(StudentHomeFragment.this, "Database Error", Toast.LENGTH_LONG).show();
                }
            });
        }

        return view;
    }


    @Override
    public void onStart(){
        super.onStart();


    }

    /*// TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    *//**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     *//*
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}
