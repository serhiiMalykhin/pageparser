package com.page.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Parser {
    private String path;
    private String query;
    private Attribute attribute;

    public Parser(String path, String query) {
        this.path = path;
        this.query = query;
    }

    public Parser(String path, Attribute attribute) {
        this.path = path;
        this.attribute = attribute;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Elements getElementsByAttributeValue() {
        try {
            Document document = Jsoup.connect(path).get();
            return document.getElementsByAttributeValue(attribute.getKey(), attribute.getValue());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Elements getElementsByQuery() {
        try {
            Document document = Jsoup.connect(path).get();

            return document.select(query);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
