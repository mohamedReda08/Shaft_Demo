package salamdigital;

import com.shaft.driver.SHAFT;
import com.shaft.enums.internal.Screenshots;
import org.openqa.selenium.By;

public class ActivationTypePage {
    SHAFT.GUI.WebDriver driver;

//    Constructor
    public ActivationTypePage(SHAFT.GUI.WebDriver driver){
        this.driver = driver;
    }

//    Locators
    private final By orderedSIMDeliveredBtn = By.xpath("//div[@id = 'option-0']");
    private final By SimPurchasedFromStoreBtn = By.xpath("//div[@id = 'option-1']");
    private final By pageName = By.xpath("//div[@id = 'replaceSimTitle']");
//    public methods implementation

    public void clickOnOrderedOnlineBtn(){
        driver.element().click(orderedSIMDeliveredBtn);
    }

//    Assertion methods Implementation
    public String pageName(){
        driver.browser().captureScreenshot(Screenshots.ELEMENT);
        return driver.element().getText(pageName);
    }
}
