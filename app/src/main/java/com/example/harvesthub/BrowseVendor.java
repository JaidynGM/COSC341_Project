package com.example.harvesthub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.ArrayAdapter;
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

        String filename = "Buggers BlueBerries.txt";
        String fileContents = "This is a test";
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_APPEND);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showInventory(View view) {
        //get the name of vendor to string
        String venName = vendorName.getSelectedItem().toString();

        String filename = venName + ".txt";

        // Read from the file
        String fileContents = readFromFile(filename);

        TextView textView = findViewById(R.id.invView);
        textView.setText(fileContents);
    }

    private String readFromFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            FileInputStream fileInputStream = openFileInput(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }

            // Close the streams
            bufferedReader.close();
            inputStreamReader.close();
            fileInputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }
}
