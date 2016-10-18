package bitManipulate;

/**
 * <p>
 * UTF8Validation_393
 * </p>
 *
 * @author qiyi
 * @version 2016Äê10ÔÂ4ÈÕ
 */
public class UTF8Validation_393 {
    // 10000000 128
    // 11000000 192
    // 11100000 224
    // 11110000 240
    public boolean validUtf8(int[] data) {
        int p = 0;
        while(p < data.length){
            int bytes= 0;
            if (data[p] < 192) {
                if (data[p] >= 128) return false;
                bytes = 1;
            }
            else if (data[p] < 224) bytes = 2;
            else if (data[p] < 240) bytes = 3;
            else bytes = 4;
            if (p + bytes > data.length) return false;
            int end = p + bytes - 1;
            for (p = p + 1; p <= end; p++){
                if (data[p] < 128 || data[p] >= 192) return false;
            }
        }
        return true;
    }
}
