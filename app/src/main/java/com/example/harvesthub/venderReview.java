package com.example.harvesthub;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class venderReview extends AppCompatActivity {

    private List<Review> reviews;
    private ArrayAdapter<Review> adapter;

    private TextView vendorView;
    private TextView reviewName;
    private RatingBar reviewRating;
    private TextView reviewComment;
    private Button addReviewButton;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vender_review);

        vendorView = findViewById(R.id.vendorView);
        addReviewButton = findViewById(R.id.addReview);
        backButton = findViewById(R.id.back);

        reviews = getReviewsFromFile();
        adapter = new ReviewAdapter(this, reviews);

        ListView reviewListView = findViewById(R.id.review);
        reviewListView.setAdapter(adapter);

        String selectedVendor = selectedVendor();
        vendorView.setText("Vendor: " + selectedVendor);

        addReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //to be added
            }
        });

        // Handle back button click
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Change to start new activity
            }
        });
    }

    private String selectedVendor(){
        return getIntent().getStringExtra("vendor");
    }

    private List<Review> getReviewsFromFile() {
        List<Review> reviewList = new ArrayList<>();
        try {
            InputStream inputStream = getAssets().open("reviews.txt"); // Dir File. Cannot be accessed by user. THis is an android studio thing
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0];
                    int rating = Integer.parseInt(parts[1]);
                    String comment = parts[2];

                    reviewList.add(new Review(name, rating, comment));
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reviewList;
    }

    private void addReviewToFile(Review newReview) { // THIS IS NOT WORKING YET
        try {
            FileOutputStream outputStream = openFileOutput("reviews.txt", MODE_APPEND);
            String reviewString = newReview.getName() + "," + newReview.getRating() + "," + newReview.getComment() + "\n";
            outputStream.write(reviewString.getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}