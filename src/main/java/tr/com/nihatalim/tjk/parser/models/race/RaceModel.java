package tr.com.nihatalim.tjk.parser.models.race;

import lombok.Getter;
import lombok.Setter;
import tr.com.nihatalim.tjk.parser.models.runs.RunModel;

import java.util.List;

@Getter
@Setter
public class RaceModel {
    private RaceHeaderModel raceHeader;
    private List<RunModel> runs;
}
