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
    public static final int[] M_2 = {1560896, 1481544, 912673, 1331000, 1520875, 1295029, 1157625, 1560896};
    public static final int[] M_3 = {64120834, 17019509, 25668535, 32627809, 18145261};

    public static final String NAME = "Oliver";

    public static void main(String[] args) {
        Key key = RSA.getKeys(P_1, P_2, E_1);

        List<Integer> slicedMessage = new ArrayList<>();
        for (char let : NAME.toCharArray()) {
            slicedMessage.add((int) let);
        }

        slicedMessage = Arrays.asList(688, 232, 687, 966, 668);

        List<BigInteger> encryptedMsg = new ArrayList<>();
        slicedMessage.forEach(letter -> encryptedMsg.add(
                BigInteger.valueOf(letter)
                        .modPow(
                                BigInteger.valueOf(key.publicKey),
                                BigInteger.valueOf(key.n))
        ));

        System.out.println("Encrypted");
        encryptedMsg.forEach(System.out::println);

        List<BigInteger> decryptedMsg = new ArrayList<>();
        encryptedMsg.forEach(encryptedLetter -> decryptedMsg.add(
                encryptedLetter.modPow(
                                BigInteger.valueOf(key.privateKey),
                                BigInteger.valueOf(key.n))
        ));

        System.out.println("Decrypted");
        decryptedMsg.forEach(System.out::println);

        /*
        int publicKey, privateKey, n;
        EncryptedMessage encryptedMessage;

        System.out.println("Exercise 5a:");
        publicKey = E_1;
        n = P_1 * Q_1;
        encryptedMessage = RSA.encryptedMessage(M_1, publicKey, n);

        System.out.println(encryptedMessage);

        System.out.println("Exercise 5b:");
        privateKey = (P_2 - 1) * (Q_2 - 1);
        publicKey = E_1;
        n = P_2 * P_1;
        // encryptedMessage = RSA.encryptAndSignMessage(NAME, privateKey, publicKey, n);
        List<BigInteger> encryptedMsg = RSA.encrypting(NAME, publicKey, n);
        encryptedMsg.forEach(System.out::println);
        //System.out.println(encryptedMessage);

        System.out.println("Exercise 5c:");
        System.out.println(RSA.decryptMessage(encryptedMsg, privateKey, n));
         */
    }
}
