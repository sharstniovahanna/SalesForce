package mypackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class MainClass {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.tut.by/");
        MainPage mainPage = new MainPage(driver);
        mainPage.clickEnter();
        mainPage.registrateUser();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registrationSubmission("sdians2403", "qwertyQW12","293447155","Diana Sherstneva");
        driver.quit();
    }

}
