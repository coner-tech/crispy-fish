package org.crispy_fish.filetype.staging;

import org.crispy_fish.domain.PenaltyType;
import org.crispy_fish.domain.payload.Driver;
import org.crispy_fish.domain.payload.Run;

public class StagingLineDomainReader<L> {

    private final StagingFileAssistant stagingFileAssistant;
    private final StagingLineReader<L> stagingLineReader;

    public StagingLineDomainReader(StagingFileAssistant stagingFileAssistant, StagingLineReader<L> stagingLineReader) {
        this.stagingFileAssistant = stagingFileAssistant;
        this.stagingLineReader = stagingLineReader;
    }

    public Driver readDriver(L stagingLine) {
        Driver driver = new Driver();
        driver.name = stagingLineReader.getRegisteredDriverName(stagingLine);
        driver.car = stagingLineReader.getRegisteredDriverCar(stagingLine);
        driver.classing = stagingLineReader.getRegisteredDriverClass(stagingLine);
        driver.number = stagingLineReader.getRegisteredDriverNumber(stagingLine);
        return driver;
    }

    public Run readRun(L stagingFileLine) {
        Run run = new Run();
        String raw = stagingLineReader.getRunRawTime(stagingFileLine);
        String pax = stagingLineReader.getRunPaxTime(stagingFileLine);
        String penalty = stagingLineReader.getRunPenalty(stagingFileLine);
        try {
            run.rawTime = stagingFileAssistant.convertStagingTimeStringToDuration(raw);
            run.paxTime = stagingFileAssistant.convertStagingTimeStringToDuration(pax);
            run.penaltyType = stagingFileAssistant.convertStagingRunPenaltyStringToPenaltyType(penalty);
            if (run.penaltyType == PenaltyType.CONE) {
                run.cones = stagingFileAssistant.convertStagingRunPenaltyStringToConeCount(penalty);
            } else if (run.penaltyType == PenaltyType.CLEAN) {
                run.cones = 0;
            } else {
                run.cones = Run.CONES_UNKNOWN;
            }
        } catch (StagingLineException e) {
            return null;
        }
        return run;
    }
}
