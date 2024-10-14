package salamdigital;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class TapPaymentPage extends Page{
    public TapPaymentPage(SHAFT.GUI.WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    By creditCardNumberLocator = By.xpath("//input[@id ='card-number']");
    By cardExpiryLocator = By.xpath("//input[@id ='expiration-date']");
    By cardHolderLocator = By.xpath("//input[@id ='card-holder']");
    By cvvLocator = By.xpath("//input[@id ='cvv']");
    By payNowBtnLocator = By.xpath("//button[@id ='tap-btn']");


    By cardFrameLocator = By.xpath("//iframe[@id = 'myFrame']");
    By submitIFrameLocator = By.xpath("//iframe[@id ='challengeFrame']");
    By paymentSubmitLocator = By.xpath("//input[@id='acssubmit']");

    @Step("Enter Credit Card Parameters")
    public SubmitPayment enterTapPaymentCard(String cardNumber){

        driver.waitUntil(d->{
            driver.element().switchToIframe(cardFrameLocator);
            driver.element().type(creditCardNumberLocator,cardNumber);
            driver.element().type(cardExpiryLocator,"1229");
            driver.element().type(cvvLocator,"123");
            driver.element().type(cardHolderLocator,"Test");

            driver.element().switchToDefaultContent();
            driver.element().click(payNowBtnLocator);
            return true;
        });
        return new SubmitPayment(driver);
    }

}
