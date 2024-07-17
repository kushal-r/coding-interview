package datastructures.tree.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Given a list of reviews, list of keywords and a number k, we have to find the top k keywords
 * occurring in the reviews in order from most to least frequently occurring. If keywords are
 * occurring equal number of times then sort alphabetically.
 * <p>
 * Algorithm:
 *
 * <p>
 * Date: 16/04/20
 *
 * @author Kushal Roy
 * References:
 * https://leetcode.com/discuss/interview-question/542597/
 */
public class TopKCompetitors {

  public List<String> findTopKKeywords(int topNCompetitors,
      List<String> competitors,
      List<String> reviews) {

    //Set<String> competitorSet = new HashSet<>(competitors);
    Set<String> competitorSet =
        competitors.parallelStream().map(String::toLowerCase).collect(Collectors.toSet());
    Map<String, Integer> count = new HashMap<>();
    List<String> topK = new ArrayList<>();
    //Iterate over all the review keywords and and get count of occurrences
    reviews.parallelStream().map(r -> r.split("\\W"))
        .forEach(words -> {
          for (String k : words) {
            if (competitorSet.contains(k)) {
              if (count.containsKey(k)) {
                int mentions = count.get(k) + 1;
                count.put(k.toLowerCase(), mentions);
              } else {
                count.put(k.toLowerCase(), 1);
              }
            }
          }
        });

    Queue<Entry<String, Integer>> maxHeap = new PriorityQueue<>(new KeyComparator());
    maxHeap.addAll(count.entrySet());

    while (!maxHeap.isEmpty() && topNCompetitors-- > 0) {
      topK.add(maxHeap.poll().getKey());
    }

    return topK;
  }


  public static void main(String[] args) {
    int k1 = 2;
    String[] keywords1 = {"newshop", "shopnow", "afshion", "fashionbeats", "mymarket", "tcellular"};
    String[] reviews1 = {
        "newshop is providing good services in the city; everyone should use newshop",
        "best services by newshop", "fashionbeats has great services in the city",
        "I am proud to have fashionbeats", "mymarket has awesome services",
        "Thanks Newshop for the quick delivery."};

    TopKCompetitors tc = new TopKCompetitors();
    System.out.println(tc.findTopKKeywords(k1, Arrays.asList(keywords1), Arrays.asList(reviews1)));
  }

  static class KeyComparator implements Comparator<Entry<String, Integer>> {

    @Override
    public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
      if (o1.getValue().equals(o2.getValue())) {
        return o1.getKey().compareTo(o2.getKey());
      } else {
        return o1.getValue().compareTo(o2.getValue());
      }
    }
  }
}
