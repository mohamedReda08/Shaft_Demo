package salamdigital;

import com.shaft.driver.SHAFT;
import com.shaft.enums.internal.Screenshots;
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

    //TODO to be refactor
    public void selectSolo149Plan(){
        driver.element().click(solo149Locator);
    }

    public void selectSolo199Plan(){
        driver.element().click(solo199Locator);
    }


    public boolean detailsCardIsDisplayed(){
        return driver.element().isElementDisplayed(detailsCard);
    }

    public String priceOfThePlan(){
       driver.browser().captureScreenshot(Screenshots.ELEMENT);
        return driver.element().getText(priceLocator);

    }
    public void subscribeToSelectedPlan(){
        driver.element().click(subscribeLocator);
    }

    //TODO to be refactor with selectSoloPlan method
    private By locatePlan(String xpath){
        return By.xpath("//div[text() = '"+xpath+"']");
    }
}
