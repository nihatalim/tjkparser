package tr.com.nihatalim.tjk.parser.service.race;

import org.springframework.stereotype.Service;
import tr.com.nihatalim.tjk.parser.models.race.RaceHeaderModel;
import tr.com.nihatalim.tjk.parser.models.race.RaceModel;
import tr.com.nihatalim.tjk.parser.models.runs.RunModel;
import tr.com.nihatalim.tjk.parser.service.IStringParser;
import tr.com.nihatalim.tjk.parser.service.runs.RunParser;
import tr.com.nihatalim.tjk.parser.util.ParserUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RaceParser implements IStringParser<RaceModel> {
    private final RaceHeaderParser raceHeaderParser;
    private final RunParser runParser;

    public RaceParser(RaceHeaderParser raceHeaderParser, RunParser runParser) {
        this.raceHeaderParser = raceHeaderParser;
        this.runParser = runParser;
    }

    @Override
    public RaceModel parse(String param) {
        final RaceModel raceModel = new RaceModel();

        final String[] lines = param.split(ParserUtil.newLine);

        final String raceHeader = lines[0];

        final RaceHeaderModel raceHeaderModel = raceHeaderParser.parse(raceHeader);

        raceModel.setRaceHeader(raceHeaderModel);

        final List<String> runs = getRuns(lines);

        final List<RunModel> runModels = runs.stream()
                .map(runParser::parse)
                .collect(Collectors.toList());

        raceModel.setRuns(runModels);

        return raceModel;
    }

    private List<String> getRuns(String[] lines) {
        final int lineSize = lines.length;

        final List<String> runStrings = new ArrayList<>();
        int startIndex = 1;

        for (int i = startIndex; i < lines.length; i++) {
            boolean isEnoughLineExists = lineSize - startIndex > 5;

            if (!isEnoughLineExists) {
                break;
            }

            boolean isLineEmpty = lines[i].equals(ParserUtil.runSeperator);

            if (isLineEmpty) {
                final String runString = collectAsString(lines, startIndex, i);
                runStrings.add(runString);

                startIndex = i + 1;
            }
        }

        return runStrings;
    }

    private String collectAsString(String[] lines, int startIndex, int endIndex) {
        return Arrays.stream(lines)
                .skip(startIndex)
                .limit(endIndex - startIndex)
                .collect(Collectors.joining(ParserUtil.newLine));
    }

}
