package salamdigital;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class LandingPage extends Page {
    //define webDriver
    SHAFT.GUI.WebDriver driver;
    String url = "https://staging-my.salammobile.sa/en/home";
    // declare locators
    final private By prepaidPlansLocator = By.xpath("//div[text() = 'Prepaid Plans']");
    final private By postpaidPlansLocator = By.xpath("//div[text() = 'Postpaid Plans']");
    final private By activateSIMLocator = By.xpath("//img[@alt ='Activate SIM card']");
    final private By signInLocator = By.xpath("//span[text() = 'Sign In']");

    //build constructor
    public LandingPage(SHAFT.GUI.WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    //public methode

    @Step("navigate to landing page")
    public LandingPage navigate(){
        driver.browser().navigateToURL(url);
        return this;
    }


    @Step("When click on prepaid plans, user navigated to onboarding page")
    public AllPrepaidPlansPage clickOnPrepaidPlans(){
        driver.element().click(prepaidPlansLocator);
        return new AllPrepaidPlansPage(driver);
    }

    @Step("Click On Activate SIM Button")
    public void clickOnActivateYourSIM(){driver.element().click(activateSIMLocator);}

    @Step("Click On Sign In Button")
    public void clickOnSignIn(){driver.element().click(signInLocator);}
}
