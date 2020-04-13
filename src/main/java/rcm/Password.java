package rcm;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Password {

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
        System.out.println(generatedPassword);
        return generatedPassword;
    }

//also need:
    // Method that compares name(ID) with stored name and retrieves corresponding
    // hashkey
    // Method that compares new hash and the retrieved hashkey and returns a bool
    // (logged-in)
    // add updateInfo options for passwords (make sure the cleartext is deleted
    // after hash is stored)
    // method for rules about valid passwords
}
