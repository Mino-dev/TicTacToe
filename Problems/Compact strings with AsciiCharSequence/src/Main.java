//import java.nio.charset.StandardCharsets;
import java.util.*;

class AsciiCharSequence implements CharSequence/* extends/implements */ {

    private final byte[] charArray;

    public AsciiCharSequence(byte[] charArray) {
        this.charArray = charArray.clone();
    }

    @Override
    public int length() {
        return this.charArray.length;
    }

    @Override
    public char charAt(int index) {
        return (char) charArray[index];
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return new AsciiCharSequence(Arrays.copyOfRange(this.charArray, start, end));
    }

    @Override
    public String toString() {
        return new String(this.charArray);
    }

}
