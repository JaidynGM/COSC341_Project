package com.example.harvesthub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        String filename = "cart.txt";

        // Read words from the file into an array
        String[] wordsArray = readFromFile(filename);

        // Assuming you have a TableLayout with ID "yourTableLayoutId"
        TableLayout tableLayout = findViewById(R.id.cartText);

        // Clear existing rows in the TableLayout
        tableLayout.removeAllViews();

        // Create a new row for each word in the array
        for (String word : wordsArray) {
            TableRow row = new TableRow(this);

            // Create a TextView for each word
            TextView textView = new TextView(this);
            textView.setText(word);

            // Add the TextView to the TableRow
            row.addView(textView);

            // Add the TableRow to the TableLayout
            tableLayout.addView(row);
        }

    }

    public void checkout (View view){

        //create intent, will take products to checkout page
        Intent check = new Intent(this, Checkout.class);

        startActivity(check);
    }

    public void shop (View view){
        finish();
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