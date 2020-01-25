package com.codermonkeys.bloodpoka;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.shashank.sony.fancytoastlib.FancyToast;

public class Requests extends Fragment {

    private EditText username, location, contactnumber;
    private Button requestButto;

    private ProgressDialog dialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.request, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        username = view.findViewById(R.id.username);
        location = view.findViewById(R.id.userlocation);
        contactnumber = view.findViewById(R.id.contactnumber);
        requestButto = view.findViewById(R.id.request);

        String userName = username.getText().toString();
        String userLocation = location.getText().toString();
        String userContactNumber = contactnumber.getText().toString();

        dialog = new ProgressDialog(getContext());

        requestButto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FancyToast.makeText(getContext(), "Requested Successfully", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS,false);
            }
        });
    }
}
