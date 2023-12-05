package com.example.harvesthub;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class addReview extends AppCompatActivity {

    private TextView name, comment;
    private RatingBar rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);





    }
//
//    private void writeToFile(){
//        String name = name.getText().toString();
//        String comment = lastName.getText().toString();
//        int rate = gender.getCheckedRadioButtonId();
//
//        try{
//            PrintWriter out = new PrintWriter(new FileWriter(getFilesDir() +
//                    "/data.txt", true));
//            out.println(write);
//            out.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}