import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import mypackage.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import static org.junit.jupiter.api.Assertions.*;
import static utills.PropertyManager.getProperty;

import java.util.concurrent.TimeUnit;

public class MainPageTests {
    private WebDriver driver;
    private MainPage mainPage;
    private RabotaPage rabotaPage;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.tut.by/");
        driver.manage().window().maximize();
        mainPage = new MainPage(driver);
    }

    @Test
    @Order(1)
    @DisplayName("Login with Empty Creds test")
    @Severity(SeverityLevel.BLOCKER)
    public void positiveLoginTestWithEmptyLoginAndPass() {
        mainPage = new MainPage(driver);
        mainPage.clickEnter();
        Boolean result = mainPage.isEnterSubmissionButtonDisabled();
        System.out.println(result);
        assertFalse(result);
    }

    @Test
    @Order(2)
    @DisplayName("Recovery Password test")
    @Severity(SeverityLevel.BLOCKER)
    public void positiveRecoveryPasswordTest() {
        mainPage = new MainPage(driver);
        mainPage.recoveryPassword();
        PasswordRecoveryPage passwordRecoveryPage = new PasswordRecoveryPage(driver);
        String heading1 = passwordRecoveryPage.getHeadingOnRecoveryPage();
        System.out.println(heading1);
        assertEquals("???????????????????????????? ????????????", heading1);
    }

    @Test
    @Order(3)
    @DisplayName("Successful Registration test")
    @Severity(SeverityLevel.BLOCKER)
    public void positiveRegistrationTest() {
        mainPage = new MainPage(driver);
        mainPage.clickEnter();
        mainPage.registrateUser();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registrationSubmission(getProperty("correct_email"), getProperty("correct_password"), getProperty("correct_phone"), getProperty("correct_name_surname"));
        String heading = registrationPage.getHeadinText();
        System.out.println(heading);
        assertEquals("?????????????????????????? ????????????", heading);
    }

    @Test
    @Order(4)
    @DisplayName("Registration with Incorrect Phone test")
    @Severity(SeverityLevel.CRITICAL)
    public void negativeTestIncorrectPhone() {
        mainPage = new MainPage(driver);
        mainPage.clickEnter();
        mainPage.registrateUser();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registrationSubmission(getProperty("correct_email"), getProperty("correct_password"), getProperty("incorrect_phone"), getProperty("correct_name_surname"));
        String errorMessPhone = registrationPage.getErrorMessIncorrectPhone();
        System.out.println(errorMessPhone);
        assertEquals("???????????????????????? ??????????????", errorMessPhone);
    }

    @Test
    @Order(5)
    @DisplayName("Registration with Incorrect Name/Surname test")
    @Severity(SeverityLevel.CRITICAL)
    public void negativeTestIncorrectNameAndSurname() {
        mainPage = new MainPage(driver);
        mainPage.clickEnter();
        mainPage.registrateUser();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registrationSubmission(getProperty("correct_email"), getProperty("correct_password"), getProperty("incorrect_phone"), getProperty("incorrect_name_surname"));
        String errorMessNameAndSurname = registrationPage.getErrorMessIncorrectNameSurname();
        System.out.println(errorMessNameAndSurname);
        assertEquals("?????????????????? ???????????? ??????????, ?????????? ?? ????????????", errorMessNameAndSurname);
    }

    @Test
    @Order(6)
    @DisplayName("Registration with Exist Email test")
    @Severity(SeverityLevel.CRITICAL)
    public void negativeTestIncorrectEmailExist() {
        mainPage = new MainPage(driver);
        mainPage.clickEnter();
        mainPage.registrateUser();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registrationSubmission(getProperty("existing_email"), getProperty("correct_password"), getProperty("incorrect_phone"), getProperty("correct_name_surname"));
        String errorMessEmailExist = registrationPage.getErrorMessageEmailExist();
        System.out.println(errorMessEmailExist);
        assertEquals("???????????????????????? ?? ?????????? ?????????????? ?????? ????????????????????", errorMessEmailExist);
    }

    @Test
    @Order(7)
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }
    @DisplayName("Login test")
    @Severity(SeverityLevel.BLOCKER)
    public void positiveLoginTest() {
        mainPage = new MainPage(driver);
        mainPage.clickEnter();
        mainPage.login(getProperty("correct_login"), getProperty("correct_password"));
        PersonalPage personalPage = new PersonalPage(driver);
        Boolean result = personalPage.isPersonalLabelDispalyed();
        assertTrue(result);
    }

    @Test
    @Order(8)
    @DisplayName("Logout test")
    @Severity(SeverityLevel.BLOCKER)
    public void positiveLogoutTest() throws InterruptedException {
        mainPage = new MainPage(driver);
        mainPage.clickEnter();
        mainPage.login(getProperty("correct_login"), getProperty("correct_password"));
        PersonalPage personalPage = new PersonalPage(driver);
        personalPage.logout();
        Boolean logoutResult = mainPage.isEnterSubmissionButtonMainPageDisabled();
        Thread.sleep(5000);
        assertTrue(logoutResult);
    }

    @Test
    @Order(9)
    @DisplayName("Check sections of menu")
    @Severity(SeverityLevel.NORMAL)
    public void checkSections() {
        mainPage.openSectionsMenu();
        String firstSection = mainPage.getFirstSectionText();
        String lastSection = mainPage.getLastSectionText();
        Assertions.assertAll(
                () -> assertEquals("??????????????", firstSection),
                () -> assertEquals("????????????????", lastSection)
        );
    }

    @Test
    @Order(10)
    @Attachment
    @DisplayName("Find job")
    @Severity(SeverityLevel.BLOCKER)
    public void findJob() throws InterruptedException {
        mainPage.openSectionsMenu();
        mainPage.openJobSection();
        RabotaPage rabotaPage = new RabotaPage(driver);
        rabotaPage.openExtendedSearch();
        rabotaPage.setJobParameters("??????????????????????", "??????????");
        String searchField = rabotaPage.getResultText();
        String regionValue = rabotaPage.getRegionValue();
        Assertions.assertAll(
                () -> assertEquals("??????????????????????1", searchField),
                () -> assertEquals("??????????",regionValue)
        );
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
