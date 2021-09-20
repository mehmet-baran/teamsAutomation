package com.sample.pages;

import com.sample.utilities.Driver;

public class PageInitializer extends Driver {

    public static LoginPage loginPage;
    public static DashboardPage dashboardPage;
    public static MeetingLoginPage meetingLoginPage;

    public static void initialize() {

        loginPage=new LoginPage();
        dashboardPage = new DashboardPage();
        meetingLoginPage = new MeetingLoginPage();



    }
}
