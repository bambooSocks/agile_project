package rcm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class M1 {

    private Client client1;
    private LogisticsCompany company1;
    private boolean successfulEntry = false;
    private boolean successfulUpdate = false;
    private boolean searchResults = false;

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
    }

    @When("the first logistics company enters client data")
    public void the_first_logistics_company_enters_client_data() {
        successfulEntry = company1.addClient(client1);
    }

    @Then("an id is automatically generated")
    public void an_id_is_automatically_generated() {
        assertNotEquals(null, client1.id);
    }

    @Then("a new client profile is successfully created")
    public void a_new_client_profile_is_successfully_created() {
        assertTrue(successfulEntry);
    }

    @Given("no logistics company")
    public void no_logistics_company() {
        company1 = null;
    }

    @When("the first client enters client data")
    public void the_first_client_enters_client_data() {
//        ask where this needs to be
        try {
            company1.addClient(client1);
        } catch (Exception e) {
            successfulEntry = false;
        }
    }

    @Then("a new client profile is not created")
    public void a_new_client_profile_is_not_created() {
        assertFalse(successfulEntry);
    }

    ////////////////////////////////////////////////////////////////////////////////////////
//	code snippets for M1:2

    @When("a first logistics company searches for parameter {string} in client profile")
    public void a_first_logistics_company_searches_for_parameter_in_client_profile(String searchParam) {
        searchResults = company1.searchClient(searchParam);
    }

    @When("parameter {string} exists in client profile")
    public void parameter_exists_in_client_profile(String searchParam) {
        searchResults = company1.addClient(client1);
        assertTrue(searchResults);
    }

    @When("parameter {string} does not exist in client profile")
    public void parameter_does_not_exist_in_client_profile(String searchParam) {
//        assertFalse(searchResults);
        searchResults = company1.searchClient(searchParam);
    }

    @Then("the client {string} with address {string} reference person {string} email {string} and ID {int} is returned")
    public void the_client_with_address_reference_person_email_and_ID_is_returned(String name, String address,
            String refPerson, String email, Integer id) {
//        assertNotEquals(null, searchResults);
    }

    @Then("no client is returned")
    public void no_client_is_returned() {
//        assertEquals(null, searchResults);
    }

    ///////////////////////////////////////////////////////////////////////////////
//	code snippets for M1:3

    @When("a client enters new client info {string} with address {string} reference person {string} and email {string}")
    public void a_client_enters_new_client_info_with_address_reference_person_and_email(String name, String address,
            String refPerson, String email) {
        successfulUpdate = client1.updateClient(name, address, refPerson, email);
    }

    @Then("the client profile is successfully updated")
    public void the_client_profile_is_successfully_updated() {
        assertTrue(successfulUpdate);
    }
}
