package rcm.cucumber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.time.LocalDateTime;

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

    @When("the logistics company starts a journey with timestamp {int}:{int} {int}\\/{int}\\/{int}")
    public void the_logistics_company_starts_a_journey_with_a_timestamp(Integer hours, Integer minutes, Integer day,
            Integer month, Integer year) {
        LocalDateTime timestamp = LocalDateTime.of(year, month, day, hours, minutes);
        successfulJourneyStart = holder.getApp().startJourney(holder.getFirstJourney().getId(), timestamp);
    }

    @When("the logistics company starts a second journey with timestamp {int}:{int} {int}\\/{int}\\/{int}")
    public void the_logistics_company_starts_a_second_journey_with_timestamp(Integer hours, Integer minutes,
            Integer day, Integer month, Integer year) {
        LocalDateTime timestamp = LocalDateTime.of(year, month, day, hours, minutes);
        successfulJourneyStart = holder.getApp().startJourney(holder.getSecondJourney().getId(), timestamp);
    }

    @When("the logistics company ends the journey with timestamp {int}:{int} {int}\\/{int}\\/{int}")
    public void the_logistics_company_ends_the_journey_with_timestamp(Integer hours, Integer minutes, Integer day,
            Integer month, Integer year) {
        LocalDateTime timestamp = LocalDateTime.of(year, month, day, hours, minutes);
        successfulJourneyEnd = holder.getApp().endJourney(holder.getFirstJourney().getId(), timestamp);
    }

    @Then("the journey failed to start")
    public void the_first_journey_failed_to_start() {
        assertFalse(successfulJourneyStart);
    }

    @Then("the journey has started")
    public void the_journey_has_started() {
        assertTrue(successfulJourneyStart);
        assertTrue(holder.getFirstJourney().isStarted());
    }

    @Then("the second journey has started")
    public void the_second_journey_has_started() {
        assertTrue(successfulJourneyStart);
        assertTrue(holder.getSecondJourney().isStarted());
    }

    @Then("the journey has ended")
    public void the_journey_has_ended() {
        assertTrue(successfulJourneyEnd);
        assertTrue(holder.getFirstJourney().isEnded());
    }

    @Then("the journey failed to end")
    public void the_journey_failed_to_end() {
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

    @Then("the starting timestamp of the journey is {int}:{int} {int}\\/{int}\\/{int}")
    public void the_starting_timestamp_of_the_journey_is(Integer hours, Integer minutes, Integer day, Integer month,
            Integer year) {
        LocalDateTime timestamp = LocalDateTime.of(year, month, day, hours, minutes);
        assertEquals(timestamp, holder.getFirstJourney().getStartTimestamp());
    }

    @Then("the ending timestamp of the journey is {int}:{int} {int}\\/{int}\\/{int}")
    public void the_ending_timestamp_of_the_journey_is(Integer hours, Integer minutes, Integer day, Integer month,
            Integer year) {
        LocalDateTime timestamp = LocalDateTime.of(year, month, day, hours, minutes);
        assertEquals(timestamp, holder.getFirstJourney().getEndTimestamp());
    }

    @Then("the logistics company successfully adds a container status with timestamp {int}:{int} {int}\\/{int}\\/{int}")
    public void the_logistics_company_successfully_adds_a_container_status_with_a_timestamp(Integer hours,
            Integer minutes, Integer day, Integer month, Integer year) throws IOException {
        LocalDateTime timestamp = LocalDateTime.of(year, month, day, hours, minutes);
        ContainerStatus status = new ContainerStatus(timestamp, 5.0, 80.0, 1.01, "New York");
        assertTrue(holder.getApp().enterNewContainerStatus(holder.getFirstJourney().getId(), status));
    }

    @Then("the logistics company fails to add a container status with timestamp {int}:{int} {int}\\/{int}\\/{int}")
    public void the_logistics_company_fails_to_add_a_container_status_with_timestamp(Integer hours, Integer minutes,
            Integer day, Integer month, Integer year) throws IOException {
        LocalDateTime timestamp = LocalDateTime.of(year, month, day, hours, minutes);
        ContainerStatus status = new ContainerStatus(timestamp, 5.0, 80.0, 1.01, "New York");
        assertFalse(holder.getApp().enterNewContainerStatus(holder.getFirstJourney().getId(), status));
    }
}
