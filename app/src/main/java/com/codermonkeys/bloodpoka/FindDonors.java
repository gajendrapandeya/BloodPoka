package com.codermonkeys.bloodpoka;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.codermonkeys.bloodpoka.Users.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class FindDonors extends Fragment {

    private DatabaseReference db = FirebaseDatabase.getInstance().getReference();
    private ListView listView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.find_donors, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //login user

        listView = view.findViewById(R.id.listView);
        ArrayAdapter<String > adapter = new ArrayAdapter<String >(getContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.array));

        listView.setAdapter(adapter);


    }
}

