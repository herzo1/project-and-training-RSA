package bfh.pt2.mathematik;

import java.util.ArrayList;
import java.util.List;

public class RSA {
    /*
     * Following constants are from the Mail from daniel.tokarev@bfh.ch from 01.11.2020
     */
    private static final int P_1 = 13451;
    private static final int Q_1 = 2621;
    private static final int N_1 = P_1 * Q_1;
    private static final int E_1 = 3;
    private static final int P_2 = 1451;
    private static final int Q_2 = 15161;
    private static final int N = 77526089;
    private static final int E_2 = 5;

    private static final String M_1 = "needed to";
    private static final int[] M_2 = {1560896, 1481544, 912673, 1331000, 1520875, 1295029, 1157625, 1560896};
    private static final int[] M_3 = {64120834, 17019509, 25668535, 32627809, 18145261};

    /**
     * Solution to Exercise 5a:
     * Encryption of message M_1 with P_1, Q_1
     * @return encrypted slices of the message M_1
     */
    public static List<Integer> encryptMessage() {
        List<Integer> slicedMessage = messageASCIISlicer(M_1);

        List<Integer> encryptedMessage = new ArrayList<>();
        /*
         * Encrypt every sliced junk of the original message and store it inside
         * the encryptedMessage list.
         */
        slicedMessage.forEach(msg -> encryptedMessage.add(encryptASCIIMessage(msg)));
        return encryptedMessage;
    }

    /**
     * Method to slice a message into junks of ASCII-Numbers, which are not
     * greater then the maxSize.
     * @param message - Message to slice into ASCII junks
     * @return - returns an array of ASCII junks
     */
    private static List<Integer> messageASCIISlicer(String message) {
        /*
         * One message slice cannot be greater then the max size. The highest ASCII value is 127.
         * I divided the max Size by 1000, so when I add one more letter to the message, it won't
         * overlap the max size.
         */
        int maxSize = N_1 / 1000;

        /*
         * The result will be a list of integer, which representing one or more ASCII character
         */
        List<Integer> result = new ArrayList<>();
        /*
         * I use a string to keep track of the current junk, so I don't have to worry about powers of 10
         */
        String temp = "";
        for(char letter : message.toCharArray()) {
            /*
             * If the temp value is greater then the max size (divided by 1000)
             * than at it to the result and begin a new junk
             */
            if (!temp.isEmpty() && Integer.parseInt(temp) > maxSize) {
                result.add(Integer.parseInt(temp));
                temp = "";
            }
            /*
             * (int) letter gets the decimal ASCII value of the letter.
             */
            temp += String.valueOf((int) letter);
        }
        if (!temp.isEmpty()) {
            result.add(Integer.parseInt(temp));
        }
        return result;
    }

    /**
     * Method to encrypt a message with RSA.
     * @param msg - message to encrypt
     * @return - (msg^e)%n
     */
    private static Integer encryptASCIIMessage(Integer msg) {
        return (int) (Math.pow(msg, E_1) % N_1);
    }

}
