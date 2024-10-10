package edu.icet.ecom.util;

import org.mindrot.jbcrypt.BCrypt;

public class EncryptPassword {
    public static String hashingPassword(String plainPassword){
        return BCrypt.hashpw(plainPassword,BCrypt.gensalt());
    }

    public static boolean checkPassword(String plainPassword, String hashedPassword){
        return BCrypt.checkpw(plainPassword,hashedPassword);
    }
}
