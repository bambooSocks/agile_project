package rcm.cucumber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.time.LocalDateTime;

import io.cucumber.java.en.Given;
import rcm.model.Application;
import rcm.model.Client;
import rcm.model.Container;
import rcm.model.Journey;
import rcm.model.User;
import rcm.model.WrongInputException;
import rcm.repository.Repository;
import rcm.repository.SqliteRepository;

public class SharedStepMethods {

    private SharedObjectHolder holder;
    public Repository repo;

    public SharedStepMethods(SharedObjectHolder holder) {
        this.holder = holder;
    }

    @Given("a logged in logistics company")
    public void a_logged_in_logistics_company() throws IOException, WrongInputException {
        Repository repo = new SqliteRepository();
        repo.clearDatabase();
        holder.setApp(new Application(repo));
        holder.setFirstCompany(holder.getApp().createNewLogisticsCompany("Maersk", "Kbh", "Soeren Skou",
                "bigboats@maersk.com", "Agile123"));
        assertEquals("Maersk", holder.getFirstCompany().getName());
        assertEquals("Kbh", holder.getFirstCompany().getAddress());
        assertEquals("Soeren Skou", holder.getFirstCompany().getRefPerson());
        assertEquals("bigboats@maersk.com", holder.getFirstCompany().getEmail());
        assertEquals(User.SHA1_Hasher("Agile123"), holder.getFirstCompany().getPassword());
        holder.getApp().logInUser("bigboats@maersk.com", "Agile123");
    }

    @Given("a logged in logistics company with client")
    public void a_logged_in_logistics_company_with_client() throws IOException, WrongInputException {
        a_logged_in_logistics_company();
        Client c = holder.getApp().createNewClient("Chiquita", "Miami, FL", "Banana Man", "bananas@chiquita.com",
                "Object123");
        holder.setFirstClient(c);
    }

    @Given("a logistics company with logged in client")
    public void a_logistics_company_with_logged_in_client() throws IOException, WrongInputException {
        a_logged_in_logistics_company_with_client();
        holder.getApp().logInUser("bananas@chiquita.com", "Object123");
    }

    @Given("a logistics company with container and logged in client")
    public void a_logistics_company_with_container_and_logged_in_client() throws IOException, WrongInputException {
        a_logged_in_logistics_company_with_client();
        holder.getApp().createNewContainer();
        holder.getApp().logInUser("bananas@chiquita.com", "Object123");
    }

    @Given("a logged in logistics company with container and client with journey")
    public void a_logged_in_logistics_company_with_container_and_client_with_journey()
            throws IOException, WrongInputException {
        a_logistics_company_with_container_and_logged_in_client();
        Journey journey = holder.getApp().requestNewJourney("Rome", "New York", "True pizza");
        holder.setFirstJourney(journey);
        holder.getApp().createNewContainer();
        holder.getApp().logInUser("bigboats@maersk.com", "Agile123");
    }

    @Given("a logistics company with two containers and a logged in client with two journeys")
    public void a_logistics_company_with_two_containers_and_a_logged_in_client_with_two_journeys()
            throws IOException, WrongInputException {
        a_logged_in_logistics_company_with_client();
        holder.getApp().createNewContainer();
        holder.getApp().createNewContainer();
        holder.getApp().logInUser("bananas@chiquita.com", "Object123");
        Journey journey = holder.getApp().requestNewJourney("Rome", "New York", "Kinder eggs");
        holder.setFirstJourney(journey);
        Journey journey2 = holder.getApp().requestNewJourney("Copenhagen", "Stockholm", "Kinder eggs");
        holder.setSecondJourney(journey2);
    }

    @Given("a logged in logistics company with a container and client with two journeys")
    public void a_logged_in_logistics_company_with_a_container_and_client_with_two_journeys()
            throws IOException, WrongInputException {
        a_logged_in_logistics_company_with_client();
        Container c = holder.getApp().createNewContainer();
        holder.setFirstContainer(c);
        holder.getApp().logInUser("bananas@chiquita.com", "Object123");
        Journey journey = holder.getApp().requestNewJourney("Rome", "New York", "Kinder eggs");
        holder.setFirstJourney(journey);
        Journey journey2 = holder.getApp().requestNewJourney("Copenhagen", "Stockholm", "Kinder eggs");
        holder.setSecondJourney(journey2);
        holder.getApp().logInUser("bigboats@maersk.com", "Agile123");
    }

