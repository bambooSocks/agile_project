package rcm;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class M2 {

    private LogisticsCompany logisticsCompany, logisticsCompany2;
    private Container container;
    private Client client, client2;
    private Journey journey;

    private Response response;

    @Given("the first logistics company {string} with address {string}, reference person {string} and email {string}")
    public void the_first_logistics_company_with_address_reference_person_and_email(String name, String address,
            String refPerson, String email) {
        logisticsCompany = new LogisticsCompany(name,address,refPerson,email);
    }

    @Given("the second logistic company {string} with address {string}, reference person {string} and email {string}")
    public void the_second_logistic_company_with_address_reference_person_and_email(String name, String address,
            String refPerson, String email) {
        logisticsCompany2 = new LogisticsCompany(name,address,refPerson,email);
    }

    @Given("the container of the first logistics company with ID {int}")
    public void the_container_of_the_first_logistics_company_with_ID(Integer id) {
        container = new Container(id, logisticsCompany);

    }

    @Given("the first client {string} with address {string}, reference person {string} and email {string}")
    public void the_first_client_with_address_reference_person_and_email(String name, String address, String refPerson,
            String email) {
        client = new Client(name,address,refPerson,email);

    }

    @Given("the second client {string} with address {string}, reference person {string} and email {string}")
    public void the_second_client_with_address_reference_person_and_email(String name, String address, String refPerson,
            String email) {
        client = new Client(name,address,refPerson,email);
    }

    @Given("the journey of given container and first client with origin port of {string}, destination port of {string} and a content of {string}")
    public void the_journey_of_given_container_and_first_client_with_origin_port_of_destination_port_of_and_a_content_of(
            String originPort, String destinationPort, String content) {
        journey = new Journey(originPort,destinationPort,content,container,client);
    }

    @When("the client requests to register the container")
    public void the_client_requests_to_register_the_container() {
        journey.setID();
    }

    @Then("an Id is created")
    public void an_Id_is_created() {
        assertTrue(((client.getContainer()).getJourney()).getID != null);
    }

    @Given("the container has a location {double} {double}")
    public void the_container_has_a_location(Double x, Double y) {
        // container.setLocation(x,y);

    }

    @When("the first logistics company updates containers location")
    public void the_first_logistics_company_updates_containers_location() {
        // response = container.updateLocation(logisticsCompany); updateLocation() method
        // contains
        // setLocation(x,y) where x and y are read from the GPS

    }
    
    @When("the second logistics company updates containers location")
    public void the_second_logistics_company_updates_containers_location() {
        // response = container.updateLocation(logisticsCompany2); updateLocation() method
        // contains
        // setLocation(x,y) where x and y are read from the GPS

    }

    @Then("the location is changed")
    public void the_location_is_changed() {
        // assertEquals("Location changed",response.getErrorMessage());

    }

    @Then("the location is not changed")
    public void the_location_is_not_changed() {
        // assertEquals("Location not changed",response.getErrorMessage());

    }

    @When("the first client filters his containers journeys based on the destination {string}")
    public void the_first_client_filters_his_containers_journeys_based_on_the_destination(String destination) {
        // response = client.filter(destination);

    }


    @Then("the clients containers journeys with the specific destination are listed")
    public void the_clients_containers_journeys_with_the_specific_destination_are_listed() {
        // assertEquals("Successful filtering",response.getErrorMessage());

    }


}
