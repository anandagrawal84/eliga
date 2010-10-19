package com.example;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import junit.framework.TestCase;

public class TwitterFeedParserTest extends TestCase {

	public void testExtractTweetsFromTwitterEntries() throws IOException {
		InputStream stream =this.getClass().getResourceAsStream("sampleTwitterFeed.xml");
		TwitterFeedParser parser = new TwitterFeedParser();
		List<Tweet> tweets = parser.parse(stream);
		assertEquals(2, tweets.size());
	}

	private InputStream getInputStream(String twitterFeed) {
		try {
			return new ByteArrayInputStream(twitterFeed
					.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Coversion to input stream failed", e);
		}
	}
}
