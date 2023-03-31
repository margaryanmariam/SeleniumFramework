import dataFiles.FundamentalUseForTests;
import dataFiles.Retry;
import driver.SingletonDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorValidationsTest extends FundamentalUseForTests {
    //public  LoginPage loginPage;
    @Test(groups = {"ErrorHandling"})
    public  void wrongLogin()  {
        LoginPage loginPage = new LoginPage(SingletonDriver.getDriver());
        loginPage.login("1argaryan.mariam1707@maininator.com", "Maryan1707");
        Assert.assertEquals("Incorrect email or password.", loginPage.getErrorMessage());
    }
    @Test(retryAnalyzer = Retry.class)
    public void productErrorValidation() throws InterruptedException {
        LoginPage loginPage = new LoginPage(SingletonDriver.getDriver());
        loginPage.login("margaryan.mariam1707@maininator.com","Maryan1707");
        Thread.sleep(1000);
        CatalogPage catalogPage = new CatalogPage(driver);
        catalogPage.addToCart("ZARA COAT 3");
        catalogPage.goToCartPage();
        MyCartPage myCartPage = new MyCartPage(driver);
        Assert.assertFalse(myCartPage.anyMatch("ZARA COAT 33"));
    }
}
