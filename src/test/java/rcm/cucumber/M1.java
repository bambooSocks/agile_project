package rcm.cucumber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Set;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rcm.model.Client;
import rcm.model.User;
import rcm.model.WrongInputException;

public class M1 {

    private Set<Client> searchResults;
    private SharedObjectHolder holder;

    public M1(SharedObjectHolder holder) {
        this.holder = holder;
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
        assertEquals(User.SHA1_Hasher(password), holder.getFirstClient().getPassword());
    }

    @Then("the information is not valid and the client is not created")
    public void the_information_is_not_valid_and_the_client_is_not_created() {
        assertEquals(null, holder.getFirstClient());
    }

///////////////////M1:2//////////////////////////////////////////
    @When("a first logistics company searches for name {string}")
    public void a_first_logistics_company_searches_for_name(String name) {
        searchResults = holder.getFirstCompany().searchClientByName(name);
    }

    @When("a first logistics company searches for address {string}")
    public void a_first_logistics_company_searches_for_address(String address) {
        searchResults = holder.getFirstCompany().searchClientByAddress(address);
    }

    @When("a first logistics company searches for reference person {string}")
    public void a_first_logistics_company_searches_for_reference_person(String refPerson) {
        searchResults = holder.getFirstCompany().searchClientByRefPerson(refPerson);
    }

    @When("a first logistics company searches for email {string}")
    public void a_first_logistics_company_searches_for_email(String email) {
        searchResults = holder.getFirstCompany().searchClientByEmail(email);
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
        assertEquals(User.SHA1_Hasher(password), holder.getFirstClient().getPassword());
    }

    @Then("it does not exist and no client is returned")
    public void it_does_not_exist_and_no_client_is_returned() {
        assertTrue(searchResults.isEmpty());
    }

//////////////////M1:3////////////////////////////////////////////////
    @When("a client enters new client info {string} with address {string} reference person {string} email {string} and password {string}")
    public void a_client_enters_new_client_info_with_address_reference_person_email_and_password(String name,
            String address, String refPerson, String email, String password) {
        try {
            holder.getFirstClient().updateName(name);
        } catch (WrongInputException e) {
            System.err.println(e.getMessage());
        }
        try {
            holder.getFirstClient().updateAddress(address);
        } catch (WrongInputException e) {
            System.err.println(e.getMessage());
        }
        try {
            holder.getFirstClient().updateRefPerson(refPerson);
        } catch (WrongInputException e) {
            System.err.println(e.getMessage());
        }
        try {
            holder.getFirstClient().updateEmail(email);
        } catch (WrongInputException e) {
            System.err.println(e.getMessage());
        }
        try {
            holder.getFirstClient().updatePassword(password);
        } catch (WrongInputException e) {
            System.err.println(e.getMessage());
        }
    }

    @Then("the client {string} with address {string} reference person {string} email {string} and password {string} is successfully updated")
    public void the_client_with_address_reference_person_email_and_password_is_successfully_updated(String name,
            String address, String refPerson, String email, String password) {
        assertNotEquals(null, holder.getFirstClient());
        assertEquals(name, holder.getFirstClient().getName());
        assertEquals(address, holder.getFirstClient().getAddress());
        assertEquals(refPerson, holder.getFirstClient().getRefPerson());
        assertEquals(email, holder.getFirstClient().getEmail());
        assertEquals(User.SHA1_Hasher(password), holder.getFirstClient().getPassword());
    }

    @Then("the client {string} with address {string} reference person {string} email {string} and password {string} is not updated")
    public void the_client_with_address_reference_person_email_and_password_is_not_updated(String name, String address,
            String refPerson, String email, String password) {
        assertNotEquals(null, holder.getFirstClient());
        assertNotEquals(name, holder.getFirstClient().getName());
        assertNotEquals(address, holder.getFirstClient().getAddress());
        assertNotEquals(refPerson, holder.getFirstClient().getRefPerson());
        assertNotEquals(email, holder.getFirstClient().getEmail());
        assertNotEquals(User.SHA1_Hasher(password), holder.getFirstClient().getPassword());
    }

    @When("a logistics company enters new info {string} with address {string} reference person {string} email {string} and password {string}")
    public void a_logistics_company_enters_new_info_with_address_reference_person_email_and_password(String name,
            String address, String refPerson, String email, String password) {
        try {
            holder.getFirstCompany().updateName(name);
        } catch (WrongInputException e) {
            System.err.println(e.getMessage());
        }
        try {
            holder.getFirstCompany().updateAddress(address);
        } catch (WrongInputException e) {
            System.err.println(e.getMessage());
        }
        try {
            holder.getFirstCompany().updateRefPerson(refPerson);
        } catch (WrongInputException e) {
            System.err.println(e.getMessage());
        }
        try {
            holder.getFirstCompany().updateEmail(email);
        } catch (WrongInputException e) {
            System.err.println(e.getMessage());
        }
        try {
            holder.getFirstCompany().updatePassword(password);
        } catch (WrongInputException e) {
            System.err.println(e.getMessage());
        }
    }

    @Then("the logistics company {string} with address {string} reference person {string} email {string} and password {string} is successfully updated")
    public void the_logistics_company_with_address_reference_person_email_and_password_is_successfully_updated(
            String name, String address, String refPerson, String email, String password) {
        assertNotEquals(null, holder.getFirstCompany());
        assertEquals(name, holder.getFirstCompany().getName());
        assertEquals(address, holder.getFirstCompany().getAddress());
        assertEquals(refPerson, holder.getFirstCompany().getRefPerson());
        assertEquals(email, holder.getFirstCompany().getEmail());
        assertEquals(User.SHA1_Hasher(password), holder.getFirstCompany().getPassword());
    }

    @Then("the logistics company {string} with address {string} reference person {string} email {string} and password {string} is not updated")
    public void the_logistics_company_with_address_reference_person_email_and_password_is_not_updated(String name,
            String address, String refPerson, String email, String password) {
        assertNotEquals(null, holder.getFirstCompany());
        assertNotEquals(name, holder.getFirstCompany().getName());
        assertNotEquals(address, holder.getFirstCompany().getAddress());
        assertNotEquals(refPerson, holder.getFirstCompany().getRefPerson());
        assertNotEquals(email, holder.getFirstCompany().getEmail());
        assertNotEquals(User.SHA1_Hasher(password), holder.getFirstCompany().getPassword());
    }
}
