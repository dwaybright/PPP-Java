package Passcode;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.math.BigInteger;

public class BasicPasscodeImpl implements Passcode {

    private Charset charset;
    private SecretKey skey;
    private IvParameterSpec ivSpec;

    public BasicPasscodeImpl(Charset set, SecretKey skey, IvParameterSpec ivSpec) {
        this.charset = set;
        this.skey = skey;
        this.ivSpec = ivSpec;
    }

    public String GetPassCode(int passcodeLength, int passcodeIndex) {
        try {
            // Setup the amount to divide by
            BigInteger divider = new BigInteger(Integer.toString(charset.getCharsetSize()));

            // Build the Cipher
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skey, ivSpec);

            // Encrypt the message, here it is the passcode index
            String message = "Encrypted Passcode Index " + Integer.toString(passcodeIndex);
            byte[] encrypted = cipher.doFinal(message.getBytes());
            BigInteger encryptedBigInt = new BigInteger(encrypted);

            // Build passcode through successive dividing of message
            StringBuilder output = new StringBuilder();

            for(int i=0; i < passcodeLength; i++) {
                int remainder = encryptedBigInt.mod(divider).intValueExact();

                output.append(charset.getCharset()[remainder]);

                encryptedBigInt = encryptedBigInt.divide(divider);
            }

            return output.toString();
        } catch(Exception ex) {
            System.out.println(ex.toString());
        }

        return null;
    }

    public Charset GetCharset() { return this.charset; }

    public SecretKey GetSecretKey() { return this.skey; }

    public IvParameterSpec GetIvSpec() { return this.ivSpec; }

}
