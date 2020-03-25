package rcm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class M1 {
	
	private Client client;
	private LogisticsCompany company;
	private String searchParam, successfulProfile, param1, param2;
    private boolean successfulEntry = false;
    private boolean searchResults = false;
	private Response response;
	private int id, newId;

	
////	code snippets for M1:1
	@Given("a logistic company {string} with address {string} reference person {string} and email {string}")
	public void a_logistic_company_with_address_reference_person_and_email(String name, String address, String refPerson, String email) {
		company = new LogisticsCompany(name, address, refPerson, email);
	}
	
	@Given("a client {string}")
	public void a_client(String name) {
		String searchParam = name;
	}

	@Given("no client {string} exists in client profile")
	public void no_client_exists_in_client_profile(String name) {
//		searchResults = client.searchProfiles(searchParam);
	}

	@When("a logistic company enters client data of {string}, {string}, {string} and {string} to the client profile")
	public void a_logistic_company_enters_client_data_of_and_to_the_client_profile(String name, String address, String refPerson, String email) {
//        successfulProfile = client.enterProfile(name, address, refPerson, email, id);
 	}
	
	@Then("id is automatically generated")
	public void id_is_automatically_generated() {
//		newId = client.autoGenerateId();
	}
	
	@Then("a client profile contains {string}, {string}, {string}, {string} and {int}")
	public void a_client_profile_contains_and(String name, String address, String refPerson, String email, Integer id) {
//      successfulProfile = client.createdProfile(name, address, refPerson, email, newId);
	}

	@Then("a new client profile is successfully created")
	public void a_new_client_profile_is_successfully_created() {
        assertTrue(successfulEntry);
	}

	@Given("a client {string} exists in client profile")
	public void a_client_exists_in_client_profile(String name) {
//		searchResults = client.searchProfiles(searchParam);
	}

	@Then("display a message that the client already exists")
	public void display_a_message_that_the_client_already_exists() {
		assertEquals("This client already exists",response.getErrorMessage());
	}

	@Given("no logistic company")
	public void no_logistic_company() {
		company = new LogisticsCompany(null, null, null, null);
	}

	@Then("display a message that only a logistic company may create a client profile")
	public void display_a_message_that_only_a_logistic_company_may_create_a_client_profile() {
		assertEquals("Only a logistics company may create a client profile",response.getErrorMessage());
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
//	code snippets for M1:2
	@Given("email {string}")
	public void email(String email) {
		searchParam = email;
	}

	@When("a logistic company searches for email {string} in client profile")
	public void a_logistic_company_searches_for_email_in_client_profile(String email) {
//        searchResults = client.searchProfiles(searchParam);
	}

	@When("email {string} exists")
	public void email_exists(String email) {
		assertTrue(searchResults);
	}
	
	@When("email {string} does not exist")
	public void email_does_not_exist(String email) {
		assertFalse(searchResults);
	}

	@Then("the client {string} with address {string} reference person {string} email {string} and ID {int} is returned")
	public void the_client_with_address_reference_person_email_and_ID_is_returned(String name, String address, String refPerson, String email, Integer id) {
//        assertNotEquals(null, statusList);
//        its late and I don't know how to do this...
	}

	@Then("display a message that the email does not exist")
	public void display_a_message_that_the_email_does_not_exist() {
		assertEquals("This email does not exist",response.getErrorMessage());
	}
	
	@Given("name {string}")
	public void name(String name) {
		searchParam = name;
	}
	
	@When("a logistic company searches for name {string} in client profile")
	public void a_logistic_company_searches_for_name_in_client_profile(String name) {
//        searchResults = client.searchProfiles(searchParam);
	}
	
	@When("name {string} exists")
	public void name_exists(String name) {
		assertTrue(searchResults);
	}

	@When("name {string} does not exist")
	public void name_does_not_exist(String name) {
		assertFalse(searchResults);
	}

	@Then("display a message that the client does not exist")
	public void display_a_message_that_the_client_does_not_exist() {
		assertEquals("This client does not exist",response.getErrorMessage());
	}
	
	///////////////////////////////////////////////////////////////////////////////
//	code snippets for M1:3
	
	@Given("a client {string} with address {string} reference person {string} email {string} and ID {int}")
	public void a_client_with_address_reference_person_email_and_ID(String name, String address, String refPerson, String email, Integer id) {
//		client = new Client(name, address, refPerson, email, id);
//		this will give errors unless we add id to the profile in the Client class
	}

	@When("a client enters new address {string} and email {string}")
	public void a_client_enters_new_address_and_email(String address, String email) {
		param1 = address;
		param2 = email;
	}
	
	@Then("the client profile is changed to address {string} reference person {string} email {string} and ID {int}")
	public void the_client_profile_is_changed_to_address_reference_person_email_and_ID(String name, String address, String refPerson, Integer id) {
//        successfulEntry = client.updateProfile(name, param1, refPerson, param2, id);
	}

	@Then("the client profile is successfully updated")
	public void the_client_profile_is_successfully_updated() {
        assertTrue(successfulEntry);
	}

	@Then("display a message that the client id cannot be changed")
	public void display_a_message_that_the_client_id_cannot_be_changed() {
		assertEquals("An ID number can not be changed",response.getErrorMessage());
	}

	@When("a client enters a new id {int}")
	public void a_client_enters_a_new_id(Integer id) {
		successfulEntry = false;
	}
	
	@Then("the client profile fails to update")
	public void the_client_profile_fails_to_update() {
        assertFalse(successfulEntry);

	}
}
