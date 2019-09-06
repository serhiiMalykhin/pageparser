package com.page.parser;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class App {
    private static Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws IOException {
        File file;
        if (args.length > 0 && (file = new File(args[0])).exists()) {
            Properties prop = getProperties(file);
            String relatedSourcePage = relatedSourcePage(prop);

            Elements sourceElement = new Parser(
                    getSourcePage(prop),
                    Attribute.getInstanceByAttributeValue(getSourceAttribute(prop)))
                    .getElementsByAttributeValue();

            CaseParser caseParser = new CaseParser(sourceElement, relatedSourcePage);
            List<Element> result = caseParser.parse();

            new OutputData(getResultFile(prop), result)
                    .save();

        } else {
            LOGGER.info("Check your path to property file");
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

    private static String getSourceAttribute(Properties prop) {
        return prop.getProperty("sourceAttribute");
    }

    private static String getSourcePage(Properties prop) {
        return prop.getProperty("sourcePage");
    }

    private static String relatedSourcePage(Properties prop) {
        return prop.getProperty("relatedSourcePage");
    }

    private static String getResultFile(Properties prop) {
        return prop.getProperty("resultFile");
    }
}