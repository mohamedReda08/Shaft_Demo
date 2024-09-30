package TestSalamDigital;

import com.shaft.driver.DriverFactory;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import salamdigital.HomePage;
import salamdigital.LandingPage;
import salamdigital.LoginPage;
import salamdigital.PostpaidPlansPage;

public class TestChangePlanPostpaidToPrepaid {
    SHAFT.GUI.WebDriver driver;
    SHAFT.TestData.JSON testData;

    LandingPage landingPage;
    LoginPage login;
    HomePage homePage;
    PostpaidPlansPage postpaidPlans;

    @BeforeClass
    public void initiateTest(){
        testData = new SHAFT.TestData.JSON("changePlanData.json");
    }

    @BeforeMethod
    public void setup(){
        driver = new SHAFT.GUI.WebDriver(DriverFactory.DriverType.CHROME);
        landingPage = new LandingPage(driver);
        login = new LoginPage(driver);
        homePage = new HomePage(driver);

        landingPage.navigateToHomePage();
    }

    @AfterMethod
    public void tearDown(){driver.quit();}

    @Story("Change Plan from Postpaid to Prepaid")
    @Step("Login with Un-registered Postpaid Number")
    @Test
    public void testQuickLogin(){
    landingPage.clickOnSignIn();
    login.quickLogin(testData.getTestData("unregisteredMsisdn"));
    }
    @Story("Change Plan from Postpaid to Prepaid")
    @Step("Login with registered Postpaid Number")
    @Test
    public void testLogin(){
        landingPage.clickOnSignIn();
        login.registeredUserLogin(testData.getTestData("registeredMsisdn"));
    }


    @Story("Change Plan from Postpaid to Prepaid")
    @Step("Login with registered Postpaid Number")
    @Test
    public void testChangePlanFrmPostToPre(){
        landingPage.clickOnSignIn();
        login.quickLogin(testData.getTestData("unregisteredMsisdn"));
        homePage.clickOnChangePlan();
    }




}
