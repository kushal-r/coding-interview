package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Date: 10/05/20
 *
 * @author Kushal Roy
 */
public class Solution1 {

  public static List<List<String>> getSelectionStatus(List<List<String>> applications) {

    if (applications == null || applications.size() <= 0) {
      return new ArrayList<>();
    }

    List<List<String>> op = new ArrayList<>();
    if (applications.size() == 1) {
      return applications.parallelStream().map(r -> {
        return new ArrayList<>(Arrays.asList(r.get(0), "REJECT", "NA"));
      }).collect(Collectors.toList());
    }

    List<List<String>> strikers = new ArrayList<>();
    List<List<String>> defenders = new ArrayList<>();

    List<List<String>> sortedStrikers = applications.parallelStream()
        .filter(a -> Double.parseDouble(a.get(1)) >= 5.8 && Double.parseDouble(a.get(2)) <= 23
            && Integer.parseInt(a.get(3)) >= 50)
        .sorted(new StrikerComparator()).collect(Collectors.toList());

    List<List<String>> sortedDefenders = applications.parallelStream()
        .filter(a -> Double.parseDouble(a.get(1)) >= 5.8 && Double.parseDouble(a.get(2)) <= 23
            && Integer.parseInt(a.get(4)) >= 30)
        .sorted(new DefenderComparator()).collect(Collectors.toList());

    int i = 0, j = 0;
    Map<String, String> tmpSelection = new HashMap<>();
    Set<List<String>> defSelection = new TreeSet<>(new DefenderComparator());
    Set<List<String>> strSelection = new TreeSet<>(new StrikerComparator());

    while (i < sortedStrikers.size() && j < sortedDefenders.size()) {

      while (i < sortedStrikers.size() && defSelection.contains(sortedStrikers.get(i))) {
        i++;
      }

      if (i < sortedStrikers.size()) {
        strSelection.add(sortedStrikers.get(i));
        i++;
      }

      while (j < sortedDefenders.size() && strSelection.contains(sortedDefenders.get(j))) {
        j++;
      }

      if (j < sortedDefenders.size()) {
        defSelection.add(sortedDefenders.get(j));
        j++;
      }
    }

    return applications.stream().map(a -> {
      List<String> pl = new ArrayList<>();
      if (strSelection.contains(a)) {
        pl.add(a.get(0));
        pl.add("SELECT");
        pl.add("STRIKER");
      } else if (defSelection.contains(a)) {
        pl.add(a.get(0));
        pl.add("SELECT");
        pl.add("DEFENDER");
      } else {
        pl.add(a.get(0));
        pl.add("REJECT");
        pl.add("NA");
      }
      return pl;
    }).sorted((x1, x2) -> x1.get(0).compareTo(x2.get(0))).collect(Collectors.toList());

  }


  public static void main(String[] args) {
    List<List<String>> applications = new ArrayList<>();
    applications.add(new ArrayList<>(Arrays.asList("Boateng", "6.1", "22", "24", "45")));
    applications.add(new ArrayList<>(Arrays.asList("Kaka", "6", "22", "1", "1")));
    applications.add(new ArrayList<>(Arrays.asList("Ronaldo", "5.8", "21", "120", "0")));
    applications.add(new ArrayList<>(Arrays.asList("Suarez", "5.9", "20", "100", "1")));

    System.out.println(getSelectionStatus(applications));

  }

  static class StrikerComparator implements Comparator<List<String>> {

    @Override
    public int compare(List<String> a1, List<String> a2) {

      return Integer.compare(Integer.parseInt(a2.get(3)), Integer.parseInt(a1.get(3)));
    }
  }

  static class DefenderComparator implements Comparator<List<String>> {

    @Override
    public int compare(List<String> a1, List<String> a2) {

      return Integer.compare(Integer.parseInt(a2.get(4)), Integer.parseInt(a1.get(4)));
    }
  }
}
