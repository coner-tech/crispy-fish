package org.crispy_fish.sample.results;

import io.airlift.airline.Command;
import org.crispy_fish.datatype.underscore_pairs.SimpleStringUnderscorePairReader;
import org.crispy_fish.domain.Result;
import org.crispy_fish.filetype.ecf.EventControlFile;
import org.crispy_fish.filetype.staging.*;
import org.crispy_fish.query.PaxTimeResultsQuery;
import org.crispy_fish.query.QueryException;
import org.crispy_fish.sample.ui.DisplayResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Command(name = "pax", description = "Prints the pax time results")
public class PaxTimeResultsCommand extends BaseResultsCommand implements Runnable {

    @Override
    public void run() {
        Path eventControlFilePath = Paths.get(file);
        EventControlFile eventControlFile = new EventControlFile(eventControlFilePath, eventDays == 2, conePenalty);
        StagingFileAssistant stagingFileAssistant = new StagingFileAssistant();
        StagingFileLocator stagingFileLocator = new StagingFileLocator(stagingFileAssistant);
        Path stagingFilePath = stagingFileLocator.locate(eventControlFile, eventDay);
        List<String> stagingFileLines;
        try {
            stagingFileLines = Files.readAllLines(stagingFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        StagingLineReader<String> reader = new SimpleStringStagingLineReader(new SimpleStringUnderscorePairReader());
        PaxTimeResultsQuery paxTimeResultsQuery = new PaxTimeResultsQuery(
                eventControlFile,
                reader,
                new StagingLineDomainReader<>(stagingFileAssistant, reader)
        );
        List<Result> domainResults;
        try {
            domainResults = paxTimeResultsQuery.query(stagingFileLines);
        } catch (QueryException e) {
            e.printStackTrace();
            return;
        }
        List<DisplayResult> displayResults = domainResults.stream()
                .map(DisplayResult::new)
                .collect(Collectors.toList());
        final String format = "|%3s|%7s|%3s|%24s|%8s|%4s|\n";
        System.out.format(format, "Pos", "Class", "No.", "Name", "Time", "Pty.");
        displayResults.stream()
                .forEachOrdered(displayResult -> {
                    System.out.format(
                            format,
                            String.valueOf(displayResult.domainResult.position),
                            displayResult.domainResult.driver.classing,
                            displayResult.domainResult.driver.number,
                            displayResult.domainResult.driver.name,
                            displayResult.timeForDisplay,
                            displayResult.penaltyForDisplay
                    );
                });
    }
}
