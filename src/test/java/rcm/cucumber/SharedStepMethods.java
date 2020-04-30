package rcm.cucumber;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.time.LocalDateTime;

import io.cucumber.java.en.Given;
import rcm.model.Application;
import rcm.model.Client;
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
        holder.setFirstCompany(
                holder.getApp().createNewLogisticsCompany("Maersk", "Kbh", "Soeren Skou", "bigboats@maersk.com", "Agile123"));
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
        Client c = holder.getApp().createNewClient("Chiquita", "Miami, FL", "Banana Man", "bananas@chiquita.com", "Object123");
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
    public void a_logged_in_logistics_company_with_container_and_client_with_journey() throws IOException, WrongInputException {
        a_logistics_company_with_container_and_logged_in_client();
        Journey journey = holder.getApp().requestNewJourney("Rome", "New York", "True pizza");
        holder.setFirstJourney(journey);
        holder.getApp().logInUser("bigboats@maersk.com", "Agile123");
    }
    
    @Given("another logged in logistics company")
    public void another_logged_in_logistics_company() throws WrongInputException, IOException {
        holder.setFirstCompany(
                holder.getApp().createNewLogisticsCompany("Hamburg Sud", "Hamburg", "Willy Wonka", "notsobigboats@hamburg.com", "Agile123"));
        assertEquals("Hamburg Sud", holder.getFirstCompany().getName());
        assertEquals("Hamburg", holder.getFirstCompany().getAddress());
        assertEquals("Willy Wonka", holder.getFirstCompany().getRefPerson());
        assertEquals("notsobigboats@hamburg.com", holder.getFirstCompany().getEmail());
        assertEquals(User.SHA1_Hasher("Agile123"), holder.getFirstCompany().getPassword());
        holder.getApp().logInUser("notsobigboats@hamburg.com", "Agile123");
    }
    
    @Given("the first journey has ended at {int}:{int} {int}\\/{int}\\/{int}")
    public void the_first_journey_has_ended_at(Integer hours, Integer minutes, Integer day, Integer month,
            Integer year) {
        LocalDateTime timestamp = LocalDateTime.of(year, month, day, hours, minutes);
        holder.getApp().endJourney(holder.getFirstJourney().getId(), timestamp);
    }

    @Given("the journey has started at {int}:{int} {int}\\/{int}\\/{int}")
    public void the_journey_has_started_at(Integer hours, Integer minutes, Integer day, Integer month, Integer year) {
        LocalDateTime timestamp = LocalDateTime.of(year, month, day, hours, minutes);
        holder.getApp().startJourney(holder.getFirstJourney().getId(), timestamp);
    }
    
    
    
    @Given("a first logistics company {string} with address {string} reference person {string} email {string} and password {string}")
    public void a_first_logistics_company_with_address_reference_person_email_and_password(String name, String address,
            String refPerson, String email, String password) throws IOException {
        try {
            holder.setFirstCompany(
                    holder.getApp().createNewLogisticsCompany(name, address, refPerson, email, password));
            assertEquals(name, holder.getFirstCompany().getName());
            assertEquals(address, holder.getFirstCompany().getAddress());
            assertEquals(refPerson, holder.getFirstCompany().getRefPerson());
            assertEquals(email, holder.getFirstCompany().getEmail());
            assertEquals(User.SHA1_Hasher(password), holder.getFirstCompany().getPassword());
        } catch (WrongInputException e) {
            System.err.println(e.getMessage());
        }
    }

    @Given("first logistics company is logged-in with email {string} and password {string}")
    public void first_logistics_company_is_logged_in_with_email_and_password(String email, String password) {
        try {
            holder.getApp().logInUser(email, password);
        } catch (WrongInputException e) {
            System.err.println(e.getMessage());
        }
        assertEquals(holder.getFirstCompany(), holder.getApp().getLoggedInCompany());
    }

    @Given("a second logistics company {string} with address {string} reference person {string} email {string} and password {string}")
    public void a_second_logistics_company_with_address_reference_person_email_and_password(String name, String address,
            String refPerson, String email, String password) throws IOException {
        try {
            holder.setSecondCompany(
                    holder.getApp().createNewLogisticsCompany(name, address, refPerson, email, password));
            assertEquals(name, holder.getSecondCompany().getName());
            assertEquals(address, holder.getSecondCompany().getAddress());
            assertEquals(refPerson, holder.getSecondCompany().getRefPerson());
            assertEquals(email, holder.getSecondCompany().getEmail());
            assertEquals(User.SHA1_Hasher(password), holder.getSecondCompany().getPassword());
        } catch (WrongInputException e) {
            System.err.println(e.getMessage());
        }
    }

    @Given("second logistics company is logged-in with email {string} and password {string}")
    public void second_logistics_company_is_logged_in_with_email_and_password(String email, String password) {
        try {
            holder.getApp().logInUser(email, password);
        } catch (WrongInputException e) {
            System.err.println(e.getMessage());
        }
        assertEquals(holder.getSecondCompany(), holder.getApp().getLoggedInCompany());
    }

    @Given("a first client {string} with address {string} reference person {string} email {string} and password {string}")
    public void a_first_client_with_address_reference_person_email_and_password(String name, String address,
            String refPerson, String email, String password) throws IOException {
        try {
            Client client = holder.getApp().createNewClient(name, address, refPerson, email, password);
            holder.setFirstClient(client);
            assertEquals(name, holder.getFirstClient().getName());
            assertEquals(address, holder.getFirstClient().getAddress());
            assertEquals(refPerson, holder.getFirstClient().getRefPerson());
            assertEquals(email, holder.getFirstClient().getEmail());
            assertEquals(User.SHA1_Hasher(password), holder.getFirstClient().getPassword());
        } catch (WrongInputException e) {
            System.err.println(e.getMessage());
        }
    }

    @Given("first client is logged-in with email {string} and password {string}")
    public void first_client_is_logged_in_with_email_and_password(String email, String password) {
        try {
            holder.getApp().logInUser(email, password);
        } catch (WrongInputException e) {
            System.err.println(e.getMessage());
        }
        assertEquals(holder.getFirstClient(), holder.getApp().getLoggedInClient());
    }

    @Given("a second client {string} with address {string} reference person {string} email {string} and password {string}")
    public void a_second_client_with_address_reference_person_email_and_password(String name, String address,
            String refPerson, String email, String password) throws IOException {
        try {
            Client client = holder.getApp().createNewClient(name, address, refPerson, email, password);
            holder.setSecondClient(client);
            assertEquals(name, holder.getSecondClient().getName());
            assertEquals(address, holder.getSecondClient().getAddress());
            assertEquals(refPerson, holder.getSecondClient().getRefPerson());
            assertEquals(email, holder.getSecondClient().getEmail());
            assertEquals(User.SHA1_Hasher(password), holder.getSecondClient().getPassword());
        } catch (WrongInputException e) {
            System.err.println(e.getMessage());
        }
    }

    @Given("second client is logged-in with email {string} and password {string}")
    public void second_client_is_logged_in_with_email_and_password(String email, String password) {
        try {
            holder.getApp().logInUser(email, password);
        } catch (WrongInputException e) {
            System.err.println(e.getMessage());
        }
        assertEquals(holder.getSecondClient(), holder.getApp().getLoggedInClient());
    }

