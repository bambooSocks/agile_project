package rcm.cucumber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rcm.model.Journey;
import rcm.model.WrongInputException;

public class O2 {

    private SharedObjectHolder holder;
    private List<Journey> journeys;
    private boolean sharedJourney;

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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    @When("client with email {string} tries to view containers and data of client with email {string}")
    public void client_with_email_tries_to_view_containers_and_data_of_client_with_email(String email1, String email2) {
        journeys = holder.getFirstClient().viewClientData(email1, email2);
    }

    @Then("the containers and data can be viewed")
    public void the_containers_and_data_can_be_viewed() {
        assertEquals(holder.getFirstClient().getJourneyList(), journeys);
    }

    @Then("the containers and data can not be viewed")
    public void the_containers_and_data_can_not_be_viewed() {
        assertEquals(null, journeys);
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
}
