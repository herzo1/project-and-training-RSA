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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (signedHash != null) {
            sb.append("Signed Hash: ")
                .append(this.signedHash)
                .append("\n");
        }
        sb.append("Encrypted Message:\n");
        message.forEach(junk -> sb.append(junk).append("\n"));

        return sb.toString();
    }
}
