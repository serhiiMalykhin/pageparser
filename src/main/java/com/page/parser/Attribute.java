package com.page.parser;

public class Attribute {
    /**
     * Pattern parse element for key and value.
     */
    private static final String ELEMENT_PARSER = "(.*)=(.*)";
    private static final String EQUALS = "=";

    private String key;
    private String value;
    private String originValue;

    public Attribute(String key, String value, String originValue) {
        this.key = key;
        this.value = value;
        this.originValue = originValue;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public String getOriginValue() {
        return originValue;
    }

    public static Attribute getInstanceByAttributeValue(String element) {
        if(element == null || !element.matches(ELEMENT_PARSER)){
            throw new IllegalArgumentException("Check your params");
        }
        String[] dataElements = element.split(EQUALS);
        String key = dataElements[0];
        String value = dataElements[1];
        return new Attribute(key, value, element);
    }

    public static Attribute getInstanceByQuery(String query) {
        if(query == null ){
            throw new IllegalArgumentException("Check your params");
        }
        return new Attribute(null, null, query);
    }

}
