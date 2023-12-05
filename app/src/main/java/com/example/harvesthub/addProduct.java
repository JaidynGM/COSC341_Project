package com.example.harvesthub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.FileOutputStream;

public class addProduct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
    }

    public void back (View view){
        finish();
    }

    public void addProduct (View view){

        //define ouputstream
        FileOutputStream outputStream;

        //get vendor name to string
        EditText Vendorname = findViewById(R.id.storeNameText);
        String vendorName = Vendorname.getText().toString();

        //get price to string
        EditText price = findViewById(R.id.priceText);
        String Price = price.getText().toString();

        //get product name to string
        EditText productName = findViewById(R.id.productNameText);
        String ProductName = productName.getText().toString();

        String filename = vendorName + ".txt";
        String filecontents = ProductName + " $" + Price + ".00,";

        try {
            outputStream = openFileOutput(filename, Context.MODE_APPEND);
            outputStream.write(filecontents.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        finish();
    }
}