package com.example;

import android.app.ListActivity;
import android.net.http.AndroidHttpClient;
import android.os.Bundle;
import android.util.Log;
import android.widget.SimpleAdapter;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
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
            @Override
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

        Log.d("mylog", "end join");
    }

    private List<Tweet> examineXMLFile(HttpResponse httpResponse) {
        try {
            InputStream content = httpResponse.getEntity().getContent();
            InputSource is = new InputSource(content);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader xmlreader = parser.getXMLReader();
            TwitterFeedHandler tfh = new TwitterFeedHandler();

            xmlreader.setContentHandler(tfh);
            xmlreader.parse(is);
            return tfh.getResults();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<Tweet>();
    }

    public String convertStreamToString(InputStream is)
            throws IOException {
        /*
         * To convert the InputStream to String we use the
         * Reader.read(char[] buffer) method. We iterate until the
         * Reader return -1 which means there's no more data to
         * read. We use the StringWriter class to produce the string.
         */
        if (is != null) {
            Writer writer = new StringWriter();

            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(
                        new InputStreamReader(is, "UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            } finally {
                is.close();
            }
            return writer.toString();
        } else {
            return "";
        }
    }
}
