package Passcode;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
 * Created by danielwaybright on 7/3/16.
 */
public interface Passcode {

    public String GetPassCode(Charset charset, SecretKey skey, IvParameterSpec ivSpec, int passcodeLength, int passcodeIndex);

}
