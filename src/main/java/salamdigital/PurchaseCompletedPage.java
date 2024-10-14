package salamdigital;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;


public class PurchaseCompletedPage extends Page{

    By paymentConfirmedTitleLocator = By.xpath("//div[contains(@class, 'style_mainTitle__24Rv5')]");
    By paymentConfirmedSubLocator = By.xpath("//div[contains(@class, 'paymentConfirmedSubTitle')]");
    By goToHomeBtnLocator = By.xpath("//button[contains(@class, 'continueBtn')]");

    public PurchaseCompletedPage(SHAFT.GUI.WebDriver driver){
        super(driver);
        this.driver = driver;
    }
@Step("Verify that Purchasing is completed")
    public PurchaseCompletedPage getPaymentTitle(){
        driver.element().switchToDefaultContent();
       driver.element().assertThat(paymentConfirmedTitleLocator).text().toString()
               .equalsIgnoreCase("Payment Confirmed");
        return this;
    }

    public PurchaseCompletedPage getSubTitle(){
         driver.element().assertThat(paymentConfirmedSubLocator)
                .text().toString()
                .equalsIgnoreCase("Your Salam Mobile SIM will be delivered to the selected location");
         return this;
    }

    public LandingPage returnToLandingPage(){
        driver.element().click(goToHomeBtnLocator);
        return new LandingPage(driver);
    }

}
