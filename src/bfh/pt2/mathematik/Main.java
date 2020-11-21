package bfh.pt2.mathematik;


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
        int publicKey, privateKey, n;
        EncryptedMessage encryptedMessage;

        // Exercise 5a:
        publicKey = E_1;
        n = P_1 * Q_1;
        encryptedMessage = RSA.encryptedMessage(M_1, publicKey, n);

        // Exercise 5b:
        privateKey = (P_2 - 1) * (Q_2 - 1);
        publicKey = E_1;
        n = P_2 * P_1;
        encryptedMessage = RSA.encryptAndSignMessage(NAME, privateKey, publicKey, n);


    }
}
