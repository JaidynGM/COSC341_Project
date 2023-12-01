package com.example.harvesthub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

            // You can set additional properties for the RadioButton if needed

            // Add the RadioButton to the RadioGroup
            radioGroup.addView(radioButton);
        }

        // Add the RadioGroup to your layout (assuming you have a LinearLayout with id "radioGroupContainer")
        LinearLayout radioGroupContainer = findViewById(R.id.radioGroupContainer);
        radioGroupContainer.addView(radioGroup);

        /* If you want to display the words in a TextView, you can use Arrays.toString(wordsArray)
        TextView textView = findViewById(R.id.invView);
        textView.setText(Arrays.toString(wordsArray));
         */
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
