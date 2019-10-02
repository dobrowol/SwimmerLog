package dobrowol.swimmerlog.util;

import androidx.room.TypeConverter;

import java.util.Date;

public class Converters {
    public static final String DATE_FORMAT="YYYY-MM-dd";
    public static final String TIME_FORMAT="HH:mm";

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

}