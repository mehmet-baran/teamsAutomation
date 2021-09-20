package com.sample.step_definitions;

import com.sample.utilities.CommonSteps;
import com.sample.utilities.ConfigurationReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalField;

public class LoginSteps extends CommonSteps {

    @Given("the user logs in through teams website")
    public void the_user_logs_in_through_teams_website() {
        driver.get(ConfigurationReader.get("url1"));
        loginPage.emailTextbox.sendKeys(ConfigurationReader.get("email"));
        loginPage.nextButton.click();
        loginPage.passwordTextbox.sendKeys(ConfigurationReader.get("password"));
        loginPage.submitButton.click();
        loginPage.dontAskCheckbox.click();
        waitFor(10);
        loginPage.verifyButton.click();
        loginPage.staySignedCheckbox.click();
        loginPage.yesButton.click();
    }

    @Given("the user navigates to the meeting link")
    public void the_user_navigates_to_the_meeting_link() {
        driver.get(ConfigurationReader.get("url2"));
    }

    @When("the user continues on browser instead of Teams application")
    public void the_user_continues_on_browser_instead_of_teams_application() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        meetingLoginPage.continueOnBrowserButton.click();
    }

    @Given("the user waits for the meeting link and once it's provided navigates to that link")
    public void the_user_waits_for_the_meeting_link_and_once_it_s_provided_navigates_to_that_link() throws IOException, InterruptedException {
        byte testDurationInDays = 7;
        LocalDateTime finalTime = LocalDateTime.now().plus(Duration.ofDays(testDurationInDays));
        boolean flag=true;
        while (LocalDateTime.now().isBefore(finalTime) && flag) {

            String cmd = "aws ssm get-parameter --name \"QMUL-WifiTesting-MeetingLink\" --region \"eu-west-1\"";
            Runtime run = Runtime.getRuntime();
            Process pr = run.exec(cmd);
            pr.waitFor();
            BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line = "";

            while ((line = buf.readLine()) != null) {

                if (line.contains("Value")) {

                    String result = line;
                    String[] resultArray = result.split("\"");
                    String url = resultArray[3];

                    if (url.equalsIgnoreCase("empty")) {
                        //driver.get(url);
                        driver.get(ConfigurationReader.get("url2"));
                        flag=false;
                        break;
                    } else {
                        waitFor(60);
                    }

                }

            }

        }
    }




}
