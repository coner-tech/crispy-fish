package org.coner.crispy_fish.filetype.staging;

import org.coner.crispy_fish.domain.EventDay;
import org.coner.crispy_fish.filetype.ecf.EventControlFile;

import java.io.File;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;

public class StagingFileLocator {

    private final StagingFileAssistant stagingFileAssistant;

    public StagingFileLocator(StagingFileAssistant stagingFileAssistant) {
        this.stagingFileAssistant = stagingFileAssistant;
    }


    public Path locate(EventControlFile eventControlFile, EventDay eventDay) {
        if (eventControlFile == null) {
            throw new IllegalArgumentException("Programmer error: eventControlFile is null");
        }
        if (eventDay == null) {
            throw new IllegalArgumentException("Programmer error: eventDay is null");
        }
        if (!eventControlFile.isTwoDayEvent() && eventDay == EventDay.TWO) {
            throw new IllegalArgumentException("Cannot locate Path for StagingFile for Day 2 of 1-Day event");
        }

        File eventControlFileParent = eventControlFile.getPath().getParent().toFile();
        StagingFilenameFilter stagingFilenameFilter = stagingFileAssistant.buildStagingFilenameFilter(
                eventControlFile,
                eventDay
        );

        File[] files = eventControlFileParent.listFiles(stagingFilenameFilter);
        File selectedFile = selectFile(files);
        if (selectedFile != null) {
            return selectedFile.toPath();
        } else {
            return null;
        }
    }

    File selectFile(File[] files) {
        if (files == null) {
            return null;
        }
        switch (files.length) {
            case 0:
                return null;
            case 1:
                return files[0];
            default:
                Arrays.sort(files, comparator);
                return files[0];
        }
    }

    private final Comparator<File> comparator = (left, right) -> {
        if (left == null && right == null) {
            return 0;
        } else if (left == null) {
            return 1;
        } else if (right == null) {
            return -1;
        }

        String leftFileName = left.getName();
        String rightFileName = right.getName();
        Matcher leftOriginalFileMatcher = StagingFilenames.ORIGINAL_FILE_DAY_1.matcher(leftFileName);
        boolean leftOriginal = leftOriginalFileMatcher.matches();
        Matcher rightOriginalFileMatcher = StagingFilenames.ORIGINAL_FILE_DAY_1.matcher(rightFileName);
        boolean rightOriginal = rightOriginalFileMatcher.matches();

        if ((leftOriginal) && !(rightOriginal)) {
            return -1;
        }
        if ((rightOriginal) && !(leftOriginal)) {
            return 1;
        }
        if (leftOriginal && rightOriginal) {
            return 0;
        } else if (leftOriginal) {
            return 1;
        } else if (rightOriginal) {
            return -1;
        }
        return 0;
    };

}
