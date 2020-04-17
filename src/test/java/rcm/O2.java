package rcm;

import static org.junit.Assert.assertEquals;
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
        loggedIn = holder.getFirstCompany().logInStatus(email, password);
    }

    @Then("the client is logged in")
    public void the_client_is_logged_in() {
        assertTrue(loggedIn);
    }

    @Given("first client is logged-in")
    public void first_client_is_logged_in() {
        loggedIn = holder.getFirstCompany().logInStatus(holder.getFirstClient().getEmail(),
                holder.getFirstClient().getPassword());
    }

    @When("client with email {string} tries to view containers and data of client with email {string}")
    public void client_with_email_tries_to_view_containers_and_data_of_client_with_email(String email1, String email2) {
        data = holder.getFirstClient().viewData(loggedIn, email1, email2, access);
    }

    @Then("the containers and data can be viewed")
    public void the_containers_and_data_can_be_viewed() {
//        assertEquals(something, data);
    }

    @Then("the containers and data can not be viewed")
    public void the_containers_and_data_can_not_be_viewed() {
//      assertEquals(something, data);
        throw new io.cucumber.java.PendingException();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Given("first logistics company is logged-in")
    public void first_logistics_company_is_logged_in() {
//        a boolean for log-in status?
        throw new io.cucumber.java.PendingException();
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
