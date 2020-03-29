package rcm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class M1 {

    private Client client1;
    private LogisticsCompany company1;
    private String searchParam, param1, param2;
    private boolean successfulEntry = false;
    private boolean searchResults = false;
    private Response response;
    private int id, newId;

////	code snippets for M1:1
    @Given("a first logistics company {string} with address {string} reference person {string} and email {string}")
    public void a_first_logistics_company_with_address_reference_person_and_email(String name, String address,
            String refPerson, String email) {
        company1 = new LogisticsCompany(name, address, refPerson, email);
    }

    @Given("first client {string} with address {string} reference person {string} and email {string}")
    public void first_client_with_address_reference_person_and_email(String name, String address, String refPerson,
            String email) {
        client1 = new Client(name, address, refPerson, email);
        searchParam = name;
    }

    @Given("client {string} does not exist in client profile")
    public void client_does_not_exist_in_client_profile(String name) {
		searchResults = client1.searchProfiles(searchParam);
    }

    @When("the first logistics company enters client data")
    public void the_first_logistics_company_enters_client_data() {
        client1.addProfile(client1);
    }

    @Then("an id is automatically generated")
    public void an_id_is_automatically_generated() {
//		newId = client.autoGenerateId();
    }

    @Then("a client profile contains the client data and id")
    public void a_client_profile_contains_the_client_data_and_id() {
//      successfulProfile = client.createdProfile(name, address, refPerson, email, newId);
    }

    @Then("a new client profile is successfully created")
    public void a_new_client_profile_is_successfully_created() {
//        assertTrue(successfulEntry);
    }

    @Given("client {string} exists in client profile")
    public void client_exists_in_client_profile(String name) {
//		searchResults = client.searchProfiles(searchParam);
    }

    @Then("display a message that the client already exists")
    public void display_a_message_that_the_client_already_exists() {
//        assertEquals("This client already exists", response.getErrorMessage());
    }

    @Given("no logistics company")
    public void no_logistics_company() {
//        company = new LogisticsCompany(null, null, null, null);
    }

    @Then("display a message that only a logistics company may create a client profile")
    public void display_a_message_that_only_a_logistics_company_may_create_a_client_profile() {
//        assertEquals("Only a logistics company may create a client profile", response.getErrorMessage());
    }

    ////////////////////////////////////////////////////////////////////////////////////////
//	code snippets for M1:2

    @When("a first logistics company searches for parameter {string} in client profile")
    public void a_first_logistics_company_searches_for_parameter_in_client_profile(String searchParam) {
//        searchResults = client.searchProfiles(searchParam);
    }

    @When("parameter {string} exists in client profile")
    public void parameter_exists_in_client_profile(String searchParam) {
//        assertTrue(searchResults);
    }

    @When("parameter {string} does not exist in client profile")
    public void parameter_does_not_exist_in_client_profile(String searchParam) {
//        assertFalse(searchResults);
    }

    @Then("the client {string} with address {string} reference person {string} email {string} and ID {int} is returned")
    public void the_client_with_address_reference_person_email_and_ID_is_returned(String name, String address,
            String refPerson, String email, Integer id) {
//        assertNotEquals(null, statusList);
    }

    @Then("display a message that the parameter does not exist")
    public void display_a_message_that_the_parameter_does_not_exist() {
//        assertEquals("This item does not exist in the client database", response.getErrorMessage());
    }

    ///////////////////////////////////////////////////////////////////////////////
//	code snippets for M1:3

    @When("a client enters new address {string} and email {string}")
    public void a_client_enters_new_address_and_email(String address, String email) {
//        param1 = address;
//        param2 = email;
    }

    @Then("the client profile is changed to address {string} reference person {string} email {string}")
    public void the_client_profile_is_changed_to_address_reference_person_email(String name, String address,
            String refPerson) {
//        successfulEntry = client.updateProfile(name, param1, refPerson, param2);
    }

    @Then("the client profile is successfully updated")
    public void the_client_profile_is_successfully_updated() {
//        assertTrue(successfulEntry);
    }

    @Then("display a message that the client id cannot be changed")
    public void display_a_message_that_the_client_id_cannot_be_changed() {
//        assertEquals("An ID number can not be changed", response.getErrorMessage());
    }

    @When("a client enters a new id {int}")
    public void a_client_enters_a_new_id(Integer id) {
//        successfulEntry = false;
    }

    @Then("the client profile fails to update")
    public void the_client_profile_fails_to_update() {
//        assertFalse(successfulEntry);

    }
}
