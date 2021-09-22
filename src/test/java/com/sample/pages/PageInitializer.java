package com.sample.pages;

import com.sample.utilities.Driver;

public class PageInitializer extends Driver {

    public static DashboardPage dashboardPage;
    public static MeetingLoginPage meetingLoginPage;

    public static void initialize() {

        dashboardPage = new DashboardPage();
        meetingLoginPage = new MeetingLoginPage();



    }
}
