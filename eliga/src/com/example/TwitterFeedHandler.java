package com.example;

import android.util.Log;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class TwitterFeedHandler extends DefaultHandler {

    StringBuilder sb = null;
    boolean bStore = false;
    private Tweet currentTweet;
    List<Tweet> tweets = new ArrayList<Tweet>();

    public TwitterFeedHandler() {
    }

    public List<Tweet> getResults() {
        return tweets;
    }

    @Override

    public void startDocument() throws SAXException {
        this.sb = new StringBuilder("");
        // initialize "list"
    }

    @Override
    public void endDocument() throws SAXException {

    }


    @Override
    public void startElement(String namespaceURI, String localName, String qName,
                             Attributes atts) throws SAXException {

        try {
            if (qName.equals("entry")) {
                currentTweet = new Tweet();
            }
            if (qName.equals("link")) {
                sb.append(atts.getValue("href"));
            }

        } catch (Exception ee) {

            Log.d("error in startElement", ee.getStackTrace().toString());
        }
    }


    @Override
    public void endElement(String namespaceURI, String localName, String qName)
            throws SAXException {
        if (currentTweet != null) {
            if (qName.equals("title")) {
                currentTweet.setTitle(sb.toString());
            }
            if (qName.equals("link")) {
                currentTweet.setImageUrl(sb.toString());
            }
            if (qName.equals("entry")) {
                tweets.add(currentTweet);
            }
        }

        sb.setLength(0);
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        super.characters(ch, start, length);
        sb.append(ch, start, length);
    }

}

