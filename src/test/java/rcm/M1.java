package rcm;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class M1 {
	
	private Client client;
	private LogisticsCompany company;
	
	
////	code snippets for M1:1
	@Given("a logistic company {string} with address {string} reference person {string} and email {string}")
	public void a_logistic_company_with_address_reference_person_and_email(String name, String address, String refPerson, String email) {
	    // Write code here that turns the phrase above into concrete actions
	}
	
	@Given("a client {string}")
	public void a_client(String name) {
	    // Write code here that turns the phrase above into concrete actions
	}

	@Given("no client {string} exists in client profile")
	public void no_client_exists_in_client_profile(String name) {
	    // Write code here that turns the phrase above into concrete actions
	}

	@When("a logistic company enters client data of {string}, {string}, {string} and {string} to the client profile")
	public void a_logistic_company_enters_client_data_of_and_to_the_client_profile(String name, String address, String refPerson, String email) {
	    // Write code here that turns the phrase above into concrete actions
 	}
	
	@Then("a client profile contains {string}, {string}, {string}, {string} and {int}")
	public void a_client_profile_contains_and(String name, String address, String refPerson, String email, Integer id) {
	    // Write code here that turns the phrase above into concrete actions
	}

	@Then("a new client profile is successfully created")
	public void a_new_client_profile_is_successfully_created() {
	    // Write code here that turns the phrase above into concrete actions
	}

	@When("a logistic company enters client data {string}, {string}, {string} and bananas@chiquita.com to the client profile")
	public void a_logistic_company_enters_client_data_and_bananas_chiquita_com_to_the_client_profile(String string, String string2, String string3) {
	    // Write code here that turns the phrase above into concrete actions
	}

	@Given("a client {string} exists in client profile")
	public void a_client_exists_in_client_profile(String name) {
	    // Write code here that turns the phrase above into concrete actions
	}

	@Then("display a message that the client already exists")
	public void display_a_message_that_the_client_already_exists() {
	    // Write code here that turns the phrase above into concrete actions
	}

	@Given("no logistic company")
	public void no_logistic_company() {
	    // Write code here that turns the phrase above into concrete actions
	}

	@Then("display a message that only a logistic company may create a client profile")
	public void display_a_message_that_only_a_logistic_company_may_create_a_client_profile() {
	    // Write code here that turns the phrase above into concrete actions
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
//	code snippets for M1:2
	@Given("email {string}")
	public void email(String email) {
	    // Write code here that turns the phrase above into concrete actions
	}

	@When("a logistic company searches for email {string} in client profile")
	public void a_logistic_company_searches_for_email_in_client_profile(String email) {
	    // Write code here that turns the phrase above into concrete actions
	}

	@When("email {string} exists")
	public void email_exists(String email) {
	    // Write code here that turns the phrase above into concrete actions
	}
	
	@When("email {string} does not exist")
	public void email_does_not_exist(String email) {
		// Write code here that turns the phrase above into concrete actions
	}

	@Then("the client {string} with address {string} reference person {string} email {string} and ID {int} is returned")
	public void the_client_with_address_reference_person_email_and_ID_is_returned(String name, String address, String refPerson, String email, Integer id) {
	    // Write code here that turns the phrase above into concrete actions
	}

	@Then("display a message that the email does not exist")
	public void display_a_message_that_the_email_does_not_exist() {
		// Write code here that turns the phrase above into concrete actions
	}
	
	@Given("name {string}")
	public void name(String name) {
	    // Write code here that turns the phrase above into concrete actions
	}
	
	@When("a logistic company searches for name {string} in client profile")
	public void a_logistic_company_searches_for_name_in_client_profile(String name) {
	    // Write code here that turns the phrase above into concrete actions
	}
	
	@When("name {string} exists")
	public void name_exists(String name) {
	    // Write code here that turns the phrase above into concrete actions
	}

	@When("name {string} does not exist")
	public void name_does_not_exist(String name) {
	    // Write code here that turns the phrase above into concrete actions
	}

	@Then("display a message that the client does not exist")
	public void display_a_message_that_the_client_does_not_exist() {
    	// Write code here that turns the phrase above into concrete actions
	}
	
	///////////////////////////////////////////////////////////////////////////////
//	code snippets for M1:3
	
	@Given("a client {string} with address {string} reference person {string} email {string} and ID {int}")
	public void a_client_with_address_reference_person_email_and_ID(String name, String address, String refPerson, String email, Integer id) {
	    // Write code here that turns the phrase above into concrete actions
	}

	@When("a client enters new address {string} and email {string}")
	public void a_client_enters_new_address_and_email(String address, String email) {
	    // Write code here that turns the phrase above into concrete actions
	}
	@Then("the client profile is changed to address {string} reference person {string} email {string} and ID {int}")
	public void the_client_profile_is_changed_to_address_reference_person_email_and_ID(String name, String address, String refPerson, Integer id) {
	    // Write code here that turns the phrase above into concrete actions
	}

	@Then("the client profile is successfully updated")
	public void the_client_profile_is_successfully_updated() {
	    // Write code here that turns the phrase above into concrete actions
	}

	@When("a client enters a new name")
	public void a_client_enters_a_new_name() {
	    // Write code here that turns the phrase above into concrete actions
	}

	@Then("display a message that the client name cannot be changed")
	public void display_a_message_that_the_client_name_cannot_be_changed() {
	    // Write code here that turns the phrase above into concrete actions
	}
	@Then("display a message that the client id cannot be changed")
	public void display_a_message_that_the_client_id_cannot_be_changed() {
	    // Write code here that turns the phrase above into concrete actions
	}

	@When("a client enters a new id {int}")
	public void a_client_enters_a_new_id(Integer id) {
	    // Write code here that turns the phrase above into concrete actions
	}
	
	@Then("the client profile fails to update")
	public void the_client_profile_fails_to_update() {
	    // Write code here that turns the phrase above into concrete actions
	}
}
