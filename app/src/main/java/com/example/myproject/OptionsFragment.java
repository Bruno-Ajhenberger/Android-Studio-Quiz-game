package com.example.myproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class OptionsFragment extends Fragment {

    private OnClickInterface onClickInterface;
    private TextView tvCategory1,tvCategory2 ,tvCategory3, tvCategory4,tvCategory5,tvCategory6,tvCategory7,tvCategory8,tvCategory9,tvCategory10,tvCategory11,tvProfile;
    private CheckBox cbCategory1, cbCategory2,cbCategory3,cbCategory4,cbCategory5,cbCategory6,cbCategory7,cbCategory8,cbCategory9,cbCategory10,cbCategory11;
    private Button btnSave;
    private List<Boolean> checked;
    private int checkedCounter;


    public OptionsFragment(OnClickInterface onClickInterface){
        this.onClickInterface = onClickInterface;
    }


    public static OptionsFragment newInstance(OnClickInterface onClickInterface) {
        OptionsFragment fragment = new OptionsFragment(onClickInterface);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_options_fragent, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeUI(view);

    }

    private void initializeUI(View view) {
        checked = new ArrayList<Boolean>(Arrays.asList(new Boolean[12]));
        tvCategory1 = view.findViewById(R.id.tvCategory1);
        tvCategory2 = view.findViewById(R.id.tvCategory2);
        tvCategory3 = view.findViewById(R.id.tvCategory3);
        tvCategory4 = view.findViewById(R.id.tvCategory4);
        tvCategory5 = view.findViewById(R.id.tvCategory5);
        tvCategory6 = view.findViewById(R.id.tvCategory6);
        tvCategory7 = view.findViewById(R.id.tvCategory7);
        tvCategory8 = view.findViewById(R.id.tvCategory8);
        tvCategory9 = view.findViewById(R.id.tvCategory9);
        tvCategory10 = view.findViewById(R.id.tvCategory10);
        tvCategory11 = view.findViewById(R.id.tvCategory11);
        cbCategory1 = view.findViewById(R.id.cbCategory1);
        cbCategory2 = view.findViewById(R.id.cbCategory2);
        cbCategory3 = view.findViewById(R.id.cbCategory3);
        cbCategory4 = view.findViewById(R.id.cbCategory4);
        cbCategory5 = view.findViewById(R.id.cbCategory5);
        cbCategory6 = view.findViewById(R.id.cbCategory6);
        cbCategory7 = view.findViewById(R.id.cbCategory7);
        cbCategory8 = view.findViewById(R.id.cbCategory8);
        cbCategory9 = view.findViewById(R.id.cbCategory9);
        cbCategory10 = view.findViewById(R.id.cbCategory10);
        cbCategory11 = view.findViewById(R.id.cbCategory11);
        btnSave = view.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkedCounter=0;
                checked.set(0,true);

            //region checkbox if statements

            if(cbCategory1.isChecked()){
                checked.set(1,true);
            }
            else{
                checkedCounter ++;
                checked.set(1,false);
            }
            if(cbCategory2.isChecked()){
                    checked.set(2,true);
                }
            else{
                    checkedCounter ++;
                checked.set(2,false);
                }
            if(cbCategory3.isChecked()){
                    checked.set(3,true);
                }
            else{
                    checkedCounter ++;
                checked.set(3,false);
                }
            if(cbCategory4.isChecked()){
                    checked.set(4,true);
                }
            else{
                    checkedCounter ++;
                checked.set(4,false);
                }
            if(cbCategory5.isChecked()){
                    checked.set(5,true);
                }
            else{
                    checkedCounter ++;
                checked.set(5,false);
                }
            if(cbCategory6.isChecked()){
                    checked.set(6,true);
                }
            else{
                    checkedCounter ++;
                checked.set(6,false);
                }
            if(cbCategory7.isChecked()){
                    checked.set(7,true);
                }
            else{
                    checkedCounter ++;
                checked.set(7,false);
                }
            if(cbCategory8.isChecked()){
                    checked.set(8,true);
                }
            else{
                    checkedCounter ++;
                checked.set(8,false);
                }
            if(cbCategory9.isChecked()){
                    checked.set(9,true);
                }
            else{
                    checkedCounter ++;
                checked.set(9,false);
                }
            if(cbCategory10.isChecked()){
                    checked.set(10,true);
                }
            else{
                    checkedCounter ++;
                checked.set(10,false);
                }
            if(cbCategory11.isChecked()){
                    checked.set(11,true);
                }
            else{
                    checkedCounter ++;
                checked.set(11,false);
                }

            //endregion

                if(checkedCounter <= 4){
                        onClickInterface.saveButtonClicked( checked);
                }
                else{
                    Toast.makeText(getActivity(),"You can only deselect 4 categories", Toast.LENGTH_LONG).show();
                    ResetCheckBoxes();
                }

            }
        });

    }

    private void ResetCheckBoxes() {
        cbCategory1.setChecked(true);
        cbCategory2.setChecked(true);
        cbCategory3.setChecked(true);
        cbCategory4.setChecked(true);
        cbCategory5.setChecked(true);
        cbCategory6.setChecked(true);
        cbCategory7.setChecked(true);
        cbCategory8.setChecked(true);
        cbCategory9.setChecked(true);
        cbCategory10.setChecked(true);
        cbCategory11.setChecked(true);

    }
}