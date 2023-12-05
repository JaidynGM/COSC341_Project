package com.example.harvesthub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Messaging extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String venName = bundle.getString("vendorName");

        //initializing Buttons and EditText
        Button back = findViewById(R.id.back);
        Button send = findViewById(R.id.send);
        Button view = findViewById(R.id.viewMessages);
        EditText message = findViewById(R.id.message);

        //Back button goes back to previous screen
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Send button calls write method
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMessageToFile(message);
            }
        });

        //View button calls read method
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readMessageFromFile(venName);
            }
        });
    }

    //Saves message to file
    public void saveMessageToFile(EditText message) {

        //File names
        String messageFile = "message.txt";
        String sentMessage = message.getText().toString() + "\n";

        //Try adding the string into the file, appending if needed
        try {
            FileOutputStream fos = openFileOutput(messageFile, Context.MODE_APPEND);
            fos.write(sentMessage.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //Read file
    public List<String> readMessageFromFile(String vendorName) {

        List<String> Messages = new ArrayList<>();
        //File names
        String messageFile = vendorName + ".txt";

        try {
            FileInputStream fos = openFileInput(messageFile);
            String line;
            InputStreamReader inputStreamReader = new InputStreamReader(fos);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while ((line = bufferedReader.readLine()) != null) {
                Messages.add(line);
            };
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Messages;
    }
}