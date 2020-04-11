package rcm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class M3 {

    private ContainerStatus status;

    private boolean successfulEntry = false;
    private boolean successfulJourneyStart = false;
    private List<ContainerStatus> statusList;

    private SharedObjectHolder holder;

    public M3(SharedObjectHolder holder) {
        this.holder = holder;
    }

    @Given("a container status of {double} degrees, {double} % humidity and {double} bar with timestamp {int}:{int} {int}\\/{int}\\/{int}")
    public void a_container_status_of_degrees_humidity_and_bar_with_timestamp(Double temperature, Double humidity,
            Double airPressure, Integer hours, Integer minutes, Integer day, Integer month, Integer year) {
        LocalDateTime timestamp = LocalDateTime.of(year, month, day, hours, minutes);
        status = new ContainerStatus(timestamp, temperature, humidity, airPressure);
    }

    @Given("an initial container status in the journey of {double} degrees, {double} % humidity and {double} bar with a timestamp {int}:{int} {int}\\/{int}\\/{int}")
    public void an_initial_container_status_in_the_journey_of_degrees_humidity_and_bar_with_a_timestamp(
            Double temperature, Double humidity, Double airPressure, Integer hours, Integer minutes, Integer day,
            Integer month, Integer year) {
        LocalDateTime timestamp = LocalDateTime.of(year, month, day, hours, minutes);
        status = new ContainerStatus(timestamp, temperature, humidity, airPressure);
        LogisticsCompany company = holder.getFirstJourney().getCompany();
        successfulEntry = company.enterStatus(status, holder.getFirstJourney());
        assertTrue(holder.getFirstJourney().containsStatus(status));
    }

    @Given("the journey has started at {int}:{int} {int}\\/{int}\\/{int}")
    public void the_journey_has_started_at(Integer hours, Integer minutes, Integer day, Integer month, Integer year) {
        LocalDateTime timestamp = LocalDateTime.of(year, month, day, hours, minutes);
        holder.getFirstCompany().startJourney(holder.getFirstJourney(), timestamp);
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

    @When("the logistics company starts a journey with a timestamp {int}:{int} {int}\\/{int}\\/{int}")
    public void the_logistics_company_starts_a_journey_with_a_timestamp(Integer hours, Integer minutes, Integer day,
            Integer month, Integer year) {
        LocalDateTime timestamp = LocalDateTime.of(year, month, day, hours, minutes);
        successfulJourneyStart = holder.getFirstCompany().startJourney(holder.getFirstJourney(), timestamp);
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
        ContainerStatus _status = new ContainerStatus(timestamp, temperature, humidity, airPressure);
        assertTrue(statusList.contains(_status));
    }

    @Then("the container is allocated")
    public void the_container_is_allocated() {
        assertTrue(holder.getFirstCompany().isAllocated(holder.getContainer()));
    }

    @Then("the journey is started")
    public void the_journey_is_started() {
        assertTrue(successfulJourneyStart);
        assertTrue(holder.getFirstJourney().isStarted());
    }

    @Then("the journey failed to start")
    public void the_journey_failed_to_start() {
        assertFalse(successfulJourneyStart);
    }

    @Then("the starting timestamp of the journey is {int}:{int} {int}\\/{int}\\/{int}")
    public void the_starting_timestamp_of_the_journey_is(Integer hours, Integer minutes, Integer day, Integer month,
            Integer year) {
        Calendar timestamp = new GregorianCalendar(year, month, day, hours, minutes);
        assertEquals(timestamp, holder.getFirstJourney().getStartTimestamp());
    }

    @Then("the logistics company succesfully adds a container status with a timestamp {int}:{int} {int}\\/{int}\\/{int}")
    public void the_logistics_company_succesfully_adds_a_container_status_with_a_timestamp(Integer hours,
            Integer minutes, Integer day, Integer month, Integer year) {
        LocalDateTime timestamp = LocalDateTime.of(year, month, day, hours, minutes);
        ContainerStatus status = new ContainerStatus(timestamp, 5.0, 80.0, 1.01);
        assertFalse(holder.getFirstCompany().enterStatus(status, holder.getFirstJourney()));

    }

    @Then("the logistics company fails to add a container status with a timestamp {int}:{int} {int}\\/{int}\\/{int}")
    public void the_logistics_company_fails_to_add_a_container_status_with_a_timestamp(Integer hours, Integer minutes,
            Integer day, Integer month, Integer year) {
        LocalDateTime timestamp = LocalDateTime.of(year, month, day, hours, minutes);
        ContainerStatus status = new ContainerStatus(timestamp, 5.0, 80.0, 1.01);
        assertFalse(holder.getFirstCompany().enterStatus(status, holder.getFirstJourney()));
    }
}
