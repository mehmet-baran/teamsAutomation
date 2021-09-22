package com.sample.step_definitions;

import com.sample.utilities.CommonSteps;
import com.sample.utilities.ConfigurationReader;
import com.sample.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestSteps extends CommonSteps {

    @When("the user clicks on Activity tab")
    public void the_user_clicks_on_activity_tab()  {
        waitFor(1);
        waitForVisibility(dashboardPage.activityButton,15);
        dashboardPage.activityButton.click();
    }

    @Then("the user should be able to join the meeting")
    public void the_user_should_be_able_to_join_the_meeting() throws InterruptedException {
        Thread.sleep(3000);
        Driver.winAppDriverSetUp();
        Actions actions = new Actions(windowsDriver);
        windowsDriver.findElement(By.name("Type here to search")).click();
        waitFor(1);
        windowsDriver.findElement(By.name("Search box")).sendKeys("obs");
        actions.sendKeys(Keys.ENTER).perform();
        waitFor(2);
        windowsDriver.findElement(By.name("OBS Studio (64bit) - 1 running window")).click();
        Thread.sleep(2000);
        windowsDriver.findElement(By.name("Start Recording")).click();
        waitFor(2);
        windowsDriver.findElement(By.name("Google Chrome - 1 running window")).click();
        waitFor(2);
        waitForVisibility(dashboardPage.joinButton, 15);
        dashboardPage.joinButton.click();
        waitFor(1);
        waitForVisibility(dashboardPage.joinNowButton,15);
        dashboardPage.joinNowButton.click();
        String timeStamp=LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy-HH.mm.ss"));
        System.out.println("Meeting started at around"+timeStamp);

    }

    @Then("the user should be able to join the meeting with a username")
    public void the_user_should_be_able_to_join_the_meeting_with_a_username() throws InterruptedException {

        Thread.sleep(3000);
        Driver.winAppDriverSetUp();
        Actions actions = new Actions(windowsDriver);
        windowsDriver.findElement(By.name("Type here to search")).click();
        waitFor(1);
        windowsDriver.findElement(By.name("Search box")).sendKeys("obs");
        actions.sendKeys(Keys.ENTER).perform();
        waitFor(2);
        windowsDriver.findElement(By.name("OBS Studio (64bit) - 1 running window")).click();
        Thread.sleep(2000);
        windowsDriver.findElement(By.name("Start Recording")).click();
        waitFor(2);
        windowsDriver.findElement(By.name("Google Chrome - 1 running window")).click();
        waitFor(1);
        waitForVisibility(meetingLoginPage.usernameTextbox, 15);
        meetingLoginPage.usernameTextbox.clear();
        meetingLoginPage.usernameTextbox.sendKeys("Mehmet");
        waitFor(1);
        waitForVisibility(meetingLoginPage.joinNowButton,15);
        meetingLoginPage.joinNowButton.click();
        String timeStamp=LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy-HH.mm.ss"));
        System.out.println("Meeting started at around "+timeStamp);


    }

    @Then("the user should be able to stay in the meeting room until it's ended")
    public void the_user_should_be_able_to_stay_in_the_meeting_room_until_it_s_ended() throws IOException, InterruptedException {
        while(dashboardPage.isMeetingGoingOn()){
            String timeStamp=LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy-HH.mm.ss"));
            File meetingScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(meetingScreenshot, new File(System.getProperty("user.dir") + "\\src\\test\\resources\\screenshots\\"+timeStamp+".png"));
            waitFor(10);
            System.out.println("Meeting is going on at "+timeStamp);
        }
        String timeStamp=LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy-HH.mm.ss"));
        File meetingScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(meetingScreenshot, new File(System.getProperty("user.dir") + "\\src\\test\\resources\\screenshots\\meetingfinished"+timeStamp+".png"));
        System.out.println("Meeting finished at around "+ timeStamp);
        waitFor(1);
        windowsDriver.findElement(By.name("OBS Studio (64bit) - 1 running window")).click();
        waitFor(1);
        if(windowsDriver.findElementsByName("Stop Recording").size()!=0){
            windowsDriver.findElement(By.name("Stop Recording")).click();
            waitFor(5);
        }
        while (true){
            windowsDriver.findElement(By.name("Close")).click();
            waitFor(1);
            if(windowsDriver.findElements(By.name("OBS is currently active. All streams/recordings will be shut down. Are you sure you wish to exit?")).size()!=0){
                windowsDriver.findElement(By.name("No")).click();
                waitFor(5);
            }else {
                break;
            }

        }
        windowsDriver.quit();

    }

    @Given("the user logs in through teams website")
    public void the_user_logs_in_through_teams_website() {
        driver.get(ConfigurationReader.get("url1"));
        waitFor(1);
        waitForVisibility(loginPage.emailTextbox,15);
        loginPage.emailTextbox.sendKeys(ConfigurationReader.get("email"));
        waitFor(1);
        waitForVisibility(loginPage.nextButton,15);
        loginPage.nextButton.click();
        waitFor(1);
        waitForVisibility(loginPage.passwordTextbox,15);
        loginPage.passwordTextbox.sendKeys(ConfigurationReader.get("password"));
        waitFor(1);
        waitForVisibility(loginPage.submitButton,15);
        loginPage.submitButton.click();
        waitFor(1);
        waitForVisibility(loginPage.dontAskCheckbox,15);
        loginPage.dontAskCheckbox.click();
        waitFor(10);
        waitForVisibility(loginPage.verifyButton,15);
        loginPage.verifyButton.click();
        waitFor(1);
        waitForVisibility(loginPage.staySignedCheckbox,15);
        loginPage.staySignedCheckbox.click();
        waitFor(1);
        waitForVisibility(loginPage.yesButton,15);
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
        waitFor(1);
        waitForVisibility(meetingLoginPage.continueOnBrowserButton,15);
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

    @Then("the user should be able to wait for a new meeting link")
    public void the_user_should_be_able_to_wait_for_a_new_meeting_link() throws IOException, InterruptedException, AWTException {
        boolean flag = true;
        while (flag){
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

                    if (!url.equalsIgnoreCase("testFinished")) {
                        the_user_waits_for_the_meeting_link_and_once_it_s_provided_navigates_to_that_link();
                        the_user_continues_on_browser_instead_of_teams_application();
                        the_user_should_be_able_to_join_the_meeting_with_a_username();
                        the_user_should_be_able_to_stay_in_the_meeting_room_until_it_s_ended();
                    } else {
                        flag=false;
                        break;
                    }

                }

            }

        }
    }

    @Then("the user should be able to wait for a new meeting link in idle condition")
    public void the_user_should_be_able_to_wait_for_a_new_meeting_link_in_idle_condition() throws IOException, InterruptedException {
        boolean flag = true;
        while (flag){
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

                    if (!url.equalsIgnoreCase("testFinished")) {
                        the_user_clicks_on_activity_tab();
                        the_user_should_be_able_to_join_the_meeting();
                        the_user_should_be_able_to_stay_in_the_meeting_room_until_it_s_ended();
                    } else {
                        flag=false;
                        break;
                    }

                }

            }

        }
    }
}
