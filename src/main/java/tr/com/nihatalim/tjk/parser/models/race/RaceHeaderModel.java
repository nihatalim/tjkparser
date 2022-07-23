package tr.com.nihatalim.tjk.parser.models.race;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RaceHeaderModel {
    private String location;
    private String raceName;
    private Integer raceDay;
    private Date raceDate;
}
