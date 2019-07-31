package dev.jlibra.extension.verifier;

import dev.jlibra.extension.MessageUtil;
import org.bouncycastle.crypto.params.Ed25519PublicKeyParameters;
import org.bouncycastle.crypto.signers.Ed25519Signer;
import org.bouncycastle.jcajce.provider.digest.SHA3;

import java.util.Arrays;

public class Verifier {

    public static boolean isSignedByAddress(byte[] libraAddress, byte[] publicKey, byte[] bytesToSign, byte[] signature) {
        SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest256();
        if (verifyMessage(publicKey, bytesToSign, signature)) {
            return Arrays.equals(digestSHA3.digest(publicKey), libraAddress);
        }
        return false;
    }

    public static boolean verifyMessage(byte[] publicKey, byte[] bytesToSign, byte[] signature) {
        SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest256();
        byte[] message = MessageUtil.prepareMessage("Message@@$$LIBRA$$@@", bytesToSign);
        try {
            Ed25519Signer signer = new Ed25519Signer();
            signer.init(false, new Ed25519PublicKeyParameters(publicKey, 0));
            signer.update(digestSHA3.digest(message), 0, 32);
            return signer.verifySignature(signature);
        } catch (Exception e) {
            throw new RuntimeException("Verification of payload failed", e);
        }
    }
}
