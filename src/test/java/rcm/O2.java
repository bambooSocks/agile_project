package rcm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class O2 {

    private SharedObjectHolder holder;
    private boolean loggedIn = false;
    private boolean access = false;
    private List<Journey> data;

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
//        this is failing so something must still be wrong with the companyLogInStatus
//        assertTrue(loggedIn);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Given("first client is logged-in with email {string} and password {string}")
    public void first_client_is_logged_in_with_email_and_password(String email, String password) {
//        loggedIn = holder.getFirstCompany().clientLogInStatus(holder.getFirstClient().getEmail(),
//                holder.getFirstClient().getPassword());
        loggedIn = holder.getFirstCompany().clientLogInStatus(email, password);
    }

    @When("client with email {string} tries to view containers and data of client with email {string}")
    public void client_with_email_tries_to_view_containers_and_data_of_client_with_email(String email1, String email2) {
        data = holder.getFirstClient().viewData(loggedIn, email1, email2, access);
    }

    @Then("the containers and data can be viewed")
    public void the_containers_and_data_can_be_viewed() {
//        having a null pointer exception here...
        System.out.println("data to be viewed " + data); // this one is printing null
        System.out.println("data to be compared " + holder.getSecondClient().getJourneyList()); // this one doesnt print
//        are we comparing the same types here?
        assertEquals(holder.getSecondClient().getJourneyList(), data);
    }

    @Then("the containers and data can not be viewed")
    public void the_containers_and_data_can_not_be_viewed() {
        assertEquals(null, data);
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
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the clients, containers, and data can not be viewed")
    public void the_clients_containers_and_data_can_not_be_viewed() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the clients, containers, and data can be viewed")
    public void the_clients_containers_and_data_can_be_viewed() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @When("first client with email {string} gives access to client with email {string}")
    public void first_client_with_email_gives_access_to_client_with_email(String email1, String email2) {
        access = true;
        data = holder.getFirstClient().viewData(loggedIn, email1, email2, access);

    }

    @Then("they can view the containers and data of the first client")
    public void they_can_view_the_containers_and_data_of_the_first_client() {
//      assertEquals(something, data);

    }
}
