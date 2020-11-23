package bfh.pt2.mathematik;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    /*
     * Following constants are from the Mail from daniel.tokarev@bfh.ch from 01.11.2020
     */
    public static final int P_1 = 13451;
    public static final int Q_1 = 2621;
    public static final int E_1 = 3;
    public static final int P_2 = 1451;
    public static final int Q_2 = 15161;
    public static final int N = 77526089;
    public static final int E_2 = 5;

    public static final String M_1 = "needed to";
    public static final Integer[] M_2 = {1560896, 1481544, 912673, 1331000, 1520875, 1295029, 1157625, 1560896};
    public static final Integer[] M_3 = {64120834, 17019509, 25668535, 32627809, 18145261};

    public static final String NAME = "Oliver";

    public static void main(String[] args) {
        long publicKey = 2;
        long privateKey = 1;
        while (true) {
            try {
                if (publicKey > N){
                    throw new Exception("stop it");
                }
                privateKey = 1;
                while (((privateKey * publicKey) % ((8849-1) * (8761-1))) != 1 || publicKey == privateKey) {
                    privateKey++;
                    if (privateKey > N) {
                        throw new RuntimeException("to large pk");
                    }
                }
                break;
            } catch (RuntimeException e) {
                System.out.println("NOT Public Key: " + publicKey);
                publicKey++;
            } catch (Exception e) {
                System.out.println("no keys found");
                break;
            }
        }
        System.out.println("public: " + publicKey);
        // -> 11
        System.out.println("private: " + privateKey);
        // -> 14092451

        for (int msg : M_2) {
            System.out.println(RSA.decrypt(msg, privateKey, N));
        }

        for (int msg : M_3) {
            System.out.println(RSA.decrypt(msg, privateKey, N));
        }

        EncryptedMessage msg;
        Key key;

        System.out.println("Exercise 5a:");
        key = RSA.getKeys(P_1, Q_1, E_1);
        msg = RSA.encryptedMessage(M_1, key);
        System.out.println(msg);
        /*
         * Output:
         * Encrypted Message:
         * 1331000
         * 1030301
         * 1030301
         * 1000000
         * 1030301
         * 1000000
         * 32768
         * 1560896
         * 1367631
         */

        System.out.println("Exercise 5b:");
        key = RSA.getKeys(P_2, Q_2, E_1);
        msg = RSA.encryptAndSignMessage(NAME, key);
        System.out.println(msg);
        /*
         * Output:
         * Signed Hash: 20045305
         * Encrypted Message:
         * 493039
         * 1259712
         * 1157625
         * 1643032
         * 1030301
         * 1481544
         */

        System.out.println("Exercise 5c:");
        System.out.println(RSA.decryptMessage(msg, key));
        /*
         * Output:
         * Oliver
         */
    }
}
