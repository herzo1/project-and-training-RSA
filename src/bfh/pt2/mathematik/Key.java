package bfh.pt2.mathematik;

public class Key {
    public final int n;
    public final int privateKey;
    public final int publicKey;

    public Key(int n, int privateKey, int publicKey) {
        this.n = n;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }
}
