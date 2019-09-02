package com.page.parser;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class OutputData {

    private static final String EMPTY = " ";
    private static final String LESS = ">";
    private static final int ZERO = 0;

    private String fileName;
    private Elements elements;

    public OutputData(String fileName, Elements elements) {
        this.elements = elements;
        this.fileName = fileName;
    }

    public void save() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        write(writer);
        writer.close();
    }

    private void write(BufferedWriter writer) throws IOException {
        write(elements, writer);
    }

    private void write(Elements elements, BufferedWriter writer) throws IOException {
        for (int i = 0; i < elements.size(); i++) {
            StringBuilder stringBuilder = new StringBuilder();
            buildPath(elements.get(i), stringBuilder);
            writer.write(stringBuilder.toString());
            writer.newLine();
        }
    }

    private StringBuilder buildPath(Element element, StringBuilder stringBuilder) {
        stringBuilder.insert(ZERO, element.tagName());
        stringBuilder.insert(ZERO, EMPTY);
        if (element.parent() != null) {
            stringBuilder.insert(ZERO, LESS);
            stringBuilder.insert(ZERO, EMPTY);
            buildPath(element.parent(), stringBuilder);
        }
        return stringBuilder;
    }
}
