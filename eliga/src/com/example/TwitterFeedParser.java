package com.example;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;
import java.util.List;

public class TwitterFeedParser {

    public List<Tweet> parse(InputStream content) throws RuntimeException {
        try {
            InputSource is = new InputSource(content);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader xmlreader = parser.getXMLReader();
            TwitterFeedHandler tfh = new TwitterFeedHandler();

            xmlreader.setContentHandler(tfh);
            xmlreader.parse(is);
            return tfh.getResults();
        } catch (Exception e) {
            throw new RuntimeException("Failed while parsing twitter feed", e);
        }
    }
}
