package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by HaaZzZ on 12/02/16.
 */
public class EndpointAsyncTaskTest extends AndroidTestCase {

    @SmallTest
    public void testRetrievedJokeNotNull() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);

        EndpointAsyncTask.JokeReceiver jokeReceiver = new EndpointAsyncTask.JokeReceiver() {
            @Override
            public void onJokeReceived(String joke) {
                assertNotNull(joke);
                assertFalse(joke.isEmpty());

                signal.countDown();
            }
        };

        EndpointAsyncTask task = new EndpointAsyncTask(jokeReceiver);
        task.execute();


        signal.await(5, TimeUnit.SECONDS);
    }
}
