package rcm.cucumber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
    private List<Journey> journeys;
    private boolean sharedJourney;
    private Set<Client> clients;

    public O2(SharedObjectHolder holder) {
        this.holder = holder;
    }

    @Given("user is logged out")
    public void user_is_logged_out() {
        holder.getApp().logOut();
    }

    @When("first client enters email {string} and password {string}")
    public void first_client_enters_email_and_password(String email, String password) {
        try {
            holder.getApp().logInUser(email, password);
        } catch (WrongInputException e) {
            System.err.println(e.getMessage());
        }
    }

    @Then("the client is logged in")
    public void the_client_is_logged_in() {
        assertEquals(holder.getFirstClient(), holder.getApp().getLoggedInClient());
    }

    @Then("the client is not logged in")
    public void the_client_is_not_logged_in() {
        assertNotEquals(holder.getFirstClient(), holder.getApp().getLoggedInClient());
    }

    @When("first logistics company enters email {string} and password {string}")
    public void first_logistics_company_enters_email_and_password(String email, String password) {
        try {
            holder.getApp().logInUser(email, password);
        } catch (WrongInputException e) {
            System.err.println(e.getMessage());
        }
    }

    @Then("the company is logged in")
    public void the_company_is_logged_in() {
        assertNotEquals(null, holder.getApp().getLoggedInCompany());
    }

    @Then("the company is not logged in")
    public void the_company_is_not_logged_in() {
        assertEquals(null, holder.getApp().getLoggedInCompany());
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @When("first client shares journey with second client")
    public void first_client_shares_journey_with_second_client() {
        sharedJourney = holder.getFirstClient().shareJourney(holder.getSecondClient(), holder.getFirstJourney());
    }

    @Then("second client can view the journey of the first client")
    public void second_client_can_view_the_journey_of_the_first_client() {
        assertTrue(sharedJourney);
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @When("first logistics company views its client list of {string} and {string}")
    public void first_logistics_company_views_its_client_list_of_and(String name1, String name2) {
        clients = holder.getFirstCompany().searchClientByName(name1);
        Set<Client> client2 = holder.getFirstCompany().searchClientByName(name2);  
        clients.addAll(client2);
    }

    @Then("it can see both clients and their data")
    public void it_can_see_both_clients_and_their_data() {
        assertEquals(holder.getFirstCompany().getClients(), clients);
    }
}
