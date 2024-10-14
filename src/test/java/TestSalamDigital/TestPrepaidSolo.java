package TestSalamDigital;

import io.qameta.allure.Story;
import org.testng.annotations.*;
import salamdigital.LandingPage;

public class TestPrepaidSolo extends TestAbstract{

    @Story("Purchase new prepaid physical SIM")
    @Test
    public void testPurchaseSoloPlanWithPhysicalSim(){
     new LandingPage(driver.get())
             .navigate()
             .clickOnPrepaidPlans()
             .clickOnPrepaidSoloPlansCard()
             .selectSolo199Plan()
             .subscribeToSelectedPlan()
             .choosePSIM()
             .chooseNewLine()
             .chooseMSISDN()
             .enterCustomerID(testData.get().getTestData("ID"))
             .selectCustomerNationality()
             .acceptTerms()
             .enterCustomerDetails(testData.get().getTestData("name")
                     ,testData.get().getTestData("mobile")
                     ,testData.get().getTestData("email"))
             .enterOTP("7","4","2","1")
             .enterPasswordAndConfirm("Temp@123")
//             .allowLocationAlert()
             .clickOnMapIcon()
             .getMyLocation()
             .acceptPayment()
             .selectPaymentCard()
             .enterHyperPayCardDetails(testData.get().getTestData("cardNumber"))
             .submitValidHyperPayment()
             .getPaymentTitle()
             .getSubTitle()
             .returnToLandingPage();

    }

}
