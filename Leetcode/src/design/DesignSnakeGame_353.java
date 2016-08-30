package design;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * <p>
 * DesignSnakeGame_353
 * </p>
 *
 * @author qiyi
 * @version 2016Äê6ÔÂ16ÈÕ
 */
public class DesignSnakeGame_353 {
    private int s = 0;
    private int round = 0;
    private int[][] food;
    private int width;
    private int height;
    private int r = 0;
    private int c = 0;
    //private boolean[][] board; memory limit
    private HashSet<String> pos = new HashSet<String>();
    private LinkedList<int[]> snack = new LinkedList<int[]>();
    
    /** Initialize your data structure here.
        @param width - screen width
        @param height - screen height 
        @param food - A list of food positions
        E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    public DesignSnakeGame_353(int width, int height, int[][] food) {
        this.food = food;
        this.width = width;
        this.height = height;
        //board = new boolean[height][width];
    }
    
    /** Moves the snake.
        @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down 
        @return The game's score after the move. Return -1 if game over. 
        Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        char d = direction.charAt(0);
        switch(d){
            case 'U' : r--; break;
            case 'L' : c--; break;
            case 'R' : c++; break;
            case 'D' : r++; break;
            default: return -1;
        }
        String posStr = r + "_" + c;
        if (c < 0 || c == width || r < 0 || r == height || pos.contains(posStr)) return -1;
        if (round != food.length && r == food[round][0] && c == food[round][1]){
            // food may eat up
            //board[r][c] = true;
            pos.add(posStr);
            snack.addLast(new int[]{r, c});
            round++;
            s++;
            return s;
        }
        else {
            //board[r][c] = true;
            pos.add(posStr);
            snack.addLast(new int[]{r, c});
            int[] p = snack.removeFirst();
            //board[p[0]][p[1]] = false;
            pos.remove(p[0] + "_" + p[1]);
            return s;
        }
    }
}
