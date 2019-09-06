package com.page.parser;

import org.jsoup.nodes.Element;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class OutputData {

    private static final String EMPTY = " ";
    private static final String LESS = ">";
    private static final int ZERO = 0;

    private String fileName;
    private List<Element> elements;

    public OutputData(String fileName, List<Element> elements) {
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

    private void write(List<Element> elements, BufferedWriter writer) throws IOException {
        for (Element element : elements) {
            StringBuilder stringBuilder = new StringBuilder();
            buildPath(element, stringBuilder);
            writer.write(stringBuilder.toString());
            writer.newLine();
        }
    }

    private void buildPath(Element element, StringBuilder stringBuilder) {
        if (!element.tagName().contains("root")) {
            stringBuilder.insert(ZERO, element.tagName());
            if (element.parent() != null && !element.tagName().contains("html")) {
                stringBuilder.insert(ZERO, EMPTY);
                stringBuilder.insert(ZERO, LESS);
                stringBuilder.insert(ZERO, EMPTY);
                buildPath(element.parent(), stringBuilder);
            }
        }
    }
}