    @Given("a logged in logistics company with a container and a clients with two ended journeys")
    public void a_logged_in_logistics_company_with_a_container_and_a_clients_with_two_ended_journeys()
            throws IOException, WrongInputException {
        a_logged_in_logistics_company_with_client();
        Container c = holder.getApp().createNewContainer();
        holder.setFirstContainer(c);
        holder.getApp().logInUser("bananas@chiquita.com", "Object123");
        Journey journey = holder.getApp().requestNewJourney("Rome", "New York", "Kinder eggs");
        holder.setFirstJourney(journey);
        Journey journey2 = holder.getApp().requestNewJourney("Copenhagen", "Stockholm", "Kinder eggs");
        holder.setSecondJourney(journey2);
        holder.getApp().logInUser("bigboats@maersk.com", "Agile123");
        assertTrue(holder.getApp().startJourney(journey.getId(), LocalDateTime.of(2020, 3, 13, 4, 20)));
        assertTrue(holder.getApp().endJourney(journey.getId(), LocalDateTime.of(2020, 3, 14, 4, 20)));
        assertTrue(holder.getApp().startJourney(journey2.getId(), LocalDateTime.of(2020, 3, 15, 4, 20)));
        assertTrue(holder.getApp().endJourney(journey2.getId(), LocalDateTime.of(2020, 3, 16, 4, 20)));

    }

    @Given("another logged in logistics company")
    public void another_logged_in_logistics_company() throws WrongInputException, IOException {
        holder.setSecondCompany(holder.getApp().createNewLogisticsCompany("Hamburg Sud", "Hamburg", "Willy Wonka",
                "notsobigboats@hamburg.com", "Agile123"));
        assertEquals("Hamburg Sud", holder.getSecondCompany().getName());
        assertEquals("Hamburg", holder.getSecondCompany().getAddress());
        assertEquals("Willy Wonka", holder.getSecondCompany().getRefPerson());
        assertEquals("notsobigboats@hamburg.com", holder.getSecondCompany().getEmail());
        assertEquals(User.SHA1_Hasher("Agile123"), holder.getSecondCompany().getPassword());
        holder.getApp().logInUser("notsobigboats@hamburg.com", "Agile123");
    }

    @Given("a logged in logistics company with client and journey")
    public void a_logged_in_logistics_company_with_client_and_journey() throws IOException, WrongInputException {
        a_logged_in_logistics_company_with_client();
        holder.getApp().logInUser("bananas@chiquita.com", "Object123");
        Journey journey = holder.getApp().requestNewJourney("Rome", "New York", "Kinder eggs");
        holder.setFirstJourney(journey);
        holder.getApp().logInUser("bigboats@maersk.com", "Agile123");
    }

    @Given("the journey has ended at {int}:{int} {int}\\/{int}\\/{int}")
    public void the_journey_has_ended_at(Integer hours, Integer minutes, Integer day, Integer month, Integer year) {
        LocalDateTime timestamp = LocalDateTime.of(year, month, day, hours, minutes);
        holder.getApp().endJourney(holder.getFirstJourney().getId(), timestamp);
    }

    @Given("the journey has started at {int}:{int} {int}\\/{int}\\/{int}")
    public void the_journey_has_started_at(Integer hours, Integer minutes, Integer day, Integer month, Integer year) {
        LocalDateTime timestamp = LocalDateTime.of(year, month, day, hours, minutes);
        holder.getApp().startJourney(holder.getFirstJourney().getId(), timestamp);
    }

    @Given("a second client {string} with address {string} reference person {string} email {string} and password {string}")
    public void a_second_client_with_address_reference_person_email_and_password(String name, String address,
            String refPerson, String email, String password) throws IOException, WrongInputException {
        Client client = holder.getApp().createNewClient(name, address, refPerson, email, password);
        holder.setSecondClient(client);
        assertEquals(name, holder.getSecondClient().getName());
        assertEquals(address, holder.getSecondClient().getAddress());
        assertEquals(refPerson, holder.getSecondClient().getRefPerson());
        assertEquals(email, holder.getSecondClient().getEmail());
        assertEquals(User.SHA1_Hasher(password), holder.getSecondClient().getPassword());
    }

    @Given("another client is now logged in")
    public void another_client_is_now_logged_in() throws IOException, WrongInputException {
        Client client = holder.getApp().createNewClient("Dole", "California", "Banana Woman", "info@dole.com",
                "Object123");
        holder.setSecondClient(client);
        assertEquals("Dole", holder.getSecondClient().getName());
        assertEquals("California", holder.getSecondClient().getAddress());
        assertEquals("Banana Woman", holder.getSecondClient().getRefPerson());
        assertEquals("info@dole.com", holder.getSecondClient().getEmail());
        assertEquals(User.SHA1_Hasher("Object123"), holder.getSecondClient().getPassword());
        holder.getApp().logInUser("info@dole.com", "Object123");
    }

    @Given("the client is now logged in")
    public void the_client_is_now_logged_in() throws WrongInputException {
        holder.getApp().logInUser("bananas@chiquita.com", "Object123");
    }
}
