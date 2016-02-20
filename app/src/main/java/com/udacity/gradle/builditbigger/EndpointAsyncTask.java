package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.util.Log;

import com.example.haazzz.builditbigger.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by HaaZzZ on 12/02/16.
 */
public class EndpointAsyncTask extends AsyncTask<Void, Integer, String> {
    private static final String TAG = "EndpointAsyncTask";
    private static MyApi myApiService = null;
    private JokeReceiver mJokeReceiver;

    public EndpointAsyncTask(JokeReceiver jokeReceiver) {
        mJokeReceiver = jokeReceiver;
    }

    @Override
    protected String doInBackground(Void... params) {
        if (myApiService == null) {  // Only do this once
//            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
//                    new AndroidJsonFactory(), null)
//                    // options for running against local devappserver
//                    // - 10.0.2.2 is localhost's IP address in Android emulator
//                    // - 10.0.3.2 is localhost's IP address in Genymotion emulator
//                    // - turn off compression when running against local devappserver
//                    .setRootUrl("http://10.0.3.2:8080/_ah/api/")
//                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
//                        @Override
//                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
//                            abstractGoogleClientRequest.setDisableGZipContent(true);
//                        }
//                    });
            // end options for devappserver

            // options for real server
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://builditbigger-1227.appspot.com/_ah/api/");

            myApiService = builder.build();
        }

        try {
            return myApiService.tellJoke().execute().getData();
        } catch (IOException e) {
            Log.e(TAG, "doInBackground: ", e);
            return null;
        }
    }

    @Override
    protected void onPostExecute(String joke) {
        super.onPostExecute(joke);

        if (joke != null && mJokeReceiver != null) {
            mJokeReceiver.onJokeReceived(joke);
        }
    }

    public interface JokeReceiver {
        void onJokeReceived(String joke);
    }
}
