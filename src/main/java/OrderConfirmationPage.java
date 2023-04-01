import globalUse.FundamentalUse;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;


public class OrderConfirmationPage extends FundamentalUse {
    public Actions action;
    public OrderConfirmationPage(WebDriver driver) {
        super(driver);
        action  =  new Actions(driver);
    }

    @FindBy(css ="[placeholder='Select Country']")
    private WebElement selectCountry;
    @FindBy(css ="[class*='ta-item']:nth-child(3)")
    private WebElement country;
    @FindBy(css = "[class='actions'] a")
    private WebElement placeOrderButton;
    @FindBy(css = "[class*='ta-item']:nth-child(3)")
    private WebElement waitForCountryName;

    public void setSelectCountry(String countryName){
        action.sendKeys(selectCountry,countryName).build().perform();
        waitForElementToBeClickable(waitForCountryName);
        click(country);
    }

    public void placeOrder(){
        scrollDownToElementAndClick(placeOrderButton);
    }

    public OrderConfirmationPage orderConfirmationPageObj(){
        return  new OrderConfirmationPage(driver);
    }




}
