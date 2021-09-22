package com.sample.utilities;

import com.sample.pages.PageInitializer;
import io.appium.java_client.windows.WindowsDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class Driver {

    public static WebDriver driver;
    public static WindowsDriver windowsDriver=null;

    /*
     * This method will create a driver and return it
     * @return WebDriver driver
     */
    public static WebDriver setUp() throws Exception {

        String browser = ConfigurationReader.get("browser");
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("use-fake-device-for-media-stream");
                options.addArguments("use-fake-ui-for-media-stream");
                options.addArguments("--incognito");
                options.addArguments("disable-infobars");
                options.addArguments("ignore-certificate-errors");
                options.addArguments("disable-popup-blocking");
                options.addArguments("disable-extensions");
                options.addArguments("disable-notifications");
                options.addArguments("no-sandbox");
                options.addArguments("allow-running-insecure-content");
                options.setAcceptInsecureCerts(true);
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability(ChromeOptions.CAPABILITY, options);
                driver = new ChromeDriver(caps);
                enableThirdPartyCookies(driver);
                break;
            case "chrome-headless":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "firefox-headless":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(new FirefoxOptions().setHeadless(true));
                break;
            case "ie":
                if (!System.getProperty("os.name").toLowerCase().contains("windows"))
                    throw new WebDriverException("Your OS doesn't support Internet Explorer");
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "safari":
                if (!System.getProperty("os.name").toLowerCase().contains("mac"))
                    throw new WebDriverException("Your OS doesn't support Safari");
                WebDriverManager.getInstance(SafariDriver.class).setup();
                driver = new SafariDriver();
                break;


        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        PageInitializer.initialize();
        return driver;
    }
    public static void winAppDriverSetUp(){
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();


        desiredCapabilities.setCapability("app", "C:\\Program Files\\obs-studio\\bin\\64bit\\obs64.exe");
        desiredCapabilities.setCapability("platformName", "Windows");
        desiredCapabilities.setCapability("deviceName", "WindowsPC");
        desiredCapabilities.setCapability("app", "Root");
        try {
            windowsDriver = new WindowsDriver(new URL("http://127.0.0.1:4723"), desiredCapabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        windowsDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public static void closeDriver() {
        if (driver != null) {
            //driver.quit();
        }
    }

    public static void enableThirdPartyCookies(WebDriver driver) throws Exception {
        ArrayList<String> windowHandles = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(driver.getWindowHandle());

        // Open New Tab Ctrl + T
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_T);
        robot.keyRelease(KeyEvent.VK_T);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        // Wait for open new tab
        int retries = 100;
        for (int i = 0; i < retries; i++)
        {
            Thread.sleep(100);
            if (driver.getWindowHandles().size() > windowHandles.size())
                break;
        }

        // Enable Third Party Cookies
        if (driver.getWindowHandles().size() > windowHandles.size())
        {
            driver.close();
            windowHandles = new ArrayList<String> (driver.getWindowHandles());
            driver.switchTo().window(windowHandles.get(windowHandles.size() - 1));
            List<WebElement> list = driver.findElements(By.id("cookie-controls-toggle"));
            Optional<WebElement> selectedCookieControlsToggle = driver.findElements(By.id("cookie-controls-toggle")).stream()
                    .filter(x -> x.getAttribute("checked") != null).findFirst();
            Optional.ofNullable(selectedCookieControlsToggle).get().get().click();
            //driver.findElement(By.id("cookie-controls-toggle")).click();
        }
    }


}