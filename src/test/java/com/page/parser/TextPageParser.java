package com.page.parser;

import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;

@RunWith(Parameterized.class)
public class TextPageParser {
    private static String SOURCE_PAGE = "https://agileengine.bitbucket.io/beKIvpUlPMtzhfAy/samples/sample-0-origin.html";
    private static String SOURCE_FIND_ATTRIBUTE = "id=\"make-everything-ok-button\"";

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "https://agileengine.bitbucket.io/beKIvpUlPMtzhfAy/samples/sample-1-evil-gemini.html", 3},
                { "https://agileengine.bitbucket.io/beKIvpUlPMtzhfAy/samples/sample-2-container-and-clone.html", 2},
                { "https://agileengine.bitbucket.io/beKIvpUlPMtzhfAy/samples/sample-3-the-escape.html", 2},
                { "https://agileengine.bitbucket.io/beKIvpUlPMtzhfAy/samples/sample-4-the-mash.html", 1}
        });
    }

    private  String relatedPage;
    private int expectedResultCount;
    public TextPageParser(String relatedPage, int expectedResultCount) {
        this.relatedPage = relatedPage;
        this.expectedResultCount = expectedResultCount;
    }

    @Test
    public void testPageCases() {
        Elements sourceElement = new Parser(SOURCE_PAGE, Attribute.getInstanceByAttributeValue(SOURCE_FIND_ATTRIBUTE))
                .getElementsByAttributeValue();

        CaseParser caseParser = new CaseParser(sourceElement, relatedPage);
        Assert.assertThat(caseParser.parse().size(), is(expectedResultCount));

    }
}
