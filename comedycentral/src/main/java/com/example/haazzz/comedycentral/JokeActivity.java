package com.example.haazzz.comedycentral;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {
    public static final String EXTRA_JOKE = "extra_joke";
    private static final String TAG = "JokeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        TextView jokeTextView = (TextView) findViewById(R.id.joke_textView);
        if (!getIntent().hasExtra(EXTRA_JOKE)) {
            jokeTextView.setText(R.string.error_joke);
            Log.e(TAG, "onCreate: Joke extra should always be present");
        } else {
            jokeTextView.setText(getIntent().getStringExtra(EXTRA_JOKE));
        }
    }
}
