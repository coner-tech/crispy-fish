package org.coner.crispy_fish.filetype.staging;

import java.io.*;
import java.util.regex.Pattern;
import org.apache.commons.io.FilenameUtils;

public class StagingFilenameFilter implements FilenameFilter {

    private final String eventFileOriginalStagingBaseName;
    private final Pattern originalFilePattern;

    public StagingFilenameFilter(String eventControlFileOriginalStagingBaseName, Pattern stagingOriginalFilePattern) {
        this.eventFileOriginalStagingBaseName = eventControlFileOriginalStagingBaseName;
        this.originalFilePattern = stagingOriginalFilePattern;
    }

    public boolean accept(File dir, String name) {
        String baseName = FilenameUtils.getBaseName(name);

        if (name == null || baseName == null) {
            return false;
        }

        if (originalFilePattern.matcher(name).matches()) {
            if (eventFileOriginalStagingBaseName.equalsIgnoreCase(baseName)) {
                return true;
            }
            return false;
        }

        return false;
    }

    String getEventFileOriginalStagingBaseName() {
        return eventFileOriginalStagingBaseName;
    }

    Pattern getOriginalFilePattern() {
        return originalFilePattern;
    }
}
