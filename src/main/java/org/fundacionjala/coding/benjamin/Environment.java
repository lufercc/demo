package org.fundacionjala.coding.benjamin;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

/**
 * @author Benjamin Huanca on 10/17/2019.
 * @version 1.0
 */
public class Environment {
    private static Environment instance = new Environment();
    private DocumentContext jsonContext;

    private Environment() {
        JSONParser parser = new JSONParser();
        try (InputStream inputStream = new FileInputStream("config.json")) {
            Reader fileReader = new InputStreamReader(inputStream);
            JSONObject jsonObject = (JSONObject) parser.parse(fileReader);
            jsonContext = JsonPath.parse(jsonObject);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static Environment getInstance() {
        return instance;
    }

    public String getValue(final String keyJsonPath){
        return jsonContext.read(keyJsonPath);
    }
}
