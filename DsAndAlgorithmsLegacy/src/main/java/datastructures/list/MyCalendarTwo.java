package datastructures.list;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * Implement a MyCalendarTwo class to store your events. A new event can be added if adding the
 * event will not cause a triple booking.
 * <p>
 * Your class will have one method, book(int start, int end). Formally, this represents a booking
 * on
 * the half open interval [start, end), the range of real numbers x such that start <= x < end.
 * <p>
 * A triple booking happens when three events have some non-empty intersection (ie., there is some
 * time that is common to all 3 events.)
 * <p>
 * For each call to the method MyCalendar.book, return true if the event can be added to the
 * calendar successfully without causing a triple booking. Otherwise, return false and do not add
 * the event to the calendar.
 * <p>
 * Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start,
 * end)
 * <p>
 * Example 1:
 * <p>
 * MyCalendar();
 * MyCalendar.book(10, 20); // returns true
 * MyCalendar.book(50, 60); // returns true
 * MyCalendar.book(10, 40); // returns true
 * MyCalendar.book(5, 15); // returns false
 * MyCalendar.book(5, 10); // returns true
 * MyCalendar.book(25, 55); // returns true
 * Explanation:
 * The first two events can be booked.  The third event can be double booked.
 * The fourth event (5, 15) can't be booked, because it would result in a triple booking.
 * The fifth event (5, 10) can be booked, as it does not use time 10 which is already double
 * booked.
 * The sixth event (25, 55) can be booked, as the time in [25, 40) will be double booked with the
 * third event;
 * the time [40, 50) will be single booked, and the time [50, 55) will be double booked with the
 * second event.
 * <p>
 * <p>
 * Algorithm 1:
 * Evidently, two events [s1, e1) and [s2, e2) do not conflict if and only if one of them starts
 * after the other one ends: either e1 <= s2 OR e2 <= s1. By De Morgan's laws, this means the
 * events
 * conflict when s1 < e2 AND s2 < e1.
 * <p>
 * If our event conflicts with a double booking, it's invalid. Otherwise, we add conflicts with the
 * calendar to our double bookings, and add the event to our calendar.
 * <p>
 * <p>
 * Algorithm 2:
 * When booking a new event [start, end), count delta[start]++ and delta[end]--. When processing
 * the
 * values of delta in sorted order of their keys, the running sum active is the number of events
 * open at that time. If the sum is 3 or more, that time is (at least) triple booked.
 * <p>
 * Complexity:
 * Time - O(n) - traverse delta n time and we do this for another m events . So overall complexity
 * become O(mn) = O(n^2)
 * Space - O(n)
 *
 * @author kushal
 * <p>
 * Refenrences:
 * https://leetcode.com/problems/my-calendar-ii/
 */
public class MyCalendarTwo {

  private List<Event> calender;
  private List<Event> overlapped;

  private Map<Integer, Integer> delta = new TreeMap<>();

  //------------------ Method 1 -----------------------//

  public boolean bookMethod1(int start, int end) {
    for (Event e : overlapped) {
      if (start < e.end && end > e.start) {
        return false;
      }
    }

    for (Event e : calender) {
      if (end > e.start && start < e.end) {
        overlapped.add(new Event(Math.max(start, e.start), Math.min(end, e.end)));
      }
    }
    calender.add(new Event(start, end));
    return true;
  }

  //------------------ Method 2 -----------------------//

  public boolean book(int start, int end) {
    delta.put(start, delta.getOrDefault(start, 0) + 1);
    delta.put(end, delta.getOrDefault(end, 0) - 1);

    //At this stage the map will have equal number of start and end count (e.g +2,-2)
    int active = 0;
    for (int k : delta.values()) {

      //calculate the total sum till now
      active += k;

      //If there is an overlap the count will increase, and if crosses 3 will return false
      if (active >= 3) {
        delta.put(start, delta.get(start) - 1);
        delta.put(end, delta.get(end) + 1);
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    MyCalendarTwo mct = new MyCalendarTwo();
    System.out.println(mct.book(10, 20)); // returns true
    System.out.println(mct.book(50, 60)); // returns true
    System.out.println(mct.book(10, 40)); // returns true
    System.out.println(mct.book(5, 15)); // returns false
    System.out.println(mct.book(5, 10)); // returns true
    System.out.println(mct.book(25, 55)); // returns true

    mct = new MyCalendarTwo();

  }

  public MyCalendarTwo() {
    this.calender = new ArrayList<>();
    this.overlapped = new ArrayList<>();
  }

  static class Event {

    int start;
    int end;

    public Event(int start, int end) {
      this.start = start;
      this.end = end;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Event event = (Event) o;
      return start == event.start &&
          end == event.end;
    }

    @Override
    public int hashCode() {
      return Objects.hash(start, end);
    }
  }

}
