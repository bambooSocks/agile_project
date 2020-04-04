package rcm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class M1 {

    private boolean successfulEntry = false;
    private boolean successfulUpdate = false;
    private boolean searchResults = false;

    private SharedObjectHolder holder;

    public M1(SharedObjectHolder holder) {
        this.holder = holder;
    }

    @When("the first logistics company enters client data")
    public void the_first_logistics_company_enters_client_data() {
        // maybe change the step to first logistics company adds a client
        successfulEntry = holder.getFirstCompany().addClient(holder.getFirstClient());
    }

    @When("the first client enters client data")
    public void the_first_client_enters_client_data() {
        // -------------------------------
        // The step says that first client enters client data and instead you add a client to first company
        // What is going on here?
        // -------------------------------
//        ask where this needs to be
        // why is an Exception here if the function doesn't throw one?
        // if you wanna throw exception then do it in addClient method
        // otherwise look at this
        // https://stackoverflow.com/questions/17272161/cucumber-jvm-test-if-the-correct-exception-is-thrown
        try {
            holder.getFirstCompany().addClient(holder.getFirstClient());
        } catch (Exception e) {
            successfulEntry = false;
        }
    }

    @When("a first logistics company searches for parameter {string} in client profile")
    public void a_first_logistics_company_searches_for_parameter_in_client_profile(String searchParam) {
        // as we discussed consider changing this to some other method
        // I will show some suggestions for this during the meeting
        searchResults = holder.getFirstCompany().searchClient(searchParam);
    }

    @When("parameter {string} exists in client profile")
    public void parameter_exists_in_client_profile(String searchParam) {
        // this doesn't seem correct you are saving the result of adding a client to a company
        // and calling it search result 
        searchResults = holder.getFirstCompany().addClient(holder.getFirstClient());
        // maybe this assert should be in "then" step
        assertTrue(searchResults);
    }

    @When("parameter {string} does not exist in client profile")
    public void parameter_does_not_exist_in_client_profile(String searchParam) {
//        assertFalse(searchResults);
        // duplicate: looks like a first company searches for parameter in client profile
        // and also should it be "when" step or "then" step?
        searchResults = holder.getFirstCompany().searchClient(searchParam);
    }

    @When("a client enters new client info {string} with address {string} reference person {string} and email {string}")
    public void a_client_enters_new_client_info_with_address_reference_person_and_email(String name, String address,
            String refPerson, String email) {
        // this one seems ok, only a suggestion that the name of the method would be updateInfo or something like that since you run it on client
        successfulUpdate = holder.getFirstClient().updateClient(name, address, refPerson, email);
    }

    @Then("a new client profile is not created")
    public void a_new_client_profile_is_not_created() {
        assertFalse(successfulEntry);
    }

    @Then("the client {string} with address {string} reference person {string} email {string} and ID {int} is returned")
    public void the_client_with_address_reference_person_email_and_ID_is_returned(String name, String address,
            String refPerson, String email, Integer id) {
        // why do you pass all the variables if you don't use them?
        // in this function you assert not equal to null and in next step equal to false
        // also this will never happen (you never return null)
        assertNotEquals(null, searchResults);
    }

    @Then("no client is returned")
    public void no_client_is_returned() {
        assertEquals(false, searchResults);
    }

    @Then("an id is automatically generated")
    public void an_id_is_automatically_generated() {
        // not how you should be getting ID
        assertNotEquals(null, holder.getFirstClient().id);
    }

    @Then("a new client profile is successfully created")
    public void a_new_client_profile_is_successfully_created() {
        assertTrue(successfulEntry);
    }

    @Then("the client profile is successfully updated")
    public void the_client_profile_is_successfully_updated() {
        assertTrue(successfulUpdate);
    }
}
