package mypackage;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.SendKeysAction;

public class RabotaPage {

    public final By EXTENDED_SEARCH_BUTTON = By.xpath("//a[contains(@class,'bloko-icon-link')]");
    public final By SEARCH_IN_COMPANY_NAME_CHECKBOX = By.xpath("//span[contains(text(),'в названии вакансии')]");
    public final By REGION_FIELD = By.xpath("//input[@class='bloko-input                                         Bloko-CompositeSelection-Suggest                                         jsxComponents-Hint-Input']");
    public final By EXPERIENCE_RADIOBUTTON = By.xpath("//span[contains(text(),'От 1 года до 3 лет')]");
    public final By SUBMIT_SEARCH_BUTTON = By.xpath("//input[@id='submit-bottom']");
    public final By REMOVE_REGION_BUTTON = By.xpath("//span[@class='bloko-icon                                  bloko-icon_remove                                  bloko-icon_initial-impact                                  bloko-icon_highlighted-action']");
    public final By SOMEWHERE_CLICK  = By.xpath("//div[@class='row-content']");


    private WebDriver driver;

    public RabotaPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openExtendedSearch(){
        driver.findElement(EXTENDED_SEARCH_BUTTON).click();
    }

    public void setJobParameters (String jobName, String region) throws InterruptedException {
        driver.findElement(By.id("advancedsearchmainfield")).sendKeys(jobName);
        driver.findElement(SEARCH_IN_COMPANY_NAME_CHECKBOX).click();
        driver.findElement(REMOVE_REGION_BUTTON).click();
        driver.findElement(REGION_FIELD).sendKeys(region);
        Thread.sleep(3000);
        driver.findElement(REGION_FIELD).sendKeys(Keys.ENTER);
        Thread.sleep(3000);
//        driver.findElement(EXPERIENCE_RADIOBUTTON).click();
        driver.findElement(SUBMIT_SEARCH_BUTTON).click();

    }

    public String getResultText(){
        return driver.findElement(By.xpath("//input[@data-qa='search-input']")).getAttribute("value");
    }
    public String getRegionValue(){
        return driver.findElement(By.xpath("//span[@data-qa='serp__cluster-item-selected_1007']")).getText();
    }
}
