package org.coner.crispy_fish.filetype.staging;

import org.coner.crispy_fish.filetype.ecf.EcfAssistant;

import java.io.*;
import java.nio.file.Path;
import org.apache.commons.io.FilenameUtils;

public class StagingFilenameFilter implements FilenameFilter {

    private final String eventFileOriginalStagingBaseName;
    private final String eventFileReacceptedStagingBaseName;

    public StagingFilenameFilter(EcfAssistant ecfAssistant, Path eventControlFile) {
        if (!ecfAssistant.isEcf(eventControlFile)) {
            throw new IllegalArgumentException("eventControlFile is not an ecf");
        }
        this.eventFileOriginalStagingBaseName = FilenameUtils.getBaseName(eventControlFile.toString());
        this.eventFileReacceptedStagingBaseName = StagingFilenames.getReacceptedFileBaseName(eventFileOriginalStagingBaseName);
    }

    public boolean accept(File dir, String name) {
        String baseName = FilenameUtils.getBaseName(name);

        if (name == null || baseName == null) {
            return false;
        }

        if (StagingFilenames.ORIGINAL_FILE_PATTERN.matcher(name).matches()) {
            if (eventFileOriginalStagingBaseName.equalsIgnoreCase(baseName)) {
                return true;
            }
            return false;
        }

        if (StagingFilenames.REACCEPTED_FILE_PATTERN.matcher(name).matches()) {
            if (eventFileReacceptedStagingBaseName.equalsIgnoreCase(baseName)) {
                return true;
            }
            return false;
        }

        return false;
    }
}
