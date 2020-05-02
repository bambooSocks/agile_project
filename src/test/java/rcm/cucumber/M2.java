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
import rcm.model.Journey;
import rcm.model.WrongInputException;

public class M2 {

    private List<Journey> filteredContent, filteredDestination, filteredOrigin;
    private SharedObjectHolder holder;
    protected LocalDateTime timestamp;
    private ContainerStatus status;
    private boolean successfulEntry = false;
    private int journeyId;

    public M2(SharedObjectHolder holder) {
        this.holder = holder;
    }

    @Given("the first logistics company has two available containers")
    public void the_first_logistics_company_has_two_available_containers() throws IOException {
        holder.getApp().createNewContainer();
        holder.getApp().createNewContainer();
    }

    @Given("the container entered location {string} at {int}:{int} {int}\\\\/{int}\\\\/{int}")
    public void the_container_entered_location_at(String location, Integer hours, Integer minutes, Integer day,
            Integer month, Integer year) {
        LocalDateTime timestamp = LocalDateTime.of(year, month, day, hours, minutes);
        status = new ContainerStatus(timestamp, 25.0, 50.0, 101.0, location);
    }

    @When("the client requests to register a journey with origin {string}, destination {string} and content {string}")
    public void the_client_requests_to_register_a_journey_with_origin_destination_and_content(String originPort,
            String destinationPort, String content) {
        try {
            holder.setFirstJourney(holder.getApp().requestNewJourney(originPort, destinationPort, content));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @When("the logistics company updates containers location to {string} at {int}:{int} {int}\\/{int}\\/{int}")
    public void the_logistics_company_updates_containers_location_to_at(String loc, Integer hours, Integer minutes,
            Integer day, Integer month, Integer year) throws IOException, WrongInputException {
        status = new ContainerStatus(LocalDateTime.of(year, month, day, hours, minutes), 5.0, 60.0,
                1.01, loc);
        journeyId = holder.getFirstJourney().getId();
        successfulEntry = holder.getApp().enterNewContainerStatus(journeyId, status);
        
       
    }

    @When("the second logistics company updates containers location to {string} at {int}:{int} {int}\\/{int}\\/{int}")
    public void the_second_logistics_company_updates_containers_location_to_at(String loc, Integer hours,
            Integer minutes, Integer day, Integer month, Integer year) throws IOException {
        ContainerStatus status = new ContainerStatus(LocalDateTime.of(year, month, day, hours, minutes), 5.0, 60.0,
                1.01, loc);
        successfulEntry = holder.getApp().enterNewContainerStatus(holder.getFirstJourney().getId(), status);
    }

    @When("the first client filters his journeys based on the origin port {string}")
    public void the_first_client_filters_his_journeys_based_on_the_origin_port(String origin) {
        filteredOrigin = holder.getFirstClient().journeySearchByOrigin(origin);
    }

    @When("the first client filters his journeys based on the destination {string}")
    public void the_first_client_filters_his_journeys_based_on_the_destination(String destination) {
        filteredDestination = holder.getApp().getLoggedInClient().journeySearchByDestination(destination);
    }

    @When("the first client filters his journeys based on the content {string}")
    public void the_first_client_filters_his_journeys_based_on_the_content(String content) {
        filteredContent = holder.getApp().getLoggedInClient().journeySearchByContent(content);
    }

    @Then("the location is changed")
    public void the_location_is_changed() {
        assertTrue(successfulEntry);
        LocalDateTime t = LocalDateTime.of(2020, 3, 13, 4, 20);
        ContainerStatus s = new ContainerStatus(t, 5.0, 60.0, 1.01, "New York");
        assertTrue(holder.getFirstJourney().getStatus().contains(s));
    }

    @Then("the location is not changed")
    public void the_location_is_not_changed() {
        assertFalse(successfulEntry);
        assertTrue(holder.getFirstJourney().getStatus().isEmpty());

    }

    @Then("the first journey is listed")
    public void the_first_journey_is_listed() {
        assertTrue(filteredDestination.contains(holder.getFirstJourney()));
        assertTrue(filteredDestination.size() == 1);
    }

    @Then("the second journey is listed")
    public void the_second_journey_is_listed() {
        assertTrue(filteredOrigin.contains(holder.getSecondJourney()));
        assertTrue(filteredOrigin.size() == 1);
    }

    @Then("both journeys are listed")
    public void both_journeys_are_listed() {
        assertTrue(filteredContent.size() == 2);
        assertTrue(filteredContent.contains(holder.getFirstJourney()));
        assertTrue(filteredContent.contains(holder.getSecondJourney()));
    }

    @Then("the journey is created")
    public void the_journey_is_created() {
        assertNotEquals(null, holder.getFirstJourney());
        assertFalse(holder.getApp().requestJourneys().isEmpty());
    }

    @Then("the journey is not created")
    public void the_journey_is_not_created() {
        assertEquals(null, holder.getFirstJourney());
    }

}
