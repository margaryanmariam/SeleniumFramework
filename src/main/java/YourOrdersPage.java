import globalUse.FundamentalUse;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class YourOrdersPage extends FundamentalUse {

    public YourOrdersPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "tr > td:nth-child(3)")
    private List<WebElement> orderList;


    public boolean anyMatch(String productName) {
        return orderList.stream().anyMatch(item -> item.getText().equalsIgnoreCase(productName));

    }
    public YourOrdersPage yourOrdersPageObj(){
        return  new YourOrdersPage(driver);
    }
}
