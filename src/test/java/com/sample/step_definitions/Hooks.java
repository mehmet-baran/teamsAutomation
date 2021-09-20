package com.sample.step_definitions;

import com.sample.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.net.MalformedURLException;

public class Hooks {

    @Before
    public void start() throws MalformedURLException {
        Driver.setUp();
    }

    @After
    public void tearDown(Scenario scenario) throws InterruptedException {
//        byte[] picture;
//        if (scenario.isFailed()) {
//            // take screenshot and save it in /failed
//            picture = CommonSteps.takeScreenshot("failed/" + scenario.getName());
//        } else {
//            // take screenshot and put it in /passed folder
//            picture = CommonSteps.takeScreenshot("passed/" + scenario.getName());
//        }
//
//        scenario.attach(picture, "image/png", scenario.getName());
        Thread.sleep(5000);
        Driver.closeDriver();
    }






}

