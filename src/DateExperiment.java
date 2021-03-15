import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.*;

public class DateExperiment {
    final static int gap = 6;
    static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) throws ParseException {
        Date date = new Date();
        Set<DayOfWeek> dayOfWeekSet = new TreeSet<>();
        dayOfWeekSet.add(DayOfWeek.MONDAY);
        dayOfWeekSet.add(DayOfWeek.THURSDAY);
        dayOfWeekSet.add(DayOfWeek.SATURDAY);
        System.out.println(dayOfWeekSet.toString());
        HashMap<DayOfWeek, Date> map = getDateAndDayMapping(date,dayOfWeekSet);
        System.out.println(Arrays.asList(map));
    }

    public static Date getDatefromString(String date) throws ParseException {
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        return formatter.parse(date);
    }

    public static StartWeekDateAndEndWeekDate getStartWeekDateAndEndWeekDate(Date date) throws ParseException {
        StartWeekDateAndEndWeekDate dateObject = new StartWeekDateAndEndWeekDate();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // Set the calendar to monday of the current week
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        // Print dates of the current week starting on Monday
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        dateObject.setStartDate(getDatefromString(df.format(c.getTime())));
        c.add(Calendar.DATE, gap);
        dateObject.setEndDate(getDatefromString(df.format(c.getTime())));
        return dateObject;
    }

    public static HashMap<DayOfWeek, Date> getDateAndDayMapping(Date date, Set<DayOfWeek> days) throws ParseException {
        HashMap<DayOfWeek, Date> result = new HashMap<>();
        StartWeekDateAndEndWeekDate startDate = getStartWeekDateAndEndWeekDate(date);
        Date firstDate = startDate.getStartDate();
        Calendar c = Calendar.getInstance();
        c.setTime(firstDate);
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        for (DayOfWeek dayOfWeek : days) {
            c.setTime(firstDate);
            switch (dayOfWeek) {
                case MONDAY:
                    result.put(dayOfWeek, c.getTime());
                    break;
                case TUESDAY:
                    c.add(Calendar.DATE, 1);
                    result.put(dayOfWeek, c.getTime());
                    break;
                case WEDNESDAY:
                    c.add(Calendar.DATE, 2);
                    result.put(dayOfWeek, c.getTime());
                    break;
                case THURSDAY:
                    c.add(Calendar.DATE, 3);
                    result.put(dayOfWeek, c.getTime());
                    break;
                case FRIDAY:
                    c.add(Calendar.DATE, 4);
                    result.put(dayOfWeek, c.getTime());
                    break;
                case SATURDAY:
                    c.add(Calendar.DATE, 5);
                    result.put(dayOfWeek, c.getTime());
                    break;
                case SUNDAY:
                    c.add(Calendar.DATE, 6);
                    result.put(dayOfWeek, c.getTime());
            }
        }
        return result;
    }
}
