package com.example.harvesthub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class ReviewAdapter extends ArrayAdapter<Review> {

    public ReviewAdapter(Context context, List<Review> reviews) {
        super(context, 0, reviews);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Review review = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.review_item, parent, false);
        }

        TextView nameRatingCommentTextView = convertView.findViewById(R.id.nameRatingCommentTextView);
        RatingBar ratingBar = convertView.findViewById(R.id.reviewRatingBar);

        if (review != null) {
            String nameRatingComment = String.format("%s\nRating: %.1f\nComment: %s",
                    review.getName(), review.getRating(), review.getComment());

            nameRatingCommentTextView.setText(nameRatingComment);
            ratingBar.setRating(review.getRating());
        }

        return convertView;
    }
}
