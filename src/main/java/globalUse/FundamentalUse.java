package globalUse;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Logger;

public class FundamentalUse {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Logger logger;
    protected final int TIMEOUT = 15;


    public FundamentalUse(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        PageFactory.initElements(driver, this);
        logger = Logger.getLogger(FundamentalUse.class.getName());
    }

    @FindBy(css = "[routerlink='/dashboard/cart']>i")
    WebElement goToCart;
    @FindBy(css = "[routerlink='/dashboard/myorders']>i")
    WebElement goToOrdersPage;


    public void goToOrdersPage() {
        waitForElementToBeClickable(goToOrdersPage);
        click(goToOrdersPage);
        waitForPageLoaded();
    }

    public void goToCartPage() {
        waitForElementToBeClickable(goToCart);
        click(goToCart);
        waitForPageLoaded();
    }

    protected void click(WebElement webElement) {
        waitForElementToBeClickable(webElement);
        logger.info("Clicking to " + webElement.getText());
        webElement.click();
    }

    protected void sendKeys(WebElement webElement, String text) {
        waitForVisibilityOfElement(webElement);
        logger.info("Filling " + webElement.getText() + "field with text " + text);
        webElement.sendKeys(text);
    }

    public void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForVisibilityOfElement(WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public void waitInvisibilityOfElementLocated(By findBy) {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
        } catch (WebDriverException e) {
            System.out.println("Locator of element still visible");
        }
    }

    public void waitForPageLoaded() {
        ExpectedCondition<Boolean> pageLoadCondition =
                driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        wait.until(pageLoadCondition);
    }

    public void scrollDownToElementAndClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }
}
