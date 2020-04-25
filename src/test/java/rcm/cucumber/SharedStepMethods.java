package rcm.cucumber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import rcm.model.Client;
import rcm.model.LogisticsCompany;
import rcm.model.User;
import rcm.model.WrongInputException;

public class SharedStepMethods {

    private SharedObjectHolder holder;
    private boolean loggedIn = false;

    public SharedStepMethods(SharedObjectHolder holder) {
        this.holder = holder;
    }

    @Given("a first logistics company {string} with address {string} reference person {string} email {string} and password {string}")
    public void a_first_logistics_company_with_address_reference_person_email_and_password(String name, String address,
            String refPerson, String email, String password) {
        try {
            holder.setFirstCompany(new LogisticsCompany(name, address, refPerson, email, password));
            assertEquals(name, holder.getFirstCompany().getName());
            assertEquals(address, holder.getFirstCompany().getAddress());
            assertEquals(refPerson, holder.getFirstCompany().getRefPerson());
            assertEquals(email, holder.getFirstCompany().getEmail());
            assertEquals(User.SHA1_Hasher(password), holder.getFirstCompany().getPassword());
        } catch (WrongInputException e) {
            System.err.println(e.getMessage());
        }
    }

    @Given("first logistics company is logged-in with email {string} and password {string}")
    public void first_logistics_company_is_logged_in_with_email_and_password(String email, String password) {
        try {
            loggedIn = holder.getFirstCompany().logInStatus(email, password);
        } catch (WrongInputException e) {
            System.err.println(e.getMessage());
        }
        assertTrue(loggedIn);
    }

    @Given("a second logistics company {string} with address {string} reference person {string} email {string} and password {string}")
    public void a_second_logistics_company_with_address_reference_person_email_and_password(String name, String address,
            String refPerson, String email, String password) {
        try {
            holder.setSecondCompany(new LogisticsCompany(name, address, refPerson, email, password));
            assertEquals(name, holder.getSecondCompany().getName());
            assertEquals(address, holder.getSecondCompany().getAddress());
            assertEquals(refPerson, holder.getSecondCompany().getRefPerson());
            assertEquals(email, holder.getSecondCompany().getEmail());
            assertEquals(User.SHA1_Hasher(password), holder.getSecondCompany().getPassword());
        } catch (WrongInputException e) {
            System.err.println(e.getMessage());
        }
    }

    @Given("second logistics company is logged-in with email {string} and password {string}")
    public void second_logistics_company_is_logged_in_with_email_and_password(String email, String password) {
        try {
            loggedIn = holder.getSecondCompany().logInStatus(email, password);
        } catch (WrongInputException e) {
            System.err.println(e.getMessage());
        }
        assertTrue(loggedIn);
    }

    @Given("a first client {string} with address {string} reference person {string} email {string} and password {string}")
    public void a_first_client_with_address_reference_person_email_and_password(String name, String address,
            String refPerson, String email, String password) {
        Client client = holder.getFirstCompany().createClient(name, address, refPerson, email, password);
        holder.setFirstClient(client);
        assertEquals(name, holder.getFirstClient().getName());
        assertEquals(address, holder.getFirstClient().getAddress());
        assertEquals(refPerson, holder.getFirstClient().getRefPerson());
        assertEquals(email, holder.getFirstClient().getEmail());
        assertEquals(User.SHA1_Hasher(password), holder.getFirstClient().getPassword());
    }

    @Given("first client is logged-in with email {string} and password {string}")
    public void first_client_is_logged_in_with_email_and_password(String email, String password) {
        try {
            loggedIn = holder.getFirstClient().logInStatus(email, password);
        } catch (WrongInputException e) {
            System.err.println(e.getMessage());
        }
        assertTrue(loggedIn);
    }

    @Given("a second client {string} with address {string} reference person {string} email {string} and password {string}")
    public void a_second_client_with_address_reference_person_email_and_password(String name, String address,
            String refPerson, String email, String password) {
        Client client = holder.getFirstCompany().createClient(name, address, refPerson, email, password);
        holder.setSecondClient(client);
        assertEquals(name, holder.getSecondClient().getName());
        assertEquals(address, holder.getSecondClient().getAddress());
        assertEquals(refPerson, holder.getSecondClient().getRefPerson());
        assertEquals(email, holder.getSecondClient().getEmail());
        assertEquals(User.SHA1_Hasher(password), holder.getSecondClient().getPassword());
    }

    @Given("second client is logged-in with email {string} and password {string}")
    public void second_client_is_logged_in_with_email_and_password(String email, String password) {
        try {
            loggedIn = holder.getSecondClient().logInStatus(email, password);
        } catch (WrongInputException e) {
            System.err.println(e.getMessage());
        }
        assertTrue(loggedIn);
    }

    @Given("a first journey of first client with origin port of {string} destination port of {string} and a content of {string}")
    public void a_first_journey_with_origin_port_of_destination_port_of_and_a_content_of(String originPort,
            String destinationPort, String content) {
        holder.setFirstJourney(
                holder.getFirstCompany().createJourney(holder.getFirstClient(), originPort, destinationPort, content));
        assertEquals(holder.getFirstClient(), holder.getFirstJourney().getClient());
        assertEquals(originPort, holder.getFirstJourney().getOriginPort());
        assertEquals(destinationPort, holder.getFirstJourney().getDestinationPort());
        assertEquals(content, holder.getFirstJourney().getContent());
    }

    @Given("a second journey of first client with origin port of {string} destination port of {string} and a content of {string}")
    public void a_second_journey_with_origin_port_of_destination_port_of_and_a_content_of(String originPort,
            String destinationPort, String content) {
        holder.setSecondJourney(
                holder.getFirstCompany().createJourney(holder.getFirstClient(), originPort, destinationPort, content));
        assertEquals(holder.getFirstClient(), holder.getSecondJourney().getClient());
        assertEquals(originPort, holder.getSecondJourney().getOriginPort());
        assertEquals(destinationPort, holder.getSecondJourney().getDestinationPort());
        assertEquals(content, holder.getSecondJourney().getContent());
    }

    @Given("a container of the first logistics company")
    public void a_container_of_the_first_logistics_company() {
        holder.setFirstContainer(holder.getFirstCompany().createContainer());
    }

}
