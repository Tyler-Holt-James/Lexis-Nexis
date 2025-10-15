package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static utils.Print.print;

public class API
{
    public void getResult(String query)
    {
        Response response = apiCaller("https://api.dictionaryapi.dev/api/v2/entries/en/" + query);
        String word = response.jsonPath().getString("[0].word");
        String definition = response.jsonPath().getString("[0].meanings[0].definitions[0].definition");
        assertNotNull(word);
        assertNotNull(definition);
        print("Word: " + word);
        print("Definition: " + definition);
    }

    private Response apiCaller(String URL)
    {
        // Set the base URI for the RESTful web service
        RestAssured.baseURI = URL;

        // Create a request specification
        RequestSpecification httpRequest = RestAssured.given();

        // Send a GET request and store the response
        Response response = httpRequest.get();
        //this assertion checks that the api has worked
        assertEquals(200, response.getStatusCode());
        return response;
    }
}
