package parabank;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;

public class Home {
    By registerLocator = By.xpath("//a[text() = 'Register']");
    SHAFT.GUI.WebDriver driver;
    String url;
    public Home(SHAFT.GUI.WebDriver driver){
        this.driver = driver;
    }

    public void navigate(){
        url = "https://parabank.parasoft.com/parabank/index.htm";
        driver.browser().navigateToURL(url);
    }
    public void clickOnRegister(){
        driver.element().click(registerLocator);
    }
    public boolean registerIsDisplayed(){
        return driver.element().isElementDisplayed(registerLocator);
    }
    public boolean registerIsClickable(){
        return driver.element().isElementClickable(registerLocator);
    }


}
