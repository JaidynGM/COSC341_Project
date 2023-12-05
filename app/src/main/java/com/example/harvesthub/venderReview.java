package com.example.harvesthub;

import android.content.Intent;
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

        String selectedVendor = selectedVendor();
        vendorView.setText("Vendor: " + selectedVendor);
        reviews = getReviewsFromFile();
        adapter = new ReviewAdapter(this, reviews);



        ListView reviewListView = findViewById(R.id.review);
        reviewListView.setAdapter(adapter);

        addReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(venderReview.this, addReview.class);
                String selectedVendor = selectedVendor();
                intent.putExtra("vendor", selectedVendor);
                startActivity(intent);
            }
        });

        // Handle back button click
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private String selectedVendor(){
        return getIntent().getStringExtra("vendor");
    }

    private List<Review> getReviewsFromFile() {
        List<Review> reviewList = new ArrayList<>();
        try {
            InputStream inputStream = openFileInput("review.txt"); // Dir File. Cannot be accessed by user. THis is an android studio thing
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0];
                    float rating = Float.parseFloat(parts[1]);
                    String comment = parts[2];

                    reviewList.add(new Review(name, (int) rating, comment));
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reviewList;
    }


}