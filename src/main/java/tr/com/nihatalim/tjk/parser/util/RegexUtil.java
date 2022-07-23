package tr.com.nihatalim.tjk.parser.util;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {
    public static final String onlyDigitsRegex = "[0-9]+";
    public static Optional<String> getFirstMatch(String pattern, String value) {
        Matcher matcher = Pattern.compile(pattern)
                .matcher(value);

        if (!matcher.find()) {
            return Optional.empty();
        }

        return Optional.of(value.substring(matcher.start(), matcher.end()));
    }
}
