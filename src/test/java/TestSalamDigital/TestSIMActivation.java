package TestSalamDigital;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import salamdigital.ActivateSIMPage;
import salamdigital.ActivationTypePage;
import salamdigital.HomePage;

public class TestSIMActivation {
    SHAFT.GUI.WebDriver driver;
    SHAFT.TestData.JSON testData;

    HomePage homePage;
    ActivationTypePage activationType;
    ActivateSIMPage activateSIM;

@BeforeClass
    public void prepareTestData(){
    testData = new SHAFT.TestData.JSON("SalamNewCustomer.json");
 }

 @BeforeMethod
    public void setup(){
    driver = new SHAFT.GUI.WebDriver();
    homePage = new HomePage(driver);
    activationType = new ActivationTypePage(driver);
    activateSIM = new ActivateSIMPage(driver);
    homePage.navigateToHomePage();
 }

 @AfterMethod
 public void tearDown(){driver.quit();}

 @Story("Activate New SIM")
 @Step("click on Activate SIM button")
 @Test()
    public void testClickOnActivate(){
    homePage.clickOnActivateYourSIM();
    String expectedPage = "Select activation type";
    String actualPage =activationType.pageName();
     Assert.assertEquals(actualPage,expectedPage);
}

@Story("Activate New SIM")
@Step("click on I have ordered My SIM and delivered")
@Test
    public void testClickOnOrderedOnline(){
    homePage.clickOnActivateYourSIM();
    activationType.clickOnOrderedOnlineBtn();
    String actualResult = "Enter your ID and contact details used during your SIM order";
    String expectedResult = activateSIM.pageContext();
    Assert.assertEquals(actualResult,expectedResult);
}

@Story("Activate New SIM")
@Step("enter order details")
@Test
    public void testEnterOrderDetails(){
    homePage.clickOnActivateYourSIM();
    activationType.clickOnOrderedOnlineBtn();
    activateSIM.enterOrderDetails(testData.getTestData("ID"),testData.getTestData("mobile"),
            testData.getTestData("email"));
    String actualResult = activateSIM.getOrdersPageText();
    String expectedResult = "Existing orders";

    Assert.assertEquals(actualResult, expectedResult);
}

@Story("Activate New SIM")
    @Step("Pick the fist Order")
    @Test
    public void testOrderSelection(){
    homePage.clickOnActivateYourSIM();
    activationType.clickOnOrderedOnlineBtn();
    activateSIM.enterOrderDetails(testData.getTestData("ID"),testData.getTestData("mobile"),
            testData.getTestData("email"));
    activateSIM.selectFirstOrder();
    activateSIM.enterPassword(testData.getTestData("password"));

}

    @Story("Activate New SIM")
    @Step("Enter OTP digits")
    @Test
    public void testOTP(){
        homePage.clickOnActivateYourSIM();
        activationType.clickOnOrderedOnlineBtn();
        activateSIM.enterOrderDetails(testData.getTestData("ID"),testData.getTestData("mobile"),
                                      testData.getTestData("email"));
        activateSIM.selectFirstOrder();
        activateSIM.enterPassword(testData.getTestData("password"));
        activateSIM.enterOTP(testData.getTestData("otp1"),testData.getTestData("otp2"),
                testData.getTestData("otp3"),testData.getTestData("otp4"));

        activateSIM.enterICCID(testData.getTestData("iccid"));

    }

}
