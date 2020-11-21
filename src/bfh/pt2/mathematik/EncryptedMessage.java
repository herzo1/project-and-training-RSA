package bfh.pt2.mathematik;

import java.util.List;

public class EncryptedMessage {
    public final Integer signedHash;
    public final List<Integer> message;

    public EncryptedMessage(Integer signedHash, List<Integer> message) {
        this.signedHash = signedHash;
        this.message = message;
    }

    public EncryptedMessage(List<Integer> message) {
        this(null, message);
    }
}
