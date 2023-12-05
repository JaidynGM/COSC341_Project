package com.example.harvesthub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Checkout extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
    }

    public void checkOut (View view){

        //Toast variables
        int duration = Toast.LENGTH_SHORT;
        CharSequence text = "";

        //create intent, will take products to checkout page
        Intent done = new Intent(this, Homepage.class);

        //get first name to string
        EditText firstName = findViewById(R.id.firstName);
        String firstname = firstName.getText().toString();

        //get last name to string
        EditText lastName = findViewById(R.id.lastName);
        String lastname = lastName.getText().toString();

        //get address to string
        EditText address = findViewById(R.id.address1);
        String Address = address.getText().toString();

        //get postal code to string
        EditText postalCode = findViewById(R.id.postalCode);
        String postalcode = postalCode.getText().toString();

        //get city to string
        EditText city = findViewById(R.id.city);
        String City = city.getText().toString();

        //get Province to string
        EditText province = findViewById(R.id.province);
        String Province = province.getText().toString();

        //get card number to string
        EditText cardNumber = findViewById(R.id.cardNumber);
        String cardnumber = cardNumber.getText().toString();


        //get date to string
        EditText date = findViewById(R.id.date);
        String Date = date.getText().toString();

        //get cvv to string
        EditText cvv = findViewById(R.id.cvv);
        String CVV = cvv.getText().toString();

        //Check all fields to see if they have entered information, card number has to be 16 numbers long
        if (firstname.isEmpty()) {
            text = "Please enter your first name";
            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
        }else if (lastname.isEmpty()) {
            text = "Please enter your last name";
            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
        }else if (Address.isEmpty()) {
            text = "Please enter at least one address line";
            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
        }else if (postalcode.isEmpty()) {
            text = "Please enter your postal code";
            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
        }else if (City.isEmpty()) {
            text = "Please enter your city";
            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
        }else if (Province.isEmpty()) {
            text = "Please enter your province";
            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
        }else if (cardnumber.length() != 16) {
            text = "Please enter a valid credit card number";
            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
        }else if (Date.isEmpty()) {
            text = "Please enter your credit card date";
            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
        }else if (CVV.length() != 3) {
            text = "Please enter a valid CVV";
            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
        }else {
            text = "Your order has been successful!";
            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
            startActivity(done);
        }
    }
}