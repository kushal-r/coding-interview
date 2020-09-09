package datastructures.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given two sentences words1, words2 (each represented as an array of strings), and a list of
 * similar word pairs pairs, determine if two sentences are similar. For example, "great acting
 * skills" and "fine drama talent" are similar, if the similar word pairs are pairs = [["great",
 * "fine"], ["acting","drama"], ["skills","talent"]]. Note that the similarity relation is not
 * transitive. For example, if "great" and "fine" are similar, and "fine" and "good" are similar,
 * "great" and "good" are not necessarily similar.
 * <p>
 * However, similarity is symmetric. For example, "great" and "fine" being similar is the same as
 * "fine" and "great" being similar. Also, a word is always similar with itself. For example, the
 * sentences words1 = ["great"], words2 = ["great"], pairs = [] are similar, even though there are
 * no specified similar word pairs. Finally, sentences can only be similar if they have the same
 * number of words. So a sentence like words1 = ["great"] can never be similar to words2 =
 * ["doubleplus","good"].
 * <p>
 * Algorithm: Do simple string comparison.
 * <p>
 * Complexity: Time - O(n+p)= O(n) [n- size of words, p - size of pair] Space - O(2n) = O(n)
 *
 * @author kushal References: https://leetcode.com/problems/sentence-similarity/
 */
public class SentenceSimilarity {

  public boolean areSentencesSimilar(String[] word1, String[] word2, List<List<String>> pairs) {
    if (word1 == null || word2 == null || word1.length != word2.length) {
      return false;
    }

    for (int i = 0; i < word1.length; i++) {
      List<String> tmpPair1 = new ArrayList<>(Arrays.asList(word1[i], word2[i]));
      List<String> tmpPair2 = new ArrayList<>(Arrays.asList(word2[i], word1[i]));

      if (!word1[i].equals(word2[i]) && !pairs.contains(tmpPair1) && !pairs.contains(tmpPair2)) {
        return false;
      }
    }

    return true;
  }

  public static void main(String[] args) {
    String[] word1 = {"great"};
    String[] word2 = {"great"};
    List<List<String>> pairs = new ArrayList<>();

    SentenceSimilarity ss = new SentenceSimilarity();
    System.out.println(ss.areSentencesSimilar(word1, word2, pairs));

  }

}
