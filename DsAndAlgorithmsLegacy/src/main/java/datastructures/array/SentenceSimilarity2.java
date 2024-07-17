package datastructures.array;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given two sentences words1, words2 (each represented as an array of strings), and a list of
 * similar word pairs pairs, determine if two sentences are similar. For example, words1 = ["great",
 * "acting", "skills"] and words2 = ["fine", "drama", "talent"] are similar, if the similar word
 * pairs are pairs = [["great", "good"], ["fine", "good"], ["acting","drama"],
 * ["skills","talent"]].
 * <p>
 * Note that the similarity relation is transitive. For example, if "great" and "good" are similar,
 * and "fine" and "good" are similar, then "great" and "fine" are similar. Similarity is also
 * symmetric. For example, "great" and "fine" being similar is the same as "fine" and "great" being
 * similar. Also, a word is always similar with itself. For example, the sentences words1 =
 * ["great"], words2 = ["great"], pairs = [] are similar, even though there are no specified similar
 * word pairs. Finally, sentences can only be similar if they have the same number of words. So a
 * sentence like words1 = ["great"] can never be similar to words2 = ["doubleplus","good"].
 * <p>
 * Algorithm: Do simple string comparison.
 * <p>
 * Complexity:
 * Time - O(n+p)= O(n) [n- size of words, p - size of pair]
 * Space - O(2n) = O(n)
 *
 * @author kushal
 * <p>
 * References:
 * https://leetcode.com/problems/sentence-similarity-ii/
 */
public class SentenceSimilarity2 {

  public boolean areSentencesSimilar(String[] word1, String[] word2, List<List<String>> pairs) {
    if (word1 == word2) {
      return true;
    }

    if (word1 == null || word2 == null || word1.length != word2.length) {
      return false;
    }

    Map<String, Set<String>> pairGraph = new LinkedHashMap<>();

    for (List<String> pair : pairs) {
      if (!pairGraph.containsKey(pair.get(0))) {
        Set<String> vertices = new HashSet<>();
        vertices.add(pair.get(1));
        pairGraph.put(pair.get(0), vertices);
      } else {
        Set<String> vertices = pairGraph.get(pair.get(0));
        vertices.add(pair.get(1));
      }

      if (!pairGraph.containsKey(pair.get(1))) {
        Set<String> vertices = new HashSet<>();
        vertices.add(pair.get(0));
        pairGraph.put(pair.get(1), vertices);
      } else {
        Set<String> vertices = pairGraph.get(pair.get(1));
        vertices.add(pair.get(0));
      }
    }

    for (int i = 0; i < word1.length; i++) {
      if (word1[i].equals(word2[i])) {
        continue;
      }

      if (pairGraph.containsKey(word1[i]) && pairGraph.containsKey(word2[i])) {
        if (!isWordSimilar(pairGraph, word1[i], word2[i]) || !isWordSimilar(pairGraph, word2[i],
            word1[i])) {
          return false;
        }
      } else {
        return false;
      }
    }

    return true;
  }


  public boolean isWordSimilar(Map<String, Set<String>> pairGraph, String word1, String
      word2) {
    Set<String> visited = new HashSet<>();
    return isWordSimilarUtil(pairGraph, word1, word2, visited);
  }


  public boolean isWordSimilarUtil(Map<String, Set<String>> pairGraph, String word1, String
      word2, Set<String> visited) {
    visited.add(word1);

    if (word1.equals(word2)) {
      return true;
    } else {
      for (String adjVertices : pairGraph.get(word1)) {
        if (!visited.contains(adjVertices)) {
          if (isWordSimilarUtil(pairGraph, adjVertices, word2, visited)) {
            return true;
          } else {
            visited.remove(adjVertices);
          }
        }
      }
    }

    return false;
  }


