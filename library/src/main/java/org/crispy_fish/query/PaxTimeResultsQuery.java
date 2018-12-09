package org.crispy_fish.query;

import org.crispy_fish.domain.Result;
import org.crispy_fish.domain.payload.Driver;
import org.crispy_fish.domain.payload.Run;
import org.coner.crispy_fish.filetype.ecf.EventControlFile;
import org.crispy_fish.filetype.staging.StagingLineDomainReader;
import org.crispy_fish.filetype.staging.StagingLineReader;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PaxTimeResultsQuery {

    private final EventControlFile eventControlFile;
    private final StagingLineReader<String> stagingLineReader;
    private final StagingLineDomainReader<String> stagingLineDomainReader;

    public PaxTimeResultsQuery(EventControlFile eventControlFile, StagingLineReader<String> stagingLineReader, StagingLineDomainReader<String> stagingLineDomainReader) {
        this.eventControlFile = eventControlFile;
        this.stagingLineReader = stagingLineReader;
        this.stagingLineDomainReader = stagingLineDomainReader;
    }

    public List<Result> query(List<String> stagingFileLines) throws QueryException {
        Map<Driver, Result> driverBestPaxResults = new HashMap<>();
        for (String stagingFileLine : stagingFileLines) {
            Driver driver = stagingLineDomainReader.readDriver(stagingFileLine);
            Run run = stagingLineDomainReader.readRun(stagingFileLine);
            if (driver == null || run == null) {
                continue;
            }
            run.timeScratchAsString = stagingLineReader.getRunPaxTime(stagingFileLine);
            run.timeScratchAsDuration = run.paxTime;
            Duration penaltyDuration = Duration.ZERO;
            switch (run.penaltyType) {
                case DID_NOT_FINISH:
                    penaltyDuration = Duration.ofDays(1);
                    break;
                case DISQUALIFIED:
                    penaltyDuration = Duration.ofDays(2);
                    break;
                case RERUN:
                    continue;
                case CLEAN:
                case CONE:
                    // cone penalties already included in pax time
                    break;
                default:
                    throw new IllegalStateException("Unrecognized penalty type: " + run.penaltyType);
            }
            if (run.paxTime == null && penaltyDuration != Duration.ZERO) {
                run.paxTime = Duration.ZERO;
            }
            run.timeScored = run.paxTime.plus(penaltyDuration);
            boolean shouldPutResult;
            if (driverBestPaxResults.containsKey(driver)) {
                Result bestResult = driverBestPaxResults.get(driver);
                shouldPutResult = run.timeScored.compareTo(bestResult.run.timeScored) < 0;
            } else {
                shouldPutResult = true;
            }
            if (!shouldPutResult) {
                continue;
            }
            Result result = new Result();
            result.driver = driver;
            result.run = run;
            driverBestPaxResults.put(driver, result);
        }
        List<Result> results = driverBestPaxResults.values().stream()
                .sorted((result1, result2) -> result1.run.timeScored.compareTo(result2.run.timeScored))
                .collect(Collectors.toList());
        int position = 1;
        for (Result result : results) {
            result.position = position++;
        }
        return results;
    }

}
