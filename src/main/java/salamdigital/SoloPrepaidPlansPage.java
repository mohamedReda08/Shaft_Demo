package salamdigital;

import com.shaft.driver.SHAFT;
import com.shaft.enums.internal.Screenshots;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class SoloPrepaidPlansPage {
    SHAFT.GUI.WebDriver driver;

    By solo199Locator = By.xpath("//div[text() = 'Solo 199']");
    By solo149Locator = By.xpath("//div[text() = 'SOLO 149 Prepaid']");
    By detailsCard = By.xpath("//div[@class = 'style_boxDetailsWrapper__24374  ']");
    By priceLocator = By.xpath("//div[@class = 'style_column1__139kw']/child::div[2]");
    By subscribeLocator = By.xpath("//button[text() = 'Subscribe now!']");

    public SoloPrepaidPlansPage(SHAFT.GUI.WebDriver driver){
        this.driver = driver;
    }

    @Step("Select Solo 149 Prepaid Plan")
    public void selectSolo149Plan(){
        driver.element().click(solo149Locator);
    }

    @Step("Select Solo 199 Prepaid Plan")
    public SoloPrepaidPlansPage selectSolo199Plan(){
        driver.element().click(solo199Locator);
        detailsCardIsDisplayed();
        priceOfThePlan();
        return this;
    }


    @Step("Subscribe to Selected Plan")
    public OnboardingPage subscribeToSelectedPlan(){
        driver.element().click(subscribeLocator);
        return new OnboardingPage(driver);
    }

    private void detailsCardIsDisplayed(){
         driver.element().isElementDisplayed(detailsCard);
    }

    private void priceOfThePlan(){
       driver.browser().captureScreenshot(Screenshots.ELEMENT);
       driver.element().getText(priceLocator);

    }


    //TODO to be refactor with selectSoloPlan method
    private By locatePlan(String xpath){
        return By.xpath("//div[text() = '"+xpath+"']");
    }
}
