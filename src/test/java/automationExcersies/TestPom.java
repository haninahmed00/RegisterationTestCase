package automationExcersies;

import io.qameta.allure.Severity;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import registerationPages.*;
import utilites.DriverFactory;
import utilites.DriverFactory;
import utilites.JsonFileManager;
import utilites.PropertiesReader;

public class TestPom {
    private WebDriver driver;
    private HomePage homePage;
    private SignUpPage signUpPage;
    private RegisterationPage registerationPage;
    private AccountCreatedPage accountCreatedPage;
    private AccountDeletedPage accountDeletedPage;
    private JsonFileManager jsonFileManager;
    private JsonFileManager testData;

    @BeforeClass
    public void setup() {
        PropertiesReader.loadProperties();
        driver = DriverFactory.initiateDriver(System.getProperty("BrowserName","chrome"),true, true);
        homePage = new HomePage(driver);
        signUpPage = new SignUpPage(driver);
        registerationPage = new RegisterationPage(driver);
        accountCreatedPage = new AccountCreatedPage(driver);
        jsonFileManager = new JsonFileManager("D:\\Users\\java\\Java Task\\RegisterationTestCase\\src\\test\\resources\\testData.json");
    }

    @Test(description = "Create new account with valid data")

    public void testCaseOne() {
        homePage.navigate();
        homePage.validateHomePage();
        signUpPage.navigateToSignUpPage();
        signUpPage.validateNewUserHeader();
        signUpPage.performSignUp(jsonFileManager.getTestData("username"), jsonFileManager.getTestData("email"));
        registerationPage.validateRegistrationPageLoaded();
        registerationPage.fillAccountInformation("2134", "29", "May", "2000");
        registerationPage.fillUserDetails("Haneen", "Ahmed", "Giza", "NewCiaro", "Zayed", "Canada", "mySatte", "Egypt", "12345", "01123575432");
        registerationPage.clickCreateAccountButton();
        accountCreatedPage.validateAccountCreated("ACCOUNT CREATED!");
        accountCreatedPage.clickContinueButton();

    }

    @AfterTest
    public void close() {
        driver.quit();

    }
}
