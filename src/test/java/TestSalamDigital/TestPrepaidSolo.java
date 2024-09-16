package TestSalamDigital;

import com.shaft.driver.DriverFactory;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.*;
import salamdigital.AllPrepaidPlansPage;
import salamdigital.HomePage;
import salamdigital.OnboardingPage;
import salamdigital.SoloPrepaidPlansPage;

public class TestPrepaidSolo {
    SHAFT.GUI.WebDriver driver;
    SHAFT.TestData.JSON testData;

    //Pages object declarations
    HomePage homePage;
    SoloPrepaidPlansPage soloPrepaidPlans;
    AllPrepaidPlansPage prepaidPlansPage;
    OnboardingPage onboarding;


    @BeforeClass
    public void testPreparation(){
        testData = new SHAFT.TestData.JSON("SalamNewCustomer.json");
    }

    @BeforeMethod
    public void navigate(){
        driver = new SHAFT.GUI.WebDriver(DriverFactory.DriverType.CHROME);
        homePage =new HomePage(driver);
        prepaidPlansPage = new AllPrepaidPlansPage(driver);
        soloPrepaidPlans = new SoloPrepaidPlansPage(driver);
        onboarding = new OnboardingPage(driver);
        homePage.navigateToHomePage();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }


    @Story("Purchase new prepaid physical SIM")
    @Step("click on prepaid plans in homepage")
    @Test
    public void testClickOnPrepaidPlans(){
        String expectedUrl = "https://staging-my.salammobile.sa/en/category_selection/prepaid?only=sim,both";
        homePage.clickOnPrepaidPlans();
        Assert.assertEquals(prepaidPlansPage.pageUrl(),expectedUrl);
    }

    @Story("Purchase new prepaid physical SIM")
    @Step("Choose Solo prepaid plans")
    @Test(dependsOnMethods = {"testClickOnPrepaidPlans"})
    public void selectSoloPrepaidPlans(){
        homePage.clickOnPrepaidPlans();
        prepaidPlansPage.clickOnPrepaidSoloPlansCard();
        }

    @Story("Purchase new prepaid physical SIM")
    @Step("Choose Solo prepaid plan to purchase")
    @Test()
    public void selectSoloPlan(){
        String expectedPrice = "171.35";
        homePage.clickOnPrepaidPlans();
        prepaidPlansPage.clickOnPrepaidSoloPlansCard();
        soloPrepaidPlans.selectSolo149Plan();
        Assert.assertTrue(soloPrepaidPlans.detailsCardIsDisplayed());
        Assert.assertTrue(soloPrepaidPlans.priceOfThePlan().contains(expectedPrice));
    }

    @Story("Purchase new prepaid physical SIM")
    @Step("Click on subscribe button of the selected plan")
    @Test()
    public void testClickOnSubscribeButton(){
        homePage.clickOnPrepaidPlans();
        prepaidPlansPage.clickOnPrepaidSoloPlansCard();
        soloPrepaidPlans.selectSolo149Plan();
        soloPrepaidPlans.subscribeToSelectedPlan();
    }

    @Story("Purchase new prepaid physical SIM")
    @Step("Select SIM type => Physical SIM")
    @Test
    public void testSelectPhysicalSIM(){
        homePage.clickOnPrepaidPlans();
        prepaidPlansPage.clickOnPrepaidSoloPlansCard();
        soloPrepaidPlans.selectSolo149Plan();
        soloPrepaidPlans.subscribeToSelectedPlan();
        onboarding.choosePSIM();
        Assert.assertEquals(onboarding.activeStep(),"1");
    }

    @Story("Purchase new prepaid physical SIM")
    @Step("Select New Line")
    @Test
    public void testSelectNewLine(){
        homePage.clickOnPrepaidPlans();
        prepaidPlansPage.clickOnPrepaidSoloPlansCard();
        soloPrepaidPlans.selectSolo149Plan();
        soloPrepaidPlans.subscribeToSelectedPlan();
        onboarding.choosePSIM();
        onboarding.chooseNewLine();
        Assert.assertEquals(onboarding.activeStep(),"2");

    }

    @Story("Purchase new prepaid physical SIM")
    @Step("Select the mobile number")
    @Test
    public void testSelectMSISDN(){
        homePage.clickOnPrepaidPlans();
        prepaidPlansPage.clickOnPrepaidSoloPlansCard();
        soloPrepaidPlans.selectSolo149Plan();
        soloPrepaidPlans.subscribeToSelectedPlan();
        onboarding.choosePSIM();
        onboarding.chooseNewLine();
        onboarding.chooseMSISDN();
        Assert.assertEquals(onboarding.activeStep(),"3");
    }

    @Story("Purchase new prepaid physical SIM")
    @Step("Enter Customer Details")
    @Test
    public void enterCustomerDetails(){
    testSelectMSISDN();
    onboarding.enterCustomerID(testData.getTestData("ID"));
    onboarding.selectCustomerNationality();
    onboarding.acceptTerms();
    onboarding.clickOnContinue();
    }

    @Story("Purchase new prepaid physical SIM")
    @Step("Enter Contact Details")
    @Test
    public void enterContactDetails(){
        enterCustomerDetails();
        onboarding.enterCustomerName(testData.getTestData("name"));
        onboarding.enterContactNumber(testData.getTestData("mobile"));
        onboarding.enterCustomerEmail(testData.getTestData("email"));
        onboarding.clickOnContinue();
    }

    @Story("Purchase new prepaid physical SIM")
    @Step("Enter OTP digits")
    @Test
    public void testOTP(){
        enterContactDetails();
        onboarding.enterOTP(testData.getTestData("otp1"),testData.getTestData("otp2"),
                testData.getTestData("otp3"),testData.getTestData("otp4"));
        onboarding.clickOnOtpSubmit();
    }

    @Story("Purchase new prepaid physical SIM")
    @Step("Set Password and Confirm Password")
    @Test(dependsOnMethods = {"testOTP"})
    public void testPassword(){
        testOTP();
        onboarding.enterPasswordAndConfirm(testData.getTestData("password"));
        onboarding.clickOnContinue();
    }

    @Story("Purchase new prepaid physical SIM")
    @Step("Set Delivery Location")
    @Test(dependsOnMethods = {"testOTP", "testPassword"})
    public void testDeliveryLocation(){
        testPassword();
        onboarding.clickOnMapIcon();
        //onboarding.allowLocationAlert();
        onboarding.getMyLocation();


    }

    @Story("Purchase new prepaid physical SIM")
    @Step("Complete Payment for the selected plan")
    @Test(dependsOnMethods = {"testOTP","testPassword", "testDeliveryLocation"})
    public void testChoosePaymentMethod(){
        testDeliveryLocation();
        onboarding.acceptPayment();
        onboarding.selectPaymentCard();
        onboarding.completePayment(testData.getTestData("cardNumber"),testData.getTestData("expiry"),
                                    testData.getTestData("cardHolder"),testData.getTestData("ccv"));
        onboarding.submitPayment();

    }
    @Story("Purchase new prepaid physical SIM")
    @Step("Confirm Payment screen")

    @Test(dependsOnMethods = {"testChoosePaymentMethod", "testOTP","testPassword", "testDeliveryLocation"})
    public void testPaymentConfirm(){
        testChoosePaymentMethod();
        String expectedResult = "Payment Confirmed";
        String actualResult = onboarding.paymentConfirmed();

        Assert.assertEquals(actualResult,expectedResult);
    }


}
