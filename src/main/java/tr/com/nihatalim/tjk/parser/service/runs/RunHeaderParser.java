package tr.com.nihatalim.tjk.parser.service.runs;

import org.springframework.stereotype.Service;
import tr.com.nihatalim.tjk.parser.models.KeyValueModel;
import tr.com.nihatalim.tjk.parser.models.runs.RunHeaderModel;
import tr.com.nihatalim.tjk.parser.service.IStringParser;
import tr.com.nihatalim.tjk.parser.util.ParserUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RunHeaderParser implements IStringParser<RunHeaderModel> {
    @Override
    public RunHeaderModel parse(String param) {
        final RunHeaderModel runHeaderModel = new RunHeaderModel();

        final String[] params = ParserUtil.splitWithSemicolon(param);

        final String runName = params[0];
        final String lastParam = params[params.length - 1];

        final Integer runNumber = Optional.of(runName)
                .map(ParserUtil::toKvmByDot)
                .map(KeyValueModel::getKey)
                .map(Integer::parseInt)
                .orElse(null);

        final String runDate = Optional.of(runName)
                .map(ParserUtil::toKvmByColon)
                .map(KeyValueModel::getValue)
                .map(String::trim)
                .orElse(null);

        final String recordedDegree = Optional.of(lastParam)
                .map(ParserUtil::toKvmByColon)
                .map(KeyValueModel::getValue)
                .map(String::trim)
                .orElse(null);

        final List<String> properties = Arrays.stream(params)
                .skip(1)
                .limit(param.length() - 1)
                .map(String::trim)
                .collect(Collectors.toList());

        runHeaderModel.setRunName(runName);
        runHeaderModel.setRunNumber(runNumber);
        runHeaderModel.setRunDate(runDate);
        runHeaderModel.setRecordedDegree(recordedDegree);
        runHeaderModel.setProperties(properties);

        return runHeaderModel;
    }
}
