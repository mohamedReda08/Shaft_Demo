package salamdigital;

import com.shaft.driver.SHAFT;
import com.shaft.enums.internal.Screenshots;
import io.qameta.allure.Step;
import org.openqa.selenium.By;


public class OnboardingPage extends Page{
    SHAFT.GUI.WebDriver driver;

    By physicalSIMLocator = By.xpath("//span[text() = 'SIM']");
    By eSIMLocator = By.xpath("//span[text() = 'eSIM']");
    By newLineLocator = By.xpath("//span[text() = 'New Line']");
    By switchToSalamLocator = By.xpath("//span[text() = 'Switch to Salam Mobile']");
    By activeStepLocator = By.xpath("//div[@class = 'style_stepCircle__3JwIW style_LTR__Hi0tQ style_active__1ovmc']");
    By msisdnsSectionLocator = By.xpath("//div[@class = 'style_numbersWrapper__3IZWv']");
    By msisdnLocator = By.xpath("//div[@class = 'style_numbersWrapper__3IZWv']/child::div[10]");
    By IDLocator = By.xpath("//input[@name = 'idNumber']");
    By nationalityLocator = By.xpath("//div[@id = 'nationality']");
    By saudiArabiaLocator = By.xpath("(//div//li)[1]");
    By acceptTermsLocator = By.xpath("//span[@class ='acceptanceText' ]");
    By continueButtonLocator = By.xpath("//button[text() = 'Continue']");
    By customerNameLocator = By.xpath("//input[@name = 'name']");
    By contactMobileLocator = By.xpath("//input[@name = 'mobileNumber']");
    By emailLocator = By.xpath("//input[@name = 'email']");
    By otpFirstDigit = By.xpath("//input[@id = 'digit1']");
    By otpSecondDigit = By.xpath("//input[@id = 'digit2']");
    By otpThirdDigit = By.xpath("//input[@id = 'digit3']");
    By otpFourthDigit = By.xpath("//input[@id = 'digit4']");
    By submitLocator = By.xpath("//button[text() ='Submit'");
    By otpSubmitLocator = By.xpath("//div[@class = 'style_btnZone__1kWJM']/button");
    By passwordLocator = By.xpath("//input[@name = 'password']");
    By repeatPasswordLocator = By.xpath("//input[@name = 'confirmPassword']");
    By mapPinLocator = By.xpath("//div[@class = 'style_pin__1wvRZ']");


    //  Locate elements on Map Pop-up
    By locateMeLocator = By.xpath("//div[@id = 'MyLocationIcon']");
    By confirmButtonLocator = By.xpath("//button[text() ='Confirm']");
    By mapLocator = By.xpath("//div[@aria-label='Map']");
    By cityFieldLocator = By.xpath("//div[@id='city']");
    By riyadhCityLocator = By.xpath("//li[@data-value='41']");
    By locationContinueBtnLocator = By.xpath("//button");

//  Payment Step Locators
    By acceptSalamTermsAndConditionsLocator = By.xpath("//span[contains(text(),'I accept Salam')]");
    By checkoutBtnLocator = By.xpath("//button[text() = 'Check Out']");

//  Payment pop-up cards Locators
    By creditCardLocator = By.xpath("(//span[text() = 'Credit Card'])[1]");


