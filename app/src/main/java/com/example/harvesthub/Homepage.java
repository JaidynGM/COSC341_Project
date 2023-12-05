package com.example.harvesthub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
    }

    public void vendorPage (View view){
        Intent vendor = new Intent(this, BrowseVendor.class);
        startActivity(vendor);
    }

    public void addProduct (View view){
        Intent product = new Intent(this, addProduct.class);
        startActivity(product);
    }

    public void cart (View view){
        Intent cart = new Intent(this, Cart.class);
        startActivity(cart);
    }

    public void checklist (View view){
        Intent check = new Intent(this, checklist.class);
        startActivity(check);
    }
}