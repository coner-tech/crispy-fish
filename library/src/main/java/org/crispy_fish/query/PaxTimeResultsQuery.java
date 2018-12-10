package org.crispy_fish.query;

import org.coner.crispy_fish.domain.Result;
import org.coner.crispy_fish.domain.Driver;
import org.coner.crispy_fish.domain.Numbers;
import org.coner.crispy_fish.domain.Run;
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
        Map<Numbers, Result> driverBestPaxResults = new HashMap<>();
        for (String stagingFileLine : stagingFileLines) {
            Driver driver = stagingLineDomainReader.readDriver(stagingFileLine);
            Run run = stagingLineDomainReader.readRun(stagingFileLine);
            if (driver == null || run == null) {
                continue;
            }
            run.setTimeScratchAsString(stagingLineReader.getRunPaxTime(stagingFileLine));
            run.setTimeScratchAsDuration(run.getPaxTime());
            Duration penaltyDuration = Duration.ZERO;
            switch (run.getPenaltyType()) {
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
                    throw new IllegalStateException("Unrecognized penalty type: " + run.getPenaltyType());
            }
            if (run.getPaxTime() == null && penaltyDuration != Duration.ZERO) {
                run.setPaxTime(Duration.ZERO);
            }
            run.setTimeScored(run.getPaxTime().plus(penaltyDuration));
            boolean shouldPutResult;
            if (driverBestPaxResults.containsKey(driver.getNumbers())) {
                Result bestResult = driverBestPaxResults.get(driver.getNumbers());
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
            driverBestPaxResults.put(driver.getNumbers(), result);
        }
        List<Result> results = driverBestPaxResults.values().stream()
                .sorted((result1, result2) -> result1.getRun().getTimeScored().compareTo(result2.getRun().getTimeScored()))
                .collect(Collectors.toList());
        int position = 1;
        for (Result result : results) {
            result.setPosition(position++);
        }
        return results;
    }

}
