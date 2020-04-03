package rcm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class M2 {

    private LogisticsCompany logisticsCompany, logisticsCompany2;
    private Container container;
    private Client client, client2;
    private Journey journey, journey2;
    private LinkedList<Journey> filteredContent, filteredDestination, filteredOrigin;

    private Response response;

    @Given("the first logistics company {string} with address {string}, reference person {string} and email {string}")
    public void the_first_logistics_company_with_address_reference_person_and_email(String name, String address,
            String refPerson, String email) {
        logisticsCompany = new LogisticsCompany(name, address, refPerson, email);
    }


    @Given("the second logistics company {string} with address {string}, reference person {string} and email {string}")
    public void the_second_logistics_company_with_address_reference_person_and_email(String name, String address,
            String refPerson, String email) {
        logisticsCompany2 = new LogisticsCompany(name, address, refPerson, email);
    }

    @Given("the container of the first logistics company")
    public void the_container_of_the_first_logistics_company() {
        container = new Container(logisticsCompany);

    }

    @Given("the first client {string} with address {string}, reference person {string} and email {string}")
    public void the_first_client_with_address_reference_person_and_email(String name, String address, String refPerson,
            String email) {
        client = new Client(name, address, refPerson, email);

    }

    @Given("the second client {string} with address {string}, reference person {string} and email {string}")
    public void the_second_client_with_address_reference_person_and_email(String name, String address, String refPerson,
            String email) {
        client2 = new Client(name, address, refPerson, email);
    }

    @Given("the container has a location {double} {double}")
    public void the_container_has_a_location(Double x, Double y) {
        container.setLocation(x, y);

    }
    @Given("the first journey with origin port of {string}, destination port of {string} and a content of {string}")
    public void the_first_journey_with_origin_port_of_destination_port_of_and_a_content_of(String originPort, String destinationPort, String content) {
        journey = new Journey(originPort, destinationPort, content, container, client);
    }
    @Given("the second journey with origin port of {string}, destination port of {string} and a content of {string}")
    public void the_second_journey_with_origin_port_of_destination_port_of_and_a_content_of( String originPort, String destinationPort, String content) {
        journey2 = new Journey(originPort, destinationPort, content, container, client);
    }


    @When("the first client filters his containers journeys based on the origin port {string}")
    public void the_first_client_filters_his_containers_journeys_based_on_the_origin_port(String origin) {
        filteredOrigin = client.searchByOrigin(origin);
    }

    @When("the first client requests to register the container for the journey with the first logistics company with origin port of {string}, destination port of {string} and a content of {string}")
    public void the_first_client_requests_to_register_the_container_for_the_journey_with_the_first_logistics_company_with_origin_port_of_destination_port_of_and_a_content_of(
            String originPort, String destinationPort, String content) {
        response = client.requestJourney(originPort,destinationPort,content,logisticsCompany);
    }


    @When("the first logistics company updates containers location")
    public void the_first_logistics_company_updates_containers_location() {
        response = logisticsCompany.updateLocation(container);

    }

    @When("the second logistics company updates containers location")
    public void the_second_logistics_company_updates_containers_location() {
        response = logisticsCompany2.updateLocation(container);

    }

    @When("the first client filters his containers journeys based on the destination {string}")
    public void the_first_client_filters_his_containers_journeys_based_on_the_destination(String destination) {
        filteredDestination = client.searchByDestination(destination);

    }

    @When("the first client filters his containers journeys based on the content {string}")
    public void the_first_client_filters_his_containers_journeys_based_on_the_content(String content) {
        filteredContent = client.searchByContent(content);
    }


    @Then("the location is changed")
    public void the_location_is_changed() {
        assertEquals(Response.SUCCESS, response);

    }

    @Then("the location is not changed")
    public void the_location_is_not_changed() {
        assertEquals(Response.LOCATION_NOT_CHANGED, response);

    }

    @Then("an Id is created")
    public void an_Id_is_created() {
        assertEquals(Response.SUCCESS,response);
    }
    @Then("the first journey is listed")
    public void the_first_journey_is_listed() {
        assertTrue(filteredDestination.contains(journey));
        assertTrue(filteredDestination.size()==1);
    }
    @Then("the second journey is listed")
    public void the_second_journey_is_listed() {
        assertTrue(filteredOrigin.contains(journey2));
        assertTrue(filteredOrigin.size()==1);
    }
    @Then("both journeys are listed")
    public void both_journeys_are_listed() {
        assertTrue(filteredContent.size()==2);
    }
    @Then("the journey doesnt exist")
    public void the_journey_doesnt_exist() {
        assertEquals(Response.JOURNEY_NOT_CREATED, response);
    }

}
