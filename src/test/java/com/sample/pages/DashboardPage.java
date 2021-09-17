package com.sample.pages;

import org.openqa.selenium.support.PageFactory;

public class DashboardPage extends CommonPageElements{

    public DashboardPage() {
        PageFactory.initElements(driver,this);
    }


}
