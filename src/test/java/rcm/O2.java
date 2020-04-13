package rcm;

import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class O2 {

    private SharedObjectHolder holder;
    private String newHashKey;

    public O2(SharedObjectHolder holder) {
        this.holder = holder;
    }

    @When("first client enters a valid password {string}")
    public void first_client_enters_a_valid_password(String password) {
        newHashKey = Password.SHA1_Hasher(password);
    }

    @Then("the password matches the hashKey {string} and the client is logged in")
    public void the_password_matches_the_hashKey_and_the_client_is_logged_in(String key) {
        assertEquals(key, newHashKey);
    }

    @When("first client {string} is logged-in")
    public void first_client_is_logged_in(String string) {
//      a boolean for log-in status?
        throw new io.cucumber.java.PendingException();
    }

    @Then("client {string} can view {string} containers and data")
    public void client_can_view_containers_and_data(String name1, String name2) {
//        if true and name1 = name 2 then show data
        throw new io.cucumber.java.PendingException();
    }

    @Then("client {string} can not view {string} containers and data")
    public void client_can_not_view_containers_and_data(String name1, String name2) {
//        if true and name1 != name2 then do not show data
        throw new io.cucumber.java.PendingException();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @When("first logistics company {string} is logged-in")
    public void first_logistics_company_is_logged_in(String name) {
//        a boolean for log-in status?
        throw new io.cucumber.java.PendingException();
    }

    @Then("logistics company {string} can view {string} clients, containers, and data")
    public void logistics_company_can_view_clients_containers_and_data(String name1, String name2) {
//        if true and name2 belongs to name1 then show data
        throw new io.cucumber.java.PendingException();
    }

    @Then("logistics company {string} can not view {string} clients, containers, and data")
    public void logistics_company_can_not_view_clients_containers_and_data(String name1, String name2) {
//        if true and name2 doesn't belong to name1 then don't show data
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
