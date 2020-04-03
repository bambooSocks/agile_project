package rcm;

import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.Given;

public class SharedStepMethods {

    private SharedObjectHolder holder;

    public SharedStepMethods(SharedObjectHolder holder) {
        this.holder = holder;
    }

    @Given("a first logistics company {string} with address {string} reference person {string} and email {string}")
    public void a_first_logistics_company_with_address_reference_person_and_email(String name, String address,
            String refPerson, String email) {
        holder.setFirstCompany(new LogisticsCompany(name, address, refPerson, email));
        assertEquals(name, holder.getFirstCompany().getName());
        assertEquals(address, holder.getFirstCompany().getAddress());
        assertEquals(refPerson, holder.getFirstCompany().getRefPerson());
        assertEquals(email, holder.getFirstCompany().getEmail());
    }

    
    // maybe not
    @Given("no logistics company")
    public void no_logistics_company() {
        holder.setFirstCompany(null);
    }
    
    @Given("a first client {string} with address {string} reference person {string} and email {string}")
    public void a_first_client_with_address_reference_person_and_email(String name, String address, String refPerson,
            String email) {
        holder.setFirstClient(new Client(name, address, refPerson, email));
        assertEquals(name, holder.getFirstClient().getName());
        assertEquals(address, holder.getFirstClient().getAddress());
        assertEquals(refPerson, holder.getFirstClient().getRefPerson());
        assertEquals(email, holder.getFirstClient().getEmail());
    }
    
    @Given("a second client {string} with address {string} reference person {string} and email {string}")
    public void a_second_client_with_address_reference_person_and_email(String name, String address, String refPerson,
            String email) {
        holder.setSecondClient(new Client(name, address, refPerson, email));
        assertEquals(name, holder.getSecondClient().getName());
        assertEquals(address, holder.getSecondClient().getAddress());
        assertEquals(refPerson, holder.getSecondClient().getRefPerson());
        assertEquals(email, holder.getSecondClient().getEmail());
    }
    
}
