package org.crispy_fish.sample.results;

import com.google.common.base.Strings;
import io.airlift.airline.Command;
import org.crispy_fish.datatype.underscore_pairs.SimpleStringUnderscorePairReader;
import org.crispy_fish.filetype.ecf.EventControlFile;
import org.crispy_fish.filetype.staging.SimpleStringStagingLineReader;
import org.crispy_fish.filetype.staging.StagingFileAssistant;
import org.crispy_fish.filetype.staging.StagingFileLocator;
import org.crispy_fish.filetype.staging.StagingLineReader;
import org.crispy_fish.sample.domain.Driver;
import org.crispy_fish.sample.domain.Result;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Command(name = "raw", description = "Prints the raw results")
public class RawResultsCommand extends BaseResultsCommand implements Runnable {

    @Override
    public void run() {
        Path eventControlFilePath = Paths.get(file);
        EventControlFile eventControlFile = new EventControlFile(eventControlFilePath, eventDays == 2);
        StagingFileLocator stagingFileLocator = new StagingFileLocator(new StagingFileAssistant());
        Path stagingFilePath = stagingFileLocator.locate(eventControlFile, eventDay);
        List<String> stagingFileLines;
        try {
            stagingFileLines = Files.readAllLines(stagingFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Map<Driver, Result> driverBestResults = new HashMap<>();
        StagingLineReader<String> reader = new SimpleStringStagingLineReader(new SimpleStringUnderscorePairReader());
        for (String stagingFileLine : stagingFileLines) {
            Result result = new Result();
            result.driver = new Driver();
            result.driver.classing = reader.getRegisteredClass(stagingFileLine);
            result.driver.number = reader.getRegisteredNumber(stagingFileLine);
            if (Strings.isNullOrEmpty(result.driver.classing) || Strings.isNullOrEmpty(result.driver.number)) {
                // line lacks a driver, skip it
                continue;
            }
            result.driver.name = reader.getRegisteredDriverName(stagingFileLine);
            result.driver.car = reader.getRegisteredDriverCar(stagingFileLine);
            result.timeLiteral = reader.getRunRawTime(stagingFileLine);
            Duration parsedTimeLiteral;
            try {
                parsedTimeLiteral = Duration.parse("PT" + result.timeLiteral + "S");
            } catch (DateTimeParseException e) {
                // line lacks a timeLiteral or invalid, skip it
                continue;
            }
            result.penalty = reader.getRunPenalty(stagingFileLine);
            if (Strings.isNullOrEmpty(result.penalty)) {
                // clean, no penalty
                result.timeForSort = parsedTimeLiteral;
                result.timeForDisplay = result.timeLiteral;
            } else {
                switch (result.penalty.toUpperCase()) {
                    case "DNF":
                        // Did Not Finish
                        result.timeForSort = Duration.ofDays(1);
                        result.timeForDisplay = "DNF";
                        break;
                    case "DSQ":
                    case "RRN":
                        continue;
                    default:
                        int cones;
                        try {
                            cones = Integer.parseUnsignedInt(result.penalty);
                        } catch (NumberFormatException e) {
                            // Unexpected value as penalty
                            e.printStackTrace();
                            continue;
                        }
                        int conePenaltySeconds = cones * conePenalty;
                        result.timeForSort = parsedTimeLiteral.plusSeconds(conePenaltySeconds);
                        result.timeForDisplay = String.format(
                                "%d.%d",
                                result.timeForSort.getSeconds(),
                                TimeUnit.NANOSECONDS.toMillis(result.timeForSort.getNano())
                        );
                }
            }
            Result driverBestResult = driverBestResults.get(result.driver);
            if (driverBestResult != null) {
                if (result.timeForSort.compareTo(driverBestResult.timeForSort) < 0) {
                    driverBestResults.put(result.driver, result);
                }
            } else {
                driverBestResults.put(result.driver, result);
            }
        }
        List<Result> results = driverBestResults.values().stream()
                .sorted((result, t1) -> result.timeForSort.compareTo(t1.timeForSort))
                .collect(Collectors.toList());
        int position = 1;
        for (Result result : results) {
            result.position = position++;
        }
        final String format = "|%3s|%7s|%3s|%24s|%8s|\n";
        System.out.format(format, "Pos", "Class", "No.", "Name", "Time");
        results.stream()
                .forEachOrdered(result -> System.out.format(
                        format,
                        String.valueOf(result.position),
                        result.driver.classing,
                        result.driver.number,
                        result.driver.name,
                        result.timeForDisplay
                ));
    }
}
