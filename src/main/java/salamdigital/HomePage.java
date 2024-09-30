package salamdigital;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;

public class HomePage {
    SHAFT.GUI.WebDriver driver;

    By changePlanLocator = By.xpath("//span[text() = 'Change Plan']");
    By closeRegisterPopUpLocator = By.xpath("(//div[contains(@class , 'closeIcon')]/img)[1]");


    public HomePage (SHAFT.GUI.WebDriver driver){
        this.driver = driver;
    }

    public void clickOnChangePlan(){
        driver.element().click(closeRegisterPopUpLocator);
        driver.element().click(changePlanLocator);
    }

}
