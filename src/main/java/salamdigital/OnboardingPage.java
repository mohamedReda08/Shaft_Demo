package salamdigital;

import com.shaft.driver.SHAFT;
import com.shaft.enums.internal.Screenshots;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class OnboardingPage {
    SHAFT.GUI.WebDriver driver;
    Alert alert;


    By physicalSIMLocator = By.xpath("//span[text() = 'SIM']");
    By eSIMLocator = By.xpath("//span[text() = 'eSIM']");
    By newLineLocator = By.xpath("//span[text() = 'New Line']");
    By switchToSalamLocator = By.xpath("//span[text() = 'Switch to Salam Mobile']");
    By activeStepLocator = By.xpath("//div[@class = 'style_stepCircle__3JwIW style_LTR__Hi0tQ style_active__1ovmc']");
    By firstMSISDNLocator = By.xpath("//div[@class = 'style_numbersWrapper__3IZWv']/child::div[2]");
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
    By locationContinueBtnLocator = By.xpath("//span[text() = 'Continue']");

//  Payment Step Locators
    By acceptSalamTermsAndConditionsLocator = By.xpath("//span[contains(text(),'I accept Salam')]");
    By checkoutBtnLocator = By.xpath("//button[text() = 'Check Out']");

//  Payment pop-up cards Locators
    By creditCardLocator = By.xpath("(//span[text() = 'Credit Card'])[1]");

//  Credit Card inputs Locators
    By cardNumIFrame = By.xpath("//iframe[@title = 'Card Number']");
    By cardCVVIFrame = By.xpath("//iframe[@name='card.cvv']");

    By creditCardNumberLocator = By.xpath("//input[@name = 'card.number']");
    By cardExpiryLocator = By.xpath("//input[@data-action = 'blur-card-expiry']");
    By cardHolderLocator = By.xpath("//input[@name = 'card.holder']");
    By cvvLocator = By.xpath("//input[@name = 'card.cvv']");
    By payNowBtnLocator = By.xpath("//button[text() = 'Pay now']");

    By submitIFrameLocator = By.xpath("//iframe[@class ='wpwl-target']");
    By paymentSubmitLocator = By.xpath("//input[@name='commit']");

    By paymentConfirmedLocator = By.xpath("//div[contains(@class,'paymentConfirmedTitle')]");

    public OnboardingPage(SHAFT.GUI.WebDriver driver){
        this.driver = driver;
    }

    public void choosePSIM(){
        driver.element().click(physicalSIMLocator);
    }

    public void chooseESIM(){
        driver.element().click(eSIMLocator);
    }

    public void chooseNewLine(){
        driver.element().click(newLineLocator);
    }

    public void choosePortIn(){
        driver.element().click(switchToSalamLocator);
    }

    public void chooseMSISDN(){
        driver.element().scrollToElement(firstMSISDNLocator).click(firstMSISDNLocator);
    }

    public void enterCustomerID(String ID){
        driver.element().typeSecure(IDLocator, ID);

    }
    public void selectCustomerNationality(){
        driver.element().click(nationalityLocator).
        and().scrollToElement(saudiArabiaLocator).click(saudiArabiaLocator);
    }

    public void acceptTerms(){
         driver.element().click(acceptTermsLocator);
    }

    public void clickOnContinue(){
        driver.element().click(continueButtonLocator);
    }

    public void enterCustomerName(String customerName){
        driver.element().typeSecure(customerNameLocator,customerName);
    }

    public void enterContactNumber(String contactNumber){
        driver.element().typeSecure(contactMobileLocator, contactNumber);
    }

    public void enterCustomerEmail(String email){
        driver.element().typeSecure(emailLocator,email);
    }

    public void clickOnSubmit(){
        driver.element().click(submitLocator);
    }

    public void enterOTP(String otp1, String otp2, String otp3, String otp4){
        driver.element().typeSecure(otpFirstDigit,otp1);
        driver.element().typeSecure(otpSecondDigit, otp2);
        driver.element().typeSecure(otpThirdDigit, otp3);
        driver.element().typeSecure(otpFourthDigit, otp4);
    }

    public void clickOnOtpSubmit(){
        driver.element().click(otpSubmitLocator);
        driver.browser().captureScreenshot(Screenshots.FULL);
    }
    public void enterPasswordAndConfirm(String password){
        driver.element().type(passwordLocator,password);
        driver.element().type(repeatPasswordLocator,password);
        driver.browser().captureScreenshot(Screenshots.FULL);
    }
    public void clickOnMapIcon(){
        driver.element().click(mapPinLocator);
    }

    public void allowLocationAlert(){
       driver.waitUntil(ExpectedConditions.alertIsPresent());
       alert.getText();
       alert.accept();
    }

    public void getMyLocation(){
        driver.waitUntil(driver ->{
            driver.findElement(mapLocator);
            return true;
        });
        driver.element().click(locateMeLocator);
        driver.element().click(confirmButtonLocator);
        driver.browser().captureScreenshot(Screenshots.FULL);
        driver.element().click(locationContinueBtnLocator);
    }

    public void acceptPayment(){
        driver.element().scrollToElement(acceptSalamTermsAndConditionsLocator);
        driver.element().click(acceptSalamTermsAndConditionsLocator);
        driver.element().click(checkoutBtnLocator);
        driver.browser().captureScreenshot(Screenshots.FULL);
    }

    public void selectPaymentCard(){
        driver.waitUntil(d->{
            driver.element().isElementDisplayed(creditCardLocator);
            driver.element().click(creditCardLocator);
            return true;
        });
    }


    public void completePayment(String cardNumber, String expiry, String cardHolder, String ccv){

       driver.waitUntil(d->{
           driver.element().isElementDisplayed(cardExpiryLocator);
           driver.element().type(cardExpiryLocator,"1227");

           driver.element().isElementDisplayed(cardHolderLocator);
           driver.element().type(cardHolderLocator,"Test");

           driver.element().switchToIframe(cardCVVIFrame);
           driver.element().isElementDisplayed(cvvLocator);
           driver.element().type(cvvLocator,"123");
           driver.element().switchToDefaultContent();

           driver.element().switchToIframe(cardNumIFrame);
           driver.element().type(creditCardNumberLocator,cardNumber);
           driver.element().switchToDefaultContent();

           driver.element().click(payNowBtnLocator);
           driver.browser().captureScreenshot(Screenshots.FULL);
           return true;
       });
    }

    public void submitPayment(){
        driver.element().switchToIframe(submitIFrameLocator);
        driver.element().click(paymentSubmitLocator);
        driver.browser().captureScreenshot(Screenshots.FULL);
        driver.element().switchToDefaultContent();
    }

    //Assertion Methods
    public String activeStep(){
        return driver.element().getText(activeStepLocator);
    }
    public String paymentConfirmed(){
        driver.browser().captureScreenshot(Screenshots.FULL);
        return driver.element().getText(paymentConfirmedLocator);
    }
}
