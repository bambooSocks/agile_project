package rcm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class User {

    protected int id;
    protected String password;
    protected String name;
    protected String address;
    protected String refPerson;
    protected String email;

    private static final String regexEmail = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final String regexName = "^[A-Z]+([a-z]*)+(([',. -][a-zA-Z ])?[a-zA-Z]*)*.{2,25}$";
    private static final String regexPassword = "^(?=.*[a-z])(?=.*[0-9])(?=.*[@#$%])*(?=.*[A-Z]).{6,16}$";

    /**
     * User constructor
     * 
     * @param name      Name of the user
     * @param address   Address of the user
     * @param refPerson Reference person of the user
     * @param email     Email of the user
     * @param password  Password of the user
     * @throws WrongInputException
     */
    public User(String name, String address, String refPerson, String email, String password)
            throws WrongInputException {
        this.address = address;

        if (validateSomeName(name)) {
            this.name = name;
        } else {
            throw new WrongInputException("The given name is not valid");
        }

        if (validateSomeName(refPerson)) {
            this.refPerson = refPerson;
        } else {
            throw new WrongInputException("The given name is not valid");
        }

        if (validateEmail(email)) {
            this.email = email;
        } else {
            throw new WrongInputException("The given email is not valid");
        }

        if (validatePassword(password)) {
            this.password = Password.SHA1_Hasher(password);
        } else {
            throw new WrongInputException("The given password is not valid");
        }
    }

    /**
     * Getter for user name
     * 
     * @return name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for user address
     * 
     * @return address of the user
     */
    public String getAddress() {
        return address;
    }

    /**
     * Getter for user reference person
     * 
     * @return reference person of the user
     */
    public String getRefPerson() {
        return refPerson;
    }

    /**
     * Getter for user email
     * 
     * @return email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Getter for user id
     * 
     * @return id of the user
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for user password
     * 
     * @return password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Method to validate user name or reference person
     * 
     * @param someName Name or reference person of the User
     * @return true if valid name or reference person, otherwise return false
     */
    private static boolean validateSomeName(String someName) {
        Matcher matcherName = Pattern.compile(regexName).matcher(someName);
        Matcher matcherRefPerson = Pattern.compile(regexName).matcher(someName);
        if (matcherName.matches() || matcherRefPerson.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to validate user email
     * 
     * @param email Email of the user
     * @return true if valid email, otherwise return false
     */
    private static boolean validateEmail(String email) {
        Matcher matcherEmail = Pattern.compile(regexEmail).matcher(email);
        if (matcherEmail.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to validate user password
     * 
     * @param password Password of the user
     * @return true if valid password, otherwise return false
     */
    private static boolean validatePassword(String password) {
        Matcher matcherPassword = Pattern.compile(regexPassword).matcher(password);
        if (matcherPassword.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to update user name
     * 
     * @param newName New name of the user
     */
    public void updateName(String newName) {
        if (validateSomeName(newName)) {
            name = newName;
        }
    }

    /**
     * Method to update user address
     * 
     * @param newAddress New address of the user
     */
    public void updateAddress(String newAddress) {
        address = newAddress;
    }

    /**
     * Method to update user reference person
     * 
     * @param newRefPerson New reference person of the user
     */
    public void updateRefPerson(String newRefPerson) {
        if (validateSomeName(newRefPerson)) {
            refPerson = newRefPerson;
        }
    }

    /**
     * Method to update user email
     * 
     * @param newEmail New email of the user
     */
    public void updateEmail(String newEmail) {
        if (validateEmail(newEmail)) {
            email = newEmail;
        }
    }

    /**
     * Method to update user password
     * 
     * @param newEmail New password of the user
     */
    public void updatePassword(String newPassword) {
        if (validatePassword(newPassword)) {
            password = Password.SHA1_Hasher(newPassword);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id != other.id)
            return false;
        return true;
    }
}
