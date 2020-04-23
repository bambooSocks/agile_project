package rcm.cucumber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rcm.model.ContainerStatus;

public class O1 {
    private SharedObjectHolder holder;
    private boolean successfulJourneyStart = false;
    private boolean successfulJourneyEnd = false;

    public O1(SharedObjectHolder holder) {
        this.holder = holder;
    }

    @Given("another container of first logistics company")
    public void another_container_of_first_logistics_company() {
        holder.setSecondContainer(holder.getFirstCompany().createContainer());
    }

    @Given("the first journey has ended at {int}:{int} {int}\\/{int}\\/{int}")
    public void the_first_journey_has_ended_at(Integer hours, Integer minutes, Integer day, Integer month,
            Integer year) {
        LocalDateTime timestamp = LocalDateTime.of(year, month, day, hours, minutes);
        holder.getFirstCompany().endJourney(holder.getFirstJourney(), timestamp);
    }

    @Given("the list of journeys of the container contains the first journey")
    public void the_list_of_journeys_of_the_container_contains_the_first_journey() {
        assertTrue(holder.getFirstContainer().getJourneyList().contains(holder.getFirstJourney()));
    }

    @Given("the last journey of the container list is ended")
    public void the_last_journey_of_the_container_list_is_ended() {
        assertTrue(holder.getFirstContainer().getJourneyList()
                .get(holder.getFirstContainer().getJourneyList().size() - 1).isEnded());
    }

    @Given("the last journey of the container list is not ended")
    public void the_last_journey_of_the_container_list_is_not_ended() {
        assertFalse(holder.getFirstContainer().getJourneyList()
                .get(holder.getFirstContainer().getJourneyList().size() - 1).isEnded());
    }

    @Given("the journey has started at {int}:{int} {int}\\/{int}\\/{int}")
    public void the_journey_has_started_at(Integer hours, Integer minutes, Integer day, Integer month, Integer year) {
        LocalDateTime timestamp = LocalDateTime.of(year, month, day, hours, minutes);
        holder.getFirstCompany().startJourney(holder.getFirstJourney(), timestamp);
    }

    @When("the logistics company starts a first journey of the first client with a timestamp {int}:{int} {int}\\/{int}\\/{int}")
    public void the_logistics_company_starts_a_journey_with_a_timestamp(Integer hours, Integer minutes, Integer day,
            Integer month, Integer year) {
        LocalDateTime timestamp = LocalDateTime.of(year, month, day, hours, minutes);
        successfulJourneyStart = holder.getFirstCompany().startJourney(holder.getFirstJourney(), timestamp);
    }

    @When("the logistics company starts a second journey of the first client with a timestamp {int}:{int} {int}\\/{int}\\/{int}")
    public void the_logistics_company_starts_a_second_journey_of_the_first_client_with_a_timestamp(Integer hours,
            Integer minutes, Integer day, Integer month, Integer year) {
        LocalDateTime timestamp = LocalDateTime.of(year, month, day, hours, minutes);
        successfulJourneyStart = holder.getFirstCompany().startJourney(holder.getSecondJourney(), timestamp);
    }

    @When("the logistics company ends the first journey with a timestamp {int}:{int} {int}\\/{int}\\/{int}")
    public void the_logistics_company_ends_the_first_journey_with_a_timestamp(Integer hours, Integer minutes,
            Integer day, Integer month, Integer year) {
        LocalDateTime timestamp = LocalDateTime.of(year, month, day, hours, minutes);
        successfulJourneyEnd = holder.getFirstCompany().endJourney(holder.getFirstJourney(), timestamp);
    }

    @Then("the first journey has started")
    public void the_first_journey_has_started() {
        assertTrue(successfulJourneyStart);
        assertTrue(holder.getFirstJourney().isStarted());
    }

    @Then("the first journey failed to start")
    public void the_first_journey_failed_to_start() {
        assertFalse(successfulJourneyStart);
    }

    @Then("the second journey has started")
    public void the_second_journey_has_started() {
        assertTrue(successfulJourneyStart);
        assertTrue(holder.getSecondJourney().isStarted());
    }

    @Then("the first journey has ended")
    public void the_first_journey_has_ended() {
        assertTrue(successfulJourneyEnd);
        assertTrue(holder.getFirstJourney().isEnded());
    }

    @Then("the first journey failed to end")
    public void the_first_journey_failed_to_end() {
        assertFalse(successfulJourneyEnd);
    }

    @Then("the list of journeys of the container contains the second journey")
    public void the_list_of_journeys_of_the_container_contains_the_second_journey() {
        assertTrue(holder.getFirstContainer().getJourneyList().contains(holder.getFirstJourney()));
    }

    @Then("the second journey failed to start")
    public void the_second_journey_failed_to_start() {
        assertFalse(successfulJourneyStart);
    }

    @Then("the list of journeys of the container does not contain the second journey")
    public void the_list_of_journeys_of_the_container_does_not_contain_the_second_journey() {
        assertFalse(holder.getFirstContainer().getJourneyList().contains(holder.getSecondJourney()));
    }

    @Then("the container is not available at {int}:{int} {int}\\/{int}\\/{int}")
    public void the_container_is_not_available_at(Integer hours, Integer minutes, Integer day, Integer month,
            Integer year) {
        assertFalse(holder.getFirstContainer().isAvailable(LocalDateTime.of(year, month, day, hours, minutes)));
    }

    @Then("the starting timestamp of the first journey is {int}:{int} {int}\\/{int}\\/{int}")
    public void the_starting_timestamp_of_the_journey_is(Integer hours, Integer minutes, Integer day, Integer month,
            Integer year) {
        LocalDateTime timestamp = LocalDateTime.of(year, month, day, hours, minutes);
        assertEquals(timestamp, holder.getFirstJourney().getStartTimestamp());
    }

    @Then("the ending timestamp of the first journey is {int}:{int} {int}\\/{int}\\/{int}")
    public void the_ending_timestamp_of_the_journey_is(Integer hours, Integer minutes, Integer day, Integer month,
            Integer year) {
        LocalDateTime timestamp = LocalDateTime.of(year, month, day, hours, minutes);
        assertEquals(timestamp, holder.getFirstJourney().getEndTimestamp());
    }

    @Then("the logistics company successfully adds a container status with a timestamp {int}:{int} {int}\\/{int}\\/{int}")
    public void the_logistics_company_successfully_adds_a_container_status_with_a_timestamp(Integer hours,
            Integer minutes, Integer day, Integer month, Integer year) {
        LocalDateTime timestamp = LocalDateTime.of(year, month, day, hours, minutes);
        ContainerStatus status = new ContainerStatus(timestamp, 5.0, 80.0, 1.01, "New York");
        assertTrue(holder.getFirstCompany().enterStatus(status, holder.getFirstJourney()));
    }

    @Then("the logistics company fails to add a container status with a timestamp {int}:{int} {int}\\/{int}\\/{int}")
    public void the_logistics_company_fails_to_add_a_container_status_with_a_timestamp(Integer hours, Integer minutes,
            Integer day, Integer month, Integer year) {
        LocalDateTime timestamp = LocalDateTime.of(year, month, day, hours, minutes);
        ContainerStatus status = new ContainerStatus(timestamp, 5.0, 80.0, 1.01, "New York");
        assertFalse(holder.getFirstCompany().enterStatus(status, holder.getFirstJourney()));
    }
}
