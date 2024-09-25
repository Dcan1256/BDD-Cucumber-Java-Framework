/**
 * **********************************************************************
 * Project Name: BDD-Framework_Selenium_Cucumber_Java
 * Author: Necdet Dogancan Yormaz
 * Date : 9/19/2024
 * Description: WebDriver Initialization locally or remotely based on the user preference,
 * tear down the initialized browser after the usage, and some other needed utilities
 *
 * Revision History:
 * Date | Author | Description
 * ----------------------------------------------------------------------
 * 9/17/2024 | Necdet Dogancan Yormaz | Created
 * [Date] | [Contributor] | [Update Description]
 *
 * **********************************************************************
 */
package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.LoginPage;
import pageObjects.MainPage;
import utilities.DataReader;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

/**
 * **********************************************************************
 * Method Name: LoginSteps
 *
 * Description:
 *     Contains code blocks for the corresponding action based on Scenarios
 *
 * Usage:
 *      Whenever you need to perform an action under your Scenarios
 *
 * **********************************************************************
 */

public class LoginSteps {
    WebDriver driver=Hooks.driver;
    static LoginPage lp;
    static MainPage mp;
    List<HashMap<String, String>> datamap; //Data driven

    @Given("The user is on SauceLabs login page")
    public void the_user_is_on_sauce_labs_login_page() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();

    }
    @When("The user enters valid credentials as username: {string}, password: {string}")
    public void the_user_enters_valid_credentials_as_username_password(String username, String password) {
        lp=new LoginPage(driver);
        lp.userNameBox.click();
        lp.userNameBox.sendKeys(username);
        lp.passwordBox.click();
        lp.passwordBox.sendKeys(password);

    }
    @When("The user clicks on the login button")
    public void the_user_clicks_on_the_login_button() {
        lp=new LoginPage(driver);
        lp.loginButton.click();
    }
    @Then("The user should be redirected on the main page")
    public void the_user_should_be_redirected_on_the_main_page() {
        mp=new MainPage(driver);
        Assert.assertEquals("Products",mp.mainPageTittle.getText());
    }
    @Then("The user should see the products page")
    public void the_user_should_see_the_products_page() {
        mp=new MainPage(driver);
        Assert.assertEquals("Products",mp.mainPageTittle.getText());
        driver.quit();
    }

    //******************** Data Driven Test **********************************
    @Then("the user should be redirected to the main page by passing username and password with excel row {string}")
    public void the_user_should_be_redirected_to_the_main_page_by_passing_username_and_password_with_excel_row(String rows) {
        datamap= DataReader.data(System.getProperty("user.dir")+"\\testData\\SauceLabs_LoginData.xlsx","Sheet1");
        int index=Integer.parseInt(rows)-1;
        String username=datamap.get(index).get("username");
        System.out.println(username);
        String password=datamap.get(index).get("password");
        System.out.println(password);
        String exp_res=datamap.get(index).get("res");

        lp=new LoginPage(driver);
        lp.userNameBox.click();
        lp.userNameBox.sendKeys(username);
        lp.passwordBox.click();
        lp.passwordBox.sendKeys(password);
        lp.loginButton.click();

        mp=new MainPage(driver);
        Assert.assertEquals("Products",mp.mainPageTittle.getText());
        driver.quit();

    }
}
