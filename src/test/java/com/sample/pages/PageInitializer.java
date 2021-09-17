package com.sample.pages;

import com.sample.utilities.Driver;

public class PageInitializer extends Driver {

    public static LoginPage loginPage;
    public static DashboardPage dashboardPage;
    public static ProgramsPage programsPage;
    public static ProfilePage profilePage;

    public static void initialize() {

        loginPage=new LoginPage();
        dashboardPage=new DashboardPage();
        programsPage=new ProgramsPage();
        profilePage=new ProfilePage();

    }
}
