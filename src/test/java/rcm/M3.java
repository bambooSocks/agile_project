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
	private ContainerJourney journey;
	private LogisticCompany company;
	private Client client;
	private boolean successful_entry = false;
	private LinkedList<Client> status_list;
	
	@Given("a container journey without a container")
	public void a_container_journey_without_a_container() {
	    journey = new ContainerJourney(null);
	}

	@Given("a container of {string} in a container journey")
	public void a_container_of_in_a_journey(String companyName) {
		container = new Container(new LogisticCompany(companyName));
	    journey = new ContainerJourney(container);
	}

	@Given("a logistic company {string}")
	public void a_logistic_company(String companyName) {
		company = new LogisticCompany(companyName);
	}

	@When("a logistic company enters a container status of {int} degrees, {int} % humidity and {double} bar to the container journey")
	public void a_logistic_company_enters_a_container_status_of_degrees_humidity_and_bar_to_the_journey(Integer temp, Integer humidity, Double pressure) {
	    ContainerStatus status = new ContainerStatus(temp, humidity, pressure);
	    successful_entry = company.enterStatus(journey, status);
	}

	@Then("a journey contains status {int} degrees, {int} % humidity and {double} bar")
	public void a_journey_contains_status_degrees_humidity_and_bar(Integer temp, Integer humidity, Double pressure) {
		ContainerStatus status = new ContainerStatus(temp, humidity, pressure);
		assertTrue(journey.contains(status));
	}
	
	@Then("a journey is successfully updated")
	public void a_journey_is_successfully_updated() {
	    assertTrue(successful_entry);
	}
	
	@Then("a journey does not contain status {int} degrees, {int} % humidity and {double} bar")
	public void a_journey_does_not_contain_status_degrees_humidity_and_bar(Integer temp, Integer humidity, Double pressure) {
		ContainerStatus status = new ContainerStatus(temp, humidity, pressure);
		assertFalse(journey.contains(status));
	}

	@Then("a journey is failed to update")
	public void a_journey_is_failed_to_update() {
		assertFalse(successful_entry);
	}
	
	
	/// _2 
	// feature_2
	
	@Given("a client {string} owns the container journey")
	public void a_client_owns_the_container_journey(String client_name) {
	    client = new Client(client_name);
	    client.ownsJourney(journey);
	}

	@Given("the container journey has a status of {int} degrees, {int} % humidity and {double} bar")
	public void the_container_journey_has_a_status_of_degrees_humidity_and_bar(Integer temp, Integer humidity, Double pressure) {
		ContainerStatus status = new ContainerStatus(temp, humidity, pressure);
		successful_entry = company.enterStatus(journey, status);
		
	}

	@When("a client requests access to the status")
	public void a_client_requests_access_to_the_status() {		
	    status_list = client.requestStatus();
	}

	@Then("a list of statuses contains a status of {int} degrees, {int} % humidity and {double} bar")
	public void a_list_of_statuses_contains_a_status_of_degrees_humidity_and_bar(Integer temp, Integer humidity, Double pressure) {
		ContainerStatus status = new ContainerStatus(temp, humidity, pressure);
		assertTrue(status_list.contains(status));
	}

	@Then("a list of statuses is returned")
	public void a_list_of_statuses_is_returned() {
	    assertNotEquals(null, status_list);
	}
	
}
