package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SingletonDriver {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ChromeOptions option = new ChromeOptions();
    private SingletonDriver() {
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            switch (System.getProperty("browser", "chrome")) {
                case "firefox": {
                    //Initializing the firefox driver (Gecko)
                    System.setProperty("webdriver.gecko.driver", "/home/beeweb/Desktop/JavaProjects/UdemyClass/resources/geckodriver");
                    driver.set(new FirefoxDriver());
                    driver.get();
                }
                default: {
                    //Initialize the chrome driver
                    option.addArguments("--remote-allow-origins=*");
                    WebDriverManager.chromedriver().setup();
                    driver.set(new ChromeDriver(option));
                    driver.get();
                }
            }

            driver.get().manage().window().maximize();
        }
        return driver.get();
    }

    public static void closeDriver() {
        driver.get().close();
        driver.set(null);
    }
}
