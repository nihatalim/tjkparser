package tr.com.nihatalim.tjk.parser.models.runs;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RunHeaderModel {
    private String runName;
    private Integer runNumber;
    private String runDate;
    private List<String> properties;
    private String recordedDegree;
}
