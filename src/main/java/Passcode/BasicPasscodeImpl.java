package Passcode;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.math.BigInteger;

/**
 * Created by danielwaybright on 7/3/16.
 */
public class BasicPasscodeImpl implements Passcode {

    public String GetPassCode(Charset charset, SecretKey skey, IvParameterSpec ivSpec, int passcodeLength, int passcodeIndex) {
        BigInteger divider = new BigInteger(Integer.toString(charset.getCharsetSize()));

        try {
            // Build the Cipher
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skey, ivSpec);

            // Encrypt the message, here it is the passcode index
            String message = "Encrypted Passcode Index " + Integer.toString(passcodeIndex);
            byte[] encrypted = cipher.doFinal(message.getBytes());
            BigInteger encryptedBigInt = new BigInteger(encrypted);

            // Build passcode through successive dividing
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

}
