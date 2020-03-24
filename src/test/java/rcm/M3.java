package rcm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class M3 {

    private Container container;
    private Journey journey;
    private LogisticsCompany company1, company2;
    private Client client1, client2;
    private ContainerStatus status;

    private boolean successfulEntry = false;
    private LinkedList<Client> statusList;

    @Given("a first logistic company {string} with address {string}, reference person {string} and email {string}")
    public void a_first_logistic_company_with_address_reference_person_and_email(String name, String address,
            String refPerson, String email) {
        company1 = new LogisticsCompany(name, address, refPerson, email);
    }

    @Given("a second logistic company {string} with address {string}, reference person {string} and email {string}")
    public void a_second_logistic_company_with_address_reference_person_and_email(String name, String address,
            String refPerson, String email) {
        company2 = new LogisticsCompany(name, address, refPerson, email);
    }

    @Given("a first client {string} with address {string}, reference person {string} and email {string}")
    public void a_first_client_with_address_reference_person_and_email(String name, String address, String refPerson,
            String email) {
        client1 = new Client(name, address, refPerson, email);
    }

    @Given("a second client {string} with address {string}, reference person {string} and email {string}")
    public void a_second_client_with_address_reference_person_and_email(String name, String address, String refPerson,
            String email) {
        client2 = new Client(name, address, refPerson, email);
    }

    @Given("a container of first logistic company with ID {int}")
    public void a_container_of_first_logistic_company_with_ID(Integer id) {
        container = new Container(id, company1);
    }

    @Given("a journey of given container and first client with origin port of {string}, destination port of {string} and a content of {string}")
    public void a_journey_of_given_container_and_first_client_with_origin_port_of_destination_port_of_and_a_content_of(
            String originPort, String destinationPort, String content) {
        journey = new Journey(originPort, destinationPort, content, container, client1);
    }

    @Given("a journey of no container and first client with origin port of {string}, destination port of {string} and a content of {string}")
    public void a_journey_of_no_container_and_first_client_with_origin_port_of_destination_port_of_and_a_content_of(
            String originPort, String destinationPort, String content) {
        journey = new Journey(originPort, destinationPort, content, null, client1);
    }

    @Given("a container status of {double} degrees, {double} % humidity and {double} bar to the given journey")
    public void a_container_status_of_degrees_humidity_and_bar_to_the_given_journey(Double temperature, Double humidity,
            Double airPressure) {
        status = new ContainerStatus(temperature, humidity, airPressure);
    }

    @Given("an initial container status in the journey of {double} degrees, {double} % humidity and {double} bar")
    public void an_initial_container_status_in_the_journey_of_degrees_humidity_and_bar(Double temperature,
            Double humidity, Double airPressure) {
        status = new ContainerStatus(temperature, humidity, airPressure);
        LogisticsCompany company = journey.getCompany();
        successfulEntry = company.enterStatus(journey, status);
    }

    @When("the first logistic company enters the given container status")
    public void the_first_logistic_company_enters_the_given_container_status() {
        successfulEntry = company1.enterStatus(journey, status);
    }

    @When("the second logistic company enters the given container status")
    public void the_second_logistic_company_enters_the_given_container_status() {
        successfulEntry = company2.enterStatus(journey, status);
    }

    @When("the first client requests access to the status")
    public void the_first_client_requests_access_to_the_status() {
        statusList = client1.requestStatus();
    }

    @When("the second client requests access to the status")
    public void the_second_client_requests_access_to_the_status() {
        statusList = client2.requestStatus();
    }

    @Then("the journey contains the given status")
    public void the_journey_contains_the_given_status() {
        assertTrue(journey.contains(status));
    }

    @Then("the journey does not contain the given status")
    public void the_journey_does_not_contain_the_given_status() {
        assertFalse(journey.contains(status));
    }

    @Then("the journey is successfully updated")
    public void the_journey_is_successfully_updated() {
        assertTrue(successfulEntry);
    }

    @Then("the journey has failed to update")
    public void the_journey_has_failed_to_update() {
        assertFalse(successfulEntry);
    }

    @Then("a list of statuses is returned")
    public void a_list_of_statuses_is_returned() {
        assertNotEquals(null, statusList);
    }

    @Then("a list of statuses is not returned")
    public void a_list_of_statuses_is_not_returned() {
        assertEquals(null, statusList);
    }

    @Then("a list of statuses contains a status of {double} degrees, {double} % humidity and {double} bar")
    public void a_list_of_statuses_contains_a_status_of_degrees_humidity_and_bar(Double temperature, Double humidity,
            Double airPressure) {
        ContainerStatus _status = new ContainerStatus(temperature, humidity, airPressure);
        assertTrue(statusList.contains(_status));
    }

}
