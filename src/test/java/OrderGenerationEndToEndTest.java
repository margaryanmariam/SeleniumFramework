import dataFiles.FundamentalUseForTests;
import dataFiles.Retry;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class OrderGenerationEndToEndTest extends FundamentalUseForTests {
    String productName = "ZARA COAT 3";
    public LoginPage loginPage;

    @Test(dataProvider = "getData", groups = "Purchase",retryAnalyzer = Retry.class)
    public void createOrder(HashMap<String, String> input) throws InterruptedException {
        loginPage = new LoginPage(driver);
        loginPage.login(input.get("email"), input.get("password"));
        Thread.sleep(1000);
        CatalogPage catalogPage = new CatalogPage(driver);
        catalogPage.addToCart(input.get("productName"));
        catalogPage.goToCartPage();
        MyCartPage myCartPage = new MyCartPage(driver);
        Assert.assertTrue(myCartPage.anyMatch(input.get("productName")));
        myCartPage.goToOrderConfirmationPage();
        OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage(driver);
        orderConfirmationPage.setSelectCountry("India");
        orderConfirmationPage.placeOrder();
        OrderConfirmedPage orderConfirmedPage = new OrderConfirmedPage(driver);
        Assert.assertTrue(orderConfirmedPage.getSuccessMessage().equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }

    @Test(dependsOnMethods = {"createOrder"})
    public void checkCreatedOrder() throws InterruptedException {
        loginPage = new LoginPage(driver);
        loginPage.login("margaryan.mariam1707@maininator.com", "Maryan1707");
        Thread.sleep(1000);
        CatalogPage catalogPage = new CatalogPage(driver);
        catalogPage.goToOrdersPage();
        YourOrdersPage yourOrdersPage = new YourOrdersPage(driver);
        Assert.assertTrue(yourOrdersPage.anyMatch(productName));
    }


    @DataProvider
    public Object[][] getData() throws IOException {
//        HashMap<String,String> map = new HashMap<>();
//        map.put("email","margaryan.mariam1707@maininator.com");
//        map.put("password","Maryan1707");
//        map.put("productName","ZARA COAT 3");
//        HashMap<String,String> map1 = new HashMap<>();
//        map1.put("email","margaryan.mariam1707@maininator.com");
//        map1.put("password","Maryan1707");
//        map1.put("productName","ADIDAS ORIGINAL");
        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//dataFiles//PurchaseOrder.json");
        return new Object[][]{{data.get(0)}, {data.get(1)}};
    }

}
