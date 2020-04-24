package rcm.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
    private static final String regexName = "^[A-Z]+[^±!@£$%^&*_+§¡€#¢§¶•ªº«\\/<>?:;|=0-9]{2,30}$";
    private static final String regexPassword = "^(?=.*[a-z])(?=.*[0-9])(?=.*[@#$%])*(?=.*[A-Z]).{6,16}$";
    private static final String regexAddress = "^[^±!@£$%^&*_+§¡€#¢§¶•ªº«\\\\/<>?;|=]{2,50}$";

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

        if (validateName(name)) {
            this.name = name;
        } else {
            throw new WrongInputException("The given client name is not valid");
        }

        if (validateAddress(address)) {
            this.address = address;
        } else {
            throw new WrongInputException("The given address is not valid");
        }

        if (validateRefPerson(refPerson)) {
            this.refPerson = refPerson;
        } else {
            throw new WrongInputException("The given reference name is not valid");
        }

        if (validateEmail(email)) {
            this.email = email;
        } else {
            throw new WrongInputException("The given email is not valid");
        }

        if (validatePassword(password)) {
            this.password = SHA1_Hasher(password);
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
     * Method to validate user name
     * 
     * @param name Name of the User
     * @return true if valid name, otherwise return false
     */
    private static boolean validateName(String name) {
        Matcher matcherName = Pattern.compile(regexName).matcher(name);
        if (matcherName.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to validate user address
     * 
     * @param address Address of the User
     * @return true if valid address, otherwise return false
     */
    private static boolean validateAddress(String address) {
        Matcher matcherAddress = Pattern.compile(regexAddress).matcher(address);
        if (matcherAddress.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to validate user reference person
     * 
     * @param refPerson Reference Person of the User
     * @return true if valid reference person, otherwise return false
     */
    private static boolean validateRefPerson(String refPerson) {
        Matcher matcherRefPerson = Pattern.compile(regexName).matcher(refPerson);
        if (matcherRefPerson.matches()) {
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
    public void updateName(String newName) throws WrongInputException {
        if (validateName(newName)) {
            name = newName;
        } else {
            throw new WrongInputException("The given client name is not valid");
        }
    }

    /**
     * Method to update user address
     * 
     * @param newAddress New address of the user
     */
    public void updateAddress(String newAddress) throws WrongInputException {
        if (validateAddress(newAddress)) {
            address = newAddress;
        } else {
            throw new WrongInputException("The given address is not valid");
        }
    }

    /**
     * Method to update user reference person
     * 
     * @param newRefPerson New reference person of the user
     */
    public void updateRefPerson(String newRefPerson) throws WrongInputException {
        if (validateRefPerson(newRefPerson)) {
            refPerson = newRefPerson;
        } else {
            throw new WrongInputException("The given reference name is not valid");
        }
    }

    /**
     * Method to update user email
     * 
     * @param newEmail New email of the user
     */
    public void updateEmail(String newEmail) throws WrongInputException {
        if (validateEmail(newEmail)) {
            email = newEmail;
        } else {
            throw new WrongInputException("The given email is not valid");
        }
    }

    /**
     * Method to update user password
     * 
     * @param newEmail New password of the user
     */
    public void updatePassword(String newPassword) throws WrongInputException {
        if (validatePassword(newPassword)) {
            password = SHA1_Hasher(newPassword);
        } else {
            throw new WrongInputException("The given password is not valid");
        }
    }

    /**
     * Method to convert a cleartext password into a hashkey
     * 
     * @param passwordToHash Cleartext password entered by the user
     * @return Hashkey of the users password
     */
    public static String SHA1_Hasher(String passwordToHash) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
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
