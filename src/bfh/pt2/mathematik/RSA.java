package bfh.pt2.mathematik;

import javax.xml.stream.events.Characters;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RSA {

    /**
     * Creates a key object for the input primes and the public key
     * @param p - first prime factor
     * @param q - second prime factor
     * @param e - public key
     * @return key object with private key, public key and n
     */
    public static Key getKeys(int p, int q, int e) {
        int n = p * q;
        return new Key(n, createPrivateKey(p, q, e), e);
    }

    private static int createPrivateKey(int p, int q, int e) {
        int privateKey = 1;
        while (((privateKey * e) % ((p-1) * (q-1))) != 1 || e == privateKey) { privateKey++; }
        return privateKey;
    }

    /**
     * Encrypts a message with the given keys
     * @param msg - message to encrypt
     * @param key - key object
     * @return EncryptedMessage Object
     */
    public static EncryptedMessage encryptedMessage(String msg, Key key) {
        return new EncryptedMessage(encrypt(msg, key.publicKey, key.n));
    }

    private static List<Integer> encrypt(String msg, int publicKey, int n) {
        /*
         * Break down the whole message in smaller parts. I'm using individual letters, because
         * otherwise I couldn't calculate it without using BigInteger.
         */
        List<Integer> slicedMessage = new ArrayList<>();
        for (char letter : msg.toCharArray()) {
            slicedMessage.add((int) letter);
        }

        List<Integer> encryptedMessage = new ArrayList<>();
        /*
         * Encrypt every sliced part of the original message and store it inside
         * the encryptedMessage list.
         */
        slicedMessage.forEach(subMsg -> encryptedMessage.add(encryptASCIIMessage(subMsg, publicKey, n)));
        return encryptedMessage;
    }

    private static Integer encryptASCIIMessage(Integer msg, int publicKey, int n) {
        return squareAndMultiply(msg, publicKey, n);
    }

    /**
     * Creates a signed hash for a given message and the keys and also encrypt the message with the key
     * @param msg - encrypted message
     * @param key - Key object with private and public key and n
     * @return - Object with the encrypted message and the signed hash
     */
    public static EncryptedMessage encryptAndSignMessage(String msg, Key key) {
        int signedHash = sign(msg.hashCode(), key.privateKey, key.n);
        return new EncryptedMessage(signedHash, encrypt(msg, key.publicKey, key.n));
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
        long result = 1;
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
        return  (int) (result % modulo);
    }

    private static int getTwoPotency(int power) {
        return (int) Math.pow(2, power);
    }

    /**
     * Decrypte an encrypted message with the given keys
     * @param msg - encrypted message
     * @param key - Key object with private and public key and n
     * @return the encrypted message
     */
    public static String decryptMessage(EncryptedMessage msg, Key key) {
        StringBuilder sb = new StringBuilder();
        msg.message.forEach(junk -> sb.append(decrypt(junk, key.privateKey, key.n)));
        return sb.toString();
    }

    private static char decrypt(int encryptedMsg, int privateKey, int n) {
        /*
         * Doesn't worked for me without BigInteger
         * String decryptedMsg = String.valueOf(squareAndMultiply(encryptedMsg, privateKey, n));
         */
        BigInteger decryptedMsg = BigInteger.valueOf(encryptedMsg).modPow(BigInteger.valueOf(privateKey), BigInteger.valueOf(n));
        return (char) decryptedMsg.intValue();
    }
}
