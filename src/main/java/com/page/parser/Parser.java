package com.page.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class Parser {
    private static final String UTF_8 = "utf-8";

    private String path;
    private Attribute attribute;

    public Parser(String path, Attribute attribute) {
        this.path = path;
        this.attribute = attribute;
    }

    public Elements getElementsByAttributeValue() throws IOException {
        Document document = Jsoup.parse(new File(path), UTF_8);

        return document.getElementsByAttributeValue(attribute.getKey(), attribute.getValue());
    }

}
