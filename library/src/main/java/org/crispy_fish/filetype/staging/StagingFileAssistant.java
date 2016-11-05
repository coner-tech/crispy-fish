package org.crispy_fish.filetype.staging;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.apache.commons.io.FilenameUtils;
import org.crispy_fish.domain.EventDay;
import org.crispy_fish.domain.PenaltyType;
import org.crispy_fish.filetype.ecf.EventControlFile;

import javax.annotation.Nonnull;
import java.time.Duration;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;


public class StagingFileAssistant {
    StagingFilenameFilter buildStagingFilenameFilter(@Nonnull EventControlFile eventControlFile, @Nonnull EventDay eventDay) {
        Preconditions.checkNotNull(eventControlFile, "eventControlFile must not be null");
        Preconditions.checkNotNull(eventDay, "eventDay must not be null");
        Preconditions.checkArgument(
                eventControlFile.isTwoDayEvent() || eventDay == EventDay.ONE,
                "eventControlFile is a one-day event, won't build StagingFilenameFilter for eventDay == %s",
                eventDay.toString()
        );
        Pattern originalFilePattern;
        String originalFileBaseName = getOriginalFileBaseName(eventControlFile);
        switch (eventDay) {
            case ONE:
                originalFilePattern = StagingFilenames.ORIGINAL_FILE_DAY_1;
                break;
            case TWO:
                originalFilePattern = StagingFilenames.ORIGINAL_FILE_DAY_2;
                break;
            default:
                throw new IllegalStateException("Unrecognized eventDay: " + eventDay);
        }
        return new StagingFilenameFilter(
                originalFileBaseName,
                originalFilePattern
        );
    }

    String getOriginalFileBaseName(EventControlFile eventControlFile) {
        return FilenameUtils.getBaseName(eventControlFile.getPath().toString());
    }

    Duration convertStagingTimeStringToDuration(String stagingTime) throws StagingLineException {
        try {
             return Duration.parse("PT" + stagingTime + "S");
        } catch (DateTimeParseException e) {
            throw new StagingLineException(
                    "Staging time " + stagingTime + " cannot be parsed to Duration",
                    e
            );
        }
    }

    PenaltyType convertStagingRunPenaltyStringToPenaltyType(String penalty) throws StagingLineException {
        if (!Strings.isNullOrEmpty(penalty)) {
            switch (penalty.toUpperCase()) {
                case "DNF":
                    return PenaltyType.DID_NOT_FINISH;
                case "DSQ":
                    return PenaltyType.DISQUALIFIED;
                case "RRN":
                    return PenaltyType.RERUN;
            }
            int cones = convertStagingRunPenaltyStringToConeCount(penalty);
            if (cones > 0) {
                return PenaltyType.CONE;
            }
        }
        return PenaltyType.CLEAN;
    }

    int convertStagingRunPenaltyStringToConeCount(String penalty) throws StagingLineException {
        try {
            return Integer.parseUnsignedInt(penalty);
        } catch (NumberFormatException e) {
            throw new StagingLineException(
                    "Unable to convert staging penalty cones " + penalty + " to cone count",
                    e
            );
        }
    }
}
