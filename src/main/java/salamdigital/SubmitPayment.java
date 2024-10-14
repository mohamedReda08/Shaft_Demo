package salamdigital;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class SubmitPayment extends Page{

    By paymentIframeLocator = By.xpath("//iframe[@id = 'challengeFrame']");
    By submitLocator = By.xpath("//input[@id = 'acssubmit']");

    By hyperPayCardFrame = By.xpath("//iframe[@class = 'wpwl-target']");
    By hyperPaySubmitLocator = By.xpath("//input[@name = 'commit']");

    public SubmitPayment(SHAFT.GUI.WebDriver driver){
        super(driver);
        this.driver = driver;
    }
@Step("Submit Tap Payment")
    public void submitValidPayment(){

        driver.element().click(submitLocator);
        driver.element().switchToDefaultContent();
    }

    @Step("Submit HyperPay payment")
    public PurchaseCompletedPage submitValidHyperPayment(){
        driver.element().switchToIframe(hyperPayCardFrame);
        driver.element().click(hyperPaySubmitLocator);
        return new PurchaseCompletedPage(driver);
    }


}
