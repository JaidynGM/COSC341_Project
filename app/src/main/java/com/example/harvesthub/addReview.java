package com.example.harvesthub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileOutputStream;
import java.io.IOException;


public class addReview extends AppCompatActivity {

    private EditText nameEnter;
    private TextView vendorView;

    private RatingBar ratingBar;
    private EditText commentEnter;
    private Button addReviewButton;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

        nameEnter = findViewById(R.id.nameEnter);
        ratingBar = findViewById(R.id.ratingBar);
        commentEnter = findViewById(R.id.commentEnter);
        addReviewButton = findViewById(R.id.addReview);
        backButton = findViewById(R.id.back);
        String selectedVendor = selectedVendor();

        vendorView = findViewById(R.id.vendorView);
        vendorView.setText("Vendor: " + selectedVendor);

        addReviewButton.setOnClickListener(v -> {
            String name = nameEnter.getText().toString().trim();
            float rating = (Float) ratingBar.getRating();
            String comment = commentEnter.getText().toString().trim();

            if (name.isEmpty() || comment.isEmpty()) {
//                    Toast.makeText("No data available in the File. Please add some data.", Toast.LENGTH_SHORT).show();
                return;
            }
            Review newReview = new Review(name, (int) rating, comment);
            addReviewToFile(newReview);


            Intent intent = new Intent(addReview.this, venderReview.class);
            intent.putExtra("vendor", selectedVendor);
            startActivity(intent);

        });

        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(addReview.this, venderReview.class);
            intent.putExtra("vendor", selectedVendor);
            startActivity(intent);
        });
    }

    private String selectedVendor(){
        return getIntent().getStringExtra("vendor");
    }
    private void addReviewToFile(Review newReview) {
        try {
            FileOutputStream outputStream = openFileOutput("review.txt", MODE_APPEND);
            String reviewString = newReview.getName() + "," + newReview.getRating() + "," + newReview.getComment() + "\n";
            outputStream.write(reviewString.getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
