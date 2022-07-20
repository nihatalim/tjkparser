package tr.com.nihatalim.tjk.parser.service;

import org.springframework.stereotype.Service;
import tr.com.nihatalim.tjk.parser.exception.ParseException;
import tr.com.nihatalim.tjk.parser.models.RaceHeaderModel;
import tr.com.nihatalim.tjk.parser.util.ParserUtil;

@Service
public class RaceHeaderParser implements IStringParser<RaceHeaderModel>{
    private static final int HEADER_DEFAULT_PARAM_COUNT = 3;

    private final RaceDayParser raceDayParser;

    public RaceHeaderParser(RaceDayParser raceDayParser) {
        this.raceDayParser = raceDayParser;
    }

    @Override
    public RaceHeaderModel parse(String param) {
        final RaceHeaderModel raceHeaderModel = new RaceHeaderModel();

        final String[] values = param.split(ParserUtil.semicolon);

        if (values.length != HEADER_DEFAULT_PARAM_COUNT) {
            throw new ParseException("Race header cannot parsed because not valid. Header should be like following format: İstanbul;(30. Yarış Günü);20/06/2004");
        }

        final String location = values[0];
        final String raceName = values[1];
        final String raceDate = values[2];
        final Integer raceDay = raceDayParser.parse(raceName);

        raceHeaderModel.setLocation(location);
        raceHeaderModel.setRaceName(raceName);
        raceHeaderModel.setRaceDay(raceDay);
        raceHeaderModel.setRaceDate(ParserUtil.formatDate(raceDate));

        return raceHeaderModel;
    }
}
