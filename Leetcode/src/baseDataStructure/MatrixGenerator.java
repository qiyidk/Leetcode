package baseDataStructure;

/**
 * <p>
 * MatrixGenerator
 * </p>
 *
 * @author qiyi
 * @version 2016Äê6ÔÂ22ÈÕ
 */
public class MatrixGenerator {
    
    public static int[][] getMatrix(String s){
        s = s.substring(2, s.length() - 2);
        String[] k = s.split("\\],\\[");
        int n = k[0].split(",").length;
        int m = k.length;
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++){
            String[] str = k[i].split(",");
            for (int j = 0; j < n; j++){
                res[i][j] = Integer.parseInt(str[j]);
            }
        }
        return res;
    }
    
    public static char[][] getCharMatrix(String s){
        s = s.substring(1, s.length() - 1);
        String[] k = s.split("\\],\\[");
        int n = k[0].length();
        int m = k.length;
        char[][] res = new char[m][n];
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                res[i][j] = k[i].charAt(j);
            }
        }
        return res;
    }

    public static void print(int[][] res){
        for (int i = 0; i < res.length; i++){
            if (i == 0) {
                System.out.print("      ");
                for (int j = 0; j < res[0].length; j++){
                    System.out.printf("%6d", j);
                }
                System.out.println();
                for (int j = 0; j < res[0].length + 1; j++){
                    System.out.print("------");
                }
                System.out.println();
            }
            for (int j = 0; j < res[0].length; j++) {
                if (j == 0) System.out.printf("%6s", i + "|");
                System.out.printf("%6d", res[i][j]);
            }
            System.out.println();
        }
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String s = "[[27,5,-20,-9,1,26,1,12,7,-4,8,7,-1,5,8],[16,28,8,3,16,28,-10,-7,-5,-13,7,9,20,-9,26],[24,-14,20,23,25,-16,-15,8,8,-6,-14,-6,12,-19,-13],[28,13,-17,20,-3,-18,12,5,1,25,25,-14,22,17,12],[7,29,-12,5,-5,26,-5,10,-5,24,-9,-19,20,0,18],[-7,-11,-8,12,19,18,-15,17,7,-1,-11,-10,-1,25,17],[-3,-20,-20,-7,14,-12,22,1,-9,11,14,-16,-5,-12,14],[-20,-4,-17,3,3,-18,22,-13,-1,16,-11,29,17,-2,22],[23,-15,24,26,28,-13,10,18,-6,29,27,-19,-19,-8,0],[5,9,23,11,-4,-20,18,29,-6,-4,-11,21,-6,24,12],[13,16,0,-20,22,21,26,-3,15,14,26,17,19,20,-5],[15,1,22,-6,1,-9,0,21,12,27,5,8,8,18,-1],[15,29,13,6,-11,7,-6,27,22,18,22,-3,-9,20,14],[26,-6,12,-10,0,26,10,1,11,-10,-16,-18,29,8,-8],[-19,14,15,18,-10,24,-9,-7,-19,-14,23,23,17,-5,6]]";
        int[][] res = getMatrix(s);
        print(res);
    }
}
