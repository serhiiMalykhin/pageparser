package com.page.parser;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class App {

    public static void main(String[] args) throws IOException {
        File file;
        if (args.length == 1 && (file = new File(args[0])).exists()) {
            Properties prop = getProperties(file);

            startParse(getSourcePage(prop), getSourceAttribute(prop), getRelatedSourcePage(prop), getResultFile(prop));

        } else if (args.length == 2) {
            String sourcePage = args[0];
            String relatedSourcePage = args[1];
            String defaultPatternAttribute = "id=\"make-everything-ok-button\"";
            String outputFile = "result.txt";

            startParse(sourcePage, defaultPatternAttribute, relatedSourcePage, outputFile);
        } else {
            System.out.println("Check your path to property file");
            return;
        }
    }

    private static void startParse(String sourcePage,
                                   String patternSourceAttribute,
                                   String relatedSourcePage,
                                   String outputFileName) throws IOException {

        Elements sourceElement = new Parser(sourcePage, Attribute.getInstanceByAttributeValue(patternSourceAttribute))
                .getElementsByAttributeValue();

        CaseParser caseParser = new CaseParser(sourceElement, relatedSourcePage);

        List<Element> result = caseParser.parse();

        new OutputData(outputFileName, result)
                .save();
    }

    private static Properties getProperties(File propertyPath) {
        try (InputStream input = new FileInputStream(propertyPath)) {
            Properties prop = new Properties();
            prop.load(input);
            return prop;
        } catch (IOException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    private static String getSourceAttribute(Properties prop) {
        return prop.getProperty("sourceAttribute");
    }

    private static String getSourcePage(Properties prop) {
        return prop.getProperty("sourcePage");
    }

    private static String getRelatedSourcePage(Properties prop) {
        return prop.getProperty("relatedSourcePage");
    }

    private static String getResultFile(Properties prop) {
        return prop.getProperty("resultFile");
    }
}