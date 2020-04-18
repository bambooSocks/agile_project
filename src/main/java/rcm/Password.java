package rcm;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Password {

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
}
