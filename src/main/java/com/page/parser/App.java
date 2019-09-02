package com.page.parser;

import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class App {
    private static Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws IOException {
        File file;
        if (args.length > 0 && (file = new File(args[0])).exists()) {
            Properties prop = getProperties(file);

            Elements elements = getElements(prop);

            String outputFile = prop.getProperty("outputFile");
            new OutputData(outputFile, elements).save();

        } else {
            LOGGER.warn("Check your path to property file");
        }
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

    private static Elements getElements(Properties prop) throws IOException {
        String filePath = prop.getProperty("htmlFilePath");
        String element = prop.getProperty("attribute");
        String queryParam = prop.getProperty("queryParam");
        String isSearchByQuery = prop.getProperty("isSearchByQuery");

        Parser parser;
        Elements elements;

        if (Boolean.valueOf(isSearchByQuery) == Boolean.TRUE) {
            parser = new Parser(filePath, Attribute.getInstanceByQuery(queryParam));
            elements = parser.getElementsByQuery();
        } else {
            parser = new Parser(filePath, Attribute.getInstanceByAttributeValue(element));
            elements = parser.getElementsByAttributeValue();
        }
        return elements;
    }
}
