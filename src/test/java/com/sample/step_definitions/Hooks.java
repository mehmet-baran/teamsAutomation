package com.sample.step_definitions;

import com.sample.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public void start() throws Exception {
        Driver.setUp();
    }

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        Driver.closeDriver();
    }






}

