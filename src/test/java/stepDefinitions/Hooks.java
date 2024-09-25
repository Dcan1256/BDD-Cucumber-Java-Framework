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

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


/**
 * **********************************************************************
 * Method Name: Hooks
 *
 * Description:
 *     Application of PageFactory Model to all PageClasses under the project, this will
 *     make WebElement initialization cleaner at PageClasses.
 *
 * Usage:
 *      Application of PageFactory Model to the all PageClasses under the project.
 *
 * **********************************************************************
 */
public class Hooks {

	 static WebDriver driver;
	 Properties p;

    /**
     * Initiate the driver
     * @throws IOException
     */
	@Before
    public void setup() throws IOException
    {
    	driver=BaseClass.initilizeBrowser();

    	p=BaseClass.getProperties();
    	driver.get(p.getProperty("appURL"));
    	driver.manage().window().maximize();
	}

    /**
     * Close the driver
     * @param scenario
     */
    @After
    public void tearDown(Scenario scenario) {
       driver.quit();
    }

    @AfterStep
    public void addScreenshot(Scenario scenario) {
        
    	// this is for cucumber junit report
        if(scenario.isFailed()) {
        	
        	TakesScreenshot ts=(TakesScreenshot) driver;
        	byte[] screenshot=ts.getScreenshotAs(OutputType.BYTES);
        	scenario.attach(screenshot, "image/png",scenario.getName());
        }
    }
}
