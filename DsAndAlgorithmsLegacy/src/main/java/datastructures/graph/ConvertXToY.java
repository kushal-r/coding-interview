package datastructures.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Given a number X, we need to count the number of operations needed to convert it to Y, using
 * the specified operations, in this case:
 * 1) Multiply by 2
 * 2) Subtract 1
 * <p>
 * Algorithm:
 * We will use BFS, starting at X, we subtract 1 to get adj. vertex, we multiply by 2, to
 * get adjacent vertex,update count of the new vertex as the sum of parent vertex count +1, we
 * repeat this till we find a number equal to Y.
 *
 * <p>
 * Date: 20/03/20
 *
 * @author Kushal Roy
 * <p>
 * Referances:
 * https://www.geeksforgeeks.org/minimum-number-operation-required-convert-number-x-y/
 */
public class ConvertXToY {

  public void bfs(Step src, Set<Integer> visited, Queue<Step> q) {
    int sub = src.val - 1;
    int mul = src.val * 2;

    if (sub > 0 && !visited.contains(sub)) {
      visited.add(sub);
      q.offer(new Step(sub, src.steps + 1));
    }
    if (!visited.contains(mul)) {
      visited.add(mul);
      q.offer(new Step(mul, src.steps + 1));
    }

  }

  public int countSteps(int x, int y) {
    Set<Integer> visited = new HashSet<>();
    Queue<Step> q = new LinkedList<>();

    Step src = new Step(x, 0);

    q.offer(src);
    visited.add(src.val);

    while (!q.isEmpty()) {
      Step curr = q.poll();

      if (curr.val == y) {
        return curr.steps;
      }

      bfs(curr, visited, q);

    }

    return -1;
  }

  public static void main(String[] args) {
    int x = 4, y = 7;
    ConvertXToY convertXToY = new ConvertXToY();
    System.out.println(convertXToY.countSteps(x, y));
  }


  private static class Step {

    private int val;
    private int steps;

    public Step(int val, int steps) {
      this.val = val;
      this.steps = steps;
    }
  }

}
