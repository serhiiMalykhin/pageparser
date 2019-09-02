package com.page.parser;

import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class App {
    public static void main(String[] args) throws IOException {

        if (args.length > 0 && App.class.getResource(args[0]) != null) {

            Properties prop = getProperties(args[0]);

            String filePath = prop.getProperty("htmlFilePath");
            String outputFile = prop.getProperty("outputFile");
            String element = prop.getProperty("attribute");

            Parser parser = new Parser(filePath, Attribute.getInstanceByAttributeValue(element));

            Elements elements = parser.getElementsByAttributeValue();

            new OutputData(outputFile, elements).save();

        } else {
            System.out.println("Check your path to property file");
        }
    }

    private static Properties getProperties(String propertyPath) {
        File file = new File(App.class.getResource(propertyPath).getPath());
        try (InputStream input = new FileInputStream(file)) {
            Properties prop = new Properties();
            prop.load(input);
            return prop;
        } catch (IOException ex) {
            throw new IllegalArgumentException(ex);
        }
    }
}
