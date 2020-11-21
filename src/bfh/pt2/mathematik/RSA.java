package bfh.pt2.mathematik;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RSA {
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

    /**
     * Solution to Exercise 5a:
     * Encryption of message  with P_1, Q_1
     * @return encrypted slices of the message M_1
     */
    public static List<Integer> encryptMessage(String msg, int p, int q, int e) {
        final int n = p * q;

        List<Integer> slicedMessage = messageASCIISlicer(msg, n);

        List<Integer> encryptedMessage = new ArrayList<>();
        /*
         * Encrypt every sliced junk of the original message and store it inside
         * the encryptedMessage list.
         */
        slicedMessage.forEach(msgJnk -> encryptedMessage.add(encryptASCIIMessage(msgJnk, e, n)));
        return encryptedMessage;
    }

    /**
     * Method to slice a message into junks of ASCII-Numbers, which are not
     * greater then the maxSize.
     * @param message - Message to slice into ASCII junks
     * @return - returns an array of ASCII junks
     */
    private static List<Integer> messageASCIISlicer(String message, int maxSize) {
        /*
         * One message slice cannot be greater then the max size. The highest ASCII value is 127.
         * I divided the max Size by 1000, so when I add one more letter to the message, it won't
         * overlap the max size.
         */
         maxSize = maxSize / 1000;

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
    private static Integer encryptASCIIMessage(Integer msg, int e, int n) {
        return (int) (Math.pow(msg, e) % n);
    }

    /**
     * Solution to Exercise 5b:
     * A hash funktion to sign the encrypted message with P_2 and Q_2 with NAME.
     * @param msg - encrypted message
     * @return - hash value for the encrypted message
     */
    public static List<Integer> signMessage(String msg, int p, int q, int e) {
        int hash = msg.hashCode();
        return null;
    }

    private static int sign(int hash, int p, int q, int e) {
        int d = (p-1) * (q-1);
        return 0;
    }

    /**
     * Implementation of the 'Square and Multiply Algorithmen'.
     * Calculates and returns the result of: (number^power) % modulo
     */
    private static int squareAndMultiply(int number, int power, int modulo) {
        /*
         * Find the 2er potencies of the power.
         */
        List<Integer> twoPotencies = new ArrayList<>();
        while (power > 0) {
            int pow = 0;
            while (getTwoPotency(pow) < power) { pow++; }
            pow = Math.max(0, pow - 1);
            twoPotencies.add(getTwoPotency(pow));
            power -= getTwoPotency(pow);
        }

        /*
         * The square and multiplying part.
         */
        int result = 1;
        for (
                int powOfTwo = 1;
                powOfTwo <= Collections.max(twoPotencies);
                powOfTwo *= 2
        ) {
            number = number % modulo;
            if (twoPotencies.contains(powOfTwo)) {
                result *= number;
            }
            number = (int) Math.pow(number, 2);
        }

        /*
         * Simplified the last step, because modulo of big numbers is less critical.
         */
        return result % modulo;
    }

    private static int getTwoPotency(int power) {
        return (int) Math.pow(2, power);
    }

}