  public static void main(String[] args) {
    Gson gson = new Gson();
    SentenceSimilarity2 ss = new SentenceSimilarity2();

    String[] word1 = {"great", "acting", "skills"};
    String[] word2 = {"fine", "drama", "talent"};
    List<List<String>> pairs = new ArrayList<>();
    pairs.add(new ArrayList<>(Arrays.asList("great", "good")));
    pairs.add(new ArrayList<>(Arrays.asList("fine", "good")));
    pairs.add(new ArrayList<>(Arrays.asList("acting", "drama")));
    pairs.add(new ArrayList<>(Arrays.asList("skills", "talent")));
    System.out.println("Case 1[true]:" + ss.areSentencesSimilar(word1, word2, pairs));

    word1 = new String[]{"great", "acting", "skills"};
    word2 = new String[]{"fine", "painting", "talent"};
    pairs = new ArrayList<>();
    pairs.add(new ArrayList<>(Arrays.asList("great", "fine")));
    pairs.add(new ArrayList<>(Arrays.asList("drama", "acting")));
    pairs.add(new ArrayList<>(Arrays.asList("skills", "talent")));
    System.out.println("Case 2[false]:" + ss.areSentencesSimilar(word1, word2, pairs));

    word1 = new String[]{"a", "very", "delicious", "meal"};
    word2 = new String[]{"one", "really", "good", "dinner"};
    pairs = gson.fromJson(
        "[[\"great\",\"good\"],[\"extraordinary\",\"good\"],[\"well\",\"good\"],[\"wonderful\",\"good\"],[\"excellent\",\"good\"],[\"fine\",\"good\"],[\"nice\",\"good\"],[\"any\",\"one\"],[\"some\",\"one\"],[\"unique\",\"one\"],[\"the\",\"one\"],[\"an\",\"one\"],[\"single\",\"one\"],[\"a\",\"one\"],[\"truck\",\"car\"],[\"wagon\",\"car\"],[\"automobile\",\"car\"],[\"auto\",\"car\"],[\"vehicle\",\"car\"],[\"entertain\",\"have\"],[\"drink\",\"have\"],[\"eat\",\"have\"],[\"take\",\"have\"],[\"fruits\",\"meal\"],[\"brunch\",\"meal\"],[\"breakfast\",\"meal\"],[\"food\",\"meal\"],[\"dinner\",\"meal\"],[\"super\",\"meal\"],[\"lunch\",\"meal\"],[\"possess\",\"own\"],[\"keep\",\"own\"],[\"have\",\"own\"],[\"extremely\",\"very\"],[\"actually\",\"very\"],[\"really\",\"very\"],[\"super\",\"very\"]]",
        ArrayList.class);
    System.out.println("Case 3[false]:" + ss.areSentencesSimilar(word1, word2, pairs));

    word1 = new String[]{"one", "excellent", "meal"};
    word2 = new String[]{"one", "good", "dinner"};
    pairs = gson.fromJson(
        "[[\"great\",\"good\"],[\"extraordinary\",\"good\"],[\"well\",\"good\"],[\"wonderful\",\"good\"],[\"excellent\",\"good\"],[\"fine\",\"good\"],[\"nice\",\"good\"],[\"any\",\"one\"],[\"some\",\"one\"],[\"unique\",\"one\"],[\"the\",\"one\"],[\"an\",\"one\"],[\"single\",\"one\"],[\"a\",\"one\"],[\"truck\",\"car\"],[\"wagon\",\"car\"],[\"automobile\",\"car\"],[\"auto\",\"car\"],[\"vehicle\",\"car\"],[\"entertain\",\"have\"],[\"drink\",\"have\"],[\"eat\",\"have\"],[\"take\",\"have\"],[\"fruits\",\"meal\"],[\"brunch\",\"meal\"],[\"breakfast\",\"meal\"],[\"food\",\"meal\"],[\"dinner\",\"meal\"],[\"super\",\"meal\"],[\"lunch\",\"meal\"],[\"possess\",\"own\"],[\"keep\",\"own\"],[\"have\",\"own\"],[\"extremely\",\"very\"],[\"actually\",\"very\"],[\"really\",\"very\"],[\"super\",\"very\"]]",
        ArrayList.class);
    System.out.println("Case 4[true]:" + ss.areSentencesSimilar(word1, word2, pairs));
    word1 = gson.fromJson(
        "[\"this\",\"summer\",\"thomas\",\"get\",\"really\",\"very\",\"rich\",\"and\",\"have\",\"any\",\"actually\",\"wonderful\",\"and\",\"good\",\"truck\",\"every\",\"morning\",\"he\",\"drives\",\"an\",\"extraordinary\",\"truck\",\"around\",\"the\",\"nice\",\"city\",\"to\",\"eat\",\"some\",\"extremely\",\"extraordinary\",\"food\",\"as\",\"his\",\"meal\",\"but\",\"he\",\"only\",\"entertain\",\"an\",\"few\",\"well\",\"fruits\",\"as\",\"single\",\"lunch\",\"he\",\"wants\",\"to\",\"eat\",\"single\",\"single\",\"and\",\"really\",\"healthy\",\"life\"]",
        String[].class);
    word2 = gson.fromJson(
        "[\"this\",\"summer\",\"thomas\",\"get\",\"very\",\"extremely\",\"rich\",\"and\",\"possess\",\"the\",\"actually\",\"great\",\"and\",\"wonderful\",\"vehicle\",\"every\",\"morning\",\"he\",\"drives\",\"unique\",\"extraordinary\",\"automobile\",\"around\",\"unique\",\"fine\",\"city\",\"to\",\"drink\",\"single\",\"extremely\",\"nice\",\"meal\",\"as\",\"his\",\"super\",\"but\",\"he\",\"only\",\"entertain\",\"a\",\"few\",\"extraordinary\",\"food\",\"as\",\"some\",\"brunch\",\"he\",\"wants\",\"to\",\"take\",\"any\",\"some\",\"and\",\"really\",\"healthy\",\"life\"]",
        String[].class);
    pairs = gson.fromJson(
        "[[\"good\",\"nice\"],[\"good\",\"excellent\"],[\"good\",\"well\"],[\"good\",\"great\"],[\"fine\",\"nice\"],[\"fine\",\"excellent\"],[\"fine\",\"well\"],[\"fine\",\"great\"],[\"wonderful\",\"nice\"],[\"wonderful\",\"excellent\"],[\"wonderful\",\"well\"],[\"wonderful\",\"great\"],[\"extraordinary\",\"nice\"],[\"extraordinary\",\"excellent\"],[\"extraordinary\",\"well\"],[\"extraordinary\",\"great\"],[\"one\",\"a\"],[\"one\",\"an\"],[\"one\",\"unique\"],[\"one\",\"any\"],[\"single\",\"a\"],[\"single\",\"an\"],[\"single\",\"unique\"],[\"single\",\"any\"],[\"the\",\"a\"],[\"the\",\"an\"],[\"the\",\"unique\"],[\"the\",\"any\"],[\"some\",\"a\"],[\"some\",\"an\"],[\"some\",\"unique\"],[\"some\",\"any\"],[\"car\",\"vehicle\"],[\"car\",\"automobile\"],[\"car\",\"truck\"],[\"auto\",\"vehicle\"],[\"auto\",\"automobile\"],[\"auto\",\"truck\"],[\"wagon\",\"vehicle\"],[\"wagon\",\"automobile\"],[\"wagon\",\"truck\"],[\"have\",\"take\"],[\"have\",\"drink\"],[\"eat\",\"take\"],[\"eat\",\"drink\"],[\"entertain\",\"take\"],[\"entertain\",\"drink\"],[\"meal\",\"lunch\"],[\"meal\",\"dinner\"],[\"meal\",\"breakfast\"],[\"meal\",\"fruits\"],[\"super\",\"lunch\"],[\"super\",\"dinner\"],[\"super\",\"breakfast\"],[\"super\",\"fruits\"],[\"food\",\"lunch\"],[\"food\",\"dinner\"],[\"food\",\"breakfast\"],[\"food\",\"fruits\"],[\"brunch\",\"lunch\"],[\"brunch\",\"dinner\"],[\"brunch\",\"breakfast\"],[\"brunch\",\"fruits\"],[\"own\",\"have\"],[\"own\",\"possess\"],[\"keep\",\"have\"],[\"keep\",\"possess\"],[\"very\",\"super\"],[\"very\",\"actually\"],[\"really\",\"super\"],[\"really\",\"actually\"],[\"extremely\",\"super\"],[\"extremely\",\"actually\"]]",
        ArrayList.class);
    System.out.println("Case 5[true]:" + ss.areSentencesSimilar(word1, word2, pairs));

    word1 = gson.fromJson(
        "[\"this\",\"summer\",\"thomas\",\"get\",\"really\",\"very\",\"rich\",\"and\",\"have\",\"any\",\"actually\",\"wonderful\",\"and\",\"good\",\"truck\",\"every\",\"morning\",\"he\",\"drives\",\"an\",\"extraordinary\",\"truck\",\"around\",\"the\",\"nice\",\"city\",\"to\",\"eat\",\"some\",\"extremely\",\"extraordinary\",\"food\",\"as\",\"his\",\"meal\",\"but\",\"he\",\"only\",\"entertain\",\"an\",\"few\",\"well\",\"fruits\",\"as\",\"single\",\"lunch\",\"he\",\"wants\",\"to\",\"eat\",\"single\",\"single\",\"and\",\"really\",\"healthy\",\"life\"]",
        String[].class);
    word2 = gson.fromJson(
        "[\"this\",\"summer\",\"thomas\",\"get\",\"very\",\"extremely\",\"rich\",\"and\",\"possess\",\"the\",\"actually\",\"great\",\"and\",\"wonderful\",\"vehicle\",\"every\",\"morning\",\"he\",\"drives\",\"unique\",\"extraordinary\",\"automobile\",\"around\",\"unique\",\"fine\",\"city\",\"to\",\"drink\",\"single\",\"extremely\",\"nice\",\"meal\",\"as\",\"his\",\"super\",\"but\",\"he\",\"only\",\"entertain\",\"a\",\"few\",\"extraordinary\",\"food\",\"as\",\"some\",\"brunch\",\"he\",\"wants\",\"to\",\"take\",\"any\",\"some\",\"and\",\"really\",\"healthy\",\"life\"]",
        String[].class);
    pairs = gson.fromJson(
        "[[\"good\",\"wonderful\"],[\"nice\",\"well\"],[\"fine\",\"extraordinary\"],[\"excellent\",\"good\"],[\"wonderful\",\"nice\"],[\"well\",\"fine\"],[\"extraordinary\",\"excellent\"],[\"great\",\"wonderful\"],[\"one\",\"the\"],[\"a\",\"unique\"],[\"single\",\"some\"],[\"an\",\"one\"],[\"the\",\"a\"],[\"unique\",\"single\"],[\"some\",\"an\"],[\"any\",\"the\"],[\"car\",\"wagon\"],[\"vehicle\",\"car\"],[\"auto\",\"vehicle\"],[\"automobile\",\"auto\"],[\"wagon\",\"automobile\"],[\"truck\",\"wagon\"],[\"have\",\"have\"],[\"take\",\"take\"],[\"eat\",\"eat\"],[\"drink\",\"drink\"],[\"entertain\",\"entertain\"],[\"meal\",\"food\"],[\"lunch\",\"breakfast\"],[\"super\",\"brunch\"],[\"dinner\",\"meal\"],[\"food\",\"lunch\"],[\"breakfast\",\"super\"],[\"brunch\",\"dinner\"],[\"fruits\",\"food\"],[\"own\",\"own\"],[\"have\",\"have\"],[\"keep\",\"keep\"],[\"possess\",\"own\"],[\"very\",\"very\"],[\"super\",\"super\"],[\"really\",\"really\"],[\"actually\",\"actually\"],[\"extremely\",\"extremely\"]]",
        ArrayList.class);
    System.out.println("Case 6[false]:" + ss.areSentencesSimilar(word1, word2, pairs));

  }

}
