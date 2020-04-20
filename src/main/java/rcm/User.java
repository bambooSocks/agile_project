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
    private static final String regexAddress = "^([A-Za-z0-9+_.(), -])+$";
//    "^[0-9]+(([,. -][a-zA-Z0-9])?[a-zA-Z]*)*.{1,30}$"

    public User(String name, String address, String refPerson, String email, String password)
            throws WrongInputException {
        this.address = address;

        if (validateSomeName(name)) {
            this.name = name;
        } else {
            throw new WrongInputException("The given client name is not valid");
        }

        if (validateAddress(address)) {
            this.address = address;
        } else {
            throw new WrongInputException("The given address is not valid");
        }

        if (validateSomeName(refPerson)) {
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
            this.password = Password.SHA1_Hasher(password);
        } else {
            throw new WrongInputException("The given password is not valid");
        }
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getRefPerson() {
        return refPerson;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    private static boolean validateSomeName(String someName) {
        Matcher matcherName = Pattern.compile(regexName).matcher(someName);
        Matcher matcherRefPerson = Pattern.compile(regexName).matcher(someName);
        if (matcherName.matches() || matcherRefPerson.matches()) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean validateAddress(String address) {
        Matcher matcherAddress = Pattern.compile(regexAddress).matcher(address);
        if (matcherAddress.matches()) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean validateEmail(String email) {
        Matcher matcherEmail = Pattern.compile(regexEmail).matcher(email);
        if (matcherEmail.matches()) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean validatePassword(String password) {
        Matcher matcherPassword = Pattern.compile(regexPassword).matcher(password);
        if (matcherPassword.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public void updateName(String newName) throws WrongInputException {
        if (validateSomeName(newName)) {
            name = newName;
        } else {
            throw new WrongInputException("The given client name is not valid");
        }
    }

    public void updateAddress(String newAddress) throws WrongInputException {
        if (validateAddress(newAddress)) {
            address = newAddress;
        } else {
            throw new WrongInputException("The given address is not valid");
        }
    }

    public void updateRefPerson(String newRefPerson) throws WrongInputException {
        if (validateSomeName(newRefPerson)) {
            refPerson = newRefPerson;
        } else {
            throw new WrongInputException("The given reference name is not valid");
        }
    }

    public void updateEmail(String newEmail) throws WrongInputException {
        if (validateEmail(newEmail)) {
            email = newEmail;
        } else {
            throw new WrongInputException("The given email is not valid");
        }
    }

    public void updatePassword(String newPassword) throws WrongInputException {
        if (validatePassword(newPassword)) {
            password = Password.SHA1_Hasher(newPassword);
        } else {
            throw new WrongInputException("The given password is not valid");
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
