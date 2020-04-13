package rcm;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class O2 {

    private SharedObjectHolder holder;

    public O2(SharedObjectHolder holder) {
        this.holder = holder;
    }
    
    @When("first client enters a valid password {string}")
    public void first_client_enters_a_valid_password(String password) {
        throw new io.cucumber.java.PendingException();
    }

    @Then("first client is logged in")
    public void first_client_is_logged_in() {
        throw new io.cucumber.java.PendingException();
    }

    @Given("first client {string} is logged-in")
    public void first_client_is_logged_in(String string) {
        throw new io.cucumber.java.PendingException();
    }

    @When("client {string} tries to view {string} containers and data")
    public void client_tries_to_view_containers_and_data(String name1, String name2) {
        throw new io.cucumber.java.PendingException();
    }

    @Then("they can view it")
    public void they_can_view_it() {
        throw new io.cucumber.java.PendingException();
    }

    @Given("second client {string} with address {string} reference person {string} and email {string}")
    public void second_client_with_address_reference_person_and_email(String name, String address, String refPerson,
            String email) {
        throw new io.cucumber.java.PendingException();
    }

    @Then("they can not view it")
    public void they_can_not_view_it() {
        throw new io.cucumber.java.PendingException();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Given("first logistics company {string} is logged-in")
    public void first_logistics_company_is_logged_in(String name) {
        throw new io.cucumber.java.PendingException();
    }

    @When("logistics company {string} tries to view {string} clients, containers, and data")
    public void logistics_company_tries_to_view_clients_containers_and_data(String name1, String name2) {
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
