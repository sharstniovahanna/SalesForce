package mypackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    public final By ENTER_BUTTON = By.xpath("//a[@class=\'enter\']");
    public final By REGISTRATION_BUTTON = By.xpath("//a[@class='button wide auth__reg']");
    public final By ENTER_SUBMISSION_BUTTON = By.xpath("//div[@class='b-auth-form__inner']//div[2]/input");
    public final By FORGET_PASSWORD_LINK = By.xpath("//*[contains(text(),'Забыли пароль')]");
    public final By LOGIN_FIELD = By.xpath("//*[@autocomplete='username']");
    public final By PASSWORD_FIELD = By.xpath("//*[@autocomplete='current-password']");
    public final By LOGIN_SUBMISSION_BUTTON = By.xpath("//input[@type='submit'  and @tabindex='6']");
    public final By SECTIONS_MENU = By.xpath("//span[@class='b-icon icon-burger']");
    public final By FIRST_SECTION  = By.xpath("//a[@title='Новости']");
    public final By LAST_SECTION  = By.xpath("//body/div[@id='animated_mainmenu']/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/ul[2]/li[19]/a[1]");
    public final By RABOTA_SECTION  = By.xpath("//ul[@class='b-topbar-more-list']//a[@title='Работа'][contains(text(),'Работа')]");


    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickEnter() {
        driver.findElement(ENTER_BUTTON).click();
    }

    public RegistrationPage registrateUser() {
        driver.findElement(REGISTRATION_BUTTON).click();
        return new RegistrationPage(driver);
    }

    public boolean isEnterSubmissionButtonDisabled() {
        return driver.findElement(ENTER_SUBMISSION_BUTTON).isEnabled();
    }

    public boolean isEnterSubmissionButtonMainPageDisabled() {
        return driver.findElement(ENTER_BUTTON).isEnabled();
    }


    public PasswordRecoveryPage recoveryPassword() {
        driver.findElement(ENTER_BUTTON).click();
        driver.findElement(FORGET_PASSWORD_LINK).click();
        return new PasswordRecoveryPage(driver);
    }

    public PersonalPage login(String username, String password) {
        driver.findElement(LOGIN_FIELD).sendKeys(username);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGIN_SUBMISSION_BUTTON).click();
        return new PersonalPage(driver);
    }

    public void openSectionsMenu(){
        driver.findElement(SECTIONS_MENU).click();
    }
    public String getFirstSectionText(){
        return driver.findElement(FIRST_SECTION).getText();
    }

    public String getLastSectionText(){
        return driver.findElement(LAST_SECTION).getText();
    }

    public RabotaPage openJobSection(){
        driver.findElement(RABOTA_SECTION).click();
        return new RabotaPage(driver);
    }




}
