package com.sample.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage extends CommonPageElements{

    public ProfilePage() {
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "OLD_PASSWORD")
    public WebElement oldPasswordTextbox;

    @FindBy(id = "NEW_PASSWORD")
    public WebElement newPasswordTextbox;

    @FindBy(id = "CONFIRM_PASSWORD")
    public WebElement confirmPasswordTextbox;

    @FindBy(css = "[class='ns-history-table']")
    public WebElement historyTable;

    public WebElement getErrorMessage(String errorMessage){
        return driver.findElement(By.xpath("//*[.=' "+errorMessage+" ']"));
    }


}
