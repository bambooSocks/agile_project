package rcm.cucumber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rcm.Client;

public class M1 {

    private boolean successfulUpdate = false;
    private Set<Client> searchResults;

    private SharedObjectHolder holder;

    public M1(SharedObjectHolder holder) {
        this.holder = holder;
    }

    ////////////// M1:1////////////////////////////////////////////////
    @When("the company creates a first client {string} with address {string} reference person {string} and email {string}")
    public void the_company_creates_a_first_client_with_address_reference_person_and_email(String name, String address,
            String refPerson, String email) {
        Client client = holder.getFirstCompany().createClient(name, address, refPerson, email);
        holder.setFirstClient(client);
    }

    @Then("an id is automatically generated")
    public void an_id_is_automatically_generated() {
        assertNotEquals(null, holder.getFirstClient().getId());
    }

    @Then("a new client {string} with address {string} reference person {string} and email {string} belongs to the company")
    public void a_new_client_with_address_reference_person_and_email_belongs_to_the_company(String name, String address,
            String refPerson, String email) {
        assertNotEquals(null, holder.getFirstClient());
        assertEquals(name, holder.getFirstClient().getName());
        assertEquals(address, holder.getFirstClient().getAddress());
        assertEquals(refPerson, holder.getFirstClient().getRefPerson());
        assertEquals(email, holder.getFirstClient().getEmail());
    }

    @Then("the email is not a valid email and the client is not created")
    public void the_email_is_not_a_valid_email_and_the_client_is_not_created() {
        assertEquals(null, holder.getFirstClient());
    }

    @Then("the name is not a valid email and the client is not created")
    public void the_name_is_not_a_valid_email_and_the_client_is_not_created() {
        assertEquals(null, holder.getFirstClient());
    }

///////////////////M1:2//////////////////////////////////////////
    @Given("the logistic company has some clients including first client")
    public void the_logistic_company_has_some_clients_including_first_client() {
        String name = holder.getFirstClient().getName();
        String address = holder.getFirstClient().getAddress();
        String refPerson = holder.getFirstClient().getRefPerson();
        String email = holder.getFirstClient().getEmail();
        holder.getFirstCompany().createClient(name, address, refPerson, email);
    }

    @When("a first logistics company searches for name {string}")
    public void a_first_logistics_company_searches_for_name(String name) {
        searchResults = holder.getFirstCompany().searchByName(name);
    }

    @When("a first logistics company searches for email {string}")
    public void a_first_logistics_company_searches_for_email(String email) {
        searchResults = holder.getFirstCompany().searchByEmail(email);
    }

    @Then("it exists and the client {string} with address {string} reference person {string} and email {string} is returned")
    public void it_exists_and_the_client_with_address_reference_person_and_email_is_returned(String name,
            String address, String refPerson, String email) {
        assertFalse(searchResults.isEmpty());
        assertNotEquals(null, holder.getFirstClient());
        assertEquals(name, holder.getFirstClient().getName());
        assertEquals(address, holder.getFirstClient().getAddress());
        assertEquals(refPerson, holder.getFirstClient().getRefPerson());
        assertEquals(email, holder.getFirstClient().getEmail());
    }

    @Then("it does not exist and no client is returned")
    public void it_does_not_exist_and_no_client_is_returned() {
        assertTrue(searchResults.isEmpty());
    }

//////////////////M1:3////////////////////////////////////////////////
    @When("a client enters new client info {string} with address {string} reference person {string} and email {string}")
    public void a_client_enters_new_client_info_with_address_reference_person_and_email(String name, String address,
            String refPerson, String email) {
        successfulUpdate = holder.getFirstClient().updateInfo(name, address, refPerson, email);
    }

    @Then("the client {string} with address {string} reference person {string} and email {string} is successfully updated")
    public void the_client_with_address_reference_person_and_email_is_successfully_updated(String name, String address,
            String refPerson, String email) {
        assertTrue(successfulUpdate);
        assertNotEquals(null, holder.getFirstClient());
        assertEquals(name, holder.getFirstClient().getName());
        assertEquals(address, holder.getFirstClient().getAddress());
        assertEquals(refPerson, holder.getFirstClient().getRefPerson());
        assertEquals(email, holder.getFirstClient().getEmail());
    }
}
