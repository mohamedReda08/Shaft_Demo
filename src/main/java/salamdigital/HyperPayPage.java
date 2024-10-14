package salamdigital;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;

public class HyperPayPage extends Page{
    public HyperPayPage (SHAFT.GUI.WebDriver driver){
        super(driver);
        this.driver = driver;
    }
    By cardNumIframeLocator = By.xpath("//iframe[@name='card.number']");
    By cardNumLocator = By.xpath("//input[@name = 'card.number']");
    By expiryDateLocator = By.xpath("//input[contains(@class, 'wpwl-control-expiry')]");
    By cardHolderLocator = By.xpath("//input[@name = 'card.holder']");
    By cardCvvIframeLocator = By.xpath("//iframe[@name = 'card.cvv']");
    By cardCvv = By.xpath("//form/input[@name = 'card.cvv']");
    By payNowBtnLocator = By.xpath("//button[contains(@class , 'wpwl-button-pay')]");


    public SubmitPayment enterHyperPayCardDetails(String cardNum){
        driver.element().switchToIframe(cardNumIframeLocator);
        driver.element().typeSecure(cardNumLocator,cardNum);
        driver.element().switchToDefaultContent();

        driver.element().type(expiryDateLocator, "1229");

        driver.element().type(cardHolderLocator,"Test");

        driver.element().switchToIframe(cardCvvIframeLocator);
        driver.element().type(cardCvv,"123");
        driver.element().switchToDefaultContent();

        driver.element().click(payNowBtnLocator);
        return new SubmitPayment(driver);
    }
}
