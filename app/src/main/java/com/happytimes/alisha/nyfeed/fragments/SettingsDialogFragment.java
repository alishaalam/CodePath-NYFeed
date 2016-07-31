package com.happytimes.alisha.nyfeed.fragments;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.happytimes.alisha.nyfeed.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by alishaalam on 7/29/16.
 */

public class SettingsDialogFragment extends DialogFragment implements DatePickerFragment.DatePickerFragmentListener{

    public TextView tvBeginDate;
    public TextView tvEndDate;

    public SettingsDialogFragment() {
    }

    public static SettingsDialogFragment newInstance(String date) {

        Bundle args = new Bundle();
        args.putString("begin_date", date);

        SettingsDialogFragment fragment = new SettingsDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container);
    }


    @Override
    public void onViewCreated(View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvBeginDate = (TextView) view.findViewById(R.id.tv_begin_date);
        tvEndDate = (TextView) view.findViewById(R.id.tv_end_date);

        String title = getArguments().getString("title", "Settings");
        getDialog().setTitle(title);
        // Show soft keyboard automatically and request focus to field
        tvBeginDate.requestFocus();

        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        String today = formatter.format(Calendar.getInstance().getTime());
        tvBeginDate.setText(today);
        tvEndDate.setText(today);

        tvBeginDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "On click of text view", Toast.LENGTH_SHORT).show();
                onCreateDatePickerDialog(v);
                Log.d("Settings", "onCreate dialog called");
            }
        });
    }


    public Dialog onCreateDatePickerDialog(View v) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Activity needs to implement this interface
        DatePickerDialog.OnDateSetListener listener = (DatePickerDialog.OnDateSetListener) getActivity();

        // Create a new instance of TimePickerDialog and return it
        return new DatePickerDialog(getActivity(), listener, year, month, day);
    }

    @Override
    public void onFinishedDatePicking() {
        Toast.makeText(getActivity(), "On click of onFinishedDatePicking", Toast.LENGTH_SHORT).show();
    }
}
