package parabank;

import com.shaft.driver.SHAFT;

import org.openqa.selenium.By;

public class RegisterPage {
    SHAFT.GUI.WebDriver driver;
    By firstNameLocator = By.xpath("//input[@id = 'customer.firstName']");
    By lastNameLocator = By.xpath("//input[@id = 'customer.lastName']");
    By addressLocator = By.xpath("//input[@id = 'customer.address.street']");
    By cityLocator = By.xpath("//input[@id = 'customer.address.city']");
    By stateLocator = By.xpath("//input[@id = 'customer.address.state']");
    By zipCodeLocator = By.xpath("//input[@id = 'customer.address.zipCode']");
    By phoneLocator = By.xpath("//input[@id = 'customer.phoneNumber']");
    By ssnLocator = By.xpath("//input[@id = 'customer.ssn']");
    By usernameLocator = By.xpath("//input[@id = 'customer.username']");
    By passwordLocator = By.xpath("//input[@id = 'customer.password']");
    By confirmPasswordLocator = By.xpath("//input[@id = 'repeatedPassword']");
    By registerButtonLocator = By.xpath("//input[@value = 'Register']");
    By registerSuccessHeaderLocator = By.id("//h1[@class = 'title']");

    public RegisterPage(SHAFT.GUI.WebDriver driver){
        this.driver = driver;
    }
    public String getRegisterPageTitle(){
        return driver.browser().getCurrentWindowTitle();
    }

    public void registerWithExistingUser(String firstName, String lastName, String address, String city,
                                         String state, String zipCode, String phone, String ssn, String userName, String password,
                                         String confirmPassword){
        driver.element().typeSecure(firstNameLocator,firstName);
        driver.element().typeSecure(lastNameLocator,lastName);
        driver.element().typeSecure(addressLocator,address);
        driver.element().typeSecure(cityLocator,city);
        driver.element().typeSecure(stateLocator,state);
        driver.element().typeSecure(zipCodeLocator, zipCode);
        driver.element().typeSecure(ssnLocator,ssn);
        driver.element().typeSecure(phoneLocator,phone);
        driver.element().typeSecure(usernameLocator,userName);
        driver.element().typeSecure(passwordLocator,password);
        driver.element().typeSecure(confirmPasswordLocator,confirmPassword);
        driver.element().click(registerButtonLocator);

    }
    public String registerSuccessText(){
        return driver.element().getText(registerSuccessHeaderLocator);
    }


}
