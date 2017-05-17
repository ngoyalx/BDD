package utils;

import com.google.gson.JsonObject;
import org.json.JSONObject;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

/**
 * Created by ngoyal on 5/11/2017.
 */
public class Utility {


    public void loadHashMap(HashMap<String,String> objHashMap,Properties objProp){

        for (final Map.Entry<Object, Object> entry : objProp.entrySet()) {
            objHashMap.put((String) entry.getKey(), (String) entry.getValue());
            LogUtility.log.info("");
        }
    }

    public static JSONObject readJSONFile(String filepath){
        try {
            Scanner scanner = new Scanner(new File(filepath));
            String next = scanner.useDelimiter("\\Z").next();
            scanner.close();
            return new JSONObject(next);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }









}
