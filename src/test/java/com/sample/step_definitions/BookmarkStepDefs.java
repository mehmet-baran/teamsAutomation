package com.sample.step_definitions;

import com.sample.utilities.CommonSteps;
import com.sample.utilities.ConfigurationReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BookmarkStepDefs extends CommonSteps {

    @Given("the user logs in as {string} role")
    public void the_user_logs_in_as_role(String role) {
        if(role.equalsIgnoreCase("student")){
            sendText(loginPage.usernameTextbox, ConfigurationReader.get("studentUsername"));
            sendText(loginPage.passwordTextbox, ConfigurationReader.get("studentPassword"));
        }
        click(loginPage.loginButton);
    }

    @When("the user navigates to {string} page")
    public void the_user_navigates_to_page(String menuOption) {
        dashboardPage.navigateTo(menuOption);
    }
    @When("the user clicks bookmark icon for a course")
    public void the_user_clicks_bookmark_icon_for_a_course() {
        click(programsPage.firstBookmarkButton);
    }
    @Then("the course should been able to bookmarked")
    public void the_course_should_been_able_to_bookmarked() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user selects {string} program")
    public void the_user_selects_program(String programName) {
        programsPage.goToProgram(programName);
    }

}
