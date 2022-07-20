package tr.com.nihatalim.tjk.parser.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParserUtil {
    public static final String raceDateFormat = "dd/MM/yyyy";
    public static final String comma = ",";
    public static final String semicolon = ";";

    public static final SimpleDateFormat dateFormat = new SimpleDateFormat(raceDateFormat);

    public static Date formatDate(String date) {
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String formatDate(Date date) {
        return dateFormat.format(date);
    }
}
