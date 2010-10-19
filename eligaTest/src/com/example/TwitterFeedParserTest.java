package com.example;

import com.example.TwitterFeedParser;

import java.io.InputStream;

import junit.framework.TestCase;

public class TwitterFeedParserTest extends TestCase {

    public void testExtractTweetsFromTwitterEntries() {
    	System.out.println("HEllo");
        InputStream stream = multipleTwitterEntries();
        TwitterFeedParser parser = new TwitterFeedParser();
        parser.parse(stream);
        assertTrue(true);
    }

    private InputStream multipleTwitterEntries() {
        return null;
    }
}
