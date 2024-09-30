package salamdigital;

import com.shaft.driver.SHAFT;
import com.shaft.enums.internal.Screenshots;
import org.openqa.selenium.By;

import java.time.Duration;

public class ActivateSIMPage {
//    Driver declaration;
    SHAFT.GUI.WebDriver driver;


//    Locators
    private final By pageContextLocator = By.xpath("//div[contains(@class , 'subTitle')]");
    private final By idNumberLocator = By.xpath("//input[@name='idNumber']");
    private final By mobileNumberLocator = By.xpath("//input[@name='mobileNumber']");
    private final By emailLocator = By.xpath("//input[@name='email']");
    private final By continueBtnLocator = By.xpath("//button[text()='Continue']");

    //    Order Locators
    private final By firstOrderMobileNumLocator = By.xpath("(//div[contains(@class,'simValue')])[1]");
    private final  By ordersTextLocator = By.xpath("//div[@class='style_ordersIconTitleWrapper__3Ksj9']");
    private final By firstOrderLocator = By.xpath("(//div[text() = 'Activate'])[1]");
    //    Password Step Locators
    private final By pageTitleLocator = By.xpath("(//div[contains(@class,'title')])[2]");
    private final By passwordLocator = By.xpath("//input[@type = 'password']");
    private final By confirmBtnLocator = By.xpath("//button[text() = 'Confirm']");

//    OTP Locators
    private final By otpFirstDigit = By.xpath("//input[@id = 'digit1']");
    private final By otpSecondDigit = By.xpath("//input[@id = 'digit2']");
    private final By otpThirdDigit = By.xpath("//input[@id = 'digit3']");
    private final By otpFourthDigit = By.xpath("//input[@id = 'digit4']");
    By submitLocator = By.xpath("//button[contains(@class, 'submitBtn')]");

//  ICCID Locators
    private final By iccidLocator = By.xpath("//input[contains(@class,'textBoxICCIDNumber')]");
    private final By iccidContinueBtnLocator = By.xpath("//button[text() = 'Continue']");

//  Nafath buttons locator
    private final By nafathBtnLocator = By.xpath("//span[text() = 'Check my request status']");

//    Constructor Implementation
    public ActivateSIMPage(SHAFT.GUI.WebDriver driver){
        this.driver = driver;
    }
//Public Methods

    public void enterOrderDetails(String id, String mobile, String email){
        driver.element().type(idNumberLocator, id);
        driver.element().type(mobileNumberLocator, mobile);
        driver.element().type(emailLocator,email);
        driver.element().click(continueBtnLocator);
    }

    public void selectFirstOrder(){
        driver.element().click(firstOrderLocator);

    }

    private String testOrderMobile(){
        return driver.element().getText(firstOrderMobileNumLocator);
    }

    public String pageContext(){
        driver.browser().captureScreenshot(Screenshots.FULL);
        return driver.element().getText(pageContextLocator);
    }

    public String getOrdersPageText(){
        return driver.element().getText(ordersTextLocator);
    }

    public void enterPassword(String password){
        driver.element().type(passwordLocator,password);
        driver.element().click(confirmBtnLocator);
    }

    public void enterOTP(String otp1, String otp2, String otp3, String otp4){
        driver.element().typeSecure(otpFirstDigit,otp1);
        driver.element().typeSecure(otpSecondDigit, otp2);
        driver.element().typeSecure(otpThirdDigit, otp3);
        driver.element().typeSecure(otpFourthDigit, otp4);
        driver.waitUntil(driver1 -> {
            driver.element().isElementClickable(submitLocator);
            driver.element().click(submitLocator);
            return true;
        });

    }

    public void enterICCID(String iccid){
        driver.element().type(iccidLocator,iccid);
        driver.element().click(iccidContinueBtnLocator);
    }

    public void checkNafath(){
        try {
            Thread.sleep(Duration.ofSeconds(180));
            driver.element().click(nafathBtnLocator);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
