package com.sample.step_definitions;

import com.sample.utilities.CommonSteps;
import com.sample.utilities.ConfigurationReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class ProfileStepDefs extends CommonSteps {

    @Given("{string} is on the {string} page")
    public void is_on_the_page(String userRole, String tab) {
        new BookmarkStepDefs().the_user_logs_in_as_role(userRole);
        profilePage.navigateTo(tab);
    }

    @When("the user provides correct old password to change password")
    public void the_user_provides_correct_old_password_to_change_password() {
        profilePage.oldPasswordTextbox.sendKeys(ConfigurationReader.get("studentPassword"));
    }
    @When("the user provides mismatched new password")
    public void the_user_provides_mismatched_new_password() {
        profilePage.newPasswordTextbox.sendKeys("Aa.123456");
        profilePage.confirmPasswordTextbox.sendKeys("Bb.123456");
        profilePage.clickOnButtonOf("Save");
    }
    @Then("the user should get {string} error")
    public void the_user_should_get_error(String errorMessage) {
        Assert.assertTrue(profilePage.getErrorMessage(errorMessage).isDisplayed());
    }

    @When("the user clicks on {string} tab")
    public void the_user_clicks_on_tab(String tab) {
        profilePage.clickOnButtonOf(tab);
    }

    @Then("the user should be able to see personal history")
    public void the_user_should_be_able_to_see_personal_history() {
        Assert.assertTrue(profilePage.historyTable.isDisplayed());

    }

}
