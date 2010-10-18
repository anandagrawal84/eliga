package com.example;

import org.junit.Test;

import java.io.InputStream;

public class TwitterFeedParserTest {

    @Test
    public void shouldExtractTweetsFromTwitterEntries() {
        InputStream stream = multipleTwitterEntries();
        TwitterFeedParser parser = new TwitterFeedParser();
    }

    private InputStream multipleTwitterEntries() {
        return null;
    }
}
