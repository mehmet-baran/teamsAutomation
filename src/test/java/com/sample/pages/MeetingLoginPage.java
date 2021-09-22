package com.sample.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MeetingLoginPage extends CommonPageElements{

    @FindBy (css = "[data-tid=\"joinOnWeb\"]")
    public WebElement continueOnBrowserButton;

    @FindBy(id = "username")
    public WebElement usernameTextbox;

    @FindBy (xpath = "//toggle-button[@title=\"Turn camera off\"]")
    public List<WebElement> cameraToggle;

    @FindBy (xpath = "//toggle-button[@title=\"Mute microphone\"]")
    public List <WebElement> microphoneToggle;
}
