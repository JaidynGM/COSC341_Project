package com.example.harvesthub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BrowseVendor extends AppCompatActivity {

    Spinner vendorName;
    private String selectedAnswer;

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

    public void showInventory(View view) {

        //get the name of vendor to string
        String venName = vendorName.getSelectedItem().toString();

        String filename = venName + ".txt";

        // Read words from the file into an array
        String[] wordsArray = readFromFile(filename);

        // Create a RadioGroup
        RadioGroup radioGroup = new RadioGroup(this);

        // Dynamically create a RadioButton for each word in the array
        for (String word : wordsArray) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(word);

            // Add the RadioButton to the RadioGroup
            radioGroup.addView(radioButton);
        }

        // Add the RadioGroup to your layout
        LinearLayout radioGroupContainer = findViewById(R.id.radioGroup);

        // Remove all views (buttons) from the existing RadioGroup
        radioGroupContainer.removeAllViews();

        // Add the new RadioGroup to your layout
        radioGroupContainer.addView(radioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Find the selected RadioButton by ID
                RadioButton selectedRadioButton = findViewById(checkedId);

                // Get the text of the selected RadioButton
                selectedAnswer = (selectedRadioButton != null) ? selectedRadioButton.getText().toString() : null;
            }
        });
    }

    public void addCart(View view) {
        TextView test = findViewById(R.id.testView);
        test.setText(selectedAnswer);
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

    public void back (View view){
        finish();
    }
}
