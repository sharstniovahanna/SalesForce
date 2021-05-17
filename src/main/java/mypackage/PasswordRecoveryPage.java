package mypackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PasswordRecoveryPage {
    private WebDriver driver;

    public PasswordRecoveryPage(WebDriver driver) {
        this.driver = driver;
    }
    private final By HEADING_RECOVERY_PAGE = By.xpath("//h1[contains(text(),'Восстановление пароля')]");


    public String getHeadingOnRecoveryPage(){
       return driver.findElement(HEADING_RECOVERY_PAGE).getText();
    }
}
