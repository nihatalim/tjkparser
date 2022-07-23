package tr.com.nihatalim.tjk.parser.service.runs;

import org.springframework.stereotype.Service;
import tr.com.nihatalim.tjk.parser.models.runs.RunHeaderModel;
import tr.com.nihatalim.tjk.parser.models.runs.RunModel;
import tr.com.nihatalim.tjk.parser.service.IStringParser;
import tr.com.nihatalim.tjk.parser.util.ParserUtil;

@Service
public class RunParser implements IStringParser<RunModel> {
    private final RunHeaderParser runHeaderParser;

    public RunParser(RunHeaderParser runHeaderParser) {
        this.runHeaderParser = runHeaderParser;
    }

    @Override
    public RunModel parse(String param) {
        final RunModel runModel = new RunModel();

        final String[] lines = ParserUtil.splitWithNewLine(param);

        final String runHeader = lines[0];

        final RunHeaderModel runHeaderModel = runHeaderParser.parse(runHeader);

        runModel.setRunHeaderModel(runHeaderModel);

        return runModel;
    }
}
