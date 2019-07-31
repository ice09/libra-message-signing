package dev.jlibra.extension.signer;

import dev.jlibra.extension.MessageUtil;
import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters;
import org.bouncycastle.crypto.signers.Ed25519Signer;
import org.bouncycastle.jcajce.provider.digest.SHA3;

public class Signer {

    public static byte[] signMessage(byte[] privateKey, byte[] bytesToSign) {
        SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest256();
        byte[] message = MessageUtil.prepareMessage("Message@@$$LIBRA$$@@", bytesToSign);
        try {
            Ed25519Signer signer = new Ed25519Signer();
            signer.init(true, new Ed25519PrivateKeyParameters(privateKey, 0));
            signer.update(digestSHA3.digest(message), 0, 32);
            return signer.generateSignature();
        } catch (Exception e) {
            throw new RuntimeException("Verification of payload failed", e);
        }
    }

}
