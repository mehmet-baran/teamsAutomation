package com.sample.step_definitions;

import com.sample.utilities.CommonSteps;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

public class DashboardSteps extends CommonSteps {

    @When("the user clicks on Activity tab")
    public void the_user_clicks_on_activity_tab()  {
        waitForVisibility(dashboardPage.activityButton,15);
        dashboardPage.activityButton.click();
    }

    @Then("the user should be able to join the meeting")
    public void the_user_should_be_able_to_join_the_meeting() {
        waitFor(1);
        dashboardPage.joinButton.click();
        waitFor(1);
        dashboardPage.joinNowButton.click();

    }

    @Then("the user should be able to join the meeting with a username")
    public void the_user_should_be_able_to_join_the_meeting_with_a_username() {
        waitForVisibility(meetingLoginPage.usernameTextbox, 15);
        meetingLoginPage.usernameTextbox.sendKeys("Mehmet");
        meetingLoginPage.joinNowButton.click();
    }
}
