package TestSalamDigital;

import com.shaft.driver.DriverFactory;
import com.shaft.driver.SHAFT;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import salamdigital.HomePage;
import salamdigital.LandingPage;
import salamdigital.LoginPage;
import salamdigital.PostpaidPlansPage;

public class TestAbstract {

    LandingPage landingPage;


    ThreadLocal<SHAFT.GUI.WebDriver> driver = new ThreadLocal<>();
    ThreadLocal<SHAFT.TestData.JSON> testData = new ThreadLocal<>();
    @BeforeClass
    public void initiateTest(){
        testData.set(new SHAFT.TestData.JSON("SalamNewCustomer.json")) ;
    }

    @BeforeMethod
    public void setup(){
        driver.set(new SHAFT.GUI.WebDriver());
    }

    @AfterMethod
    public void tearDown(){driver.get().quit();}
}
