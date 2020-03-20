package rcm;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
public class M2 {

	private int ID;
	private LinkedList<Client> registration_list;
	
	
	private LogisticsCompany lc = new LogisticsCompany();
	private Container c = new Container();
	private Client client = new Client();
	private Journey journey = new Journey();
	
	private LogisticsCompany lc2 = new LogisticsCompany();
	private Container c2 = new Container();
	private Journey journey2 = new Journey();
	private ResponseObject response;
	@Given("a container of {string}")
	public void a_container_of(String companyName) {
		//lc.setName(companyName);
	    //c.setLogisticsCompany(lc);
		
	}

	@Given("a client {string} who owns the container journey")
	public void a_client_who_owns_the_container_journey(String clientName) {
		//client.setName(clientName)
		//client.assignContainer(c);
		
	}

	@Given("the container journey has start location {string} and destination {string}")
	public void the_container_journey_has_start_location_and_destination(String start, String end) {
	    //journey.setTrip(start,end);
	}

	@When("a client requests to register the container")
	public void a_client_requests_to_register_the_container() {
		//registration_list = client.requestRegistration();
		//c.assignJourney(journey);
		//journey.setID(ID);
	}


	@Then("an Id is created")
	public void an_Id_is_created() {
		//assertTrue(((client.getContainer()).getJourney()).getID != null);
	}


	
	
	@Given("a logistics company {string}")
	public void a_logistics_company(String companyName) {
		//lc2.setName(companyName);
	}


	@Given("the container has a location {double} {double}")
	public void the_container_has_a_location(Double x, Double y) {
	    // c2.setLocation(x,y);

	}

	@When("logistics company updates containers location")
	public void logistics_company_updates_containers_location() {
	    // response = c2.updateLocation();   updateLocation() method contains setLocation(x,y) where x and y are read from the GPS
	 
	}

	@Then("the location is changed")
	public void the_location_is_changed() {
		//assertEquals("Location changed",response.getErrorMessage());
	    
	}



	@Then("the location is not changed")
	public void the_location_is_not_changed() {
		//assertEquals("Location not changed",response.getErrorMessage());
	    
	}


	@Given("all the containers assigned to {string}")
	public void all_the_containers_assigned_to(String clientName) {
		// client = setName(clientName);
	    // client.assignContainer(c2);
	    
	}

	@Given("the containers start location {string} and destination {string}")
	public void the_containers_start_location_and_destination(String start, String end) {
		//journey2.setTrip(start,end);
		//c2.assignJourney(journey);
	    
	}


	@When("the client filters the containers journeys based on the destination {string}")
	public void the_client_filters_the_containers_journeys_based_on_the_destination(String destination) {
	     //response = client.filter(destination)
	    
	}

	@Then("the clients containers journeys with the specific destination are listed")
	public void the_clients_containers_journeys_with_the_specific_destination_are_listed() {
		//assertEquals("Successful filtering",response.getErrorMessage());
	    
	}

	@Then("the containers journeys with the specific destination are not listed")
	public void the_containers_journeys_with_the_specific_destination_are_not_listed() {
		//assertEquals("Unsuccessful filtering",response.getErrorMessage());
	    
	}



}
