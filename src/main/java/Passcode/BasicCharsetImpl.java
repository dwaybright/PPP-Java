package Passcode;


/**
 * Created by danielwaybright on 7/3/16.
 */
public class BasicCharsetImpl implements Charset {

    public static String[] charSet = {
        "!", "#", "%", "+", "2", "3", "4", "5", "6", "7", "8", "9", ":", "=", "?", "@",
        "A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "R", "S",
        "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i",
        "j", "k", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
    };

    public String[] getCharset() {
        return charSet;
    }

    public int getCharsetSize() {
        return charSet.length;
    }
}
