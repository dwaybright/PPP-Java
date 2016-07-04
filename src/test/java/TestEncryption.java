import Passcode.Card.BasicCardFactoryImpl;
import Passcode.Card.CardFactory;
import Passcode.Passcode;
import Passcode.BasicPasscodeImpl;
import Passcode.BasicCharsetImpl;
import org.junit.Test;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;
import java.util.Base64;


public class TestEncryption {

    @Test
    public void TestAES128() {
        try {
            Passcode passCode128 = BuildAES(128);

            // Set Vars
            int passcodeIndex = 128;

            // Generate pCode
            String pCode = passCode128.GetPassCode(4, passcodeIndex);

            // Print out test message
            System.out.println("Testing AES 128 bit");
            System.out.println("Secret Key: " + Base64.getEncoder().encodeToString(passCode128.GetSecretKey().getEncoded()));
            System.out.println("Init Vector: " + Base64.getEncoder().encodeToString(passCode128.GetIvSpec().getIV()));
            System.out.println("Code: " + pCode);
            System.out.println();

        } catch(Exception ex){
            System.out.println(ex.toString());
        }
    }

    @Test
    public void TestAES256() {
        try {
            Passcode passCode256 = BuildAES(256);

            // Set Vars
            int passcodeIndex = 256;

            // Generate pCode
            String pCode = passCode256.GetPassCode(4, passcodeIndex);

            // Print out test message
            System.out.println("Testing AES 256 bit");
            System.out.println("Secret Key: " + Base64.getEncoder().encodeToString(passCode256.GetSecretKey().getEncoded()));
            System.out.println("Init Vector: " + Base64.getEncoder().encodeToString(passCode256.GetIvSpec().getIV()));
            System.out.println("Code: " + pCode);
            System.out.println();

        } catch(Exception ex){
            System.out.println(ex.toString());
        }
    }

    @Test
    public void TestCardOutput() {
        try{
            Passcode passCode = BuildAES(256);

            int cardIndex = 1;
            int numPerCard = 70;

            CardFactory cFac = new BasicCardFactoryImpl();
            String[] output = cFac.GenerateCard(passCode, cardIndex, numPerCard);

        } catch(Exception ex) {
            System.out.println(ex.toString());
        }
    }


    private Passcode BuildAES(int bits) {
        try {
            // Generate secret key
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(bits);
            SecretKey skey = kgen.generateKey();

            // Generate initialization vector
            byte[] iv = new byte[16];
            SecureRandom srand = new SecureRandom();
            srand.nextBytes(iv);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            // Build passCodeGen (this would identify to a User)
            return new BasicPasscodeImpl(new BasicCharsetImpl(), skey, ivSpec);
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

}
