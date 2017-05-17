package stepdefinitions.api;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageobjects.api.PageObjectPostAPI;

/**
 * Created by ngoyal on 5/16/2017.
 */
public class PostApi {

    PageObjectPostAPI postAPI = new PageObjectPostAPI();


    @When("^user send a post request at \"([^\"]*)\" with json data \"([^\"]*)\"$")
    public void user_send_a_post_request_at_with_json_data(String url,String fileName) {
        postAPI.postMethod(url,fileName);
    }

    @Then("^response code should be \"([^\"]*)\"$")
    public void response_code_should_be(String statusCode){

        postAPI.verifyStatusCode(Integer.parseInt(statusCode));
    }
}
