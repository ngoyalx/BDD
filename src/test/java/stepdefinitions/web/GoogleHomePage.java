package stepdefinitions.web;
import pageobjects.web.PageObjectGoogleHomePage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


/**
 * Created by ngoyal on 5/13/2017.
 */
public class GoogleHomePage {

    PageObjectGoogleHomePage googleHomePage = new PageObjectGoogleHomePage();

    @Given("^user is on the google home page$")
    public void user_is_on_the_google_home_page() throws Throwable {
        googleHomePage.openGoogleHomePage();
    }

    @When("^user search some keyword$")
    public void user_search_some_keyword() throws Throwable {
        googleHomePage.search();
    }


    @Then("^user should see the search results$")
    public void user_should_see_the_search_results() throws Throwable {
        System.out.println("I am able to see the results");
    }
}
