package dataFiles;

import com.sun.net.httpserver.Authenticator;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
    int count = 0;
    int maxTry = 1;
    @Override
    public boolean retry(ITestResult iTestResult) {
        while (count < maxTry){
            count ++;
            return  true;
        }
        return false;
    }
}
