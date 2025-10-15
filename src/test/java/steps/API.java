package steps;

import io.cucumber.java.en.Given;

public class API
{
    api.API api = new api.API();

    @Given("Tell me about {string}")
    public void tellMeAbout(String query)
    {
        api.getResult(query);
    }
}
