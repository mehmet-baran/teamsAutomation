package com.sample.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends CommonPageElements{

    public LoginPage() {
        PageFactory.initElements(driver,this);
    }

    @FindBy(name = "loginfmt")
    public WebElement emailTextbox;

    @FindBy(id = "idSIButton9")
    public WebElement nextButton;

    @FindBy(id = "i0118")
    public WebElement passwordTextbox;

    @FindBy(css = "[value=\"Sign in\"]")
    public WebElement submitButton;

    @FindBy(id = "idChkBx_SAOTCC_TD")
    public WebElement dontAskCheckbox;

    @FindBy(id = "idSubmit_SAOTCC_Continue")
    public WebElement verifyButton;

    @FindBy(id = "KmsiCheckboxField")
    public WebElement staySignedCheckbox;

    @FindBy(css = "[value=\"Yes\"]")
    public WebElement yesButton;

}
