package dev.jlibra.extension.signing;

import dev.jlibra.KeyUtils;
import dev.jlibra.extension.signer.Signer;
import dev.jlibra.extension.verifier.Verifier;
import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.junit.Before;
import org.junit.Test;

import java.nio.charset.Charset;
import java.security.PrivateKey;
import java.security.Security;

import static junit.framework.TestCase.assertTrue;

public class SignAndVerify {

    private static final String PRIVATE_KEY_HEX = "3051020101300506032b6570042204206dadf7a252c0e74add2e545a1e3c811f1f4bdd88f8c5e0080e068f4df6d909128121000b29a7adce0897b2d1ec18cc482237463efa173945fa3bd2703023e1a2489021";

    @Before
    public void setup() {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    }

    @Test
    public void testSignatureVerification() {
        PrivateKey privateKey = KeyUtils.privateKeyFromHexString(PRIVATE_KEY_HEX);
        SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest256();
        byte[] encodedPrivKey = digestSHA3.digest(privateKey.getEncoded());
        byte[] encodedPubKey = new Ed25519PrivateKeyParameters(encodedPrivKey, 0).generatePublicKey().getEncoded();
        byte[] signature = Signer.signMessage(encodedPrivKey, "h3ll0".getBytes(Charset.forName("UTF-8")));
        boolean isSignatureVerified = Verifier.verifyMessage(encodedPubKey, "h3ll0".getBytes(Charset.forName("UTF-8")), signature);
        assertTrue("Signature cannot be verified.", isSignatureVerified);
    }

    @Test
    public void testSignatureAddressVerification() {
        PrivateKey privateKey = KeyUtils.privateKeyFromHexString(PRIVATE_KEY_HEX);
        SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest256();
        byte[] encodedPrivKey = digestSHA3.digest(privateKey.getEncoded());
        byte[] encodedPubKey = new Ed25519PrivateKeyParameters(encodedPrivKey, 0).generatePublicKey().getEncoded();
        byte[] libraAddress = digestSHA3.digest(encodedPubKey);
        byte[] signature = Signer.signMessage(encodedPrivKey, "h3ll0".getBytes(Charset.forName("UTF-8")));
        boolean isSignedByAddress = Verifier.isSignedByAddress(libraAddress, encodedPubKey, "h3ll0".getBytes(Charset.forName("UTF-8")), signature);
        assertTrue("Signature cannot be verified.", isSignedByAddress);
    }

}
