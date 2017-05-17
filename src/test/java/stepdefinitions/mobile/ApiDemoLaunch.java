package stepdefinitions.mobile;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageobjects.mobile.PageObjectApiDemoLaunch;

/**
 * Created by ngoyal on 5/14/2017.
 */
public class ApiDemoLaunch {

    PageObjectApiDemoLaunch apiDemoLaunch = new PageObjectApiDemoLaunch();

    @When("^user click on View tab$")
    public void user_click_on_View_tab() {
        apiDemoLaunch.launchViews();
    }

    @Then("^views menu should be displayed$")
    public void views_menu_should_be_displayed() {

        System.out.println("I can see the menu");
    }




}
