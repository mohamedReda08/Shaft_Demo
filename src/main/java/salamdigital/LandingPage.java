package salamdigital;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;

public class LandingPage {
    //define webDriver
    SHAFT.GUI.WebDriver driver;
    String homeUrl = "https://staging-my.salammobile.sa/en/home";
    // declare locators
    final private By prepaidPlansLocator = By.xpath("//div[text() = 'Prepaid Plans']");
    final private By postpaidPlansLocator = By.xpath("//div[text() = 'Postpaid Plans']");
    final private By activateSIMLocator = By.xpath("//img[@alt ='Activate SIM card']");
    final private By signInLocator = By.xpath("//span[text() = 'Sign In']");



    //build constructor
    public LandingPage(SHAFT.GUI.WebDriver driver){
        this.driver = driver;
    }

    //public methode

    public void navigateToHomePage(){driver.browser().navigateToURL(homeUrl);}

    public void clickOnPrepaidPlans(){driver.element().click(prepaidPlansLocator);}

    public void clickOnActivateYourSIM(){driver.element().click(activateSIMLocator);}

    public void clickOnSignIn(){driver.element().click(signInLocator);}
}
