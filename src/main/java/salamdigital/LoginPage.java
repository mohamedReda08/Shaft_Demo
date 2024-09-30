package salamdigital;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;

public class LoginPage {
    SHAFT.GUI.WebDriver driver;
    OnboardingPage onboarding;

    public LoginPage (SHAFT.GUI.WebDriver driver){this.driver = driver;}

    By mobileNumberLocator = By.xpath("//input[@name='emailPhone']");
    By continueBtnLocator = By.xpath("//button[text()='Continue']");
    By passwordLocator = By.xpath("//input");
    By loginBtnLocator = By.xpath("//button[text()='Login']");
    By otpPageLocator = By.xpath("//div[text()='Enter the 4-digit code']");
    By otpFirstDigit = By.xpath("//input[@id = 'digit1']");
    By otpSecondDigit = By.xpath("//input[@id = 'digit2']");
    By otpThirdDigit = By.xpath("//input[@id = 'digit3']");
    By otpFourthDigit = By.xpath("//input[@id = 'digit4']");
    By otpSubmitLocator = By.xpath("//button[text() ='Submit']");
    By passwordStepLocator = By.xpath("//div[text() = 'Enter password to Login.']");


    public void quickLogin(String msisdn) {
        driver.element().type(mobileNumberLocator, msisdn);
        driver.element().click(continueBtnLocator);
        enterOTP();
    }
    public void registeredUserLogin(String msisdn){

        driver.waitUntil(
                d-> {
                    driver.element().type(mobileNumberLocator, msisdn);
                    driver.element().click(continueBtnLocator);
                    enterPassword();
                    enterOTP();
                    return true;
                });
    }

    private void enterPassword(){
        driver.element().type(passwordLocator,"Temp@123");
        driver.element().click(loginBtnLocator);
    }

    private void enterOTP(){
        driver.element().type(otpFirstDigit,"7");
        driver.element().type(otpSecondDigit,"4");
        driver.element().type(otpThirdDigit,"2");
        driver.element().type(otpFourthDigit,"1");
        driver.element().click(otpSubmitLocator);
    }

}

