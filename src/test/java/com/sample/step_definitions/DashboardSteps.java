package com.sample.step_definitions;

import com.sample.utilities.CommonSteps;
import com.sample.utilities.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DashboardSteps extends CommonSteps {

    @When("the user clicks on Activity tab")
    public void the_user_clicks_on_activity_tab()  {
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
        waitFor(1);
        dashboardPage.joinButton.click();
        waitFor(1);
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
        waitForVisibility(meetingLoginPage.usernameTextbox, 15);
        meetingLoginPage.usernameTextbox.sendKeys("Mehmet");
        meetingLoginPage.joinNowButton.click();
        String timeStamp=LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy-HH.mm.ss"));
        System.out.println("Meeting started at around "+timeStamp);


    }

    @Then("the user should be able to stay in the meeting room until it's ended")
    public void the_user_should_be_able_to_stay_in_the_meeting_room_until_it_s_ended() throws IOException {
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
        windowsDriver.findElement(By.name("Stop Recording")).click();
        waitFor(5);
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
}
