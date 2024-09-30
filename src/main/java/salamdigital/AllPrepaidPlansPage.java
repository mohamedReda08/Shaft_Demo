package salamdigital;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;

public class AllPrepaidPlansPage {
    //Declare WebDriver
    SHAFT.GUI.WebDriver driver;


    //Build Constructor
    public AllPrepaidPlansPage(SHAFT.GUI.WebDriver driver){
        this.driver = driver;
    }

    //Locators
    By soloPlansCardLocator = By.xpath("//div[text() = 'Solo Plans']");
    By flexPlansCardLocator = By.xpath("//div[text() = 'Flex Plans']");
    By extendedPlansCardLocator = By.xpath("//div[text() = 'Extended Validity Plans']");

    public String pageUrl(){
        driver.browser().capturePageSnapshot();
        return driver.browser().getCurrentURL();

    }
    public void clickOnPrepaidSoloPlansCard(){
        driver.element().click(soloPlansCardLocator);
    }
    public void clickOnFlexPlans(){
        driver.element().click(flexPlansCardLocator);
    }
    public void clickOnExtendedValidityPlans(){
        driver.element().click(extendedPlansCardLocator);
    }
}
