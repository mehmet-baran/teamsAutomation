package com.sample.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends CommonPageElements{

    public LoginPage() {
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "Username-Field")
    public WebElement usernameTextbox;

    @FindBy(id = "Password-Field")
    public WebElement passwordTextbox;

    @FindBy(css = "[type=\"submit\"]")
    public WebElement loginButton;



}
