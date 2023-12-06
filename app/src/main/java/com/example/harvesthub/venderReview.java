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
    private String selectedVendor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vender_review);

        vendorView = findViewById(R.id.vendorView);
        addReviewButton = findViewById(R.id.addReview);
        backButton = findViewById(R.id.back);

        selectedVendor = selectedVendor();
        vendorView.setText("Vendor: " + selectedVendor);
        reviews = getReviewsFromFile();
        adapter = new ReviewAdapter(this, reviews);

        ListView reviewListView = findViewById(R.id.review);
        reviewListView.setAdapter(adapter);

        addReviewButton.setOnClickListener(v -> {
            Intent intent = new Intent(venderReview.this, addReview.class);
            String selectedVendor = selectedVendor();
            intent.putExtra("vendor", selectedVendor);
            startActivity(intent);
        });

        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(venderReview.this, BrowseVendor.class);
            startActivity(intent);
        });
    }

    private String selectedVendor(){
        return getIntent().getStringExtra("vendor");
    }


    private List<Review> getReviewsFromFile() {
        List<Review> reviewList = new ArrayList<>();
        try {
            InputStream inputStream = openFileInput("review.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String vendor = parts[0];
                    String name = parts[1];
                    float rating = Float.parseFloat(parts[2]);
                    String comment = parts[3];

                    if (vendor.equals(selectedVendor)) {
                        reviewList.add(new Review(vendor, name, rating, comment));
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reviewList;
    }

}