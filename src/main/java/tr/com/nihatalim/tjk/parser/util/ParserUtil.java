package tr.com.nihatalim.tjk.parser.util;

import tr.com.nihatalim.tjk.parser.models.KeyValueModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParserUtil {
    public static final String raceDateFormat = "dd/MM/yyyy";
    public static final String comma = ",";
    public static final String dot = ".";
    public static final String semicolon = ";";
    public static final String colon = ":";
    public static final String newLine = "\n";
    public static final String runSeperator = "\r";


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

    public static String[] splitWithSemicolon(String param) {
        return param.split(semicolon);
    }

    public static String[] splitWithColon(String param) {
        return param.split(colon);
    }

    public static String[] splitWithNewLine(String param) {
        return param.split(newLine);
    }

    public static String[] split(String param, String delimeter) {
        int delimeterIndex = param.indexOf(delimeter);

        final String key = param.substring(0, delimeterIndex);

        final String value = param.substring(delimeterIndex + 1);

        return new String[]{key, value};
    }

    public static KeyValueModel toKvmByColon(String params) {
        return KeyValueModel.of(ParserUtil.split(params, colon));
    }

    public static KeyValueModel toKvmBySemicolon(String params) {
        return KeyValueModel.of(ParserUtil.split(params, semicolon));
    }

    public static KeyValueModel toKvmByDot(String params) {
        return KeyValueModel.of(ParserUtil.split(params, dot));
    }
}
