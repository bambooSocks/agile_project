package rcm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class M2 {

    private List<Journey> filteredContent, filteredDestination, filteredOrigin;
    private Response response;
    private SharedObjectHolder holder;

    public M2(SharedObjectHolder holder) {
        this.holder = holder;
    }

    @Given("the first client is a client of the the first logistics company")
    public void the_first_client_is_a_client_of_the_the_first_logistics_company() {
        holder.getFirstCompany().addClient(holder.getFirstClient());
    }

    @Given("the container has a location {double} {double}")
    public void the_container_has_a_location(Double x, Double y) {
        holder.getContainer().setLocation(x, y);
    }

    @When("the first client requests to register the container for the journey with the first logistics company with origin port of {string}, destination port of {string} and a content of {string}")
    public void the_first_client_requests_to_register_the_container_for_the_journey_with_the_first_logistics_company_with_origin_port_of_destination_port_of_and_a_content_of(
            String originPort, String destinationPort, String content) {
        // veeeeeeeeeeeeery long name :D
        response = holder.getFirstClient().requestJourney(originPort, destinationPort, content,
                holder.getFirstCompany());
    }

    @When("the first logistics company updates containers location")
    public void the_first_logistics_company_updates_containers_location() {
        // update to what?
        response = holder.getFirstCompany().updateLocation(holder.getContainer());
    }

    @When("the second logistics company updates containers location")
    public void the_second_logistics_company_updates_containers_location() {
        // same here ... what are you even updating?
        response = holder.getSecondCompany().updateLocation(holder.getContainer());
    }

    @When("the first client filters his containers journeys based on the origin port {string}")
    public void the_first_client_filters_his_containers_journeys_based_on_the_origin_port(String origin) {
        // minor suggestion : containers journeys -> container journeys or just journeys
        filteredOrigin = holder.getFirstClient().searchByOrigin(origin);
    }

    @When("the first client filters his containers journeys based on the destination {string}")
    public void the_first_client_filters_his_containers_journeys_based_on_the_destination(String destination) {
        // same here
        filteredDestination = holder.getFirstClient().searchByDestination(destination);
    }

    @When("the first client filters his containers journeys based on the content {string}")
    public void the_first_client_filters_his_containers_journeys_based_on_the_content(String content) {
        // and here
        filteredContent = holder.getFirstClient().searchByContent(content);
    }

    @Then("the location is changed")
    public void the_location_is_changed() {
        // maybe check for expected value?
        assertEquals(Response.SUCCESS, response);
    }

    @Then("the location is not changed")
    public void the_location_is_not_changed() {
        assertEquals(Response.LOCATION_NOT_CHANGED, response);
    }

    @Then("an id is created")
    public void an_id_is_created() {
        assertEquals(Response.SUCCESS, response);
    }

    @Then("the first journey is listed")
    public void the_first_journey_is_listed() {
        assertTrue(filteredDestination.contains(holder.getFirstJourney()));
        assertTrue(filteredDestination.size() == 1);
    }

    @Then("the second journey is listed")
    public void the_second_journey_is_listed() {
        assertTrue(filteredOrigin.contains(holder.getSecondJourney()));
        assertTrue(filteredOrigin.size() == 1);
    }

    @Then("both journeys are listed")
    public void both_journeys_are_listed() {
        // maybe check for the actual journeys
        assertTrue(filteredContent.size() == 2);
    }

    @Then("the journey doesnt exist")
    public void the_journey_doesnt_exist() {
        assertEquals(Response.JOURNEY_NOT_CREATED, response);
    }

}
