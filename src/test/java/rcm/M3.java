package rcm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class M3 {

    private ContainerStatus status;

    private boolean successfulEntry = false;
    private List<ContainerStatus> statusList;

    private SharedObjectHolder holder;

    public M3(SharedObjectHolder holder) {
        this.holder = holder;
    }

    @Given("a container status of {double} degrees, {double} % humidity and {double} bar")
    public void a_container_status_of_degrees_humidity_and_bar(Double temperature, Double humidity,
            Double airPressure) {
        status = new ContainerStatus(temperature, humidity, airPressure);
    }

    @Given("an initial container status in the journey of {double} degrees, {double} % humidity and {double} bar")
    public void an_initial_container_status_in_the_journey_of_degrees_humidity_and_bar(Double temperature,
            Double humidity, Double airPressure) {
        status = new ContainerStatus(temperature, humidity, airPressure);
        LogisticsCompany company = holder.getFirstJourney().getCompany();
        successfulEntry = company.enterStatus(status, holder.getFirstJourney());
        assertTrue(holder.getFirstJourney().containsStatus(status));
    }

    @When("the first logistics company enters the given container status")
    public void the_first_logistics_company_enters_the_given_container_status() {
        successfulEntry = holder.getFirstCompany().enterStatus(status, holder.getFirstJourney());
    }

    @When("the second logistics company enters the given container status")
    public void the_second_logistics_company_enters_the_given_container_status() {
        successfulEntry = holder.getSecondCompany().enterStatus(status, holder.getFirstJourney());
    }

    @When("the first client requests access to the status")
    public void the_first_client_requests_access_to_the_status() {
        statusList = holder.getFirstClient().requestStatus(holder.getFirstJourney());
    }

    @When("the second client requests access to the status")
    public void the_second_client_requests_access_to_the_status() {
        statusList = holder.getSecondClient().requestStatus(holder.getFirstJourney());
    }

    @Then("the journey contains the given status")
    public void the_journey_contains_the_given_status() {
        assertTrue(holder.getFirstJourney().containsStatus(status));
    }

    @Then("the journey does not contain the given status")
    public void the_journey_does_not_contain_the_given_status() {
        assertFalse(holder.getFirstJourney().containsStatus(status));
    }

    @Then("the journey is successfully updated")
    public void the_journey_is_successfully_updated() {
        assertTrue(successfulEntry);
    }

    @Then("the journey has failed to update")
    public void the_journey_has_failed_to_update() {
        assertFalse(successfulEntry);
    }

    @Then("a list of statuses is returned")
    public void a_list_of_statuses_is_returned() {
        assertNotEquals(null, statusList);
    }

    @Then("a list of statuses is not returned")
    public void a_list_of_statuses_is_not_returned() {
        assertEquals(null, statusList);
    }

    @Then("a list of statuses contains a status of {double} degrees, {double} % humidity and {double} bar")
    public void a_list_of_statuses_contains_a_status_of_degrees_humidity_and_bar(Double temperature, Double humidity,
            Double airPressure) {
        ContainerStatus _status = new ContainerStatus(temperature, humidity, airPressure);
        assertTrue(statusList.contains(_status));
    }
    
    @Then("the status will fail to be entered")
    public void the_status_will_fail_to_be_entered() {
        assertFalse(successfulEntry);
    }

}
