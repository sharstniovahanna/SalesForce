package mypackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {
    public final By EMAIL_REGISTRATION_FIELD = By.xpath("//input[@name='fieldEmail']");
    public final By PASSWORD_REGISTRATION_FIELD = By.xpath("//input[@name='fieldPassword']");
    public final By TELEPHONE_REGISTRATION_FIELD = By.xpath("//input[@name='fieldPhone']");
    public final By NAME_AND_SURNAME_FIELD = By.xpath("//input[@name='fieldFio']");
    public final By REGISTRATION_SUBMIT_BUTTON = By.xpath("//button[@id='reg_submit']");
    public final By HEADING_TEXT = By.xpath("//header[contains(@class,'b-form__header')]/h1");
    public final By ERROR_MESS_INCORRECT_PHONE = By.xpath ("//div[@id='field-phone-notice']/span");
    public final By ERROR_MESS_INCORRECT_NAME_AND_SURNAME =By.xpath("//div[@id='field-fio-notice']");
    public final By ERROR_MESS_INCORRECT_EMAIL_EXIST = By.xpath("//span[@id='field-email-notice']");
    private WebDriver driver;
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void registrationSubmission(String email, String password, String phone, String nameSurname) {
        driver.findElement(EMAIL_REGISTRATION_FIELD).sendKeys(email);
        driver.findElement(PASSWORD_REGISTRATION_FIELD).sendKeys(password);
        driver.findElement(TELEPHONE_REGISTRATION_FIELD).sendKeys(phone);
        driver.findElement(NAME_AND_SURNAME_FIELD).sendKeys(nameSurname);
        driver.findElement(REGISTRATION_SUBMIT_BUTTON).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getHeadinText() {
        return driver.findElement(HEADING_TEXT).getText();
    }

    public String getErrorMessIncorrectPhone(){
        return driver.findElement(ERROR_MESS_INCORRECT_PHONE).getText();
    }

    public String getErrorMessIncorrectNameSurname(){
        return driver.findElement(ERROR_MESS_INCORRECT_NAME_AND_SURNAME).getText();
    }

    public String getErrorMessageEmailExist(){
        return driver.findElement(ERROR_MESS_INCORRECT_EMAIL_EXIST).getText();
    }



}
