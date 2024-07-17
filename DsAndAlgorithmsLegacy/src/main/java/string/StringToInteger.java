package string;

/**
 * Implement atoi which converts a string to an integer.
 * The function first discards as many whitespace characters as necessary until the first
 * non-whitespace character is found. Then, starting from this character, takes an optional initial
 * plus or minus sign followed by as many numerical digits as possible, and interprets them as a
 * numerical value.
 * The string can contain additional characters after those that form the integral number, which
 * are
 * ignored and have no effect on the behavior of this function.
 * If the first sequence of non-whitespace characters in str is not a valid integral number, or if
 * no such sequence exists because either str is empty or it contains only whitespace characters,
 * no
 * conversion is performed.
 * If no valid conversion could be performed, a zero value is returned.
 * Note:
 * Only the space character ' ' is considered as whitespace character.
 * Assume we are dealing with an environment which could only store integers within the 32-bit
 * signed integer range: [−231,  231 − 1]. If the numerical value is out of the range of
 * representable values, INT_MAX (231 − 1) or INT_MIN (−231) is returned.
 * Example 1:
 * Input: "42"
 * Output: 42
 * Example 2:
 * Input: "   -42"
 * Output: -42
 * Explanation: The first non-whitespace character is '-', which is the minus sign.
 * Then take as many numerical digits as possible, which gets 42.
 * Example 3:
 * Input: "4193 with words"
 * Output: 4193
 * Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.
 * Example 4:
 * Input: "words and 987"
 * Output: 0
 * Explanation: The first non-whitespace character is 'w', which is not a numerical
 * digit or a +/- sign. Therefore no valid conversion could be performed.
 * Example 5:
 * Input: "-91283472332"
 * Output: -2147483648
 * Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer.
 * Therefore INT_MIN (−231) is returned.
 * <p>
 * <p>
 * Algorithm:
 * We have to build numerical value by repeatedly shifting the result to left by one digit and
 * adding the next number at the unit place. Since numerical value is a decimal number represented
 * as base 10 in the number system, each digit can be expressed as multiples of powers of 10.
 * <p>
 * Example - "142" can be represented as 1 * (10^2) + 4 * (10^1) + 2 * (10^0)1∗(10
 * 2
 * )+4∗(10
 * 1
 * )+2∗(10
 * 0
 * )
 * <p>
 * 1 is at the hundredth place and left-shifted twice, 4 at tens place and shifted once, and so on.
 * However, this could lead to integer overflow or underflow conditions. Since integer must be
 * within the 32-bit signed integer range.
 * Handling overflow and underflow conditions
 * If the integer is positive, for 32 bit signed integer, \text{INT\_MAX}INT_MAX is 2147483647
 * (2^{31}-1)2147483647(2
 * 31
 * −1) To avoid integer overflow, we must ensure that it doesn't exceed this value. This condition
 * needs to be handled when the result is greater than or equal to \frac{\text{INT\_MAX}}{10}
 * 10
 * INT_MAX
 * (214748364)
 * Case 1). If \text{result} = \frac{\text{INT\_MAX}}{10}result=
 * 10
 * INT_MAX
 * , it would result in integer overflow if next integer character is greater than 7. (7 in this
 * case is last digit of \text{INT\_MAX} (2147483647)INT_MAX(2147483647)). We can use
 * \text{INT\_MAX} \% 10INT_MAX%10 to generically represent the last digit.
 * Case 2). If \text{result} > \frac{\text{INT\_MAX}}{10}result>
 * 10
 * INT_MAX
 * <p>
 * , we are sure that adding next number would result in integer overflow.
 * This holds for negative integer as well. In the 32-bit integer, \text{INT\_MIN}INT_MIN value is
 * -2147483648 (-2^{31})−2147483648(−2
 * 31
 * ). As the last digit is greater than 7 (\text{INT\_MAX} \% 10INT_MAX%10), integer underflow can
 * also be handled using the above approach.
 * We must return \text{INT\_MAX}INT_MAX in case of integer overflow and \text{INT\_MIN}INT_MIN in
 * case of integer underflow.
 *
 * @author kushal
 * <p>
 * References:
 * https://leetcode.com/problems/string-to-integer-atoi/
 */
public class StringToInteger {

  public int myAtoi(String str) {
    if (str == null || str.isEmpty()) {
      return 0;
    }

    str = str.trim();
    char firstChar = str.charAt(0);

    if (Character.isAlphabetic(firstChar)) {
      return 0;
    }

    int sign = 1;

    if (str.charAt(0) == '-') {
      sign = -1;
      str = str.substring(1);
    }

    if (str.charAt(0) == '+') {
      str = str.substring(1);
    }

    int i = 0;
    int result = 0;

    while (i <= str.length() - 1) {
      char c = str.charAt(i);
      if (!Character.isDigit(c)) {
        break;
      }

      if (result >= (Integer.MAX_VALUE / 10) && c > Integer.MAX_VALUE % 10) {
        return Integer.MAX_VALUE * sign;

      }

      result = result * 10;
      result = result + (c - '0');

      i++;
    }

    return result * sign;
  }

  public static void main(String[] args) {
    StringToInteger stringToInteger = new StringToInteger();
    System.out.println(stringToInteger.myAtoi("42"));
    System.out.println(stringToInteger.myAtoi("   -42"));
    System.out.println(stringToInteger.myAtoi("4193 with words"));
    System.out.println(stringToInteger.myAtoi("words and 987"));
    System.out.println(stringToInteger.myAtoi("-91283472332"));
    System.out.println(stringToInteger.myAtoi("91283472332"));
    System.out.println(stringToInteger.myAtoi("91283472332sdsd"));
    System.out.println(stringToInteger.myAtoi("ds91283472332sdsd"));
    System.out.println(stringToInteger.myAtoi("0"));
    System.out.println(stringToInteger.myAtoi(""));
    System.out.println(stringToInteger.myAtoi("--45--"));
    System.out.println(stringToInteger.myAtoi("asc"));


  }
}
