package com.sample.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DashboardPage extends CommonPageElements{


    @FindBy(css = "[title=\"Meet\"]")
    public WebElement meetButton;

    @FindBy(css = "[aria-label=\"Join\"]")
    public WebElement joinButton;

    @FindBy(css = "[aria-label=\"Activity Toolbar\"]")
    public WebElement activityButton;

    @FindBy(id = "hangup-button")
    public List<WebElement> hangupButton;

    @FindBy(css = "[data-tid=\"meeting-title\"]")
    public List<WebElement> letPeopleKnowText;


    public boolean isMeetingGoingOn(){
        if(hangupButton.size()!=0 || letPeopleKnowText.size()!=0){
            return true;
        }
        else {
            return false;
        }
    }





}
