package rcm;

public class M1 {
	
	private Client client;
	private LogisticsCompany company;
	
	
//	code snippets for M1:1
	@Given("a client {string}")
	public void a_client(String string) {
	    // Write code here that turns the phrase above into concrete actions
	}

	@Given("no client {string} exists in client profile")
	public void no_client_exists_in_client_profile(String string) {
	    // Write code here that turns the phrase above into concrete actions
	}

	@When("a logistic company enters client data {string}, {string}, {string} and bananas@chiquita.com to the client profile")
	public void a_logistic_company_enters_client_data_and_bananas_chiquita_com_to_the_client_profile(String string, String string2, String string3) {
	    // Write code here that turns the phrase above into concrete actions
	}

	@Then("a client profile contains {string}, {string}, {string}, bananas@chiquita.com and {int}")
	public void a_client_profile_contains_bananas_chiquita_com_and(String string, String string2, String string3, Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	}

	@Then("a new client profile is successfully created")
	public void a_new_client_profile_is_successfully_created() {
	    // Write code here that turns the phrase above into concrete actions
	}

	@Given("client {string} exists in client profile")
	public void client_exists_in_client_profile(String string) {
	    // Write code here that turns the phrase above into concrete actions
	}

	@Then("display a message that the client already exists")
	public void display_a_message_that_the_client_already_exists() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("no logistic company")
	public void no_logistic_company() {
	    // Write code here that turns the phrase above into concrete actions
	}

	@Then("display a message that only a logistics company may create a client profile")
	public void display_a_message_that_only_a_logistic_company_may_create_a_client_profile() {
	    // Write code here that turns the phrase above into concrete actions
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
//	code snippets for M1:2
	@Given("an email bananas@chiquita.com")
	public void an_email_bananas_chiquita_com() {
		// Write code here that turns the phrase above into concrete actions
	}

	@When("a logistic company searches for bananas@chiquita.com in client profile")
	public void a_logistic_company_searches_for_bananas_chiquita_com_in_client_profile() {
		// Write code here that turns the phrase above into concrete actions
	}

	@When("bananas@chiquita.com exists")
	public void bananas_chiquita_com_exists() {
		// Write code here that turns the phrase above into concrete actions
	}

	@Then("the client profile {string} is returned")
	public void the_client_profile_is_returned(String string) {
		// Write code here that turns the phrase above into concrete actions
	}

	@When("bananas@chiquita.com does not exist")
	public void bananas_chiquita_com_does_not_exist() {
		// Write code here that turns the phrase above into concrete actions
	}

	@Then("display a message that the email does not exist")
	public void display_a_message_that_the_email_does_not_exist() {
		// Write code here that turns the phrase above into concrete actions
	}

	@Given("a client name {string}")
	public void a_client_name(String string) {
		// Write code here that turns the phrase above into concrete actions
	}

	@When("a logistic company searches for {string} in client profile")
	public void a_logistics_company_searches_for_in_client_profile(String string) {
		// Write code here that turns the phrase above into concrete actions
	}

	@When("{string} exists")
	public void exists(String string) {
    	// Write code here that turns the phrase above into concrete actions
	}

	@When("{string} does not exists")
	public void does_not_exists(String string) {
		// Write code here that turns the phrase above into concrete actions
	}

	@Then("display a message that the client does not exist")
	public void display_a_message_that_the_client_does_not_exist() {
    	// Write code here that turns the phrase above into concrete actions
	}
	
	///////////////////////////////////////////////////////////////////////////////
//	code snippets for M1:3
	
	@Given("a client {string} exists in client profile")
	public void a_client_of_exists_in_client_profile(String string) {
	    // Write code here that turns the phrase above into concrete actions
	}

	@When("a client enters new information {string}")
	public void a_client_enters_new_information(String string) {
	    // Write code here that turns the phrase above into concrete actions
	}

	@Then("the client profile information is changed to {string}")
	public void the_client_profile_information_is_changed_to(String string) {
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

	@Then("the client profile is failed to update")
	public void the_client_profile_is_failed_to_update() {
	    // Write code here that turns the phrase above into concrete actions
	}

	@When("a client enters a new id")
	public void a_client_enters_a_new_id() {
	    // Write code here that turns the phrase above into concrete actions
	}

	@Then("display a message that the client id cannot be changed")
	public void display_a_message_that_the_client_id_cannot_be_changed() {
	    // Write code here that turns the phrase above into concrete actions
	}
}
