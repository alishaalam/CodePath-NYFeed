package com.happytimes.alisha.nyfeed.fragments;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.happytimes.alisha.nyfeed.R;
import com.happytimes.alisha.nyfeed.model.SearchFilters;

import org.parceler.Parcels;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by alishaalam on 7/29/16.
 */

public class FilterDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener, View.OnClickListener {

    private SearchFilters mFilters;

    public TextView etBeginDate;
    public Spinner spSortOrder;
    Button btnSave;
    CheckBox cbArts;
    CheckBox cbFashion;
    CheckBox cbSports;
    public boolean artsChecked = false, fashionChecked = false, sportsChecked = false;

    public FilterDialogFragment() {
    }

    public static FilterDialogFragment newInstance(SearchFilters filters) {

        Bundle args = new Bundle();
        args.putParcelable("filters", Parcels.wrap(filters));
        FilterDialogFragment fragment = new FilterDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    // Define a listener interface with a method passing back filters as result to activity.
    public interface OnFilterSearchListener {
        void onUpdateFilters(SearchFilters filters);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_filter_settings, container);
    }


    @Override
    public void onViewCreated(View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mFilters = (SearchFilters) Parcels.unwrap(getArguments().getParcelable("filters"));

        etBeginDate = (TextView) view.findViewById(R.id.et_begin_date);
        String title = getArguments().getString("title", "Settings");
        getDialog().setTitle(title);

        // Show soft keyboard automatically and request focus to field
        etBeginDate.requestFocus();

        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        String today = formatter.format(Calendar.getInstance().getTime());
        etBeginDate.setText(today);
        etBeginDate.setOnClickListener(v -> showDatePickerDialog(v));

        spSortOrder = (Spinner) view.findViewById(R.id.sp_sort_order);
        // Create an ArrayAdapter using the string array and a default spSortOrder layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.sort_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spSortOrder
        spSortOrder.setAdapter(adapter);

        cbArts = (CheckBox) view.findViewById(R.id.cb_arts);
        cbFashion = (CheckBox) view.findViewById(R.id.cb_fashion_and_style);
        cbSports = (CheckBox) view.findViewById(R.id.cb_sports);

        cbArts.setOnClickListener(this);
        cbFashion.setOnClickListener(this);
        cbSports.setOnClickListener(this);

        btnSave = (Button) view.findViewById(R.id.btn_save);
        btnSave.setOnClickListener(this);


    }


    // attach to an onclick handler to show the date picker
    public void showDatePickerDialog(View v) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setTargetFragment(this, 300);
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");

    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        showDate(year, monthOfYear + 1, dayOfMonth);
    }

    private void showDate(int year, int month, int day) {
        etBeginDate.setText(new StringBuilder().append(year).append(month).append(day));
    }

    public void onClick(View v) {

        StringBuffer buffer = new StringBuffer();
        switch (v.getId()) {
            case R.id.btn_save:
                // Update the mFilters attribute values based on the input views

                mFilters.beginDate = etBeginDate.getText().toString();
                mFilters.sort_order = spSortOrder.getSelectedItem().toString().toLowerCase();

                if(artsChecked)
                    buffer.append("\"Arts\" ");
                if(fashionChecked)
                    buffer.append("\"Fashion and Style\" ");
                if(sportsChecked)
                    buffer.append("\"Sports\" ");
                String newsDeskItems = buffer.toString();
                mFilters.news_desk_values =
                        String.format("news_desk:(%s)", newsDeskItems);

                // Return filters back to activity through the implemented listener
                OnFilterSearchListener listener = (OnFilterSearchListener) getActivity();
                listener.onUpdateFilters(mFilters);
                // Close the dialog to return back to the parent activity
                dismiss();
                break;
            case R.id.cb_arts:
                 artsChecked = ((CheckBox) v).isChecked();
                break;
            case R.id.cb_fashion_and_style:
                 fashionChecked = ((CheckBox) v).isChecked();
                break;
            case R.id.cb_sports:
                 sportsChecked = ((CheckBox) v).isChecked();
                break;
        }
    }
}
