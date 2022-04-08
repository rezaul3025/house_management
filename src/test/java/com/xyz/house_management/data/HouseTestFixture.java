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

    public static final String HOUSE_DATA_INVALID_DESC = "{\n" +
            "\"title\":\"test title\",\n" +
            "\"location\":\"test location\",\n" +
            "\"constructionYear\":1970,\n" +
            "\"numberOfFloor\":3,\n" +
            "\"totalSizeInSqm\":1000.00\n" +
            "}";

    public static final String HOUSE_DATA_INVALID_TITLE = "{\n" +
            "\"description\":\"test description\",\n" +
            "\"location\":\"test location\",\n" +
            "\"constructionYear\":1970,\n" +
            "\"numberOfFloor\":3,\n" +
            "\"totalSizeInSqm\":1000.00\n" +
            "}";

    public static final String HOUSE_DATA_INVALID_LOCATION = "{\n" +
            "\"title\":\"test title\",\n" +
            "\"description\":\"test description\",\n" +
            "\"constructionYear\":1970,\n" +
            "\"numberOfFloor\":3,\n" +
            "\"totalSizeInSqm\":1000.00\n" +
            "}";

    public static final String HOUSE_DATA_INVALID_CONS_YEAR = "{\n" +
            "\"title\":\"test title\",\n" +
            "\"description\":\"test description\",\n" +
            "\"location\":\"test location\",\n" +
            "\"numberOfFloor\":3,\n" +
            "\"totalSizeInSqm\":1000.00\n" +
            "}";

    public static final String HOUSE_DATA_INVALID_NO_OF_FLOOR = "{\n" +
            "\"title\":\"test title\",\n" +
            "\"description\":\"test description\",\n" +
            "\"location\":\"test location\",\n" +
            "\"constructionYear\":1970,\n" +
            "\"totalSizeInSqm\":1000.00\n" +
            "}";

    public static final String HOUSE_DATA_INVALID_SIZE = "{\n" +
            "\"title\":\"test title\",\n" +
            "\"description\":\"test description\",\n" +
            "\"location\":\"test location\",\n" +
            "\"constructionYear\":1970,\n" +
            "\"numberOfFloor\":3\n" +
            "}";
}
