
package test;

import security.PasswordUtil;

public class GenerateHash {

    public static void main(String[] args) {

        String password = "AdminGym123";

        String hash = PasswordUtil.hashPassword(password);

        System.out.println("Password Hash:");
        System.out.println(hash);
    }
}