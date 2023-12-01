package com.example.harvesthub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import java.io.FileOutputStream;

public class addProduct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        //define ouputstream
        FileOutputStream outputStream;

        //create temporary text files with some contents
        String vendor1 = "Buggers BlueBerries.txt";
        String fileContents1 = "BlueBerries $2.00/lb, StrawBerries $2.20/lb, Raspberries $3.00/lb" ;

        String vendor2 = "Dannys Delights.txt";
        String fileContents2 = "Waffles, Pancakes, Crepes" ;

        String vendor3 = "Simple Strawberries.txt";
        String fileContents3 = "Strawberries, Blackberries, Cherries" ;

        String vendor4 = "Beady Bananas.txt";
        String fileContents4 = "Bananas, Pineapples, Mangoes" ;

        //output stream for text files
        try {
            outputStream = openFileOutput(vendor1, Context.MODE_APPEND);
            outputStream.write(fileContents1.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            outputStream = openFileOutput(vendor2, Context.MODE_APPEND);
            outputStream.write(fileContents2.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            outputStream = openFileOutput(vendor3, Context.MODE_APPEND);
            outputStream.write(fileContents3.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            outputStream = openFileOutput(vendor4, Context.MODE_APPEND);
            outputStream.write(fileContents4.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void back (View view){
        finish();
    }
}