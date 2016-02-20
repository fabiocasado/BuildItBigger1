package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.haazzz.comedycentral.JokeActivity;


public class MainActivity extends ActionBarActivity {
    private ProgressBar mProgressBar;
    private IAdsFragment mAdsFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdsFragment = (IAdsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mProgressBar.setIndeterminate(true);
        mProgressBar.setVisibility(View.GONE);
    }

    public void tellJoke(View view) {
        mProgressBar.setVisibility(View.VISIBLE);

        EndpointAsyncTask.JokeReceiver receiver = new EndpointAsyncTask.JokeReceiver() {
            @Override
            public void onJokeReceived(String joke) {
                mProgressBar.setVisibility(View.GONE);

                if (mAdsFragment.shouldShowIntersitialAd()) {
                    mAdsFragment.showIntersitialAd(joke);
                } else {
                    showJoke(joke);
                }
            }
        };

        EndpointAsyncTask jokeTask = new EndpointAsyncTask(receiver);
        jokeTask.execute();
    }

    public void showJoke(String joke) {
        Intent tellJokeIntent = new Intent(MainActivity.this, JokeActivity.class);
        tellJokeIntent.putExtra(JokeActivity.EXTRA_JOKE, joke);
        startActivity(tellJokeIntent);
    }

}
