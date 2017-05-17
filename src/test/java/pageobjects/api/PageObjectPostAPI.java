package pageobjects.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import stepdefinitions.Hooks;
import utils.GlobalProperties;
import utils.Utility;

import static io.restassured.RestAssured.given;

/**
 * Created by ngoyal on 5/16/2017.
 */
public class PageObjectPostAPI {

    private RequestSpecification requestSpec  = null;
    private Response postResponse = null;
    private JSONObject inputJSON = null;
    private JSONObject outputJSONPOSTResponse = null;
    private String postURL = "";
    private String filePath = "";
    private int responseStatusCode;

    public PageObjectPostAPI(){
        if(Hooks.requestSpec!=null){
            this.requestSpec = Hooks.requestSpec;
        }else{
            System.out.println("Request specification not set in Hooks");
        }

    }

    public void postMethod(String url, String fileName){
    try {
        postURL = GlobalProperties.getPropertyMap().get("ApiEndPoint") + url;
        inputJSON = Utility.readJSONFile(getJSONPathForPOST(fileName));

        postResponse =
                given().spec(requestSpec)
                        .body(inputJSON.toString())
                        .contentType(ContentType.JSON)
                        .when()
                        .post(postURL);

        responseStatusCode = postResponse.getStatusCode();
        String postResponseAsString = postResponse.getBody().asString();
        System.out.println(postResponseAsString);
        outputJSONPOSTResponse = new JSONObject(postResponseAsString);
    }catch(Exception e){
        e.printStackTrace();
    }
    }

    public boolean verifyStatusCode(int statusCode){
            if(responseStatusCode==statusCode){
                return true;
            }else{
                return false;
            }
    }

    private String getJSONPathForPOST(String filename){
        filePath =  System.getProperty("user.dir") + GlobalProperties.FileSeperator.get() + "src" + GlobalProperties.FileSeperator.get()
                    + "main" + GlobalProperties.FileSeperator.get() + "resources" + GlobalProperties.FileSeperator.get()
                    + "API" + GlobalProperties.FileSeperator.get() + "POST" + GlobalProperties.FileSeperator.get()
                    + filename + ".json";
        return filePath;
    }

}
