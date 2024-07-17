package algorithms.backtracking;

/**
 * Given a square board of size [N][N], we need to find if there exists an arrangement such that
 * N queens can be placed on the board in such a way that no two queens kill each other.
 * <p>
 * Algorithm:
 * Backtracking - We start from the first row in the 1st column and try each row one by one and
 * see if there exists a way to place all N queens, if so we return the solution. Otherwise we
 * backtrack and un-mark the last visited cell and continue with the other arrangements, till we
 * reach the end of board or all queens have been placed on the board.
 * <p>
 * Complexity:
 * Time - O(N!) In each recursive call the branch factor decreases by 1, so the recursion can be
 * thought of as N nested loops, where in each loop the number of iterations decreases by 1
 * N * (T(N-1) + O(1)) = N * (N-1) * (N-2).... = O(N!)
 *
 * <p>
 * Date: 24/06/20
 *
 * @author Kushal Roy
 * <p>
 * Referances:
 * https://www.geeksforgeeks.org/n-queen-problem-backtracking-3/
 */
public class NQueensProblem {

  /**
   * Utility Method will be called each time before placing a queen.
   * This will be called when queens has already been placed on left [col - 1] cells, so we need to
   * check only left, top diagonal , bottom diagonal of col
   *
   * @param board
   * @param row
   * @param col
   * @return
   */
  public boolean isSafe(int[][] board, int row, int col) {

    //Left cols
    for (int i = 0; i <= col; i++) {
      if (board[row][i] == 1) {
        return false;
      }
    }

    //Top Diagonal
    for (int i = 0, j = 0; i <= col && j <= row; i++, j++) {
      if (board[i][j] == 1) {
        return false;
      }
    }

    //Bottom diagonal
    for (int i = row, j = col; i < board.length && j >= 0; i++, j--) {
      if (board[i][j] == 1) {
        return false;
      }
    }
    return true;
  }


  public void placeQueen(int n) {
    int[][] board = new int[n][n];
    int queenCount = 0;

    if (placeQueenHelper(board, 0, 0, queenCount)) {
      for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[0].length; j++) {
          System.out.print(String.format("%d", board[i][j]) + "  ");
        }
        System.out.println();
      }
    } else {
      System.out.println("No solution exists");
    }

  }

  public boolean placeQueenHelper(int[][] board, int row, int col, int queenCount) {

    //If all queens are places or we have already traversed the last column return
    if (col >= board.length) {
      return true;
    }

    for (int i = 0; i < board.length; i++) {
      if (isSafe(board, i, col)) {
        board[i][col] = 1;
        if (placeQueenHelper(board, i, col + 1, queenCount)) {
          return true;
        }
        //Backtrack
        board[i][col] = 0;
      }
    }

    return false;
  }

  public static void main(String[] args) {

    NQueensProblem nqp = new NQueensProblem();
    nqp.placeQueen(4);
  }
}
