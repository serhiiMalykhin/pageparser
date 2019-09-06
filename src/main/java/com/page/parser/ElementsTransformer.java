package com.page.parser;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Iterator;

public class ElementsTransformer {

    private static final char COMA = ',';
    private static final char EQUALS = '=';
    private static final char SQUARE_BRACKET_BACK = ']';
    private static final char SQUARE_BRACKET_FORCE = '[';

    public static String geSelectAttributeQuery(Elements sourceElement) {
        if (sourceElement.size() == 0) {
            throw new IllegalArgumentException("Element was not found");
        }
        StringBuilder result = new StringBuilder();

        Element element = sourceElement.first();
        String tagName = element.tagName();
        for (Iterator<Attribute> iterator = element.attributes().iterator(); iterator.hasNext(); ) {
            Attribute attribute = iterator.next();
            result.append(tagName)
                    .append(SQUARE_BRACKET_FORCE)
                    .append(attribute.getKey())
                    .append(EQUALS)
                    .append(attribute.getValue())
                    .append(SQUARE_BRACKET_BACK);
            if(iterator.hasNext()) {
                result.append(COMA);
            }
        }

        return result.toString();
    }
}
