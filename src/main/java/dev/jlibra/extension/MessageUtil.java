package dev.jlibra.extension;

import org.bouncycastle.jcajce.provider.digest.SHA3;

import java.nio.ByteBuffer;

public abstract class MessageUtil {

    public static byte[] prepareMessage(String prefix, byte[] bytesToSign) {
        SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest256();
        byte[] saltDigest = digestSHA3.digest(prefix.getBytes());
        return ByteBuffer.allocate(saltDigest.length + bytesToSign.length)
                .put(saltDigest)
                .put(bytesToSign)
                .array();
    }
}
