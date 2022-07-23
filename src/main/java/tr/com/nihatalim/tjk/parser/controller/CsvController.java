package tr.com.nihatalim.tjk.parser.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.nihatalim.tjk.parser.models.race.RaceModel;
import tr.com.nihatalim.tjk.parser.service.race.RaceParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/csv")
public class CsvController {
    private final RaceParser raceParser;

    public CsvController(RaceParser raceParser) {
        this.raceParser = raceParser;
    }

    @GetMapping
    public RaceModel get() throws IOException {
        final String csvFile = new String(Files.readAllBytes(Paths.get("target.csv")));

        return raceParser.parse(csvFile);
    }
}
