package rcm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class M1 {

    private boolean successfulEntry = false;
    private boolean successfulUpdate = false;
    private Set<Client> searchResults;

    private SharedObjectHolder holder;

    public M1(SharedObjectHolder holder) {
        this.holder = holder;
    }
    
    @Given("the logistic company has some clients including first client")
    public void the_logistic_company_has_some_clients_including_first_client() {
        holder.getFirstCompany().addClient(holder.getFirstClient());
        for (int i = 0; i < 9; i++) {
            holder.getFirstCompany().addClient(new Client("n" + i, "a" + i, "r" + i, "e" + i));
        }
    }

    @When("the first logistics company adds a client")
    public void the_first_logistics_company_adds_a_client() {
        successfulEntry = holder.getFirstCompany().addClient(holder.getFirstClient());
    }

    @When("a first logistics company searches for name {string}")
    public void a_first_logistics_company_searches_for_name(String name) {
        searchResults = holder.getFirstCompany().searchByName(name);
    }

    @When("a first logistics company searches for email {string}")
    public void a_first_logistics_company_searches_for_email(String email) {
        searchResults = holder.getFirstCompany().searchByEmail(email);
    }

    @When("a client enters new client info {string} with address {string} reference person {string} and email {string}")
    public void a_client_enters_new_client_info_with_address_reference_person_and_email(String name, String address,
            String refPerson, String email) {
        successfulUpdate = holder.getFirstClient().updateInfo(name, address, refPerson, email);
    }

    @When("the company creates a first client {string} with address {string} reference person {string} and email {string}")
    public void the_company_creates_a_first_client_with_address_reference_person_and_email(String name, String address, String refPerson, String email) {
        // creates an instance of Client (also checks input) and adds it to the client list
        Client client = holder.getFirstCompany().createClient(name, address, refPerson, email);
        holder.setFirstClient(client);
    }

    @Then("a client {string} with  address {string} reference person {string} and email {string} belongs to the company")
    public void a_client_with_address_reference_person_and_email_belongs_to_the_company(String name, String address, String refPerson, String email) {
        // find the client in client list of the company
        Client client = holder.getFirstCompany().getClient(name, address, refPerson, email);
        assertNotEquals(null, client);
        assertEquals(name, client.getName());
        assertEquals(address, client.getAddress());
        assertEquals(refPerson, client.getRefPerson());
        assertEquals(email, client.getEmail());
    }
    
    @Then("an id is automatically generated")
    public void an_id_is_automatically_generated() {
        assertNotEquals(null, holder.getFirstClient().getId());
    }

    @Then("a new client profile is not created")
    public void a_new_client_profile_is_not_created() {
        assertFalse(successfulEntry);
    }

    @Then("a new client profile is successfully created")
    public void a_new_client_profile_is_successfully_created() {
        assertTrue(successfulEntry);
    }

    @Then("it exists and the client is returned")
    public void it_exists_and_the_client_is_returned() {
        assertFalse(searchResults.isEmpty());
        // how to add the clients from Given to the set?
    }

    @Then("it does not exist and no client is returned")
    public void it_does_not_exist_and_no_client_is_returned() {
        assertTrue(searchResults.isEmpty());
    }

    @Then("the client profile is successfully updated")
    public void the_client_profile_is_successfully_updated() {
        assertTrue(successfulUpdate);
    }
}
