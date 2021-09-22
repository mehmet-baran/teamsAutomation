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

    @Given("the user waits for the meeting link and once it's provided navigates to that link")
    public void the_user_waits_for_the_meeting_link_and_once_it_s_provided_navigates_to_that_link() throws IOException, InterruptedException {
        byte testDurationInDays = 7;
        LocalDateTime finalTime = LocalDateTime.now().plus(Duration.ofDays(testDurationInDays));
        boolean flag = true;
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
                    String value = resultArray[3];

                    if (!value.equalsIgnoreCase("empty")) {
                        driver.get(value);
                        flag = false;
                        break;
                    } else{
                        driver.get(ConfigurationReader.get("url"));
                        flag= false;
                        break;
//                        waitFor(60);

                    }

                }

            }

        }
    }

    @When("the user continues on browser instead of Teams application")
    public void the_user_continues_on_browser_instead_of_teams_application() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        waitFor(1);
        waitForVisibility(meetingLoginPage.continueOnBrowserButton, 15);
        meetingLoginPage.continueOnBrowserButton.click();
    }

    @Then("the user should be able to join the meeting with a username")
    public void the_user_should_be_able_to_join_the_meeting_with_a_username() throws InterruptedException {
        if (System.getProperty("os.name").contains("Windows")) {
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
        }
        waitForVisibility(meetingLoginPage.usernameTextbox, 15);
        meetingLoginPage.usernameTextbox.clear();
        meetingLoginPage.usernameTextbox.sendKeys(System.getenv("QMUL_USERNAME"));
        waitFor(1);
        if (meetingLoginPage.cameraToggle.size()!=0) {
            waitFor(1);
            waitForVisibility(meetingLoginPage.cameraToggle.get(0),15);
            meetingLoginPage.cameraToggle.get(0).click();
        }
        if (meetingLoginPage.microphoneToggle.size()!=0) {
            waitFor(1);
            waitForVisibility(meetingLoginPage.microphoneToggle.get(0),15);
            meetingLoginPage.microphoneToggle.get(0).click();
        }
        waitForVisibility(meetingLoginPage.joinNowButton, 15);
        meetingLoginPage.joinNowButton.click();
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy-HH.mm.ss"));
        System.out.println("Meeting started at around " + timeStamp);
    }

    @Then("the user should be able to stay in the meeting room until it's ended")
    public void the_user_should_be_able_to_stay_in_the_meeting_room_until_it_s_ended() throws IOException, InterruptedException {
        while (dashboardPage.isMeetingGoingOn()) {
            String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy-HH.mm.ss"));
            File meetingScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(meetingScreenshot, new File(System.getProperty("user.dir") + "\\src\\test\\resources\\screenshots\\" + timeStamp + ".png"));
            waitFor(10);
            System.out.println("Meeting is going on at " + timeStamp);
        }
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy-HH.mm.ss"));
        File meetingScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(meetingScreenshot, new File(System.getProperty("user.dir") + "\\src\\test\\resources\\screenshots\\meetingfinished" + timeStamp + ".png"));
        System.out.println("Meeting finished at around " + timeStamp);
        if (System.getProperty("os.name").contains("Windows")) {
            waitFor(1);
            windowsDriver.findElement(By.name("OBS Studio (64bit) - 1 running window")).click();
            waitFor(1);
            if (windowsDriver.findElementsByName("Stop Recording").size() != 0) {
                windowsDriver.findElement(By.name("Stop Recording")).click();
                waitFor(1);
            }
            while (true) {
                windowsDriver.findElement(By.name("Close")).click();
                waitFor(1);
                if (windowsDriver.findElements(By.name("OBS is currently active. All streams/recordings will be shut down. Are you sure you wish to exit?")).size() != 0) {
                    windowsDriver.findElement(By.name("No")).click();
                    waitFor(5);
                } else {
                    break;
                }
            }
            windowsDriver.quit();
        }
    }

    @Then("the user should be able to wait for a new meeting link")
    public void the_user_should_be_able_to_wait_for_a_new_meeting_link() throws IOException, InterruptedException, AWTException {
        boolean flag = true;
        while (flag) {
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
                    String value = resultArray[3];

                    if (!value.equalsIgnoreCase("testFinished")) {
                        the_user_waits_for_the_meeting_link_and_once_it_s_provided_navigates_to_that_link();
                        the_user_continues_on_browser_instead_of_teams_application();
                        the_user_should_be_able_to_join_the_meeting_with_a_username();
                        the_user_should_be_able_to_stay_in_the_meeting_room_until_it_s_ended();
                    } else {
                        flag = false;
                        break;
                    }

                }

            }

        }
    }

}
