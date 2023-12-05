package com.example.harvesthub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class checklist extends AppCompatActivity {

    Spinner vendorName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist);

        //initialize spinner
        vendorName = (Spinner) findViewById(R.id.checklist);

        //getting the information for spinners
        ArrayAdapter<CharSequence> venName = ArrayAdapter.createFromResource(this, R.array.vendorName,
                android.R.layout.simple_spinner_item);

        //allow for the spinners to drop down
        venName.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //set the adapters
        vendorName.setAdapter(venName);
    }

    public void loadStore (View view){

        // Get the name of the vendor as a string
        String venName = vendorName.getSelectedItem().toString();

        String filename = venName + "checklist.txt";

        // Read words from the file into an array
        String[] wordsArray = readFromFile(filename);

        // Create a LinearLayout to hold the CheckBoxes
        LinearLayout checkBoxContainer = findViewById(R.id.checkBoxContainer);

        // Remove all views (checkboxes) from the existing container
        checkBoxContainer.removeAllViews();

        // Dynamically create a CheckBox for each word in the array
        for (String word : wordsArray) {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText(word);

            // Add the CheckBox to the LinearLayout
            checkBoxContainer.addView(checkBox);
        }

    }

    private String[] readFromFile(String fileName) {
        List<String> wordList = new ArrayList<>();

        try {
            FileInputStream fileInputStream = openFileInput(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // Split the line based on commas
                String[] words = line.split(",");

                // Add each word to the list
                Collections.addAll(wordList, words);
            }

            // Close the streams
            bufferedReader.close();
            inputStreamReader.close();
            fileInputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Convert the list to an array
        return wordList.toArray(new String[0]);
    }
}