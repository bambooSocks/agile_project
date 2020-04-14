package rcm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class O2 {

    private SharedObjectHolder holder;
    private boolean loggedIn = false;
    private LinkedList<String> data;

    public O2(SharedObjectHolder holder) {
        this.holder = holder;
    }

    @When("first client enters password {string}")
    public void first_client_enters_password(String password) {
        loggedIn = holder.getFirstCompany().logInStatus(holder.getFirstClient().getName(), password);
    }

    @Then("the client is logged in")
    public void the_client_is_logged_in() {
        assertTrue(loggedIn);
    }

    @Given("first client is logged-in")
    public void first_client_is_logged_in() {
        loggedIn = holder.getFirstCompany().logInStatus(holder.getFirstClient().getName(),
                holder.getFirstClient().getPassword());
    }

    @When("client {string} tries to view {string} containers and data")
    public void client_tries_to_view_containers_and_data(String name1, String name2) {
        data = holder.getFirstClient().viewData(loggedIn, name1, name2);
    }

    @Then("they can view {string} containers and data")
    public void they_can_view_containers_and_data(String name2) {
//        assertEquals(name2.getJourneyList(), data);
//        not an assert true but maybe return the data?
    }

    @Then("they can not view {string} containers and data")
    public void they_can_not_view_containers_and_data(String name2) {
//        if true and name1 != name2 then do not show data
        throw new io.cucumber.java.PendingException();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Given("first logistics company is logged-in")
    public void first_logistics_company_is_logged_in() {
//        a boolean for log-in status?
        throw new io.cucumber.java.PendingException();
    }

    @When("logistics company {string} tries to view {string} clients, containers, and data")
    public void logistics_company_tries_to_view_clients_containers_and_data(String name1, String name2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("they can not view {string} clients, containers, and data")
    public void they_can_not_view_clients_containers_and_data(String name2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("they can view {string} clients, containers, and data")
    public void they_can_view_clients_containers_and_data(String name2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @When("client {string} gives access to client {string}")
    public void client_gives_access_to_client(String name1, String name2) {
        throw new io.cucumber.java.PendingException();
    }

    @Then("client {string} can view {string} clients, containers, and data")
    public void client_can_view_clients_containers_and_data(String name1, String name2) {
        throw new io.cucumber.java.PendingException();
    }
}
