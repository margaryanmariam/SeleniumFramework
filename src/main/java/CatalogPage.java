import globalUse.FundamentalUse;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import java.util.List;

public class CatalogPage extends FundamentalUse {

    By waitForLoadIconDisappeared = By.cssSelector(".ng-animating");
    By addToCart = By.cssSelector("[class = 'btn w-10 rounded']");

    public CatalogPage(WebDriver driver) {
        super(driver);

    }
    @FindBy(css = "[class='card']")
    public List<WebElement> productList;
    @FindBy(css = "[class = 'card']")
    public WebElement waitForHomePage;
    public List<WebElement> getProductList() {
        waitForVisibilityOfElement(waitForHomePage);
        return productList;
    }
    public WebElement getNeededItem(String productName) {

        return getProductList().stream()
                .filter(product -> product.findElement(By.cssSelector("b"))
                        .getText()
                        .equalsIgnoreCase(productName))
                .findFirst()
                .orElse(null);
    }

    public void addToCart(String productName) {
        WebElement element = getNeededItem(productName).findElement(addToCart);
        click(element);
        waitInvisibilityOfElementLocated(waitForLoadIconDisappeared);
    }
    public  CatalogPage categoryPageObj(){
        return  new CatalogPage(driver);
    }

}
