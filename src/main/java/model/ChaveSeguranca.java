package model;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

public class ChaveSeguranca {

    private PrivateKey priv;
    private PublicKey pub;

    private final static String ALGORITMO = "RSA";

    public ChaveSeguranca() {
        try {

            final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITMO);
            final SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
            keyGen.initialize(1024, random);
            KeyPair pair = keyGen.generateKeyPair();
            this.priv = pair.getPrivate();
            this.pub = pair.getPublic();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public PrivateKey getPriv() {
        return priv;
    }

    public PublicKey getPub() {
        return pub;
    }

}
