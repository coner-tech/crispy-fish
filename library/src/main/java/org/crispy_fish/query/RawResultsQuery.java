package org.crispy_fish.query;

import com.google.common.base.Strings;
import java.util.*;
import org.crispy_fish.domain.EventDay;
import org.crispy_fish.filetype.ecf.EventControlFile;
import org.crispy_fish.filetype.staging.*;

public abstract class RawResultsQuery<L> {

    private final EventControlFile eventControlFile;
    private final StagingFileLocator stagingFileLocator;
    private final StagingLineReader<L> stagingLineReader;

    public RawResultsQuery(EventControlFile eventControlFile, StagingFileLocator stagingFileLocator, StagingLineReader<L> stagingLineReader) {
        this.eventControlFile = eventControlFile;
        this.stagingFileLocator = stagingFileLocator;
        this.stagingLineReader = stagingLineReader;
    }

    public List<RawResult> query(EventDay eventDay) throws QueryException {
        stagingFileLocator.locate(eventControlFile, eventDay);
        List<L> stagingFileLines;
        try {
            stagingFileLines = getStagingFileContents();
        } catch (Exception e) {
            throw new QueryException("Failed to get staging file contents", e);
        }
        Map<Driver, RawResult> driverBestRawResults = new HashMap<>();
        for (L stagingFileLine : stagingFileLines) {
            RawResult rawResult = new RawResult();
            rawResult.driver = new Driver();
            rawResult.times = new RawResult.Times();
            rawResult.driver.classing = stagingLineReader.getRegisteredClass(stagingFileLine);
            rawResult.driver.number = stagingLineReader.getRegisteredNumber(stagingFileLine);
            rawResult.times.scratch = stagingLineReader.getRunRawTime(stagingFileLine);
            if (Strings.isNullOrEmpty(rawResult.driver.classing) || Strings.isNullOrEmpty(rawResult.driver.number)) {
                // line lacks a driver, skip it
                continue;
            }
        }
    }

    protected abstract List<L> getStagingFileContents() throws Exception;

}
