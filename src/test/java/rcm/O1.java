package rcm;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class O1 {
    private SharedObjectHolder holder;

    public O1(SharedObjectHolder holder) {
        this.holder = holder;
    }

    @Given("the first journey has started at {int}:{int} {int}\\/{int}\\/{int}")
    public void the_first_journey_has_started_at(Integer hours, Integer minutes, Integer day, Integer month,
            Integer year) {
        LocalDateTime timestamp = LocalDateTime.of(year, month, day, hours, minutes);
        holder.getFirstCompany().startJourney(holder.getFirstJourney(), timestamp);
    }

    @Given("the first journey has ended at {int}:{int} {int}\\/{int}\\/{int}")
    public void the_first_journey_has_ended_at(Integer hours, Integer minutes, Integer day, Integer month,
            Integer year) {
        LocalDateTime timestamp = LocalDateTime.of(year, month, day, hours, minutes);
        holder.getFirstCompany().endJourney(holder.getFirstJourney(), timestamp);
    }

    @Given("the list of journeys of the container contains the first journey")
    public void the_list_of_journeys_of_the_container_contains_the_first_journey() {
        assertTrue(holder.getContainer().getJourneyList().contains(holder.getFirstJourney()));
    }

    @Given("the last journey of the container list is ended")
    public void the_last_journey_of_the_container_list_is_ended() {
        assertTrue(holder.getContainer().getJourneyList().getLast().isEnded());
    }

    @When("the logistics company starts a second journey of the first client with a timestamp {int}:{int} {int}\\/{int}\\/{int}")
    public void the_logistics_company_starts_a_second_journey_of_the_first_client_with_a_timestamp(Integer hours,
            Integer minutes, Integer day, Integer month, Integer year) {
        LocalDateTime timestamp = LocalDateTime.of(year, month, day, hours, minutes);
        holder.getFirstCompany().startJourney(holder.getSecondJourney(), timestamp);
    }

    @Then("the second journey has started at {int}:{int} {int}\\/{int}\\/{int}")
    public void the_second_journey_has_started_at(Integer hours, Integer minutes, Integer day, Integer month,
            Integer year) {
        LocalDateTime timestamp = LocalDateTime.of(year, month, day, hours, minutes);
        holder.getFirstCompany().startJourney(holder.getSecondJourney(), timestamp);
    }

    @Then("the list of journeys of the container contains the second journey")
    public void the_list_of_journeys_of_the_container_contains_the_second_journey() {
        assertTrue(holder.getContainer().getJourneyList().contains(holder.getFirstJourney()));
    }

}
