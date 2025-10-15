package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import po.LexisNexisHomePage;
import utils.DriverManager;

import static org.junit.Assert.assertTrue;
import static utils.DriverManager.quit;

public class Common
{
    DriverManager driverManager;
    LexisNexisHomePage lexisNexisHomePage = new LexisNexisHomePage();

    @Given("I have opened a browser")
    public void iHaveOpenedABrowser()
    {
        driverManager = new DriverManager();
    }

    @When("I navigate to the Homepage")
    public void iNavigateToTheHomepage()
    {
        lexisNexisHomePage.go();
    }

    @Then("I verify that the Homepage has loaded")
    public void iVerifyThatTheHomepageHasLoaded()
    {
        assertTrue(lexisNexisHomePage.verify());
    }

    @And("I close the browser")
    public void iCloseTheBrowser()
    {
        quit();
    }
}
