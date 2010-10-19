package com.example.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.TwitterFeed;

public class TwitterFeedTest extends ActivityInstrumentationTestCase2<TwitterFeed> {

    private TwitterFeed activity;
	private ListView listView;

	public TwitterFeedTest() {
        super("com.example", TwitterFeed.class);
    }
    
    public void setUp() throws Exception {
    	super.setUp();
        activity = this.getActivity();
        listView = activity.getListView();
    }
    
    public void testShouldShowTwitterFeeds() {
    	ListAdapter listAdapter = listView.getAdapter();
    	assertTrue(listAdapter.getCount() > 0);
    }

}
