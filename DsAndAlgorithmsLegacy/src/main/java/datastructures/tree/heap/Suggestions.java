package datastructures.tree.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given a list of dict words and a query, we have to suggest words from the dict based on the
 * query, at any time we have start suggesting after the user has typed in the first 2 letters of
 * the query.
 * Ex:
 * numreviews = 5
 * repo= [ "mobile", "mouse", "moneypot", "monitor", "mousepad" ]
 * customerQuery = "mouse"
 * <p>
 * Output:
 * ["mobile", "moneypot", "monitor"]
 * ["mouse", "mousepad"]
 * ["mouse", "mousepad"]
 * ["mouse", "mousepad"]
 * <p>
 * <p>
 * Algorithm :
 * We can use priority queue or sorted list for iterate over the dict and return back the
 * suggestions
 * <p>
 * Complexity:
 * Time - O(m*n) m - size of dict, n - size of letters
 * Space - O(m)
 *
 * <p>
 * Date: 17/04/20
 *
 * @author Kushal Roy
 * <p>
 * References
 * https://leetcode.com/problems/search-suggestions-system/discuss/580023/2-simple-JAVA-solutions-with-detailed-explanation
 */
public class Suggestions {

  public List<List<String>> suggestions(List<String> repository,
      String customerQuery) {

    if (customerQuery == null || customerQuery.length() <= 0
        || repository == null || repository.size() <= 0) {
      return new ArrayList<>();
    }

    List<List<String>> suggestions = new ArrayList<>();
    customerQuery = customerQuery.toLowerCase();
    repository =
        repository.parallelStream().map(String::toLowerCase).sorted().collect(Collectors.toList());

    //Start with query at least of length 2
    for (int i = 2; i < customerQuery.length(); i++) {
      List<String> matches = new ArrayList<>();

      for (String m : repository) {
        if (m.startsWith(customerQuery.substring(0, i))) {
          matches.add(m);
        }
      }

      //If there are more than 3 matches take top 3, matches would already be sorted as repo is
      // sorted
      if (matches.size() > 3) {
        matches = matches.stream().limit(3).collect(Collectors.toList());
      }

      suggestions.add(matches);
    }

    return suggestions;
  }

  public static void main(String[] args) {

    List<String> repo = new ArrayList<>(
        Arrays.asList("mobile", "mouse", "moneypot", "monitor", "mousepad"));
    String query = "mouse";

    Suggestions sg = new Suggestions();
    System.out.println("Suggestions : " + sg.suggestions(repo, query));

  }

}
