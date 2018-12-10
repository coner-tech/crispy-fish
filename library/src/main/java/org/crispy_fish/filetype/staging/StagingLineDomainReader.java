package org.crispy_fish.filetype.staging;

import org.coner.crispy_fish.domain.PenaltyType;
import org.coner.crispy_fish.domain.Driver;
import org.coner.crispy_fish.domain.Numbers;
import org.coner.crispy_fish.domain.Run;

public class StagingLineDomainReader<L> {

    private final StagingFileAssistant stagingFileAssistant;
    private final StagingLineReader<L> stagingLineReader;

    public StagingLineDomainReader(StagingFileAssistant stagingFileAssistant, StagingLineReader<L> stagingLineReader) {
        this.stagingFileAssistant = stagingFileAssistant;
        this.stagingLineReader = stagingLineReader;
    }

    public Driver readDriver(L stagingLine) {
        Driver driver = new Driver();
        driver.setName(stagingLineReader.getRegisteredDriverName(stagingLine));
        driver.setCar(stagingLineReader.getRegisteredDriverCar(stagingLine));
        Numbers numbers = new Numbers();
        numbers.setClassing(stagingLineReader.getRegisteredDriverClass(stagingLine));
        numbers.setNumber(stagingLineReader.getRegisteredDriverNumber(stagingLine));
        driver.setNumbers(numbers);
        return driver;
    }

    public Run readRun(L stagingFileLine) {
        Run run = new Run();
        String raw = stagingLineReader.getRunRawTime(stagingFileLine);
        String pax = stagingLineReader.getRunPaxTime(stagingFileLine);
        String penalty = stagingLineReader.getRunPenalty(stagingFileLine);
        try {
            run.setRawTime(stagingFileAssistant.convertStagingTimeStringToDuration(raw));
            run.setPenaltyType(stagingFileAssistant.convertStagingRunPenaltyStringToPenaltyType(penalty));
            try {
                run.setPaxTime(stagingFileAssistant.convertStagingTimeStringToDuration(pax));
            } catch (StagingLineException e) {
                switch (run.getPenaltyType()) {
                    case CONE:
                        throw new StagingLineException("Unable to parse pax time from coned run", e);
                    case DID_NOT_FINISH:
                        break;
                    case DISQUALIFIED:
                        break;
                    case RERUN:
                        break;
                    case CLEAN:
                        throw new StagingLineException("Unable to parse pax time from clean run", e);
                    default:
                        throw new IllegalStateException("Unrecognized run.penaltyType: " + run.getPenaltyType(), e);
                }
            }
            if (run.getPenaltyType() == PenaltyType.CONE) {
                run.setCones(stagingFileAssistant.convertStagingRunPenaltyStringToConeCount(penalty));
            } else if (run.getPenaltyType() == PenaltyType.CLEAN) {
                run.setCones(0);
            } else {
                run.setCones(null);
            }
        } catch (StagingLineException e) {
            return null;
        }
        return run;
    }
}
