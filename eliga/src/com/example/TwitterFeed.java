package com.example;

import android.app.ListActivity;
import android.net.http.AndroidHttpClient;
import android.os.Bundle;
import android.util.Log;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TwitterFeed extends ListActivity {
    public HttpResponse response;
    protected static final String DESCRIPTION = "description";
    protected static final String IMAGE_URL = "imageUrl";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        makeTwitterRequest();
        List<Tweet> tweets = examineXMLFile(response);

        setListAdapter(new ImageAdapter(this, tweets));
    }

    private void makeTwitterRequest() {
        Runnable searchRequest = new Runnable() {
            public void run() {
                HttpUriRequest request = new HttpGet("http://search.twitter.com/search.atom?q=%23sachin");
                HttpClient client = AndroidHttpClient.newInstance("Mozilla/5.0");
                try {
                    Log.d("mylog", "start request");
                    response = client.execute(request);
                    Log.d("mylog", "end request");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(searchRequest);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private List<Tweet> examineXMLFile(HttpResponse httpResponse) {
        try {
            TwitterFeedParser twitterFeedParser = new TwitterFeedParser();
            return twitterFeedParser.parse(httpResponse.getEntity().getContent());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<Tweet>();
    }

}
