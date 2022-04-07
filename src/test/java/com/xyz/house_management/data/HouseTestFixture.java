package com.xyz.house_management.data;

public class HouseTestFixture {
    public static final String HOUSE_DATA = "{\n" +
            "\"title\":\"test title\",\n" +
            "\"description\":\"test description\",\n" +
            "\"location\":\"test location\",\n" +
            "\"constructionYear\":1970,\n" +
            "\"numberOfFloor\": 3,\n" +
            "\"totalSizeInSqm\":1000.00\n" +
            "}";

    public static final String HOUSE_DATA_INVALID = "{\n" +
            "\"title\":\"test title\",\n" +
            "\"location\":\"test location\",\n" +
            "\"constructionYear\":1970,\n" +
            "\"numberOfFloor\":3,\n" +
            "\"totalSizeInSqm\":1000.00\n" +
            "}";
}
