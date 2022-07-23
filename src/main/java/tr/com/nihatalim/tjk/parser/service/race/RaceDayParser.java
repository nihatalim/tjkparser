package tr.com.nihatalim.tjk.parser.service.race;

import org.springframework.stereotype.Service;
import tr.com.nihatalim.tjk.parser.service.IStringParser;
import tr.com.nihatalim.tjk.parser.util.RegexUtil;

@Service
public class RaceDayParser implements IStringParser<Integer> {
    @Override
    public Integer parse(String param) {
        return RegexUtil.getFirstMatch(RegexUtil.onlyDigitsRegex, param)
                .map(Integer::parseInt)
                .orElse(null);
    }
}
