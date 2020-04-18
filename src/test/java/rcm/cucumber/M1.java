package rcm.cucumber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rcm.Client;
import rcm.Password;

public class M1 {

    private Set<Client> searchResults;
    private SharedObjectHolder holder;

    public M1(SharedObjectHolder holder) {
        this.holder = holder;
    }

    ////////////// M1:1////////////////////////////////////////////////
    @When("the company creates a first client {string} with address {string} reference person {string} email {string} and password {string}")
    public void the_company_creates_a_first_client_with_address_reference_person_email_and_password(String name,
            String address, String refPerson, String email, String password) {
        Client client = holder.getFirstCompany().createClient(name, address, refPerson, email, password);
        holder.setFirstClient(client);
    }

    @Then("an id is automatically generated")
    public void an_id_is_automatically_generated() {
        assertNotEquals(null, holder.getFirstClient().getId());
    }

    @Then("a new client {string} with address {string} reference person {string} email {string} and password {string} belongs to the company")
    public void a_new_client_with_address_reference_person_email_and_password_belongs_to_the_company(String name,
            String address, String refPerson, String email, String password) {
        assertNotEquals(null, holder.getFirstClient());
        assertEquals(name, holder.getFirstClient().getName());
        assertEquals(address, holder.getFirstClient().getAddress());
        assertEquals(refPerson, holder.getFirstClient().getRefPerson());
        assertEquals(email, holder.getFirstClient().getEmail());
        assertEquals(Password.SHA1_Hasher(password), holder.getFirstClient().getPassword());
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
    @When("a first logistics company searches for name {string}")
    public void a_first_logistics_company_searches_for_name(String name) {
        searchResults = holder.getFirstCompany().searchByName(name);
    }

    @When("a first logistics company searches for email {string}")
    public void a_first_logistics_company_searches_for_email(String email) {
        searchResults = holder.getFirstCompany().searchByEmail(email);
    }

    @Then("it exists and the client {string} with address {string} reference person {string} email {string} and password {string} is returned")
    public void it_exists_and_the_client_with_address_reference_person_email_and_pasword_is_returned(String name,
            String address, String refPerson, String email, String password) {
        assertFalse(searchResults.isEmpty());
        assertNotEquals(null, holder.getFirstClient());
        assertEquals(name, holder.getFirstClient().getName());
        assertEquals(address, holder.getFirstClient().getAddress());
        assertEquals(refPerson, holder.getFirstClient().getRefPerson());
        assertEquals(email, holder.getFirstClient().getEmail());
        assertEquals(Password.SHA1_Hasher(password), holder.getFirstClient().getPassword());
    }

    @Then("it does not exist and no client is returned")
    public void it_does_not_exist_and_no_client_is_returned() {
        assertTrue(searchResults.isEmpty());
    }

//////////////////M1:3////////////////////////////////////////////////
    @When("a client enters new client info {string} with address {string} reference person {string} email {string} and password {string}")
    public void a_client_enters_new_client_info_with_address_reference_person_email_and_password(String name,
            String address, String refPerson, String email, String password) {
        holder.getFirstClient().updateName(name);
        holder.getFirstClient().updateAddress(address);
        holder.getFirstClient().updateRefPerson(refPerson);
        holder.getFirstClient().updateEmail(email);
        holder.getFirstClient().updatePassword(password);
    }

    @Then("the client {string} with address {string} reference person {string} email {string} and password {string} is successfully updated")
    public void the_client_with_address_reference_person_email_and_password_is_successfully_updated(String name,
            String address, String refPerson, String email, String password) {
        assertNotEquals(null, holder.getFirstClient());
        assertEquals(name, holder.getFirstClient().getName());
        assertEquals(address, holder.getFirstClient().getAddress());
        assertEquals(refPerson, holder.getFirstClient().getRefPerson());
        assertEquals(email, holder.getFirstClient().getEmail());
        assertEquals(Password.SHA1_Hasher(password), holder.getFirstClient().getPassword());
    }
}
