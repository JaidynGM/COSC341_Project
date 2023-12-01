package com.example.harvesthub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class BrowseVendor extends AppCompatActivity {

    Spinner vendorName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_vendor);

        //initialize spinner
        vendorName = (Spinner) findViewById(R.id.spinner);

        //getting the information for spinners
        ArrayAdapter<CharSequence> venName = ArrayAdapter.createFromResource(this, R.array.vendorName,
                android.R.layout.simple_spinner_item);

        //allow for the spinners to drop down
        venName.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //set the adapters
        vendorName.setAdapter(venName);
    }
}