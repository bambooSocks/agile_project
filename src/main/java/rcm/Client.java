package rcm;

import java.util.List;
import java.util.stream.Collectors;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Client extends User {

    private List<Journey> journeyList;
    private LogisticsCompany company;

    private static final String regexEmail = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final String regexName = "^[A-Z]+([a-z]*)+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";

    public Client(String name, String address, String refPerson, String email, String password) {
        super(name, address, refPerson, email, password);
        journeyList = new LinkedList<Journey>();
        id = IdGenerator.getInstance().getId(GroupIdType.CLIENT);
    }

    public void assignCompany(LogisticsCompany company) {
        this.company = company;
    }

    public void addJourney(Journey journey) {
        journeyList.add(journey);
    }

    public List<Journey> searchByDestination(String destination) {
        return journeyList.stream().filter(j -> j.getDestinationPort().equals(destination))
                .collect(Collectors.toList());
    }

    public List<Journey> searchByOrigin(String origin) {
        return journeyList.stream().filter(j -> j.getOriginPort().equals(origin)).collect(Collectors.toList());
    }

    public List<Journey> searchByContent(String content) {
        return journeyList.stream().filter(j -> j.getContent().equals(content)).collect(Collectors.toList());
    }

    public static boolean validInfo(String name, String address, String refPerson, String email, String password) {
        Matcher matcherName = Pattern.compile(regexName).matcher(name);
        Matcher matcherEmail = Pattern.compile(regexEmail).matcher(email);
        Matcher matcherRefPerson = Pattern.compile(regexName).matcher(refPerson);
        if (name.length() <= 25 && matcherName.matches() && matcherEmail.matches() && matcherRefPerson.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean updateInfo(String newName, String newAddress, String newRefPerson, String newEmail,
            String newPassword) {
        if (validInfo(newName, newAddress, newRefPerson, newEmail, newPassword)) {
            System.out.println("this is new password" + newPassword);
            name = newName;
            address = newAddress;
            refPerson = newRefPerson;
            email = newEmail;
            password = Password.SHA1_Hasher(newPassword);
            System.out.println("this is the rehased" + password);
            return true;
        } else {
            return false;
        }

    }

    public boolean closeButton() {
        // TODO Auto-generated method stub
        return true;
    }

    /**
     * Method which requests the company to create a journey
     * 
     * @param originPort      the origin port of the journey
     * @param destinationPort the destination port of the journey
     * @param content         content of the container in the journey
     * @return Response.SUCCESS for journey created and added to journeyList
     *         JOURNEY_NOT_CREATED for failing to create journey
     * @implNote This method only works if the client is assigned to a company
     */
    public Response requestJourney(String originPort, String destinationPort, String content) {
        if (company.createJourney(this, originPort, destinationPort, content) != null) {
            return Response.SUCCESS;
        } else {
            return Response.JOURNEY_NOT_CREATED;
        }
    }

}
