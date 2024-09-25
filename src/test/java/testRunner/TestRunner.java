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

package testRunner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


/**
 * **********************************************************************
 * Method Name: TestRunner
 *
 * Description:
 *     Running the selected tests with the required predefined conditions.
 *
 * Usage:
 *      To Run the selected scenarios
 *
 * **********************************************************************
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = {".//FeatureFiles/"}, // The Feature file(s) on the current run
        glue = {"stepDefinitions"}, // The file containing your steps
        plugin = {"pretty","html:reports/myreport.html","rerun:target/rerun.txt",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}, // External Plug-In(s)
        dryRun = false, // checks mapping between scenario steps and step definition methods
        monochrome = true, // to avoid junk characters in output
        publish = true // to publish a report in cucumber server
        // tags = "@Regression" // Groups or tags on the current run
)
public class TestRunner {
}
