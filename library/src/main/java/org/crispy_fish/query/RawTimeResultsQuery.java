package org.crispy_fish.query;

import org.coner.crispy_fish.domain.Result;
import org.coner.crispy_fish.domain.Driver;
import org.coner.crispy_fish.domain.Numbers;
import org.coner.crispy_fish.domain.Run;
import org.coner.crispy_fish.filetype.ecf.EventControlFile;
import org.crispy_fish.filetype.staging.StagingLineDomainReader;
import org.crispy_fish.filetype.staging.StagingLineReader;

import java.time.Duration;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RawTimeResultsQuery {

    private final EventControlFile eventControlFile;
    private final StagingLineReader<String> stagingLineReader;
    private final StagingLineDomainReader<String> stagingLineDomainReader;

    public RawTimeResultsQuery(EventControlFile eventControlFile, StagingLineReader<String> stagingLineReader, StagingLineDomainReader<String> stagingLineDomainReader) {
        this.eventControlFile = eventControlFile;
        this.stagingLineReader = stagingLineReader;
        this.stagingLineDomainReader = stagingLineDomainReader;
    }

    public List<Result> query(List<String> stagingFileLines) throws QueryException {
        Map<Numbers, Result> driverBestRawResults = new HashMap<>();
        for (String stagingFileLine : stagingFileLines) {
            Driver driver = stagingLineDomainReader.readDriver(stagingFileLine);
            Run run = stagingLineDomainReader.readRun(stagingFileLine);
            if (driver == null || run == null) {
                continue;
            }
            run.setTimeScratchAsString(stagingLineReader.getRunRawTime(stagingFileLine));
            run.setTimeScratchAsDuration(run.getRawTime());
            Duration penaltyDuration = Duration.ZERO;
            switch (run.getPenaltyType()) {
                case CONE:
                     penaltyDuration = Duration.ofSeconds(run.getCones() * eventControlFile.getConePenalty());
                    break;
                case DID_NOT_FINISH:
                    penaltyDuration = Duration.ofDays(1);
                    break;
                case DISQUALIFIED:
                    penaltyDuration = Duration.ofDays(2);
                    break;
                case RERUN:
                    continue;
                case CLEAN:
                    break;
                default:
                    throw new IllegalStateException("Unrecognized penalty type: " + run.getPenaltyType());
            }
            run.setTimeScored(run.getRawTime().plus(penaltyDuration));
            boolean shouldPutResult;
            if (driverBestRawResults.containsKey(driver.getNumbers())) {
                Result bestResult = driverBestRawResults.get(driver.getNumbers());
                shouldPutResult = run.getTimeScored().compareTo(bestResult.getRun().getTimeScored()) < 0;
            } else {
                shouldPutResult = true;
            }
            if (!shouldPutResult) {
                continue;
            }
            Result result = new Result();
            result.setDriver(driver);
            result.setRun(run);
            driverBestRawResults.put(driver.getNumbers(), result);
        }
        List<Result> results = driverBestRawResults.values().stream()
                .sorted(Comparator.comparing(result -> result.getRun().getTimeScored()))
                .collect(Collectors.toList());
        int position = 1;
        for (Result result : results) {
            result.setPosition(position++);
        }
        return results;
    }

}
