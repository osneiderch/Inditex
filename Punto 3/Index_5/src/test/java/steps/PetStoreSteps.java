package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.Pet;
import models.PetCounter;

public class PetStoreSteps {

    private List<Pet> pets;
    private Response response;

    @Given("I retrieve pets with status {string}")
    public void i_retrieve_pets_with_status(String status) {
        response = RestAssured.given()
                .queryParam("status", status)
                .get("https://petstore.swagger.io/v2/pet/findByStatus");
        assertEquals(200, response.getStatusCode());
        ObjectMapper mapper = new ObjectMapper();
        try {
            pets = mapper.readValue(response.getBody().asString(), new TypeReference<List<Pet>>(){});
        } catch (Exception e) {
            e.printStackTrace();
            fail("Failed to parse response");
        }
    }

    @Then("I should see the count of sold pets by name")
    public void i_should_see_the_count_of_sold_pets_by_name() {
        PetCounter petCounter = new PetCounter(pets);
        Map<String, Long> petCount = petCounter.countPetsByName();
        System.out.println(petCount);
    }
}