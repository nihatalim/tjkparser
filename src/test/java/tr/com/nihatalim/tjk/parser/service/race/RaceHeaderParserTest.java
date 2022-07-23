package tr.com.nihatalim.tjk.parser.service.race;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tr.com.nihatalim.tjk.parser.exception.ParseException;
import tr.com.nihatalim.tjk.parser.models.race.RaceHeaderModel;
import tr.com.nihatalim.tjk.parser.util.ParserUtil;

@ExtendWith(MockitoExtension.class)
class RaceHeaderParserTest {
    @InjectMocks
    private RaceHeaderParser raceHeaderParser;

    @Mock
    private RaceDayParser raceDayParser;

    @Test
    void whenFormatNotValid_thenThrowParseException() {
        final String header = "İstanbul;(30. Yarış Günü)20/06/2004";

        Assertions.assertThatExceptionOfType(ParseException.class)
                .isThrownBy(() -> raceHeaderParser.parse(header))
                .withMessageStartingWith("Race header");

        Mockito.verifyNoInteractions(raceDayParser);
    }

    @Test
    void test() {
        final String header = "İstanbul;(30. Yarış Günü);20/06/2004";

        Mockito.doReturn(30)
                .when(raceDayParser).parse(Mockito.anyString());

        final RaceHeaderModel actualModel = raceHeaderParser.parse(header);

        Mockito.verify(raceDayParser).parse(Mockito.anyString());

        Assertions.assertThat(actualModel.getRaceDate())
                .isEqualTo(ParserUtil.formatDate("20/06/2004"));

        Assertions.assertThat(actualModel.getLocation())
                .isEqualTo("İstanbul");

        Assertions.assertThat(actualModel.getRaceName())
                .isEqualTo("(30. Yarış Günü)");

        Assertions.assertThat(actualModel.getRaceDay())
                .isEqualTo(30);
    }
}
