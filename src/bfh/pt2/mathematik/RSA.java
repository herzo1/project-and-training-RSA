package bfh.pt2.mathematik;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RSA {

    /**
     * Solution to Exercise 5a:
     * @param publicKey - public key of the perwon, who wants to sign and encrypt a message
     * @param n - product of the two prime numbers
     * @return - Object with the encrypted message
     */
    public static EncryptedMessage encryptedMessage(String msg, int publicKey, int n) {
        return new EncryptedMessage(encrypt(msg, publicKey, n));
    }

    private static List<Integer> encrypt(String msg, int publicKey, int n) {
        List<Integer> slicedMessage = messageASCIISlicer(msg, n);

        List<Integer> encryptedMessage = new ArrayList<>();
        /*
         * Encrypt every sliced junk of the original message and store it inside
         * the encryptedMessage list.
         */
        slicedMessage.forEach(msgJnk -> encryptedMessage.add(encryptASCIIMessage(msgJnk, publicKey, n)));
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
    private static Integer encryptASCIIMessage(Integer msg, int publicKey, int n) {
        return (int) (Math.pow(msg, publicKey) % n);
    }

    /**
     * Solution to Exercise 5b:
     * A hash funktion to sign the encrypted message with P_2 and Q_2 with NAME.
     * @param msg - encrypted message
     * @param privateKey - private key of the person, who wants to sign and encrypt a message
     * @param publicKey - public key of the perwon, who wants to sign and encrypt a message
     * @param n - product of the two prime numbers
     * @return - Object with the encrypted message and the signed hash
     */
    public static EncryptedMessage encryptAndSignMessage(String msg, int privateKey, int publicKey, int n) {
        int signedHash = sign(msg.hashCode(), privateKey, n);
        return new EncryptedMessage(signedHash, encrypt(msg, publicKey, n));
    }

    /**
     * Method to sign a hash value.
     * @param hash - the hash value to sign
     * @param privateKey - private key of the person, who wants to sign the hash
     * @param n - product of the two prime numbers
     * @return the signed hash value
     */
    private static int sign(int hash, int privateKey, int n) {
        return squareAndMultiply(hash, privateKey, n);
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
