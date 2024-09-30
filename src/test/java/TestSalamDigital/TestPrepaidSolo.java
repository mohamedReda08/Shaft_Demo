package TestSalamDigital;

import com.shaft.driver.DriverFactory;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;
import salamdigital.AllPrepaidPlansPage;
import salamdigital.LandingPage;
import salamdigital.OnboardingPage;
import salamdigital.SoloPrepaidPlansPage;

import java.io.FileWriter;

public class TestPrepaidSolo {
    SHAFT.GUI.WebDriver driver;
    SHAFT.TestData.JSON testData;

    //Pages object declarations
    LandingPage landingPage;
    SoloPrepaidPlansPage soloPrepaidPlans;
    AllPrepaidPlansPage prepaidPlansPage;
    OnboardingPage onboarding;
    JSONObject jsonObject;
    FileWriter writer;
    String filePath = "//resources/testDataFiles/msisdn.json";

    @BeforeClass
    public void testPreparation(){
        testData = new SHAFT.TestData.JSON("SalamNewCustomer.json");
        jsonObject = new JSONObject();
//        writer = new FileWriter("//resources/testDataFiles/msisdn.json",false);

    }

    @BeforeMethod
    public void navigate(){
        driver = new SHAFT.GUI.WebDriver(DriverFactory.DriverType.CHROME);
        landingPage =new LandingPage(driver);
        prepaidPlansPage = new AllPrepaidPlansPage(driver);
        soloPrepaidPlans = new SoloPrepaidPlansPage(driver);
        onboarding = new OnboardingPage(driver);
        landingPage.navigateToHomePage();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }


    @Story("Purchase new prepaid physical SIM")
    @Step("click on prepaid plans in LandingPage")
    @Test
    public void testClickOnPrepaidPlans(){
        String expectedUrl = "https://staging-my.salammobile.sa/en/category_selection/prepaid?only=sim,both";
        landingPage.clickOnPrepaidPlans();
        Assert.assertEquals(prepaidPlansPage.pageUrl(),expectedUrl);
    }

    @Story("Purchase new prepaid physical SIM")
    @Step("Choose Solo prepaid plans")
    @Test(dependsOnMethods = {"testClickOnPrepaidPlans"})
    public void selectSoloPrepaidPlans(){
        landingPage.clickOnPrepaidPlans();
        prepaidPlansPage.clickOnPrepaidSoloPlansCard();
        }

    @Story("Purchase new prepaid physical SIM")
    @Step("Choose Solo prepaid plan to purchase")
    @Test()
    public void selectSoloPlan(){
        String expectedPrice = "171.35";
        landingPage.clickOnPrepaidPlans();
        prepaidPlansPage.clickOnPrepaidSoloPlansCard();
        soloPrepaidPlans.selectSolo149Plan();
        Assert.assertTrue(soloPrepaidPlans.detailsCardIsDisplayed());
        Assert.assertTrue(soloPrepaidPlans.priceOfThePlan().contains(expectedPrice));
    }

    @Story("Purchase new prepaid physical SIM")
    @Step("Click on subscribe button of the selected plan")
    @Test()
    public void testClickOnSubscribeButton(){
        landingPage.clickOnPrepaidPlans();
        prepaidPlansPage.clickOnPrepaidSoloPlansCard();
        soloPrepaidPlans.selectSolo149Plan();
        soloPrepaidPlans.subscribeToSelectedPlan();
    }

    @Story("Purchase new prepaid physical SIM")
    @Step("Select SIM type => Physical SIM")
    @Test
    public void testSelectPhysicalSIM(){
        landingPage.clickOnPrepaidPlans();
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
        landingPage.clickOnPrepaidPlans();
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
        landingPage.clickOnPrepaidPlans();
        prepaidPlansPage.clickOnPrepaidSoloPlansCard();
        soloPrepaidPlans.selectSolo149Plan();
        soloPrepaidPlans.subscribeToSelectedPlan();
        onboarding.choosePSIM();
        onboarding.chooseNewLine();
        onboarding.chooseMSISDN(filePath);
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

    @Test()
    public void testPaymentConfirm(){
        landingPage.clickOnPrepaidPlans();
        prepaidPlansPage.clickOnPrepaidSoloPlansCard();
        soloPrepaidPlans.selectSolo149Plan();
        soloPrepaidPlans.subscribeToSelectedPlan();
        onboarding.choosePSIM();
        onboarding.chooseNewLine();
        onboarding.chooseMSISDN(filePath);
        onboarding.enterCustomerID(testData.getTestData("ID"));
        onboarding.selectCustomerNationality();
        onboarding.acceptTerms();
        onboarding.clickOnContinue();
        onboarding.enterCustomerName(testData.getTestData("name"));
        onboarding.enterContactNumber(testData.getTestData("mobile"));
        onboarding.enterCustomerEmail(testData.getTestData("email"));
        onboarding.clickOnContinue();
        onboarding.enterOTP(testData.getTestData("otp1"),testData.getTestData("otp2"),
                testData.getTestData("otp3"),testData.getTestData("otp4"));
        onboarding.clickOnOtpSubmit();
        onboarding.enterPasswordAndConfirm(testData.getTestData("password"));
        onboarding.clickOnContinue();
        onboarding.clickOnMapIcon();
        //onboarding.allowLocationAlert();
        onboarding.getMyLocation();
        onboarding.acceptPayment();
        onboarding.selectPaymentCard();
        onboarding.completePayment(testData.getTestData("cardNumber"),testData.getTestData("expiry"),
                testData.getTestData("cardHolder"),testData.getTestData("ccv"));
        onboarding.submitPayment();
        String expectedResult = "Payment Confirmed";
        String actualResult = onboarding.paymentConfirmed();

        Assert.assertEquals(actualResult,expectedResult);
    }


}
