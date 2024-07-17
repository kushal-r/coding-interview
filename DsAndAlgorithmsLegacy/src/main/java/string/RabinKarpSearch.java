package string;

/**
 * Given a text txt[0..n-1] and a pattern pat[0..m-1], write a function search(char pat[], char
 * txt[]) that prints all occurrences of pat[] in txt[]. You may assume that n > m.
 * <p>
 * Input:  txt[] = "THIS IS A TEST TEXT"
 * pat[] = "TEST"
 * Output: Pattern found at index 10
 * <p>
 * Input:  txt[] =  "AABAACAADAABAABA"
 * pat[] =  "AABA"
 * Output: Pattern found at index 0
 * Pattern found at index 9
 * Pattern found at index 12
 * <p>
 * <p>
 * Algorithm:
 * We have to calculate the rolling hash of the patternLen chars from the txt to be searched, and
 * then one by one remove the left most char and another char from the right till we reach the end
 * of the txt with less than patternLen chars. We will be re using the same hash to calculate the
 * new hash.
 * <p>
 * Complexity:
 * Time - O(mn) [Length of txt and len of pattern in case of hash collisions]
 * Space - O(1)
 *
 * @author kushal
 * <p>
 * References:
 * https://www.youtube.com/watch?v=H4VrKHVG5qI&ab_channel=TusharRoy-CodingMadeSimple
 */
public class RabinKarpSearch {

  private static final int prime = 101;


  public int patternSearch(char[] txt, char[] pattern) {
    int patternLen = pattern.length;
    int txtLen = txt.length;

    long patternHash = calculateHash(pattern, patternLen - 1);
    long txtHash = calculateHash(txt, patternLen - 1);

    //Loop till we reach the end of the txt with only patternLen chars remaining at the end
    for (int i = 1; i <= txtLen - patternLen + 1; i++) {
      if (patternHash == txtHash && checkEqual(txt, i - 1, i + patternLen - 2,
          pattern,
          0,
          patternLen - 1)) {
        return i - 1;
      }

      if (i < txtLen - patternLen + 1) {
        txtHash = reCalculateHash(txt, txtHash, i - 1, i + patternLen - 1, patternLen);
      }
    }

    return -1;
  }


  public long calculateHash(char[] txt, int endIdx) {
    long hash = 0L;
    for (int i = 0; i <= endIdx; i++) {
      hash = hash + (long) (txt[i] * Math.pow(prime, i));
    }

    return hash;
  }


  public long reCalculateHash(char[] txt, long oldHash, int oldIdx, int newIdx, int patternLen) {
    long newHash = oldHash - txt[oldIdx];
    newHash = newHash / prime;
    newHash += txt[newIdx] * Math.pow(prime, patternLen - 1);

    return newHash;
  }


  public boolean checkEqual(char[] str1, int start1, int end1, char[] str2, int start2, int end2) {
    if (end1 - start1 != end2 - start2) {
      return false;
    }

    while (start1 <= end1 && start2 <= end2) {
      if (str1[start1] != str2[start2]) {
        return false;
      }

      start1++;
      start2++;
    }

    return true;
  }


  public static void main(String[] args) {
    RabinKarpSearch rks = new RabinKarpSearch();
    System.out.println(rks.patternSearch("GEEKS FOR GEEKS".toCharArray(), " GEEKS".toCharArray()));
    System.out.println(rks.patternSearch("GEEKS FOR GEEKS".toCharArray(), "GEEKS".toCharArray()));
    System.out.println(rks.patternSearch("GEEKS FOR GEEKS".toCharArray(), "FG".toCharArray()));

  }

}
