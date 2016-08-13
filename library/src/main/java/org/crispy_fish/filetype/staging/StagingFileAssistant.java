package org.crispy_fish.filetype.staging;

import com.google.common.base.Preconditions;
import org.apache.commons.io.FilenameUtils;
import org.crispy_fish.domain.EventDay;
import org.crispy_fish.filetype.ecf.EventControlFile;

import javax.annotation.Nonnull;
import java.util.regex.Pattern;


class StagingFileAssistant {
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
}
