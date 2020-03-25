package rcm;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
public class O4 {
	
	private LogisticsCompany logisticsCompany;
	private Container container;
	private Client client;
	private Journey journey;
	private ResponseObject response;


	@Given("client {string} containers journeys")
	public void client_containers_journeys(String string) {
	    // Write code here that turns the phrase above into concrete actions

	}
	
	@Given("a client {string} who owns the containers journeys")
	public void a_client_who_owns_the_containers_journeys(String string) {
	    // Write code here that turns the phrase above into concrete actions
	}
	
	@When("the client wants to close the app")
	public void the_client_wants_to_close_the_app() {
	    // response = client.closeApp()
	   
	}
	@Then("the clients {string} containers journeys are saved and the app is closed")
	public void the_clients_containers_journeys_are_saved_and_the_app_is_closed(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    
	}

	@When("the client {string} wants to open the app")
	public void the_client_wants_to_open_the_app(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    
	}
	@Then("the clients {string} containers journeys are loaded from the database")
	public void the_clients_containers_journeys_are_loaded_from_the_database(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    
	}

}
