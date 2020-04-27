package rcm.cucumber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rcm.model.ContainerStatus;

public class M3 {

    private ContainerStatus status;

    private boolean successfulEntry = false;
    private List<ContainerStatus> statusList;

    private SharedObjectHolder holder;

    public M3(SharedObjectHolder holder) {
        this.holder = holder;
    }

    @Given("a container status of {double} degrees, {double} % humidity and {double} bar with timestamp {int}:{int} {int}\\/{int}\\/{int}")
    public void a_container_status_of_degrees_humidity_and_bar_with_timestamp(Double temperature, Double humidity,
            Double airPressure, Integer hours, Integer minutes, Integer day, Integer month, Integer year) {
        LocalDateTime timestamp = LocalDateTime.of(year, month, day, hours, minutes);
        status = new ContainerStatus(timestamp, temperature, humidity, airPressure, "New York");
    }

    @Given("an initial container status in the journey of {double} degrees, {double} % humidity and {double} bar with a timestamp {int}:{int} {int}\\/{int}\\/{int}")
    public void an_initial_container_status_in_the_journey_of_degrees_humidity_and_bar_with_a_timestamp(
            Double temperature, Double humidity, Double airPressure, Integer hours, Integer minutes, Integer day,
            Integer month, Integer year) throws IOException {
        LocalDateTime timestamp = LocalDateTime.of(year, month, day, hours, minutes);
        status = new ContainerStatus(timestamp, temperature, humidity, airPressure, "New York");
        successfulEntry = holder.getApp().enterNewContainerStatus(holder.getFirstJourney(), status);
        assertTrue(holder.getFirstJourney().containsStatus(status));
    }

    @When("the first logistics company enters the given container status")
    public void the_first_logistics_company_enters_the_given_container_status() throws IOException {
        successfulEntry = holder.getApp().enterNewContainerStatus(holder.getFirstJourney(), status);
    }

    @When("the second logistics company enters the given container status")
    public void the_second_logistics_company_enters_the_given_container_status() throws IOException {
        successfulEntry = holder.getApp().enterNewContainerStatus(holder.getFirstJourney(), status);
    }

    @When("the first client requests access to the status")
    public void the_first_client_requests_access_to_the_status() {
        statusList = holder.getApp().requestStatus(holder.getFirstJourney());
    }

    @When("the second client requests access to the status")
    public void the_second_client_requests_access_to_the_status() {
        statusList = holder.getApp().requestStatus(holder.getFirstJourney());
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

    @Then("a list of statuses contains a status of {double} degrees, {double} % humidity and {double} bar with a timestamp {int}:{int} {int}\\/{int}\\/{int}")
    public void a_list_of_statuses_contains_a_status_of_degrees_humidity_and_bar_with_a_timestamp(Double temperature,
            Double humidity, Double airPressure, Integer hours, Integer minutes, Integer day, Integer month,
            Integer year) {
        LocalDateTime timestamp = LocalDateTime.of(year, month, day, hours, minutes);
        ContainerStatus _status = new ContainerStatus(timestamp, temperature, humidity, airPressure,"New York");
        assertTrue(statusList.contains(_status));
    }

}
