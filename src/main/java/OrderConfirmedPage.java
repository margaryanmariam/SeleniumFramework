import globalUse.FundamentalUse;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class OrderConfirmedPage extends FundamentalUse {

    public OrderConfirmedPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[class='box'] h1")
    private WebElement message;

    public String getSuccessMessage(){
      return   message.getText();
    }

    public OrderConfirmedPage orderConfirmedPageObj(){
        return  new OrderConfirmedPage(driver);
    }

}
