package algorithms.backtracking;

/**
 * The knight in a chess-board can move on 8 different direction in each move. Give a chess-board
 * and the knight placed on the first block of the board find the sequence of moves that the
 * knight should make so as to visit each of the blocks of the board only once.
 * <p>
 * Algorithm :
 * Backtracking - We start of the 1st block from the board and for every move we check if all the
 * blocks have been visited, if so print the path.
 * Else, we recursively pick the next available block and if this block reaches a solution we
 * return true, else return false and we pick another block and continue.
 * If none of the chosen blocks reaches a solution, return false.
 * <p>
 * <p>
 * Complexity:
 * Time - O(8^(n^(2-1))) Each step has 8 options, and this has to be done for each cell
 * Space - O(n^2) nXn - size of the board
 * <p>
 * Date: 05/06/20
 *
 * @author Kushal Roy
 * <p>
 * References:
 * https://www.geeksforgeeks.org/the-knights-tour-problem-backtracking-1/
 */
public class KnightsTour {


  private static final int[] xMoves = {1, 2, 2, 1, -1, -2, -2, -1};
  private static final int[] yMoves = {2, 1, -1, -2, -2, -1, 1, 2};


  public boolean isSafe(int[][] board, int x, int y) {

    if (x >= 0 && x < board.length && y >= 0 && y < board[0].length && board[x][y] == -1) {
      return true;
    }
    return false;
  }

  public boolean tour(int[][] board) {

    //Initialize the board to -1
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        board[i][j] = -1;
      }
    }

    //Start from the 1st square in the chess board
    board[0][0] = 0;
    if (tourHelper(board, 0, 0, 1)) {
      return true;
    }
    return false;
  }

  public boolean tourHelper(int[][] board, int x, int y, int moveCount) {

    //Case : We have covered all the 64 distinct squares in the board of size 8X8
    if (moveCount == board.length * board[0].length) {
      return true;
    }

    //Iterate over all the possible 8 surrounding squares in the board
    for (int k = 0; k < 8; k++) {
      int nextX = x + xMoves[k];
      int nextY = y + yMoves[k];
      if (isSafe(board, nextX, nextY)) {
        board[nextX][nextY] = moveCount;

        //If this is a valid square in the chess board
        if (tourHelper(board, nextX, nextY, moveCount + 1)) {
          return true;
        } else {
          //This was a failed route make this square as not visited
          board[nextX][nextY] = -1;
        }
      }
    }
    return false;
  }

  public static void main(String[] args) {

    int[][] board = new int[8][8];
    KnightsTour kt = new KnightsTour();

    if (kt.tour(board)) {
      for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[0].length; j++) {
          System.out.print(String.format("%02d", board[i][j]) + "  ");
        }
        System.out.println();
      }
    } else {
      System.out.println("No valid path found.");
    }

  }
}
