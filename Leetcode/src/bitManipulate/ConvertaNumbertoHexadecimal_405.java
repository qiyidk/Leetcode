package bitManipulate;

/**
 * <p>
 * ConvertaNumbertoHexadecimal_405
 * </p>
 *
 * @author qiyi
 * @version 2016Äê10ÔÂ4ÈÕ
 */
public class ConvertaNumbertoHexadecimal_405 {
    public String toHex(int num) {
        if (num == 0) return "0";// corner case
        StringBuilder sb = new StringBuilder();
        int mask = 0xf; //mask to get the least significant 4 bits
        while(num != 0){
            char c = getHexChar(num & mask);
            sb.append(c);
            num >>>= 4;
        }
        return sb.reverse().toString();
    }
    private char getHexChar(int i){
        switch(i){
            case 10: return 'a';
            case 11: return 'b';
            case 12: return 'c';
            case 13: return 'd';
            case 14: return 'e';
            case 15: return 'f';
            default: return (char)(i + '0');
        }
    }
}
