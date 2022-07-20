package tr.com.nihatalim.tjk.parser.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
class RaceDayParserTest {
    @InjectMocks
    private RaceDayParser raceDayParser;

    @Test
    void whenRaceDayNotFoundInRaceName_thenReturnNull() {
        final String raceName = "(X. Yarış Günü)";
        final Integer actualRaceDay = raceDayParser.parse(raceName);

        Assertions.assertThat(actualRaceDay).isNull();
    }

    @MethodSource("argumentsStream")
    @ParameterizedTest
    void whenRaceDayFound_thenAssertExpectedRaceDay(String raceName, Integer expectedResult) {
        final Integer actualRaceDay = raceDayParser.parse(raceName);

        Assertions.assertThat(actualRaceDay).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> argumentsStream() {
        return Stream.of(
                Arguments.of("(30. Yarış Günü)", 30),
                Arguments.of("(230. Yarış Günü)", 230),
                Arguments.of("(5. Yarış Günü)", 5),
                Arguments.of("(20. Yarış Günü)", 20),
                Arguments.of("(990. Yarış Günü)", 990),
                Arguments.of("(9. Yarış Günü)", 9),
                Arguments.of("(17. Yarış Günü)", 17),
                Arguments.of("(61. Yarış Günü)", 61)
        );
    }
}