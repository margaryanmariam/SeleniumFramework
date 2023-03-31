package dataFiles;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import driver.SingletonDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class FundamentalUseForTests {
    public WebDriver driver;
    //public  LoginPage loginPage;


    @BeforeMethod(alwaysRun = true)
    public void initializeDriver() {
        driver = SingletonDriver.getDriver();
        driver.get("https://rahulshettyacademy.com/client");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }


//This method will make json to string and return List of HashMap.
    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
        //read json to String
        String jsonContent =  FileUtils.readFileToString(new File(filePath));
        //Convert String to HashMap Jackson databind
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });
        return  data;
    }

    public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source,new File(System.getProperty("user.dir")+ "//screenshotReports//"+ testCaseName + ".png"));
        return System.getProperty("user.dir")+ "//screenshotReports//"+ testCaseName + ".png";
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        SingletonDriver.closeDriver();
    }
}
