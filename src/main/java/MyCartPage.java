import globalUse.FundamentalUse;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import java.util.List;

public class MyCartPage extends FundamentalUse {
    public WebDriver driver;
    public MyCartPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(css ="[class='infoWrap']")
    private List<WebElement> itemsInOrder ;
    @FindBy(css ="li[class='totalRow']>button")
    private WebElement checkoutButton;

    public boolean anyMatch (String productName){
        return itemsInOrder.stream()
                .anyMatch(item -> item.findElement(By.cssSelector("h3")).getText()
                        .equalsIgnoreCase(productName));

    }

    public void goToOrderConfirmationPage(){
        click(checkoutButton);
    }
    public MyCartPage myCartPageObj(){
        return  new MyCartPage(driver);
    }
}
