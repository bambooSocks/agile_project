package rcm.cucumber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rcm.model.Client;
import rcm.model.Journey;
import rcm.model.WrongInputException;

public class O2 {

    private SharedObjectHolder holder;
    private boolean loggedIn = false;
    private boolean access = false;
    private List<Journey> journeys;
    private Set<Client> clients;

    public O2(SharedObjectHolder holder) {
        this.holder = holder;
    }

    @When("first client enters email {string} and password {string}")
    public void first_client_enters_email_and_password(String email, String password) {
        loggedIn = holder.getFirstCompany().clientLogInStatus(email, password);
    }

    @Then("the client is logged in")
    public void the_client_is_logged_in() {
        assertTrue(loggedIn);
    }

    @Then("the client is not logged in")
    public void the_client_is_not_logged_in() {
        assertFalse(loggedIn);
    }

    @When("first logistics company enters email {string} and password {string}")
    public void first_logistics_company_enters_email_and_password(String email, String password) {
        try {
            loggedIn = holder.getFirstCompany().companyLogInStatus(email, password);
        } catch (WrongInputException e) {
            System.err.println(e.getMessage());
        }
    }

    @Then("the company is logged in")
    public void the_company_is_logged_in() {
        assertTrue(loggedIn);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Given("first client is logged-in with email {string} and password {string}")
    public void first_client_is_logged_in_with_email_and_password(String email, String password) {
        loggedIn = holder.getFirstCompany().clientLogInStatus(email, password);
    }

    @Given("a first journey of second client with origin port of {string} destination port of {string} and a content of {string}")
    public void a_first_journey_of_second_client_with_origin_port_of_destination_port_of_and_a_content_of(
            String originPort, String destinationPort, String content) {
        holder.setFirstJourney(
                holder.getFirstCompany().createJourney(holder.getSecondClient(), originPort, destinationPort, content));
        assertEquals(holder.getSecondClient(), holder.getFirstJourney().getClient());
        assertEquals(originPort, holder.getFirstJourney().getOriginPort());
        assertEquals(destinationPort, holder.getFirstJourney().getDestinationPort());
        assertEquals(content, holder.getFirstJourney().getContent());
    }

    @When("client with email {string} tries to view containers and data of client with email {string}")
    public void client_with_email_tries_to_view_containers_and_data_of_client_with_email(String email1, String email2) {
        journeys = holder.getFirstClient().viewClientData(loggedIn, email1, email2, access);
    }

    @Then("the containers and data can be viewed")
    public void the_containers_and_data_can_be_viewed() {
        assertEquals(holder.getFirstClient().getJourneyList(), journeys);
    }

    @Then("the containers and data can not be viewed")
    public void the_containers_and_data_can_not_be_viewed() {
        assertEquals(null, journeys);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Given("first logistics company is logged-in with email {string} and password {string}")
    public void first_logistics_company_is_logged_in_with_email_and_password(String email, String password) {
        try {
            loggedIn = holder.getFirstCompany().companyLogInStatus(email, password);
        } catch (WrongInputException e) {
            System.err.println(e.getMessage());
        }
    }

    @When("logistics company with email {string} tries to view clients, containers, and data of logistics company with email {string}")
    public void logistics_company_with_email_tries_to_view_clients_containers_and_data_of_logistics_company_with_email(
            String email1, String email2) {
        clients = holder.getFirstCompany().viewCompanyData(loggedIn, email1, email2);
    }

    @Then("the clients, containers, and data can not be viewed")
    public void the_clients_containers_and_data_can_not_be_viewed() {
        assertEquals(null, clients);
    }

    @Then("the clients, containers, and data can be viewed")
    public void the_clients_containers_and_data_can_be_viewed() {
        assertEquals(holder.getFirstCompany().getClients(), clients);
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @When("first client with email {string} gives access to client with email {string}")
    public void first_client_with_email_gives_access_to_client_with_email(String email1, String email2) {
        access = true;
        journeys = holder.getSecondClient().viewClientData(loggedIn, email1, email2, access);
    }

    @Then("they can view the containers and data of the first client")
    public void they_can_view_the_containers_and_data_of_the_first_client() {
        assertEquals(holder.getSecondClient().getJourneyList(), journeys);
    }
}