    public OnboardingPage(SHAFT.GUI.WebDriver driver){
        super(driver);
        this.driver = driver;
    }

@Step("Select Physical SIM")
    public OnboardingPage choosePSIM(){
        driver.element().click(physicalSIMLocator);
        return new OnboardingPage(driver);
    }
@Step("Select E-SIM")
    public OnboardingPage chooseESIM(){
        driver.element().click(eSIMLocator);
        return new OnboardingPage(driver);
    }

@Step("Choose new Line")
    public OnboardingPage chooseNewLine(){
        driver.element().click(newLineLocator);
        return this;
    }
@Step("Select Port-In to Salam")
    public void choosePortIn(){
        driver.element().click(switchToSalamLocator);
    }

@Step("Select MSISDN")
    public OnboardingPage chooseMSISDN(){
        driver.waitUntil(d->{
            driver.element().scrollToElement(msisdnsSectionLocator);
            driver.element().scrollToElement(msisdnLocator).click(msisdnLocator);
            return true;
        });
        return this;
    }
@Step("Enter customer ID ")
    public OnboardingPage enterCustomerID(String ID){
        driver.element().typeSecure(IDLocator, ID);
    return new OnboardingPage(driver);
    }
@Step("Select Customer Nationality")
    public OnboardingPage selectCustomerNationality(){
        driver.element().click(nationalityLocator).
        and().scrollToElement(saudiArabiaLocator).click(saudiArabiaLocator);
        return new OnboardingPage(driver);
    }

@Step("Accept Salam Terms and Conditions")
    public OnboardingPage acceptTerms(){
        driver.element().click(acceptTermsLocator);
        driver.element().click(continueButtonLocator);
        return new OnboardingPage(driver);
    }
@Step("Enter Customer Details")
    public OnboardingPage enterCustomerDetails(String customerName, String contactNumber,String email){
        driver.element().typeSecure(customerNameLocator,customerName);
        driver.element().typeSecure(contactMobileLocator, contactNumber);
        driver.element().typeSecure(emailLocator,email);
        driver.element().click(continueButtonLocator);
            return this;
    }


@Step("Enter OTP")
    public OnboardingPage enterOTP(String otp1, String otp2, String otp3, String otp4){
        driver.element().typeSecure(otpFirstDigit,otp1);
        driver.element().typeSecure(otpSecondDigit, otp2);
        driver.element().typeSecure(otpThirdDigit, otp3);
        driver.element().typeSecure(otpFourthDigit, otp4);
        driver.element().click(otpSubmitLocator);
        driver.browser().captureScreenshot(Screenshots.FULL);
        return this;
    }

    @Step("Enter Password")
    public OnboardingPage enterPasswordAndConfirm(String password){
        driver.element().type(passwordLocator,password);
        driver.element().type(repeatPasswordLocator,password);
        driver.element().click(continueButtonLocator);
        driver.browser().captureScreenshot(Screenshots.FULL);
        return this;
    }
    @Step("Open Map Pop-up")
    public OnboardingPage clickOnMapIcon(){
        driver.element().click(mapPinLocator);
        return this;
    }

    public OnboardingPage allowLocationAlert(){
        if(driver.alert().isAlertPresent()){
            driver.alert().acceptAlert();
        }
       return this;
    }

    @Step("Set Delivery Location")
    public OnboardingPage getMyLocation(){
        driver.waitUntil(d ->{
            driver.element().isElementClickable(mapLocator);
            driver.element().click(locateMeLocator);
            driver.element().click(confirmButtonLocator);
            driver.browser().captureScreenshot(Screenshots.FULL);
            driver.element().click(cityFieldLocator);
            driver.element().scrollToElement(riyadhCityLocator).click(riyadhCityLocator);
            driver.element().click(locationContinueBtnLocator);
            return true;
        });
        return this;
    }
@Step("Accept Payment Amount")
    public OnboardingPage acceptPayment(){
        driver.element().scrollToElement(acceptSalamTermsAndConditionsLocator);
        driver.element().click(acceptSalamTermsAndConditionsLocator);
        driver.element().click(checkoutBtnLocator);
        driver.browser().captureScreenshot(Screenshots.FULL);
        return this;
    }
    @Step("Select Credit Card as payment method")
    public HyperPayPage selectPaymentCard(){
        driver.waitUntil(d->{
            driver.element().isElementDisplayed(creditCardLocator);
            driver.element().click(creditCardLocator);
            return true;
        });
        return new HyperPayPage(driver);
    }


}
