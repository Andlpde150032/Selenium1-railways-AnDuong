/**
 * @author      me
 * @version     1.0
 * @since       2025-11-18
 *
 * Test module: Test Data
 * Description: Utility class to read test data from a JSON file.
 */
package demo.utils;

import org.json.JSONObject;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import org.json.JSONTokener;

public class JsonDataReader {

    private final JSONObject testData;

    public JsonDataReader(String filePath) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath)) {
            if (inputStream == null) {
                throw new RuntimeException("Cannot find resource file: " + filePath);
            }
            JSONTokener tokener = new JSONTokener(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            this.testData = new JSONObject(tokener);
        } catch (Exception e) {
            throw new RuntimeException("Error reading JSON file: " + filePath, e);
        }
    }

    public String getTestData(String testCase, String key) {
        return testData.getJSONObject(testCase).getString(key);
    }
}
