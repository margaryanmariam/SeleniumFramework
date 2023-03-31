import globalUse.FundamentalUse;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage extends FundamentalUse {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "userEmail")
    private WebElement userEmail;

    @FindBy(id = "userPassword")
    private WebElement userPassword;

    @FindBy(id = "login")
    private WebElement submit;

    @FindBy(css = "[id ='toast-container']>div")
    private WebElement errorMessage;

    @FindBy(css = "[id ='toast-container']>div")
    private WebElement error;

    public void login(String login, String password) {
        sendKeys(userEmail, login);
        sendKeys(userPassword, password);
        click(submit);
    }

    public String getErrorMessage() {
        waitForVisibilityOfElement(error);
        return errorMessage.getText();
    }
}
