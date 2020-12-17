package time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author kushal
 * <p>
 * Date 17/12/20
 */
public class TimeFormat {

  private static final DateTimeFormatter uc4DateTimeFormatter = DateTimeFormatter
      .ofPattern("yyyy-MM-dd HH:mm:ss");

  public static void main(String[] args) throws ParseException {

    long l = Long.parseLong("1581055530178");
    Instant now = Instant.ofEpochMilli(l);
    ZoneId zoneId = ZoneId.of("America/Los_Angeles");
    ZonedDateTime zdt = now.atZone(zoneId);
    System.out.println(zdt.toString());

    LocalDateTime ldt = LocalDateTime.parse("2020-02-21 05:16:27", uc4DateTimeFormatter);
    ZonedDateTime zdt1 = ldt.atZone(ZoneId.of("America/Los_Angeles"));
    // long milli = LocalDateTime.parse("2020-02-21 05:16:27", uc4DateTimeFormatter)
    // .toInstant(ZoneOffset.of(ZoneId.SHORT_IDS.get("PST"))).toEpochMilli();
    System.out.println(zdt1.toInstant().toEpochMilli());
    System.out.println(System.getProperty("user.dir"));

    System.out.println(ZonedDateTime.now(ZoneId.of("UTC")).toString());
    System.out.println(ZonedDateTime.now(ZoneId.of("-07:00")).toString());

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss Z");
    String formattedString = ZonedDateTime.now(ZoneId.of("UTC")).format(formatter);
    System.out.println(formattedString);
    System.out.println(ZonedDateTime.now(ZoneId.of("America/Los_Angeles")).format(formatter));

    System.out.println(
        ZonedDateTime.now(ZoneId.of("UTC")).withZoneSameInstant(ZoneId.of("America/Los_Angeles"))
            .toLocalDateTime().toString());

    System.out.println(
        ZonedDateTime.now(ZoneId.of("UTC")).withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime()
            .toString());

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss Z");
    DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    ZonedDateTime startDate = ZonedDateTime.parse("2020-02-26T11:02:41 +0000", dateTimeFormatter);
    System.out.print(
        startDate.withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime()
            .format(dateTimeFormatter2));

    OffsetDateTime odt = OffsetDateTime.now();

    LocalDateTime endLDT = odt.withOffsetSameInstant(ZoneOffset.of("+0000")).toLocalDateTime();
    System.out.println(endLDT.format(DateTimeFormatter.ofPattern("yyyy-MM-dd " + "HH:mm:ss")));
    System.out.println(endLDT.format(DateTimeFormatter.ISO_DATE_TIME));
    System.out.println(odt);

    String t = "Apr 15, 2020 12:14:17 AM";
    DateTimeFormatter CTM_STATUS_FORMATTER = DateTimeFormatter
        .ofPattern("MMM dd',' yyyy hh:mm:ss a");
    SimpleDateFormat displayFormat = new SimpleDateFormat("MMM DD',' YYYY h:mm:ss a");

    LocalDateTime li = LocalDateTime.parse(t, CTM_STATUS_FORMATTER);
    System.out.println(li.toString());

    DateTimeFormatter CTM_ORDER_FORMATTER = DateTimeFormatter.ofPattern("yyMMdd");

    LocalDate date = LocalDate.parse("200424", CTM_ORDER_FORMATTER);

    System.out.println(date.toString());

    DateTimeFormatter ti = DateTimeFormatter.ofPattern("hh:mm:ss");

    System.out.println(LocalTime.now());

    String g = "5:00:0";

    int h = g.indexOf(':', 0);
    int m = g.indexOf(':', h + 1);

    char[] c = g.toCharArray();
    c[h] = 'H';
    c[m] = 'M';

    System.out.println("PT" + String.valueOf(c));

    System.out.println(Duration.parse("PT5H21M17S").getSeconds());

    Duration d = Duration.parse("PT" + String.valueOf(c) + 'S');

    System.out.println(d.getSeconds());

    String dateString = "2010-03-01 00:00:31.166482+00:00";
    String pattern = "yyyy-MM-dd HH:mm:ssssss";
    DateTimeFormatter AIRFLOW_DATE_FORMATTER = DateTimeFormatter
        .ofPattern("yyyy-MM-dd HH:mm:ss[.SSSSSS][XXX]");

    System.out.println(LocalDateTime.parse("2010-03-01 00:00:31+00:00", AIRFLOW_DATE_FORMATTER));

    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    Date dd = sdf.parse(dateString);
    System.out.println(dd);

    DateTimeFormatter dt = DateTimeFormatter
        .ofPattern("yyyy-MM-dd HH:mm:ss[XXX][X]");
    LocalDateTime dateTime2 = LocalDateTime
        .parse("2020-05-31 22:55:40+00:00", dt);

    System.out.println(dateTime2.toString());

    String i = "Mon Jun 08 00:06:22 IST 2020";
    SimpleDateFormat sdf22 = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
    Date dd22 = sdf22.parse(i);
    System.out.println(dd22);

    DateTimeFormatter f = DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss z uuuu");
    ZonedDateTime zdt11 = ZonedDateTime.parse(i, f);

    System.out.println(ZonedDateTime.parse(dateString, AIRFLOW_DATE_FORMATTER).toLocalDateTime()
        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

    System.out.println(LocalDateTime.ofInstant(dd22.toInstant(),
        ZoneId.of("UTC")).toString());

    DateTimeFormatter AIRFLOW_DATE_TIME_FORMATTER = DateTimeFormatter
        .ofPattern("yyyy-MM-dd HH:mm:ss[.SSSSSS][XXX]");
    DateTimeFormatter sdd = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    Date d2 = new Date();

    String s = LocalDateTime.parse("2020-06-08 13:37:29.261995+00:00", AIRFLOW_DATE_TIME_FORMATTER)
        .format(sdd);
    System.out.println(s);
    System.out
        .println(LocalDateTime.parse(s, sdd).atZone(ZoneId.of("UTC")).toInstant().toEpochMilli());

    LocalDateTime sx = LocalDateTime
        .parse("2010-03-01 00:00:31.166482+00:00", AIRFLOW_DATE_FORMATTER).atZone(ZoneId.of("UTC"))
        .toLocalDateTime();

    System.out.println(sx.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

    String startTime = "Jun 26, 2020 2:38:18 AM";

    DateTimeFormatter CONTROLM_DATE_FORMATTER = DateTimeFormatter
        .ofPattern("MMM dd',' yyyy h:mm:ss a");

    System.out.println(LocalDateTime.parse(startTime, CONTROLM_DATE_FORMATTER));

    System.out.println(startDate.withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime()
        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

    Set<String> als = new HashSet<>(Arrays.asList("t1", "t2"));
    String cc = "test22";

    Map<String, String> map = als.stream().collect(Collectors.toMap(String::toString, e -> cc));

    System.out.println(map.toString());

    DateTimeFormatter DTF = DateTimeFormatter.ofPattern("MM-dd-uuuu HH:mm:ss");
    System.out.print(LocalDateTime.now(ZoneId.of("America/Los_Angeles")).format(DTF));


  }

}

