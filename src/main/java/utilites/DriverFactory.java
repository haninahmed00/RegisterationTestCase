package utilites;



import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {
    static WebDriver driver;

    @Step("Initializing WebDriver for browser: {browserName}, headless: {headlessExecution}")
    public static WebDriver initiateDriver(String browserName, boolean maximize, boolean headlessExecution) {

        if (browserName == null || browserName.isEmpty()) {
            throw new IllegalArgumentException("Browser name cannot be null or empty.");
        }

        switch (browserName.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                if (headlessExecution) {
                    chromeOptions.addArguments("--headless");
                    System.out.println("Headless Chrome Driver initialized");
                }
                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headlessExecution) {
                    firefoxOptions.addArguments("--headless");
                    System.out.println("Headless Firefox Driver initialized");
                }
                driver = new FirefoxDriver(firefoxOptions);
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }

        if (maximize) {
            driver.manage().window().maximize();
        }

        System.out.println("Browser initialized successfully: " + browserName);
        return driver;
    }
}
