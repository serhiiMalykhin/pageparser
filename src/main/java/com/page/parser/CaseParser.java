package com.page.parser;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class CaseParser {
    private Elements sourceElement;
    private String urlCasePage;

    public CaseParser(Elements sourceElement, String urlCasePage) {
        this.sourceElement = sourceElement;
        this.urlCasePage = urlCasePage;
    }

    public List<Element> parse() {
        String query = ElementsTransformer.geSelectAttributeQuery(sourceElement);
        Parser parser = new Parser(urlCasePage, query);
        List<Element> elements = new ArrayList<>();
        Elements pageResult = parser.getElementsByQuery();
        if (pageResult.size() > 0) {
            elements.addAll(pageResult);
        }

        return elements;
    }
}
