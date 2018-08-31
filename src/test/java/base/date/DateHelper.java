package base.date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.MutableDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class DateHelper {

    public static final String ISO_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String API_PATTERN = "yyyy-MM-dd'T'HH:mm";
    public static final String WL_API_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";
    private static final int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    private DateTimeZone timeZone;
    private DateTimeFormatter dtf;
    private MutableDateTime mdt;
    private String pattern;

    public DateHelper() {
        mdt = MutableDateTime.now();
    }

    public DateHelper(XMLGregorianCalendar xmlDate, String pattern) {
        dtf = DateTimeFormat.forPattern(pattern).withDefaultYear(currentYear);
        mdt = new MutableDateTime(dtf.parseDateTime(xmlDate.toString()));
        Calendar.getInstance().get(Calendar.YEAR);
    }

    public DateHelper(XMLGregorianCalendar xmlDate, DateTimeZone timeZone, String pattern) {
        dtf = DateTimeFormat.forPattern(pattern).withDefaultYear(currentYear);
        mdt = new MutableDateTime(dtf.parseDateTime(xmlDate.toString()));
        mdt.setZone(timeZone);
    }


    public DateHelper(String date, String pattern) {
        this.pattern = pattern;
        dtf = DateTimeFormat.forPattern(pattern).withDefaultYear(currentYear);
        if (date != null)
            mdt = new MutableDateTime(dtf.parseDateTime(date));
    }

    public DateHelper(int stamp, String pattern) {
        this.pattern = pattern;
        dtf = DateTimeFormat.forPattern(pattern).withDefaultYear(currentYear);
        mdt = new MutableDateTime(stamp * 1000L);
    }

    public DateHelper(String pattern) {

        this.pattern = pattern;
        mdt = MutableDateTime.now();
        dtf = DateTimeFormat.forPattern(pattern).withDefaultYear(currentYear);
    }

    public DateHelper(String date, DateTimeZone timeZone, String pattern) {

        this.pattern = pattern;
        this.timeZone = timeZone;
        dtf = DateTimeFormat.forPattern(pattern).withDefaultYear(currentYear);
        mdt = new MutableDateTime(dtf.parseDateTime(date));
        mdt.setZone(timeZone);
    }

    public DateHelper(DateHelper dateHelper) {
        mdt = new MutableDateTime(dateHelper.mdt);
    }

    public DateHelper addDays(int days) {
        mdt.addDays(days);
        return this;
    }

    public DateHelper addHours(int hours) {
        mdt.addHours(hours);
        return this;
    }

    public DateHelper setHours(int hours) {
        mdt.setHourOfDay(hours);
        return this;
    }

    public DateHelper setMinutes(int minutes) {
        mdt.setMinuteOfHour(minutes);
        return this;
    }

    public DateHelper toZero() {
        mdt.setSecondOfMinute(0);
        mdt.setMillisOfSecond(0);
        return this;
    }

    public DateHelper timeZone(DateTimeZone timeZone) {
        this.timeZone = timeZone;
        return this;
    }

    public MutableDateTime getDateTime() {
        return mdt;
    }

    public DateHelper format(String pattern) {
        this.pattern = pattern;
        dtf = DateTimeFormat.forPattern(pattern).withDefaultYear(2018);
        return this;
    }

    public DateTimeFormatter format() {
        return dtf;
    }

    public DateTimeZone getTimeZone() {
        return timeZone;
    }

    public GregorianCalendar toGregorianCalendar() {
        return mdt.toGregorianCalendar();
    }

    @Override
    public String toString() {
        return mdt.toString(pattern);
    }

    public String toString(String pattern) {
        return mdt.toString(pattern);
    }

    public DateTime toDateTime() {
        mdt.setMillisOfSecond(0);
        mdt.setSecondOfMinute(0);
        return mdt.toDateTime();
    }

    public DateHelper addTime(String time) {

        if (time != null) {
            String[] parsed = time.split(":");
            mdt.addHours(Integer.parseInt(parsed[0]));
            mdt.addMinutes(Integer.parseInt(parsed[1]));
        }
        return this;
    }
}
