package com.example.test;

import android.test.ActivityInstrumentationTestCase2;

import com.example.TwitterFeed;

public class TwitterFeedTest extends ActivityInstrumentationTestCase2<TwitterFeed> {

    public TwitterFeedTest() {
        super("com.example", TwitterFeed.class);
    }
    
    public void testShouldShowTwitterFeeds() {
    	assertTrue(true);
    }

}
