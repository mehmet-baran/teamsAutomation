package com.sample.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends CommonPageElements{


    @FindBy(css = "[title=\"Meet\"]")
    public WebElement meetButton;

    @FindBy(css = "[aria-label=\"Join\"]")
    public WebElement joinButton;

    @FindBy(css = "[aria-label=\"Activity Toolbar\"]")
    public WebElement activityButton;





}
