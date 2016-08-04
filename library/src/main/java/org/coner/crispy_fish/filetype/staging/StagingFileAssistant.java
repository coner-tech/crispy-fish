package org.coner.crispy_fish.filetype.staging;

import org.coner.crispy_fish.domain.EventDay;
import org.coner.crispy_fish.filetype.ecf.EventControlFile;

import java.util.regex.Pattern;
import org.apache.commons.io.FilenameUtils;


class StagingFileAssistant {
    StagingFilenameFilter buildStagingFilenameFilter(EventControlFile eventControlFile, EventDay eventDay) {
        if (eventControlFile == null) {
            throw new IllegalArgumentException("eventControlFile is null");
        }
        if (!eventControlFile.isTwoDayEvent() && eventDay == EventDay.TWO) {
            throw new IllegalArgumentException("eventControlFile is a one-day event, cannot build for EventDay.TWO");
        }
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
}