//    @Given("a first journey of first client with origin port of {string} destination port of {string} and a content of {string}")
//    public void a_first_journey_with_origin_port_of_destination_port_of_and_a_content_of(String originPort,
//            String destinationPort, String content) throws IOException {
//        holder.setFirstJourney(holder.getApp().requestNewJourney(originPort, destinationPort, content, null));
//        assertEquals(holder.getFirstClient(), holder.getFirstJourney().getClient());
//        assertEquals(originPort, holder.getFirstJourney().getOriginPort());
//        assertEquals(destinationPort, holder.getFirstJourney().getDestinationPort());
//        assertEquals(content, holder.getFirstJourney().getContent());
//    }
//
//    @Given("a first journey of first client with origin port of {string} destination port of {string} and a content of {string} started at {int}:{int} {int}\\/{int}\\/{int}")
//    public void a_first_journey_of_first_client_with_origin_port_of_destination_port_of_and_a_content_of_started_at(
//            String originPort, String destinationPort, String content, Integer hours, Integer minutes, Integer day,
//            Integer month, Integer year) throws IOException {
//        LocalDateTime timestamp = LocalDateTime.of(year, month, day, hours, minutes);
//        holder.setFirstJourney(holder.getApp().requestNewJourney(originPort, destinationPort, content, timestamp));
//        assertEquals(holder.getFirstClient(), holder.getFirstJourney().getClient());
//        assertEquals(originPort, holder.getFirstJourney().getOriginPort());
//        assertEquals(destinationPort, holder.getFirstJourney().getDestinationPort());
//        assertEquals(content, holder.getFirstJourney().getContent());
//    }
//
//    @Given("a second journey of first client with origin port of {string} destination port of {string} and a content of {string}")
//    public void a_second_journey_with_origin_port_of_destination_port_of_and_a_content_of(String originPort,
//            String destinationPort, String content) throws IOException {
//        holder.setSecondJourney(holder.getApp().requestNewJourney(originPort, destinationPort, content, null));
//        assertEquals(holder.getFirstClient(), holder.getSecondJourney().getClient());
//        assertEquals(originPort, holder.getSecondJourney().getOriginPort());
//        assertEquals(destinationPort, holder.getSecondJourney().getDestinationPort());
//        assertEquals(content, holder.getSecondJourney().getContent());
//    }

    @Given("a container of the first logistics company")
    public void a_container_of_the_first_logistics_company() throws IOException {
        holder.setFirstContainer(holder.getApp().createNewContainer());
    }

    @Given("the first journey has started at {int}:{int} {int}\\/{int}\\/{int}")
    public void the_first_journey_has_started_at(Integer hours, Integer minutes, Integer day, Integer month,
            Integer year) {
        LocalDateTime timestamp = LocalDateTime.of(year, month, day, hours, minutes);
        holder.getApp().startJourney(holder.getFirstJourney().getId(), timestamp);
    }

}
