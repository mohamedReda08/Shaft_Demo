package TestPackage;

import com.shaft.driver.DriverFactory;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import parabank.Home;
import parabank.RegisterPage;

import java.time.Duration;

public class TestRegister {
    SHAFT.GUI.WebDriver driver;
    Home home;
    RegisterPage register;
    Wait wait;
    SHAFT.TestData.JSON testData;

@BeforeClass
public void beforeClass(){
    testData = new SHAFT.TestData.JSON("simpleJSON.json");

}
    @BeforeMethod
    public void setup(){
        driver = new SHAFT.GUI.WebDriver(DriverFactory.DriverType.CHROME);
        home = new Home(driver);
        register = new RegisterPage(driver);
        home.navigate();
        wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(TimeoutException.class)
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test(description = "Verify that register button is displayed in homepage" )
    public void testRegisterButtonIsDisplayed(){
        Assert.assertTrue(home.registerIsDisplayed());
    }

    @Test(description = "Verify that register button is clickable", dependsOnMethods = "testRegisterButtonIsDisplayed")
    public void testRegisterButtonISClickable(){
        Assert.assertTrue(home.registerIsClickable());
    }

    @Test(description = "Verify that user will be redirected to Registration page after click on register button",
            dependsOnMethods = {"testRegisterButtonIsDisplayed","testRegisterButtonISClickable"} )
    public void testClickOnRegister(){
        home.clickOnRegister();
        wait.until(d->{
            String title = register.getRegisterPageTitle();
            Assert.assertTrue(title.contains("Register"));
            return true;
        });

    }
    @Story("Registration Story")
    @Step("Test Registration Happy Scenario")
    @Test(dependsOnMethods = "testClickOnRegister")
    public void testRegister(){

    String expectedHeader = "Welcome "+ testData.getTestData("username");


    home.clickOnRegister();
        String firstName = testData.getTestData("firstName");
        String lastName  = testData.getTestData("lastName");
        String address = testData.getTestData("address");
        String city = testData.getTestData("city");
        String state = testData.getTestData("state");
        String zipCode = testData.getTestData("zipCode");
        String phone = testData.getTestData("phone");
        String ssn = testData.getTestData("SSN");
        String userName = testData.getTestData("username");
        String password = testData.getTestData("password");
        String confirm = testData.getTestData("confirm");

    wait.until(d->{


        register.registerWithExistingUser(firstName,lastName, address, city, state, zipCode,
                                            phone, ssn,userName,password,confirm);
           Assert.assertEquals(register.registerSuccessText(),expectedHeader);
           return true;
       });
    }

}
