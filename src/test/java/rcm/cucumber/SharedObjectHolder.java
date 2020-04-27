package rcm.cucumber;

import rcm.model.Application;
import rcm.model.Client;
import rcm.model.Container;
import rcm.model.Journey;
import rcm.model.LogisticsCompany;

public class SharedObjectHolder {

    private Application app;
    private LogisticsCompany company1, company2;
    private Client client1, client2;
    private Journey journey1, journey2;
    private Container container1;

    public Application getApp() {
        return app;
    }

    public void setApp(Application app) {
        this.app = app;
    }

    public LogisticsCompany getFirstCompany() {
        return company1;
    }

    public void setFirstCompany(LogisticsCompany company) {
        company1 = company;
    }

    public LogisticsCompany getSecondCompany() {
        return company2;
    }

    public void setSecondCompany(LogisticsCompany company) {
        company2 = company;
    }

    public Client getFirstClient() {
        return client1;
    }

    public void setFirstClient(Client client) {
        client1 = client;
    }

    public Client getSecondClient() {
        return client2;
    }

    public void setSecondClient(Client client) {
        client2 = client;
    }

    public Journey getFirstJourney() {
        return journey1;
    }

    public void setFirstJourney(Journey journey) {
        journey1 = journey;
    }

    public Journey getSecondJourney() {
        return journey2;
    }

    public void setSecondJourney(Journey journey) {
        journey2 = journey;
    }

    public Container getFirstContainer() {
        return container1;
    }

    public void setFirstContainer(Container container) {
        this.container1 = container;
    }

}
