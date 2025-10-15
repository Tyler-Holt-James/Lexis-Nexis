package steps;

import dto.Input;
import io.cucumber.java.DataTableType;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import po.LexisNexisHomePage;
import java.util.Map;

import static org.junit.Assert.assertTrue;


public class LexisNexis
{
    LexisNexisHomePage lexisNexisHomePage = new LexisNexisHomePage();

    @DataTableType
    public Input decode(Map<String, String> row)
    {
        return Input.decode(row);
    }

    @Given("I pass the following form details:")
    public void iPassTheFollowingFormDetails(Input input)
    {
        lexisNexisHomePage.enterDetails(input);
    }

    @When("I click the other available form details")
    public void iClickTheOtherAvailableFormDetails()
    {
        lexisNexisHomePage.clickableDetails();
    }

    @And("I click the {string} link and collect the amount of html links that appear")
    public void iClickTheLinkAndCollectTheAmountOfHtmlLinksThatAppear(String link)
    {
        lexisNexisHomePage.collectHtmlLinks(link);
    }

    @Then("I submit the form")
    public void iSubmitTheForm()
    {
        lexisNexisHomePage.submitForm();
    }

    @And("I verify that the form has been submitted")
    public void iVerifyThatTheFormHasBeenSubmitted()
    {
        assertTrue(lexisNexisHomePage.verifySubmission());
    }
}










