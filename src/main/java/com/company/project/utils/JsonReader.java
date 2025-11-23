package com.company.project.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonReader {
    private static final String TEST_DATA_PATH = "src/test/resources/testdata/test-data.json";
    private static JsonNode rootNode;

    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            rootNode = mapper.readTree(new File(TEST_DATA_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getData(String section, String key) {
        return rootNode.path(section).path(key).asText();
    }
}
