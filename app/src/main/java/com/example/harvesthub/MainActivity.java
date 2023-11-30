package com.example.harvesthub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login (View view){
        //Create intent for going to different pages
        Intent intent = new Intent(this, Homepage.class);

        //Toast variables
        int duration = Toast.LENGTH_SHORT;
        CharSequence text = "";

        //get email to string
        EditText email = findViewById(R.id.emailText);
        String Email = email.getText().toString();

        //get password to string
        EditText password = findViewById(R.id.passwordText);
        String passWord = password.getText().toString();

        //Check to see if a username or password has been typed
        if (Email.isEmpty()){
            text = "Please enter your email";
            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
        } else if (passWord.isEmpty()){
            text = "Please enter your password";
            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
        }else{
            startActivity(intent);
        }
    }
}