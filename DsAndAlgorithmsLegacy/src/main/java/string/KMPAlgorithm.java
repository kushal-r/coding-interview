package string;

/**
 * @author kushal
 * Date - 07/09/20
 * <p>
 * References:
 * https://www.youtube.com/watch?v=GTJr8OvyEVQ&ab_channel=TusharRoy-CodingMadeSimple
 * https://www.youtube.com/watch?v=V5-7GzOfADQ&ab_channel=AbdulBari
 * https://www.educative.io/edpresso/what-is-the-knuth-morris-pratt-algorithm
 */
public class KMPAlgorithm {

  public boolean search(String str, String pattern) {
    if (str == null || str.isEmpty() || pattern == null || pattern.isEmpty()) {
      throw new IllegalArgumentException("String or pattern cannot be null or empty");
    }

    int[] pi = calculatePi(pattern.toCharArray());
    char[] strChar = str.toCharArray();
    char[] patternChar = pattern.toCharArray();

    int i = 0, j = 0;

    while (i < str.length() && j < pattern.length()) {
      //If last char in pattern matches then return
      if (strChar[i] == patternChar[j] && j == pattern.length() - 1) {
        return true;
      } else if (strChar[i] == patternChar[j]) {
        i++;
        j++;
      } else {
        //If character didn't match -> Backtrack
        if (j == 0) {
          //If none of the chars n pattern matched the given str
          i++;
        } else {
          //Move the idx in pattern to the value of last seen char
          j = pi[j - 1];
        }

      }
    }

    return false;
  }


  public int[] calculatePi(char[] s) {
    int[] pi = new int[s.length];
    int i = 0;
    int j = 1;

    while (j < s.length) {
      if (s[j] == s[i]) {
        i++;
        pi[j] = i;
        j++;
        // The first character didn't match:
      } else if (i == 0) {
        pi[j] = 0;
        j++;
      } else {
        // Mismatch after at least one matching character
        i = pi[i - 1];
      }
    }

    return pi;
  }


  public static void main(String[] args) {
    KMPAlgorithm kmp = new KMPAlgorithm();

    System.out.println(kmp.search("hayhello", "hell"));
  }
}
