package tr.com.nihatalim.tjk.parser.models.runs;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
public class RunModel {
    private RunHeaderModel runHeaderModel;
    private String name;
    private int runNumber;
    private Date date;
    private Map<String, String> properties;
}
