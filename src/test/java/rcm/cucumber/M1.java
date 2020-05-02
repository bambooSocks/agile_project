package rcm.cucumber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Set;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rcm.model.Client;
import rcm.model.User;
import rcm.model.WrongInputException;

public class M1 {

    private Set<Client> searchResults;
    private SharedObjectHolder holder;
    private Client client;
    private boolean failed = false;

    public M1(SharedObjectHolder holder) {
        this.holder = holder;
    }

    @Given("a client with email {string}")
    public void a_client_with_email(String email) throws IOException, WrongInputException {
        holder.getApp().createNewClient("Dole", "Miami, FL", "Banana Man", email, "Object123");
    }

    ////////////// M1:1////////////////////////////////////////////////
    @When("the company creates a first client {string} with address {string} reference person {string} email {string} and password {string}")
    public void the_company_creates_a_first_client_with_address_reference_person_email_and_password(String name,
            String address, String refPerson, String email, String password) throws IOException {
        try {
            Client client = holder.getApp().createNewClient(name, address, refPerson, email, password);
            holder.setFirstClient(client);
        } catch (IOException | WrongInputException e) {
            System.err.println(e.getMessage());
        }
    }

    @Then("a new client {string} with address {string} reference person {string} email {string} and password {string} belongs to the company")
    public void a_new_client_with_address_reference_person_email_and_password_belongs_to_the_company(String name,
            String address, String refPerson, String email, String password) {
        assertNotEquals(null, holder.getFirstClient());
        assertEquals(name, holder.getFirstClient().getName());
        assertEquals(address, holder.getFirstClient().getAddress());
        assertEquals(refPerson, holder.getFirstClient().getRefPerson());
        assertEquals(email, holder.getFirstClient().getEmail());
        assertEquals(User.SHA1_Hasher(password), holder.getFirstClient().getPassword());
    }

    @Then("the information is not valid and the client is not created")
    public void the_information_is_not_valid_and_the_client_is_not_created() {
        assertFalse(holder.getApp().requestClients().contains(client));
    }

///////////////////M1:2//////////////////////////////////////////
    @When("a logistics company searches for name {string}")
    public void a_logistics_company_searches_for_name(String name) {
        searchResults = holder.getFirstCompany().searchClientByName(name);
    }

    @When("a logistics company searches for address {string}")
    public void a_logistics_company_searches_for_address(String address) {
        searchResults = holder.getFirstCompany().searchClientByAddress(address);
    }

    @When("a logistics company searches for reference person {string}")
    public void a_logistics_company_searches_for_reference_person(String refPerson) {
        searchResults = holder.getFirstCompany().searchClientByRefPerson(refPerson);
    }

    @When("a logistics company searches for email {string}")
    public void a_logistics_company_searches_for_email(String email) {
        searchResults = holder.getFirstCompany().searchClientByEmail(email);
    }

    @Then("the client of the logistics company is returned")
    public void the_client_of_the_logistics_company_is_returned() {
        assertFalse(searchResults.isEmpty());
        assertTrue(searchResults.contains(holder.getFirstClient()));
    }

    @Then("no client is returned")
    public void no_client_is_returned() {
        assertTrue(searchResults.isEmpty());
    }

//////////////////M1:3////////////////////////////////////////////////
    @When("a client enters new client info {string} with address {string} reference person {string} email {string} and password {string}")
    public void a_client_enters_new_client_info_with_address_reference_person_email_and_password(String name,
            String address, String refPerson, String email, String password) {
        failed = false;
        try {
            holder.getFirstClient().updateName(name);
            holder.getFirstClient().updateAddress(address);
            holder.getFirstClient().updateRefPerson(refPerson);
            holder.getFirstClient().updateEmail(email);
            holder.getFirstClient().updatePassword(password);
        } catch (WrongInputException e) {
            System.err.println(e.getMessage());
            failed = true;
        }
    }

    @Then("the new client data is {string} with address {string} reference person {string} email {string} and password {string}")
    public void the_new_client_data_is_with_address_reference_person_email_and_password(String name, String address,
            String refPerson, String email, String password) {
        assertFalse(failed);
        assertNotEquals(null, holder.getFirstClient());
        assertEquals(name, holder.getFirstClient().getName());
        assertEquals(address, holder.getFirstClient().getAddress());
        assertEquals(refPerson, holder.getFirstClient().getRefPerson());
        assertEquals(email, holder.getFirstClient().getEmail());
        assertEquals(User.SHA1_Hasher(password), holder.getFirstClient().getPassword());
    }

    @Then("the client data has not been updated")
    public void the_client_data_has_not_been_updated() {
        assertTrue(failed);
    }

    @When("a logistics company enters new info {string} with address {string} reference person {string} email {string} and password {string}")
    public void a_logistics_company_enters_new_info_with_address_reference_person_email_and_password(String name,
            String address, String refPerson, String email, String password) {
        failed = false;
        try {
            holder.getFirstCompany().updateName(name);
            holder.getFirstCompany().updateAddress(address);
            holder.getFirstCompany().updateRefPerson(refPerson);
            holder.getFirstCompany().updateEmail(email);
            holder.getFirstCompany().updatePassword(password);
        } catch (WrongInputException e) {
            System.err.println(e.getMessage());
            failed = true;
        }
    }

    @Then("the new logistics company data is {string} with address {string} reference person {string} email {string} and password {string}")
    public void the_new_logistics_company_data_is_with_address_reference_person_email_and_password(String name,
            String address, String refPerson, String email, String password) {
        assertFalse(failed);
        assertNotEquals(null, holder.getFirstCompany());
        assertEquals(name, holder.getFirstCompany().getName());
        assertEquals(address, holder.getFirstCompany().getAddress());
        assertEquals(refPerson, holder.getFirstCompany().getRefPerson());
        assertEquals(email, holder.getFirstCompany().getEmail());
        assertEquals(User.SHA1_Hasher(password), holder.getFirstCompany().getPassword());
    }

    @Then("the logistics company data has not been updated")
    public void the_logistics_company_data_has_not_been_updated() {
        assertTrue(failed);
    }
}
