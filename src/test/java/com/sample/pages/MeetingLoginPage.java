package com.sample.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MeetingLoginPage extends CommonPageElements{

    @FindBy (css = "[data-tid=\"joinOnWeb\"]")
    public WebElement continueOnBrowserButton;

    @FindBy(id = "username")
    public WebElement usernameTextbox;
}
