package org.coner.crispy_fish.filetype.staging;

import org.apache.commons.io.FilenameUtils;
import org.coner.crispy_fish.filetype.ecf.EventControlFile;

import java.io.File;
import java.io.FilenameFilter;

public class StagingFilenameFilter implements FilenameFilter {

    private final String eventFileOriginalStagingBaseName;
    private final String eventFileReacceptedStagingBaseName;

    public StagingFilenameFilter(EventControlFile eventControlFile, StagingFileAssistant stagingFileAssistant) {
        this.eventFileOriginalStagingBaseName = FilenameUtils.getBaseName(eventControlFile.getPath().toString());
        this.eventFileReacceptedStagingBaseName = stagingFileAssistant.getReacceptedFileBaseName(eventFileOriginalStagingBaseName);
    }

    StagingFilenameFilter(String eventFileOriginalStagingBaseName, String eventFileReacceptedStagingBaseName) {
        this.eventFileOriginalStagingBaseName = eventFileOriginalStagingBaseName;
        this.eventFileReacceptedStagingBaseName = eventFileReacceptedStagingBaseName;
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
