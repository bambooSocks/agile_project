package rcm.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;
    @Column
    protected String password;
    @Column
    protected String name;
    @Column
    protected String address;
    @Column
    protected String refPerson;
    @Column
    protected String email;

    @Transient
    private String exceptions = "Please correct the following input:";
    @Transient
    private static final String regexEmail = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    @Transient
    private static final String regexName = "^[A-Z]+[^!@$%^&*_+#\\/<>?:;|=0-9]{2,30}$";
    @Transient
    private static final String regexPassword = "^(?=.*[a-z])(?=.*[0-9])(?=.*[@#$%])*(?=.*[A-Z]).{6,16}$";
    @Transient
    private static final String regexAddress = "^[^!@$%^&*_+#\\/<>?:;|=]{2,50}$";

    protected User() {
    }

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
    public User(String name, String address, String refPerson, String email, String password) {
        this.name = name;
        this.address = address;
        this.refPerson = refPerson;
        this.email = email;
        this.password = SHA1_Hasher(password);
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
     * Setter for user name
     * 
     * @param name of the user
     */
    public void setName(String name) {
        this.name = name;
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
     * Setter for user address
     * 
     * @param address of the user
     */
    public void setAddress(String address) {
        this.address = address;
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
     * Setter for user reference person
     * 
     * @param reference person of the user
     */
    public void setRefPerson(String refPerson) {
        this.refPerson = refPerson;
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
     * Setter for user email
     * 
     * @param email of the user
     */
    public void setEmail(String email) {
        this.email = email;
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
     * Setter for user password
     * 
     * @param password of the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Method to validate user name
     * 
     * @param name Name of the User
     * @return true if valid name, otherwise return false
     */
    public static boolean validateName(String name) {
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
    public static boolean validateAddress(String address) {
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
    public static boolean validateRefPerson(String refPerson) {
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
    public static boolean validateEmail(String email) {
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
    public static boolean validatePassword(String password) {
        Matcher matcherPassword = Pattern.compile(regexPassword).matcher(password);
        if (matcherPassword.matches()) {
            return true;
        } else {
            return false;
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
        }
        return generatedPassword;
    }
}
