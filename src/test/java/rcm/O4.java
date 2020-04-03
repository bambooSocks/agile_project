package rcm;

import io.cucumber.java.en.Given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
public class O4 {
	
	private LogisticsCompany logisticsCompany;
	private Container container;
	private Client client;
	private Journey journey;
	private boolean closed;


	
	@Given("the client {string} with address {string}, reference person {string} and email {string}")
    public void the_client_with_address_reference_person_and_email(String name, String address, String refPerson,
            String email) {
        client = new Client(name, address, refPerson, email);

    }
	
	@When("the client clicks the close button")
	public void the_client_clicks_the_close_button() {
	    closed = client.closeButton();
	    
	}

	@Then("the clients containers journeys are saved and the app is closed")
	public void the_clients_containers_journeys_are_saved_and_the_app_is_closed() {
	    assertTrue(closed);
	}

	@When("the client wants to open the app")
	public void the_client_wants_to_open_the_app() {
	    // Write code here that turns the phrase above into concrete actions
	}

	@Then("the clients containers journeys are loaded from the database")
	public void the_clients_containers_journeys_are_loaded_from_the_database() {
	    assertTrue(!closed);
	}

}
