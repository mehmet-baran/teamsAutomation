package com.sample.pages;

import com.sample.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class CommonPageElements extends Driver {
    public CommonPageElements() {
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "[aria-label=\"Join the meeting\"]")
    public WebElement joinNowButton;
}

