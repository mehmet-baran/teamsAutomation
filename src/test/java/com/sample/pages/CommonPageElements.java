package com.sample.pages;

import com.sample.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

public abstract class CommonPageElements extends Driver {
    public CommonPageElements() {
        PageFactory.initElements(driver,this);
    }

    public void navigateTo(String menuOption){
        driver.findElement(By.xpath("//span[.='"+menuOption+"']")).click();
    }

    public void clickOnButtonOf(String buttonText){
        if(driver.findElements(By.xpath("//span[.='"+buttonText+"']")).size()!=0){
            driver.findElement(By.xpath("//span[.='"+buttonText+"']")).click();
        }
        else if(driver.findElements(By.xpath("//a[.=' "+buttonText+" ']")).size()!=0){
            driver.findElement(By.xpath("//a[.=' "+buttonText+" ']")).click();
        }

    }
}

