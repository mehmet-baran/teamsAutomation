package com.sample.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProgramsPage extends CommonPageElements{

    public ProgramsPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//button[@class=\"NS_Button-Bookmark\" ])[1]")
    public WebElement firstBookmarkButton;

    public void goToProgram(String programName){
        driver.findElement(By.xpath("//a[.=' "+programName+" ']")).click();
    }
}
