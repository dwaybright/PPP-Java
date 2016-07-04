package Passcode;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;


public interface Passcode {

    String GetPassCode(int passcodeLength, int passcodeIndex);

    Charset GetCharset();

    SecretKey GetSecretKey();

    IvParameterSpec GetIvSpec();

}
