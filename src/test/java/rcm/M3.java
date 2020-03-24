package rcm;

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
	private boolean successfulEntry = false;
	private LinkedList<Client> statusList;
	private ContainerStatus status;
	
	@Given("a container journey without a container")
	public void a_container_journey_without_a_container() {
//	    journey = new Journey(null);
	}

	@Given("a container of {string} in a container journey")
	public void a_container_of_in_a_journey(String companyName) {
//		container = new Container(new LogisticsCompany(companyName));
//	    journey = new Journey(container);
	}

	@Given("a logistic company {string}")
	public void a_logistic_company(String companyName) {
//		
	}

	@When("a logistic company enters a container status of {int} degrees, {int} % humidity and {double} bar to the container journey")
	public void a_logistic_company_enters_a_container_status_of_degrees_humidity_and_bar_to_the_journey(Integer temp, Integer humidity, Double pressure) {
//	    ContainerStatus status = new ContainerStatus(temp, humidity, pressure);
//	    successful_entry = company.enterStatus(journey, status);
	}

	@Then("a journey contains status {int} degrees, {int} % humidity and {double} bar")
	public void a_journey_contains_status_degrees_humidity_and_bar(Integer temp, Integer humidity, Double pressure) {
//		ContainerStatus status = new ContainerStatus(temp, humidity, pressure);
//		assertTrue(journey.contains(status));
	}
	
	@Then("a journey is successfully updated")
	public void a_journey_is_successfully_updated() {
	    assertTrue(successful_entry);
	}
	
	@Then("a journey does not contain status {int} degrees, {int} % humidity and {double} bar")
	public void a_journey_does_not_contain_status_degrees_humidity_and_bar(Integer temp, Integer humidity, Double pressure) {
//		ContainerStatus status = new ContainerStatus(temp, humidity, pressure);
//		assertFalse(journey.contains(status));
	}

	@Then("a journey is failed to update")
	public void a_journey_is_failed_to_update() {
		assertFalse(successful_entry);
	}
	
	
	/// _2 
	// feature_2
	
	@Given("a client {string} owns the container journey")
	public void a_client_owns_the_container_journey(String client_name) {
//	    client = new Client(client_name);
//	    client.ownsJourney(journey);
	}

	@Given("the container journey has a status of {int} degrees, {int} % humidity and {double} bar")
	public void the_container_journey_has_a_status_of_degrees_humidity_and_bar(Integer temp, Integer humidity, Double pressure) {
//		ContainerStatus status = new ContainerStatus(temp, humidity, pressure);
//		successful_entry = company.enterStatus(journey, status);
		
	}

	@When("a client requests access to the status")
	public void a_client_requests_access_to_the_status() {		
//	    status_list = client.requestStatus();
	}

	@Then("a list of statuses contains a status of {int} degrees, {int} % humidity and {double} bar")
	public void a_list_of_statuses_contains_a_status_of_degrees_humidity_and_bar(Integer temp, Integer humidity, Double pressure) {
//		ContainerStatus status = new ContainerStatus(temp, humidity, pressure);
//		assertTrue(status_list.contains(status));
	}

	@Then("a list of statuses is returned")
	public void a_list_of_statuses_is_returned() {
	    assertNotEquals(null, status_list);
	}
	
	// NEW STEPS: ////////////////////////////////////////////////////////
	
	@Given("a first logistic company {string} with address {string}, reference person {string} and email {string}")
	public void a_first_logistic_company_with_address_reference_person_and_email(String name, String address , String refPerson, String email) {
		company1 = new LogisticsCompany( name, address , refPerson, email);
		
	}
	
	@Given("a second logistic company {string} with address {string}, reference person {string} and email {string}")
	public void a_second_logistic_company_with_address_reference_person_and_email(String name, String address , String refPerson, String email) {
	    company2 = new LogisticsCompany(name, address , refPerson, email);
	}

	@Given("a first client {string} with address {string}, reference person {string} and email {string}")
	public void a_first_client_with_address_reference_person_and_email(String name, String address , String refPerson, String email) {
		client1=new Client(name, address , refPerson, email);
	}

	@Given("a second client {string} with address {string}, reference person {string} and email {string}")
	public void a_second_client_with_address_reference_person_and_email(String name, String address , String refPerson, String email) {
		client2 = new Client(name, address , refPerson, email);
	}
	
	@Given("a container of first logistic company with ID {int}")
	public void a_container_of_first_logistic_company_with_ID(Integer id) {
		container = new Container(id, company1);
	}

	@Given("a journey of given container and first client with origin port of {string}, destination port of {string} and a content of {string}")
	public void a_journey_of_given_container_and_first_client_with_origin_port_of_destination_port_of_and_a_content_of(String originPort, String destinationPort, String content) {
	    journey = new Journey(originPort, destinationPort, content, container, client1);
	}

	@Given("a container status of {double} degrees, {double} % humidity and {double} bar to the given journey")
	public void a_container_status_of_degrees_humidity_and_bar_to_the_given_journey(Double temperature, Double humidity, Double airPressure) {
		status = new ContainerStatus(temperature, humidity, airPressure);
	}

	@When("the first logistic company enters the given container status")
	public void the_first_logistic_company_enters_the_given_container_status() {
		successfulEntry = company1.enterStatus(journey, status);
	}

	@Then("the journey contains the given status")
	public void the_journey_contains_the_given_status() {
		assertTrue(journey.contains(status));
	}

	@Then("the journey is successfully updated")
	public void the_journey_is_successfully_updated() {
	    assertTrue(successfulEntry);
	}



	@When("the second logistic company enters the given container status")
	public void the_second_logistic_company_enters_the_given_container_status() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("the journey does not contain the given status")
	public void the_journey_does_not_contain_the_given_status() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("the journey has failed to update")
	public void the_journey_has_failed_to_update() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@Given("a journey of no container and first client with origin port of {string}, destination port of {string} and a content of {string}")
	public void a_journey_of_no_container_and_first_client_with_origin_port_of_destination_port_of_and_a_content_of(String originPort, String destinationPort, String content) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("an initial container status in the journey of {double} degrees, {double} % humidity and {double} bar")
	public void an_initial_container_status_in_the_journey_of_degrees_humidity_and_bar(Double temperature, Double humidity, Double airPressure) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the first client requests access to the status")
	public void the_first_client_requests_access_to_the_status() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}


	@When("the second client requests access to the status")
	public void the_second_client_requests_access_to_the_status() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("a list of statuses is empty")
	public void a_list_of_statuses_is_empty() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("a list of statuses is not returned")
	public void a_list_of_statuses_is_not_returned() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	
}
